package com.jobsity.tenpinbowling;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;

import static com.jobsity.tenpinbowling.FramePos.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ReaderTests {

    @Autowired
    private Reader reader;

    @Test
    void givenValidInputFileConversionShouldNotFail() {
        Path pathToInputFile = Paths.get("src", "test", "resources", "sample-1.txt");
        Set<ScoreBoard> scoreBoards = reader.createScoreBoardsFromFile(pathToInputFile);
        assertNotNull(scoreBoards);
        assertFalse(scoreBoards.isEmpty());
        assertEquals(2, scoreBoards.size());
        scoreBoards.stream().findFirst().ifPresent(scoreBoard -> {
            assertEquals("Jeff", scoreBoard.getName());
            Map<FramePos, Frame> frames = scoreBoard.getFrames();
            Frame frame = frames.get(FRAME_1);
            assertEquals("\t\tX", frame.pinfallsToString());
            frame = frames.get(FRAME_2);
            assertEquals("\t7\t/", frame.pinfallsToString());
            frame = frames.get(FRAME_3);
            assertEquals("\t9\t0", frame.pinfallsToString());
            frame = frames.get(FRAME_4);
            assertEquals("\t\tX", frame.pinfallsToString());
            frame = frames.get(FRAME_5);
            assertEquals("\t0\t8", frame.pinfallsToString());
            frame = frames.get(FRAME_6);
            assertEquals("\t8\t/", frame.pinfallsToString());
            frame = frames.get(FRAME_7);
            assertEquals("\tF\t6", frame.pinfallsToString());
            frame = frames.get(FRAME_8);
            assertEquals("\t\tX", frame.pinfallsToString());
            frame = frames.get(FRAME_9);
            assertEquals("\t\tX", frame.pinfallsToString());
            frame = frames.get(FRAME_10);
            assertEquals("\tX\t8\t1", frame.pinfallsToString());
        });
        scoreBoards.stream().skip(1).forEach(scoreBoard -> {
            assertEquals("John", scoreBoard.getName());
            Map<FramePos, Frame> frames = scoreBoard.getFrames();
            Frame frame = frames.get(FRAME_1);
            assertEquals("\t3\t/", frame.pinfallsToString());
            frame = frames.get(FRAME_2);
            assertEquals("\t6\t3", frame.pinfallsToString());
            frame = frames.get(FRAME_3);
            assertEquals("\t\tX", frame.pinfallsToString());
            frame = frames.get(FRAME_4);
            assertEquals("\t8\t1", frame.pinfallsToString());
            frame = frames.get(FRAME_5);
            assertEquals("\t\tX", frame.pinfallsToString());
            frame = frames.get(FRAME_6);
            assertEquals("\t\tX", frame.pinfallsToString());
            frame = frames.get(FRAME_7);
            assertEquals("\t9\t0", frame.pinfallsToString());
            frame = frames.get(FRAME_8);
            assertEquals("\t7\t/", frame.pinfallsToString());
            frame = frames.get(FRAME_9);
            assertEquals("\t4\t4", frame.pinfallsToString());
            frame = frames.get(FRAME_10);
            assertEquals("\tX\t9\t0", frame.pinfallsToString());
        });
    }
}
