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
import android.text.*;
import android.widget.*;
import com.badlogic.gdx.utils.*;


public class PlayStateLE extends FlxState
{	
	private static final int TILE_WIDTH = 16;
	private static final int TILE_HEIGHT = 16;

	private FlxText coords;
	private FlxObject highlightbox;
	private int action;
	private static final int ACTION_IDLE = 0;
	private FlxButton backbtn;
	private final String ImgTextField = "textfield.png";
	private FlxInputText inputText;
	private int[] ScreenData;
	private FlxDialogBox dialog;
	
	//new Level info
	private int levelWidth = 50;
	private int levelHeight = 30;
	private String color = "0xFF00CCF";
	private Boolean isPortal = true;
	private boolean isPortalCoin = true;
	private boolean allCode = false;
	private int []enemyPos;
	private int enemySum = 0;
	private int getmob;
	private FlxTimer timer;
	private FlxGroup enemies;
	
	public FlxButton pause;
	public FlxTilemap level;
	public FlxTileblock block;
	private FlxButton closeMenu;
	private FlxButton Menu;
	private FlxButton typeBtn; 
	private FlxButton itemBtn;
	private FlxButton itemBackBtn;
	private FlxButton ItemDisplay;
	private FlxButton savebtn;
	private int Item;
	private int Type;
	private String ifSaved = " ";
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
		
		timer= new FlxTimer();
		_littleGibs = new FlxEmitter();
		_littleGibs.setXSpeed(-150,150);
		_littleGibs.setYSpeed(-200,0);
		_littleGibs.setRotation(-720,-720);
		_littleGibs.gravity = 350;
		_littleGibs.bounce = 0.5f;
		_littleGibs.makeParticles("gibs.png",100,10,true,0.5f);
		
		savebtn = new FlxButton(240, 5, "Save", new IFlxButton(){@Override public void callback(){saveGame();}});
		savebtn.scrollFactor.x = 0;
		savebtn.scrollFactor.y = 0;
		add(savebtn);	
		
		backbtn = new FlxButton(310, 5, "Back", new IFlxButton(){@Override public void callback(){onBack();}});
		backbtn.scrollFactor.x = 0;
		backbtn.scrollFactor.y = 0;
		add(backbtn);	
		
		Menu = new FlxButton(310, 25, "Menu/Tools", new IFlxButton(){@Override public void callback(){onMenu();}});
		Menu.scrollFactor.x = 0;
		Menu.scrollFactor.y = 0;
		add(Menu);	
		Menu.visible=false;
		
		itemBackBtn = new FlxButton(310,35,"Back A Item", new IFlxButton(){@Override public void callback(){onItemBack();}});
		itemBackBtn.scrollFactor.x = 0;
		itemBackBtn.scrollFactor.y = 0;
		add(itemBackBtn);
		
		itemBtn = new FlxButton(310,55,"Grass", new IFlxButton(){@Override public void callback(){onItem();}});
		itemBtn.scrollFactor.x = 0;
		itemBtn.scrollFactor.y = 0;
		add(itemBtn);
		
		ItemDisplay = new FlxButton(310,75," ", new IFlxButton(){@Override public void callback(){onItemDisplay();}});
		ItemDisplay.scrollFactor.x = 0;
		ItemDisplay.scrollFactor.y = 0;
		add(ItemDisplay);
		ItemDisplay.width = 100;
		ItemDisplay.height = 100;
		
		enemies = new FlxGroup();
		add(enemies);
		
		
		typeBtn = new FlxButton(310,91,"Blocks", new IFlxButton(){@Override public void callback(){onType();}});
		typeBtn.scrollFactor.x = 0;
		typeBtn.scrollFactor.y = 0;
		add(typeBtn);
		
		closeMenu = new FlxButton(310,111,"Close Menu", new IFlxButton(){@Override public void callback(){onCloseMenu();}});
		closeMenu.scrollFactor.x = 0;
		closeMenu.scrollFactor.y = 0;
		add(closeMenu);
		
		highlightbox = new FlxObject(0,0,TILE_WIDTH, TILE_HEIGHT);
		// 400x240						
 		FlxG.playMusic("Pneumatic-Tok.mp3", 1f, true, true);

