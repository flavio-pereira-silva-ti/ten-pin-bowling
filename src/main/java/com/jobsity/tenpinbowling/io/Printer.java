package com.jobsity.tenpinbowling.io;

import com.jobsity.tenpinbowling.scoreboard.Scoreboard;
import org.springframework.stereotype.Component;

@Component
public class Printer {

    private static final String HEADER = "Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10";

    public void printHeader() {
        print(HEADER);
    }

    public void printOutScoreBoard(Scoreboard scoreboard) {
        print(scoreboard.getName());
        StringBuilder pinfalls = new StringBuilder("Pinfalls");
        StringBuilder score = new StringBuilder("Score");
        scoreboard.getFrames().forEach(frame -> {
            pinfalls.append(frame.pinfallsToString());
            score.append(frame.totalToString());
        });
        print(pinfalls.toString());
        print(score.toString());
    }

    private void print(String line) {
        System.out.println(line);
    }
}
