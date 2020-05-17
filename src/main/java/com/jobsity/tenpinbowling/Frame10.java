package com.jobsity.tenpinbowling;

import static com.jobsity.tenpinbowling.FrameState.*;

public class Frame10 extends Frame {

    private Integer score3;

    private String score3Str;

    @Override
    protected void acceptFirstScore(Integer score, boolean isFoul) {
        score1 = score;
        score1Str = isStrike(score) ? "X" : String.valueOf(score);
        if (isFoul) {
            score1Str = "F";
        }
        state = ACCEPTING_SECOND_SCORE;
    }

    @Override
    public void acceptSecondScore(Integer score) {
        super.acceptSecondScore(score);
        if (isStrike(score)) {
            score2Str = "X";
        }
        if (isStrike(score) || didSpare() || score1.equals(10)) {
            state = ACCEPTING_THIRD_SCORE;
        }
    }

    @Override
    protected void acceptThirdScore(Integer score) {
        score3 = score;
        if (isStrike(score)) {
            score3Str = "X";
        } else {
            score3Str = String.valueOf(score);
        }
        state = CLOSED;
    }

    @Override
    public String pinfallsToString() {
        return "\t" + score1Str + "\t" + score2Str + "\t" + score3Str;
    }
}
