package net.focaenterprises.world;

import net.focaenterprises.entity.Entity;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class World {
  private List<Entity> entities;

  public void initialize() {
    entities = new ArrayList<>();
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
    entities.forEach(e -> e.render(graphics));
  }

  public List<Entity> getEntities() {
    return new ArrayList<>(entities);
  }

  public void addEntity(Entity entity) {
    entities.add(entity);
  }
}
