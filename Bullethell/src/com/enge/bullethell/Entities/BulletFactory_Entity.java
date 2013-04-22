package com.enge.bullethell.Entities;

import com.enge.bullethell.Bullethell;
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
		bullet.addComponent(new Velocity_Component(velocity));
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

	public static void playerFire(World world) {
	    // TODO: switch on bullet type?
	    createBullet(world, new Vector2(0, 4), Bullethell.player.getComponent(Position_Component.class).position,
			20, 40, Bullethell.player.getComponent(Bullet_Component.class).weaponType, 1);	}
}
