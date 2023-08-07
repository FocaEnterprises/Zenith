package net.focaenterprises.zenith.world;

import net.focaenterprises.zenith.entity.Entity;
import net.focaenterprises.zenith.world.tilemap.Tilemap;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class World {
  private List<Entity> entities;
  private Tilemap tilemap;

  public void initialize(Tilemap tilemap) {
    this.entities = new ArrayList<>();
    this.tilemap = tilemap;
  }

  public void update() {
    Iterator<Entity> iterator = entities.iterator();

    while(iterator.hasNext()) {
      Entity entity = iterator.next();

      if(entity.isRemoved()) {
        iterator.remove();
        continue;
      }

      entity.update();
    }
  }

  public void render(Graphics graphics) {
    tilemap.render(graphics);
    entities.forEach(e -> e.render(graphics));
  }

  public List<Entity> getEntities() {
    return new ArrayList<>(entities);
  }

  public Tilemap getTilemap() {
    return tilemap;
  }

  public void addEntity(Entity entity) {
    entities.add(entity);
  }
}
