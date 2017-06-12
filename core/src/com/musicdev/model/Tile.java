package com.musicdev.model;

import com.badlogic.gdx.graphics.Texture;

public class Tile {
	Texture img;
	Texture installImg;

	String name = "Tile";

	public enum TileType {
		None, Dirt, Grass, Stone, Water
	}

	public enum Installed {
		None, Wall1, Doormat
	}

	public boolean hasObject = false;

	TileType type = TileType.None;
	Installed object = Installed.None;

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

	public void Install(Installed object) {
		this.hasObject = true;
		this.object = object;
		this.installImg = new Texture(this.object.toString().toLowerCase() + ".png");

	}

}
