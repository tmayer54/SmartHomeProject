/**
 * @file GameStates.java
 * @date 27/12/2022
 * Used to know in which part of the application the user is currently in
 */
package utils;

public enum GameStates {

	PLAYING, MENU, GAMEOVER; // currently in the game playing, in the menu, in the settings

	public static GameStates gameState = MENU; // current state of the application

	/**
	 * Static method called anywhere in the application changing the current state of the game
	 * @param state: The new state
	 */
	public static void setGameState(GameStates state) {
		gameState = state;
	}
}
