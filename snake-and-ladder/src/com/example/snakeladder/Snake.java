package com.example.snakeladder;

/**
 * A snake on the board — drags the player down from head to tail.
 */
public class Snake implements BoardEntity {
    private final int head;
    private final int tail;

    public Snake(int head, int tail) {
        if (tail >= head) throw new IllegalArgumentException("Snake tail must be below its head");
        this.head = head;
        this.tail = tail;
    }

    @Override
    public int getTriggerCell() { return head; }

    @Override
    public int getDestinationCell() { return tail; }

    @Override
    public String label() { return "Snake"; }

    @Override
    public String toString() {
        return "Snake{" + head + " -> " + tail + "}";
    }
}
