package com.jobsity.tenpinbowling;

import com.jobsity.tenpinbowling.io.Printer;
import com.jobsity.tenpinbowling.io.Reader;
import com.jobsity.tenpinbowling.scoreboard.Scoreboard;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

@SpringBootApplication
public class TenPinBowlingApplication {

    private final Reader reader;

    private final Printer printer;

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
        return args -> {
            Path pathToInputFile = Paths.get(args[0]);
            Set<Scoreboard> scoreboards = reader.createScoreBoardsFromFile(pathToInputFile);
            scoreboards.forEach(Scoreboard::calculateScores);
            printer.printHeader();
            scoreboards.forEach(printer::printOutScoreBoard);
        };
    }
}
