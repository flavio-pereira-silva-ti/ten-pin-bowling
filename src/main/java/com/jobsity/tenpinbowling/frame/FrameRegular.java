package com.jobsity.tenpinbowling.frame;

import java.util.Arrays;
import java.util.List;

public class FrameRegular extends Frame {

    protected int scoreB;

    protected boolean isFoul;

    public FrameRegular(int scoreA, int scoreB, boolean isFoul, int myIndex, List<Frame> frames) {
        super(scoreA, myIndex, frames);
        this.scoreB = scoreB;
        this.isFoul = isFoul;
    }

    @Override
    public List<Integer> getScores() {
        return Arrays.asList(scoreA, scoreB);
    }

    @Override
    public void calculateTotal() {
        if (myIndex == 0) {
            total = scoreA + scoreB;
            return;
        }
        total = frames.get(myIndex - 1).total + scoreA + scoreB;
    }

    @Override
    public String pinfallsToString() {
        return String.format("\t%s\t%d", isFoul ? "F" : scoreA, scoreB);
    }
}
