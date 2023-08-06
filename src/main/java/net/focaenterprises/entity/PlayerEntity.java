package net.focaenterprises.entity;

import net.focaenterprises.world.World;

import java.awt.image.BufferedImage;
import java.util.Random;

public class PlayerEntity extends Entity {
  public PlayerEntity(World world, int x, int y, BufferedImage sprite) {
    super(world, x, y, sprite);
  }

  @Override
  public void update() {
    Random random = new Random();
    int mx = random.nextInt(2);
    int my = random.nextInt(2);

    if(random.nextBoolean()) {
      mx *= -1;
    }

    if(random.nextBoolean()) {
      my *= -1;
    }

    x += mx;
    y += my;
  }
}
