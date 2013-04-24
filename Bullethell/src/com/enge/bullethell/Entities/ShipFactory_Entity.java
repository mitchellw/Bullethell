package com.enge.bullethell.Entities;

import com.artemis.Entity;
import com.artemis.World;
import com.enge.bullethell.Owner;
import com.enge.bullethell.Vector2;
import com.enge.bullethell.Components.Bullet_Component;
import com.enge.bullethell.Components.Destination_Component;
import com.enge.bullethell.Components.Health_Component;
import com.enge.bullethell.Components.Hitbox_Component;
import com.enge.bullethell.Components.Owner_Component;
import com.enge.bullethell.Components.Position_Component;
import com.enge.bullethell.Components.Score_Component;
import com.enge.bullethell.Components.Sprite_Component;
import com.enge.bullethell.Components.Velocity_Component;

public class ShipFactory_Entity
{
    /**
     * The linear velocity of a player ship.
     */
    public static final float PLAYER_VELOCITY = 5;

	public static Entity createShip(World world, Vector2 velocity, Vector2 position, String spriteName,
			int width, int height, int bulletType, int health, int score, Owner owner, Vector2 destination)
    {
        Entity ship = world.createEntity();
        ship.addComponent(new Sprite_Component(spriteName));
        ship.addComponent(new Velocity_Component(velocity));
        ship.addComponent(new Position_Component(position));
        ship.addComponent(new Hitbox_Component(width, height));
        ship.addComponent(new Bullet_Component(bulletType));
        ship.addComponent(new Health_Component(health));
        ship.addComponent(new Score_Component(score));
        ship.addComponent(new Owner_Component(owner));
        ship.addComponent(new Destination_Component(destination));

		ship.addToWorld();
		return ship;
    }

	public static Entity createPlayer(World world, Vector2 position, int bulletType) {
		return createShip(world, Vector2.Zero, position, "player", 64 ,64, bulletType, 1, 0, Owner.HUMAN, null);
	}

	public static Entity createEnemy1(World world, Vector2 position, Vector2 velocity, int bulletType, Vector2 destination) {
		return createShip(world, velocity, position, "enemy", 64, 64, bulletType, 1, 100, Owner.COMPUTER, destination);
	}
}
