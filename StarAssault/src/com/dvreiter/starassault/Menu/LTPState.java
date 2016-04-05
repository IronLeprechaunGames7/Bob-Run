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
import org.flixel.ui.event.*;
import com.dvreiter.starassault.*;

public class LTPState extends FlxState
{		 
	private FlxText Title;
    private FlxButton backbtn;
	private FlxButton Linkbtn;
	
	@Override
	public void create()
	{			
		FlxG.setBgColor(0xFF00CCFF);
// 5,5
		backbtn = new FlxButton(5, 215, "Back", new IFlxButton(){@Override public void callback(){onBack();}});
		add(backbtn);		
		
		// Tab group that holds the tabs and set the tab alignment vertical.
		FlxTabGroup tabGroup = new FlxTabGroup(0, 0);
		tabGroup.align = FlxUIGroup.ALIGN_HORIZONTAL;
		add(tabGroup);

		// Create bunch of tabs.
		tabGroup.addTab(new FlxTab(null, "Controls"));
		tabGroup.addTab(new FlxTab(null, "Story"));
		tabGroup.addTab(new FlxTab(null, "Credits"));
	//	tabGroup.addTab(new FlxTab(null, "Settings"));
		tabGroup.loadDividerSkin();
		tabGroup.setDefault(0);

		// Add content
		tabGroup.addContent(0, new FlxText(80, 50, FlxG.width-80, "Press A To Jump.\n Press Left or right button to move to the left or right.\n B To Restart Level(Debating to keep)."));
		tabGroup.addContent(1, new FlxText(80, 50, FlxG.width-80, "Bob has been sent on a mission to recover the lost ancient artifact of The Lightning Sword.Bob must go through different parts of the world and fight enemys in his path."));
		tabGroup.addContent(2, new FlxText(80, 50, FlxG.width-80, "MCPEGAMER - HEAD DEVELOPER "));
	//	tabGroup.addContent(3, new FlxSwitch(156, 398, "O", null, "Debug Mode"));
		
		
	//	add(new FlxText(150, 30, 100, "How to play:\n Press A To Jump.\n Press Left button to move left.\n Press Right button to move right.\n Story: Bob has been sent on a mission to recover the lost ancient artifact of The Lightning Sword.Bob must go through different parts of the world and fight enemys in his path."));
	
	}
    @Override
	public void update(){	

		super.update();}

	private void onBack(){
		FlxG.switchState(new MenuState());
	}
}
