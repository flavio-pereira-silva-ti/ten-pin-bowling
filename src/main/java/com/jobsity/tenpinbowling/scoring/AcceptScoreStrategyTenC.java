package com.jobsity.tenpinbowling.scoring;

import com.jobsity.tenpinbowling.frame.Frame;
import com.jobsity.tenpinbowling.frame.FrameRegular;
import com.jobsity.tenpinbowling.frame.FrameTenSpare;
import com.jobsity.tenpinbowling.frame.FrameTenStrike;

import java.util.List;

public class AcceptScoreStrategyTenC extends AcceptScoreStrategy {

    private final int scoreA;

    private final int scoreB;

    private final boolean isScoreAFoul;

    private final boolean isScoreBFoul;

    public AcceptScoreStrategyTenC(int scoreA, int scoreB, boolean isScoreAFoul, boolean isScoreBFoul) {
        super(9);
        this.scoreA = scoreA;
        this.scoreB = scoreB;
        this.isScoreAFoul = isScoreAFoul;
        this.isScoreBFoul = isScoreBFoul;
    }

    @Override
    protected AcceptScoreStrategy acceptSanitisedScoreAndChangeStrategy(Integer score, List<Frame> frames) {
        Frame frame;
        if (isStrike(scoreA)) {
            frame = new FrameTenStrike(scoreB, score, isScoreBFoul, frames);
        } else if (scoreA + scoreB == 10) {
            frame = new FrameTenSpare(scoreA, score, isScoreAFoul, frames);
        } else {
            frame = new FrameRegular(scoreA, scoreB, isScoreAFoul, 9, frames);
        }
        frames.add(frame);
        return null;
    }

    @Override
    public AcceptScoreStrategy acceptFoulAndChangeStrategy(List<Frame> frames) {
        throw new UnsupportedOperationException("Fouls are not accepted in this frame's third score.");
    }
}
