package com.musicdev.entities;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class Faction {

	String name;

	FileInputStream fs;
	BufferedReader br;
	ArrayList<String> adj = new ArrayList<>();
	String line;
	int randIndex;

	Random rand = new Random();

	public Faction() throws IOException {
		fs = new FileInputStream(System.getProperty("user.dir") + "/WordBank/adjectives.txt");
		br = new BufferedReader(new InputStreamReader(fs));

		while ((line = br.readLine()) != null) {
			adj.add(br.readLine());

		}
		randIndex = rand.nextInt(adj.size());
		System.out.println(adj.get(randIndex));

	}

	public String GenerateName() {
		return "name";
	}

}
