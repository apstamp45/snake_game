package com.apstamp45.snake_game.game;

import com.apstamp45.snake_game.Main;
import com.apstamp45.snake_game.game.snake.Snake;
import com.apstamp45.snake_game.game.snake.TailSegment;
import com.apstamp45.snake_game.graphics.Pixel;

/**
 * Represents an apple in the snake game.
 * 
 * @author apstamp45
 */
public class Apple {

    /** The apple's color. */
    private Pixel color;

    /** The apple's x position. */
    private int x;

    /** The apple's y position. */
    private int y;

    /**
     * Creates an apple with a random
     * position.
     * @param snake The snake.
     * @param width The canvas' width.
     * @param height The canvas' height.
     * @param color The apple's color.
     */
    public Apple(Snake snake, int width, int height, Pixel color) {
        this.color = color;
        getRandomPosition(width, height, snake);
    }

    /** Draws the apple. */
    public void draw() {
        Main.setPixel(x, y, color);
    }

    /**
     * Gets a new random position.
     * @param width The canvas' width.
     * @param height The canvas' height.
     * @param snake The snake.
     * @return An Apple with a random position.
     */
    public void getRandomPosition(int width, int height, Snake snake) {
        int[][] availablePositions = new int[width * height - (snake.tail.size() + 1)][2];
        int nextPosition = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                boolean isAvailablePosition = true;
                if (snake.head.x == i && snake.head.y == j) {
                    isAvailablePosition = false;
                } else {
                    for (TailSegment tailSegment: snake.tail) {
                        if (tailSegment.x == i && tailSegment.y == j) {
                            isAvailablePosition = false;
                            break;
                        }
                    }
                }
                if (isAvailablePosition) {
                    int[] position = {i, j};
                    availablePositions[nextPosition] = position;
                    nextPosition++;
                }
            }
        }
        int randomNum = (int) (Math.random() * availablePositions.length);
        x = availablePositions[randomNum][0];
        y = availablePositions[randomNum][1];
    }
}
