package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.mygdx.game.Mainchar;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Pixmap;
import com.mygdx.game.lasers;
import com.mygdx.game.Enemy;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class Finalproject extends ApplicationAdapter {

 private static final int FRAME_COLS = 2, FRAME_ROWS = 1;

    // Objects used
	BitmapFont font;
	BitmapFont lives;
    Texture Sheet;
    SpriteBatch spriteBatch;
    SpriteBatch laser;
    SpriteBatch enemy1;
    boolean alreadyPlayed = false;
    int level = 1;
    Texture backgroundTexture;
    Mainchar me = new Mainchar();
    OrthographicCamera camera;
    Array<lasers> mainLasers;
    // A variable for tracking elapsed time for the animation
    float stateTime;
    float enemyTime;
    boolean shoot;
    int random;
    lasers main = new lasers();
    Enemy one = new Enemy();
    Array<Enemy> enemyArray;
    Array<Rectangle> enemyHitbox;
    boolean beenHit = false;


    @Override
    public void create() {
    font = new BitmapFont();
    lives = new BitmapFont();
    enemyArray = new Array<Enemy>();
	camera = new OrthographicCamera();
	camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()/2);
	// Load the sprite sheet as a Texture
	Sheet = new Texture(Gdx.files.internal("spritesheet.png"));
	mainLasers = new Array<lasers>();
	backgroundTexture = new Texture (Gdx.files.internal("background.png"));


	// Use the split utility method to create a 2D array of TextureRegions. This is 
	// possible because this sprite sheet contains frames of equal size and they are 
	// all aligned.
	TextureRegion[][] tmp = TextureRegion.split(Sheet, 
		Sheet.getWidth() / FRAME_COLS,
		Sheet.getHeight() / FRAME_ROWS);

	// Place the regions into a 1D array in the correct order, starting from the top 
	// left, going across first. The Animation constructor requires a 1D array.
	TextureRegion[] flying = new TextureRegion[FRAME_COLS * FRAME_ROWS];
	int index = 0;
	for (int i = 0; i < FRAME_ROWS; i++) {
	    for (int j = 0; j < FRAME_COLS; j++) {
		flying[index++] = tmp[i][j];
	    }
	}
	
		
	// Initialize the Animation with the frame interval and array of frames
	me.ship = new Animation(0.1f, flying);
	
	// Instantiate a SpriteBatch for drawing and reset the elapsed animation
	// time to 0
	spriteBatch = new SpriteBatch();
	laser = new SpriteBatch();
	enemy1 = new SpriteBatch();
	stateTime = 0f;
	enemyTime = 0f;
    }

    @Override 
    public void render() {
	camera.update();

	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Clear screen
	if (level == 1)
	{
		if (alreadyPlayed == false)
		{
			for (int y = 1; y < 5; y++)
			{
				for (int x = 0; x < 5; x++)
				{
					enemyArray.add(new Enemy());
					switch (y)
					{
						case 1:
						enemyArray.get(x).x = 120 + (50*x);
						enemyArray.get(x).y = 300;
						enemyArray.get(x).originalPositionX = 120 + (50*x);
						enemyArray.get(x).originalPositionY = 300;
						break;
						case 2:
						enemyArray.get(x+5).x = 120 + (50*x);
						enemyArray.get(x+5).y = 260;
						enemyArray.get(x).originalPositionX = 120 + (50*x);
						enemyArray.get(x).originalPositionY = 260;
						break;
						case 3:
						enemyArray.get(x+10).x = 120 + (50*x);
						enemyArray.get(x+10).y = 220;
						enemyArray.get(x).originalPositionX = 120 + (50*x);
						enemyArray.get(x).originalPositionY = 220;
						break;
						case 4:
						enemyArray.get(x+15).x = 120 + (50*x);
						enemyArray.get(x+15).y = 180;
						enemyArray.get(x).originalPositionX = 120 + (50*x);
						enemyArray.get(x).originalPositionY = 180;
						break;
					}
				}
			}
			alreadyPlayed = true;
		}	
	}
	for (int x = 0; x < enemyArray.size; x++)
	{
		enemyArray.get(x).laser = new Texture (Gdx.files.internal("evil laser.png"));
		enemyArray.get(x).Sheet = new Texture (Gdx.files.internal("enemysheet.png"));
		TextureRegion[][] meep = TextureRegion.split(enemyArray.get(x).Sheet, 
				enemyArray.get(x).Sheet.getWidth() / FRAME_COLS,
				enemyArray.get(x).Sheet.getHeight() / FRAME_ROWS);
			TextureRegion[] flyingenemy = new TextureRegion[2 * 1];
		    int enemyindex = 0;
		    for (int i = 0; i < FRAME_ROWS; i++) 
		    {
		    	for (int j = 0; j < FRAME_COLS; j++) 
		    	{
		    		flyingenemy[enemyindex++] = meep[i][j];
		    	}
		    }
	    enemyArray.get(x).ship1 = new Animation(0.1f, flyingenemy);
	}
	
	if (Gdx.input.isKeyPressed(Keys.A))
	{
		me.shipX -= Gdx.graphics.getDeltaTime()* me.speed;

	}
	if (Gdx.input.isKeyPressed(Keys.D))
	{
		me.shipX += Gdx.graphics.getDeltaTime()* me.speed;

	}
	if (Gdx.input.isKeyPressed(Keys.W))
	{
		me.shipY += Gdx.graphics.getDeltaTime()* me.speed;

	}
	if (Gdx.input.isKeyPressed(Keys.S))
	{
		me.shipY -= Gdx.graphics.getDeltaTime()* me.speed;

	}
	for (int o = 0; o < enemyArray.size; o++)
	{
		enemyArray.get(o).hitbox = (new Rectangle(enemyArray.get(o).x-7, enemyArray.get(o).y, 20, 40));
	}
	//
	if (me.shipY < 0) 
	{
		me.shipY = 0;
	}
	if (me.shipY > 288)
	{
		me.shipY = 288;
	}
	if (me.shipX > 465)
	{
		me.shipX = 465;
	}
	if (me.shipX < -5) 
	{
		me.shipX = -5;
	}
	//
	if (Gdx.input.isKeyJustPressed(Keys.SPACE))
	{
		mainLasers.add(new lasers());
		mainLasers.get(mainLasers.size -1).x = me.shipX + 9;
	    mainLasers.get(mainLasers.size -1).y = me.shipY + 36;
	    mainLasers.get(mainLasers.size -1).shoot = true;
	    mainLasers.get(mainLasers.size -1).sprite = new Texture (Gdx.files.internal("base laser.png"));
	}
	me.hitbox = (new Rectangle(me.shipX, me.shipY, 12, 40));

	// Get current frame of animation for the current stateTime                
	TextureRegion currentFrame = me.ship.getKeyFrame(stateTime, true);
	spriteBatch.begin();
	spriteBatch.draw(backgroundTexture, 0, 0);
	font.draw(spriteBatch, "Score: " + me.score, 420, 320);
	lives.draw(spriteBatch, "Lives: " + me.lives, 420, 20);
	spriteBatch.draw(currentFrame, me.shipX, me.shipY);
	spriteBatch.end();
	enemy1.begin();
	for (int x = 0; x < enemyArray.size; x++)
	{
		enemy1.draw(enemyArray.get(x).ship1.getKeyFrame(enemyTime, true), enemyArray.get(x).x, enemyArray.get(x).y);
	}
	enemy1.end();
	for (int a = 0; a <= mainLasers.size-1; a++)
	{

		if (mainLasers.get(a).shoot == true)
		{

			if (mainLasers.get(a).y < 333)
			{ 
				mainLasers.get(a).y += 4;
				laser.begin();
				laser.draw(mainLasers.get(a).sprite, mainLasers.get(a).x, mainLasers.get(a).y);
				laser.end();	
				mainLasers.get(a).hitbox = new Rectangle(mainLasers.get(a).x-20, mainLasers.get(a).y-5,30,11);
				int temp = 0;
				int temp2 = 0;
				boolean loop = false;
				for (int y = 0; y <= enemyArray.size-1; y++)
				{
					if (mainLasers.get(a).hitbox.overlaps(enemyArray.get(y).hitbox))
					{
						enemyArray.get(y).loseHealth(mainLasers.get(a).damage);
						mainLasers.get(a).shoot = false;    
						temp2 = a;
						if (enemyArray.get(y).health == 0)
						{
							loop = true;
							temp = y;
							me.score += enemyArray.get(y).score;
						}
					}
				}
				if (loop == true)
				{
					enemyArray.removeIndex(temp);
					mainLasers.removeIndex(temp2);
				}
			}
			else
			{
				mainLasers.get(a).shoot = false;    
				mainLasers.removeIndex(a);
			}
		}
	} 
	int timer = (int) enemyTime; 
	int temper = timer + 2;
	if (timer % 5 == 0 && beenHit == true)
	{
		beenHit = false;
	}
	for (int x=0; x<enemyArray.size;x++)
	{
		if (me.hitbox.overlaps(enemyArray.get(x).hitbox) && beenHit == false)
		{
			me.loseHealth(enemyArray.get(x).damage);
			beenHit = true;
		}
	}
	if (me.health <= 0)
	{
		me.lives -= 1;
		me.health = 100;
	}
	/*
	int timer = (int) enemyTime; 
	if (timer %5 != 0 && enemyArray.size > 0)
	{
		enemyArray.get(random).follow(me.shipX, me.shipY);
	}
	else if (timer % 5 == 0 && enemyArray.size > 0)
	{
		enemyArray.get(random).returnToOG();
		random = (int)(Math.random() * enemyArray.size -1);
	}
	*/



	stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time
	enemyTime += Gdx.graphics.getDeltaTime();
    } 

    @Override
    public void dispose() { // SpriteBatches and Textures must always be disposed
	spriteBatch.dispose(); 
	Sheet.dispose();
	laser.dispose();
	enemy1.dispose();
    }
}

