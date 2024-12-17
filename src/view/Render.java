/**
 * @file Task.java
 * @date 14/12/2022
 * Used to render a different view depending on the current state of the game
 */
package view;

import utils.GameStates;
import java.awt.Graphics;

public class Render {

	private final GUIManager GUIManager; // manages all the views of the application

	/**
	 * Class constructor
	 * @param GUIManager: manages all the views of the application
	 */
	public Render(GUIManager GUIManager) {
		this.GUIManager = GUIManager;
	}

	/**
	 * Renders a view depending on the GameState
	 * @param g: graphics component of the app
	 */
	public void render(Graphics g) {
		switch (GameStates.gameState) {
			case MENU -> GUIManager.getMenu().render(g);
			case PLAYING -> GUIManager.getGameGUI().render(g);
			case GAMEOVER -> GUIManager.getGameOver().render(g);
			default -> {
			}
		}
	}
}
