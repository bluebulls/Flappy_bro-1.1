package com.blue.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.blue.game.blue;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width=blue.WIDTH;
		config.height=blue.HEIGHT;
		config.title=blue.TITLE;
		new LwjglApplication(new blue(), config);
	}
}
