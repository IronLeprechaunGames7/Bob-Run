package com.dvreiter.starassault.Levels;	

import org.flixel.FlxButton;
import org.flixel.event.IFlxButton;
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
import org.flixel.system.*;
import com.badlogic.gdx.utils.*;
import org.flixel.ui.event.*;
import com.dvreiter.starassault.Mobs.*;
import com.dvreiter.starassault.Player.*;
import com.dvreiter.starassault.Menu.*;
import com.dvreiter.starassault.Objects.*;

public class PlayState6 extends FlxState
{	

	private static final int TILE_WIDTH = 16;
	private static final int TILE_HEIGHT = 16;

	public FlxButton pause;
	public FlxTilemap level;
	public FlxTileblock pauseblock;
	public FlxSprite portal;	
	public FlxSprite hearts;
	public FlxGroup coins;
	public FlxGroup walls;
	public FlxSprite portalcoin;
	public FlxGroup spikes;	   
	public FlxText status;
	public FlxText goal;	
	public FlxSprite coin;
	public FlxSprite wallSwitch;
	public FlxText _fps;
	public FlxSprite elevator, pusher;

	public FlxGroup enemies;
	Skeleton skeleton;
	Turret turret;
	Player _player;
	FlxVirtualPad pad;
	public  FlxGroup _bullets;
	public FlxGroup _tbullets;
	protected FlxEmitter _littleGibs;

	private FlxButton Pausebtn;
	private FlxButton Exitbtn;
	private FlxButton Settingsbtn;
	private FlxButton Resumebtn;

