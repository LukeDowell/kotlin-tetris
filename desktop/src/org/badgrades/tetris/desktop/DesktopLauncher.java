package org.badgrades.tetris.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.badgrades.tetris.TetrisGame;
import org.badgrades.tetris.world.WorldRenderer;
import org.badgrades.tetris.world.TetrisWorld;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		// Why is there a black bar at the bottom ;_;
		int height = Math.round(
				TetrisWorld.GRID_HEIGHT * WorldRenderer.VISUAL_UNITS);
		int width = Math.round(
				TetrisWorld.GRID_WIDTH * WorldRenderer.VISUAL_UNITS);
		config.width = width;
		config.height = height + 16;
		new LwjglApplication(new TetrisGame(), config);
	}
}
