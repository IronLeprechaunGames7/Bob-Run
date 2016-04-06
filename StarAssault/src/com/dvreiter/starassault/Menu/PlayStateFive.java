package com.dvreiter.starassault.Menu;	

import java.io.*;

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
import android.os.*;

public class PlayStateFive extends FlxState
{	
	private static final int TILE_WIDTH = 16;
	private static final int TILE_HEIGHT = 16;

	private FlxText coords;
	private FlxObject highlightbox;
	private int action;
	private static final int ACTION_IDLE = 0;
	private FlxButton backbtn;
	//private final String ImgTextField = "textfield.png";
	//private FlxInputText inputText;
	private int[] ScreenData;
	
	//new Level info
	private int levelWidth = 50;
	private int levelHeight = 30;
	private String color = "0xFF00CCF";
	private Boolean isPortal = true;
	private boolean isPortalCoin = true;
	
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
		FlxG.setBgColor(0xFFE0CCFF);
		FlxG.mouse.show();

		action = ACTION_IDLE;
		System.out.println("hi");
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
		level.loadMap(FlxTilemap.arrayToCSV(new IntArray(data), 50), "tilemap.png", 16, 16);// 50x30
		add(level);
		
	
		coords = new FlxText(10,10,90,"Coords:");	
		coords.setSize(10);
		coords.setColor(0x3a5c39);
		add(coords);
		
		
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
//		FlxUISkin skin = new FlxUISkin();
//		skin.DISABLED = 1;
//		skin.HIGHLIGHT = 2;
//		skin.PRESSED = 2;
//		skin.ACTIVE_NORMAL = 2;
//		skin.ACTIVE_HIGHTLIGHT = 2;
//		skin.ACTIVE_PRESSED = 2;
//		skin.labelPosition = FlxUISkin.LABEL_TOP;
//		skin.labelOffset.y = -3;
//		skin.labelWidth = 200;
//		skin.setImage(ImgTextField, 328, 32);
//		skin.setFormat(FntRobotoRegular, 12, 0x0099CC);
//
		// This use only one image for the skin.
//		inputText = new FlxInputText(10, 270, skin, "SINGLE IMAGE", 200, 32);
//		inputText.textfield.setFormat(FntRobotoRegular, 18);
//		add(inputText);
		add(player);					
		add(_littleGibs);
		add(pad);
		add(_bullets);
		

	}

    @Override
	public void update(){	
		//Get data map coordinate
		int mx = (int)FlxG.mouse.x / TILE_WIDTH;
		int my = (int)FlxG.mouse.y / TILE_HEIGHT;
		coords.setText("Coords:" + "\n X:" +mx + "\n Y:" +my);
		//coords.x = mx * 16;
		//coords.y = my * 16;
		coords.x = player.x;
		coords.y = player.y + 40;
		if (FlxG.mouse.justPressed() && action == ACTION_IDLE) 
        {	
            //Change tile toogle
			if(level.getTile(mx, my) == 21)//max amount ofblocks
			{
				level.setTile(mx, my,0, true);
			}else
			{
            level.setTile(mx, my,1 + level.getTile(mx, my), true);
			}
        }
	
			if(FlxG.keys.justPressed("BACK"))
			{
				saveGame();
			}
		//if(blocktypeflx
	/*	final FlxText text = new FlxText(2, 2, 100)
			.setFormat("Roboto-Regular.tff", 30, 0x0099CC);
		add(text);

		// Just to vertical align the buttons.
		FlxUIGroup radioButtonGroup;
		add(radioButtonGroup = new FlxUIGroup(10, 20, "Select Choice"));
		radioButtonGroup.label.setFormat("Roboto-Regular.tff", 14, 0x0099CC);

		// Radiobutton group. It's mand3atory.
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
	
	public int[] CSVtoArray(int width,int height)
	{
		ScreenData = new int[width * height];
		int round = 0;
		
		for(int y = 0;  y <= height - 1;  y = y +1)
		{
			for(int x = 0; x <= width - 1; x = x + 1)
			{
				ScreenData[round] =  level.getTile(x , y);	
				level.setTile(x, y,1,true);
				round++;
			}
		}
		return ScreenData;	
	}
	
	private void onBack(){
		FlxG.switchState(new Settings());
	}
	private void saveGame()
	{
		try {
			int [] views;
			views = new int [ 50 * 30];
			views = CSVtoArray(50,30);
			
			final File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Bobrun/" );

			if (!dir.exists())
				dir.mkdirs(); 

			final File myFile = new File(dir, "test" + ".txt");

			if (!myFile.exists()) 
				myFile.createNewFile();
			

			PrintWriter pr = new PrintWriter(myFile);    

			for (int i=0; i<views.length ; i++)
			{
				if(i == 0)
				{
//					pr.println("package com.dvreiter.starassault.Levels;");
//
//					pr.println("import org.flixel.FlxButton;");
//					pr.println("import org.flixel.FlxG;");
//					pr.println("import org.flixel.FlxObject;");
//					pr.println("import org.flixel.FlxSprite;");
//					pr.println("import org.flixel.FlxState;");
//					pr.println("import org.flixel.FlxText;");
//					pr.println("import org.flixel.FlxTileblock;");
//					pr.println("import org.flixel.FlxTilemap;");
//					pr.println("import org.flixel.event.IFlxCollision;");
//					pr.println("import org.flixel.*;");
//					pr.println("import com.badlogic.gdx.utils.IntArray;");
//					pr.println("import org.flixel.ui.*;");
//					pr.println("import org.flixel.event.*;");
//					pr.println("import org.apache.http.message.*;");
//					pr.println("import com.dvreiter.starassault.Player.*;");
//					pr.println("import com.dvreiter.starassault.*;");
//					pr.println("import com.dvreiter.starassault.Mobs.*;");
//					pr.println("import com.dvreiter.starassault.Objects.*;");
//					pr.println("import com.badlogic.gdx.utils.*;\n\n");
//					pr.println("public class ExamplePlayState extends FlxState{\n//My public variables\npublic FlxTilemap level; //A name for our first level or world which is declared as a FlxTilemap.");
//					pr.println("public FlxVirtualPad pad; //Adding the game pad for buttons that comes already made. You can add custom ones.\npublic FlxSprite player; //Our sprite named player.	\npublic FlxSprite enemy1; //Our sprite named enemy1.");
//					pr.println("\npublic FlxSprite portal; //Our sprite named portal.\npublic FlxGroup coins; //A group of coins for creating multiple coins in the level.");
//					pr.println("\npublic FlxSprite portalcoin; //Our sprite named portalcoin.\npublic FlxGroup spikes; //A group of spikes for creating multiple spikes in pur level.");
//					pr.println("public FlxText status; //A text for checking the player status during the game.\npublic FlxTileblock leftboundary; //The left border of our world which is a block");
//					pr.println("public FlxTileblock rightboundary; //The right border of our world which is a block\npublic FlxTileblock upperboundary; //The upper border of our world which is a block\n");
//
//					pr.println("@Override");
//					pr.println("public void create(){		");				
//					pr.println("FlxG.setBgColor(" + color +");");
					pr.println("int data [] = {");
				}

				pr.print(views[i]);
				if(i != views.length - 1){
					pr.print(",");
				}else
				{
					pr.println("};");
					pr.println("level = new FlxTilemap();");
					pr.print("level.loadMap(FlxTilemap.arrayToCSV(new IntArray(data),"+ levelWidth +"), " + '"' + "tilemap.png" + '"' +" +, 16, 16);\n");
					pr.println("level.setSolid(true);");
					pr.println("add(level);");

//					pr.println("\n\n_littleGibs = new FlxEmitter();\n_littleGibs.setXSpeed(-150,150);\n_littleGibs.setYSpeed(-200,0);\n_littleGibs.setRotation(-720,-720);");
//					pr.println("_littleGibs.gravity = 350;\n_littleGibs.bounce = 0.5f;");
//					pr.print("_littleGibs.makeParticles(" + '"' + "gibs.png" + '"' + ",100,10,true,0.5f);");
//					
//					if(isPortal)
//					{
//					pr.println("portal = new FlxSprite(50, 50);//50");
//					pr.print("portal.loadGraphic(" + '"' + "portal.png" + '"' + ", true, true, 16, 16);\n");
//					pr.print("portal.addAnimation(" + '"' + "spinning" + '"' + ", new int[]{0, 1, 2}, 6, true);\n");
//					pr.print("portal.play(" + '"' + "spinning" + '"' + ");\n");
//					pr.println("portal.immovable = true;" + "portal.exists = false;" +"add(portal);");
//					}
//
//					if(isPortalCoin)
//					{
//					pr.println("portalcoin = new FlxSprite(170, 80);//80");
//					pr.print("portalcoin.loadGraphic(" + '"' + "Portalcoin.png" + '"' + ", true, true, 16, 16);\n");
//					pr.print("portalcoin.addAnimation(" + '"' + "rotate" + '"' + ", new int[]{0, 1, 2}, 4, true);\n");
//					pr.print("portalcoin.play(" + '"' + "rotate" + '"' + ");\n");
//					pr.println("add(portalcoin);\n");
//					}				
//					pr.println("//Now for Entities") ;
//					
//					pr.println("pad = new FlxVirtualPad(FlxVirtualPad.LEFT_RIGHT, FlxVirtualPad.A_B_X_Y);");
//					pr.println("pad.setAlpha(0.5f);\n" + "_bullets = new FlxGroup()\n");
//					//players and enimies
//					pr.println("player = new Player(20,150,16,16,_bullets,_littleGibs,pad);" + "player.setHasGravityToggle(false);" + "player.setHasFlyingToggle(false);\n");
//					
//					pr.println("FlxG.camera.follow(player, FlxCamera.STYLE_PLATFORMER);");
//					pr.println("FlxG.camera.setBounds(0,0,"+ levelWidth * 16 +"," + levelHeight *16 +",ctrue);// 1st 400,240  2nd 800,240, 3rd 1200,480//");
//					
					
					
					
					
					
				}
			}
			pr.close();
		}catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("No such file exists.");
		}		
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
	
	
}
