package com.dvreiter.starassault.LevelLoader;

import org.flixel.FlxGame;
import org.flixel.FlxCamera;
import org.flixel.FlxG;
import com.dvreiter.starassault.*;
import com.dvreiter.starassault.Menu.*;
import com.dvreiter.starassault.Levels.*;

public class StarAssaultGame extends FlxGame
{
	public StarAssaultGame()
	{
		super(400, 240,MenuState.class, 2, 50, 50, false);	
		//400,240
		/*forceDebugger = true;
		FlxG.debug = true;*/
	}
}
