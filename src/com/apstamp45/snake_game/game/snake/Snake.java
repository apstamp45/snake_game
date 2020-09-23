package com.apstamp45.snake_game.game.snake;

import java.util.ArrayList;

import com.apstamp45.snake_game.Main;
import com.apstamp45.snake_game.game.Apple;
import com.apstamp45.snake_game.graphics.Pixel;

/**
 * Represents the snake in the snake game.
 * @author apstamp45
 */
public class Snake {

    /** The snake's color. */
    private Pixel color;

    /** The snake's head. */
    public Head head;

    /** The snake's tail. */
    public ArrayList<TailSegment> tail;
    
    /**
     * Creates a snake.
     * @param x The snake head's x coordinate.
     * @param y The snake head's y coordinate.
     * @param xSpeed The snake's head's x speed.
     * @param ySpeed The snake's head's y speed.
     * @param tailLength the length of the tail
     * to be added to the snake.
     * @param color The snake's color.
     */
    public Snake(int x, int y, int xSpeed, int ySpeed, int tailLength, Pixel color) {
        this.color = color;
        tail = new ArrayList<>();
        head = new Head(x, y, xSpeed, ySpeed);
        for (int i = 0; i < tailLength; i++) {
            addSegment();
            move();
        }
    }

	/** Adds a segment to the snake's tail. */
    public void addSegment() {
        if (tail.size() == 0) {
            tail.add(new TailSegment(head));
        } else {
            tail.add(new TailSegment(tail.get(tail.size() - 1)));
        }
    }

    /**
     * Checks if the snake's head is touching the apple.
     * @param apple The apple.
     * @return If the snake has collided, true.
     */
    public boolean collidedWithApple(Apple apple) {
        return head.x + head.xSpeed == apple.x && head.y + head.ySpeed == apple.y;
    }

    /** Draws the snake. */
    public void draw() {
        Main.setPixel(head.x, head.y, color);
        for (TailSegment tailSegment: tail) {
            Main.setPixel(tailSegment.x, tailSegment.y, color);
        }
    }

    /** Moves the snake. */
    public void move() {
        for (int i = tail.size() - 1; i >= 0; i--) {
            tail.get(i).move();
        }
        head.move(Main.PIXEL_ARRAY_WIDTH, Main.PIXEL_ARRAY_HEIGHT);
    }
}
