package com.jobsity.tenpinbowling.scoring;

import com.jobsity.tenpinbowling.frame.Frame;
import com.jobsity.tenpinbowling.frame.FrameStrike;

import java.util.List;

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
