package com.apstamp45.snake_game.game.snake;

/**
 * Represents the snake's head.
 * @author apstamp45
 */
public class Head extends Segment {

    /** Contains all of the four main directions. */
    public enum Direction {UP, DOWN, RIGHT, LEFT};

    /** The head's x speed. */
    int xSpeed;

    /** The head's y speed. */
    int ySpeed;

    public Head.Direction direction;

    /**
     * Creates a snake's head.
     * @param x The head's x coordinate.
     * @param y The head's y coordinate.
     * @param xSpeed The head's x speed.
     * @param ySpeed the head's y speed.
     */
    public Head(int x, int y, int xSpeed, int ySpeed) {
        this.x = x;
        this.y = y;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    public Head.Direction getDirection() {
        return direction;
    }

    /**
     * Moves the snake's head.
     * @param canvasWidth
     * @param canvasHeight
     */
    public void move(int canvasWidth, int canvasHeight) {
        x += xSpeed;
        y += ySpeed;
        if (x < 0) {
            x = canvasWidth - 1;
        } else if (x >= canvasWidth) {
            x = 0;
        }
        if (y < 0) {
            y = canvasHeight - 1;
        } else if (y >= canvasHeight) {
            y = 0;
        }
    }

    /**
     * Sets the head's direction.
     * @param direction The head's direction.
     */
    public void setDirection(Head.Direction direction) {
        if (direction == Head.Direction.UP) {
            this.xSpeed = 0;
            this.ySpeed = -1;
        } else if (direction == Head.Direction.DOWN) {
            this.xSpeed = 0;
            this.ySpeed = 1;
        } else if (direction == Head.Direction.RIGHT) {
            this.xSpeed = 1;
            this.ySpeed = 0;
        } else if (direction == Head.Direction.LEFT) {
            this.xSpeed = -1;
            this.ySpeed = 0;
        }
        this.direction = direction;
    }
}
