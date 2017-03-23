package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.Texture;

public class Mainchar {
	public int health = 100;
	public int score = 0;
	public int damage;
	public Animation ship; 
	public int shipX;
	public int shipY;
	public float speed = 200.0f;
	public Texture laser;
	public Rectangle hitbox;
	public int lives = 3;
	
	public int checkHealth () {
		return health;
	}
	public int gainHealth (int health, int heal) {
		health += heal;
		return health;
	}
	public int loseHealth (int enemyDamage) {
		health -= enemyDamage;
		return health;
	}
}
