package net.focaenterprises.zenith.graphics;

import java.awt.*;

public abstract class RendererRequest {
    private final int depth;

    public RendererRequest(int depth) {
        this.depth = depth;
    }

    abstract void run(Graphics g);

    public int getDepth() {
        return depth;
    }
}
