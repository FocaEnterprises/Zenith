package net.focaenterprises.zenith.ecs.component;

public class RigidBody implements IComponent {
    public int relativeX;
    public int relativeY;
    public int width;
    public int height;
    public boolean isHorizontalColliding;
    public boolean isVerticalColliding;

    public RigidBody(int relativeX, int relativeY, int width, int height) {
        this.relativeX = relativeX;
        this.relativeY = relativeY;
        this.width = width;
        this.height = height;
    }
}
