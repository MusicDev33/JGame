package com.musicdev.game;

import com.badlogic.gdx.graphics.OrthographicCamera;

public class Camera {

	OrthographicCamera camera = new OrthographicCamera();

	int correctionX = 0;
	int correctionY = 0;

	public Camera(float x, float y) {
		camera.setToOrtho(false, x, y);
		camera.position.set(x / 2, y / 2, 0);
	}

	public void move(float x, float y, float dTime) {
		camera.position.x += x * dTime;
		camera.position.y += y * dTime;
		correctionX += x * dTime;
		correctionY += y * dTime;
	}

}
