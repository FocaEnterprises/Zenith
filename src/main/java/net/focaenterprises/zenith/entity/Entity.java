package net.focaenterprises.zenith.entity;

import net.focaenterprises.zenith.graphics.Sprite;
import net.focaenterprises.zenith.world.World;

import java.awt.Graphics;

public class Entity {
  protected World world;
  protected Sprite sprite;

  protected int x;
  protected int y;

  protected boolean removed;

  public Entity(World world, int x, int y, Sprite sprite) {
    this.x = x;
    this.y = y;
    this.sprite = sprite;
    this.world = world;
  }

  public void update() {

  }

  public void render(Graphics graphics) {
    sprite.render(graphics, x, y);
  }

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  public boolean isRemoved() {
    return removed;
  }

  public void remove() {
    removed = true;
  }

  public World getWorld() {
    return world;
  }
}
