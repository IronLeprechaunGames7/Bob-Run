package com.dvreiter.starassault.Mobs;

import org.flixel.*;
import org.flixel.event.*;


public class Crusher extends FlxSprite{
	boolean isWatchingSprite;
    boolean isFalling;
    FlxSprite spriteToCrush;
    FlxSprite kills[];
    FlxSprite getsKilledBy[];
	int startHeight;
	//boolean isUpsideDown;


    @Override
    public Crusher(int x, int y, int width, int height, int accel) {
        super(width, height);
		startHeight = y;
        this.x = x;
        this.y = y;
		acceleration.y = 0;
        maxVelocity.y = 150;
		//       acceleration.y = 500;
        loadGraphic("terminator.png", true, true, 16, 16);      
		health = 3;
        isWatchingSprite = false;
		isFalling = false;
    }
    @Override
    public void update() {
        acceleration.x = 0;
        super.update();
		if (isFalling){
			acceleration.y=200;
			if (isTouching(FlxObject.FLOOR)){
				isFalling = false;
				acceleration.y=0;
				FlxG.shake(0.035f,0.25f);
			}
		}else{
        	if(isWatchingSprite && spriteToCrush != null) {
          		if(spriteToCrush.x < x + 12 && spriteToCrush.x > x -12) {
					fall();
            	} else {
               		rise();
           		}
        	}
		}
	}

	@Override
	public void destroy()
	{
		// TODO: Implement this method
		super.destroy();

	}

	@Override public void hurt(float Damage)
	{
		flicker(0.6f);//0.2f
		FlxG.score += 10;
		super.hurt(Damage);
	}

    public void WatchSprite(FlxSprite player) {
        spriteToCrush = player;
        isWatchingSprite = true;
    }
    public void fall() {
        isFalling = true;
    }
	
