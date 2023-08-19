package net.focaenterprises.zenith.entity;

import net.focaenterprises.zenith.game.IGameContext;
import net.focaenterprises.zenith.graphics.Sprite;
import net.focaenterprises.zenith.world.World;

public class PlayerEntity extends Entity {

  public PlayerEntity(IGameContext context, World world, int x, int y, Sprite sprite) {
    super(context, world, x, y, sprite);
  }
}
