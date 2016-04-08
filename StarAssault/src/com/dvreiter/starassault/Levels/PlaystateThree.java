package com.dvreiter.starassault.Levels; 

//import com.dvreiter.starassault.Enemy;
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
import com.dvreiter.starassault.Player.*;
import com.dvreiter.starassault.Mobs.*;
import com.dvreiter.starassault.*;
import com.dvreiter.starassault.Objects.*;

public class PlaystateThree extends FlxState
{	
	public FlxButton pause;
	public FlxTilemap level;
	public FlxSprite portal;
	public FlxSprite key;
	public FlxSprite lock;
	public FlxSprite hearts;
	public FlxGroup coins;
	public FlxGroup ppowerupp;
	public FlxSprite portalcoin;
	public FlxGroup spikes;
	public FlxText score;
	public FlxTileblock topBorder;
	public FlxTileblock rightBorder, leftBorder;
	protected FlxEmitter _littleGibs;
	
	public FlxTileblock pauseblock;
	public FlxButton pause;
	private FlxButton Pausebtn;
	private FlxButton Exitbtn;
	private FlxButton Settingsbtn;
	private FlxButton Resumebtn;
	
	private FlxSave gameSave;
	FlxGroup _bullets;
	
	FlxVirtualPad pad;
	Player player;
	Enemy enemy1;
	
	@Override
	public void create()
	{						
		FlxG.setBgColor(0xFF00CCFF);
		FlxG.mouse.show();

		int[] data = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			1,8,8,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,6,
			0,0,0,0,0,0,6,0,0,0,0,0,0,0,0,0,0,0,4,1,1,5,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,6,0,
			0,0,0,0,6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,6,
			4,1,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,6,0,
			0,0,0,0,4,1,1,1,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,6,
			1,1,1,1,1,1,1,1,1,1,1,1,8,8,1,1,1,1,8,8,1,1,1,1,7,               
			7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,10,
			10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,
			10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,
		};

		level = new FlxTilemap();
		level.loadMap(FlxTilemap.arrayToCSV(new IntArray(data), 25), "tilemap.png", 16, 16); 
		// 400x240										
		
		_littleGibs = new FlxEmitter();
		_littleGibs.setXSpeed(-150,150);
		_littleGibs.setYSpeed(-200,0);
		_littleGibs.setRotation(-720,-720);
		_littleGibs.gravity = 350;
		_littleGibs.bounce = 0.5f;
		_littleGibs.makeParticles("gibs.png",100,10,true,0.5f);

		leftBorder = new FlxTileblock(-16,0,16,240);
		leftBorder.makeGraphic(16,240);
		add(leftBorder);
		
		rightBorder = new FlxTileblock(401,0,16,240);
		rightBorder.makeGraphic(16,240);
		add(rightBorder);
		
		topBorder = new FlxTileblock(0, -16, 401, 16);
		topBorder.makeGraphic(401,16);
		add(topBorder);
		
		portal = new FlxSprite(300, 30);
		portal.loadGraphic("portal.png", true, true, 16, 16);
		portal.addAnimation("spinning", new int[]{0, 1, 2}, 6, true);
		portal.play("spinning");
		portal.exists = false;
		add(portal);
		
		portalcoin = new FlxSprite(10,1);
		portalcoin.loadGraphic("Portalcoin.png", true, true, 16, 16);
		portalcoin.addAnimation("rotate", new int[]{0, 1, 2}, 4, true);
		portalcoin.play("rotate");
		add(portalcoin);
		
		key = new FlxSprite(380, 10);
		key.loadGraphic("lockkey.png", true, true, 16, 16);  
		key.immovable = true;
		add(key);

		lock = new FlxSprite(30, 1);
		lock.loadGraphic("keylock.png", true, true, 16, 16);  
		lock.immovable = true;
		add(lock);
		
		pad = new FlxVirtualPad(FlxVirtualPad.LEFT_RIGHT, FlxVirtualPad.A_B_X_Y);
		pad.setAlpha(0.5f);
		
		_bullets = new FlxGroup();
		
		player = new Player(150,3,16,16,_bullets,_littleGibs,pad);
		player.setHasGravityToggle(false);
		player.setHasFlyingToggle(false);

