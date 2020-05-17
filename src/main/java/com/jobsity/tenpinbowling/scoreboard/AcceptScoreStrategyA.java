package com.jobsity.tenpinbowling.scoreboard;

import com.jobsity.tenpinbowling.frame.Frame;
import com.jobsity.tenpinbowling.frame.FrameStrike;

import java.util.List;

/**
 * This strategy is used to take a frame's first score (from frame 1 to 9),
 * then it decides whether to move to the next frame or take a second score in this same frame.
 */
public class AcceptScoreStrategyA extends AcceptScoreStrategy {

    public AcceptScoreStrategyA(int frameAcceptingScoresIndex) {
        super(frameAcceptingScoresIndex);
    }

    @Override
    protected AcceptScoreStrategy acceptSanitisedScoreAndChangeStrategy(Integer score, List<Frame> frames) {
        if (isStrike(score)) {
            Frame frame = new FrameStrike(frameAcceptingScoresIndex, frames);
            frames.add(frame);
            if (frameAcceptingScoresIndex == 8) {
                return new AcceptScoreStrategyTenA();
            }
            return new AcceptScoreStrategyA(++frameAcceptingScoresIndex);
        }
        return new AcceptScoreStrategyB(frameAcceptingScoresIndex, score, false);
    }

    @Override
    public AcceptScoreStrategy acceptFoulAndChangeStrategy(List<Frame> frames) {
        return new AcceptScoreStrategyB(frameAcceptingScoresIndex, 0, true);
    }
}
