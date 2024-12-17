/**
 * @file GameScene.java
 * @date 27/12/2022
 * Base of each scene of the game
 */
package view;

public class GameScene {

	protected GUIManager GUIManager; // manages all the views of the application

	public GameScene(GUIManager GUIManager) {
		this.GUIManager = GUIManager;
	}

	public GUIManager getGUIManager() {
		return GUIManager;
	}

}
