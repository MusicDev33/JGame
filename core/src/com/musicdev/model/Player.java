package com.musicdev.model;

import java.util.Random;
import java.util.Timer;

import com.badlogic.gdx.graphics.Texture;
import com.musicdev.model.Tile.TileType;

public class Player {
	int x;
	int y;
	int nx;
	int ny;
	int n;

	public long buildPercentage = 0;

	int destinationX;
	int destinationY;

	boolean hasDestination = false;
	boolean isBuilding = false;

	boolean isTimerOn = false;
	long startTime;
	long elapsedTime;
	World world;
	Random rand = new Random();
	Texture img;
	Timer timer = new Timer();

	int tileDistance = 0;
	int nextTileDistance = 0;
	Tile nextTile;

	boolean installingTileExists = false;

	public Player(int x, int y, World world) {
		this.x = x;
		this.y = y;
		this.world = world;
		this.img = new Texture("donut.png");
	}

	public void move(int x, int y) {
		if (this.x == 0 && x < 0) {
			this.x += Math.abs(x);
		} else if (this.x == world.Width() - 1 && x > 0) {
			this.x += -Math.abs(x);
		} else {
			this.x += x;
		}
		if (this.y == 0 && y < 0) {
			this.y += Math.abs(y);
		} else if (this.y == world.Height() - 1 && y > 0) {
			this.y += -Math.abs(y);
		}

		else {
			this.y += y;
		}
	}

	public void Build(int x, int y) {
		world.GetTileAt(x, y).Install(world.GetTileAt(x, y).nextObject);
		nextTileDistance = 0;
	}

	public void PreBuild(int x, int y) {
		buildPercentage = 0;
		hasDestination = true;
		destinationX = x;
		destinationY = y;
	}

	public void Otherbuild() {
		for (int x = 0; x < world.Width(); x++) {
			for (int y = 0; y < world.Height(); y++) {
				if (world.GetTileAt(x, y).installing == true) {
					double a = Math.abs(this.x - x);
					double b = Math.abs(this.y - y);
					tileDistance = (int) Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
					if (tileDistance > nextTileDistance) {
						nextTileDistance = tileDistance;
						nextTile = world.GetTileAt(x, y);
						PreBuild(nextTile.x, nextTile.y);
						System.out.println("Player is searching");
					}

				}
			}
		}
		// PreBuild(nextTile.x, nextTile.y);

	}

	public void Pathfind(int x, int y) {
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

		if (this.x == x && this.y == y) {
			isBuilding = true;
			startTime = System.currentTimeMillis();
		}
	}

	public void wander() {
		nx = rand.nextInt(3) - 1;
		ny = rand.nextInt(3) - 1;
		move(nx, ny);

	}

	public void randomPlace() {
		n = rand.nextInt(2);
		if (n == 1) {
			world.GetTileAt(x, y).SetType(TileType.Water);
		}
	}

	public void update(float deltaTime, World world) {
		if (isBuilding == false) {
			if (isTimerOn == false) {
				startTime = System.currentTimeMillis();
				isTimerOn = true;
			}
			elapsedTime = System.currentTimeMillis() - startTime;
			if (elapsedTime >= 800) {
				if (hasDestination == true) {
					Pathfind(destinationX, destinationY);
				} else {
					wander();
				}

				isTimerOn = false;
			}
		}

		else if (isBuilding == true) {
			elapsedTime = System.currentTimeMillis() - startTime;
			buildPercentage = elapsedTime / 4;
			if (elapsedTime >= 400) {
				Build(this.x, this.y);
				isBuilding = false;
				hasDestination = false;
				buildPercentage = 0;
			}
		}
		if (hasDestination == false) {
			Otherbuild();
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

	public void Building() {
		isBuilding = true;
	}

}
