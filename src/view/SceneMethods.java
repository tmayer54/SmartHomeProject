/**
 * @file SceneMethods.java
 * @date 27/12/2022
 * Interface used define common methods to all scenes of the game
 */
package view;

import java.awt.Graphics;

public interface SceneMethods {

	void render(Graphics g);

	void mouseClicked(int x, int y);

	void mouseMoved(int x, int y);

	void mousePressed(int x, int y);

	void mouseReleased(int x, int y);

	void mouseDragged(int x, int y);
}
