/**
 * @file GameGUI.java
 * @date 30/12/2022
 * View that manages what is displayed during the game, when the user is playing.
 */
package view;

import java.awt.Graphics;
import model.Game;
import ui.*;
import utils.GameStates;
import utils.PlayingStates;

public class GameGUI extends GameScene implements SceneMethods {

	private final Game game; // instance of the game (model)
	private final InfoBarUI infoBarUI; // info bar indicating the information of the weather and the house
	private final TasksUI tasksUI; // UI of the tasks picking
	private final DuringDayUI duringDayUI; // UI of the tasks execution
	private final PerksUI perksUI; // UI used to buy perks for the house
	private final int gameElementsHeight; // height of the main game component

	/**
	 * Class constructor
	 * @param GUIManager: manages all the views of the application
	 */
	public GameGUI(GUIManager GUIManager) {
		super(GUIManager);
		this.gameElementsHeight = this.GUIManager.getScreen().getSize().height - 120;
		this.game = new Game();
		int width = this.GUIManager.getScreen().getSize().width;
		infoBarUI = new InfoBarUI(0, gameElementsHeight, width, 120, this.game);
		tasksUI = new TasksUI(0, 0, width, gameElementsHeight, this);
		duringDayUI = new DuringDayUI(0, 0, width, gameElementsHeight, this);
		perksUI = new PerksUI(0, 0, width, gameElementsHeight, this);
	}

	public GameGUI(GUIManager GUIManager, Game game) {
		super(GUIManager);
		this.gameElementsHeight = this.GUIManager.getScreen().getSize().height - 120;
		this.game = game;
		int width = this.GUIManager.getScreen().getSize().width;
		infoBarUI = new InfoBarUI(0, gameElementsHeight, width, 120, this.game);
		tasksUI = new TasksUI(0, 0, width, gameElementsHeight, this);
		duringDayUI = new DuringDayUI(0, 0, width, gameElementsHeight, this);
		perksUI = new PerksUI(0, 0, width, gameElementsHeight, this);
	}
	
	/**
	 * Draws a different UI depending on the current state of the game
	 * @param g: graphics component of the app
	 */
	public void render(Graphics g) {
		infoBarUI.draw(g);
		switch (PlayingStates.playingState) {
			case TASK -> tasksUI.draw(g);
			case DAY -> duringDayUI.draw(g);
			case PERK -> perksUI.draw(g);
		}
	}

	public void onNewDay() {
		if (this.game.onNewDay()) {
			this.tasksUI.onNewDay();
			this.duringDayUI.onNewDay();
			this.perksUI.onNewDay();
			PlayingStates.setPlayingState(PlayingStates.TASK);
		} else {
			GameStates.setGameState(GameStates.GAMEOVER);
		}
	}
	/**
	 * Called when the user clicks anywhere on the screen
	 * @param x: x position of the mouse
	 * @param y: y position of the mouse
	 */
	@Override
	public void mouseClicked(int x, int y) {
		if (y >= this.gameElementsHeight)
			infoBarUI.mouseClicked(x, y);

		switch (PlayingStates.playingState) {
			case TASK -> tasksUI.mouseClicked(x, y);
			case DAY -> duringDayUI.mouseClicked(x, y);
			case PERK -> perksUI.mouseClicked(x, y);
		}
	}

	@Override
	public void mouseMoved(int x, int y) {
		if (y >= this.gameElementsHeight)
			infoBarUI.mouseMoved(x, y);
		switch (PlayingStates.playingState) {
			case TASK -> tasksUI.mouseMoved(x, y);
			case DAY -> duringDayUI.mouseMoved(x, y);
			case PERK -> perksUI.mouseMoved(x, y);
		}

	}

	@Override
	public void mousePressed(int x, int y) {
		if (y >= this.gameElementsHeight)
			infoBarUI.mousePressed(x, y);
		switch (PlayingStates.playingState) {
			case TASK -> tasksUI.mousePressed(x, y);
			case DAY -> duringDayUI.mousePressed(x, y);
			case PERK -> perksUI.mousePressed(x, y);
		}
	}

	@Override
	public void mouseReleased(int x, int y) {
		infoBarUI.mouseReleased(x, y);
		switch (PlayingStates.playingState) {
			case TASK -> tasksUI.mouseReleased(x, y);
			case DAY -> duringDayUI.mouseReleased(x, y);
			case PERK -> perksUI.mouseReleased(x, y);
		}
	}

	@Override
	public void mouseDragged(int x, int y) {}

	// Getters and setters
	public Game getGame() {
		return this.game;
	}

	public GUIManager getGUIManager() {
		return this.GUIManager;
	}
}
