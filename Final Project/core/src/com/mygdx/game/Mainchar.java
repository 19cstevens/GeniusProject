package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.Texture;

public class Mainchar {
	public int health = 100;
	public int score;
	public int damage;
	public Animation ship; 
	public int shipX;
	public int shipY;
	public float speed = 400.0f;
	public Texture laser;
	
	public int checkHealth () {
		return health;
	}
	public int gainHealth (int health, int heal) {
		health += heal;
		return health;
	}
	public int loseHealth (int health, int enemyDamage) {
		health -= enemyDamage;
		return health;
	}
}
