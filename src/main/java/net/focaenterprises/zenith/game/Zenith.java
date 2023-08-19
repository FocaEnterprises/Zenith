package net.focaenterprises.zenith.game;

import net.focaenterprises.zenith.entity.PlayerEntity;
import net.focaenterprises.zenith.graphics.SpriteSheet;
import net.focaenterprises.zenith.graphics.Window;
import net.focaenterprises.zenith.input.Keyboard;
import net.focaenterprises.zenith.world.World;
import net.focaenterprises.zenith.world.tilemap.TileMap;
import net.focaenterprises.zenith.world.tilemap.TileRegistry;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Zenith implements IGameContext {
  private final Window window;
  private final SpriteSheet spritesheet;
  private final Loop loop;
  private final World world;
  private TileMap tilemap;
  private Keyboard keyboard;
  private PlayerController playerController;

  public Zenith() {
    this.window = new Window(280, 180, 3);
    this.loop = new Loop(this::update, this::render, 60);
    this.spritesheet = new SpriteSheet("");
    this.world = new World();
    this.tilemap = new TileMap(50, 50, 16);
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

    world.initialize(tilemap);

    TileRegistry.newTileType(spritesheet.getSprite("grass"), false);
    TileRegistry.newTileType(spritesheet.getSprite("dirt"), true);
    TileRegistry.newTileType(spritesheet.getSprite("stone"), true);

    PlayerEntity player = new PlayerEntity(this, world, 100, 100, spritesheet.getSprite("player"));
    world.addEntity(player);

    playerController = new PlayerController(this, player);

    window.show();
    loop.start();
  }

  private void update() {
    keyboard.poll();
    playerController.update();
    world.update();
  }

  private void render() {
    BufferStrategy bufferStrategy = window.getBufferStrategy();
    BufferedImage layer = window.getScaledLayer();

    Graphics uiGraphics = bufferStrategy.getDrawGraphics();
    Graphics layerGraphics = layer.getGraphics();

    layerGraphics.setColor(new Color(141, 141, 141));
    layerGraphics.fillRect(0, 0, window.width, window.height);

    world.render(layerGraphics);

    uiGraphics.drawImage(layer, 0, 0, window.width * window.scale, window.height * window.scale, null);

    layerGraphics.dispose();
    uiGraphics.dispose();
    bufferStrategy.show();
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
