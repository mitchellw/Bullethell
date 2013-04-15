package com.enge.bullethell.Entities;

import com.artemis.Entity;
import com.artemis.World;
import com.enge.bullethell.Vector2;
import com.enge.bullethell.Components.Bullet_Component;
import com.enge.bullethell.Components.Health_Component;
import com.enge.bullethell.Components.Hitbox_Component;
import com.enge.bullethell.Components.Position_Component;
import com.enge.bullethell.Components.Sprite_Component;
import com.enge.bullethell.Components.Velocity_Component;

public class ShipFactory_Entity
{
	public static Entity createShip(World world, Vector2 velocity, Vector2 position, String spriteName,
			int width, int height, int bulletType, int health)
    {
        Entity ship = world.createEntity();
        ship.addComponent(new Sprite_Component(spriteName));
        ship.addComponent(new Velocity_Component(velocity));
        ship.addComponent(new Position_Component(position));
        ship.addComponent(new Hitbox_Component(width, height));
        ship.addComponent(new Bullet_Component(bulletType));
        ship.addComponent(new Health_Component(health));
        
		ship.addToWorld();
		return ship;
    }
}
