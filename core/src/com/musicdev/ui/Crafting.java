package com.musicdev.ui;

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
		heightSave = layoutExit.height;

	}

	public void render(SpriteBatch batch) {
		if (active) {
			shapes.begin(ShapeRenderer.ShapeType.Filled);
			shapes.setColor(Color.WHITE);
			shapes.rect(this.x - (this.wid / 2), this.y - (this.hei / 2), this.wid, this.hei);
			shapes.end();

			batch.begin();
			fontHandler.font32.setColor(Color.RED);
			fontHandler.font32.draw(batch, "Save", this.x - (widthSave / 2), this.y - (heightSave / 2) + 170);
			fontHandler.font32.draw(batch, "X", this.x + (this.wid / 2) - widthExit, this.y + (this.hei / 2) - 5);
			batch.end();

		}

	}

}
