package net.focaenterprises.zenith.ecs.component;

public class ControlComponent implements IComponent {
  public boolean up;
  public boolean down;
  public boolean right;
  public boolean left;

  public ControlComponent(boolean right, boolean left, boolean up, boolean down) {
    this.up = up;
    this.down = down;
    this.right = right;
    this.left = left;
  }

  public ControlComponent() {
    this(false, false, false, false);
  }
}