 		pad = new FlxVirtualPad(FlxVirtualPad.LEFT_RIGHT, FlxVirtualPad.A_B_X_Y);
		pad.setAlpha(0.5f);

		_bullets = new FlxGroup();
		
		add(dialog);
		
 		player = new Player(20,300,16,16,_bullets,_littleGibs, pad);
		player.setHasGravityToggle(true);
		player.setHasFlyingToggle(true);

		FlxG.camera.follow(player, FlxCamera.STYLE_PLATFORMER);
	    FlxG.camera.setBounds(0,0,800,480,true);// 1st 400,240  2nd 800,240, 3rd 1200,48
		
		
		enemyPos = new int[20];
		
		add(player);					
		add(_littleGibs);
		add(pad);
		add(_bullets);
		
		onItem();
	}

    @Override
	public void update()
	{	
		//Get data map coordinate
		int mx = (int)FlxG.mouse.x / TILE_WIDTH;
		int my = (int)FlxG.mouse.y / TILE_HEIGHT;
		coords.setText("Coords:" + "\n X:" +mx + "\n Y:" +my + "\n" + ifSaved );
		coords.x = player.x;
		coords.y = player.y + 40;
		if (FlxG.mouse.justPressed() && action == ACTION_IDLE) 
        {	
			if(Type == 0)//max amount ofblocks
            level.setTile(mx, my, Item, true);
			
			if(Type == 1)
				SpawnMob(mx,my);
        }
		super.update();				
		FlxG.collide(level,player);
		FlxG.collide(level,enemies);
	}//end of update
	
	private void onItemDisplay()
	{}

	private void onBack(){
		FlxG.switchState(new Settings());
	}

	private void onItemBack()
	{
		Item = Item - 2;
		if(Item == -2)
		{Item = 20;}
		onItem();	
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
				round++;
			}
		}
		return ScreenData;	
	}
	
	private void onItem(){
	Item++;
		
	if(Type == 0)
	{
		ItemDisplay.visible = true;
		if(Item > 21){Item = 0;}
		switch(Item)
		{
		case 0: itemBtn.label.setText("Eraser");
				ItemDisplay.visible = false;		
				break;
		case 1: itemBtn.label.setText("Grass");
				ItemDisplay.loadGraphic("BlockTextures/Grass1.png");
				break;
		case 2: itemBtn.label.setText("Bricks");
				ItemDisplay.loadGraphic("BlockTextures/Bricks1.png");
				break;
		case 3: itemBtn.label.setText("Bricks2");
				ItemDisplay.loadGraphic("BlockTextures/Bricks2.png");
				break;
		case 4: itemBtn.label.setText("GrassLeft");
				ItemDisplay.loadGraphic("BlockTextures/GrassLeft.png");
				break;
		case 5: itemBtn.label.setText("GrassRight");
				ItemDisplay.loadGraphic("BlockTextures/GrassRight.png");
				break;
		case 6: itemBtn.label.setText("Grass Alone");
				ItemDisplay.loadGraphic("BlockTextures/GrassAlone.png");
				break;
		case 7: itemBtn.label.setText("Dirt+Stone Mix");
				ItemDisplay.loadGraphic("BlockTextures/DirtStone1.png");
				break;
		case 8: itemBtn.label.setText("Grass Solid");
				ItemDisplay.loadGraphic("BlockTextures/FullGrass.png");
				break;
		case 9: itemBtn.label.setText("Dirt Alone");
				ItemDisplay.loadGraphic("BlockTextures/FullDirt.png");
				break;
		case 10: itemBtn.label.setText("Stone 1");
				ItemDisplay.loadGraphic("BlockTextures/Stone1.png");
				break;
		case 11: onItem();
				break;
		case 12: itemBtn.label.setText("Grass 2");
				ItemDisplay.loadGraphic("BlockTextures/Grass2.png");
				break;
		case 13:Item = Item +4;
				onItem();
		case 14:
				break;
		case 15:
				break;
		case 16:
				break;
		case 17:
				break;
		case 18: itemBtn.label.setText("Dirt Stone 2");
				ItemDisplay.loadGraphic("BlockTextures/DirtStone2.png");
				break;			
		case 19:Item++;
				onItem();
				break;
		case 20:
				break;
		case 21: itemBtn.label.setText("Stone 2");
				ItemDisplay.loadGraphic("BlockTextures/Stone2.png");
				break;
		case 22:onItem();
				break;
		default:itemBtn.label.setText("ERROR");
				break;
		}
	}
	
	
	if(Type == 1)
		{
		if(Item > 4){Item = 0;}
			switch(Item)
			{
			case 4: itemBtn.label.setText("Bird");
			
					break;
			case 3: itemBtn.label.setText("Crusher");
					break;
			case 0: itemBtn.label.setText("Enemy");
					break;
			case 1: itemBtn.label.setText("Skeleton");
					break;
			case 2: itemBtn.label.setText("Turret");
					break;
			default: itemBtn.label.setText("Error");
					break;
			}
		}
	}
	public void createEnemy(int X, int Y, int Accel){
		Enemy enemy = new Enemy(X,Y,16,16,Accel);
		enemies.add(enemy);
		enemySum++;
	}
	
	private void SpawnMob(int x,int y)
	{
	int _enemySum = enemySum + 1;		
		switch(getmob)
		{	
			case 4: itemBtn.label.setText("Bird");
					break;
			case 3: itemBtn.label.setText("Crusher");
					break;
			case 0: if(_enemySum < 11)
					{		
							enemyPos[enemySum *2] = x;
							enemyPos[(enemySum * 2) - 1] = y;	
							createEnemy(x *16 ,y*16 ,500);	
					}
					break;
			case 1: itemBtn.label.setText("Skeleton");
					break;
			case 2: itemBtn.label.setText("Turret");
					break;
			default: 
				break;
		}
		
	}
	private void onType(){
		Type++;
		if(Type > 1){Type = 0;}
		Item = 0;
		switch(Type){
		case 0: typeBtn.label.setText("Blocks");
				itemBtn.label.setText("Grass");
				break;
		case 1: typeBtn.label.setText("Mobs");
				itemBtn.label.setText("Enemy");
				break;
		default: typeBtn.label.setText("Error");
			}
		}
		
		
	private void onMenu()
	{
		Item++;
		savebtn.visible = true;
		itemBackBtn.visible= true;
		itemBtn.visible=true;
		typeBtn.visible=true;
		Menu.visible=false;
		closeMenu.visible=true;
		ItemDisplay.y = 75;
			
	}
		
	private void onCloseMenu()
	{
		savebtn.visible = false;
		itemBackBtn.visible= false;
		itemBtn.visible=false;
		typeBtn.visible=false;
		Menu.visible=true;
		closeMenu.visible=false;
		ItemDisplay.y = 45;
		
		
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
				if(i == 0 && allCode == true)
				{
					pr.println("package com.dvreiter.starassault.Levels;");
					pr.println("import org.flixel.FlxButton;" + "import org.flixel.FlxG;");
					pr.println("import org.flixel.FlxObject;" + "import org.flixel.FlxSprite;");
					pr.println("import org.flixel.FlxState;" + "import org.flixel.FlxText;");
					pr.println("import org.flixel.FlxTileblock;" + "import org.flixel.FlxTilemap;");
					pr.println("import org.flixel.event.IFlxCollision;" + "import org.flixel.*;");
					pr.println("import com.badlogic.gdx.utils.IntArray;");
					pr.println("import org.flixel.ui.*;" + "import org.flixel.event.*;" + "import org.apache.http.message.*;");
					pr.println("import com.dvreiter.starassault.Player.*;" + "import com.dvreiter.starassault.*;");
					pr.println("import com.dvreiter.starassault.Mobs.*;" + "import com.dvreiter.starassault.Objects.*;");
					pr.println("import com.badlogic.gdx.utils.*;\n\n" + "public class ExamplePlayState extends FlxState{\n//My public variables\npublic FlxTilemap level; //A name for our first level or world which is declared as a FlxTilemap.");
					pr.println("public FlxVirtualPad pad; //Adding the game pad for buttons that comes already made. You can add custom ones.\npublic FlxSprite player; //Our sprite named player.	\npublic FlxSprite enemy1; //Our sprite named enemy1.");
					pr.println("\npublic FlxSprite portal; //Our sprite named portal.\npublic FlxGroup coins; //A group of coins for creating multiple coins in the level." + "\npublic FlxSprite portalcoin; //Our sprite named portalcoin.\npublic FlxGroup spikes; //A group of spikes for creating multiple spikes in pur level.");
					pr.println("public FlxText status; //A text for checking the player status during the game.\npublic FlxTileblock leftboundary; //The left border of our world which is a block" + "public FlxTileblock rightboundary; //The right border of our world which is a block\npublic FlxTileblock upperboundary; //The upper border of our world which is a block\n");

					pr.println("@Override");
					pr.println("public void create(){		");				
					pr.println("FlxG.setBgColor(" + color +");");
					}
					if(i == 0)
					pr.println("int data [] = {");
				

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

					
					if(allCode)
					{
					pr.println("\n\n_littleGibs = new FlxEmitter();\n_littleGibs.setXSpeed(-150,150);\n_littleGibs.setYSpeed(-200,0);\n_littleGibs.setRotation(-720,-720);");
					pr.println("_littleGibs.gravity = 350;\n_littleGibs.bounce = 0.5f;");
					pr.print("_littleGibs.makeParticles(" + '"' + "gibs.png" + '"' + ",100,10,true,0.5f);");
					
					if(isPortal)
					{
					pr.println("portal = new FlxSprite(50, 50);//50");
					pr.print("portal.loadGraphic(" + '"' + "portal.png" + '"' + ", true, true, 16, 16);\n");
					pr.print("portal.addAnimation(" + '"' + "spinning" + '"' + ", new int[]{0, 1, 2}, 6, true);\n");
					pr.print("portal.play(" + '"' + "spinning" + '"' + ");\n");
					pr.println("portal.immovable = true;" + "portal.exists = false;" +"add(portal);");
					}

					if(isPortalCoin)
					{
					pr.println("portalcoin = new FlxSprite(170, 80);//80");
					pr.print("portalcoin.loadGraphic(" + '"' + "Portalcoin.png" + '"' + ", true, true, 16, 16);\n");
					pr.print("portalcoin.addAnimation(" + '"' + "rotate" + '"' + ", new int[]{0, 1, 2}, 4, true);\n");
					pr.print("portalcoin.play(" + '"' + "rotate" + '"' + ");\n");
					pr.println("add(portalcoin);\n");
					}				
					pr.println("//Now for Entities") ;
					
					pr.println("pad = new FlxVirtualPad(FlxVirtualPad.LEFT_RIGHT, FlxVirtualPad.A_B_X_Y);");
					pr.println("pad.setAlpha(0.5f);\n" + "_bullets = new FlxGroup()\n");
					//players and enimies
					pr.println("player = new Player(20,150,16,16,_bullets,_littleGibs,pad);" + "player.setHasGravityToggle(false);" + "player.setHasFlyingToggle(false);\n");
					
					pr.println("FlxG.camera.follow(player, FlxCamera.STYLE_PLATFORMER);");
					pr.println("FlxG.camera.setBounds(0,0,"+ levelWidth * 16 +"," + levelHeight *16 +",ctrue);// 1st 400,240  2nd 800,240, 3rd 1200,480//");
				
					while(enemySum > 0)
					{
					int enemyX = enemyPos[enemySum *2];
					int enemyY = enemyPos[(enemySum * 2) - 1];
						pr.print("enemy" + enemySum + " = new Enemy(" + enemyX + "," + enemyY + ",16,16,500); //y = 60");		
						pr.print("enemy" + enemySum + ".followSprite(player);");
						enemySum--;
					}			
					}
				}
			}
			pr.close();
		}catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("No such file exists.");
		}		
	ifSaved = "Saved";
	timer.start(3,1,Tstop);
	}
	
	IFlxTimer Tstop = new IFlxTimer(){@Override
	public void callback(FlxTimer flxTimer){ifSaved = " ";}
	};
	
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
