package com.musicdev.model;

import java.util.Random;
import java.util.Timer;

import com.badlogic.gdx.graphics.Texture;
import com.musicdev.model.Tile.Installed;
import com.musicdev.model.Tile.TileType;

public class Player {
	int x;
	int y;
	int nx;
	int ny;
	int n;

	int destinationX;
	int destinationY;

	boolean hasDestination = false;

	boolean isTimerOn = false;
	long startTime;
	long elapsedTime;
	World world;
	Random rand = new Random();
	Texture img;
	Timer timer = new Timer();

	public Player(int x, int y, World world) {
		this.x = x;
		this.y = y;
		this.world = world;
		this.img = new Texture("donut.png");
	}

	public void move(int x, int y) {
		if (this.x == 0 && x < 0) {
			this.x += Math.abs(x);
		} else {
			this.x += x;
		}
		if (this.y == 0 && y < 0) {
			this.y += Math.abs(y);
		} else {
			this.y += y;
		}
	}

	public void Build(Installed object, int x, int y) {
		hasDestination = true;
		destinationX = x;
		destinationY = y;
	}

	public void Pathfind(int x, int y) {
		System.out.println("Find");
		if ((this.x - x) < 0) {
			move(1, 0);
		} else if ((this.x - x) > 0) {
			move(-1, 0);
		}

		if ((this.y - y) < 0 && this.x - x == 0) {
			move(0, 1);
		} else if ((this.y - y) > 0 && this.x - x == 0) {
			move(0, -1);
		}
	}

	public void wander() {
		nx = rand.nextInt(3) - 1;
		ny = rand.nextInt(3) - 1;
		move(nx, ny);
		System.out.println("Wander");
	}

	public void randomPlace() {
		n = rand.nextInt(2);
		if (n == 1) {
			world.GetTileAt(x, y).SetType(TileType.Water);
		}
	}

	public void update(float deltaTime, World world) {
		if (isTimerOn == false) {
			startTime = System.currentTimeMillis();
			isTimerOn = true;
		}
		elapsedTime = System.currentTimeMillis() - startTime;
		if (elapsedTime >= 200) {
			if (hasDestination == true) {
				Pathfind(destinationX, destinationY);
			} else {
				wander();
			}

			isTimerOn = false;
		}
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public Texture getImg() {
		return this.img;
	}

	public void movePlayer(int x, int y) {

	}

}