	@Override
	public void create()
	{						
		FlxG.setFramerate(60);
		FlxG.setFlashFramerate(60);

		FlxG.setBgColor(0x4c4c4c);//6c767c
		FlxG.mouse.show();

		int[] data={2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,			
			2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,	
			2,2,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,2,2,	
			2,2,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,2,0,0,0,0,0,2,0,0,0,0,2,2,
			2,2,0,0,0,0,0,2,0,0,0,0,0,2,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,2,0,0,0,0,0,2,0,0,0,0,2,2,
			2,2,0,0,0,0,0,2,0,0,0,0,0,2,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,2,2,
			2,2,0,0,0,0,0,2,0,0,0,0,0,2,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,2,2,
			2,2,0,0,2,2,2,2,0,0,0,0,0,2,0,0,0,0,0,2,2,2,2,2,0,0,2,2,2,2,2,2,2,0,0,2,2,2,2,2,2,2,2,2,2,2,0,0,2,2,
			2,2,0,0,0,0,0,2,0,0,0,0,0,2,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,2,0,0,0,0,0,2,0,0,0,0,2,2,
			2,2,0,0,0,0,0,2,0,0,0,0,0,2,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,2,0,0,0,0,0,2,0,0,0,0,2,2,
			2,2,0,0,0,0,0,2,0,0,0,0,0,2,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,2,2,
			2,2,0,0,0,0,0,2,0,0,0,0,0,2,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,2,2,
			2,2,0,0,0,0,0,2,0,0,0,0,0,2,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,2,0,0,0,0,0,2,0,0,0,0,2,2,
			2,2,2,2,2,0,0,2,0,0,0,0,0,2,0,0,0,0,0,2,2,2,2,2,0,0,2,2,2,2,2,2,2,0,0,2,2,2,2,2,2,2,2,2,0,0,2,2,2,2,
			2,2,0,0,0,0,0,2,0,0,0,0,0,2,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,2,2,//Middle or half of y of the level map.
			2,2,0,0,0,0,0,2,0,0,0,0,0,2,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,2,0,0,0,0,0,2,0,0,0,0,2,2,			
			2,2,0,0,0,0,0,2,0,0,0,0,0,2,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,2,0,0,0,0,0,2,0,0,0,0,2,2,                                                  
			2,2,0,0,0,0,0,2,0,0,0,0,0,2,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,2,0,0,0,0,0,2,0,0,0,0,2,2, 
			2,2,0,0,0,0,0,2,0,0,0,0,0,2,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,2,0,0,0,0,0,2,0,0,0,0,2,2,
			2,2,0,0,2,2,2,2,0,0,0,0,0,2,0,0,0,0,0,2,2,2,2,2,0,0,2,2,2,2,2,2,2,2,2,2,2,2,2,2,0,0,2,2,2,2,0,0,2,2,
			2,2,0,0,0,0,0,2,0,0,0,0,0,2,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,2,2,
			2,2,0,0,0,0,0,2,0,0,0,0,0,2,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,2,0,0,0,0,0,2,0,0,0,0,2,2,
			2,2,0,0,0,0,0,2,0,0,0,0,0,2,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,2,0,0,0,0,0,2,0,0,0,0,2,2,
			2,2,0,0,0,0,0,2,0,0,0,0,0,2,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,2,0,0,0,0,2,2,
			2,2,2,2,2,0,0,2,0,0,0,0,0,2,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,2,0,0,0,0,2,2,
			2,2,0,0,0,0,0,2,0,0,0,0,0,2,0,0,0,0,0,2,0,0,2,2,2,2,2,2,2,2,2,2,2,0,0,2,2,2,2,2,2,0,0,2,2,0,0,2,2,2,
			2,2,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,2,0,0,0,0,0,2,0,0,0,0,2,2,
			2,2,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,2,0,0,0,0,0,2,0,0,0,0,2,2,
			2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,	
			2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,	
			2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,	
		};

		level = new FlxTilemap();
		level.loadMap(FlxTilemap.arrayToCSV(new IntArray(data), 50), "tilemap.png", TILE_WIDTH, TILE_HEIGHT); 
		add(level);

		portal = new FlxSprite(750, 400);//50
		portal.loadGraphic("portal.png", true, true, 16, 16);
		portal.addAnimation("spinning", new int[]{0, 1, 2}, 6, true);
		portal.play("spinning");
		portal.immovable = true;
		portal.exists = false;
		add(portal);

		portalcoin = new FlxSprite(700, 400);//first (170, 80) Second (170, 160)
		portalcoin.loadGraphic("Portalcoin.png", true, true, 16, 16);
		//	portalcoin.addAnimation("stand", new int[]{0}, 0, false);
		portalcoin.addAnimation("rotate", new int[]{0, 1, 2}, 4, true);
		portalcoin.play("rotate");
		portalcoin.exists = false;
		add(portalcoin);

		pad = new FlxVirtualPad(FlxVirtualPad.LEFT_RIGHT, FlxVirtualPad.A_B_X_Y);
		pad.setAlpha(0.5f);

		_bullets = new FlxGroup();

	    _player = new Player(64,400,16,16,_bullets,_littleGibs, pad);
		_player.setHasGravityToggle(true);

		_tbullets = new FlxGroup();

		//	turret = new Turret(740,64,16,16,_tbullets);
		enemies = new FlxGroup();
		createEnemy(115,128,500);
		
		 createEnemy(200,132,500);

		 createEnemy(200,150,500);
		 createEnemy(216,150,500);
		 add(enemies);
		 
		skeleton = new Skeleton(670,432,16,16);//x=648

		FlxG.camera.follow(_player, FlxCamera.STYLE_PLATFORMER);
	    FlxG.camera.setBounds(0,0,800,480,true);// 1st 400,240  2nd 800,240, 3rd 1200,480

	//	walls = new FlxGroup();
		//	createWall(240,224);
		//	createWall(256,224);
	//	add(walls);

		coins = new FlxGroup(); 			
		//bottom coins	
		createCoin(105, 300);//first (105, 115) second (210, 230)
		//	createCoin(90, 390);
		add(coins);

		spikes = new FlxGroup();
		/*FIRST COLUMN OF SPIKES*/
		createSpike(96,320,180);
		createSpike(80,320,180);
		
		createSpike(48,224,180);
		createSpike(64,224,180);
		
		createSpike(96,128,180);
		createSpike(80,128,180);
		
		createSpike(48,32,180);
		createSpike(64,32,180);
		add(spikes);
		
		status = new FlxText(2,2,100, "SCORE: ");
		status.setShadow(0xff000000);
		status.setText("SCORE: "+(coins.countDead()*100));
		status.scrollFactor.x = status.scrollFactor.y = 0;
		add(status);

		pauseblock = new FlxTileblock(10, 10, 380, 220);//780, 400
		pauseblock.makeGraphic(380, 220, 0xff000000);// 390, 230
		pauseblock.setAlpha(0.5f);
		pauseblock.setSolid(false);
		pauseblock.immovable = true;
		pauseblock.scrollFactor.x = pauseblock.scrollFactor.y = 0;
		pauseblock.exists = false;
		add(pauseblock);

		Resumebtn = new FlxButton(160, 100, "Resume", new IFlxButton(){@Override public void callback(){onResume();}});//x.190, x.180, x.170, y.110
		Resumebtn.setSolid(false);//Coords 1: (400, 240),
		Resumebtn.immovable = true;
		Resumebtn.scrollFactor.x = Resumebtn.scrollFactor.y = 0;
		Resumebtn.exists = false;
		add(Resumebtn);

		Settingsbtn = new FlxButton(160, 130, "Settings", new IFlxButton(){@Override public void callback(){onSettings();}});//x.190, x.180, x.170, y.130
		Settingsbtn.setSolid(false);//Coords 1: (400, 260), 
		Settingsbtn.immovable = true;
		Settingsbtn.scrollFactor.x = Settingsbtn.scrollFactor.y = 0;
		Settingsbtn.exists = false;
		add(Settingsbtn);

		Exitbtn = new FlxButton(160, 160, "Quit Game", new IFlxButton(){@Override public void callback(){onExit();}});//x.190, x.180, x.170, y.150
		Exitbtn.setSolid(false);//Coords 1: (400, 280)
		Exitbtn.immovable = true;
		Exitbtn.scrollFactor.x = Exitbtn.scrollFactor.y = 0;
		Exitbtn.exists = false;
		add(Exitbtn);

		Pausebtn = new FlxButton(300, 6, "||", new IFlxButton(){@Override public void callback(){onPause();}});
		Pausebtn.setSolid(false);
		Pausebtn.immovable = true;
		Pausebtn.exists = true;
		Pausebtn.scrollFactor.x = Pausebtn.scrollFactor.y = 0;
		add(Pausebtn);	

		add(turret);
		//	add(_tbullets);
		add(skeleton);
		add(_player);				
		add(_littleGibs);
		add(_bullets);
		add(pad);
	}
    public void createCoin(int X,int Y)
	{
		FlxSprite coin = new FlxSprite(X,Y);
		coin.loadGraphic("coin.png", true, true, 16, 16);	
		coin.addAnimation("rotating", new int[]{0, 1, 2}, 4, true);
		coin.offset.x = 3;
		coin.offset.y = 3;
		coin.centerOffsets();
		coin.play("rotating");
		coins.add(coin);
	}	

