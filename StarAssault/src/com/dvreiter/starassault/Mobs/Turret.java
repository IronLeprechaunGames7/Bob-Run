package com.dvreiter.starassault.Mobs;

import org.flixel.*;
import com.dvreiter.starassault.Objects.*;
import com.dvreiter.starassault.Player.*;

public class Turret extends FlxSprite
{
	//protected Player _player;
	private FlxGroup _tbullets;
	private int _aim;
	
	private float _shotClock;

	public Turret(int x, int y, int width, int height,FlxGroup TurretBullets)
	{
		super(width, height);
        this.x = x;
        this.y = y;
	//	acceleration.y = 500;
		loadGraphic("terminator.png", true, true, 16, 16);
		width = 12;
		height = 12;
		centerOffsets();
		
		setSolid(false);

		_tbullets = TurretBullets;
		
		_shotClock = 0;
		
	 
	}

	@Override 
	public void destroy()
	{
		super.destroy();
		_tbullets = null;

	}

	@Override 
	public void update()
	{

		TurretBullet b = (TurretBullet) _tbullets.recycle(TurretBullet.class);
		b.shoot(getMidpoint(_point),_aim);
	//	}
		super.update();
	}
}
