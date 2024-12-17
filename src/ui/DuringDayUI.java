/**
 * @file DuringDayUI.java
 * @date 31/12/2022
 * UI used to display and execute all the tasks of a day
 */
package ui;

import java.awt.*;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static utils.PlayingStates.PERK;
import static utils.PlayingStates.setPlayingState;
import java.util.ArrayList;
import model.Person;
import view.GameGUI;

public class DuringDayUI extends UIComponent {

	private final GameGUI gameGUI; // gameGUI containing the game model
	private Button nextTaskButton; // button used to execute the next task
	private ArrayList<Person> persons; // persons of the house
	private int currentTaskIndex; // index of the current task
	private boolean areAllTasksDone; // tells if all the tasks are completed

	/**
	 * Class constructor specifying the size and position of the window, and the gameGUI
	 * @param x: horizontal position of the UI
	 * @param y: vertical position of the UI
	 * @param width: width of the UI
	 * @param height: height of the UI
	 * @param gameGUI: class that contains the game model
	 */
	public DuringDayUI(int x, int y, int width, int height, GameGUI gameGUI) {
		super(x, y, width, height);
		this.gameGUI = gameGUI;
		this.persons = gameGUI.getGame().getHouse().getFamily().getPersons();
		this.currentTaskIndex = -1;
		this.areAllTasksDone = false;
		initButtons();
	}

	/**
	 * Initializes the buttons of the UI
	 */
	private void initButtons() {
		this.nextTaskButton = new Button("Next Task", (x + width / 2) - 60, (y + height) - 50, 120, 30);
	}

	/**
	 * Draws components the UI on the current scene
	 * @param g: graphics component of the app
	 */
	public void draw(Graphics g) {
		g.setColor(new Color(220, 123, 15));
		g.fillRect(x, y, width, height);
		nextTaskButton.draw(g);
		drawText(g);
	}

	/**
	 * Draws the text indicating the task already done on the screen
	 * @param g: graphics component of the app
	 */
	public void drawText(Graphics g) {
		int x = this.x + 20;
		int yStart = this.y + 50;

		Graphics2D graphics2D = (Graphics2D) g;
		graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphics2D.drawString("Day #" + gameGUI.getGame().getDayNumber() + " is going on !", this.y + this.width / 2 - 40, 20);
		int screenSplitWidth = (this.width - x) / this.persons.size();

		this.persons.forEach(person -> {
			int personTextX = x + (this.persons.indexOf(person) * screenSplitWidth);
			graphics2D.drawLine(personTextX + screenSplitWidth, yStart - 10, personTextX + screenSplitWidth, this.y + this.height - 100);
			graphics2D.drawString(person.getName() + ":", personTextX + 5, yStart);
			for (int i = 0; i < person.getStamina(); i++) {
				(new Button("", personTextX + screenSplitWidth - 15 * (i + 2), yStart - 10, 10, 10)).draw(g);
			}
			for (int i = 0; i <= min(this.currentTaskIndex, person.getTasks().size() - 1); i++) {
				graphics2D.drawString(person.getTasks().get(i).message(), personTextX + 15, yStart + 20 * (i + 1));
			}
		});
	}

	
	public void onNewDay() {
		this.persons = gameGUI.getGame().getHouse().getFamily().getPersons();
		this.currentTaskIndex = -1;
		this.areAllTasksDone = false;
		this.nextTaskButton.setText("Next task");
		this.nextTaskButton.setWidth(120);
		this.nextTaskButton.setX(this.y + this.width / 2 - 60);
	}

	/**
	 * Called when the user clicks anywhere on the screen. Used to know if the user clicked on the Next task button
	 * @param x: x position of the mouse
	 * @param y: y position of the mouse
	 */
	public void mouseClicked(int x, int y) {
		if (this.nextTaskButton.getBounds().contains(x, y)) {
			if (this.areAllTasksDone) {
				setPlayingState(PERK);
			} else {
				int tasksNumber = 0;
				for (Person person : this.persons) {
					tasksNumber = max(person.getTasks().size(), tasksNumber);
				}
				this.currentTaskIndex++;
				this.gameGUI.getGame().doNthTaskOfAllPersons(this.currentTaskIndex);
				this.areAllTasksDone = tasksNumber == this.currentTaskIndex + 1;
				if (this.areAllTasksDone) {
					this.nextTaskButton.setText("End current day");
					this.nextTaskButton.setWidth(150);
					this.nextTaskButton.setX(this.y + this.width / 2 - 75);
				}
			}
		}
	}

	public void mouseMoved(int x, int y) {
		this.nextTaskButton.setIsMouseOver(nextTaskButton.getBounds().contains(x, y));
	}

	public void mousePressed(int x, int y) {
		this.nextTaskButton.setIsMousePressed(nextTaskButton.getBounds().contains(x, y));
	}

	public void mouseReleased(int x, int y) {
		nextTaskButton.resetBooleans();
	}

}
