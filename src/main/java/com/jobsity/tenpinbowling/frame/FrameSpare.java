package com.jobsity.tenpinbowling.frame;

import java.util.List;

public class FrameSpare extends FrameRegular {

    public FrameSpare(int scoreA, boolean isFoul, int myIndex, List<Frame> frames) {
        super(scoreA, 10 - scoreA, isFoul, myIndex, frames);
    }

    @Override
    public void calculateTotal() {
        int nextScore = frames.get(myIndex + 1).scoreA;
        if (myIndex == 0) {
            total = 10 + nextScore;
            return;
        }
        total = frames.get(myIndex - 1).getTotal() + 10 + nextScore;
    }

    @Override
    public String pinfallsToString() {
        return String.format("\t%s\t/", isFoul ? "F" : scoreA);
    }
}
