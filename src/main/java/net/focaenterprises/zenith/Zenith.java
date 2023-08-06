package net.focaenterprises.zenith;

import net.focaenterprises.entity.PlayerEntity;
import net.focaenterprises.input.Keyboard;
import net.focaenterprises.world.World;
import net.focaenterprises.zenith.graphics.Spritesheet;
import net.focaenterprises.zenith.graphics.Window;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import static net.focaenterprises.zenith.graphics.Window.HEIGHT;
import static net.focaenterprises.zenith.graphics.Window.SCALE;
import static net.focaenterprises.zenith.graphics.Window.WIDTH;

public class Zenith {
  private final Window window;
  private final Spritesheet spritesheet;
  private final Loop loop;
  private final World world;
  private Keyboard keyboard;
  private PlayerEntity player;
  public Zenith() {
    this.window = new Window();
    this.loop = new Loop(this::update, this::render);
    this.spritesheet = new Spritesheet("/spritesheet.png");
    this.world = new World();
    this.keyboard = new Keyboard();
    this.window.addKeyListener(keyboard);
  }

  public void start() {
    if(!spritesheet.loadSpritesheet()) {
      System.out.println("Failed to load spritesheet, shutting down!");
      System.exit(1);
    }

    world.initialize();

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
