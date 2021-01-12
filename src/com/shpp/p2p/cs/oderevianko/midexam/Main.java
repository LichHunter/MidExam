package com.shpp.p2p.cs.oderevianko.midexam;

import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Random;

/**
 * Class contains methods to accomplish given task
 */
public class Main extends WindowProgram {
	//set window size to 0 if you want program to determine it automatically
	private final int WINDOW_WIDTH = 0; // window width
	private final int WINDOW_HEIGHT = 0; // window height
	//Attention! ROW, COLUMNS, SPACE_BETWEEN_SQUARES can't be 0
	private int ROWS = 10; // number of rows on screen
	private int COLUMNS = 10; // number of columns on screen
	private int SPACE_BETWEEN_SQUARES = 20; // space between squares
	private int SQUARE_SIZE = 50; // length of square side
	private Color SQUARE_COLOR = Color.BLACK; // square color
	private Square[][] SQUARES = new Square[ROWS][COLUMNS]; // here will be contained all squares that appear on screen
	private int MOUSE_X_COORDINATE = 0; // x coordinates of mouse
	private int MOUSE_Y_COORDINATE = 0; // y coordinates of mouse
	private boolean MOUSE_STATE = false; // false - released mouse, true - mouse is pressed
	private final double PAUSE_TIME = 1000.0 / 100;

	@Override
	public void run() {
		setScreenSize();
		addMouseListeners();
		feelScreenWithSquares();

		moveSquares();
	}

	/**
	 * Method will parse though SQUARES array and move each of square
	 */
	private void moveSquares() {
		while (true) {
			for (Square[] row : SQUARES) {
				for (Square square : row) {
					moveSquare(square);
				}
			}

			pause(PAUSE_TIME);
		}
	}

	/**
	 * Method will move square to or from a mouse pointer depending on square.isMoveToMouse()
	 * @param square contains rectangle which we will move
	 */
	private void moveSquare(Square square) {
		double x = square.getSquare().getX();
		double y = square.getSquare().getY();
		square.getSquare().move(x < MOUSE_X_COORDINATE ? 1 : -1, y < MOUSE_Y_COORDINATE ? 1 : -1);

		if (square.isMoveToMouse()) {
			square.getSquare().setLocation(x < MOUSE_X_COORDINATE ? x + 1 : x - 1,
					y < MOUSE_Y_COORDINATE ? y + 1 : y - 1);
		} else {
			square.getSquare().setLocation(x < MOUSE_X_COORDINATE ? x - 1 : x + 1,
					y < MOUSE_Y_COORDINATE ? y - 1 : y + 1);
		}
	}

	/**
	 * Method will fill screen with squares
	 */
	private void feelScreenWithSquares() {
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				int x = j * (SQUARE_SIZE + SPACE_BETWEEN_SQUARES);
				int y = i * (SQUARE_SIZE + SPACE_BETWEEN_SQUARES);

				//create square on given coordinates
				createSquare(i, j, x, y);
			}
		}
	}

	/**
	 * Method will create square with given coordinates
	 * @param i integer which contains row number of square
	 * @param j integer which contains column number of square
	 * @param x integer which contains x coordinate of square
	 * @param y integer which contains y coordinate of square
	 */
	private void createSquare(int i, int j, int x, int y) {
		Square square = new Square(new GRect(x, y, SQUARE_SIZE, SQUARE_SIZE), new Random().nextBoolean());
		square.getSquare().setFilled(true);
		square.getSquare().setColor(SQUARE_COLOR);
		SQUARES[i][j] = square;

		add(square.getSquare());
	}

	/**
	 * Method will set default screen size if WINDOW_WIDTH and WINDOW_HEIGHT == 0
	 * else will use given size and determine using formula the size of square
	 */
	private void setScreenSize() {
		if (WINDOW_HEIGHT == 0 || WINDOW_WIDTH == 0) {
			int windowHeight = (SQUARE_SIZE + SPACE_BETWEEN_SQUARES) * ROWS;
			int windowWidth = (SQUARE_SIZE + SPACE_BETWEEN_SQUARES) * COLUMNS;

			setSize(windowWidth, windowHeight);
		} else {
			setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

			SQUARE_SIZE = WINDOW_WIDTH > WINDOW_HEIGHT ?
					(WINDOW_HEIGHT / ROWS) - (ROWS * SPACE_BETWEEN_SQUARES) :
					(WINDOW_WIDTH / COLUMNS) - (COLUMNS * SPACE_BETWEEN_SQUARES);
		}
	}

	/**
	 * Update mouse coordinates
	 * @param event
	 */
	@Override
	public void mouseMoved(MouseEvent event) {
		MOUSE_X_COORDINATE = event.getX();
		MOUSE_Y_COORDINATE = event.getY();
	}

	/**
	 * Update direction of square motion
	 * if square was moving from mouse -> it will move to mouse
	 * if square was moving to mouse -> it will move from mouse
	 * @param event
	 */
	@Override
	public void mousePressed(MouseEvent event) {
		if (!MOUSE_STATE) {
			for (Square[] row : SQUARES) {
				for (Square square : row) {
					square.setMoveToMouse(!square.isMoveToMouse());
				}
			}
			MOUSE_STATE = true;
		}
	}

	/**
	 * Update direction of square motion
	 * if square was moving from mouse -> it will move to mouse
	 * if square was moving to mouse -> it will move from mouse
	 * @param event
	 */
	@Override
	public void mouseReleased(MouseEvent event) {
		if (MOUSE_STATE) {
			for (Square[] row : SQUARES) {
				for (Square square : row) {
					square.setMoveToMouse(!square.isMoveToMouse());
				}
			}
			MOUSE_STATE = false;
		}
	}
}
