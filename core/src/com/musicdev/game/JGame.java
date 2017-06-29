package com.musicdev.game;

import java.io.IOException;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.musicdev.model.Player;
import com.musicdev.model.World;
import com.musicdev.screens.MainMenu;

public class JGame extends Game {
	public SpriteBatch batch;
	World world;
	EventHandler eHandler;
	Texture empty;
	Texture select;
	Player player;
	public Camera cam;
	public Save save;

	public boolean toLoadOrNotToLoad;

	int startNum = 0;
	float deltaTime;

	public int screenX = 1600;
	public int screenY = 900;

	@Override
	public void create() {
		batch = new SpriteBatch();
		cam = new Camera(screenX, screenY);
		batch.setProjectionMatrix(cam.camera.combined);
		batch.enableBlending();
		try {
			save = new Save();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.setScreen(new MainMenu(this));

	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		screenX = width;
		screenY = height;
		cam.camera.viewportHeight = height;
		cam.camera.viewportWidth = width;
		cam = new Camera(width, height);
		cam.camera.update();
		batch.setProjectionMatrix(cam.camera.combined);
		eHandler = new EventHandler(world, cam, player, save);
	}

	@Override
	public void dispose() {
		batch.dispose();

	}

}
