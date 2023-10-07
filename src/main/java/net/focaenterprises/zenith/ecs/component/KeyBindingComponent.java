package net.focaenterprises.zenith.ecs.component;

public class KeyBindingComponent implements IComponent {
  public int upKey;
  public int downKey;
  public int rightKey;
  public int leftKey;

  public KeyBindingComponent(int upKey, int downKey, int rightKey, int leftKey) {
    this.upKey = upKey;
    this.downKey = downKey;
    this.rightKey = rightKey;
    this.leftKey = leftKey;
  }
}
