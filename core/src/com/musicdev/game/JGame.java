package com.musicdev.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
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
	Texture select;
	Player player;
	Camera cam;

	int startNum = 0;
	float deltaTime;

	int screenX = 1600;
	int screenY = 900;

	@Override
	public void create() {
		cam = new Camera(screenX, screenY);
		batch = new SpriteBatch();
		batch.setProjectionMatrix(cam.camera.combined);
		batch.enableBlending();
		world = new World(30, 30);
		player = new Player(1, 1, world);
		eHandler = new EventHandler(world, cam, player);
		empty = new Texture("blank.png");
		select = new Texture("selecttile.png");

	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		deltaTime = Gdx.graphics.getDeltaTime();
		update(deltaTime);
		entityupdate(deltaTime);
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

				if (x < world.Width() && y < world.Height() && x >= 0 && y >= 0) {

					if (world.GetTileAt(x, y).hasObject == true) {
						batch.draw(world.GetTileAt(x, y).GetInstalledImg(), (x * 64) - cam.correctionX,
								(y * 64) - cam.correctionY);
					}
				}

			}
		}
		if (eHandler.handleMouseX(deltaTime) < world.Width() && eHandler.handleMouseY(deltaTime) < world.Height()
				&& (eHandler.rawMouse(deltaTime)[0]) > 0 && (eHandler.rawMouse(deltaTime)[1]) > 0) {
			batch.draw(select, (eHandler.handleMouseX(deltaTime) * 64) - cam.correctionX,
					(eHandler.handleMouseY(deltaTime) * 64) - cam.correctionY);
			Gdx.graphics.setTitle("JGame " + Integer.toString(Gdx.graphics.getFramesPerSecond()) + " FPS "
					+ eHandler.tileHover(eHandler.handleMouseX(deltaTime), eHandler.handleMouseY(deltaTime)).GetType()
					+ " " + player.buildPercentage + "%");
		} else {
			Gdx.graphics.setTitle("JGame " + Integer.toString(Gdx.graphics.getFramesPerSecond()) + " FPS "
					+ "No Tile Selected" + player.buildPercentage + "%");
		}

		batch.draw(player.getImg(), (player.getX() * 64) - cam.correctionX, (player.getY() * 64) - cam.correctionY);
		batch.end();

	}

	public void update(float deltaTime) {
		eHandler.update(deltaTime);

	}

	public void entityupdate(float deltaTime) {
		player.update(deltaTime, world);
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
		eHandler = new EventHandler(world, cam, player);
	}

	@Override
	public void dispose() {
		batch.dispose();

	}
}
