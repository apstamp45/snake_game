package com.apstamp45.snake_game;

import com.apstamp45.snake_game.event.KeyboardEventHandler;
import com.apstamp45.snake_game.game.Apple;
import com.apstamp45.snake_game.game.snake.Head;
import com.apstamp45.snake_game.game.snake.Snake;
import com.apstamp45.snake_game.graphics.Pixel;

import javafx.scene.input.KeyCode;

/**
 * This is the main class of the snake game.
 * @author apstamp45
 */
public class Main {

    /** The width of the canvas. */
    public static int CANVAS_WIDTH;

    /** The canvas height. */
    public static int CANVAS_HEIGHT;

    /** The window's title. */
    public static final String WINDOW_TITLE = "Snake";

    /**
     * The canvas will be filled with this 
     * color before drawing.
     */
    private static final Pixel DEFAULT_CANVAS_COLOR = new Pixel(0, 0, 0, false);

    /** Default pixel color. */
    private static final Pixel DEFAULT_PIXEL_COLOR = new Pixel(55, 55, 55, false);

    /** The pixel array's height. */
    public static final int PIXEL_ARRAY_HEIGHT = 50;

    /** the pixel array's width. */
    public static final int PIXEL_ARRAY_WIDTH = 50;

    /** The pixel segment size. */
    private static final int PIXEL_SEGMENT_SIZE = 10;

    /** The spacing size. */
    private static final int SPACING_SIZE = 2;

    /**
     * Stores the current frame. 
     * Will be reset after moving the snake.
     */
    private static int currentFrame;

    /**
     * The snake will be moved every
     * framesPerMove frames
     */
    private static int framesPerMove = 10;

    /**
     * Array of Pixels to store all the
     * window "pixels".
     */
    private static Pixel[][] pixelArray = new Pixel[PIXEL_ARRAY_WIDTH][PIXEL_ARRAY_HEIGHT];

    /** The apple. */
    private static Apple apple;

    /** The snake. */
    private static Snake snake;

    /**
     * Runs the window.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        CANVAS_HEIGHT = (PIXEL_SEGMENT_SIZE + SPACING_SIZE) * PIXEL_ARRAY_HEIGHT;
        CANVAS_WIDTH = (PIXEL_SEGMENT_SIZE + SPACING_SIZE) * PIXEL_ARRAY_WIDTH;
        Window.run(args);
    }

    /**
     * The start function. Will be called 
     * after the window is created.
     * @param args Command line arguments.
     */
    public static void start(String[] args) {
        snake = new Snake(0, 0, 1, 0, 2, new Pixel(0, 255, 0, false));
        apple = new Apple(snake, PIXEL_ARRAY_WIDTH, PIXEL_ARRAY_HEIGHT, new Pixel(255, 0, 0, false));
    }

    /**
     * The loop function. Will be called
     * every animation frame.
     * @param args Command line arguments.
     */
    public static void loop(String[] args) {
        if (currentFrame == framesPerMove) {
            updateDirection();
            if (snake.collidedWithTail()) {
                System.exit(0);
            }
            if (snake.collidedWithApple(apple)) {
                snake.addSegment();
                apple.getRandomPosition(PIXEL_ARRAY_WIDTH, PIXEL_ARRAY_HEIGHT, snake);
            }
            snake.move();
            draw();
            currentFrame = 0;
        } else {
            currentFrame++;
        }
    }

    /**
     * The end function. This function will be
     * called on the close of the window.
     * @param args Command line arguments.
     */
    public static void end(String[] args) {
    }

    /** Draws on the canvas. */
    private static void draw() {
        clearPixels();
        apple.draw();
        snake.draw();
        Window.fill(DEFAULT_CANVAS_COLOR);
        for (int i = 0; i < pixelArray.length; i++) {
            for (int j = 0; j < pixelArray[0].length; j++) {
                Window.drawRect(i * (PIXEL_SEGMENT_SIZE + SPACING_SIZE), j * (PIXEL_SEGMENT_SIZE + SPACING_SIZE), PIXEL_SEGMENT_SIZE, PIXEL_SEGMENT_SIZE, pixelArray[i][j]);
            }
        }
    }

    /**
     * Updates the snake's direction
     * depending on the last key pressed.
     */
    private static void updateDirection() {
        if (KeyboardEventHandler.lastKeyPressed == KeyCode.UP) {
            if (snake.head.getDirection() != Head.Direction.DOWN) {
                snake.head.setDirection(Head.Direction.UP);
            }
        } else if (KeyboardEventHandler.lastKeyPressed == KeyCode.DOWN) {
            if (snake.head.getDirection() != Head.Direction.UP) {
                snake.head.setDirection(Head.Direction.DOWN);
            }
        } else if (KeyboardEventHandler.lastKeyPressed == KeyCode.RIGHT) {
            if (snake.head.getDirection() != Head.Direction.LEFT) {
                snake.head.setDirection(Head.Direction.RIGHT);
            }
        } else if (KeyboardEventHandler.lastKeyPressed == KeyCode.LEFT) {
            if (snake.head.getDirection() != Head.Direction.RIGHT) {
                snake.head.setDirection(Head.Direction.LEFT);
            }
        }
    }

    /**
     * Sets all of pixels in pixelArray to 
     * the default pixel. 
     */
    private static void clearPixels() {
        setPixelRect(0, 0, PIXEL_ARRAY_WIDTH, PIXEL_ARRAY_HEIGHT, DEFAULT_PIXEL_COLOR);
    }

    /**
     * Sets a pixel to the Pixel array.
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @param pixel The pixel to be set.
     */
    public static void setPixel(int x, int y, Pixel pixel) {
        pixelArray[x][y] = pixel;
    }

    /**
     * Sets a rectangle of pixels in the Pixel array.
     * @param x The starting x position of the rectangle.
     * @param y The starting y position of the rectangle.
     * @param width The width of the rectangle.
     * @param height The height of the rectangle.
     * @param pixel The pixel to be set.
     */
    public static void setPixelRect(int x, int y, int width, int height, Pixel pixel) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                pixelArray[x + i][j + y] = pixel;
            }
        }
    }
}
