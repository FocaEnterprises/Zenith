package net.focaenterprises.zenith;

import net.focaenterprises.Loop;
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
  private final Loop loop;

  public Zenith() {
    this.window = new Window();
    this.loop = new Loop(this::update, this::render);
  }

  public void start() {
    window.show();
    loop.loop();
  }

  private void update() {

  }

  private void render() {
    BufferStrategy bufferStrategy = window.getBufferStrategy();
    BufferedImage layer = window.getScaledLayer();

    Graphics uiGraphics = bufferStrategy.getDrawGraphics();
    Graphics layerGraphics = layer.getGraphics();

    layerGraphics.setColor(new Color(141, 141, 141));
    layerGraphics.fillRect(0, 0, WIDTH, HEIGHT);

    uiGraphics.drawImage(layer, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);

    layerGraphics.dispose();
    uiGraphics.dispose();
    bufferStrategy.show();
  }
}
