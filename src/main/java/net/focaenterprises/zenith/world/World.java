package net.focaenterprises.zenith.world;

import net.focaenterprises.zenith.ecs.entity.Entity;
import net.focaenterprises.zenith.ecs.system.AbstractRenderingSystem;
import net.focaenterprises.zenith.ecs.system.ISystem;
import net.focaenterprises.zenith.game.IGameContext;
import net.focaenterprises.zenith.graphics.Renderer;
import net.focaenterprises.zenith.world.tilemap.TileMap;

import java.util.ArrayList;
import java.util.List;

public class World {
  protected final IGameContext context;

  private RoomManager roomManager;
  private List<ISystem> systems;

  public World(IGameContext context) {
    this.context = context;
  }

  public void initialize() {
    this.roomManager = new RoomManager();
    this.systems = new ArrayList<>();
  }

  public void update() {
    for (ISystem system : systems) {
      if (!(system instanceof AbstractRenderingSystem)) {
        system.update();
      }
    }
  }

  public void registerSystem(ISystem system) {
    system.setWorld(this);

    getSystems().add(system);
  }

  public Entity createEntity(String name) {
    Entity entity = new Entity(name);
    roomManager.getCurrentRoom().getEntities().add(entity);

    return entity;
  }

  public Room createRoom(Room room) {
    roomManager.getRooms().add(room);

    return room;
  }

  public Room createRoom(int width, int height, int tileSize) {
    return createRoom(new Room(width, height, tileSize, context));
  }

  public boolean nextRoom() {
    return roomManager.nextRoom();
  }

  public void setRoom(int room) {
    roomManager.currentRoomIndex = room;
  }

  public Entity getEntity(String name) {
    return roomManager.getCurrentRoom().getEntities().stream().filter(entity -> entity.getName().equals(name)).findAny().orElse(null);
  }

  public List<Entity> getEntities() {
    return roomManager.getCurrentRoom().getEntities();
  }

  public List<ISystem> getSystems() {
    return systems;
  }

  public void render(Renderer renderer) {
    roomManager.getCurrentRoom().getTileMap().render(renderer);

    for (ISystem system : systems) {
      if (system instanceof AbstractRenderingSystem) {
        system.update();
      }
    }
  }

  public TileMap getTilemap() {
    return roomManager.getCurrentRoom().getTileMap();
  }

  public IGameContext getContext() {
    return context;
  }
}
