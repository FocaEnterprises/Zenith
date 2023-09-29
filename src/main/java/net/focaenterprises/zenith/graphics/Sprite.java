package net.focaenterprises.zenith.graphics;

import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Sprite {
  protected BufferedImage image;

  int width;
  int height;

  public void render(Renderer renderer) {
    render(renderer, 0, 0);
  }

  public void render(Renderer renderer, int x, int y) {
    render(renderer, x, y, width, height);
  }

  public void render(Renderer renderer, int x, int y, int width, int height) {
    renderer.drawImage(image, x, y, width, height, null);
  }

  void load(String path) throws IOException {
    image = ImageIO.read(Objects.requireNonNull(getClass().getResource(path)));
    width = image.getWidth();
    height = image.getHeight();
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }
}
