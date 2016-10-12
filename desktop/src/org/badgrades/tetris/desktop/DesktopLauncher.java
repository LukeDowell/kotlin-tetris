package org.badgrades.tetris.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.badgrades.tetris.TetrisGame;
import org.badgrades.tetris.world.GameRenderer;
import org.badgrades.tetris.world.TetrisWorld;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		int height = Math.round(TetrisWorld.GRID_HEIGHT * GameRenderer.VISUAL_UNITS);
		int width = Math.round(TetrisWorld.GRID_WIDTH * GameRenderer.VISUAL_UNITS);
		config.width = width;
		config.height = height;
		System.out.println(height + " -- " + width);
		new LwjglApplication(new TetrisGame(), config);
	}
}
