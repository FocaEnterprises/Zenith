package net.focaenterprises.zenith.entity;

import net.focaenterprises.zenith.world.World;

import java.awt.image.BufferedImage;

public class PlayerEntity extends Entity {

  public PlayerEntity(World world, int x, int y, BufferedImage sprite) {
    super(world, x, y, sprite);
  }
}
