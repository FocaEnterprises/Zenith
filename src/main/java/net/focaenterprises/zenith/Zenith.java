package net.focaenterprises.zenith;

import net.focaenterprises.zenith.entity.PlayerEntity;
import net.focaenterprises.zenith.input.Keyboard;
import net.focaenterprises.zenith.world.World;
import net.focaenterprises.zenith.world.tilemap.TileFactory;
import net.focaenterprises.zenith.world.tilemap.Tilemap;
import net.focaenterprises.zenith.graphics.Spritesheet;
import net.focaenterprises.zenith.graphics.Window;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

import static net.focaenterprises.zenith.graphics.Window.HEIGHT;
import static net.focaenterprises.zenith.graphics.Window.SCALE;
import static net.focaenterprises.zenith.graphics.Window.WIDTH;

public class Zenith {
  private final Window window;
  private final Spritesheet spritesheet;
  private final Loop loop;
  private final World world;
  private Tilemap tilemap;
  private Keyboard keyboard;
  private PlayerEntity player;

  public Zenith() {
    this.window = new Window();
    this.loop = new Loop(this::update, this::render);
    this.spritesheet = new Spritesheet("/spritesheet.png");
    this.world = new World();
    this.tilemap = new Tilemap(16, 16, 16);
    this.keyboard = new Keyboard();
    this.window.addKeyListener(keyboard);
  }

  public void start() {
    if(!spritesheet.loadSpritesheet()) {
      System.out.println("Failed to load spritesheet, shutting down!");
      System.exit(1);
    }

    world.initialize(tilemap);

    TileFactory.newTileType(spritesheet.getSprite(16, 0, 16, 16), true);
    TileFactory.newTileType(spritesheet.getSprite(32, 0, 16, 16), true);

    Random random = new Random();

    for (int x = 0; x < 16; x++) {
      for (int y = 0; y < 16; y++) {
        if (random.nextInt(100) < 4) {
          tilemap.setTileType(x, y, 1);
        }
        if (random.nextInt(100) < 4) {
          tilemap.setTileType(x, y, 2);
        }
      }
    }

    player = new PlayerEntity(world, 100, 100, spritesheet.getSprite(0, 0, 16, 16), keyboard);

    world.addEntity(player);

    window.show();
    loop.loop();
  }

  private void update() {
    keyboard.poll();
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

  public Spritesheet getSpritesheet() {
    return spritesheet;
  }

  public World getWorld() {
    return world;
  }
}
