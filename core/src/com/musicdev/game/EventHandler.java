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
}
