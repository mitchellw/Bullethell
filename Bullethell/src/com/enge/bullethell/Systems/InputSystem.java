package com.enge.bullethell.Systems;

import com.artemis.World;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.enge.bullethell.Bullethell;
import com.enge.bullethell.Vector2;
import com.enge.bullethell.Components.Bullet_Component;
import com.enge.bullethell.Components.Position_Component;
import com.enge.bullethell.Entities.BulletFactory_Entity;

public class InputSystem extends InputAdapter {
	private OrthographicCamera camera;
	private World world;
	
	public InputSystem (World world, OrthographicCamera camera) {
		this.world = world;
		this.camera = camera;
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		Vector3 unprojectedPosition = new Vector3(screenX, screenY, 0);
		camera.unproject(unprojectedPosition);
		Bullethell.player.getComponent(Position_Component.class).position = new Vector2(unprojectedPosition.x, unprojectedPosition.y);
		BulletFactory_Entity.createBullet(world, new Vector2(0, 2), Bullethell.player.getComponent(Position_Component.class).position,
				20, 40, Bullethell.player.getComponent(Bullet_Component.class).weaponType, 0);
		
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
