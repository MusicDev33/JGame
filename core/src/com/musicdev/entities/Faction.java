package com.musicdev.entities;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

import com.musicdev.tools.StringTools;

public class Faction {

	String name;

	FileInputStream fs;
	BufferedReader br;
	ArrayList<String> adj = new ArrayList<>();
	ArrayList<String> noun = new ArrayList<>();
	ArrayList<String> faction = new ArrayList<>();
	String line;

	int randIndex1;
	int randIndex2;
	int randIndex3;

	String str1;
	String str2;
	String str3;

	Random rand = new Random();

	public Faction() throws IOException {
		this.name = this.GenerateName(20);

	}

	public String GenerateName(int amount) throws IOException {

		AdjectiveGen();
		NounGen();
		GroupGen();

		// Two of the randIndexes below don't have the -1 because it allows for
		// a noun or adjective to not exist, giving a smaller name. This
		// hopefully encourages a little more variety in the name as opposed to
		// strict 3 word faction names.

		randIndex1 = rand.nextInt(adj.size());
		randIndex2 = rand.nextInt(noun.size());
		randIndex3 = rand.nextInt(faction.size() - 1);

		if (adj.get(randIndex1) != null) {
			str1 = (StringTools.toTitleCase(adj.get(randIndex1)));
		} else {
			str1 = adj.get(randIndex1);
		}

		if (noun.get(randIndex2) != null) {
			str2 = (StringTools.toTitleCase(noun.get(randIndex2)));
		} else {
			str2 = noun.get(randIndex2);

		}

		str3 = (StringTools.toTitleCase(faction.get(randIndex3)));
		String factionNameUntrimmed = "The " + str1 + str2 + str3;
		String factionName = factionNameUntrimmed.trim().replaceAll(" +", " ");

		System.out.println(factionName);
		return factionName;
	}

	public ArrayList<String> AdjectiveGen() throws IOException {
		// The adjective
		fs = new FileInputStream(System.getProperty("user.dir") + "/WordBank/adjectives.txt");
		br = new BufferedReader(new InputStreamReader(fs));

		while ((line = br.readLine()) != null) {
			if (line == " ") {
				adj.add(br.readLine());
				// System.out.println("No adjective!\n");

			} else if (line == null) {
				adj.add(br.readLine());
				// System.out.println("No adjective!\n");
			}

			else {
				adj.add(br.readLine() + " ");
			}

		}
		return adj;

	}

	public ArrayList<String> NounGen() throws IOException {
		// The noun
		fs = new FileInputStream(System.getProperty("user.dir") + "/WordBank/nouns.txt");
		br = new BufferedReader(new InputStreamReader(fs));

		while ((line = br.readLine()) != null) {
			if (line == " ") {
				noun.add(br.readLine());
				// System.out.println("No noun!\n");

			} else if (line == "") {
				noun.add(br.readLine());
				// System.out.println("No noun!\n");
			}

			else {
				noun.add(br.readLine() + " ");
			}

		}
		return adj;
	}

	public ArrayList<String> GroupGen() throws IOException {
		// The faction name
		fs = new FileInputStream(System.getProperty("user.dir") + "/WordBank/factions.txt");
		br = new BufferedReader(new InputStreamReader(fs));

		while ((line = br.readLine()) != null) {
			faction.add(br.readLine());

		}
		return adj;

	}

}
