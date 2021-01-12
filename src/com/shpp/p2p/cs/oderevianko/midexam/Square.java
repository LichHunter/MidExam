package com.shpp.p2p.cs.oderevianko.midexam;

import acm.graphics.GRect;

/**
 * Class contains information about square
 */
public class Square {
	private GRect square; // contains square it self which is on the screen
	private boolean moveToMouse; // contains direction of movement of square (true - to mouse, false - from it)

	/**
	 * Main constructor
	 * @param square GRect which contains square which will be displayed on the screed
	 * @param moveToMouse direction of movement of square (true - to mouse, false - from it)
	 */
	public Square(GRect square, boolean moveToMouse) {
		this.square = square;
		this.moveToMouse = moveToMouse;
	}

	//
	//Getters and Setters
	//

	public GRect getSquare() {
		return square;
	}

	public void setMoveToMouse(boolean moveToMouse) {
		this.moveToMouse = moveToMouse;
	}

	public boolean isMoveToMouse() {
		return moveToMouse;
	}
}
