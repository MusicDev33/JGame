package com.musicdev.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.musicdev.model.World;

public class JGame extends ApplicationAdapter {
	SpriteBatch batch;
	World world;
	OrthographicCamera camera = new OrthographicCamera();
	Texture empty;

	int startNum = 0;
	float deltaTime;
	float camMoveSpeed = 300;

	int camCorrectionX = 0;
	int camCorrectionY = 0;

	int screenX = 1600;
	int screenY = 900;

	float startX = (camera.position.x - camera.viewportWidth / 2) / 64;
	float startY = (camera.position.y - camera.viewportHeight / 2) / 64;

	@Override
	public void create() {
		camera.setToOrtho(false, screenX, screenY);
		camera.position.set(screenX / 2, screenY / 2, 0);
		batch = new SpriteBatch();
		batch.setProjectionMatrix(camera.combined);
		world = new World(300, 300);
		empty = new Texture("blank.png");

	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		deltaTime = Gdx.graphics.getDeltaTime();
		update(deltaTime);
		batch.begin();
		for (int x = -1 + camCorrectionX / 64; x < world.Width() - (world.Width() - (screenX / 64))
				+ (camCorrectionX / 64) + 1; x++) {
			for (int y = -1 + camCorrectionY / 64; y < world.Height() - (world.Height() - (screenY / 64))
					+ (camCorrectionY / 64) + 1; y++) {

				if (x >= world.Width() || y >= world.Height() || x < 0 || y < 0) {
					batch.draw(empty, (x * 64) - camCorrectionX, (y * 64) - camCorrectionY);
				} else {
					batch.draw(world.GetTileAt(x, y).GetImg(), (x * 64) - camCorrectionX, (y * 64) - camCorrectionY);
				}
			}
		}
		batch.end();
		Gdx.graphics.setTitle("JGame " + Integer.toString(Gdx.graphics.getFramesPerSecond()) + " FPS");

	}

	public void update(float deltaTime) {
		if (Gdx.input.isKeyPressed(Keys.D))
			if (camCorrectionX < (world.Width() * 64) - screenX / 2)
				moveCamera(camMoveSpeed, 0);
			else {

			}

		else if (Gdx.input.isKeyPressed(Keys.A))
			if (camCorrectionX > -screenX / 2)
				moveCamera(-camMoveSpeed, 0);
			else {

			}

		else if (Gdx.input.isKeyPressed(Keys.W))
			if (camCorrectionY < (world.Height() * 64) - screenY / 2)
				moveCamera(0, camMoveSpeed);
			else {

			}

		else if (Gdx.input.isKeyPressed(Keys.S))
			if (camCorrectionY > -screenY / 2)
				moveCamera(0, -camMoveSpeed);
			else {

			}

	}

	@Override
	public void resize(int width, int height) {
		screenX = width;
		screenY = height;
		camera.viewportHeight = height;
		camera.viewportWidth = width;
		camera.setToOrtho(false, width, height);
		camera.update();
		camera.position.set(width / 2, height / 2, 0);
		batch.setProjectionMatrix(camera.combined);
	}

	@Override
	public void dispose() {
		batch.dispose();

	}

	public void moveCamera(float x, float y) {
		camera.position.x += x * Gdx.graphics.getDeltaTime();
		camera.position.y += y * Gdx.graphics.getDeltaTime();
		camCorrectionX += x * Gdx.graphics.getDeltaTime();
		camCorrectionY += y * Gdx.graphics.getDeltaTime();

	}

}
