package com.enge.bullethell.Systems;

import com.enge.bullethell.Entities.BulletFactory_Entity;
import com.artemis.systems.VoidEntitySystem;

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
    public static boolean firing;

    public PlayerFire_System() {
        lastFired = System.currentTimeMillis();
        firing = false;
    }

    @Override
    protected void processSystem()
    {
        if (firing && System.currentTimeMillis() - lastFired > 250) {
            lastFired = System.currentTimeMillis();

            BulletFactory_Entity.playerFire(world);
        }
    }
}
