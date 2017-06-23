package com.musicdev.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class FontHandler {

	FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("arcadeclassic/ARCADECLASSIC.TTF"));
	FreeTypeFontParameter parameter = new FreeTypeFontParameter();

	public BitmapFont font24;
	public BitmapFont font32;
	public BitmapFont font64;
	public BitmapFont font90;

	public FontHandler() {

		parameter.size = 24;
		font24 = generator.generateFont(parameter);
		parameter.size = 32;
		font32 = generator.generateFont(parameter);
		parameter.size = 64;
		font64 = generator.generateFont(parameter);
		parameter.size = 90;
		font90 = generator.generateFont(parameter);

		generator.dispose();

	}

}
