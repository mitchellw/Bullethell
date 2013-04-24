package com.enge.bullethell.Systems;

import com.enge.bullethell.Entities.ShipFactory_Entity;
import com.enge.bullethell.Components.Velocity_Component;
import com.artemis.World;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.enge.bullethell.Bullethell;
import com.enge.bullethell.Vector2;
import com.enge.bullethell.Components.Bullet_Component;
import com.enge.bullethell.Components.Destination_Component;
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
		Vector3 unprojectedPos3 = new Vector3(screenX, screenY, 0);
		camera.unproject(unprojectedPos3);
		
		Bullethell.player.getComponent(Destination_Component.class).destination = new Vector2(unprojectedPos3.x, unprojectedPos3.y);

		world.getSystem(PlayerFire_System.class).firing = true;

		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		Vector3 unprojectedPos3 = new Vector3(screenX, screenY, 0);
		camera.unproject(unprojectedPos3);
		
		Bullethell.player.getComponent(Destination_Component.class).destination = new Vector2(unprojectedPos3.x, unprojectedPos3.y);

		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		Bullethell.player.getComponent(Velocity_Component.class).velocity = Vector2.Zero;
		Bullethell.player.getComponent(Destination_Component.class).destination = null;
		world.getSystem(PlayerFire_System.class).firing = false;

		return true;
	}

}
