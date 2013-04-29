package com.enge.bullethell.Systems;

import com.enge.bullethell.Components.Velocity_Component;
import com.artemis.World;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.enge.bullethell.Bullethell;
import com.enge.bullethell.GameState;
import com.enge.bullethell.Vector2;
import com.enge.bullethell.Components.Destination_Component;
import com.enge.bullethell.Entities.ShipFactory_Entity;

/**
 * System that handles input on the screen from the user.
 * @author Tyler
 * @version 29.04.2013
 */
public class InputSystem extends InputAdapter {
	private OrthographicCamera camera;
	private World world;

	/**
	 * Constructor for the input system class.
	 * @param world 
	 * @param camera
	 */
	public InputSystem (World world, OrthographicCamera camera) {
		this.world = world;
		this.camera = camera;
	}
	
	/**
	 * Returns true if the screen is being pressed.
	 * @param screenX
	 * @param screenY
	 * @param pointer
	 * @param button
	 */
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if (Bullethell.gameState == GameState.PLAYING) {
			Vector3 unprojectedPos3 = new Vector3(screenX, screenY, 0);
			camera.unproject(unprojectedPos3);

			Bullethell.player.getComponent(Destination_Component.class).destination = new Vector2(unprojectedPos3.x, unprojectedPos3.y);

			PlayerFire_System.firing = true;
		}

		return true;
	}

	/**
	 * Returns true if the input finger is being dragged across
	 * the screen
	 * @param screenX
	 * @param screenY
	 * @param pointer
	 */
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		if (Bullethell.gameState == GameState.PLAYING) {
			Vector3 unprojectedPos3 = new Vector3(screenX, screenY, 0);
			camera.unproject(unprojectedPos3);

			Bullethell.player.getComponent(Destination_Component.class).destination = new Vector2(unprojectedPos3.x, unprojectedPos3.y);
		}

		return true;
	}

	/**
	 * Returns true if the screen is not being pressed down.
	 * @param screenX
	 * @param screenY
	 * @param pointer
	 * @param button
	 */
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (Bullethell.gameState == GameState.PLAYING) {
			Bullethell.player.getComponent(Velocity_Component.class).velocity = Vector2.Zero;
			Bullethell.player.getComponent(Destination_Component.class).destination = null;
			PlayerFire_System.firing = false;
		}
		else {
			PlayerFire_System.firing = false;
			Bullethell.gameState = GameState.PLAYING;
			Bullethell.splashScreen.deleteFromWorld();
			Bullethell.player = ShipFactory_Entity.createPlayer(world, new Vector2(0, 0), 0);
			Bullethell.scoreEntity.addToWorld();
			world.setSystem(Bullethell.spawnSystem);
		}

		return true;
	}

}
