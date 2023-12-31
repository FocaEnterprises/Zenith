package net.focaenterprises.zenith.ecs.system;

import net.focaenterprises.zenith.ecs.component.SpriteComponent;
import net.focaenterprises.zenith.ecs.component.TransformComponent;
import net.focaenterprises.zenith.ecs.entity.IEntity;
import net.focaenterprises.zenith.graphics.Camera;
import net.focaenterprises.zenith.graphics.Renderer;

public class SpriteRenderingSystem extends AbstractRenderingSystem {
  public SpriteRenderingSystem(Renderer renderer) {
    super(renderer);
  }

  @Override
  public void registerComponents() {
    registerComponent(TransformComponent.class);
    registerComponent(SpriteComponent.class);
  }

  @Override
  public void update() {
    super.update();
  }

  @Override
  public void process(IEntity entity) {
    SpriteComponent sprite = (SpriteComponent) entity.getComponent(SpriteComponent.class);
    TransformComponent transform = (TransformComponent) entity.getComponent(TransformComponent.class);

    Camera camera = world.getContext().getCamera();

    renderer.setDepth(sprite.getDepth());
    sprite.getSprite().render(renderer,
      (int) transform.x - camera.getX(),
      (int) transform.y - camera.getY(),
      (int) transform.width,
      (int) transform.height);
  }
}
