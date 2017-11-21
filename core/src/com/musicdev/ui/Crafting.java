package com.musicdev.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.musicdev.model.FontHandler;

public class Crafting {

	ShapeRenderer shapes;
	FontHandler fontHandler;

	GlyphLayout layoutSave;
	float widthSave;
	float heightSave;
	float saveY;

	GlyphLayout layoutExit;
	float widthExit;
	float heightExit;
	float exitX;
	float exitY;

	int x;
	int y;
	int wid;
	int hei;

	public boolean saveMap = false;

	// Placeholder for when I clean up the code
	boolean active;

	public Crafting(int x, int y, int wid, int hei) {
		this.x = x;
		this.y = y;
		this.wid = wid;
		this.hei = hei;
		shapes = new ShapeRenderer();
		this.active = true;
		fontHandler = new FontHandler();

		layoutSave = new GlyphLayout(fontHandler.font32, "SAVE");
		widthSave = layoutSave.width;
		heightSave = layoutSave.height;

		layoutExit = new GlyphLayout(fontHandler.font32, "X");
		widthExit = layoutExit.width;
		heightExit = layoutExit.height;

	}

	public void render(SpriteBatch batch) {
		if (active) {
			shapes.begin(ShapeRenderer.ShapeType.Filled);
			shapes.setColor(Color.WHITE);
			shapes.rect(this.x - (this.wid / 2), this.y - (this.hei / 2), this.wid, this.hei);
			shapes.end();

			if (Gdx.input.getX() < this.x + (this.wid / 2) + widthExit
					&& Gdx.input.getX() > this.x + (this.wid / 2) - widthExit) {

				if ((Math.round(this.y * 2) - Gdx.input.getY()) < this.y + (this.hei / 2) - 5
						&& (Math.round(this.y * 2) - Gdx.input.getY()) > this.y + (this.hei / 2) - 5 - heightExit) {

					batch.begin();
					fontHandler.font32.setColor(Color.RED);
					fontHandler.font32.draw(batch, "Save", this.x - (widthSave / 2), this.y - (heightSave / 2) + 170);
					fontHandler.font32.setColor(Color.PINK);
					fontHandler.font32.draw(batch, "X", this.x + (this.wid / 2) - widthExit,
							this.y + (this.hei / 2) - 5);
					batch.end();

					if (Gdx.input.justTouched()) {
						active = false;
					}
				}

				else {
					batch.begin();
					fontHandler.font32.setColor(Color.RED);
					fontHandler.font32.draw(batch, "Save", this.x - (widthSave / 2), this.y - (heightSave / 2) + 170);
					fontHandler.font32.draw(batch, "X", this.x + (this.wid / 2) - widthExit,
							this.y + (this.hei / 2) - 5);
					batch.end();
				}
			}

			else {
				batch.begin();
				fontHandler.font32.setColor(Color.RED);
				fontHandler.font32.draw(batch, "Save", this.x - (widthSave / 2), this.y - (heightSave / 2) + 170);
				fontHandler.font32.draw(batch, "X", this.x + (this.wid / 2) - widthExit, this.y + (this.hei / 2) - 5);
				batch.end();
			}

			if (Gdx.input.getX() < (this.x) + widthSave / 2 && Gdx.input.getX() > this.x - (widthSave / 2)) {
				if ((Math.round(this.y * 2) - Gdx.input.getY()) < this.y - (heightSave / 2) + 170
						&& (Math.round(this.y * 2) - Gdx.input.getY()) > this.y - (heightSave * 2) + 175) {

					batch.begin();
					fontHandler.font32.setColor(Color.PINK);
					fontHandler.font32.draw(batch, "Save", this.x - (widthSave / 2), this.y - (heightSave / 2) + 170);
					fontHandler.font32.setColor(Color.RED);
					fontHandler.font32.draw(batch, "X", this.x + (this.wid / 2) - widthExit,
							this.y + (this.hei / 2) - 5);
					batch.end();

					shapes.begin(ShapeRenderer.ShapeType.Line);
					shapes.setColor(Color.BLACK);
					shapes.rect((this.x - widthSave / 2) - (widthSave / 20), this.y - (heightSave * 2) + 175,
							widthSave + (widthSave / 20), heightSave + 10);
					shapes.end();

					if (Gdx.input.justTouched()) {
						this.saveMap = true;

					}
				}

				else {
					batch.begin();
					fontHandler.font32.setColor(Color.RED);
					fontHandler.font32.draw(batch, "Save", this.x - (widthSave / 2), this.y - (heightSave / 2) + 170);
					fontHandler.font32.draw(batch, "X", this.x + (this.wid / 2) - widthExit,
							this.y + (this.hei / 2) - 5);
					batch.end();
				}
			}

		}

	}

}
