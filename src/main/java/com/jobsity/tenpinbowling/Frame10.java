package com.jobsity.tenpinbowling;

import static com.jobsity.tenpinbowling.FrameState.*;

public class Frame10 extends Frame {

    private Integer score3;

    @Override
    protected void acceptFirstScore(Integer score) {
        score1 = score;
        state = ACCEPTING_SECOND_SCORE;
    }

    @Override
    public void acceptSecondScore(Integer score) {
        super.acceptSecondScore(score);
        if (score2 == 10 || score1 + score2 == 10) {
            state = ACCEPTING_THIRD_SCORE;
        }
    }

    @Override
    protected void acceptThirdScore(Integer score) {
        score3 = score;
        state = CLOSED;
    }

    @Override
    public String pinfallsToString() {
        return String.format("\t%s\t%s\t%s", scoreToString(score1), scoreToString(score2), scoreToString(score3));
    }

    private String scoreToString(Integer score) {
        return isStrike(score) ? "X" : String.valueOf(score);
    }
}
