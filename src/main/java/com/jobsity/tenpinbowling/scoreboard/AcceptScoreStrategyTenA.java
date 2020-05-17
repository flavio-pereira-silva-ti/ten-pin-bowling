package com.jobsity.tenpinbowling.scoreboard;

import com.jobsity.tenpinbowling.frame.Frame;

import java.util.List;

/**
 * This strategy handles the first score for frame number 10 and, based on that, decides what's the next strategy.
 */
public class AcceptScoreStrategyTenA extends AcceptScoreStrategy {

    public AcceptScoreStrategyTenA() {
        super(9);
    }

    @Override
    protected AcceptScoreStrategy acceptSanitisedScoreAndChangeStrategy(Integer score, List<Frame> frames) {
        if (isStrike(score)) {
            return new AcceptScoreStrategyTenB2();
        }
        return new AcceptScoreStrategyTenB1(score, false);
    }

    @Override
    public AcceptScoreStrategy acceptFoulAndChangeStrategy(List<Frame> frames) {
        return new AcceptScoreStrategyTenB1(0, true);
    }
}
