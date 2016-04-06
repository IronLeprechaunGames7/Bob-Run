package com.dvreiter.starassault.Menu;

import org.flixel.FlxButton;
import org.flixel.FlxG;
import org.flixel.FlxState;
import org.flixel.FlxText;
import org.flixel.event.IFlxButton;
import org.flixel.*;
import org.flixel.ui.*;
import org.flixel.event.*;
import com.dvreiter.starassault.Levels.*;
import com.dvreiter.starassault.*;

public class LevelsState extends FlxState
{		
	private FlxButton Level1,Level2,Level3,Level4,Level5,Level6;
	
	private FlxText Title;
	private FlxText gameversion;

	public FlxSave gameSave;
	
	private FlxText title1,title2;

	@Override
	public void create()
	{			
	
		//saving stuff
		gameSave = new FlxSave();
		gameSave.bind("Test");
		
		//load
		@SuppressWarnings("unchecked")
		int progress = gameSave.data.get("Progress", int.class);
		if(progress == 0)
		{
			
			
		}else
		{}		
		FlxG.playMusic("Pneumatic-Tok.mp3", 1f, true, true);
	
		FlxG.setBgColor(0x0d47e1);

		Level1 = new FlxButton(10, 50, "Level1", new IFlxButton(){@Override public void callback(){onLevel1();}});
		add(Level1);	
		if(progress > 2)
		{
		Level2 = new FlxButton(10, Level1.height + Level1.y, "Level2", new IFlxButton(){@Override public void callback(){onLevel2();}});
		add(Level2);
		}
		if(progress > 3)
		{
		Level3 = new FlxButton(10, Level2.height + Level2.y, "Level3", new IFlxButton(){@Override public void callback(){onLevel3();}});//Modified
		add(Level3);
		}
		if(progress > 4)
		{
		Level4 = new FlxButton(10, Level3.height + Level3.y, "Level4", new IFlxButton(){@Override public void callback(){onLevel4();}});
		add(Level4);
		}
		if(progress > 5)
		{
		Level5 = new FlxButton(10, Level4.height + Level4.y, "Level5", new IFlxButton(){@Override public void callback(){onLevel5();}});
		add(Level5);
		}
		if(progress > 6)
		{
		Level6 = new FlxButton(10, Level5.height + Level5.y, "Level6", new IFlxButton(){@Override public void callback(){onLevel6();}});
		add(Level6);
		}
		
		//the letters "mo"
		title1 = new FlxText(FlxG.width + 12,FlxG.height / 20,120,"Level");
		title1.setSize(32);
		title1.antialiasing = true;
		title1.velocity.x = -FlxG.width;
		title1.angularAcceleration = 800;
		title1.angularVelocity = 400;
		title1.moves = true;
		add(title1);

		//the letters "de"
		title2 = new FlxText(-55,title1.y,120,"Menu");
		title2.setSize(32);
		//	title2.setColor(title1.getColor());
		title2.antialiasing = title1.antialiasing;
		title2.velocity.x = FlxG.width;
		title2.angularAcceleration = 800;
		title2.angularVelocity = 400;
		title2.moves = true;
		add(title2);


		
	}
    @Override
	public void update(){	

		super.update();


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

	private void onLevel1(){
		FlxG.fade(0xff000000,1,onFade);
	}
	private void onLevel2(){
		FlxG.fade(0xff000000,1,onFade2);
	}

	private void onLevel3(){
		FlxG.fade(0xff000000,1,onFade3);
	}
	private void onLevel4(){
		FlxG.fade(0xff000000,1,onFade4);
	}
	private void onLevel5(){
		FlxG.fade(0xff000000,1,onFade5);
	}
	private void onLevel6(){
		FlxG.fade(0xff000000,1,onFade6);
	}
	/*private void OnPress(){
	 FlxU.openURL("http://www.newgrounds.com/audio/listen/516336");
	 }*/

	public IFlxCamera onFade = new IFlxCamera()
	{
		@Override
		public void callback()
		{
			FlxG.switchState(new PlayState());
		}

	};
	
	public IFlxCamera onFade2 = new IFlxCamera()
	{
		@Override
		public void callback()
		{
			FlxG.switchState(new PlaystateTwo());
		}

	};
	
	public IFlxCamera onFade3 = new IFlxCamera()
	{
		@Override
		public void callback()
		{
			FlxG.switchState(new PlaystateThree());
		}

	};
	public IFlxCamera onFade4 = new IFlxCamera()
	{
		@Override
		public void callback()
		{
			FlxG.switchState(new PlayState4());
		}

	};
	public IFlxCamera onFade5 = new IFlxCamera()
	{
		@Override
		public void callback()
		{
			FlxG.switchState(new PlayState5());
		}

	};
	public IFlxCamera onFade6 = new IFlxCamera()
	{
		@Override
		public void callback()
		{
			FlxG.switchState(new PlayState6());
		}

	};


}