		enemy1 = new Enemy(200,50,16,16,500); 		
		enemy1.followSprite(player);
		
		coins = new FlxGroup(); 			
		//bottom coins	
		createCoin(230,145);		
		add(coins);

		ppowerupp = new FlxGroup();
	    createPpowerup(170,155);
		add(ppowerupp);
		
		spikes = new FlxGroup();	
		createSpike(200,160,0);
		createSpike(296,160,0);
		add(spikes);

		score = new FlxText(200,5,100, "SCORE: ");
		score.setShadow(0xff000000);
		score.setText("SCORE: "+(coins.countDead()*100));
		
		pauseblock = new FlxTileblock(10, 10, 340, 180);//780, 400
		pauseblock.makeGraphic(340, 180, 0xff000000);// 390, 230
		pauseblock.setAlpha(0.5f);
		pauseblock.setSolid(false);
		pauseblock.immovable = true;
		pauseblock.scrollFactor.x = pauseblock.scrollFactor.y = 0;
		pauseblock.visible = false;
		add(pauseblock);

		Resumebtn = new FlxButton(160, 100, "Resume");//x.190, x.180, x.170, y.110
		Resumebtn.setSolid(false);//Coords 1: (400, 240),
		Resumebtn.immovable = true;
		Resumebtn.scrollFactor.x = Resumebtn.scrollFactor.y = 0;
		Resumebtn.exists = true;
		Resumebtn.visible = false;
		add(Resumebtn);

		Settingsbtn = new FlxButton(160, 130, "Settings", new IFlxButton(){@Override public void callback(){onSettings();}});//x.190, x.180, x.170, y.130
		Settingsbtn.setSolid(false);//Coords 1: (400, 260), 
		Settingsbtn.immovable = true;
		Settingsbtn.scrollFactor.x = Settingsbtn.scrollFactor.y = 0;
		Settingsbtn.exists = true;
		Settingsbtn.visible = false;
		add(Settingsbtn);

		Exitbtn = new FlxButton(160, 160, "Quit Game", new IFlxButton(){@Override public void callback(){onExit();}});//x.190, x.180, x.170, y.150
		Exitbtn.setSolid(false);//Coords 1: (400, 280)
		Exitbtn.immovable = true;
		Exitbtn.scrollFactor.x = Exitbtn.scrollFactor.y = 0;
		Exitbtn.exists = true;
		Exitbtn.visible = false;
		add(Exitbtn);

		Pausebtn =  new FlxButton(300, 6, "||");
		Pausebtn.setSolid(false);
		Pausebtn.immovable = true;
		Pausebtn.exists = true;
		Pausebtn.scrollFactor.x = Pausebtn.scrollFactor.y = 0;
		add(Pausebtn);	

