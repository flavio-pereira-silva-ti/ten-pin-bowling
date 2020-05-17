package com.jobsity.tenpinbowling.scoring;

import com.jobsity.tenpinbowling.frame.Frame;

import java.util.List;

/**
 * Strategy used after getting a strike as the first score in the tenth frame.
 */
public class AcceptScoreStrategyTenB2 extends AcceptScoreStrategy {

    public AcceptScoreStrategyTenB2() {
        super(9);
    }

    @Override
    protected AcceptScoreStrategy acceptSanitisedScoreAndChangeStrategy(Integer score, List<Frame> frames) {
        return new AcceptScoreStrategyTenC(10, score, false, false);
    }

    @Override
    public AcceptScoreStrategy acceptFoulAndChangeStrategy(List<Frame> frames) {
        return new AcceptScoreStrategyTenC(10, 0, false, true);
    }
}
