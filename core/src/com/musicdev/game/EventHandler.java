package com.musicdev.game;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.musicdev.entities.Entity;
import com.musicdev.model.Tile;
import com.musicdev.model.World;

public class EventHandler {
	float camMoveSpeed = 500;
	World world;
	Camera cam;
	Entity entity;
	Save save;
	int[] xy = new int[2];
	int tileOrder = 1;

	public boolean showUI = false;

	int accessNum = 1;

	Tile.Installed currentTile;

	public EventHandler(World world, Camera camera, Entity entity, Save save) {
		this.world = world;
		this.cam = camera;
		this.entity = entity;
		this.save = save;
		this.currentTile = Tile.Installed.values()[accessNum];
	}

	public void update(float deltaTime) {
		if (Gdx.input.isKeyPressed(Keys.D))
			if (cam.correctionX < (world.Width() * 64) - cam.screenX / 2)
				this.cam.move(camMoveSpeed, 0, deltaTime);
			else {

			}

		else if (Gdx.input.isKeyPressed(Keys.A))
			if (cam.correctionX > -cam.screenX / 2)
				cam.move(-camMoveSpeed, 0, deltaTime);
			else {

			}

		else if (Gdx.input.isKeyPressed(Keys.W))
			if (cam.correctionY < (world.Height() * 64) - cam.screenY / 2)
				cam.move(0, camMoveSpeed, deltaTime);
			else {

			}

		else if (Gdx.input.isKeyPressed(Keys.S))
			if (cam.correctionY > -cam.screenY / 2)
				cam.move(0, -camMoveSpeed, deltaTime);
			else {

			}

		// Change Tiles
		else if (Gdx.input.isKeyJustPressed(Keys.C)) {
			accessNum += 1;
			if (accessNum > Tile.Installed.values().length - 1) {
				accessNum = 1;

			}
			currentTile = Tile.Installed.values()[accessNum];
			System.out.println(Tile.Installed.values()[accessNum].name());
		}

		// Activate UI
		else if (Gdx.input.isKeyJustPressed(Keys.U)) {
			this.showUI = !this.showUI;
		}

		// Testing entity.getX and getY functions

		else if (Gdx.input.isKeyPressed(Keys.SPACE)) {
			world.GetTileAt(entity.getX(), entity.getY()).SetType(Tile.TileType.Dirt);
		}

		// Saving map

		else if (Gdx.input.isKeyJustPressed(Keys.P)) {
			try {
				this.save.SaveMapToText();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}

		// Placing stuff

		else if (Gdx.input.isButtonPressed(Buttons.LEFT)) {
			try {

				tileHover(handleMouseX(deltaTime), handleMouseY(deltaTime)).PreInstall(currentTile);

			} catch (NullPointerException e1) {

			}

		}

	}

	public int handleMouseX(float deltaTime) {
		return (Gdx.input.getX() + cam.correctionX) / 64;
	}

	public int handleMouseY(float deltaTime) {
		return ((Math.round(cam.screenY) - Gdx.input.getY()) + cam.correctionY) / 64;
	}

	public int[] rawMouse(float deltaTime) {
		xy[0] = Gdx.input.getX() + cam.correctionX;
		xy[1] = ((Math.round(cam.screenY) - Gdx.input.getY()) + cam.correctionY);
		return xy;
	}

	public Tile tileHover(int x, int y) {
		// System.out.println(world.GetTileAt(x, y).y);
		return world.GetTileAt(x, y);
	}
}
