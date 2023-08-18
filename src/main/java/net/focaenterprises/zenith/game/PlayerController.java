package net.focaenterprises.zenith.game;

import net.focaenterprises.zenith.entity.PlayerEntity;
import net.focaenterprises.zenith.input.Keyboard;
import net.focaenterprises.zenith.world.World;

import java.awt.event.KeyEvent;

public class PlayerController {
  private final PlayerEntity player;
  private final Keyboard input;

  public PlayerController(Keyboard input, PlayerEntity player) {
    this.player = player;
    this.input = input;
  }

  public void update() {
    World world = player.getWorld();

    int x = player.getX();
    int y = player.getY();
    int newX = x;
    int newY = y;

    int direction_x = 0;
    int direction_y = 0;

    if (input.keyboard_check(KeyEvent.VK_W)) {
      direction_y -= 1;
    }

    if (input.keyboard_check(KeyEvent.VK_S)) {
      direction_y += 1;
    }

    if (input.keyboard_check(KeyEvent.VK_D)) {
      direction_x += 1;
    }

    if (input.keyboard_check(KeyEvent.VK_A)) {
      direction_x -= 1;
    }

    newX += direction_x;

    if (!world.getTilemap().isColliding(newX, y, 15, 15)) {
      player.setX(newX);
    }

    newY += direction_y;

    if (!world.getTilemap().isColliding(x, newY, 15, 15)) {
      player.setY(newY);
    }
  }
}