	public void createWall(int X, int Y){
		FlxSprite wall = new FlxSprite(X,Y);
		wall.loadGraphic("Wall.png", true, true, 16, 16);
		wall.centerOffsets();
		wall.immovable = true;
		walls.add(wall);

	}

	public void createSpike(int X, int Y, int Angle){
		Spike spike = new Spike(X,Y,Angle);
		spikes.add(spike);
	}

	public void createEnemy(int X, int Y, int Accel){
	 Enemy enemy = new Enemy(X,Y,16,16,Accel);
	 enemy.followSprite(_player);
	 enemies.add(enemy);
	 }

    @Override
	public void update(){	
		super.update();					  

		FlxG.collide(enemies,spikes);
		FlxG.collide(enemies,level);
		FlxG.overlap(enemies,_player,doPlayerDamage);
		FlxG.collide(enemies, _bullets,doEnemyDamage);
		FlxG.collide(enemies,enemies);
		
		/*ENEMY COLLISIONS
		FlxG.collide(_enemy,spikes);
		FlxG.collide(_enemy,level);
		FlxG.overlap(_enemy,_player,doPlayerDamage);
		FlxG.collide(_bullets, _enemy, doEnemyDamage);

		FlxG.collide(enemy2,spikes);
		FlxG.collide(enemy2,level);
		FlxG.overlap(enemy2,_player,doPlayerDamage);
		FlxG.collide(_bullets, enemy2, doEnemy2Damage);

		FlxG.collide(enemy3,spikes);
		FlxG.collide(enemy3,level);
		FlxG.overlap(enemy3,_player,doPlayerDamage);
		FlxG.collide(_bullets, enemy3, doEnemy3Damage);

		FlxG.collide(_enemy,enemy2);
		FlxG.collide(_enemy,enemy3);
		FlxG.collide(enemy2,enemy3);
*/
		/*OTHER COLLISIONS ASIDE FROM ENEMY*/

		FlxG.overlap(coins,_player,getCoin);	
		FlxG.overlap(portalcoin, _player, getPCoin);
		FlxG.overlap(spikes,_player,doSpikeDamage);
		FlxG.overlap(portal,_player,win);
		FlxG.collide(skeleton,spikes);
		FlxG.collide(skeleton,level);
		FlxG.overlap(skeleton,_player,doBoneDamage);
		FlxG.collide(_bullets, skeleton, doSkeletonDamage);
		FlxG.collide(_tbullets, _player, doPlayerTDamage);
		FlxG.collide(level, _bullets);
		FlxG.collide(level, _tbullets);
		FlxG.collide(_bullets, spikes);
		FlxG.collide(level, _player);

	}

