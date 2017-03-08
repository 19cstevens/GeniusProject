package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class evilship1 {
	public int health = 100;
	public int score;
	public int damage;
	public Animation ship; 
	public float shipX;
	public float shipY;
	public float speed = 1000.0f;
	public Texture laser;
	
	public int checkHealth () {
		return health;
	}
	public int loseHealth (int health, int enemyDamage) {
		health -= enemyDamage;
		return health;
	}
	
	
}