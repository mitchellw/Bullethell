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
		Vector2 playerPosition = Bullethell.player.getComponent(Position_Component.class).position;
		Vector2 unprojectedPos2 = new Vector2(unprojectedPos3.x, unprojectedPos3.y);
		Vector2 velocity = unprojectedPos2.sub(playerPosition).unit().mul(ShipFactory_Entity.PLAYER_VELOCITY);

		if (unprojectedPos2.sub(velocity).len() > ShipFactory_Entity.PLAYER_VELOCITY) {
		    Bullethell.player.getComponent(Velocity_Component.class).velocity = velocity;
		}
		else {
		    Bullethell.player.getComponent(Velocity_Component.class).velocity = Vector2.Zero;
		    Bullethell.player.getComponent(Position_Component.class).position = unprojectedPos2;
		}

		world.getSystem(PlayerFire_System.class).firing = true;

		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
       Vector3 unprojectedPos3 = new Vector3(screenX, screenY, 0);
        camera.unproject(unprojectedPos3);
        Vector2 playerPosition = Bullethell.player.getComponent(Position_Component.class).position;
        Vector2 unprojectedPos2 = new Vector2(unprojectedPos3.x, unprojectedPos3.y);
        Vector2 velocity = unprojectedPos2.sub(playerPosition).unit().mul(ShipFactory_Entity.PLAYER_VELOCITY);

        if (unprojectedPos2.sub(velocity).len() > ShipFactory_Entity.PLAYER_VELOCITY) {
            Bullethell.player.getComponent(Velocity_Component.class).velocity = velocity;
        }
        else {
            Bullethell.player.getComponent(Velocity_Component.class).velocity = Vector2.Zero;
            Bullethell.player.getComponent(Position_Component.class).position = unprojectedPos2;
	    }

		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		Bullethell.player.getComponent(Velocity_Component.class).velocity = Vector2.Zero;
		world.getSystem(PlayerFire_System.class).firing = false;

		return true;
	}

}
