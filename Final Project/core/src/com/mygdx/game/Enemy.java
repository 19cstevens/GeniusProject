package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;


public class Enemy {
	public int health;
	public int damage;
	public Animation ship1; 
	public Texture Sheet;
	public Texture laser;
	public float x;
	public float y;
	public Rectangle hitbox;
	public void loseHealth (int damage) {
    	health -= damage;
	}
}
