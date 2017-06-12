package com.musicdev.model;

import java.util.Random;

import com.musicdev.mapgen.SimplexNoise;

public class World {

	Tile[][] tiles;
	int wid;
	int hei;

	SimplexNoise simplex;

	int startNum;

	Random rand = new Random();
	int n;

	public World(int wid, int hei) {
		this.wid = wid;
		this.hei = hei;

		tiles = new Tile[wid][hei];

		System.out.println("Map created with " + wid * hei + " tiles.");
		CreateMap();
	}

	public void CreateMap() {
		double[][] result = new double[wid][hei];
		n = rand.nextInt(4000);
		simplex = new SimplexNoise(1000, 0.1, n);
		for (int x = 0; x < wid; x++) {
			for (int y = 0; y < hei; y++) {
				tiles[x][y] = new Tile(this, x, y);

				int i = (int) (x * wid);
				int j = (int) (y * hei);
				result[x][y] = 0.5 * (1 + simplex.getNoise(i, j));
				if (result[x][y] <= .48) {
					tiles[x][y].SetType(Tile.TileType.Water);
				}

				else if (result[x][y] > .48 && result[x][y] <= .51) {
					tiles[x][y].SetType(Tile.TileType.Dirt);
				}

				else if (result[x][y] > .51) {
					tiles[x][y].SetType(Tile.TileType.Grass);
				}
			}
		}
	}

	public void CreateMap2() {
		for (int x = 0; x < wid; x++) {
			for (int y = 0; y < hei; y++) {
				tiles[x][y] = new Tile(this, x, y);
				tiles[x][y].SetName("Tile " + startNum);
				startNum += 1;

				n = rand.nextInt(4);

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
