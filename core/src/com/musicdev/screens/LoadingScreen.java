package com.musicdev.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.musicdev.game.Camera;
import com.musicdev.game.JGame;
import com.musicdev.model.FontHandler;

public class LoadingScreen implements Screen {
	SpriteBatch batch;
	JGame game;
	Camera cam;

	FontHandler fontHandler;

	GlyphLayout layoutLoading;
	float widthLoading;

	GlyphLayout layoutCreatingMap;
	float widthCreatingMap;

	boolean loadingMap = false;
	boolean creatingMap = false;
	boolean settingMap = true;
	boolean randomOtherBoolean = false;

	float randomDelta = 0;

	public LoadingScreen(JGame game) {
		this.game = game;
		this.batch = game.batch;

		fontHandler = new FontHandler();

		layoutLoading = new GlyphLayout(fontHandler.font90, "Loading");
		widthLoading = layoutLoading.width;

	}

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		randomDelta += delta;

		game.batch.begin();
		fontHandler.font90.setColor(Color.BLACK);
		fontHandler.font90.draw(game.batch, "Loading", (game.screenX / 2) - widthLoading / 2, (game.screenY / 2) + 100);
		game.batch.end();
		if (randomDelta >= .1 && settingMap == true) {
			settingMap = false;
			game.setScreen(new GameScreen(game));
		}

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

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