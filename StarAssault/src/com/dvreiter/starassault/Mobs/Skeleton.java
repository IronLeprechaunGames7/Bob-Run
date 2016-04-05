package com.dvreiter.starassault.Mobs;

import org.flixel.*;
import org.flixel.event.*;
import com.badlogic.gdx.utils.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;

public class Skeleton extends FlxSprite {
    FlxSprite kills[];
    FlxSprite getsKilledBy[];
	FlxSprite player;
    int _jumpPower;
	FlxPath path;
	FlxPoint destination, destination2;

    @Override
    public Skeleton(int x, int y, int width, int height) {
        super(width, height);
        this.x = x;
        this.y = y;
		
        int runSpeed = 80;
        _jumpPower = 200;
        maxVelocity.y = _jumpPower;
        drag.x = runSpeed*4;
        maxVelocity.x = runSpeed;
        acceleration.y = 500;
        loadGraphic("skeletonminion.png", true, true, 16, 16); 
		addAnimation("idle_stand", new int[]{2}, 0, false);
		addAnimation("idle_walk", new int[]{2,1,0}, 4, true);
		addAnimation("idle_rwalk", new int[]{5,4,3}, 4, true);
		
		offset.x = 1;
		
		play("idle_rwalk");
	//	x-=10;
	
	health=3;

	/*	destination = getMidpoint();
		destination.x += 130;
		Array<FlxPoint> points = new Array<FlxPoint>();
		points.addAll(new FlxPoint[]{getMidpoint(),destination});
		path = new FlxPath(points);
		followPath(path,80,FlxObject.PATH_YOYO);
		*/
    }
    @Override
    public void update() {
       acceleration.x = 0;
	    super.update();
		
		acceleration.x += 1;
		
		if(x == 767){
			stop();
			x = 766;
		}
		//if(isTouching(FlxObject.WALL)){
			if(x == 766){
				goLeft();
		}
	//	}
		
		//if(isTouching(FlxObject.WALL)){
			if(x == 625){
				goRight();
			}
		//}
	}
	
	@Override public void hurt(float Damage)
	{
		flicker(0.6f);//0.2f
		FlxG.score += 10;
		super.hurt(Damage);
	}

    public void goLeft() {
      acceleration.x -= drag.x;
	//   x -= 4;
	//	setFacing(LEFT);
		play("idle_walk");
    }
    public void goRight() {
       acceleration.x += drag.x;
	//   x += 4;
		//setFacing(RIGHT);
		play("idle_rwalk");
    }
    public void stop() {
        acceleration.x = 0;
		play("idle_stand");
    }
    public void jump() {
        if(isTouching(FlxObject.FLOOR))
			velocity.y = - maxVelocity.y/1.2f;
    }
}
