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
	int playY;

	GlyphLayout layoutLoad;
	float widthLoad;
	float heightLoad;
	int loadY;

	GlyphLayout layoutExit;
	float widthExit;
	float heightExit;
	int exitY;

	GlyphLayout layoutCantLoad;
	float widthCantLoad;

	boolean cantLoad = false;

	public MainMenu(JGame game) {
		this.game = game;
		this.batch = game.batch;
		playY = (game.screenY / 2) + 200;
		loadY = (game.screenY / 2) + 100;
		exitY = (game.screenY / 2);
		shapes = new ShapeRenderer();

		fontHandler = new FontHandler();

		layoutPlay = new GlyphLayout(fontHandler.font90, "PLAY");
		widthPlay = layoutPlay.width;
		heightPlay = layoutPlay.height;

		layoutLoad = new GlyphLayout(fontHandler.font90, "LOAD");
		widthLoad = layoutLoad.width;
		heightLoad = layoutLoad.height;

		layoutExit = new GlyphLayout(fontHandler.font90, "EXIT");
		widthExit = layoutExit.width;
		heightExit = layoutExit.height;

		layoutCantLoad = new GlyphLayout(fontHandler.font90, "That doesn't quite  work yet bro.");
		widthCantLoad = layoutCantLoad.width;
	}

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if (Gdx.input.getX() < (game.screenX / 2) + widthPlay / 2
				&& Gdx.input.getX() > (game.screenX / 2) - widthPlay / 2) {
			if ((Math.round(game.screenY) - Gdx.input.getY()) < playY
					&& (Math.round(game.screenY) - Gdx.input.getY()) > playY - heightPlay) {
				shapes.begin(ShapeRenderer.ShapeType.Line);
				shapes.setColor(Color.BLACK);
				shapes.rect(((game.screenX / 2) - widthPlay / 2) - (widthPlay / 20), playY - heightPlay,
						widthPlay + (widthPlay / 20), heightPlay + 10);
				shapes.end();
				System.out.println();
				if (Gdx.input.isTouched()) {
					game.setScreen(new GameScreen(game));
				}
			}
		}

		if (Gdx.input.getX() < (game.screenX / 2) + widthLoad / 2
				&& Gdx.input.getX() > (game.screenX / 2) - widthLoad / 2) {

			if ((Math.round(game.screenY) - Gdx.input.getY()) < loadY
					&& (Math.round(game.screenY) - Gdx.input.getY()) > loadY - heightLoad) {

				shapes.begin(ShapeRenderer.ShapeType.Line);

				shapes.setColor(Color.BLACK);
				shapes.rect(((game.screenX / 2) - widthLoad / 2) - (widthLoad / 20), loadY - heightLoad,
						widthLoad + (widthLoad / 20), heightLoad + 10);

				shapes.end();

				if (Gdx.input.isTouched()) {
					cantLoad = true;
				}
			}
		}

		if (Gdx.input.getX() < (game.screenX / 2) + widthExit / 2
				&& Gdx.input.getX() > (game.screenX / 2) - widthExit / 2) {

			if ((Math.round(game.screenY) - Gdx.input.getY()) < exitY
					&& (Math.round(game.screenY) - Gdx.input.getY()) > exitY - heightExit) {

				shapes.begin(ShapeRenderer.ShapeType.Line);

				shapes.setColor(Color.BLACK);
				shapes.rect(((game.screenX / 2) - widthExit / 2) - (widthExit / 20), exitY - heightExit,
						widthExit + (widthExit / 20), heightExit + 10);

				shapes.end();

				if (Gdx.input.isTouched()) {
					Gdx.app.exit();
				}
			}
		}

		game.batch.begin();
		fontHandler.font90.setColor(Color.RED);
		fontHandler.font32.setColor(Color.RED);
		fontHandler.font90.draw(game.batch, "PLAY", (game.screenX / 2) - widthPlay / 2, playY);
		fontHandler.font90.draw(game.batch, "LOAD", (game.screenX / 2) - widthPlay / 2, loadY);
		fontHandler.font90.draw(game.batch, "EXIT", (game.screenX / 2) - widthPlay / 2, exitY);

		if (cantLoad) {
			fontHandler.font32.draw(game.batch, "That doesn't quite  work yet bro.",
					(game.screenX / 2) - (widthCantLoad / 6), 200);
		}

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
