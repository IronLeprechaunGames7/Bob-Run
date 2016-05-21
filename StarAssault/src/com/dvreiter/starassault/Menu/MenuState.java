package com.dvreiter.starassault.Menu;

import com.badlogic.gdx.*;
import org.flixel.*;
import org.flixel.event.*;

public class MenuState extends FlxState
{		
	private FlxButton PlayButton;
    	private FlxButton HelpButton,HelpButton2;
	private FlxButton SettingsButton;
	private FlxButton musicauthorbutton;
	private FlxText Title;
	private FlxText gameversion;
	private FlxText author, title1,title2;

	@Override
	public void create()
	{			
		FlxG.setBgColor(0x0d47e1);
	
		PlayButton = new FlxButton(160, 110, "Play", new IFlxButton(){@Override public void callback(){onSave();}});
		add(PlayButton);	
		
		HelpButton = new FlxButton(160, 135, "Help", new IFlxButton(){@Override public void callback(){onHelp();}});
		add(HelpButton);
		
		SettingsButton = new FlxButton(160, 160, "Settings", new IFlxButton(){@Override public void callback(){onSettings();}});
		add(SettingsButton);
		
	/*	musicauthorbutton = new FlxButton(160, 18, "Music: Envy", new IFlxButton(){@Override public void callback(){OnPress();}});
		add(musicauthorbutton);		*/
		
	/*	Title = new FlxText(100, 50, 200, "Bob Run");
		Title.setSize(40);
		add(Title);	
	*/

		//the letters "mo"
		title1 = new FlxText(FlxG.width + 12,60,90,"Bob");
		title1.setSize(32);
	//	title1.setColor(0x3a5c39);
		title1.antialiasing = true;
		title1.velocity.x = -FlxG.width;
//		title1.angle = 360;
		title1.angularAcceleration = 800;
		title1.angularVelocity = 400;
		title1.moves = true;
		add(title1);

		//the letters "de"
		title2 = new FlxText(-70,title1.y,(int) title1.width,"Run");
		title2.setSize(32);
	//	title2.setColor(title1.getColor());
		title2.antialiasing = title1.antialiasing;
		title2.velocity.x = FlxG.width;
		title2.angularAcceleration = 800;
		title2.angularVelocity = 400;
		title2.moves = true;
		add(title2);
		Gdx.input.setCatchBackKey(true);
	
		gameversion = new FlxText(2, 225, 100, "v0.1.0 alpha");
		//gameversion.setSize(40);
		add(gameversion);	
		}
    @Override
	public void update(){	

		super.update();
		if(FlxG.keys.justPressed("BACK"))
		{
			
			Gdx.app.exit();
		}

		if(title2.x > title1.x + title1.width - 12)
		{
			title2.x = title1.x + title1.width - 12;
			title1.velocity.x = 0;
			title2.velocity.x = 0;
			title1.angularVelocity = 0;
			title1.angularAcceleration = 0;
			title2.angularAcceleration = 0;
			title2.angularVelocity = 0;
			title1.angle = 0;
			title2.angle = 0;

			FlxG.flash(0xffd8eba2,0.5f);
			FlxG.shake(0.035f,0.5f);
			title1.setColor(0xd8eba2);
			title2.setColor(0xd8eba2);
			}
		}

	private void onSave(){
		FlxG.fade(0xff000000,1,onFade);
	}
	private void onHelp(){
		FlxG.switchState(new LTPState());
	}
	
	private void onSettings(){
		FlxG.switchState(new Settings());
	}
	/*private void OnPress(){
		FlxU.openURL("http://www.newgrounds.com/audio/listen/516336");
	}*/

	public IFlxCamera onFade = new IFlxCamera()
	{
		@Override
		public void callback()
		{
			FlxG.switchState(new LevelsState());//changed this
		}

};


}
