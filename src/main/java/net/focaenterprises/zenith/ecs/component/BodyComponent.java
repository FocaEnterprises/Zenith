package net.focaenterprises.zenith.ecs.component;

public class BodyComponent implements IComponent {
  public boolean isSolid;
  public double horizontalSpeed;
  public double verticalSpeed;

  public BodyComponent(boolean isSolid, double horizontalSpeed, double verticalSpeed) {
    this.horizontalSpeed = horizontalSpeed;
    this.verticalSpeed = verticalSpeed;
  }
}
