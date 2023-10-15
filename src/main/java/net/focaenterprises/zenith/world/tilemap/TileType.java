package net.focaenterprises.zenith.world.tilemap;

import net.focaenterprises.zenith.graphics.Renderer;
import net.focaenterprises.zenith.graphics.SpriteSheet;

public class TileType {
  private final String name;
  private final boolean solid;

  TileType(String name, boolean isSolid) {
    this.name = name;
    this.solid = isSolid;
  }

  public void render(Renderer r, int x, int y, SpriteSheet spriteSheet) {
    r.setDepth(Integer.MAX_VALUE);
    spriteSheet.getSprite(name).render(r, x, y);
  }

  public boolean isSolid() {
    return solid;
  }

  public String getName() {
    return name;
  }
}
