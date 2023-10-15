package net.focaenterprises.zenith.ecs.system;

import net.focaenterprises.zenith.ecs.component.BodyComponent;
import net.focaenterprises.zenith.ecs.component.ControlComponent;
import net.focaenterprises.zenith.ecs.component.TransformComponent;
import net.focaenterprises.zenith.ecs.entity.IEntity;
import net.focaenterprises.zenith.game.IGameContext;
import net.focaenterprises.zenith.utils.MathUtils;
import net.focaenterprises.zenith.world.tilemap.TileMap;

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

    if(entity.getComponent(ControlComponent.class) != null) {
      IGameContext context = world.getContext();
      TileMap tileMap = world.getTilemap();

      int width = context.getWindowWidth();
      int height = context.getWindowHeight();
      int tileSize = tileMap.getTileSize();

      int cameraX = MathUtils.clamp((int) (transform.x - width / 2), 0, tileMap.getWidth() * tileSize - width);
      int cameraY = MathUtils.clamp((int) (transform.y - height / 2), 0, tileMap.getHeight() * tileSize - height);

      context.getCamera().set(cameraX, cameraY);
    }
  }
}
