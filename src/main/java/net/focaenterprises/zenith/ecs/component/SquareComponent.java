package net.focaenterprises.zenith.ecs.component;

import java.awt.*;

public class SquareComponent implements IRenderingComponent {
    public Color color;
    private int depth;

    public SquareComponent(Color color, int depth) {
        this.color = color;
        this.depth = depth;
    }

    @Override
    public int getDepth() {
        return depth;
    }
}
