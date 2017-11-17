package com.musicdev.model;

import com.badlogic.gdx.graphics.Texture;

public class Tile {
	Texture img;
	public boolean preinstall;
	Texture installImg;
	int TileID;
	int InstallID;

	public boolean solid;

	boolean pathFound;

	String name = "Tile";

	public enum TileType {
		None, Water, Dirt, Grass, Stone
	}

	public enum Installed {
		None, Wall1, Doormat, WoodTile
	}

	public enum SolidTiles {
		Wall1
	}

	public boolean hasObject = false;
	public boolean installing = false;

	TileType type = TileType.None;
	public Installed object = Installed.None;
	public Installed nextObject = Installed.None;

	LockedObject lockedObject;
	FreeObject freeObject;

	World world;
	public int x;
	public int y;

	public Tile(World world, int x, int y) {
		this.world = world;
		this.x = x;
		this.y = y;

	}

	public void SetName(String name) {
		this.name = name;
	}

	public String GetName() {
		return name;
	}

	public void SetType(TileType type) {
		this.type = type;
		this.img = new Texture(this.type.toString().toLowerCase() + ".png");
		this.TileID = TileType.valueOf(this.type.toString()).ordinal();
	}

	public String GetType() {
		return this.type.toString();
	}

	public Texture GetImg() {
		return this.img;
	}

	public Texture GetInstalledImg() {
		return this.installImg;
	}

	public Texture GetPreinstallImg() {
		return new Texture("installing.png");
	}

	public void PreInstall(Installed object) {
		// I honestly have no clue why this line exists
		// the
		// way it does, but I can't change it to
		// Installed.None...

		this.installing = true;
		this.nextObject = object;
		this.preinstall = true;

	}

	public void Install(Installed object) {
		this.object = object;
		for (Tile.SolidTiles i : Tile.SolidTiles.values()) {
			if (i.name().equals(object.name())) {
				this.solid = true;
			}
		}

		System.out.println(this.solid);
		this.hasObject = true;
		this.installImg = new Texture(this.object.toString().toLowerCase() + ".png");
		this.InstallID = Installed.valueOf(this.object.toString()).ordinal();
		this.installing = false;
		this.preinstall = false;

	}

	// Wtf does this do? I don't remember...
	public void EmptyTile() {
		this.object = Installed.None;
		this.InstallID = Installed.valueOf(this.object.toString()).ordinal();
		this.installing = false;
	}

	public void SetPathfind(boolean found) {
		this.pathFound = found;
	}

	public int GetID() {
		return this.TileID;
	}

	public int GetInstallID() {
		return this.InstallID;
	}

	public String GetObject() {
		return this.object.toString();
	}

}
