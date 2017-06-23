package com.musicdev.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.musicdev.game.Camera;
import com.musicdev.game.JGame;
import com.musicdev.model.FontHandler;

public class MainMenu implements Screen {
	SpriteBatch batch;
	JGame game;
	Camera cam;
	Texture playButton;
	Texture playButtonHovered;

	FontHandler fontHandler;
	ShapeRenderer shapes;

	GlyphLayout layoutPlay;
	float widthPlay;
	float heightPlay;

	public MainMenu(JGame game) {
		this.game = game;
		this.batch = game.batch;
		shapes = new ShapeRenderer();

		fontHandler = new FontHandler();
		layoutPlay = new GlyphLayout(fontHandler.font90, "PLAY");
		widthPlay = layoutPlay.width;
		heightPlay = layoutPlay.height;
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

		if (Gdx.input.isTouched()) {
			if (Gdx.input.getX() < (game.screenX / 2) + widthPlay / 2
					&& Gdx.input.getX() > (game.screenX / 2) - widthPlay / 2) {
				if ((Math.round(game.screenY) - Gdx.input.getY()) < (game.screenY / 2) + 200
						&& (Math.round(game.screenY) - Gdx.input.getY()) > (game.screenY / 2) + 200 - heightPlay) {
					game.setScreen(new GameScreen(game));
				}
			}
		}

		if (Gdx.input.getX() < (game.screenX / 2) + widthPlay / 2
				&& Gdx.input.getX() > (game.screenX / 2) - widthPlay / 2) {
			if ((Math.round(game.screenY) - Gdx.input.getY()) < (game.screenY / 2) + 200
					&& (Math.round(game.screenY) - Gdx.input.getY()) > (game.screenY / 2) + 200 - heightPlay) {
				shapes.begin(ShapeRenderer.ShapeType.Line);
				shapes.setColor(Color.BLACK);
				shapes.rect(((game.screenX / 2) - widthPlay / 2) - 10, (game.screenY / 2) + 200 - heightPlay, widthPlay,
						heightPlay);
				shapes.end();
			}
		}

		game.batch.begin();
		fontHandler.font90.setColor(Color.RED);
		fontHandler.font90.draw(game.batch, "PLAY", (game.screenX / 2) - widthPlay / 2, (game.screenY / 2) + 200);

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
