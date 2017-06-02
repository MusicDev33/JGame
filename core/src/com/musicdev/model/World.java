package com.musicdev.model;

import java.util.Random;

public class World {

	Tile[][] tiles;
	int wid;
	int hei;

	int startNum;

	Random rand = new Random();
	int n;

	public World(int wid, int hei) {
		this.wid = wid;
		this.hei = hei;

		tiles = new Tile[wid][hei];

		for (int x = 0; x < wid; x++) {
			for (int y = 0; y < hei; y++) {
				tiles[x][y] = new Tile(this, x, y);
				tiles[x][y].SetName("Tile " + startNum);
				startNum += 1;

				n = rand.nextInt(4);
				tiles[x][y].SetType(Tile.TileType.Dirt);
				switch (n) {
				case 0:
					tiles[x][y].SetType(Tile.TileType.Dirt);
					break;
				case 1:
					tiles[x][y].SetType(Tile.TileType.Stone);
					break;
				case 2:
					tiles[x][y].SetType(Tile.TileType.Grass);
					break;
				case 3:
					tiles[x][y].SetType(Tile.TileType.Water);
					break;
				default:
					tiles[x][y].SetType(Tile.TileType.Dirt);
					break;
				}

			}
		}

		System.out.println("Map created with " + wid * hei + " tiles.");
	}

	public Tile GetTileAt(int x, int y) {
		if (x >= wid || x < 0 || y >= hei || y < 0) {
			System.out.println("Tile out of range.");
			return null;
		}
		return tiles[x][y];
	}

	public int Width() {
		return wid;
	}

	public int Height() {
		return hei;
	}

}
