package com.jobsity.tenpinbowling.scoreboard;

import com.jobsity.tenpinbowling.frame.Frame;
import com.jobsity.tenpinbowling.frame.FrameRegular;
import com.jobsity.tenpinbowling.frame.FrameSpare;

import java.util.List;

/**
 * This strategy is used to take a frame's second score (from frame 1 to 9),
 * then it resets to strategy A or strategy A for frame ten.
 */
public class AcceptScoreStrategyB extends AcceptScoreStrategy {

    private final int scoreA;

    private final boolean isFoul;

    public AcceptScoreStrategyB(int frameAcceptingScoresIndex, int scoreA, boolean isFoul) {
        super(frameAcceptingScoresIndex);
        this.scoreA = scoreA;
        this.isFoul = isFoul;
    }

    @Override
    protected AcceptScoreStrategy acceptSanitisedScoreAndChangeStrategy(Integer score, List<Frame> frames) {
        if (scoreA + score > 10) {
            throw new IllegalArgumentException("Frame second score adds more than 10. Frame#" + frameAcceptingScoresIndex + " " + scoreA + "+" + score);
        }
        Frame frame;
        if (scoreA + score == 10) {
            frame = new FrameSpare(scoreA, isFoul, frameAcceptingScoresIndex, frames);
        } else {
            frame = new FrameRegular(scoreA, score, isFoul, frameAcceptingScoresIndex, frames);
        }
        frames.add(frame);
        if (frameAcceptingScoresIndex == 8) {
            return new AcceptScoreStrategyTenA();
        }
        return new AcceptScoreStrategyA(++frameAcceptingScoresIndex);
    }

    @Override
    public AcceptScoreStrategy acceptFoulAndChangeStrategy(List<Frame> frames) {
        throw new UnsupportedOperationException("Fouls are not accepted as frame's second score. Frame#" + frameAcceptingScoresIndex);
    }
}
