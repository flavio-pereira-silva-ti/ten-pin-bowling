package com.jobsity.tenpinbowling.frame;

import java.util.Arrays;
import java.util.List;

public class FrameTenStrike extends FrameTen {

    private final int scoreB;

    private final int scoreC;

    private final boolean isScoreBFoul;

    public FrameTenStrike(int scoreB, int scoreC, boolean isScoreBFoul, List<Frame> frames) {
        super(10, 9, frames);
        this.scoreB = scoreB;
        this.scoreC = scoreC;
        this.isScoreBFoul = isScoreBFoul;
    }

    @Override
    public List<Integer> getScores() {
        return Arrays.asList(10, scoreB, scoreC);
    }

    @Override
    public void calculateTotal() {
        total = frames.get(8).total + 10 + scoreB + scoreC;
    }

    @Override
    public String pinfallsToString() {
        return String.format("\tX\t%s\t%s", scoreBToString(), scoreCToString());
    }

    private String scoreBToString() {
        if (isScoreBFoul) {
            return "F";
        }
        return scoreToString(scoreB);
    }

    private String scoreCToString() {
        if (scoreB + scoreC == 10) {
            return "/";
        }
        return scoreToString(scoreC);
    }
}
