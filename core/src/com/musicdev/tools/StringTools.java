package com.musicdev.tools;

public class StringTools {

	public static String toTitleCase(String string) {
		StringBuilder titleCased = new StringBuilder();
		boolean nextTitleCase = true;

		for (char ch : string.toCharArray()) {
			if (Character.isSpaceChar(ch)) {
				nextTitleCase = true;
			} else if (nextTitleCase) {
				ch = Character.toTitleCase(ch);
				nextTitleCase = false;
			}
			titleCased.append(ch);
		}

		return titleCased.toString();
	}

}
