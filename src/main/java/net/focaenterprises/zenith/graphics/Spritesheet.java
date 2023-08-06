package net.focaenterprises.zenith.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Spritesheet {
  private final String path;

  private BufferedImage spritesheet;

  public Spritesheet(String path) {
    this.path = path;
  }

  public BufferedImage getSprite(int x, int y, int width, int height) {
    return spritesheet.getSubimage(x, y, width, height);
  }

  public boolean loadSpritesheet() {
    try {
      spritesheet = ImageIO.read(getClass().getResource(path));
      return true;
    } catch(IOException e) {
      e.printStackTrace();
      return false;
    }
  }
}
