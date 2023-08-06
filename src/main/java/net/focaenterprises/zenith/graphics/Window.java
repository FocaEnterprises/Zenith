package net.focaenterprises.zenith.graphics;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Window {
  public static final int WIDTH = 320;
  public static final int HEIGHT = 180;
  public static final int SCALE = 3;

  private final JFrame frame;
  private final BufferStrategy bufferStrategy;
  private final BufferedImage scaledLayer;
  private final Canvas canvas;
  public Window() {
    canvas = new Canvas();
    canvas.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

    frame = new JFrame("Zenith");
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setResizable(false);
    frame.add(canvas);
    frame.pack();
    frame.setLocationRelativeTo(null);
    canvas.createBufferStrategy(3);

    bufferStrategy = canvas.getBufferStrategy();
    scaledLayer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
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
  }

  public void addKeyListener(KeyListener k) {
    canvas.addKeyListener(k);
  }
}
