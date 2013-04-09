package com.enge.bullethell.Entities;

import com.artemis.Entity;
import com.artemis.World;
import com.enge.bullethell.Vector2;
import com.enge.bullethell.Components.Bullet_Component;
import com.enge.bullethell.Components.Health_Component;
import com.enge.bullethell.Components.Hitbox_Component;
import com.enge.bullethell.Components.Position_Component;
import com.enge.bullethell.Components.Velocity_Component;

public class ShipFactory_Entity
{
	public static void createShip(World world, float velocity, Vector2 position, int width, int height, int bulletType, int health)
    {
        Entity ship = world.createEntity();
        ship.addComponent(new Velocity_Component());
        ship.addComponent(new Position_Component(position));
        ship.addComponent(new Hitbox_Component(width, height));
        ship.addComponent(new Bullet_Component(bulletType));
        ship.addComponent(new Health_Component(health));
        
		world.addEntity(ship);
    }
}
