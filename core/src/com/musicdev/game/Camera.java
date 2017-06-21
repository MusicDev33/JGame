package com.musicdev.game;

import com.badlogic.gdx.graphics.OrthographicCamera;

public class Camera {

	public OrthographicCamera camera = new OrthographicCamera();

	public int correctionX = 0;
	public int correctionY = 0;

	float screenX;
	float screenY;

	public Camera(float x, float y) {
		screenX = x;
		screenY = y;
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
