package com.musicdev.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.musicdev.model.Player;
import com.musicdev.model.World;

public class JGame extends ApplicationAdapter {
	SpriteBatch batch;
	World world;
	EventHandler eHandler;
	Texture empty;
	Player player;
	Camera cam;

	int startNum = 0;
	float deltaTime;

	int screenX = 1600;
	int screenY = 900;

	@Override
	public void create() {
		cam = new Camera(screenX, screenY);
		eHandler = new EventHandler(world, cam);
		batch = new SpriteBatch();
		batch.setProjectionMatrix(cam.camera.combined);
		world = new World(30, 30);
		player = new Player(1, 1, world);
		empty = new Texture("blank.png");

	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		deltaTime = Gdx.graphics.getDeltaTime();
		update(deltaTime);
		entityupdate();
		batch.begin();
		for (int x = -2 + cam.correctionX / 64; x < world.Width() - (world.Width() - (screenX / 64))
				+ (cam.correctionX / 64) + 1; x++) {
			for (int y = -2 + cam.correctionY / 64; y < world.Height() - (world.Height() - (screenY / 64))
					+ (cam.correctionY / 64) + 1; y++) {

				if (x >= world.Width() || y >= world.Height() || x < 0 || y < 0) {
					batch.draw(empty, (x * 64) - cam.correctionX, (y * 64) - cam.correctionY);
				} else {
					batch.draw(world.GetTileAt(x, y).GetImg(), (x * 64) - cam.correctionX, (y * 64) - cam.correctionY);
				}
			}
		}

		batch.draw(player.getImg(), (player.getX() * 64) - cam.correctionX, (player.getY() * 64) - cam.correctionY);
		batch.end();
		Gdx.graphics.setTitle("JGame " + Integer.toString(Gdx.graphics.getFramesPerSecond()) + " FPS");

	}

	public void update(float deltaTime) {
		if (Gdx.input.isKeyPressed(Keys.D))
			if (cam.correctionX < (world.Width() * 64) - screenX / 2)
				moveCamera(eHandler.camMoveSpeed, 0);
			else {

			}

		else if (Gdx.input.isKeyPressed(Keys.A))
			if (cam.correctionX > -screenX / 2)
				moveCamera(-eHandler.camMoveSpeed, 0);
			else {

			}

		else if (Gdx.input.isKeyPressed(Keys.W))
			if (cam.correctionY < (world.Height() * 64) - screenY / 2)
				moveCamera(0, eHandler.camMoveSpeed);
			else {

			}

		else if (Gdx.input.isKeyPressed(Keys.S))
			if (cam.correctionY > -screenY / 2)
				moveCamera(0, -eHandler.camMoveSpeed);
			else {

			}

	}

	public void entityupdate() {
		player.update(Gdx.graphics.getDeltaTime(), world);
	}

	@Override
	public void resize(int width, int height) {
		screenX = width;
		screenY = height;
		cam.camera.viewportHeight = height;
		cam.camera.viewportWidth = width;
		cam.camera.setToOrtho(false, width, height);
		cam.camera.update();
		cam.camera.position.set(width / 2, height / 2, 0);
		batch.setProjectionMatrix(cam.camera.combined);
	}

	@Override
	public void dispose() {
		batch.dispose();

	}

	public void moveCamera(float x, float y) {
		cam.camera.position.x += x * Gdx.graphics.getDeltaTime();
		cam.camera.position.y += y * Gdx.graphics.getDeltaTime();
		cam.correctionX += x * Gdx.graphics.getDeltaTime();
		cam.correctionY += y * Gdx.graphics.getDeltaTime();

	}

}
