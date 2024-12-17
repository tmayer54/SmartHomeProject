/**
 * @file InfoBarUI.java
 * @date 27/12/2022
 * UI that displays all the information of the house and weather to the player.
 */
package ui;

import static utils.GameStates.MENU;
import static utils.GameStates.setGameState;

import java.awt.*;
import java.util.ArrayList;

import model.Game;
import model.Person;
import utils.LoadSave;
import utils.PlayingStates;

public class InfoBarUI extends UIComponent {

	private final Game game;
	private ArrayList<Button> buttons; // buttons of the UI

	/**
	 * Class constructor specifying the size and position of the window, and the gameGUI
	 * @param x: horizontal position of the UI
	 * @param y: vertical position of the UI
	 * @param width: width of the UI
	 * @param height: height of the UI
	 * @param game: game model
	 */
	public InfoBarUI(int x, int y, int width, int height, Game game) {
		super(x, y, width, height);
		initButtons();
		this.game = game;
	}

	/**
	 * Initializes the buttons of the info bar
	 */
	private void initButtons() {
		this.buttons = new ArrayList<>();
		this.buttons.add(new Button("Menu", x + 5, y + 5, 100, 30, 0));
		this.buttons.add(new Button("Save", x + 5, y + 45, 100, 30, 1));
	}

	/**
	 * Draws the bar on the current scene
	 * @param g: graphics component of the app
	 */
	public void draw(Graphics g) {
		g.setColor(new Color(220, 123, 15));
		g.fillRect(this.x, this.y, this.width, this.height);
		g.drawLine(this.x, this.y - 1, this.width, this.height);
		this.buttons.forEach(button -> {
			if (button.getId() == 1) {
				switch(PlayingStates.playingState) {
					case TASK : button.draw(g);
				}
			}
			else 
				button.draw(g);
		});
		drawText(g);
	}

	/**
	 * Draws all the information about the house and the weather on the scene
	 * @param g: graphics component of the app
	 */
	private void drawText(Graphics g) {

		Graphics2D graphics2D = (Graphics2D) g;
		graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		graphics2D.drawLine(x, y, x + width, y);

		graphics2D.drawString("Day #" + this.game.getDayNumber(), this.x + 115, this.y + 20);
		graphics2D.drawString("Money : " + this.game.getHouse().getFamily().getMoney(), this.x + 115, this.y + 45);
		graphics2D.drawString("Energy : " + this.game.getHouse().getEnergy(), this.x + 115, this.y + 70);

		for (Person person: this.game.getHouse().getFamily().getPersons()) {
			graphics2D.drawString(person.toString(), this.x + 220, this.y + 20 + 25 * person.getId());
		}

		graphics2D.drawString(this.game.getWeather().toString(), this.x + 10, this.y + 95);
		graphics2D.drawString(this.game.getHouse().toString(), this.x + 10, this.y + 112);
	}
	
	/**
	 * Save the current game
	 */
	private void saveGame() {
		float temp;
		int i = 0;
		int j = 0;
		int idArr[] = new int[8+this.game.getBoughtPerks().size()+1];
		idArr[i++] = this.game.getDayNumber();
		idArr[i++] = this.game.getHouse().getFamily().getMoney();
		idArr[i++] = this.game.getHouse().getEnergy();
		temp = this.game.getWeather().getTemperature()*100;
		idArr[i++] = (int) temp;
		temp = this.game.getWeather().getHumidityRate()*100;
		idArr[i++] = (int) temp;
		if (this.game.getWeather().isRainy())
			idArr[i++] = 0;
		else if (this.game.getWeather().isSnowy())
			idArr[i++] = 1;
		else if (this.game.getWeather().isSunny())
			idArr[i++] = 2;
		else if (this.game.getWeather().isLightning())
			idArr[i++] = 3;
		else
			idArr[i++] = 2;
		temp = this.game.getHouse().getTemperature()*100;
		idArr[i++] = (int) temp;
		temp = this.game.getHouse().getHumidityRate()*100;
		idArr[i++] = (int) temp;
		idArr[i++] = this.game.getBoughtPerks().size();
		//System.out.println("Get bought perk quantity : "+ this.game.getBoughtPerks().size());

		while (j < this.game.getBoughtPerks().size()) {
			idArr[i++] = this.game.getBoughtPerks().get(j).ID();
			//System.out.println("Bought Perk ID : " +this.game.getBoughtPerks().get(j).ID());
			j++;
		}
		
		LoadSave.SaveLevel("game", idArr);
	}

	/**
	 * Called when the user clicks anywhere on the screen. Used to know if he clicked on the menu or save buttons.
	 * @param x: x position of the mouse
	 * @param y: y position of the mouse
	 */
	public void mouseClicked(int x, int y) {
		this.buttons.forEach(button -> {
			if (button.getBounds().contains(x, y)) {
				switch (button.getId()) {
					case 0 -> setGameState(MENU);
					case 1 -> {
						switch(PlayingStates.playingState) {
							case TASK : saveGame();
						}
					}
				}
			}
		});
	}

	public void mouseMoved(int x, int y) {
		this.buttons.forEach(button -> button.setIsMouseOver(button.getBounds().contains(x, y)));
	}

	public void mousePressed(int x, int y) {
		this.buttons.forEach(button -> button.setIsMousePressed(button.getBounds().contains(x, y)));
	}

	public void mouseReleased(int x, int y) {
		this.buttons.forEach(Button::resetBooleans);
	}
}
