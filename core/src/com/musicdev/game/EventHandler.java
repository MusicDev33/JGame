package com.musicdev.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.musicdev.model.Player;
import com.musicdev.model.Tile;
import com.musicdev.model.World;

public class EventHandler {
	float camMoveSpeed = 500;
	World world;
	Camera cam;
	Player player;
	int[] xy = new int[2];

	public EventHandler(World world, Camera camera, Player player) {
		this.world = world;
		this.cam = camera;
		this.player = player;
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

		else if (Gdx.input.isKeyPressed(Keys.SPACE)) {
			world.GetTileAt(player.getX(), player.getY()).SetType(Tile.TileType.Dirt);
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

	public String tileHover(int x, int y) {
		return world.GetTileAt(x, y).GetType();
	}
}
