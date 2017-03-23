package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;


public class Enemy {
	public int health = 100;
	public int score = 200;
	public int damage = 50;
	public Animation ship1; 
	public Texture Sheet;
	public Texture laser;
	public float x;
	public float y;
	public float originalPositionX;
	public float originalPositionY;
	public Rectangle hitbox;
	public boolean defeated;
	public void loseHealth (int damage) 
	{
    	health -= damage;
	}
	public void follow(int charX, int charY)
	{
		if (charX > x)
		{
			x += 3;
		}
		else if (charX < x)
		{
			x -= 3;
		}
		else
		{
		}
		if (charY < y)
		{
			y -= 3;
		}
		else if (charY > y)
		{
			y+= 3;
		}
		else
		{
		}
		if (charY + 50 > y)
		{
			y = charY + 50;
		}
	}
	public void returnToOG()
	{
		if (originalPositionX > x)
		{
			x += 3;
		}
		else if (originalPositionX< x)
		{
			x -= 3;
		}
		else
		{
		}
		if (originalPositionY < y)
		{
			y -= 3;
		}
		else if (originalPositionY > y)
		{
			y+= 3;
		}
		else
		{
		}
	}
	
}
