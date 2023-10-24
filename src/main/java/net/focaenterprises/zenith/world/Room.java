package net.focaenterprises.zenith.world;

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
}
