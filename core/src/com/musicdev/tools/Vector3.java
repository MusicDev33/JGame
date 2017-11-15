package com.musicdev.tools;

public class Vector3 {
	int x, y, z;

	public Vector3(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public int getZ() {
		return this.z;
	}

	public String toString() {
		return Integer.toString(this.x) + "," + Integer.toString(this.y) + "," + Integer.toString(this.z);
	}

}
