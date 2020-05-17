package com.jobsity.tenpinbowling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

@Component
public class Reader {

    private static final Logger LOGGER = LoggerFactory.getLogger(Reader.class);

    public Set<ScoreBoard> createScoreBoardsFromFile(Path pathToInputFile) {
        Map<String, ScoreBoard> scoreBoards = new LinkedHashMap<>(); // Keeping insertion order
        try (BufferedReader bufferedReader = Files.newBufferedReader(pathToInputFile)) {
            bufferedReader.lines()
            .map(line -> line.split("\t"))
            .peek(data -> createScoreBoardIfNeeded(scoreBoards, data))
            .forEach(data -> updateScoreBoard(scoreBoards, data));
        } catch (IOException e) {
            LOGGER.error("Fatal error while reading input file.", e);
            throw new RuntimeException(e);
        }
        return new LinkedHashSet<>(scoreBoards.values());
    }

    private void createScoreBoardIfNeeded(Map<String, ScoreBoard> scoreBoards, String[] data) {
        String name = data[0];
        scoreBoards.computeIfAbsent(name, ScoreBoard::new);
    }

    private void updateScoreBoard(Map<String, ScoreBoard> scoreBoards, String[] data) {
        String name = data[0];
        String scoreStr = data[1];
        ScoreBoard scoreBoard = scoreBoards.get(name);
        if ("F".equals(scoreStr)) {
            scoreBoard.acceptFoul();
        } else { // Try to convert score to an Integer
            Integer score;
            try {
                score = Integer.valueOf(scoreStr);
            } catch (Exception e) {
                LOGGER.error("Fatal error updating score board. " + name + "\t" + scoreStr);
                throw new RuntimeException(e);
            }
            scoreBoard.acceptScore(score);
        }
    }
}
