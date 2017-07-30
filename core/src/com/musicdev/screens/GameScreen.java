package com.musicdev.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.musicdev.entities.Entity;
import com.musicdev.game.Camera;
import com.musicdev.game.EventHandler;
import com.musicdev.game.JGame;
import com.musicdev.game.Save;
import com.musicdev.mapgen.HistoryGen;
import com.musicdev.model.FontHandler;
import com.musicdev.model.Tile.Installed;
import com.musicdev.model.World;

public class GameScreen implements Screen {

	JGame game;
	World world;
	SpriteBatch batch;
	EventHandler eHandler;
	Texture empty;
	Texture select;
	Entity entity;
	Camera cam;
	Save save;

	HistoryGen voltaire; // Because Voltaire was a historian, yo.

	FontHandler fontHandler;

	int tileSize = 64;

	int screenX;
	int screenY;

	float deltaTime;

	public GameScreen(JGame game) {

		fontHandler = new FontHandler();

		this.game = game;
		this.batch = game.batch;
		this.screenX = game.screenX;
		this.screenY = game.screenY;
		this.cam = new Camera(this.screenX, this.screenY);
		this.batch.setProjectionMatrix(this.cam.camera.combined);
		this.batch.enableBlending();
		this.save = game.save;

	}

	public void createWorld() {

		world = new World(100, 100, game.toLoadOrNotToLoad);
		save.setSaveSeed(world.getSeed());
		save.setWorld(world);
		entity = new Entity(1, 1, world);
		eHandler = new EventHandler(world, cam, entity, save);
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
		for (int x = -3 + cam.correctionX / tileSize; x < world.Width() - (world.Width() - (this.screenX / tileSize))
				+ (cam.correctionX / tileSize) + 2; x++) {
			for (int y = -3 + cam.correctionY / tileSize; y < world.Height()
					- (world.Height() - (this.screenY / tileSize)) + (cam.correctionY / tileSize) + 1; y++) {

				if (x >= world.Width() || y >= world.Height() || x < 0 || y < 0) {
					batch.draw(empty, (x * tileSize) - cam.correctionX, (y * tileSize) - cam.correctionY);
				} else {
					batch.draw(world.GetTileAt(x, y).GetImg(), (x * tileSize) - cam.correctionX,
							(y * tileSize) - cam.correctionY);
				}

				if (x < world.Width() && y < world.Height() && x >= 0 && y >= 0) {

					if (world.GetTileAt(x, y).hasObject == true && world.GetTileAt(x, y).object != Installed.None) {
						batch.draw(world.GetTileAt(x, y).GetInstalledImg(), (x * tileSize) - cam.correctionX,
								(y * tileSize) - cam.correctionY);
					}
				}
			}
		}

		if (eHandler.handleMouseX(deltaTime) < world.Width() && eHandler.handleMouseY(deltaTime) < world.Height()
				&& (eHandler.rawMouse(deltaTime)[0]) > 0 && (eHandler.rawMouse(deltaTime)[1]) > 0) {

			batch.draw(select, (eHandler.handleMouseX(deltaTime) * tileSize) - cam.correctionX,
					(eHandler.handleMouseY(deltaTime) * tileSize) - cam.correctionY);

			updateTitle(true);

		} else {

			updateTitle(false);
		}

		batch.draw(entity.getImg(), (entity.getX() * tileSize) - cam.correctionX,
				(entity.getY() * tileSize) - cam.correctionY);

		batch.end();

	}

	@Override
	public void resize(int width, int height) {

		this.screenX = width;
		this.screenY = height;
		this.cam.camera.viewportHeight = height;
		this.cam.camera.viewportWidth = width;
		this.cam = new Camera(width, height);
		this.cam.camera.update();
		this.batch.setProjectionMatrix(this.cam.camera.combined);
		this.eHandler = new EventHandler(world, this.cam, entity, save);

	}

	public void update(float deltaTime) {
		eHandler.update(deltaTime);
	}

	public void entityUpdate(float deltaTime) {
		entity.update(deltaTime, world);
	}

	public void updateTitle(boolean onMap) {

		if (onMap) {
			Gdx.graphics.setTitle("JGame " + Integer.toString(Gdx.graphics.getFramesPerSecond()) + " FPS "
					+ eHandler.tileHover(eHandler.handleMouseX(deltaTime), eHandler.handleMouseY(deltaTime)).GetType()
					+ " " + entity.buildPercentage + "%");
		} else {
			Gdx.graphics.setTitle("JGame " + Integer.toString(Gdx.graphics.getFramesPerSecond()) + " FPS "
					+ "No Tile Selected" + " " + entity.buildPercentage + "%");
		}

	}

	public void print(String stuff) {

		System.out.println(stuff);

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
