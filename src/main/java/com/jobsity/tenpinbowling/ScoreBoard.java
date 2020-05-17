package com.jobsity.tenpinbowling;

import java.util.*;

import static com.jobsity.tenpinbowling.FramePos.*;

public class ScoreBoard {

    private String name;

    private Map<FramePos, Frame> frames;

    private Iterator<FramePos> framePosIterator;

    private FramePos currentFramePos;

    public ScoreBoard(String name) {
        this.name = name;
        frames = new LinkedHashMap<>();
        frames.put(FRAME_1, new Frame());
        frames.put(FRAME_2, new Frame());
        frames.put(FRAME_3, new Frame());
        frames.put(FRAME_4, new Frame());
        frames.put(FRAME_5, new Frame());
        frames.put(FRAME_6, new Frame());
        frames.put(FRAME_7, new Frame());
        frames.put(FRAME_8, new Frame());
        frames.put(FRAME_9, new Frame());
        frames.put(FRAME_10, new Frame10());
        framePosIterator = Arrays.asList(FramePos.values()).iterator();
        currentFramePos = framePosIterator.next();
    }

    public void acceptFoul() {
        acceptScore(0, true);
    }

    public void acceptScore(Integer score) {
        acceptScore(score, false);
    }

    private void acceptScore(Integer score, boolean isFoul) {
        if (score < 0 || score > 10) {
            throw new IllegalArgumentException("Scores must be between 0 and 10. " + name + "\t" + score);
        }
        if (END.equals(currentFramePos)) {
            throw new IllegalStateException("This board cannot take anymore scores.");
        }
        Frame frame = frames.get(currentFramePos);
        frame.acceptScore(score, isFoul);
        if (frame.isClosed() && framePosIterator.hasNext()) {
            currentFramePos = framePosIterator.next();
        }
    }

    public String getName() {
        return name;
    }

    public Map<FramePos, Frame> getFrames() {
        return frames;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScoreBoard that = (ScoreBoard) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
