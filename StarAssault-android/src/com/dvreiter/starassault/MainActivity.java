package com.dvreiter.starassault;

import org.flixel.FlxAndroidApplication;
import com.dvreiter.starassault.LevelLoader.*;

public class MainActivity extends FlxAndroidApplication 
{
    public MainActivity()
	{
 		super(new StarAssaultGame());
	}
}
