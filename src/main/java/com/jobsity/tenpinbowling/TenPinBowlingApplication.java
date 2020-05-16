package com.jobsity.tenpinbowling;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

@SpringBootApplication
public class TenPinBowlingApplication {

    private Reader reader;

    private Printer printer;

    public static void main(String[] args) {
        SpringApplication.run(TenPinBowlingApplication.class, args);
    }

    public TenPinBowlingApplication(Reader reader, Printer printer) {
        this.reader = reader;
        this.printer = printer;
    }

    @Bean
    public CommandLineRunner getCommandLineRunner() {
        return this::run;
    }

    private void run(String[] args) {
//        Path pathToInputFile = Paths.get(args[0]);
//        Set<ScoreBoard> scoreBoards = reader.createScoreBoardsFromFile(pathToInputFile);
//        scoreBoards.stream().peek(ScoreBoard::calculateScores).forEach(printer::printOutScoreBoard);
    }
}
