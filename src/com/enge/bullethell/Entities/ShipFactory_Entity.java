package com.enge.bullethell.Entities;

import com.artemis.Entity;
import com.artemis.World;
import com.enge.bullethell.Components.Bullet_Component;
import com.enge.bullethell.Components.Health_Component;
import com.enge.bullethell.Components.Hitbox_Component;
import com.enge.bullethell.Components.Position_Component;
import com.enge.bullethell.Components.Velocity_Component;

public class ShipFactory_Entity 
{
	public static void createShip(World world)
    {
        Entity player = world.createEntity();
        player.addComponent(new Bullet_Component());
        player.addComponent(new Velocity_Component());
        player.addComponent(new Position_Component());
        player.addComponent(new Health_Component());
        player.addComponent(new Hitbox_Component());
        
		world.addEntity(player);
    }
}
