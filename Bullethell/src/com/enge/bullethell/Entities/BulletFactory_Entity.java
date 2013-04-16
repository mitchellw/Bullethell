package com.enge.bullethell.Entities;

import com.artemis.Entity;
import com.artemis.World;
import com.enge.bullethell.Vector2;
import com.enge.bullethell.Components.Bullet_Component;
import com.enge.bullethell.Components.Hitbox_Component;
import com.enge.bullethell.Components.Owner_Component;
import com.enge.bullethell.Components.Position_Component;
import com.enge.bullethell.Components.Sprite_Component;
import com.enge.bullethell.Components.Velocity_Component;

public class BulletFactory_Entity {

	public static Entity createBullet(World world, Vector2 velocity, Vector2 position, int width, int height, int bulletType, int owner)
	{
		Entity bullet = world.createEntity();
		bullet.addComponent(new Velocity_Component());
		bullet.addComponent(new Position_Component(position));
		bullet.addComponent(new Hitbox_Component(width, height));
		if (bulletType == 0) {
			bullet.addComponent(new Sprite_Component("bullet"));
		}
		bullet.addComponent(new Bullet_Component(bulletType));
		bullet.addComponent(new Owner_Component(0));
		
		bullet.addToWorld();
		return bullet;
	}
	
}
