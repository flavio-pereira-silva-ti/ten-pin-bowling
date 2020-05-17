package com.jobsity.tenpinbowling.io;

import com.jobsity.tenpinbowling.frame.Frame;
import com.jobsity.tenpinbowling.scoreboard.Scoreboard;
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
    void integrationTestTwoPlayers() {
        Path pathToInputFile = Paths.get("src", "test", "resources", "sample-1.txt");
        jeffAndJohnAssertions(pathToInputFile);
    }

    @Test
    void emptyLInesShouldBeIgnored() {
        Path pathToInputFile = Paths.get("src", "test", "resources", "bad-sample-1.txt");
        jeffAndJohnAssertions(pathToInputFile);
    }

    @Test
    void integrationTestPerfectAndZeroScores() {
        Path pathToInputFile = Paths.get("src", "test", "resources", "sample-2.txt");
        Set<Scoreboard> scoreboards = reader.createScoreBoardsFromFile(pathToInputFile);
        assertNotNull(scoreboards);
        assertFalse(scoreboards.isEmpty());
        assertEquals(2, scoreboards.size());
        scoreboards.forEach(Scoreboard::calculateScores);
        scoreboards.stream().findFirst().ifPresent(scoreboard -> {
            assertEquals("Flavio", scoreboard.getName());
            List<Frame> frames = scoreboard.getFrames();
            Frame frame = frames.get(0);
            assertEquals("\t0\t0", frame.pinfallsToString());
            assertEquals("\t\t0", frame.totalToString());
            frame = frames.get(1);
            assertEquals("\t0\t0", frame.pinfallsToString());
            assertEquals("\t\t0", frame.totalToString());
            frame = frames.get(2);
            assertEquals("\t0\t0", frame.pinfallsToString());
            assertEquals("\t\t0", frame.totalToString());
            frame = frames.get(3);
            assertEquals("\t0\t0", frame.pinfallsToString());
            assertEquals("\t\t0", frame.totalToString());
            frame = frames.get(4);
            assertEquals("\t0\t0", frame.pinfallsToString());
            assertEquals("\t\t0", frame.totalToString());
            frame = frames.get(5);
            assertEquals("\t0\t0", frame.pinfallsToString());
            assertEquals("\t\t0", frame.totalToString());
            frame = frames.get(6);
            assertEquals("\t0\t0", frame.pinfallsToString());
            assertEquals("\t\t0", frame.totalToString());
            frame = frames.get(7);
            assertEquals("\t0\t0", frame.pinfallsToString());
            assertEquals("\t\t0", frame.totalToString());
            frame = frames.get(8);
            assertEquals("\t0\t0", frame.pinfallsToString());
            assertEquals("\t\t0", frame.totalToString());
            frame = frames.get(9);
            assertEquals("\t0\t0", frame.pinfallsToString());
            assertEquals("\t\t0", frame.totalToString());
        });
        scoreboards.stream().skip(1).forEach(scoreboard -> {
            assertEquals("Gabriel", scoreboard.getName());
            List<Frame> frames = scoreboard.getFrames();
            Frame frame = frames.get(0);
            assertEquals("\t\tX", frame.pinfallsToString());
            assertEquals("\t\t30", frame.totalToString());
            frame = frames.get(1);
            assertEquals("\t\tX", frame.pinfallsToString());
            assertEquals("\t\t60", frame.totalToString());
            frame = frames.get(2);
            assertEquals("\t\tX", frame.pinfallsToString());
            assertEquals("\t\t90", frame.totalToString());
            frame = frames.get(3);
            assertEquals("\t\tX", frame.pinfallsToString());
            assertEquals("\t\t120", frame.totalToString());
            frame = frames.get(4);
            assertEquals("\t\tX", frame.pinfallsToString());
            assertEquals("\t\t150", frame.totalToString());
            frame = frames.get(5);
            assertEquals("\t\tX", frame.pinfallsToString());
            assertEquals("\t\t180", frame.totalToString());
            frame = frames.get(6);
            assertEquals("\t\tX", frame.pinfallsToString());
            assertEquals("\t\t210", frame.totalToString());
            frame = frames.get(7);
            assertEquals("\t\tX", frame.pinfallsToString());
            assertEquals("\t\t240", frame.totalToString());
            frame = frames.get(8);
            assertEquals("\t\tX", frame.pinfallsToString());
            assertEquals("\t\t270", frame.totalToString());
            frame = frames.get(9);
            assertEquals("\tX\tX\tX", frame.pinfallsToString());
            assertEquals("\t\t300", frame.totalToString());
        });
    }

    private void jeffAndJohnAssertions(Path pathToInputFile) {
        Set<Scoreboard> scoreboards = reader.createScoreBoardsFromFile(pathToInputFile);
        assertNotNull(scoreboards);
        assertFalse(scoreboards.isEmpty());
        assertEquals(2, scoreboards.size());
        scoreboards.forEach(Scoreboard::calculateScores);
        scoreboards.stream().findFirst().ifPresent(scoreboard -> {
            assertEquals("Jeff", scoreboard.getName());
            List<Frame> frames = scoreboard.getFrames();
            Frame frame = frames.get(0);
            assertEquals("\t\tX", frame.pinfallsToString());
            assertEquals("\t\t20", frame.totalToString());
            frame = frames.get(1);
            assertEquals("\t7\t/", frame.pinfallsToString());
            assertEquals("\t\t39", frame.totalToString());
            frame = frames.get(2);
            assertEquals("\t9\t0", frame.pinfallsToString());
            assertEquals("\t\t48", frame.totalToString());
            frame = frames.get(3);
            assertEquals("\t\tX", frame.pinfallsToString());
            assertEquals("\t\t66", frame.totalToString());
            frame = frames.get(4);
            assertEquals("\t0\t8", frame.pinfallsToString());
            assertEquals("\t\t74", frame.totalToString());
            frame = frames.get(5);
            assertEquals("\t8\t/", frame.pinfallsToString());
            assertEquals("\t\t84", frame.totalToString());
            frame = frames.get(6);
            assertEquals("\tF\t6", frame.pinfallsToString());
            assertEquals("\t\t90", frame.totalToString());
            frame = frames.get(7);
            assertEquals("\t\tX", frame.pinfallsToString());
            assertEquals("\t\t120", frame.totalToString());
            frame = frames.get(8);
            assertEquals("\t\tX", frame.pinfallsToString());
            assertEquals("\t\t148", frame.totalToString());
            frame = frames.get(9);
            assertEquals("\tX\t8\t1", frame.pinfallsToString());
            assertEquals("\t\t167", frame.totalToString());
        });
        scoreboards.stream().skip(1).forEach(scoreboard -> {
            assertEquals("John", scoreboard.getName());
            List<Frame> frames = scoreboard.getFrames();
            Frame frame = frames.get(0);
            assertEquals("\t3\t/", frame.pinfallsToString());
            assertEquals("\t\t16", frame.totalToString());
            frame = frames.get(1);
            assertEquals("\t6\t3", frame.pinfallsToString());
            assertEquals("\t\t25", frame.totalToString());
            frame = frames.get(2);
            assertEquals("\t\tX", frame.pinfallsToString());
            assertEquals("\t\t44", frame.totalToString());
            frame = frames.get(3);
            assertEquals("\t8\t1", frame.pinfallsToString());
            assertEquals("\t\t53", frame.totalToString());
            frame = frames.get(4);
            assertEquals("\t\tX", frame.pinfallsToString());
            assertEquals("\t\t82", frame.totalToString());
            frame = frames.get(5);
            assertEquals("\t\tX", frame.pinfallsToString());
            assertEquals("\t\t101", frame.totalToString());
            frame = frames.get(6);
            assertEquals("\t9\t0", frame.pinfallsToString());
            assertEquals("\t\t110", frame.totalToString());
            frame = frames.get(7);
            assertEquals("\t7\t/", frame.pinfallsToString());
            assertEquals("\t\t124", frame.totalToString());
            frame = frames.get(8);
            assertEquals("\t4\t4", frame.pinfallsToString());
            assertEquals("\t\t132", frame.totalToString());
            frame = frames.get(9);
            assertEquals("\tX\t9\t0", frame.pinfallsToString());
            assertEquals("\t\t151", frame.totalToString());
        });
    }
}
