package net.focaenterprises.zenith.world;

import net.focaenterprises.zenith.entity.Entity;
import net.focaenterprises.zenith.world.tilemap.Tilemap;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class World {
  public static final int TILE_SIZE = 16;

  private List<Entity> entities;
  private Tilemap tilemap;

  public void initialize(Tilemap tilemap) {
    this.entities = new ArrayList<>();
    this.tilemap = tilemap;
    this.generate();
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

  private void generate() {
    Random random = new Random();

    for(int x = 0; x < 50; x++) {
      for(int y = 0; y < 50; y++) {
        tilemap.setTileType(x, y, 1);

        if(random.nextInt(100) < 4) {
          tilemap.setTileType(x, y, 2);
        }

        if(random.nextInt(100) < 4) {
          tilemap.setTileType(x, y, 3);
        }
      }
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
