package com.jobsity.tenpinbowling.io;

import com.jobsity.tenpinbowling.scoreboard.Scoreboard;
import com.jobsity.tenpinbowling.scoreboard.ScoreboardBuilder;
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

import static java.util.stream.Collectors.toCollection;

@Component
public class Reader {

    private static final Logger LOGGER = LoggerFactory.getLogger(Reader.class);

    public Set<Scoreboard> createScoreBoardsFromFile(Path pathToInputFile) {
        Map<String, ScoreboardBuilder> scoreboardBuilders = new LinkedHashMap<>(); // Keeping insertion order
        try (BufferedReader bufferedReader = Files.newBufferedReader(pathToInputFile)) {
            bufferedReader.lines()
            .map(line -> line.split("\t"))
            .peek(data -> createScoreboardBuilderIfNeeded(scoreboardBuilders, data))
            .forEach(data -> sendScoreToScoreboardBuilder(scoreboardBuilders, data));
        } catch (IOException e) {
            LOGGER.error("Fatal error while reading input file.", e);
            throw new RuntimeException(e);
        }
        return scoreboardBuilders
        .values()
        .stream()
        .map(ScoreboardBuilder::build)
        .collect(toCollection(LinkedHashSet::new));
    }

    private void createScoreboardBuilderIfNeeded(Map<String, ScoreboardBuilder> scoreboardBuilders, String[] data) {
        String name = data[0];
        scoreboardBuilders.computeIfAbsent(name, ScoreboardBuilder::new);
    }

    private void sendScoreToScoreboardBuilder(Map<String, ScoreboardBuilder> scoreboardBuilders, String[] data) {
        String name = data[0];
        String scoreStr = data[1];
        ScoreboardBuilder scoreboardBuilder = scoreboardBuilders.get(name);
        if ("F".equals(scoreStr)) {
            scoreboardBuilder.acceptFoul();
        } else { // Try to convert score to an Integer
            Integer score;
            try {
                score = Integer.valueOf(scoreStr);
            } catch (Exception e) {
                LOGGER.error("Fatal error updating score board. " + name + "\t" + scoreStr);
                throw new RuntimeException(e);
            }
            scoreboardBuilder.acceptScore(score);
        }
    }
}
