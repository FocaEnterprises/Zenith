package net.focaenterprises.zenith.game;

import net.focaenterprises.zenith.ecs.component.BodyComponent;
import net.focaenterprises.zenith.ecs.component.ControlComponent;
import net.focaenterprises.zenith.ecs.component.KeyBindingComponent;
import net.focaenterprises.zenith.ecs.component.SquareComponent;
import net.focaenterprises.zenith.ecs.component.TransformComponent;
import net.focaenterprises.zenith.ecs.entity.Entity;
import net.focaenterprises.zenith.ecs.system.ControlSystem;
import net.focaenterprises.zenith.ecs.system.InputSystem;
import net.focaenterprises.zenith.ecs.system.MovementSystem;
import net.focaenterprises.zenith.ecs.system.SpriteRenderingSystem;
import net.focaenterprises.zenith.ecs.system.SquareRenderingSystem;
import net.focaenterprises.zenith.ecs.system.TileCollisionSystem;
import net.focaenterprises.zenith.graphics.Camera;
import net.focaenterprises.zenith.graphics.Renderer;
import net.focaenterprises.zenith.graphics.SpriteSheet;
import net.focaenterprises.zenith.graphics.Window;
import net.focaenterprises.zenith.input.Keyboard;
import net.focaenterprises.zenith.world.BitmapRoom;
import net.focaenterprises.zenith.world.RandomRoom;
import net.focaenterprises.zenith.world.World;
import net.focaenterprises.zenith.world.tilemap.TileRegistry;

import java.awt.Color;
import java.awt.event.KeyEvent;

public class Zenith implements IGameContext {
  private final Window window;
  private final Renderer renderer;
  private final SpriteSheet spritesheet;
  private final Loop loop;
  private final World world;
  private final Keyboard keyboard;
  private final Camera camera;

  private Entity player;

  public Zenith() {
    this.window = new Window(280, 180, 3);

    this.renderer = new Renderer(window);

    this.loop = new Loop(this::update, this::render, 60);
    this.spritesheet = new SpriteSheet("");
    this.camera = new Camera();

    this.world = new World(this);

    this.keyboard = new Keyboard();
    this.window.addKeyListener(keyboard);
  }

  public void start() {
    TileRegistry.TILE_TYPES.values().forEach(tile -> spritesheet.registerSprite(tile.getName()));
    spritesheet.registerSprite("player");

    if (!spritesheet.loadSprites()) {
      System.out.println("Failed to load spritesheet, shutting down!");
      System.exit(1);
    }

    world.initialize();

    world.createRoom(new BitmapRoom(60, 60, 16, this, "city"));
    world.createRoom(new RandomRoom(40, 40, 16, this));
    world.createRoom(new RandomRoom(40, 40, 16, this));
    world.createRoom(new RandomRoom(40, 40, 16, this));

    world.registerSystem(new InputSystem(this));
    world.registerSystem(new ControlSystem(Math.PI));
    world.registerSystem(new TileCollisionSystem());
    world.registerSystem(new MovementSystem());

    world.registerSystem(new SpriteRenderingSystem(renderer));
    world.registerSystem(new SquareRenderingSystem(renderer));

    player = world.createEntity("Godofredo");

    player
      .attach(new TransformComponent(16 * 5, 16 * 8, 16, 16))
      .attach(new KeyBindingComponent(KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_RIGHT, KeyEvent.VK_LEFT))
      .attach(new ControlComponent())
      .attach(new BodyComponent(true, 1, 1))
      .attach(new SquareComponent(Color.blue, 1));

    window.show();
    loop.start();
  }

  private void update() {
    keyboard.poll();

    if (keyboard.keyboardCheckPressed(KeyEvent.VK_D)) {
      world.nextRoom();
    }

    world.update();
  }

  private void render() {
    renderer.begin();

    world.render(renderer);

    renderer.end();
  }

  public SpriteSheet getSpritesheet() {
    return spritesheet;
  }

  @Override
  public int getMaxFPS() {
    return loop.getMaxFPS();
  }

  @Override
  public int getWindowWidth() {
    return window.width;
  }

  @Override
  public int getWindowHeight() {
    return window.height;
  }

  @Override
  public int getWindowScale() {
    return window.scale;
  }

  @Override
  public Keyboard geKeyboard() {
    return keyboard;
  }

  public World getWorld() {
    return world;
  }

  @Override
  public Camera getCamera() {
    return camera;
  }

  @Override
  public SpriteSheet getSpriteSheet() {
    return spritesheet;
  }

  @Override
  public Entity getPlayer() {
    return player;
  }
}