		add(score);					
		add(_bullets);
		add(player);			
		add(_littleGibs);
		add(enemy1);		
		add(level);
		add(pad);
	}
	public void createCoin(int X,int Y)
	{
		FlxSprite coin = new FlxSprite(X,Y);
		coin.loadGraphic("coin.png", true, true, 16, 16);
		coin.addAnimation("rotating", new int[]{0, 1, 2}, 6, true);
		coin.play("rotating");
		coins.add(coin);
	}
    public void createSpike(int X, int Y,int Angle){
		Spike spike = new Spike(X,Y,Angle);
		spikes.add(spike);
	}
    public void createPpowerup(int X, int Y){
		FlxSprite ppowerup = new FlxSprite(X,Y);
		ppowerup.loadGraphic("peppermintpowerup.png", true, true, 16, 16); 
		ppowerup.immovable = true;
		ppowerupp.add(ppowerup);
	}		
	
    @Override
	public void update()
	{
			if(Pausebtn.status == Pausebtn.PRESSED|| FlxG.keys.justPressed("BACK")){
			Pausebtn.visible = false;
			_player.exists = false;
			pauseblock.visible= true;

			Resumebtn.visible = true;
			Settingsbtn.visible = true;
			Exitbtn.visible = true;	
			enemies.active = false;
		}
		if(Resumebtn.status == Resumebtn.PRESSED || FlxG.keys.justPressed("BACK")){
			enemies.active=true;
			Pausebtn.visible = true;
			_player.exists = true;
			Resumebtn.visible = false;
			Settingsbtn.visible = false;
			Exitbtn.visible = false;
			pauseblock.visible = false;
		}
		super.update();
		FlxG.overlap(coins,player,getCoin);	
		FlxG.overlap(key,player,openlock);
		FlxG.overlap(spikes,player,doSpikeDamage);
		FlxG.overlap(portalcoin,player,getPCoin);
		FlxG.overlap(ppowerupp,player,getPP);
		FlxG.overlap(portal,player,win);
		FlxG.collide(enemy1,level);
		FlxG.collide(lock,player);
		FlxG.collide(enemy1,spikes);
		FlxG.collide(enemy1,player,doPlayerDamage);
		FlxG.collide(level,player);
		FlxG.collide(_bullets, enemy1, doEnemyDamage);
		FlxG.collide(level, _bullets);
		FlxG.collide(_bullets, spikes);
		FlxG.collide(leftBorder,player);
		FlxG.collide(rightBorder, player);
		FlxG.collide(topBorder, player);

		if(player.y > FlxG.height)
		{
			FlxG.resetState();
		}
	}

	@Override
	public void destroy()
	{
		// TODO: Implement this method
		super.destroy();
		pause = null;
		level = null;
		portal = null;
		hearts = null;
		coins = null;
		portalcoin = null;
		portal = null;
		spikes = null;
		score = null;
		player = null;
		_littleGibs = null;
		key = null;
		lock = null;
		hearts = null;
		enemy1 = null;
		pad = null;
	}

	IFlxCollision getCoin = new IFlxCollision()
	{
		@Override
		public void callback(FlxObject Coin, FlxObject Player)
		{						
	        Coin.kill();	     
			score.setText("SCORE: "+(coins.countDead()*100));	 
	    }
	};				

	IFlxCollision getPCoin = new IFlxCollision()
	{
		@Override
		public void callback(FlxObject Portalcoin, FlxObject Player)
		{						
	        Portalcoin.kill();	     
	        portal.exists = true;
	    }
	};				
	
	IFlxCollision getPP = new IFlxCollision()
	{
		@Override
		public void callback(FlxObject Ppowerupp, FlxObject Player)
		{						
	        Ppowerupp.kill();	     
	        player.maxVelocity.x = 200;
	    }
	};				
	
	IFlxCollision openlock = new IFlxCollision()
	{
		@Override
		public void callback(FlxObject Key, FlxObject Player)
		{						
	        Key.kill();	     
			lock.kill();	        
	    }
	};		

	IFlxCollision doSpikeDamage = new IFlxCollision()
	{
		@Override
		public void callback(FlxObject Spikes, FlxObject Player)
		{						
	//        add(new FlxText(200,22 , 100, "You Died!"));			
			player.kill();
	    }
	};

	IFlxCollision doEnemyDamage = new IFlxCollision()
	{
		@Override
		public void callback(FlxObject Enemy, FlxObject Bullet)
		{						
	        enemy1.hurt(1);
	    }
	};
	
	IFlxCollision doPlayerDamage = new IFlxCollision()
	{
		@Override
		public void callback(FlxObject Enemy1, FlxObject Player)
		{						
	      //  add(new FlxText(200,22 , 100, "You Died!"));			
			player.kill();
	    }
	};

	IFlxCollision win = new IFlxCollision()
	{
		@Override
		public void callback(FlxObject Exit, FlxObject Player)
		{
			add(new FlxText(200,200, 100, "You win Level 3!"));			
			player.exists = false;
			FlxG.fade(0xff000000,1, onFade);
			//FlxG.switchState(new PlayState4());
		}
	};
	
	public IFlxCamera onFade = new IFlxCamera()
	{
		@Override
		public void callback()
		{
			//saving stuff
			gameSave = new FlxSave();
			gameSave.bind("Test");

			//Save

			gameSave.data.put("Progress", 5);
			gameSave.flush();
			
			FlxG.switchState(new PlayState4());
		}

	};
}
       	
