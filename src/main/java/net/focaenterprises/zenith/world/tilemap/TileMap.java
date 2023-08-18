package net.focaenterprises.zenith.world.tilemap;

import java.awt.*;

public class TileMap {
    private final int[][] tileData;
    private final int tile_size;

    public TileMap(int width, int height, int tile_size) {
        this.tileData = new int[width][height];
        this.tile_size = tile_size;
    }

    public void render(Graphics g) {
        for (int x = 0; x < tileData.length; x++) {
            for (int y = 0; y < tileData[x].length; y++) {
                TileType tileType = getTileType(x, y);

                if (tileType != null) {
                    tileType.render(g, x * tile_size, y * tile_size);
                }
            }
        }
    }

    public TileType getTileType(int x, int y) {
        return TileFactory.getTileType(tileData[x][y]);
    }

    public void setTileType(int x, int y, int index) {
        tileData[x][y] = index;
    }

    public boolean isColliding(int x, int y, int width, int height) {
        int obj_left = x;
        int obj_right = x + width;
        int obj_top = y;
        int obj_bottom = y + height;

        int left_tile = obj_left / tile_size;
        int right_tile = obj_right / tile_size;
        int top_tile = obj_top / tile_size;
        int bottom_tile = obj_bottom / tile_size;

        if (obj_left < 0 || obj_right >= getWidth() * tile_size || obj_top < 0 || obj_bottom >= getHeight() * tile_size) {
            return true;
        }

        boolean any_collision = false;

        for (int tile_x = left_tile; tile_x <= right_tile; tile_x++) {
            for (int tile_y = top_tile; tile_y <= bottom_tile; tile_y++) {
                TileType tileType = getTileType(tile_x, tile_y);

                if (tileType != null) {
                    if (tileType.isSolid()) {
                        any_collision = true;
                    }
                }
            }
        }

        return any_collision;
    }

    public int getWidth() {
        return tileData.length;
    }

    public int getHeight() {
        return tileData[0].length;
    }
}
