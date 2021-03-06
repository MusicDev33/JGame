package com.musicdev.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import com.musicdev.entities.Faction;
import com.musicdev.game.Save;
import com.musicdev.mapgen.SimplexNoise;
import com.musicdev.model.Tile.Installed;
import com.musicdev.model.Tile.TileType;

public class World {
	Save save;
	Faction faction;

	Tile[][] tiles;
	int wid;
	int hei;

	SimplexNoise simplex;

	int startNum;

	Random rand = new Random();
	int n;
	int seed;

	public World(int wid, int hei, boolean load) {
		this.wid = wid;
		this.hei = hei;

		tiles = new Tile[wid][hei];

		try {
			if (load) {
				System.out.println("Map loaded with " + wid * hei + " tiles.");
				LoadMap2();
			} else {
				System.out.println("Map created with " + wid * hei + " tiles.");
				CreateMap();
				System.out.println("Seed used: " + this.seed);

			}
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public void CreateMap() throws IOException {
		double[][] result = new double[wid][hei];
		this.seed = rand.nextInt(999999999);

		simplex = new SimplexNoise(2800, 0.18, this.seed);
		for (int x = 0; x < wid; x++) {
			for (int y = 0; y < hei; y++) {
				tiles[x][y] = new Tile(this, x, y);
				tiles[x][y].SetName("Tile " + startNum);
				startNum += 1;
				tiles[x][y].EmptyTile();
				tiles[x][y].preinstall = false;
				tiles[x][y].hasObject = false;
				tiles[x][y].solid = false;
				tiles[x][y].SetPathfind(false);

				int i = (int) (x * wid);
				int j = (int) (y * hei);
				result[x][y] = 0.5 * (1 + simplex.getNoise(i, j));
				if (result[x][y] <= .47) {
					tiles[x][y].SetType(Tile.TileType.Water);

				}

				else if (result[x][y] > .47 && result[x][y] <= .51) {
					tiles[x][y].SetType(Tile.TileType.Dirt);

				}

				else if (result[x][y] > .51) {
					tiles[x][y].SetType(Tile.TileType.Grass);

				}
			}
		}
		faction = new Faction();

	}

	public void LoadMap(int seed) {
		double[][] result = new double[wid][hei];
		this.seed = seed;

		simplex = new SimplexNoise(1000, 0.1, this.seed);
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

	public void LoadMap2() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/saves/save.txt"));
		String line = br.readLine();
		int charNum = 0;

		for (int x = 0; x < this.wid; x++) {
			for (int y = 0; y < this.hei; y++) {
				tiles[x][y] = new Tile(this, x, y);
				tiles[x][y].SetName("Tile " + startNum);
				startNum += 1;
				int n = Character.getNumericValue(line.charAt(charNum));

				tiles[x][y].SetType(TileType.values()[n]);

				charNum += 1;
			}
		}

		br.close();

		br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/saves/saveInstall.txt"));
		String newline = br.readLine();
		charNum = 0;
		for (int x = 0; x < this.wid; x++) {
			for (int y = 0; y < this.hei; y++) {

				int n = Character.getNumericValue(newline.charAt(charNum));
				if (n == 0) {
					tiles[x][y].EmptyTile();
				} else {
					System.out.println("Installed");
					tiles[x][y].PreInstall(Installed.values()[n]);
					tiles[x][y].Install(Installed.values()[n]);
					System.out.println(tiles[x][y].GetObject());
				}

				charNum += 1;
			}
		}

	}

	public int TryLoad() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/saves/save.txt"));
		String line = br.readLine();

		if (line == null) {
			br.close();
			return 0;
		} else {
			br.close();
			return 1;
		}
	}

	public static int StaticTryLoad() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/saves/save.txt"));
		String line = br.readLine();

		if (line == null) {
			br.close();
			return 0;
		} else {
			br.close();
			return 1;
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

	public int getSeed() {
		return this.seed;
	}

}
