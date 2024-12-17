/**
 * @file UIComponent.java
 * @date 27/12/2022
 * All the UIs share the same base, a position and a size
 */
package ui;

public class UIComponent {
	protected int x, y, width, height; // horizontal position, vertical position, width of the component, height of the component

	/**
	 * Class constructor specifying the size and position of the window
	 * @param x: horizontal position of the UI
	 * @param y: vertical position of the UI
	 * @param width: width of the UI
	 * @param height: height of the UI
	 */
	public UIComponent(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
}