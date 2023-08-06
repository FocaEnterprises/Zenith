package net.focaenterprises.world.tilemap;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TileType {
    private final BufferedImage image;
    private final boolean solid;

    TileType(BufferedImage image, boolean isSolid) {
        this.image = image;
        this.solid = isSolid;
    }

    public void render(Graphics g, int x, int y) {
        g.drawImage(image, x, y, null);
    }

    public boolean isSolid() {
        return solid;
    }
}
