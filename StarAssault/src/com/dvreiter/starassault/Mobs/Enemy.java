package com.dvreiter.starassault.Mobs;

import org.flixel.*;
import org.flixel.event.*;
import com.dvreiter.starassault.Objects.*;
import com.badlogic.gdx.graphics.g2d.*;

public class Enemy extends FlxSprite {
    boolean isFollowingSprite;
	FlxSprite bulletToAvoid;
    FlxSprite spriteToFollow;
    FlxSprite kills[];
    FlxSprite getsKilledBy[];
    int _jumpPower;
	boolean isUpsideDown;
	
	
    @Override
    public Enemy(int x, int y, int width, int height, int accel) {
        super(width, height);
        this.x = x;
        this.y = y;
		acceleration.y = accel;
        int runSpeed = 80;
        _jumpPower = 200;
        maxVelocity.y = _jumpPower;
        drag.x = runSpeed*8;
        maxVelocity.x = runSpeed;
 //       acceleration.y = 500;
        loadGraphic("terminator.png", true, true, 16, 16);      
		health = 3;
        isFollowingSprite = false;
    }
    @Override
    public void update() {
        acceleration.x = 0;
        super.update();
        
		if(isFollowingSprite && spriteToFollow != null) {
            if(spriteToFollow.x != x) {
                if(isTouching(FlxObject.WALL))jump();
                if(spriteToFollow.x > x ) {
                    //if(isTouching(FlxObject.FLOOR))
                    goRight();
                } else if(spriteToFollow.x < x) {
                    //if(isTouching(FlxObject.FLOOR))
                    goLeft();
                }
            } else {
                stop();
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
		
    public void followSprite(FlxSprite player) {
        spriteToFollow = player;
        isFollowingSprite = true;	
    }
    public void goLeft() {
        acceleration.x -= drag.x;
    }
    public void goRight() {
        acceleration.x += drag.x;
    }
    public void stop() {
        acceleration.x = 0;
    }
    public void jump() {
        if(isTouching(FlxObject.FLOOR))
			velocity.y = - maxVelocity.y/1.2f;
		else if(isTouching(FlxObject.CEILING))
			velocity.y = + maxVelocity.y/1.2f;
    }
}
