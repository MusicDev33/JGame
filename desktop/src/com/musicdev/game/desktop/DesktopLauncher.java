package com.musicdev.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.musicdev.game.JGame;

public class DesktopLauncher {
	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = JGame.screenY;
		config.width = JGame.screenX;
		config.foregroundFPS = 59;
		new LwjglApplication(new JGame(), config);

	}
}
