package com.jobsity.tenpinbowling;

import com.jobsity.tenpinbowling.scoring.Scoreboard;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.nio.file.Path;
import java.nio.file.Paths;

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
    @Profile({"dev", "prod"})
    public CommandLineRunner getCommandLineRunner() {
        return this::run;
    }

    private void run(String[] args) {
        Path pathToInputFile = Paths.get(args[0]);
        reader.createScoreBoardsFromFile(pathToInputFile).stream()
        .peek(Scoreboard::calculateScores)
        .forEach(printer::printOutScoreBoard);
    }
}
