package com.musicdev.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.musicdev.game.Camera;
import com.musicdev.game.JGame;

public class MainMenu implements Screen {
	SpriteBatch batch;
	JGame game;
	Camera cam;
	Texture playButton;
	Texture playButtonHovered;

	public MainMenu(JGame game) {
		this.game = game;
		this.batch = game.batch;
	}

	@Override
	public void show() {
		playButton = new Texture("text/play.png");
		playButtonHovered = new Texture("text/playHovered.png");

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.begin();
		if (Gdx.input.isTouched()) {
			game.setScreen(new GameScreen(game));
		}
		batch.draw(playButton, 0, 0);
		game.batch.end();

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
