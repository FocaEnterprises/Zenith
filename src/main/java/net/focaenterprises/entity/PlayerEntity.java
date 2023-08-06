package net.focaenterprises.entity;

import net.focaenterprises.input.Keyboard;
import net.focaenterprises.world.World;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

public class PlayerEntity extends Entity {
  private final Keyboard keyboard;

  public PlayerEntity(World world, int x, int y, BufferedImage sprite, Keyboard keyboard) {
    super(world, x, y, sprite);

    this.keyboard = keyboard;
  }

  @Override
  public void update() {
    int direction_x = 0;
    int direction_y = 0;

    if (keyboard.keyboard_check(KeyEvent.VK_W)) {
      direction_y -= 1;
    }

    if (keyboard.keyboard_check(KeyEvent.VK_S)) {
      direction_y += 1;
    }

    if (keyboard.keyboard_check(KeyEvent.VK_D)) {
      direction_x += 1;
    }

    if (keyboard.keyboard_check(KeyEvent.VK_A)) {
      direction_x -= 1;
    }

    x += direction_x;
    y += direction_y;
  }
}
