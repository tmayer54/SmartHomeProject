package view;

import java.awt.*;

import model.Game;
import ui.Button;

import static utils.GameStates.MENU;
import static utils.GameStates.setGameState;

public class GameOver extends GameScene implements SceneMethods {

	private Button menuButton; // button to go back to the menu
	
	/**
	 * Class constructor
	 * @param GUIManager: manages all the views of the application
	 */
	public GameOver(GUIManager GUIManager) {
		super(GUIManager);
		initButtons();
	}
	
	private void initButtons() {
		int buttonWidth = 150;
		int buttonHeight = buttonWidth / 3;
		int x = this.GUIManager.getScreen().getSize().width / 2 - buttonWidth / 2;
		int y = 300;
		this.menuButton = new Button("Menu", x, y, buttonWidth, buttonHeight);
	}

	@Override
	public void render(Graphics g) {
		this.menuButton.draw(g);
		Graphics2D graphics2D = (Graphics2D) g;
		graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphics2D.drawString("Game over!", this.GUIManager.getScreen().getSize().width / 2 - 35, 200);
		graphics2D.drawString(this.GUIManager.getGameGUI().getGame().getGameOverReason(), this.GUIManager.getScreen().getSize().width / 2 - 70, 225);
		graphics2D.drawString("Number of days: " + this.GUIManager.getGameGUI().getGame().getDayNumber(), this.GUIManager.getScreen().getSize().width / 2 - 50, 250);
	}

	@Override
	public void mouseClicked(int x, int y) {
		if (this.menuButton.getBounds().contains(x, y)) {
			setGameState(MENU);
		}
	}

	@Override
	public void mouseMoved(int x, int y) {
		this.menuButton.setIsMouseOver(this.menuButton.getBounds().contains(x, y));
	}

	@Override
	public void mousePressed(int x, int y) {
		this.menuButton.setIsMousePressed(this.menuButton.getBounds().contains(x, y));
	}

	@Override
	public void mouseReleased(int x, int y) {
		this.menuButton.resetBooleans();
	}

	@Override
	public void mouseDragged(int x, int y) {
	}
}
