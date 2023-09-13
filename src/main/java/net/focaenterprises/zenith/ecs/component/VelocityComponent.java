package net.focaenterprises.zenith.ecs.component;

public class VelocityComponent implements IComponent {
    public double directionX;
    public double directionY;
    public double speed;

    public VelocityComponent(double speed) {
        this.directionX = 0;
        this.directionY = 0;
        this.speed = speed;
    }
}
