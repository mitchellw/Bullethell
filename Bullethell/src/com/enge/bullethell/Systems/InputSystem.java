package com.enge.bullethell.Systems;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.enge.bullethell.Bullethell;
import com.enge.bullethell.Vector2;
import com.enge.bullethell.Components.Position_Component;

public class InputSystem extends InputAdapter {
	private OrthographicCamera camera;
	
	public InputSystem (OrthographicCamera camera) {
		this.camera = camera;
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		Vector3 unprojectedPosition = new Vector3(screenX, screenY, 0);
		camera.unproject(unprojectedPosition);
		Bullethell.player.getComponent(Position_Component.class).position = new Vector2(unprojectedPosition.x, unprojectedPosition.y);
		
		return super.touchDown(screenX, screenY, pointer, button);
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		Vector3 unprojectedPosition = new Vector3(screenX, screenY, 0);
		camera.unproject(unprojectedPosition);
		Bullethell.player.getComponent(Position_Component.class).position = new Vector2(unprojectedPosition.x, unprojectedPosition.y);
	
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
