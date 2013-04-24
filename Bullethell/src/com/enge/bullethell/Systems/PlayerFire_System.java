package com.enge.bullethell.Systems;

import com.enge.bullethell.Entities.BulletFactory_Entity;
import com.artemis.systems.VoidEntitySystem;
import com.badlogic.gdx.audio.Sound;

// -------------------------------------------------------------------------
/**
 *  Write a one-sentence summary of your class here.
 *  Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 *
 *  @author Wilson
 *  @version Apr 22, 2013
 */
public class PlayerFire_System extends VoidEntitySystem
{
    private long lastFired;
<<<<<<< HEAD
    public static boolean firing;
    private Sound fire;
=======
    public boolean firing;
>>>>>>> db4cad9dd96b0004f54e9d317a2ada0dd8cf1dd1

    public PlayerFire_System(Sound fire) {
        lastFired = System.currentTimeMillis();
        firing = false;
        this.fire = fire;
    }

    @Override
    protected void processSystem()
    {
        if (firing && System.currentTimeMillis() - lastFired > 250) {
            lastFired = System.currentTimeMillis();

            BulletFactory_Entity.playerFire(world);
            fire.play();
        }
    }
}
