package net.focaenterprises.zenith.ecs.component;

import net.focaenterprises.zenith.graphics.Sprite;

public class SpriteComponent implements IRenderingComponent {
  private Sprite sprite;
  private final int depth;

  public SpriteComponent(Sprite sprite, int depth) {
    this.sprite = sprite;
    this.depth = depth;
  }

  public Sprite getSprite() {
    return sprite;
  }

  @Override
  public int getDepth() {
    return depth;
  }
}
