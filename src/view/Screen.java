/**
 * @file GameScreen.java
 * @date 27/12/2022
 * Main screen of the game, defining its dimension
 */
package view;

import java.awt.*;
import javax.swing.JPanel;
import ui.MyMouseListener;

public class Screen extends JPanel {

	private final GUIManager GUIManager; // manages all the views of the application
	private Dimension size; // dimension of the window

	/**
	 * Class constructor
	 * @param GUIManager: manages all the views of the application
	 */
	public Screen(GUIManager GUIManager) {
		this.GUIManager = GUIManager;
		setPanelSize();
		initInputs();
	}

	private void initInputs() {
		MyMouseListener myMouseListener = new MyMouseListener(GUIManager);
		addMouseListener(myMouseListener);
		requestFocus();
	}

	/**
	 * Sets the size of the app window
	 */
	private void setPanelSize() {
		size = new Dimension(700, 720);
		setMinimumSize(size);
		setPreferredSize(size);
		setMaximumSize(size);
	}

	/**
	 * Renders the whole game
	 * @param g: graphics component of the app
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		GUIManager.getRender().render(g);
	}

	/**
	 * Getter
	 */
	public Dimension getSize() {
		return this.size;
	}
}
