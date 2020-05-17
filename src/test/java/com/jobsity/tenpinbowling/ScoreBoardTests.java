package com.jobsity.tenpinbowling;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
public class ScoreBoardTests {

    private ScoreBoard scoreBoard;

    @BeforeEach
    void beforeEach() {
        scoreBoard = new ScoreBoard("Steve");
    }

    @Test
    void scoreBoardShouldHaveName() {
        assertNotNull(scoreBoard.getName());
        assertEquals("Steve", scoreBoard.getName());
    }

    @Test
    void scoreBoardShouldAcceptUpTo21Results() {
        // 1st frame
        scoreBoard.acceptScore(1);
        scoreBoard.acceptScore(9);

        // 2nd frame
        scoreBoard.acceptScore(0);
        scoreBoard.acceptScore(1);

        // 3rd frame
        scoreBoard.acceptScore(1);
        scoreBoard.acceptScore(1);

        // 4th frame
        scoreBoard.acceptScore(1);
        scoreBoard.acceptScore(1);

        // 5th frame
        scoreBoard.acceptScore(5);
        scoreBoard.acceptScore(5);

        // 6th frame
        scoreBoard.acceptScore(1);
        scoreBoard.acceptScore(1);

        // 7th frame
        scoreBoard.acceptScore(1);
        scoreBoard.acceptScore(1);

        // 8th frame
        scoreBoard.acceptScore(1);
        scoreBoard.acceptScore(1);

        // 9th frame
        scoreBoard.acceptScore(1);
        scoreBoard.acceptScore(1);

        // 10th frame
        scoreBoard.acceptScore(1);
        scoreBoard.acceptScore(9);
        scoreBoard.acceptScore(1);

        Map<FramePos, Frame> frames = scoreBoard.getFrames();
        Frame frame = frames.get(FramePos.FRAME_1);
        assertEquals("\t1\t/", frame.pinfallsToString());
        frame = frames.get(FramePos.FRAME_2);
        assertEquals("\t0\t1", frame.pinfallsToString());
        frame = frames.get(FramePos.FRAME_3);
        assertEquals("\t1\t1", frame.pinfallsToString());
        frame = frames.get(FramePos.FRAME_4);
        assertEquals("\t1\t1", frame.pinfallsToString());
        frame = frames.get(FramePos.FRAME_5);
        assertEquals("\t5\t/", frame.pinfallsToString());
        frame = frames.get(FramePos.FRAME_6);
        assertEquals("\t1\t1", frame.pinfallsToString());
        frame = frames.get(FramePos.FRAME_7);
        assertEquals("\t1\t1", frame.pinfallsToString());
        frame = frames.get(FramePos.FRAME_8);
        assertEquals("\t1\t1", frame.pinfallsToString());
        frame = frames.get(FramePos.FRAME_9);
        assertEquals("\t1\t1", frame.pinfallsToString());
        frame = frames.get(FramePos.FRAME_10);
        assertEquals("\t1\t/\t1", frame.pinfallsToString());
    }

    @Test
    void scoreBoardShouldAcceptUpTo12Strikes() {
        // 1st frame
        scoreBoard.acceptScore(10);

        // 2nd frame
        scoreBoard.acceptScore(10);

        // 3rd frame
        scoreBoard.acceptScore(10);

        // 4th frame
        scoreBoard.acceptScore(10);

        // 5th frame
        scoreBoard.acceptScore(10);

        // 6th frame
        scoreBoard.acceptScore(10);

        // 7th frame
        scoreBoard.acceptScore(10);

        // 8th frame
        scoreBoard.acceptScore(10);

        // 9th frame
        scoreBoard.acceptScore(10);

        // 10th frame
        scoreBoard.acceptScore(10);
        scoreBoard.acceptScore(10);
        scoreBoard.acceptScore(10);

        Map<FramePos, Frame> frames = scoreBoard.getFrames();
        Frame frame = frames.get(FramePos.FRAME_1);
        assertEquals("\t\tX", frame.pinfallsToString());
        frame = frames.get(FramePos.FRAME_2);
        assertEquals("\t\tX", frame.pinfallsToString());
        frame = frames.get(FramePos.FRAME_3);
        assertEquals("\t\tX", frame.pinfallsToString());
        frame = frames.get(FramePos.FRAME_4);
        assertEquals("\t\tX", frame.pinfallsToString());
        frame = frames.get(FramePos.FRAME_5);
        assertEquals("\t\tX", frame.pinfallsToString());
        frame = frames.get(FramePos.FRAME_6);
        assertEquals("\t\tX", frame.pinfallsToString());
        frame = frames.get(FramePos.FRAME_7);
        assertEquals("\t\tX", frame.pinfallsToString());
        frame = frames.get(FramePos.FRAME_8);
        assertEquals("\t\tX", frame.pinfallsToString());
        frame = frames.get(FramePos.FRAME_9);
        assertEquals("\t\tX", frame.pinfallsToString());
        frame = frames.get(FramePos.FRAME_10);
        assertEquals("\tX\tX\tX", frame.pinfallsToString());
    }

    @Test
    void scoreBoardShouldNotAcceptScoreHigherThan10OrLowerThan0() {
        assertThrows(IllegalArgumentException.class, () -> scoreBoard.acceptScore(11));
        assertThrows(IllegalArgumentException.class, () -> scoreBoard.acceptScore(-1));
    }
}
