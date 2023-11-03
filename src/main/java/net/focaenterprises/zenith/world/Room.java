package net.focaenterprises.zenith.world;

import net.focaenterprises.zenith.ecs.component.IComponent;
import net.focaenterprises.zenith.ecs.component.TransformComponent;
import net.focaenterprises.zenith.ecs.entity.Entity;
import net.focaenterprises.zenith.game.IGameContext;
import net.focaenterprises.zenith.world.tilemap.TileMap;

import java.util.ArrayList;
import java.util.List;

public class Room {

  private final TileMap tileMap;
  private final List<Entity> entities;

  public Room(int width, int height, int tileSize, IGameContext context) {
    this.tileMap = new TileMap(width, height, tileSize, context);
    this.entities = new ArrayList<>();
  }

  public TileMap getTileMap() {
    return tileMap;
  }

  public List<Entity> getEntities() {
    return entities;
  }

  public void setPositionFixed(Entity entity) {
    IComponent component = entity.getComponent(TransformComponent.class);

    if(component == null) return;

    TransformComponent transform = (TransformComponent) component;

    double x = transform.x;
    double y = transform.y;
    long start = System.currentTimeMillis();

    while(true) {
      transform.x++;
      transform.y++;

      if(System.currentTimeMillis() - start > 1000) {
        transform.x = x;
        transform.y = y;
      }

      if(!tileMap.isColliding((int) transform.x, (int) transform.y, (int) transform.width, (int) transform.height)) break;
    }
  }
}

