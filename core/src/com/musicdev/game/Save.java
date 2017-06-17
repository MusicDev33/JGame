package com.musicdev.game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import com.musicdev.model.World;

public class Save {
	World world;

	int seed = 0;
	String c;

	public Save() throws IOException {
		System.out.println(System.getProperty("user.dir"));
		boolean files = (new File(System.getProperty("user.dir") + "/saves")).mkdirs();
		boolean textFile = (new File(System.getProperty("user.dir") + "/saves/save.txt")).createNewFile();

		if (files && textFile) {
			System.out.println("Created save directory.");
		}
	}

	public void SaveMap() throws IOException {
		Writer writer = new FileWriter(System.getProperty("user.dir") + "/saves/save.txt");

		try {
			writer.write(Integer.toString(this.seed));
		} finally {
			writer.close();

		}

		BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/saves/save.txt"));

		try {

			String line;

			while ((line = br.readLine()) != null) {
				c = line;
				System.out.println(c);
			}
		} finally {

			br.close();

		}

	}

	public void SaveMapToText() throws IOException {
		Writer writer = new FileWriter(System.getProperty("user.dir") + "/saves/save.txt");
		for (int x = 0; x < this.world.Width(); x++) {
			for (int y = 0; y < this.world.Height(); y++) {
				writer.write(Integer.toString(this.world.GetTileAt(x, y).GetID()));
			}
		}
		writer.close();

	}

	public void setSaveSeed(int seed) {
		this.seed = seed;
	}

	public int getSeed() {
		return this.seed;
	}

	public void setWorld(World world) {
		this.world = world;
	}

}
