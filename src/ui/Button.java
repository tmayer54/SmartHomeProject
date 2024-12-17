/**
 * @file Button.java
 * @date 27/12/2022
 * Used to represent the buttons on the screen. Can also be used as a simple enhanced label
 */
package ui;

import java.awt.*;

public class Button {

	private int x, y, width, height, id; // horizontal position, vertical position, width of the button, height of the button, ID of the button
	private String text; // text displayed on the button
	private final Rectangle bounds; // bounds of the button (position and size)
	private boolean isMouseOver, isMousePressed; // in order to know if there is an interaction with the user

	/**
	 * Class constructor of buttons without ID
	 * @param text: text to display on the button
	 * @param x: horizontal position of the button
	 * @param y: vertical position of the button
	 * @param width: width of the button
	 * @param height: height of the button
	 */
	public Button(String text, int x, int y, int width, int height) {
		this.text = text;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.id = -1;
		this.bounds = new Rectangle(x, y, width, height);
	}

	/**
	 * Class constructor of buttons with an ID
	 * @param text: text to display on the button
	 * @param x: horizontal position of the button
	 * @param y: vertical position of the button
	 * @param width: width of the button
	 * @param height: height of the button
	 * @param id: ID of the button
	 */
	public Button(String text, int x, int y, int width, int height, int id) {
		this.text = text;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.id = id;
		this.bounds = new Rectangle(x, y, width, height);
	}

	/**
	 * Draws components the button on the current scene
	 * @param g: graphics component of the app
	 */
	public void draw(Graphics g) {
		drawBody(g);
		drawBorder(g);
		drawText(g);
	}

	/**
	 * Draws components the borders of the button
	 * @param g: graphics component of the app
	 */
	private void drawBorder(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawRect(x, y, width, height);
		if (isMousePressed) {
			g.drawRect(x + 1, y + 1, width - 2, height - 2);
			g.drawRect(x + 2, y + 2, width - 4, height - 4);
		}
	}

	/**
	 * Draws components the body of the button
	 * @param g: graphics component of the app
	 */
	private void drawBody(Graphics g) {
		if (isMouseOver)
			g.setColor(Color.GRAY);
		else
			g.setColor(Color.WHITE);
		g.fillRect(x, y, width, height);
	}

	/**
	 * Writes the text of the button
	 * @param g: graphics component of the app
	 */
	private void drawText(Graphics g) {
		int w = g.getFontMetrics().stringWidth(text);
		int h = g.getFontMetrics().getHeight();
		g.drawString(text, x - w / 2 + width / 2, y + h / 2 + height / 2);
	}

	/**
	 * Resets the button boolean
	 */
	public void resetBooleans() {
		this.isMouseOver = false;
		this.isMousePressed = false;
	}

	/**
	 * Getters and setters
	 */
	public void setIsMousePressed(boolean isMousePressed) {
		this.isMousePressed = isMousePressed;
	}

	public void setIsMouseOver(boolean isMouseOver) {
		this.isMouseOver = isMouseOver;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public int getId() {
		return id;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setX(int x) {
		this.x = x;
	}
}
