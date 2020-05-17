package com.jobsity.tenpinbowling.frame;

import java.util.Arrays;
import java.util.List;

public class FrameTenSpare extends FrameTen {

    private final int scoreB;

    private final int scoreC;

    private final boolean isScoreAFoul;

    public FrameTenSpare(int scoreA, int scoreC, boolean isScoreAFoul, List<Frame> frames) {
        super(scoreA, 9, frames);
        this.scoreB = 10 - scoreA;
        this.scoreC = scoreC;
        this.isScoreAFoul = isScoreAFoul;
    }

    @Override
    public List<Integer> getScores() {
        return Arrays.asList(scoreA, scoreB, scoreC);
    }

    @Override
    public void calculateTotal() {
        total = frames.get(8).total + 10 + scoreC;
    }

    @Override
    public String pinfallsToString() {
        return String.format("\t%s\t/\t%s", isScoreAFoul ? "F" : scoreA, scoreToString(scoreC));
    }
}
