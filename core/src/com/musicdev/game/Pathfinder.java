package com.musicdev.game;

import java.util.ArrayList;
import java.util.List;

import com.musicdev.model.Tile;
import com.musicdev.model.World;

public class Pathfinder {
	World world;
	List<Tile> frontier;

	public Pathfinder(int x1, int y1, int x2, int y2) {
		frontier = new ArrayList<Tile>();

		SetTile(x1, y1);

		while (!frontier.isEmpty()) {
			for (int i = 0; i < frontier.size(); i++) {
				System.out.println(i);
			}
		}

	}

	public void SetTile(int x, int y) {
		this.frontier.add(world.GetTileAt(x, y));
		world.GetTileAt(x, y).SetPathfind(true);
	}

	public void SetWorld(World world) {
		this.world = world;

	}

}
