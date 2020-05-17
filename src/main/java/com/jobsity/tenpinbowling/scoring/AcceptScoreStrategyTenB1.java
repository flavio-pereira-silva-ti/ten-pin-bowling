package com.jobsity.tenpinbowling.scoring;

import com.jobsity.tenpinbowling.frame.Frame;
import com.jobsity.tenpinbowling.frame.FrameRegular;

import java.util.List;

/**
 * Strategy used after getting a non-strike as the first score in the tenth frame.
 */
public class AcceptScoreStrategyTenB1 extends AcceptScoreStrategy {

    private final int scoreA;

    private final boolean isFoul;

    public AcceptScoreStrategyTenB1(int scoreA, boolean isFoul) {
        super(9);
        this.scoreA = scoreA;
        this.isFoul = isFoul;
    }

    @Override
    protected AcceptScoreStrategy acceptSanitisedScoreAndChangeStrategy(Integer score, List<Frame> frames) {
        if (scoreA + score > 10) {
            throw new IllegalArgumentException("Frame second score adds more than 10. Frame#" + frameAcceptingScoresIndex + " " + scoreA + "+" + score);
        }
        if (scoreA + score == 10) {
            return new AcceptScoreStrategyTenC(scoreA, score, isFoul, false);
        }
        Frame frame = new FrameRegular(scoreA, score, isFoul, 9, frames);
        frames.add(frame);
        return null;
    }

    @Override
    public AcceptScoreStrategy acceptFoulAndChangeStrategy(List<Frame> frames) {
        throw new UnsupportedOperationException("Fouls are not accepted in this frame's second score given that the first score was not a strike.");
    }
}
