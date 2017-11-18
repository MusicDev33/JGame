package com.musicdev.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Crafting {

	ShapeRenderer shapes;

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

	}

	public void render() {
		if (active) {
			shapes.begin(ShapeRenderer.ShapeType.Filled);
			shapes.setColor(Color.WHITE);
			shapes.rect(this.x - (this.wid / 2), this.y - (this.hei / 2), this.wid, this.hei);
			shapes.end();
		}

	}

}
