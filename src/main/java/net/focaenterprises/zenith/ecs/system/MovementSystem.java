package net.focaenterprises.zenith.ecs.system;

import net.focaenterprises.zenith.ecs.component.BodyComponent;
import net.focaenterprises.zenith.ecs.component.TransformComponent;
import net.focaenterprises.zenith.ecs.entity.IEntity;

public class MovementSystem extends AbstractSystem {
  @Override
  public void registerComponents() {
    registerComponent(TransformComponent.class);
    registerComponent(BodyComponent.class);
  }

  @Override
  public void process(IEntity entity) {
    TransformComponent transform = (TransformComponent) entity.getComponent(TransformComponent.class);
    BodyComponent body = (BodyComponent) entity.getComponent(BodyComponent.class);

    transform.x += body.horizontalSpeed;
    transform.y += body.verticalSpeed;
  }
}
