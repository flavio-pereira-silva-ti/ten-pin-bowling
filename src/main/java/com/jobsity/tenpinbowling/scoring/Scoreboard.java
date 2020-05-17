package com.jobsity.tenpinbowling.scoring;

import com.jobsity.tenpinbowling.frame.Frame;

import java.util.List;

public class Scoreboard {

    private String name;

    private List<Frame> frames;

    public Scoreboard(String name, List<Frame> frames) {
        this.name = name;
        this.frames = frames;
    }

    public String getName() {
        return name;
    }

    public List<Frame> getFrames() {
        return frames;
    }

    public void calculateScores() {
        frames.forEach(Frame::calculateTotal);
    }
}
