package net.focaenterprises.zenith.ecs.system;

import net.focaenterprises.zenith.ecs.component.BodyComponent;
import net.focaenterprises.zenith.ecs.component.TransformComponent;
import net.focaenterprises.zenith.ecs.entity.IEntity;

public class TileCollisionSystem extends AbstractSystem {
  @Override
  public void registerComponents() {
    registerComponent(TransformComponent.class);
    registerComponent(BodyComponent.class);
  }

  @Override
  public void process(IEntity entity) {
    TransformComponent transform = (TransformComponent) entity.getComponent(TransformComponent.class);
    BodyComponent rigidBody = (BodyComponent) entity.getComponent(BodyComponent.class);

    double x = transform.x;
    double y = transform.y;
    int width = (int) transform.width;
    int height = (int) transform.height;

    double horizontalSpeed = rigidBody.horizontalSpeed;
    double verticalSpeed = rigidBody.verticalSpeed;

    for (int i = 0; i < Math.abs(horizontalSpeed); i++) {
      int nextX = (int) (x + ((i + 1) * Math.signum(horizontalSpeed)));
      if (world.getTilemap().isColliding(nextX, (int) y, width, height)) {
        horizontalSpeed = (int) (i * Math.signum(horizontalSpeed));
        break;
      }
    }

    for (int i = 0; i < Math.abs(verticalSpeed); i++) {
      int nextY = (int) (y + ((i + 1) * Math.signum(verticalSpeed)));
      if (world.getTilemap().isColliding((int) (x + horizontalSpeed), nextY, width, height)) {
        verticalSpeed = (int) (i * Math.signum(verticalSpeed));
        break;
      }
    }

    rigidBody.horizontalSpeed = horizontalSpeed;
    rigidBody.verticalSpeed = verticalSpeed;
  }
}