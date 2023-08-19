package net.focaenterprises.zenith.graphics;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SpriteSheet {
  private final Map<String, Sprite> sprites = new HashMap<>();
  private final String path;

  public SpriteSheet(String path) {
    this.path = path;
  }

  public Sprite getSprite(String name) {
    return sprites.get(name);
  }

  public boolean loadSprites() {
    for (String name : sprites.keySet()) {
      String spritePath = "%s/%s.png".formatted(path, name);

      try {
        sprites.get(name).load(spritePath);
      } catch (IOException e) {
        e.printStackTrace();
        System.out.println("Failed to load sprite " + spritePath);
        return false;
      }
    }

    return true;
  }

  public void registerSprite(String name) {
    sprites.put(name, new Sprite());
  }
}
