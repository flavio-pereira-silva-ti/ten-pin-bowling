package com.jobsity.tenpinbowling.scoreboard;

import com.jobsity.tenpinbowling.frame.Frame;

import java.util.List;

public abstract class AcceptScoreStrategy {

    protected int frameAcceptingScoresIndex;

    public AcceptScoreStrategy(int frameAcceptingScoresIndex) {
        this.frameAcceptingScoresIndex = frameAcceptingScoresIndex;
    }

    public AcceptScoreStrategy acceptAndChangeStrategy(Integer score, List<Frame> frames) {
        if (score < 0 || score > 10) {
            throw new IllegalArgumentException("Score must be between 0 and 10.");
        }
        return acceptSanitisedScoreAndChangeStrategy(score, frames);
    }

    protected abstract AcceptScoreStrategy acceptSanitisedScoreAndChangeStrategy(Integer score, List<Frame> frames);

    public abstract AcceptScoreStrategy acceptFoulAndChangeStrategy(List<Frame> frames);

    protected boolean isStrike(int score) {
        return score == 10;
    }
}
