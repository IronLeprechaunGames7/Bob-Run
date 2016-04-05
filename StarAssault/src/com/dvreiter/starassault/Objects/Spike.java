package com.dvreiter.starassault.Objects;

import org.flixel.*;
import com.dvreiter.starassault.Objects.*;
import com.dvreiter.starassault.Player.*;

public class Spike extends FlxSprite
{

	public Spike(int x, int y, int Angle)
	{
		super(x, y);
     //   this.x = x;
     //   this.y = y;
		//	acceleration.y = 500;
		loadGraphic("spikes.png", true, true, 16, 16);
		width = 12;
		height = 12;
	//	centerOffsets();
		
		angle = Angle;
		
		immovable = true;

	}
	@Override 
	public void destroy()
	{
		super.destroy();

	}

	@Override 
	public void update()
	{
		super.update();
	}

}
