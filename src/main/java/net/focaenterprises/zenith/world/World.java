package net.focaenterprises.zenith.world;

import net.focaenterprises.zenith.ecs.component.IComponent;
import net.focaenterprises.zenith.ecs.component.IRenderingComponent;
import net.focaenterprises.zenith.ecs.entity.Entity;
import net.focaenterprises.zenith.ecs.system.AbstractRenderingSystem;
import net.focaenterprises.zenith.ecs.system.ISystem;
import net.focaenterprises.zenith.graphics.Renderer;
import net.focaenterprises.zenith.world.tilemap.TileMap;

import java.awt.Graphics;
import java.util.*;
import java.util.stream.Collectors;

public class World {
  private TileMap tilemap;
  private List<ISystem> systems;
  private List<Entity> entities;

  public void initialize(TileMap tilemap) {
    this.tilemap = tilemap;
    this.systems = new ArrayList<>();
    this.entities = new ArrayList<>();
    this.generate();
  }

  public void update() {
    for (ISystem system : systems) {
      if (system instanceof AbstractRenderingSystem == false) {
        system.update();
      }
    }
  }

  private void generate() {
    Random random = new Random();

    for (int x = 0; x < 50; x++) {
      for (int y = 0; y < 50; y++) {
        tilemap.setTileType(x, y, 1);
      }
    }

    tilemap.setTileType(5, 5, 2);
    tilemap.setTileType(5, 7, 2);
  }

  public void registerSystem(ISystem system) {
    system.setWorld(this);

    getSystems().add(system);
  }

  public Entity createEntity(String name) {
    Entity entity = new Entity(name);
    entities.add(entity);

    return entity;
  }

  public Entity getEntity(String name) {
    return entities.stream().filter(entity -> entity.getName().equals(name)).findAny().orElse(null);
  }

  public List<Entity> getEntities() {
    return entities;
  }

  public List<ISystem> getSystems() {
    return systems;
  }

  public void render(Renderer renderer) {
    tilemap.render(renderer);

//    HashMap<IRenderingComponent, Entity> map = new HashMap<>();
//
//    for (Entity entity : entities) {
//      for (Class<? extends IComponent> componentClass : entity.getAllComponents()) {
//        IComponent component = entity.getComponent(componentClass);
//
//        if (component instanceof IRenderingComponent) {
//          map.put((IRenderingComponent) component, entity);
//        }
//      }
//    }
//
//    Set<Map.Entry<IRenderingComponent, Entity>> entrySet = map.entrySet();
//
//    List<Map.Entry<IRenderingComponent, Entity>> list = new ArrayList<>(entrySet);
//
//    Collections.sort(list, Comparator.comparingInt(o -> -o.getKey().getDepth()));
//
//    System.out.println("List:");
//    for (Map.Entry<IRenderingComponent, Entity> entry : list) {
//      System.out.println(entry.getValue().getName() + " : " + entry.getKey().getClass().getSimpleName() + " : " + entry.getKey().getDepth());
//      for (ISystem system : systems) {
//        if(system instanceof AbstractRenderingSystem) {
//          Entity entity = entry.getValue();
//
//          if (entity.getAllComponents().containsAll(system.getComponents()) && system.getComponents().contains(entry.getKey().getClass())) {
//            ((AbstractRenderingSystem) system).setGraphics(graphics);
//            system.process(entity);
//          }
//        }
//      }
//    }

    for (ISystem system : systems) {
      if (system instanceof AbstractRenderingSystem == true) {
        system.update();
      }
    }
  }

  public TileMap getTilemap() {
    return tilemap;
  }
}
