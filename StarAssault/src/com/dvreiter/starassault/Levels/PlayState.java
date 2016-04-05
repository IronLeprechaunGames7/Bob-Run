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
import org.apache.http.message.*;
import com.dvreiter.starassault.Player.*;
import com.dvreiter.starassault.*;
import com.dvreiter.starassault.Mobs.*;
import com.dvreiter.starassault.Objects.*;

public class PlayState extends FlxState
{	
	public FlxButton pause;
	public FlxTilemap level;
	public FlxTileblock block;
	public FlxSprite portal;	
	public FlxSprite hearts;
	public FlxGroup coins;
	public FlxSprite portalcoin;
	public FlxGroup spikes;	   
	public FlxText status;
	public FlxText goal;
	public FlxTileblock rightBorder, leftBorder;
	protected FlxEmitter _littleGibs;
	
	FlxVirtualPad pad;
	Player player;
	Enemy enemy1;
	FlxGroup _bullets;
    
	//int playershealth;
	//int hurtwait;
	
	@Override
	public void create()
	{						
		FlxG.setBgColor(0xFF00CCF);

	/*int[] data = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, 
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,               
			10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,
			10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,
			10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,
		};*/
		int[] data = {
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,	
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,                                         
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,4,1,1,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,4,1,1,5,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,4,8,8,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,// changed this
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			1,12,12,1,12,1,12,12,12,1,1,12,1,12,12,1,12,12,1,1,12,1,12,12,1,           
			7,18,7,18,18,7,18,7,18,7,18,18,7,7,18,7,18,18,18,7,7,18,18,7,18,
			10,10,21,21,10,21,21,10,21,10,21,21,10,21,21,10,21,21,10,21,10,21,10,21,21,
			10,21,10,21,10,21,10,21,10,21,10,10,10,21,10,21,10,21,10,21,21,10,21,21,10};
		

		level = new FlxTilemap();
		level.loadMap(FlxTilemap.arrayToCSV(new IntArray(data), 25), "tilemap.png", 16, 16); //changed thos
		level.setSolid(true);
		add(level);
		// 400x240						
 	
	//	playershealth = 4;
	//	hurtwait = 0;
		
		_littleGibs = new FlxEmitter();
		_littleGibs.setXSpeed(-150,150);
		_littleGibs.setYSpeed(-200,0);
		_littleGibs.setRotation(-720,-720);
		_littleGibs.gravity = 350;
		_littleGibs.bounce = 0.5f;
		_littleGibs.makeParticles("gibs.png",100,10,true,0.5f);
		
		portal = new FlxSprite(50, 50);//50
		portal.loadGraphic("portal.png", true, true, 16, 16);
		portal.addAnimation("spinning", new int[]{0, 1, 2}, 6, true);
		portal.play("spinning");
		portal.immovable = true;
		portal.exists = false;
		add(portal);
		
		portalcoin = new FlxSprite(170, 80);//80
		portalcoin.loadGraphic("Portalcoin.png", true, true, 16, 16);
		portalcoin.addAnimation("rotate", new int[]{0, 1, 2}, 4, true);
		portalcoin.play("rotate");
		add(portalcoin);
/*
		hearts = new FlxSprite(170,5);
		hearts.loadGraphic("untitled.png", true, true, 36, 8);
		hearts.addAnimation("4HP", new int[]{3}, 0, false);
		hearts.addAnimation("3HP", new int[]{2}, 0, false);
		hearts.addAnimation("2HP", new int[]{1}, 0, false);
		hearts.addAnimation("1HP", new int[]{0}, 0, false);		
		hearts.setSolid(false);
		hearts.immovable = true;
		hearts.scrollFactor.x = hearts.scrollFactor.y = 0;
		hearts.play("4HP");
*/
 		pad = new FlxVirtualPad(FlxVirtualPad.LEFT_RIGHT, FlxVirtualPad.A_B_X_Y);
		pad.setAlpha(0.5f);
		
		_bullets = new FlxGroup();
 
 		player = new Player(20,150,16,16,_bullets,_littleGibs,pad);
		player.setHasGravityToggle(false);
		player.setHasFlyingToggle(false);
		
		enemy1 = new Enemy(300,60,16,16,500); //y = 60		
		enemy1.followSprite(player);

		FlxG.camera.follow(player, FlxCamera.STYLE_PLATFORMER);
	    FlxG.camera.setBounds(0,0,400,240,true);// 1st 400,240  2nd 800,240, 3rd 1200,480//chnaged this
		FlxG.worldBounds.x = 0 ;
		FlxG.worldBounds.y = 0 ;
		FlxG.worldBounds.width = 499;//changed this
		FlxG.worldBounds.height = 240;
		
		rightBorder = new FlxTileblock(401,0,16,240);
		rightBorder.makeGraphic(16,240);
		add(rightBorder);//added this
		
		leftBorder = new FlxTileblock(-16,0,16,240);
		leftBorder.makeGraphic(16,240);
		add(leftBorder);
		
		coins = new FlxGroup(); 			
		//bottom coins	
		createCoin(105,115);//115

		add(coins);

		spikes = new FlxGroup();
		createSpike(80,128,0);//129
		createSpike(96,128,0);

		add(spikes);

		status = new FlxText(2,2,100, "SCORE: ");
		status.setShadow(0xff000000);
		status.setText("SCORE: "+(coins.countDead()*100));
		status.scrollFactor.x = 0;
		status.scrollFactor.y = 0;
		add(status);
		
		add(_bullets);
		add(player);					
		add(_littleGibs);
		add(enemy1);
	//	add(hearts);
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

	public void createSpike(int X, int Y, int Angle){
		Spike spike = new Spike(X,Y,Angle);
		spikes.add(spike);
	}
	/*public void hurtplayer(){ 
		hurtwait = 240;
		if(playershealth == 4){
			FlxG.flash(0xFFFFFFFF, 2);
			hearts.play("3HP");
			playershealth = 3;
		}
		else if(playershealth == 3){
			FlxG.flash(0xFFFFFFFF, 2);
			hearts.play("2HP");
			playershealth = 2;
		}
		else if(playershealth == 2){
			FlxG.flash(0xFFFFFFFF, 2);
			hearts.play("1HP");		
			playershealth = 1;
		}
		else if(playershealth == 1){
			FlxG.flash(0xFFFFFFFF, 2);
			player.kill();
			hearts.kill();
			
			FlxG.resetState();
		}
	}
*/
    @Override
	public void update(){	
						
		super.update();				
		FlxG.overlap(coins,player,getCoin);	
		FlxG.overlap(portalcoin,player,getPCoin);
		FlxG.overlap(spikes,player,doSpikeDamage);
		FlxG.overlap(portal,player,win);
		FlxG.collide(enemy1,spikes);
		FlxG.collide(enemy1,level);
		FlxG.collide(enemy1,player,doPlayerDamage);
		FlxG.collide(level,player);
		FlxG.collide(_bullets, enemy1, doEnemyDamage);
		FlxG.collide(level, _bullets);
		FlxG.collide(spikes, _bullets);
		
		FlxG.collide(player,rightBorder);// added this
		FlxG.collide(player,leftBorder);

		if(player.y > FlxG.height)
		{
			FlxG.resetState();
			//FlxG.music.kill();
			}
	/*	if(hurtwait != 0){
			hurtwait -= 1;
		}*/
	}

	protected void overlapped(FlxObject object1)
	{
		if((object1 instanceof Bullet))
		object1.hurt(1);
	}

	
	@Override
	public void destroy()
	{
		// TODO: Implement this method
		super.destroy();
		pause = null;
		level = null;
		block = null;
		portal = null;
		hearts = null;
		coins = null;
		portalcoin = null;
		portal = null;
		spikes = null;
		status = null;
		goal = null;
		player = null;
		_littleGibs = null; 
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
			status.setText("SCORE: "+(coins.countDead()*100));	  		
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
//			add(new FlxText(200,22 , 100, "You Died!"));			
			player.kill();
			//FlxG.music.kill();
	    }
	};		
		
    IFlxCollision doPlayerDamage = new IFlxCollision()
	{
		@Override 
		public void callback(FlxObject Enemy1, FlxObject Player)
		{									
	//		add(new FlxText(200,22 , 100, "You Died!"));			
			player.kill();
			//FlxG.music.kill();
	    }
	};		
	
	IFlxCollision win = new IFlxCollision()
	{
		@Override
		public void callback(FlxObject Portal, FlxObject Player)
		{
		//	add(new FlxText(200,22 , 100, "You win Level 1!"));			//22
			player.exists = false;
			FlxG.fade(0xff000000,1, onFade);
		//	FlxG.switchState(new PlaystateTwo());
		}
	};
	
	IFlxCollision doEnemyDamage = new IFlxCollision()
	{
		@Override 
		public void callback(FlxObject Enemy1, FlxObject Bullet)
		{									
			//		add(new FlxText(200,22 , 100, "You Died!"));			
//			player.kill();
	        enemy1.hurt(1);
			//FlxG.music.kill();
	    }
	};		
	
	public IFlxCamera onFade = new IFlxCamera()
	{
		@Override
		public void callback()
		{
			FlxG.switchState(new PlaystateTwo());
		}

	};
}