	@Override
	public void destroy()
	{
		super.destroy();
		Pausebtn = null;
		Settingsbtn = null;
		Exitbtn = null;
		Resumebtn = null;
		pauseblock = null;
		elevator = null;
		skeleton = null;
		pusher = null;
		level = null;
		portal = null;
		hearts = null;
		coins = null;
		portalcoin = null;
		portal = null;
		spikes = null;
		enemies = null;
		_bullets = null;
		status = null;
		goal = null;
		_player = null;
		_littleGibs = null;
		hearts = null;
		pad = null;
	}	

	private void onPause(){
		if(Pausebtn.status == FlxButton.PRESSED){		
			FlxG.paused = true;
			Pausebtn.exists = false;
			_player.exists = false;
			//	_player.immovable = true;
			//	_enemy.exists = false;
			pauseblock.exists = true;
			//	Resumebtn.x = _player.x;
			//	Resumebtn.y = _player.y - 20;
			Resumebtn.exists = true;
			//	Settingsbtn.x = _player.x;
			//	Settingsbtn.y = _player.y + 10;
			Settingsbtn.exists = true;
			//	Exitbtn.x = _player.x;
			//	Exitbtn.y = _player.y + 30;
			Exitbtn.exists = true;		
		}
	}

	private void onExit(){
		FlxG.switchState(new MenuState());
	}

	private void onSettings(){
		FlxG.switchState(new Settings());
	}

	private void onResume(){
		FlxG.paused = false;
		_player.exists = true;
		//	_enemy.exists = true;
		Pausebtn.exists = true;
		pauseblock.exists = false;
		Resumebtn.exists = false;
		Settingsbtn.exists = false;
		Exitbtn.exists = false;
	}

	IFlxCollision getCoin = new IFlxCollision()
	{
		@Override
		public void callback(FlxObject Coin, FlxObject Player)
		{						
	        Coin.kill();
			status.setText("SCORE: "+(coins.countDead()*100));	  		
			portalcoin.exists = true;
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


	IFlxCollision doSpikeDamage = new IFlxCollision()
	{
		@Override
		public void callback(FlxObject Spike, FlxObject Player)
		{			
			//add(new FlxText(200,22 , 100, "You Died!"));			
			_player.kill();
			//FlxG.music.kill();
	    }
	};		

	IFlxCollision doBoneDamage = new IFlxCollision()
	{
		@Override 
		public void callback(FlxObject Skeleton, FlxObject Player)
		{									
			//add(new FlxText(400,240 , 400, "You Died!"));			
			 _player.kill();
			//	FlxG.shake(0.05f,2);
		}
	};
	
	IFlxCollision doEnemyDamage = new IFlxCollision()
	{
		@Override 
		public void callback(FlxObject Enemy, FlxObject Bullet)
		{									
			//		add(new FlxText(_player.x,_player.y , 100, "Test: Enemy killed."));	
			//		EnemyG.kill();
			Enemy.kill();
		}
	};
	
	IFlxCollision doPlayerTDamage = new IFlxCollision()
	{
		@Override 
		public void callback(FlxObject TurretBullet, FlxObject Player)
		{									
			/*add(new FlxText(400,240 , 400, "You Died!"));			*/
			_player.kill();
			//	hurtplayer();
			//	FlxG.shake(0.05f,2);
		}
	};	

    IFlxCollision doPlayerDamage = new IFlxCollision()
	{
		@Override 
		public void callback(FlxObject Enemy, FlxObject Player)
		{									
			/*add(new FlxText(400,240 , 400, "You Died!"));		*/	
			 _player.kill();
			//	FlxG.shake(0.05f,2);
		}
	};	

	IFlxCollision doSkeletonDamage = new IFlxCollision()
	{
		@Override 
		public void callback(FlxObject Bullet, FlxObject Skeleton)
		{									
			//add(new FlxText(300,100 , 100, "Test: Enemy killed."));	
			skeleton.kill();
		}
	};	

	IFlxCollision win = new IFlxCollision()
	{
		@Override
		public void callback(FlxObject Portal, FlxObject Player)
		{
			_player.exists = false;
			FlxG.switchState(new PlayStateFive());
		}
	};	

}
