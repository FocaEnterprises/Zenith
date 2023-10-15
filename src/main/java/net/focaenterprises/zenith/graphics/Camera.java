package net.focaenterprises.zenith.graphics;

public class Camera {
  protected int x;
  protected int y;

  public Camera(int x, int y) {
    set(x, y);
  }

  public Camera() {
    this(0, 0);
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public void setX(int x) {
    this.x = x;
  }

  public void setY(int y) {
    this.y = y;
  }

  public void set(int x, int y) {
    this.x = x;
    this.y = y;
  }
}
