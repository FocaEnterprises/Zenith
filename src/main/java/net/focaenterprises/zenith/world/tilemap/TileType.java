package net.focaenterprises.zenith.world.tilemap;

import net.focaenterprises.zenith.graphics.Sprite;

import java.awt.Graphics;

public class TileType {
  private final Sprite sprite;
  private final boolean solid;

  TileType(Sprite sprite, boolean isSolid) {
    this.sprite = sprite;
    this.solid = isSolid;
  }

  public void render(Graphics graphics, int x, int y) {
    sprite.render(graphics, x, y);
  }

  public boolean isSolid() {
    return solid;
  }
}
