/**
 * @file Game.java
 * @date 27/12/2022
 * Main view of the program, updates the view at a constant rate, displays a different scene depending on the GameState
 */
package view;

import javax.swing.JFrame;

public class GUIManager extends JFrame implements Runnable {

	private final Screen screen; // screen of the application
	private final Render render; // used to render a different view depending on the GameState
	private final Menu menu; // instance of the menu view
	private GameGUI gameGUI; // instance of the in game view
	private final GameOver gameOver; // instance of the gameOver view

	/**
	 * Class constructor
	 */
	public GUIManager() {
		render = new Render(this);
		screen = new Screen(this);
		menu = new Menu(this);
		gameGUI = new GameGUI(this);
		gameOver = new GameOver(this);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		add(screen);
		pack();
		setVisible(true);
	}

	/**
	 * Start the thread of the game
	 */
	public void start() {
		Thread gameThread = new Thread(this) {};
		gameThread.start();
	}

	/**
	 * Main game loop, updates the view and the game model
	 */
	@Override
	public void run() {
		long lastFrame = System.nanoTime();
		double FPS_SET = 90.0;
		double timePerFrame = 1000000000.0 / FPS_SET;

		long now;

		while (true) {
			now = System.nanoTime();
			// Render
			if (now - lastFrame >= timePerFrame) {
				lastFrame = now;
				repaint();
			}
		}
	}

	/**
	 * Getters and setters
	 */
	public Render getRender() {
		return render;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setGameGUI(GameGUI gameGUI) {
		this.gameGUI = gameGUI;
	}

	public GameOver getGameOver() {
		return this.gameOver;
	}

	public Screen getScreen() {
		return this.screen;
	}

	public GameGUI getGameGUI() {
		return this.gameGUI;
	}
}
