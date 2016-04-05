package com.dvreiter.starassault.Menu;	

import org.flixel.FlxButton;
import org.flixel.FlxG;
import org.flixel.FlxObject;
import org.flixel.FlxSprite; 
import org.flixel.FlxState;
import org.flixel.FlxText;
import org.flixel.FlxTileblock;
import org.flixel.FlxTilemap;
import org.flixel.event.IFlxCollision;
import org.flixel.*;
import com.badlogic.gdx.utils.IntArray;
import org.flixel.ui.*;
import org.flixel.event.*;
import org.apache.http.message.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.dvreiter.starassault.Player.*;
import com.dvreiter.starassault.Mobs.*;
import com.dvreiter.starassault.Levels.*;
import com.dvreiter.starassault.*;
import org.flixel.ui.event.*;
import android.view.SurfaceHolder.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;

public class PlayStateFive extends FlxState
{	
	private static final int TILE_WIDTH = 16;
	private static final int TILE_HEIGHT = 16;

	private FlxObject highlightbox;
	 private int action;
	 private static final int ACTION_IDLE = 0;
	private FlxButton backbtn;

	public FlxButton pause;
	public FlxTilemap level;
	public FlxTileblock block;
	
	public static final String FntRobotoRegular = "Roboto-Regular.ttf";
	 
	protected FlxEmitter _littleGibs;

	FlxVirtualPad pad;
	Player player;

	FlxGroup _bullets;
	
	@Override
	public void create()
	{						
		FlxG.setBgColor(0xFF00CCFF);
		FlxG.mouse.show();

		action = ACTION_IDLE;
		
		int[] data={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,			
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,                                                  
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, 
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,//Middle or half of y of the level map.
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,			
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,                                                  
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, 
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			};
		
		level = new FlxTilemap();
		level.loadMap(FlxTilemap.arrayToCSV(new IntArray(data), 50), "tilemap.png", 16, 16); 
		add(level);

		FlxDialogBox blocktype = new FlxDialogBox(0,0);
		blocktype.textfield.setFormat(FntRobotoRegular,18);
		add(blocktype);
		
		_littleGibs = new FlxEmitter();
		_littleGibs.setXSpeed(-150,150);
		_littleGibs.setYSpeed(-200,0);
		_littleGibs.setRotation(-720,-720);
		_littleGibs.gravity = 350;
		_littleGibs.bounce = 0.5f;
		_littleGibs.makeParticles("gibs.png",100,10,true,0.5f);
		
		backbtn = new FlxButton(310, 5, "Back", new IFlxButton(){@Override public void callback(){onBack();}});
		backbtn.scrollFactor.x = 0;
		backbtn.scrollFactor.y = 0;
		add(backbtn);	
		
		highlightbox = new FlxObject(0,0,TILE_WIDTH, TILE_HEIGHT);
		// 400x240						
 		FlxG.playMusic("Pneumatic-Tok.mp3", 1f, true, true);

 		pad = new FlxVirtualPad(FlxVirtualPad.LEFT_RIGHT, FlxVirtualPad.A_B_X_Y);
		pad.setAlpha(0.5f);

		_bullets = new FlxGroup();

 		player = new Player(20,300,16,16,_bullets,_littleGibs, pad);
		player.setHasGravityToggle(true);
		player.setHasFlyingToggle(true);

		FlxG.camera.follow(player, FlxCamera.STYLE_PLATFORMER);
	    FlxG.camera.setBounds(0,0,800,480,true);// 1st 400,240  2nd 800,240, 3rd 1200,48

		add(player);					
		add(_littleGibs);
		add(pad);
		add(_bullets);
		
		

	}

    @Override
	public void update(){	
		if (FlxG.mouse.justPressed() && action == ACTION_IDLE) 
        {
            //Get data map coordinate
            int mx = (int)FlxG.mouse.x / TILE_WIDTH;
            int my = (int)FlxG.mouse.y / TILE_HEIGHT;

            //Change tile toogle
			if(level.getTile(mx, my) == 21)//max amount ofblocks
			{
				level.setTile(mx, my,0, true);
			}else
			{
            level.setTile(mx, my,1 + level.getTile(mx, my), true);
			}
        }
		
		//if(blocktypeflx
	/*	final FlxText text = new FlxText(2, 2, 100)
			.setFormat("Roboto-Regular.tff", 30, 0x0099CC);
		add(text);

		// Just to vertical align the buttons.
		FlxUIGroup radioButtonGroup;
		add(radioButtonGroup = new FlxUIGroup(10, 20, "Select Choice"));
		radioButtonGroup.label.setFormat("Roboto-Regular.tff", 14, 0x0099CC);

		// Radiobutton group. It's mandatory.
		final FlxRadioButtonGroup radioGroup = new FlxRadioButtonGroup();
		// Fire a callback when a radiobutton changes status.
		radioGroup.onChange = new IFlxRadioButtonGroup()
		{
			@Override
			public void callback()
			{			
				if(radioGroup.getSelectedLabel() == radioGroup.members.get(0))
					text.setText(radioGroup.getSelectedLabel().toString());
				if (FlxG.mouse.justPressed() && action == ACTION_IDLE) 
				{
					//Get data map coordinate
					int mx = (int)FlxG.mouse.x / TILE_WIDTH;
					int my = (int)FlxG.mouse.y / TILE_HEIGHT;

					//Change tile toogle
					level.setTile(mx, my, 2 - level.getTile(mx, my), true);
				}
			}
		};

		// Create a bunch of radios. Default will be Linux.
		radioButtonGroup.add(new FlxRadioButton(400, 240, "A", radioGroup, null, "STONE"));
		//radioButtonGroup.add(new FlxRadioButton(0, 0, "B", radioGroup, null, "HTML5"));
		//radioButtonGroup.add(new FlxRadioButton(0, 0, "C", radioGroup, null, "iOS"));
		//radioButtonGroup.add(new FlxRadioButton(0, 0, "D", radioGroup, null, "Linux"));
		//radioButtonGroup.add(new FlxRadioButton(0, 0, "E", radioGroup, null, "Windows"));
		radioGroup.setCheck(3);*/
		super.update();				
		FlxG.collide(level,player);

	}
	
	private void onBack(){
		FlxG.switchState(new Settings());
	}
	
	@Override
	public void draw()
	{
		super.draw();
		highlightbox.drawDebug();
	}

	@Override
	public void destroy()
	{
		// TODO: Implement this method
		super.destroy();
		pause = null;
		level = null;
		block = null;
		player = null;
		pad = null;
	}
	
	public IFlxDialogBox blocktypeflx = new IFlxDialogBox()
	{

		
		
		@Override
		public void onCancel()
		{
			// TODO: Implement this method
		}

		@Override
		public void onInput()
		{
			
			
				
			}// TODO: Implement this method
		
		
			
			
		

		
		
		
	};
}
