package net.focaenterprises.zenith.ecs.system;


import net.focaenterprises.zenith.graphics.Renderer;

public abstract class AbstractRenderingSystem extends AbstractSystem {
  protected Renderer renderer;

  public AbstractRenderingSystem(Renderer renderer) {
    this.renderer = renderer;
  }
}
