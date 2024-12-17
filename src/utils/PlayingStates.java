/**
 * @file PlayingStates.java
 * @date 27/12/2022
 * Used to know in which part of the game the user is currently in (tasks picking, during the day or buying perks)
 */
package utils;

public enum PlayingStates {

	TASK, DAY, PERK; // picking the persons tasks, during the day executing the tasks, buying new perks

	public static PlayingStates playingState = TASK; // current state of the game

	/**
	 * Static method called anywhere in the application used to change the current in game UI
	 * @param state: The new state
	 */
	public static void setPlayingState(PlayingStates state) {
		playingState = state;
	}
}
