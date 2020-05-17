package com.jobsity.tenpinbowling.frame;

import java.util.List;

public abstract class FrameTen extends Frame {

    protected FrameTen(int scoreA, int myIndex, List<Frame> frames) {
        super(scoreA, myIndex, frames);
    }

    protected String scoreToString(int score) {
        return isStrike(score) ? "X" : String.valueOf(score);
    }

    private boolean isStrike(int score) {
        return score == 10;
    }
}
