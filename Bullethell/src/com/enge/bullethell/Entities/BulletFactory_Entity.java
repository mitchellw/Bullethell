package com.enge.bullethell.Entities;

import com.enge.bullethell.Bullethell;
import com.enge.bullethell.Owner;
import com.artemis.Entity;
import com.artemis.World;
import com.enge.bullethell.Vector2;
import com.enge.bullethell.Components.Bullet_Component;
import com.enge.bullethell.Components.Hitbox_Component;
import com.enge.bullethell.Components.Owner_Component;
import com.enge.bullethell.Components.Position_Component;
import com.enge.bullethell.Components.Sprite_Component;
import com.enge.bullethell.Components.Velocity_Component;

/**
 * 
 * @author All Members
 * @version 2013.04.23
 *
 */
public class BulletFactory_Entity {

	public static Entity createBullet(World world, Vector2 velocity, Vector2 position, int width, int height, int bulletType, Owner owner)
	{
		Entity bullet = world.createEntity();
		bullet.addComponent(new Velocity_Component(velocity));
		bullet.addComponent(new Position_Component(position));
		bullet.addComponent(new Hitbox_Component(width, height));
		switch (bulletType) {
			case 0:
				bullet.addComponent(new Sprite_Component("bullet"));
				bullet.addComponent(new Bullet_Component(bulletType, 250));
				break;
			case 1:
				bullet.addComponent(new Sprite_Component("bullet"));
				bullet.addComponent(new Bullet_Component(bulletType, 300));
				break;
			default:
				bullet.addComponent(new Sprite_Component("bullet"));
				bullet.addComponent(new Bullet_Component(bulletType, 250));
				break;
		}
		bullet.addComponent(new Owner_Component(owner));

		bullet.addToWorld();
		return bullet;
	}

	/**
	 * Method adds bullets to the world when called.
	 * @param world
	 */
	public static void playerFire(World world) {
	    // TODO: switch on bullet type?
		switch (Bullethell.player.getComponent(Bullet_Component.class).weaponType) {
			case 0:
				createBullet(world, new Vector2(0f, 8f), Bullethell.player.getComponent(Position_Component.class).position,
					20, 20, Bullethell.player.getComponent(Bullet_Component.class).weaponType, Owner.HUMAN);
				break;
			case 1:
				createBullet(world, new Vector2(0f, 8f), Bullethell.player.getComponent(Position_Component.class).position,
					20, 20, Bullethell.player.getComponent(Bullet_Component.class).weaponType, Owner.HUMAN);
				createBullet(world, new Vector2(4.899f, 6.325f), Bullethell.player.getComponent(Position_Component.class).position,
					20, 20, Bullethell.player.getComponent(Bullet_Component.class).weaponType, Owner.HUMAN);
				createBullet(world, new Vector2(-4.899f, 6.325f), Bullethell.player.getComponent(Position_Component.class).position,
					20, 20, Bullethell.player.getComponent(Bullet_Component.class).weaponType, Owner.HUMAN);
			default:
				break;
		}	}
}
