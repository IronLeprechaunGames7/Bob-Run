package com.dvreiter.starassault.Menu;

import org.flixel.FlxButton;
import org.flixel.FlxG;
import org.flixel.FlxState;
import org.flixel.FlxText;
import org.flixel.event.IFlxButton;
import org.flixel.*;
import org.flixel.ui.*;
import org.flixel.event.*;
import org.flixel.ui.FlxTab;
import org.flixel.ui.FlxTabGroup;
import org.flixel.ui.FlxUIGroup;
import org.flixel.ui.event.IFlxUIListener;

public class Settings extends FlxState
{		 
	private FlxText Title;
    private FlxButton backbtn;
	private FlxButton Linkbtn;
	private FlxButton designbtn;

	private Object onUp;
	
	public FlxState currentState;
	public FlxState PlaystateTwo;
	
	@Override
	public void create()
	{			
		FlxG.setBgColor(0xFF00CCFF);
// 5,5
		designbtn = new FlxButton(5, 5, "Level Design", new IFlxButton(){@Override public void callback(){onDesign();}});
		add(designbtn);	
		
		backbtn = new FlxButton(5, 215, "Back", new IFlxButton(){@Override public void callback(){onBack();}});
		add(backbtn);	

		// Add content
		final FlxSwitch _switch;
		add(_switch = new FlxSwitch(200, 100, "0", null, "Debug Mode"));
		
		_switch.onUp = new IFlxUIListener()
		{
			@Override
			public void callback()
			{
				if(_switch.getActivated())
				{
					FlxG.debug = true;
				}
				else
				{
					FlxG.debug = false;
				}
			}
		};
}

    @Override
	public void update(){	

		super.update();
	}

	private void onBack(){
		FlxG.switchState(new MenuState());
	}
	
	private void onDesign(){
		FlxG.switchState(new PlayStateLESettings());
	}
	
}
