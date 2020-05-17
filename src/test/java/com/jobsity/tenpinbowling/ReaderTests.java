package com.jobsity.tenpinbowling;

import com.jobsity.tenpinbowling.frame.Frame;
import com.jobsity.tenpinbowling.scoring.Scoreboard;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ReaderTests {

    @Autowired
    private Reader reader;

    @Test
    void givenValidInputFileConversionShouldNotFail() {
        Path pathToInputFile = Paths.get("src", "test", "resources", "sample-1.txt");
        Set<Scoreboard> scoreboards = reader.createScoreBoardsFromFile(pathToInputFile);
        assertNotNull(scoreboards);
        assertFalse(scoreboards.isEmpty());
        assertEquals(2, scoreboards.size());
        scoreboards.stream().findFirst().ifPresent(scoreboard -> {
            assertEquals("Jeff", scoreboard.getName());
            List<Frame> frames = scoreboard.getFrames();
            Frame frame = frames.get(0);
            assertEquals("\t\tX", frame.pinfallsToString());
            frame = frames.get(1);
            assertEquals("\t7\t/", frame.pinfallsToString());
            frame = frames.get(2);
            assertEquals("\t9\t0", frame.pinfallsToString());
            frame = frames.get(3);
            assertEquals("\t\tX", frame.pinfallsToString());
            frame = frames.get(4);
            assertEquals("\t0\t8", frame.pinfallsToString());
            frame = frames.get(5);
            assertEquals("\t8\t/", frame.pinfallsToString());
            frame = frames.get(6);
            assertEquals("\tF\t6", frame.pinfallsToString());
            frame = frames.get(7);
            assertEquals("\t\tX", frame.pinfallsToString());
            frame = frames.get(8);
            assertEquals("\t\tX", frame.pinfallsToString());
            frame = frames.get(9);
            assertEquals("\tX\t8\t1", frame.pinfallsToString());
        });
        scoreboards.stream().skip(1).forEach(scoreboard -> {
            assertEquals("John", scoreboard.getName());
            List<Frame> frames = scoreboard.getFrames();
            Frame frame = frames.get(0);
            assertEquals("\t3\t/", frame.pinfallsToString());
            frame = frames.get(1);
            assertEquals("\t6\t3", frame.pinfallsToString());
            frame = frames.get(2);
            assertEquals("\t\tX", frame.pinfallsToString());
            frame = frames.get(3);
            assertEquals("\t8\t1", frame.pinfallsToString());
            frame = frames.get(4);
            assertEquals("\t\tX", frame.pinfallsToString());
            frame = frames.get(5);
            assertEquals("\t\tX", frame.pinfallsToString());
            frame = frames.get(6);
            assertEquals("\t9\t0", frame.pinfallsToString());
            frame = frames.get(7);
            assertEquals("\t7\t/", frame.pinfallsToString());
            frame = frames.get(8);
            assertEquals("\t4\t4", frame.pinfallsToString());
            frame = frames.get(9);
            assertEquals("\tX\t9\t0", frame.pinfallsToString());
        });
    }
}
