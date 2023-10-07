package net.focaenterprises.zenith.ecs.component;

public class TransformComponent implements IComponent {
  public double x;
  public double y;
  public double width;
  public double height;

  public TransformComponent(double x, double y, double width, double height) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }
}
