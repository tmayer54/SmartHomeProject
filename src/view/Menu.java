/**
 * @file Menu.java
 * @date 27/12/2022
 * Scene of the menu of the application
 */
package view;

import java.awt.Graphics;
import java.util.ArrayList;
import ui.Button;
import utils.LoadSave;

import static utils.GameStates.PLAYING;
import static utils.GameStates.setGameState;
import static utils.PlayingStates.TASK;
import static utils.PlayingStates.setPlayingState;

public class Menu extends GameScene implements SceneMethods {

	private ArrayList<Button> buttons; // buttons of the menu

	/**
	 * Class constructor
	 * @param GUIManager: manages all the views of the application
	 */
	public Menu(GUIManager GUIManager) {
		super(GUIManager);
		initButtons();
	}

	/**
	 * Initializes the buttons of the menu
	 */
	private void initButtons() {
		int buttonWidth = 150;
		int buttonHeight = buttonWidth / 3;
		int x = this.GUIManager.getScreen().getSize().width / 2 - buttonWidth / 2;
		int y = 200;
		int yOffset = buttonHeight + 50;

		this.buttons = new ArrayList<>();
		this.buttons.add(new Button("New Game", x, y, buttonWidth, buttonHeight, 0));
		this.buttons.add(new Button("Load Save", x, y + yOffset, buttonWidth, buttonHeight, 1));
		this.buttons.add(new Button("Quit", x, y + yOffset*2, buttonWidth, buttonHeight, 2));
	}

	/**
	 * Renders the buttons on the screen
	 * @param g: graphics component of the app
	 */
	@Override
	public void render(Graphics g) {
		this.buttons.forEach(button -> button.draw(g));
	}

	/**
	 * Called when the user clicks anywhere on the screen. Used to know if he
	 * clicked on the buttons of the menu
	 * @param x: x position of the mouse
	 * @param y: y position of the mouse
	 */
	@Override
	public void mouseClicked(int x, int y) {
		this.buttons.forEach(button -> {
			if (button.getBounds().contains(x, y)) {
				switch (button.getId()) {
					case 0 -> {
						setGameState(PLAYING);
						setPlayingState(TASK);
						this.GUIManager.setGameGUI(new GameGUI(this.GUIManager));
					}
					case 1 -> {
						setGameState(PLAYING);
						setPlayingState(TASK);
						this.GUIManager.setGameGUI(new GameGUI(this.GUIManager, LoadSave.LoadGame()));
					}
					case 2 -> System.exit(0);
				}
			}
		});
	}

	@Override
	public void mouseMoved(int x, int y) {
		this.buttons.forEach(button -> button.setIsMouseOver(button.getBounds().contains(x, y)));
	}

	@Override
	public void mousePressed(int x, int y) {
		this.buttons.forEach(button -> button.setIsMousePressed(button.getBounds().contains(x, y)));
	}

	@Override
	public void mouseReleased(int x, int y) {
		this.buttons.forEach(Button::resetBooleans);
	}

	@Override
	public void mouseDragged(int x, int y) {
	}
}
