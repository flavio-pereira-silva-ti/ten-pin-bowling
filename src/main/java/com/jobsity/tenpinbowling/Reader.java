package com.jobsity.tenpinbowling;

import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.util.Collections;
import java.util.Set;

@Component
public class Reader {

    public Set<ScoreBoard> createScoreBoardsFromFile(Path pathToInputFile) {
        return Collections.EMPTY_SET;
    }
}
