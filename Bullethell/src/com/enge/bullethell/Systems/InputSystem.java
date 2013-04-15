package com.enge.bullethell.Systems;

import com.badlogic.gdx.InputAdapter;
import com.enge.bullethell.Bullethell;
import com.enge.bullethell.Vector2;
import com.enge.bullethell.Components.Position_Component;

public class InputSystem extends InputAdapter {
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		Bullethell.player.getComponent(Position_Component.class).position = new Vector2(screenX, screenY);
		// TODO Auto-generated method stub
		return super.touchDown(screenX, screenY, pointer, button);
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		Bullethell.player.getComponent(Position_Component.class).position = new Vector2(screenX, screenY);
		// TODO Auto-generated method stub
		return super.touchDragged(screenX, screenY, pointer);
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return super.touchUp(screenX, screenY, pointer, button);
	}

	public InputSystem() {
		
	}

}
