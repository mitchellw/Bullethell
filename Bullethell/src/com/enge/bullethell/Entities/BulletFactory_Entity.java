package com.enge.bullethell.Entities;

import com.artemis.Entity;
import com.artemis.World;
import com.enge.bullethell.Vector2;
import com.enge.bullethell.Components.Bullet_Component;
import com.enge.bullethell.Components.Hitbox_Component;
import com.enge.bullethell.Components.Position_Component;
import com.enge.bullethell.Components.Velocity_Component;

public class BulletFactory_Entity {

	public static void createBullet(World world, float velocity, Vector2 position, int width, int height, int bulletType)
	{
		Entity bullet = world.createEntity();
		bullet.addComponent(new Velocity_Component());
		bullet.addComponent(new Position_Component(position));
		bullet.addComponent(new Hitbox_Component(width, height));
		bullet.addComponent(new Bullet_Component(bulletType));
		
		world.addEntity(bullet);
	}
	
}
