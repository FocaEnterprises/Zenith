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
    int xNext = x;
    int yNext = y;

    int xDirection = 0;
    int yDirection = 0;

    if (input.keyboard_check(KeyEvent.VK_W)) {
      yDirection -= 1;
    }

    if (input.keyboard_check(KeyEvent.VK_S)) {
      yDirection += 1;
    }

    if (input.keyboard_check(KeyEvent.VK_D)) {
      xDirection += 1;
    }

    if (input.keyboard_check(KeyEvent.VK_A)) {
      xDirection -= 1;
    }

    xNext += xDirection;

    if (!world.getTilemap().isColliding(xNext, y, 15, 15)) {
      player.setX(xNext);
    }

    yNext += yDirection;

    if (!world.getTilemap().isColliding(x, yNext, 15, 15)) {
      player.setY(yNext);
    }
  }
}
