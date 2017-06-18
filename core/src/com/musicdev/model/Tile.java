package com.musicdev.model;

import com.badlogic.gdx.graphics.Texture;

public class Tile {
	Texture img;
	Texture installImg;
	int TileID;
	int InstallID;

	String name = "Tile";

	public enum TileType {
		None, Water, Dirt, Grass, Stone
	}

	public enum Installed {
		None, Wall1, Doormat, Installing
	}

	public boolean hasObject = false;
	public boolean installing = false;

	TileType type = TileType.None;
	public Installed object = Installed.None;
	Installed nextObject = Installed.None;

	LockedObject lockedObject;
	FreeObject freeObject;

	World world;
	int x;
	int y;

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

	public void PreInstall(Installed object) {
		this.object = null;
		this.hasObject = true;
		this.installing = true;
		this.nextObject = object;
		this.installImg = new Texture("installing.png");

	}

	public void Install(Installed object) {
		this.object = object;
		this.installImg = new Texture(this.object.toString().toLowerCase() + ".png");
		this.InstallID = Installed.valueOf(this.object.toString()).ordinal();
		this.installing = false;

	}

	public void EmptyTile() {
		this.object = Installed.None;
		this.InstallID = Installed.valueOf(this.object.toString()).ordinal();
		this.installing = false;
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
