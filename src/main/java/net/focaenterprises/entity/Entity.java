package net.focaenterprises.entity;

import net.focaenterprises.world.World;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Entity {
  protected World world;
  protected BufferedImage sprite;

  protected int x;
  protected int y;

  protected boolean removed;

  public Entity(World world, int x, int y, BufferedImage sprite) {
    this.x = x;
    this.y = y;
    this.sprite = sprite;
    this.world = world;
  }

  public void update() {

  }

  public void render(Graphics graphics) {
    graphics.drawImage(sprite, x, y, null);
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
