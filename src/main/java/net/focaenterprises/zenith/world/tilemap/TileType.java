package net.focaenterprises.zenith.world.tilemap;

import net.focaenterprises.zenith.graphics.Renderer;
import net.focaenterprises.zenith.graphics.Sprite;

import java.awt.Graphics;

public class TileType {
  private final Sprite sprite;
  private final boolean solid;

  TileType(Sprite sprite, boolean isSolid) {
    this.sprite = sprite;
    this.solid = isSolid;
  }

  public void render(Renderer r, int x, int y) {
    r.setDepth(Integer.MAX_VALUE);
    sprite.render(r, x, y);
  }

  public boolean isSolid() {
    return solid;
  }
}
