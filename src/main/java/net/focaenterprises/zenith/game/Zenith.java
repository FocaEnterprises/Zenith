package net.focaenterprises.zenith.game;

import net.focaenterprises.zenith.entity.PlayerEntity;
import net.focaenterprises.zenith.graphics.SpriteSheet;
import net.focaenterprises.zenith.graphics.Window;
import net.focaenterprises.zenith.input.Keyboard;
import net.focaenterprises.zenith.world.World;
import net.focaenterprises.zenith.world.tilemap.TileRegistry;
import net.focaenterprises.zenith.world.tilemap.TileMap;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import static net.focaenterprises.zenith.graphics.Window.HEIGHT;
import static net.focaenterprises.zenith.graphics.Window.SCALE;
import static net.focaenterprises.zenith.graphics.Window.WIDTH;
import static net.focaenterprises.zenith.world.World.TILE_SIZE;

public class Zenith {
  private final Window window;
  private final SpriteSheet spritesheet;
  private final Loop loop;
  private final World world;
  private TileMap tilemap;
  private Keyboard keyboard;
  private PlayerController playerController;

  public Zenith() {
    this.window = new Window();
    this.loop = new Loop(this::update, this::render);
    this.spritesheet = new SpriteSheet("/spritesheet.png");
    this.world = new World();
    this.tilemap = new TileMap(50, 50, TILE_SIZE);
    this.keyboard = new Keyboard();
    this.window.addKeyListener(keyboard);
  }

  public void start() {
    if(!spritesheet.loadSpritesheet()) {
      System.out.println("Failed to load spritesheet, shutting down!");
      System.exit(1);
    }

    world.initialize(tilemap);

    TileRegistry.newTileType(spritesheet.getSprite(0, 16, TILE_SIZE, TILE_SIZE), false);
    TileRegistry.newTileType(spritesheet.getSprite(16, 16, TILE_SIZE, TILE_SIZE), true);
    TileRegistry.newTileType(spritesheet.getSprite(32, 16, TILE_SIZE, TILE_SIZE), true);

    PlayerEntity player = new PlayerEntity(world, 100, 100, spritesheet.getSprite(0, 0, TILE_SIZE, TILE_SIZE));
    world.addEntity(player);

    playerController = new PlayerController(keyboard, player);

    window.show();
    loop.loop();
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
    layerGraphics.fillRect(0, 0, WIDTH, HEIGHT);

    world.render(layerGraphics);

    uiGraphics.drawImage(layer, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);

    layerGraphics.dispose();
    uiGraphics.dispose();
    bufferStrategy.show();
  }

  public SpriteSheet getSpritesheet() {
    return spritesheet;
  }

  public World getWorld() {
    return world;
  }
}
