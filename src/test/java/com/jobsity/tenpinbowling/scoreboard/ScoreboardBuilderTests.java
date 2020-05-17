package com.jobsity.tenpinbowling.scoreboard;

import com.jobsity.tenpinbowling.frame.Frame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ScoreboardBuilderTests {

    private ScoreboardBuilder scoreboardBuilder;

    @BeforeEach
    void beforeEach() {
        scoreboardBuilder = new ScoreboardBuilder("Steve");
    }

    @Test
    void scoreBoardShouldAcceptUpTo21Results() {
        // 1st frame
        scoreboardBuilder.acceptScore(1);
        scoreboardBuilder.acceptScore(9);

        // 2nd frame
        scoreboardBuilder.acceptScore(0);
        scoreboardBuilder.acceptScore(1);

        // 3rd frame
        scoreboardBuilder.acceptScore(1);
        scoreboardBuilder.acceptScore(1);

        // 4th frame
        scoreboardBuilder.acceptScore(1);
        scoreboardBuilder.acceptScore(1);

        // 5th frame
        scoreboardBuilder.acceptScore(5);
        scoreboardBuilder.acceptScore(5);

        // 6th frame
        scoreboardBuilder.acceptScore(1);
        scoreboardBuilder.acceptScore(1);

        // 7th frame
        scoreboardBuilder.acceptScore(1);
        scoreboardBuilder.acceptScore(1);

        // 8th frame
        scoreboardBuilder.acceptScore(1);
        scoreboardBuilder.acceptScore(1);

        // 9th frame
        scoreboardBuilder.acceptScore(1);
        scoreboardBuilder.acceptScore(1);

        // 10th frame
        scoreboardBuilder.acceptScore(1);
        scoreboardBuilder.acceptScore(9);
        scoreboardBuilder.acceptScore(1);

        List<Frame> frames = scoreboardBuilder.build().getFrames();
        Frame frame = frames.get(0);
        assertEquals("\t1\t/", frame.pinfallsToString());
        frame = frames.get(1);
        assertEquals("\t0\t1", frame.pinfallsToString());
        frame = frames.get(2);
        assertEquals("\t1\t1", frame.pinfallsToString());
        frame = frames.get(3);
        assertEquals("\t1\t1", frame.pinfallsToString());
        frame = frames.get(4);
        assertEquals("\t5\t/", frame.pinfallsToString());
        frame = frames.get(5);
        assertEquals("\t1\t1", frame.pinfallsToString());
        frame = frames.get(6);
        assertEquals("\t1\t1", frame.pinfallsToString());
        frame = frames.get(7);
        assertEquals("\t1\t1", frame.pinfallsToString());
        frame = frames.get(8);
        assertEquals("\t1\t1", frame.pinfallsToString());
        frame = frames.get(9);
        assertEquals("\t1\t/\t1", frame.pinfallsToString());
    }

    @Test
    void scoreBoardShouldAcceptUpTo12Strikes() {
        // 1st frame
        scoreboardBuilder.acceptScore(10);

        // 2nd frame
        scoreboardBuilder.acceptScore(10);

        // 3rd frame
        scoreboardBuilder.acceptScore(10);

        // 4th frame
        scoreboardBuilder.acceptScore(10);

        // 5th frame
        scoreboardBuilder.acceptScore(10);

        // 6th frame
        scoreboardBuilder.acceptScore(10);

        // 7th frame
        scoreboardBuilder.acceptScore(10);

        // 8th frame
        scoreboardBuilder.acceptScore(10);

        // 9th frame
        scoreboardBuilder.acceptScore(10);

        // 10th frame
        scoreboardBuilder.acceptScore(10);
        scoreboardBuilder.acceptScore(10);
        scoreboardBuilder.acceptScore(10);

        List<Frame> frames = scoreboardBuilder.build().getFrames();
        Frame frame = frames.get(0);
        assertEquals("\t\tX", frame.pinfallsToString());
        frame = frames.get(1);
        assertEquals("\t\tX", frame.pinfallsToString());
        frame = frames.get(2);
        assertEquals("\t\tX", frame.pinfallsToString());
        frame = frames.get(3);
        assertEquals("\t\tX", frame.pinfallsToString());
        frame = frames.get(4);
        assertEquals("\t\tX", frame.pinfallsToString());
        frame = frames.get(5);
        assertEquals("\t\tX", frame.pinfallsToString());
        frame = frames.get(6);
        assertEquals("\t\tX", frame.pinfallsToString());
        frame = frames.get(7);
        assertEquals("\t\tX", frame.pinfallsToString());
        frame = frames.get(8);
        assertEquals("\t\tX", frame.pinfallsToString());
        frame = frames.get(9);
        assertEquals("\tX\tX\tX", frame.pinfallsToString());
    }

    @Test
    void scoreBoardShouldNotAcceptScoreHigherThan10OrLowerThan0() {
        assertThrows(IllegalArgumentException.class, () -> scoreboardBuilder.acceptScore(11));
        assertThrows(IllegalArgumentException.class, () -> scoreboardBuilder.acceptScore(-1));
    }

    @Test
    void totalScoreCalculationShouldSucceedGivenValidScores() {
        // 1st frame
        scoreboardBuilder.acceptScore(10);

        // 2nd frame
        scoreboardBuilder.acceptScore(7);
        scoreboardBuilder.acceptScore(3);

        // 3rd frame
        scoreboardBuilder.acceptScore(9);
        scoreboardBuilder.acceptScore(0);

        // 4th frame
        scoreboardBuilder.acceptScore(10);

        // 5th frame
        scoreboardBuilder.acceptScore(0);
        scoreboardBuilder.acceptScore(8);

        // 6th frame
        scoreboardBuilder.acceptScore(8);
        scoreboardBuilder.acceptScore(2);

        // 7th frame
        scoreboardBuilder.acceptFoul();
        scoreboardBuilder.acceptScore(6);

        // 8th frame
        scoreboardBuilder.acceptScore(10);

        // 9th frame
        scoreboardBuilder.acceptScore(10);

        // 10th frame
        scoreboardBuilder.acceptScore(10);
        scoreboardBuilder.acceptScore(8);
        scoreboardBuilder.acceptScore(1);

        Scoreboard scoreboard = scoreboardBuilder.build();
        scoreboard.calculateScores();
        List<Frame> frames = scoreboard.getFrames();
        Frame frame = frames.get(0);
        assertEquals("\t\t20", frame.totalToString());
        frame = frames.get(1);
        assertEquals("\t\t39", frame.totalToString());
        frame = frames.get(2);
        assertEquals("\t\t48", frame.totalToString());
        frame = frames.get(3);
        assertEquals("\t\t66", frame.totalToString());
        frame = frames.get(4);
        assertEquals("\t\t74", frame.totalToString());
        frame = frames.get(5);
        assertEquals("\t\t84", frame.totalToString());
        frame = frames.get(6);
        assertEquals("\t\t90", frame.totalToString());
        frame = frames.get(7);
        assertEquals("\t\t120", frame.totalToString());
        frame = frames.get(8);
        assertEquals("\t\t148", frame.totalToString());
        frame = frames.get(9);
        assertEquals("\t\t167", frame.totalToString());

        scoreboardBuilder = new ScoreboardBuilder("John");

        // 1st frame
        scoreboardBuilder.acceptScore(3);
        scoreboardBuilder.acceptScore(7);

        // 2nd frame
        scoreboardBuilder.acceptScore(6);
        scoreboardBuilder.acceptScore(3);

        // 3rd frame
        scoreboardBuilder.acceptScore(10);

        // 4th frame
        scoreboardBuilder.acceptScore(8);
        scoreboardBuilder.acceptScore(1);

        // 5th frame
        scoreboardBuilder.acceptScore(10);

        // 6th frame
        scoreboardBuilder.acceptScore(10);

        // 7th frame
        scoreboardBuilder.acceptScore(9);
        scoreboardBuilder.acceptScore(0);

        // 8th frame
        scoreboardBuilder.acceptScore(7);
        scoreboardBuilder.acceptScore(3);

        // 9th frame
        scoreboardBuilder.acceptScore(4);
        scoreboardBuilder.acceptScore(4);

        // 10th frame
        scoreboardBuilder.acceptScore(10);
        scoreboardBuilder.acceptScore(9);
        scoreboardBuilder.acceptScore(0);

        scoreboard = scoreboardBuilder.build();
        scoreboard.calculateScores();
        frames = scoreboard.getFrames();
        frame = frames.get(0);
        assertEquals("\t\t16", frame.totalToString());
        frame = frames.get(1);
        assertEquals("\t\t25", frame.totalToString());
        frame = frames.get(2);
        assertEquals("\t\t44", frame.totalToString());
        frame = frames.get(3);
        assertEquals("\t\t53", frame.totalToString());
        frame = frames.get(4);
        assertEquals("\t\t82", frame.totalToString());
        frame = frames.get(5);
        assertEquals("\t\t101", frame.totalToString());
        frame = frames.get(6);
        assertEquals("\t\t110", frame.totalToString());
        frame = frames.get(7);
        assertEquals("\t\t124", frame.totalToString());
        frame = frames.get(8);
        assertEquals("\t\t132", frame.totalToString());
        frame = frames.get(9);
        assertEquals("\t\t151", frame.totalToString());
    }

    @Test
    void edgeCaseFoulAsSecondScoreInTenthFrame() {
        // 1st frame
        scoreboardBuilder.acceptScore(10);

        // 2nd frame
        scoreboardBuilder.acceptScore(10);

        // 3rd frame
        scoreboardBuilder.acceptScore(10);

        // 4th frame
        scoreboardBuilder.acceptScore(10);

        // 5th frame
        scoreboardBuilder.acceptScore(10);

        // 6th frame
        scoreboardBuilder.acceptScore(10);

        // 7th frame
        scoreboardBuilder.acceptScore(10);

        // 8th frame
        scoreboardBuilder.acceptScore(10);

        // 9th frame
        scoreboardBuilder.acceptScore(10);

        // 10th frame
        scoreboardBuilder.acceptScore(10);
        scoreboardBuilder.acceptFoul();
        scoreboardBuilder.acceptScore(10);

        List<Frame> frames = scoreboardBuilder.build().getFrames();
        Frame frame = frames.get(0);
        assertEquals("\t\tX", frame.pinfallsToString());
        frame = frames.get(1);
        assertEquals("\t\tX", frame.pinfallsToString());
        frame = frames.get(2);
        assertEquals("\t\tX", frame.pinfallsToString());
        frame = frames.get(3);
        assertEquals("\t\tX", frame.pinfallsToString());
        frame = frames.get(4);
        assertEquals("\t\tX", frame.pinfallsToString());
        frame = frames.get(5);
        assertEquals("\t\tX", frame.pinfallsToString());
        frame = frames.get(6);
        assertEquals("\t\tX", frame.pinfallsToString());
        frame = frames.get(7);
        assertEquals("\t\tX", frame.pinfallsToString());
        frame = frames.get(8);
        assertEquals("\t\tX", frame.pinfallsToString());
        frame = frames.get(9);
        assertEquals("\tX\tF\t/", frame.pinfallsToString());
    }

    @Test
    void edgeCaseFoulAsFirstScoreInTenthFrameThenConvertToSpareThenZero() {
        // 1st frame
        scoreboardBuilder.acceptScore(10);

        // 2nd frame
        scoreboardBuilder.acceptScore(10);

        // 3rd frame
        scoreboardBuilder.acceptScore(10);

        // 4th frame
        scoreboardBuilder.acceptScore(10);

        // 5th frame
        scoreboardBuilder.acceptScore(10);

        // 6th frame
        scoreboardBuilder.acceptScore(10);

        // 7th frame
        scoreboardBuilder.acceptScore(10);

        // 8th frame
        scoreboardBuilder.acceptScore(10);

        // 9th frame
        scoreboardBuilder.acceptScore(10);

        // 10th frame
        scoreboardBuilder.acceptFoul();
        scoreboardBuilder.acceptScore(10);
        scoreboardBuilder.acceptScore(0);

        List<Frame> frames = scoreboardBuilder.build().getFrames();
        Frame frame = frames.get(0);
        assertEquals("\t\tX", frame.pinfallsToString());
        frame = frames.get(1);
        assertEquals("\t\tX", frame.pinfallsToString());
        frame = frames.get(2);
        assertEquals("\t\tX", frame.pinfallsToString());
        frame = frames.get(3);
        assertEquals("\t\tX", frame.pinfallsToString());
        frame = frames.get(4);
        assertEquals("\t\tX", frame.pinfallsToString());
        frame = frames.get(5);
        assertEquals("\t\tX", frame.pinfallsToString());
        frame = frames.get(6);
        assertEquals("\t\tX", frame.pinfallsToString());
        frame = frames.get(7);
        assertEquals("\t\tX", frame.pinfallsToString());
        frame = frames.get(8);
        assertEquals("\t\tX", frame.pinfallsToString());
        frame = frames.get(9);
        assertEquals("\tF\t/\t0", frame.pinfallsToString());
    }
}
