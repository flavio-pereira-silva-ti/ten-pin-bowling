package com.jobsity.tenpinbowling;

import static com.jobsity.tenpinbowling.FrameState.*;

public class Frame {

    protected FrameState state = EMPTY;

    protected Integer score1;

    protected Integer score2;

    private Integer total;

    public void acceptScore(Integer score) {
        switch (state) {
            case EMPTY:
                acceptFirstScore(score);
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

    protected void acceptFirstScore(Integer score) {
        score1 = score;
        if (isStrike(score)) {
            state = CLOSED;
        } else {
            state = ACCEPTING_SECOND_SCORE;
        }
    }

    protected void acceptSecondScore(Integer score) {
        score2 = score;
        state = CLOSED;
    }

    protected void acceptThirdScore(Integer score) {
        throw new UnsupportedOperationException("This frame does not take a third score.");
    }

    public boolean isClosed() {
        return CLOSED.equals(state);
    }

    public String pinfallsToString() {
        if (isStrike(score1)) {
            return "\t\tX";
        }
        if (score1 + score2 == 10) {
            return String.format("\t%d\t/", score1);
        }
        return String.format("\t%d\t%d", score1, score2);
    }

    protected boolean isStrike(Integer score) {
        return Integer.valueOf(10).equals(score);
    }
}
