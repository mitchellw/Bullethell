package com.enge.bullethell.Systems;

import com.enge.bullethell.Bullethell;
import com.enge.bullethell.GameState;
import com.enge.bullethell.Entities.BulletFactory_Entity;
import com.artemis.systems.VoidEntitySystem;
import com.badlogic.gdx.audio.Sound;

// -------------------------------------------------------------------------
/**
 *  The system that handles the player firing bullets in the World.
 *
 *  @author Wilson
 *  @version Apr 22, 2013
 */
public class PlayerFire_System extends VoidEntitySystem
{
    private long lastFired;
    public static boolean firing;
    private Sound fire;

    public PlayerFire_System(Sound fire) {
        lastFired = System.currentTimeMillis();
        firing = false;
        this.fire = fire;
    }

    /**
     * Fires a bullet every 200 milliseconds.
     */
    @Override
    protected void processSystem()
    {
    	if (Bullethell.gameState == GameState.PLAYING) {
    		if (firing && System.currentTimeMillis() - lastFired > 250) {
    			lastFired = System.currentTimeMillis();

    			BulletFactory_Entity.playerFire(world);
    			fire.play(0.6f); //Plays lasergun_fire.wav
    		}
    	}
    }
}
