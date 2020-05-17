package com.jobsity.tenpinbowling.scoreboard;

import com.jobsity.tenpinbowling.frame.Frame;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ScoreboardBuilder {

    private final String name;

    private final List<Frame> frames = new ArrayList<>();

    private AcceptScoreStrategy acceptScoreStrategy = new AcceptScoreStrategyA(0);

    public ScoreboardBuilder(String name) {
        if (StringUtils.isEmpty(name)) {
            throw new IllegalArgumentException("Name cannot be empty: " + name);
        }
        this.name = name;
    }

    public Scoreboard build() {
        if (frames.size() != 10) {
            throw new IllegalStateException("Scoreboard is incomplete. Expected ten frames, found " + frames.size());
        }
        return new Scoreboard(name, frames);
    }

    public void acceptScore(Integer score) {
        acceptScore(score, false);
    }

    public void acceptFoul() {
        acceptScore(0, true);
    }

    private void acceptScore(Integer score, boolean isFoul) {
        if (acceptScoreStrategy == null) {
            // This board is already completed. Ignoring this score.
            return;
        }
        AcceptScoreStrategy nextStrategy;
        if (isFoul) {
            nextStrategy = acceptScoreStrategy.acceptFoulAndChangeStrategy(frames);
        } else {
            nextStrategy = acceptScoreStrategy.acceptAndChangeStrategy(score, frames);
        }
        acceptScoreStrategy = nextStrategy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScoreboardBuilder that = (ScoreboardBuilder) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
