package net.focaenterprises.zenith.world;

import net.focaenterprises.zenith.ecs.component.BodyComponent;
import net.focaenterprises.zenith.ecs.component.ControlComponent;
import net.focaenterprises.zenith.ecs.component.KeyBindingComponent;
import net.focaenterprises.zenith.ecs.component.SquareComponent;
import net.focaenterprises.zenith.ecs.component.TransformComponent;
import net.focaenterprises.zenith.ecs.entity.Entity;
import net.focaenterprises.zenith.game.IGameContext;
import net.focaenterprises.zenith.world.tilemap.TileMap;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Room {

  private final TileMap tileMap;
  private final List<Entity> entities;

  public Room(int width, int height, int tileSize, IGameContext context) {
    this.tileMap = new TileMap(width, height, tileSize, context);
    this.entities = new ArrayList<>();

    Entity entity = new Entity("Godofredo");

    entity
      .attach(new TransformComponent(16 * 5, 16 * 8, 16, 16))
      .attach(new KeyBindingComponent(KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_RIGHT, KeyEvent.VK_LEFT))
      .attach(new ControlComponent())
      .attach(new BodyComponent(true, 1, 1))
      .attach(new SquareComponent(Color.blue, 1));

    getEntities().add(entity);
  }

  public TileMap getTileMap() {
    return tileMap;
  }

  public List<Entity> getEntities() {
    return entities;
  }
}
