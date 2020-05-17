package com.jobsity.tenpinbowling.frame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FrameStrike extends Frame {

    public FrameStrike(int myIndex, List<Frame> frames) {
        super(10, myIndex, frames);
    }

    @Override
    public List<Integer> getScores() {
        return Collections.singletonList(10);
    }

    @Override
    public void calculateTotal() {
        List<Integer> nextTwoScores = new ArrayList<>();
        int nextFrameIndex = myIndex + 1;
        while (nextTwoScores.size() < 2) {
            nextTwoScores.addAll(frames.get(nextFrameIndex++).getScores());
        }
        int nextTwoScoresSum = nextTwoScores.get(0) + nextTwoScores.get(1);
        if (myIndex == 0) {
            total += 10 + nextTwoScoresSum;
            return;
        }
        total += frames.get(myIndex - 1).getTotal() + 10 + nextTwoScoresSum;
    }

    @Override
    public String pinfallsToString() {
        return "\t\tX";
    }
}
