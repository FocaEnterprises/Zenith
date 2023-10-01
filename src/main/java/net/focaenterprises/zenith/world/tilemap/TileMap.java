package net.focaenterprises.zenith.world.tilemap;

import net.focaenterprises.zenith.graphics.Renderer;

import java.awt.*;

public class TileMap {
  private final int[][] tileData;
  private final boolean[][] tileDebug;
  private final int tileSize;

  public TileMap(int width, int height, int tileSize) {
    this.tileData = new int[width][height];
    this.tileDebug = new boolean[width][height];
    this.tileSize = tileSize;
  }

  public void render(Renderer r) {
    for (int x = 0; x < tileData.length; x++) {
      for (int y = 0; y < tileData[x].length; y++) {
        TileType tileType = getTileType(x, y);

        if (tileDebug[x][y] == true) {
          r.setColor(Color.red);
          r.fillRect(x * tileSize, y * tileSize, tileSize, tileSize);
          tileDebug[x][y] = false;

          continue;
        }

        if (tileType != null) {
          tileType.render(r, x * tileSize, y * tileSize);
        }
      }
    }
  }

  public TileType getTileType(int x, int y) {
    return TileRegistry.getTileType(tileData[x][y]);
  }

  public void setTileType(int x, int y, int index) {
    tileData[x][y] = index;
  }

  public boolean isColliding(int x, int y, int width, int height) {
    int leftObject = x;
    int rightObject = x + width - 1;
    int topObject = y;
    int bottomObject = y + height - 1;

    int leftTile = leftObject / tileSize;
    int rightTile = rightObject / tileSize;
    int topTile = topObject / tileSize;
    int bottomTile = bottomObject / tileSize;

    if (leftObject < 0 || rightObject >= getWidth() * tileSize || topObject < 0 || bottomObject >= getHeight() * tileSize) {
      return true;
    }

    boolean hasAnyCollision = false;

    for (int tileX = leftTile; tileX <= rightTile; tileX++) {
      for (int tileY = topTile; tileY <= bottomTile; tileY++) {
        TileType tileType = getTileType(tileX, tileY);

        if (tileType != null) {
          if (tileType.isSolid()) {
            hasAnyCollision = true;
          }
        }
      }
    }

    return hasAnyCollision;
  }

  public int getWidth() {
    return tileData.length;
  }

  public int getHeight() {
    return tileData[0].length;
  }
}
