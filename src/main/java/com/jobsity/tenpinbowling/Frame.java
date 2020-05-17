package com.jobsity.tenpinbowling;

import static com.jobsity.tenpinbowling.FrameState.*;

public class Frame {

    protected FrameState state = EMPTY;

    protected Integer score1;

    protected String score1Str;

    protected Integer score2;

    protected String score2Str;

    private Integer total;

    public void acceptScore(Integer score, boolean isFoul) {
        switch (state) {
            case EMPTY:
                acceptFirstScore(score, isFoul);
                break;
            case ACCEPTING_SECOND_SCORE:
                acceptSecondScore(score);
                break;
            case ACCEPTING_THIRD_SCORE:
                acceptThirdScore(score);
                break;
            default:
                throw new IllegalStateException("This frame cannot take anymore scores.");
        }
    }

    protected void acceptFirstScore(Integer score, boolean isFoul) {
        score1 = score;
        if (isStrike(score)) {
            score1Str = "";
            score2Str = "X";
            state = CLOSED;
        } else {
            score1Str = String.valueOf(score);
            state = ACCEPTING_SECOND_SCORE;
        }
        if (isFoul) {
            score1Str = "F";
        }
    }

    protected void acceptSecondScore(Integer score) {
        score2 = score;
        score2Str = didSpare() ? "/" : String.valueOf(score);
        state = CLOSED;
    }

    protected void acceptThirdScore(Integer score) {
        throw new UnsupportedOperationException("This frame does not take a third score.");
    }

    public boolean isClosed() {
        return CLOSED.equals(state);
    }

    public String pinfallsToString() {
        return "\t" + score1Str + "\t" + score2Str;
    }

    protected boolean isStrike(Integer score) {
        return Integer.valueOf(10).equals(score);
    }

    protected boolean didSpare() {
        return score1 + score2 == 10;
    }
}
