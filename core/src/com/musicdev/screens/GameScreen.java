package com.musicdev.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.musicdev.game.Camera;
import com.musicdev.game.EventHandler;
import com.musicdev.game.JGame;
import com.musicdev.game.Save;
import com.musicdev.model.Player;
import com.musicdev.model.Tile.Installed;
import com.musicdev.model.World;

public class GameScreen implements Screen {

	JGame game;
	World world;
	SpriteBatch batch;
	EventHandler eHandler;
	Texture empty;
	Texture select;
	Player player;
	Camera cam;
	Save save;

	float deltaTime;

	public GameScreen(JGame game) {
		this.game = game;
		this.batch = game.batch;
		this.cam = game.cam;
		this.save = game.save;
	}

	public void createWorld() {
		world = new World(50, 50);
		save.setSaveSeed(world.getSeed());
		save.setWorld(world);
		player = new Player(1, 1, world);
		eHandler = new EventHandler(world, cam, player, save);
		empty = new Texture("blank.png");
		select = new Texture("selecttile.png");
	}

	@Override
	public void show() {
		createWorld();

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		deltaTime = Gdx.graphics.getDeltaTime();
		update(deltaTime);
		entityUpdate(deltaTime);
		batch.begin();
		for (int x = -3 + cam.correctionX / 64; x < world.Width() - (world.Width() - (JGame.screenX / 64))
				+ (cam.correctionX / 64) + 1; x++) {
			for (int y = -3 + cam.correctionY / 64; y < world.Height() - (world.Height() - (JGame.screenY / 64))
					+ (cam.correctionY / 64) + 1; y++) {

				if (x >= world.Width() || y >= world.Height() || x < 0 || y < 0) {
					batch.draw(empty, (x * 64) - cam.correctionX, (y * 64) - cam.correctionY);
				} else {
					batch.draw(world.GetTileAt(x, y).GetImg(), (x * 64) - cam.correctionX, (y * 64) - cam.correctionY);
				}

				if (x < world.Width() && y < world.Height() && x >= 0 && y >= 0) {

					if (world.GetTileAt(x, y).hasObject == true && world.GetTileAt(x, y).object != Installed.None) {
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
					+ "No Tile Selected" + " " + player.buildPercentage + "%");
		}

		batch.draw(player.getImg(), (player.getX() * 64) - cam.correctionX, (player.getY() * 64) - cam.correctionY);
		batch.end();

	}

	@Override
	public void resize(int width, int height) {
		JGame.setScreenX(width);
		JGame.setScreenY(height);
		cam.camera.viewportHeight = height;
		cam.camera.viewportWidth = width;
		cam = new Camera(width, height);
		cam.camera.update();
		batch.setProjectionMatrix(cam.camera.combined);
		eHandler = new EventHandler(world, cam, player, save);

	}

	public void update(float deltaTime) {
		eHandler.update(deltaTime);
	}

	public void entityUpdate(float deltaTime) {
		player.update(deltaTime, world);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
