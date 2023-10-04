package net.focaenterprises.zenith.game;

import net.focaenterprises.zenith.ecs.component.*;
import net.focaenterprises.zenith.ecs.system.*;
import net.focaenterprises.zenith.graphics.Renderer;
import net.focaenterprises.zenith.graphics.Sprite;
import net.focaenterprises.zenith.graphics.SpriteSheet;
import net.focaenterprises.zenith.graphics.Window;
import net.focaenterprises.zenith.input.Keyboard;
import net.focaenterprises.zenith.world.RandomRoom;
import net.focaenterprises.zenith.world.World;
import net.focaenterprises.zenith.world.tilemap.TileMap;
import net.focaenterprises.zenith.world.tilemap.TileRegistry;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Zenith implements IGameContext {
  private final Window window;
  private final Renderer renderer;
  private final SpriteSheet spritesheet;
  private final Loop loop;
  private final World world;
  private TileMap tilemap;
  private Keyboard keyboard;

  public Zenith() {
    this.window = new Window(280, 180, 3);

    this.renderer = new Renderer(window);

    this.loop = new Loop(this::update, this::render, 60);
    this.spritesheet = new SpriteSheet("");

    this.world = new World();

    this.keyboard = new Keyboard();
    this.window.addKeyListener(keyboard);

  }

  public void start() {
    spritesheet.registerSprite("player");
    spritesheet.registerSprite("grass");
    spritesheet.registerSprite("dirt");
    spritesheet.registerSprite("stone");

    if (!spritesheet.loadSprites()) {
      System.out.println("Failed to load spritesheet, shutting down!");
      System.exit(1);
    }

    world.initialize();

    this.tilemap = world.createRoom(new RandomRoom(16, 16, 16)).getTileMap();
    this.tilemap = world.createRoom(new RandomRoom(16, 16, 16)).getTileMap();
    this.tilemap = world.createRoom(new RandomRoom(16, 16, 16)).getTileMap();
    this.tilemap = world.createRoom(new RandomRoom(16, 16, 16)).getTileMap();

    TileRegistry.newTileType(spritesheet.getSprite("grass"), false);
    TileRegistry.newTileType(spritesheet.getSprite("dirt"), true);
    TileRegistry.newTileType(spritesheet.getSprite("stone"), true);

    world.registerSystem(new TileCollisionSystem());
    world.registerSystem(new MovementSystem());
    world.registerSystem(new InputSystem(this));
    world.registerSystem(new ControlSystem(Math.PI));

    world.registerSystem(new SpriteRenderingSystem(renderer));
    world.registerSystem(new SquareRenderingSystem(renderer));

    window.show();
    loop.start();
  }

  private void update() {
    keyboard.poll();

    if (keyboard.keyboardCheckPressed(KeyEvent.VK_D)) {
      if(!world.nextRoom()) {
        world.setRoom(0);
      }
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
}