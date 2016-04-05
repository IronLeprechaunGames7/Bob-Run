package com.dvreiter.starassault.Levels;	

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
import com.dvreiter.starassault.*;
import com.dvreiter.starassault.Mobs.*;
import com.dvreiter.starassault.Objects.*;

public class PlaystateTwo extends FlxState
{	
	public FlxButton pause;
	public FlxTilemap level;
	public FlxSprite portal;
	public FlxSprite hearts;
	public FlxGroup coins;
	public FlxSprite portalcoin;
	public FlxGroup spikes;	   
	public FlxText status;
	public FlxText goal;
	protected FlxEmitter _littleGibs;
	
	FlxVirtualPad pad;
	Player player;
	Enemy enemy1;
	FlxGroup _bullets;

	@Override
	public void create()
	{						
		FlxG.setBgColor(0xFF00CCFF);
		FlxG.mouse.show();

		int[] data = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,4,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,4,1,5,0,4,1,5,0,0,4,1,5,0,0,4,1,5,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,6,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,6,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,6,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,6,0,0,0,0,
			1,12,1,8,1,12,8,8,1,8,8,1,8,8,12,8,8,1,1,1,9,12,1,12,1,               
			7,18,7,18,18,7,18,7,18,7,18,18,7,7,18,7,18,18,18,7,7,18,18,7,18,
			10,10,21,21,10,21,21,10,21,10,21,21,10,21,21,10,21,21,10,21,10,21,10,21,21,
			10,21,10,21,10,21,10,21,10,21,10,10,10,21,10,21,10,21,10,21,21,10,21,21,10};
		

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
		
		portal = new FlxSprite(50, 50);
		portal.loadGraphic("portal.png", true, true, 16, 16);
		portal.addAnimation("spinning", new int[]{0, 1, 2}, 6, true);
		portal.play("spinning");
		portal.immovable = true;
		portal.exists = false;
		add(portal);	
		
		portalcoin = new FlxSprite(240,60);
		portalcoin.loadGraphic("Portalcoin.png", true, true, 16, 16);
		portalcoin.addAnimation("rotate", new int[]{0, 1, 2}, 4, true);
		portalcoin.play("rotate");
		add(portalcoin);		
//300,75 for free
//380,160 for prison
		pad = new FlxVirtualPad(FlxVirtualPad.LEFT_RIGHT, FlxVirtualPad.A_B_X_Y);
		pad.setAlpha(0.5f);
		
		_bullets = new FlxGroup();

		player = new Player(20,150,16,16,_bullets,_littleGibs,pad);
		player.setHasGravityToggle(false);
		player.setHasFlyingToggle(false);

		enemy1 = new Enemy(300,75,16,16,500); 		
		enemy1.followSprite(player);		
		
		coins = new FlxGroup(); 			
		//bottom coins	
		createCoin(75,150);
		createCoin(125,150);
		createCoin(175,150);				
		createCoin(225,150);
		createCoin(275,150);
		add(coins);

		spikes = new FlxGroup();
		createSpike(50,160,0);
		createSpike(100,160,0);
		createSpike(150,160,0);
		createSpike(200,160,0);
		createSpike(250,160,0);
		add(spikes);

		status = new FlxText(2,2,100, "SCORE: ");
		status.setShadow(0xff000000);
		status.setText("SCORE: "+(coins.countDead()*100));
		
		add(status);
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
		coin.addAnimation("rotating", new int[]{0, 1, 2}, 4, true);
		coin.play("rotating");
		coins.add(coin);
	}	

	public void createSpike(int X, int Y,int Angle){
		Spike spike = new Spike(X,Y,Angle);
		spikes.add(spike);
	}
	
    @Override
	public void update()
	{		
		super.update();
		FlxG.overlap(coins,player,getCoin);	
		FlxG.overlap(spikes,player,doSpikeDamage);
		FlxG.overlap(portalcoin,player,getPCoin);
		FlxG.overlap(portal,player,win);
		FlxG.collide(enemy1,level);
		FlxG.collide(enemy1,spikes);
		FlxG.collide(enemy1,player,doPlayerDamage);
		FlxG.collide(level,player);
		FlxG.collide(_bullets, enemy1, doEnemyDamage);
		FlxG.collide(level, _bullets);
		FlxG.collide(spikes, _bullets);

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
		status = null;
		goal = null;
		player = null;
		enemy1 = null;
		pad = null;
	}

	IFlxCollision getPCoin = new IFlxCollision()
	{
		@Override
		public void callback(FlxObject Portalcoin, FlxObject Player)
		{						
	        Portalcoin.kill();
			portal.exists = true;			        
	    }
	};					
	
	IFlxCollision getCoin = new IFlxCollision()
	{
		@Override
		public void callback(FlxObject Coin, FlxObject Player)
		{						
	        Coin.kill();
			status.setText("SCORE: "+(coins.countDead()*100));	  					        
	    }
	};					

	IFlxCollision doSpikeDamage = new IFlxCollision()
	{
		@Override
		public void callback(FlxObject Spike, FlxObject Player)
		{						
	  //      add(new FlxText(200,22 , 100, "You Died!"));			
			player.kill();
	    }
	};

	IFlxCollision doPlayerDamage = new IFlxCollision()
	{
		@Override
		public void callback(FlxObject Enemy1, FlxObject Player)
		{						
	    //    add(new FlxText(200,22 , 100, "You Died!"));			
			
			player.kill();
	    }
	};
	
	IFlxCollision win = new IFlxCollision()
	{
		@Override
		public void callback(FlxObject Exit, FlxObject Player)
		{
			add(new FlxText(200,22 , 100, "You win Level 2!"));			
			player.exists = false;
			FlxG.fade(0xff000000,1, onFade);
			//FlxG.switchState(new PlaystateThree());
		}
	};
	
	IFlxCollision doEnemyDamage = new IFlxCollision()
	{
		@Override
		public void callback(FlxObject Enemy1, FlxObject Bullet)
		{
			enemy1.hurt(1);
		}
	};
	
	public IFlxCamera onFade = new IFlxCamera()
	{
		@Override
		public void callback()
		{
			FlxG.switchState(new PlaystateThree());
		}

	};
}
       	
