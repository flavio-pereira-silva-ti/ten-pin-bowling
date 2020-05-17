package com.jobsity.tenpinbowling.frame;

import java.util.List;

public abstract class Frame {

    protected int scoreA;

    protected int total;

    protected int myIndex;

    protected List<Frame> frames;

    protected Frame(int scoreA, int myIndex, List<Frame> frames) {
        this.scoreA = scoreA;
        this.myIndex = myIndex;
        this.frames = frames;
    }

    public String totalToString() {
        return "\t\t" + total;
    }

    public int getTotal() {
        return total;
    }

    public abstract List<Integer> getScores();

    public abstract void calculateTotal();

    public abstract String pinfallsToString();
}
