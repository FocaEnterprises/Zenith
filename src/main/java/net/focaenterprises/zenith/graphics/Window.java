package net.focaenterprises.zenith.graphics;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Window {
  public final int width;
  public final int height;
  public final int scale;

  private final JFrame frame;
  private final BufferStrategy bufferStrategy;
  private final BufferedImage scaledLayer;
  private final Canvas canvas;

  public Window(int width, int height, int scale) {
    this.width = width;
    this.height = height;
    this.scale = scale;

    this.canvas = new Canvas();
    this.canvas.setPreferredSize(new Dimension(width * scale, height * scale));

    this.frame = new JFrame("Zenith");
    this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.frame.setResizable(false);
    this.frame.add(canvas);
    this.frame.pack();
    this.frame.setLocationRelativeTo(null);
    this.canvas.createBufferStrategy(3);

    bufferStrategy = canvas.getBufferStrategy();
    scaledLayer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
  }

  public BufferStrategy getBufferStrategy() {
    return bufferStrategy;
  }

  public BufferedImage getScaledLayer() {
    return scaledLayer;
  }

  public void show() {
    frame.setVisible(true);
  }

  public void hide() {
    frame.setVisible(false);
    canvas.requestFocus();
  }

  public void addKeyListener(KeyListener k) {
    canvas.addKeyListener(k);
  }
}
