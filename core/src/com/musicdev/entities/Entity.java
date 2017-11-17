package com.musicdev.entities;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

import com.badlogic.gdx.graphics.Texture;
import com.musicdev.model.Tile;
import com.musicdev.model.Tile.TileType;
import com.musicdev.model.World;
import com.musicdev.tools.Vector3;

public class Entity {
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
	ArrayList<Vector3> distList;
	Vector3 tileSet;

	boolean installingTileExists = false;

	public Entity(int x, int y, World world) {
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

	public boolean moveCheck(int x, int y) {
		if (x > 0 && this.x > 0) {
			if (world.GetTileAt(this.x + Math.abs(x), this.y).solid == false) {
				return true;
			}

		} else if (x < 0 && this.x < world.Width()) {
			if (world.GetTileAt(this.x - Math.abs(x), this.y).solid == false) {
				return true;
			}

		}
		if (y > 0 && this.y > 0) {
			if (world.GetTileAt(this.x, this.y + Math.abs(y)).solid == false) {
				return true;
			}

		} else if (y < 0 && this.y < world.Height()) {
			if (world.GetTileAt(this.x, this.y - Math.abs(y)).solid == false) {
				return true;
			}

		}

		else {
			return false;
		}
		return false;
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
		distList = new ArrayList<Vector3>();
		for (int x = 0; x < world.Width(); x++) {
			for (int y = 0; y < world.Height(); y++) {
				if (world.GetTileAt(x, y).installing == true) {
					double a = Math.abs(x - this.x);
					double b = Math.abs(y - this.y);
					tileDistance = (int) Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
					tileSet = new Vector3(x, y, tileDistance);
					distList.add(tileSet);

				}
			}
		}

		for (int s = 0; s <= distList.size() - 1; s++) {
			for (int k = 0; k <= distList.size() - 2; k++) {
				if (distList.get(k).getZ() > distList.get(k + 1).getZ()) {
					Vector3 temp = new Vector3(0, 0, 0);
					temp = distList.get(k);

					distList.set(k, distList.get(k + 1));
					distList.set((k + 1), temp);

				}
			}
		}
		for (int i = distList.size() - 1; i >= 0; i--) {
			// System.out.println(distList.get(i));
			// System.out.println(i);
			nextTile = world.GetTileAt(distList.get(i).getX(), distList.get(i).getY());
			PreBuild(nextTile.x, nextTile.y);
		}

	}

	public void Pathfind(int x, int y) {

		if ((this.x - x) < 0) {
			if (moveCheck(1, 0)) {
				move(1, 0);
			}
		} else if ((this.x - x) > 0) {
			if (moveCheck(-1, 0)) {
				move(-1, 0);
			}
		}

		if ((this.y - y) < 0 && this.x - x == 0) {
			if (moveCheck(0, 1)) {
				move(0, 1);
			}
		} else if ((this.y - y) > 0 && this.x - x == 0) {
			if (moveCheck(0, -1)) {
				move(0, -1);
			}
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
		System.out.println(this.x + " " + this.y);

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
