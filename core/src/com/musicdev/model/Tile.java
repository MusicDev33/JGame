package com.musicdev.model;

import com.badlogic.gdx.graphics.Texture;

public class Tile {
	Texture img;

	String name = "Tile";

	public enum TileType {
		None, Dirt, Grass, Stone, Water
	}

	TileType type = TileType.None;

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

	public Texture GetImg() {
		return this.img;
	}

}
