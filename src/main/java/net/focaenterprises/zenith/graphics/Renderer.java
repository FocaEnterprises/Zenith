package net.focaenterprises.zenith.graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Renderer {
  public Window window;
  private BufferStrategy bufferStrategy;
  private BufferedImage layer;
  private Graphics uiGraphics;
  private Graphics layerGraphics;
  public List<RendererRequest> requests;
  private int currentDepth;

  public Renderer(Window window) {
    this.window = window;
  }

  public void begin() {
    bufferStrategy = window.getBufferStrategy();
    layer = window.getScaledLayer();

    uiGraphics = bufferStrategy.getDrawGraphics();
    layerGraphics = layer.getGraphics();

    layerGraphics.setColor(Color.black);
    layerGraphics.fillRect(0, 0, window.width, window.height);

    requests = new ArrayList<>();
  }

  public void end() {
    Collections.sort(requests, (o1, o2) -> o2.getDepth() - o1.getDepth());

    for (RendererRequest request : requests) {
      request.run(layerGraphics);
    }

    uiGraphics.drawImage(layer, 0, 0, window.width * window.scale, window.height * window.scale, null);

    layerGraphics.dispose();
    uiGraphics.dispose();
    bufferStrategy.show();
  }

  public void setDepth(int depth) {
    this.currentDepth = depth;
  }

  public Graphics create() {
    return layerGraphics.create();
  }

  public Graphics create(int x, int y, int width, int height) {
    return layerGraphics.create(x, y, width, height);
  }

  public void translate(int x, int y) {
    requests.add(new RendererRequest(currentDepth) {
      @Override
      void run(Graphics g) {
        g.translate(x, y);
      }
    });
  }

  public Color getColor() {
    return layerGraphics.getColor();
  }

  public void setColor(Color c) {
    requests.add(new RendererRequest(currentDepth) {
      @Override
      void run(Graphics g) {
        g.setColor(c);
      }
    });
  }

  public void setPaintMode() {
    requests.add(new RendererRequest(currentDepth) {
      @Override
      void run(Graphics g) {
        g.setPaintMode();
      }
    });
  }

  public void setXORMode(Color c1) {
    requests.add(new RendererRequest(currentDepth) {
      @Override
      void run(Graphics g) {
        g.setXORMode(c1);
      }
    });
  }

  public void setFont(Font font) {
    requests.add(new RendererRequest(currentDepth) {
      @Override
      void run(Graphics g) {
        g.setFont(font);
      }
    });
  }

  public void clipRect(int x, int y, int width, int height) {
    requests.add(new RendererRequest(currentDepth) {
      @Override
      void run(Graphics g) {
        g.clearRect(x, y, width, height);
      }
    });
  }

  public void setClip(int x, int y, int width, int height) {
    requests.add(new RendererRequest(currentDepth) {
      @Override
      void run(Graphics g) {
        g.setClip(x, y, width, height);
      }
    });
  }

  public void setClip(Shape clip) {
    requests.add(new RendererRequest(currentDepth) {
      @Override
      void run(Graphics g) {
        g.setClip(clip);
      }
    });
  }

  public void copyArea(int x, int y, int width, int height, int dx, int dy) {
    requests.add(new RendererRequest(currentDepth) {
      @Override
      void run(Graphics g) {
        g.copyArea(x, y, width, height, dx, dy);
      }
    });
  }

  public void drawLine(int x1, int y1, int x2, int y2) {
    requests.add(new RendererRequest(currentDepth) {
      @Override
      void run(Graphics g) {
        g.drawLine(x1, y1, x2, y2);
      }
    });
  }

  public void fillRect(int x, int y, int width, int height) {
    requests.add(new RendererRequest(currentDepth) {
      @Override
      void run(Graphics g) {
        g.fillRect(x, y, width, height);
      }
    });
  }

  public void drawRect(int x, int y, int width, int height) {
    requests.add(new RendererRequest(currentDepth) {
      @Override
      void run(Graphics g) {
        g.drawRect(x, y, width, height);
      }
    });
  }

  public void clearRect(int x, int y, int width, int height) {
    requests.add(new RendererRequest(currentDepth) {
      @Override
      void run(Graphics g) {
        g.clearRect(x, y, width, height);
      }
    });
  }

  public void drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
    requests.add(new RendererRequest(currentDepth) {
      @Override
      void run(Graphics g) {
        g.drawRoundRect(x, y, width, height, arcWidth, arcHeight);
      }
    });
  }

  public void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
    requests.add(new RendererRequest(currentDepth) {
      @Override
      void run(Graphics g) {
        g.fillRoundRect(x, y, width, height, arcWidth, arcHeight);
      }
    });
  }

  public void draw3DRect(int x, int y, int width, int height, boolean raised) {
    requests.add(new RendererRequest(currentDepth) {
      @Override
      void run(Graphics g) {
        g.draw3DRect(x, y, width, height, raised);
      }
    });
  }

  public void fill3DRect(int x, int y, int width, int height, boolean raised) {
    requests.add(new RendererRequest(currentDepth) {
      @Override
      void run(Graphics g) {
        layerGraphics.fill3DRect(x, y, width, height, raised);
      }
    });
  }

  public void drawOval(int x, int y, int width, int height) {
    requests.add(new RendererRequest(currentDepth) {
      @Override
      void run(Graphics g) {
        layerGraphics.drawOval(x, y, width, height);
      }
    });
  }

  public void fillOval(int x, int y, int width, int height) {
    requests.add(new RendererRequest(currentDepth) {
      @Override
      void run(Graphics g) {
        layerGraphics.fillOval(x, y, width, height);
      }
    });
  }

  public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
    requests.add(new RendererRequest(currentDepth) {
      @Override
      void run(Graphics g) {
        layerGraphics.drawArc(x, y, width, height, startAngle, arcAngle);
      }
    });
  }

  public void fillArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
    requests.add(new RendererRequest(currentDepth) {
      @Override
      void run(Graphics g) {
        layerGraphics.fillArc(x, y, width, height, startAngle, arcAngle);
      }
    });
  }

  public void drawPolyline(int[] xPoints, int[] yPoints, int nPoints) {
    requests.add(new RendererRequest(currentDepth) {
      @Override
      void run(Graphics g) {
        layerGraphics.drawPolyline(xPoints, yPoints, nPoints);
      }
    });
  }

  public void drawPolygon(int[] xPoints, int[] yPoints, int nPoints) {
    requests.add(new RendererRequest(currentDepth) {
      @Override
      void run(Graphics g) {
        layerGraphics.drawPolygon(xPoints, yPoints, nPoints);
      }
    });
  }

  public void drawPolygon(Polygon p) {
    requests.add(new RendererRequest(currentDepth) {
      @Override
      void run(Graphics g) {
        layerGraphics.drawPolygon(p);
      }
    });
  }

  public void fillPolygon(int[] xPoints, int[] yPoints, int nPoints) {
    requests.add(new RendererRequest(currentDepth) {
      @Override
      void run(Graphics g) {
        layerGraphics.fillPolygon(xPoints, yPoints, nPoints);
      }
    });
  }

  public void fillPolygon(Polygon p) {
    requests.add(new RendererRequest(currentDepth) {
      @Override
      void run(Graphics g) {
        layerGraphics.fillPolygon(p);
      }
    });
  }

  public void drawString(String str, int x, int y) {
    requests.add(new RendererRequest(currentDepth) {
      @Override
      void run(Graphics g) {
        layerGraphics.drawString(str, x, y);
      }
    });
  }

  public void drawString(AttributedCharacterIterator iterator, int x, int y) {
    requests.add(new RendererRequest(currentDepth) {
      @Override
      void run(Graphics g) {
        layerGraphics.drawString(iterator, x, y);
      }
    });
  }

  public void drawChars(char[] data, int offset, int length, int x, int y) {
    requests.add(new RendererRequest(currentDepth) {
      @Override
      void run(Graphics g) {
        layerGraphics.drawChars(data, offset, length, x, y);
      }
    });
  }

  public void drawBytes(byte[] data, int offset, int length, int x, int y) {
    requests.add(new RendererRequest(currentDepth) {
      @Override
      void run(Graphics g) {
        layerGraphics.drawBytes(data, offset, length, x, y);
      }
    });
  }

  public void drawImage(Image img, int x, int y, ImageObserver observer) {
    requests.add(new RendererRequest(currentDepth) {
      @Override
      void run(Graphics g) {
        layerGraphics.drawImage(img, x, y, observer);
      }
    });
  }

  public void drawImage(Image img, int x, int y, int width, int height, ImageObserver observer) {
    requests.add(new RendererRequest(currentDepth) {
      @Override
      void run(Graphics g) {
        g.drawImage(img, x, y, width, height, observer);
      }
    });
  }

  public void drawImage(Image img, int x, int y, Color bgcolor, ImageObserver observer) {
    requests.add(new RendererRequest(currentDepth) {
      @Override
      void run(Graphics g) {
        layerGraphics.drawImage(img, x, y, bgcolor, observer);
      }
    });
  }

  public void drawImage(Image img, int x, int y, int width, int height, Color bgcolor, ImageObserver observer) {
    requests.add(new RendererRequest(currentDepth) {
      @Override
      void run(Graphics g) {
        layerGraphics.drawImage(img, x, y, width, height, bgcolor, observer);
      }
    });
  }

  public void drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, ImageObserver observer) {
    requests.add(new RendererRequest(currentDepth) {
      @Override
      void run(Graphics g) {
        layerGraphics.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, observer);
      }
    });
  }

  public void drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, Color bgcolor, ImageObserver observer) {
    requests.add(new RendererRequest(currentDepth) {
      @Override
      void run(Graphics g) {
        layerGraphics.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, bgcolor, observer);
      }
    });
  }

  public void dispose() {
    requests.add(new RendererRequest(currentDepth) {
      @Override
      void run(Graphics g) {
        layerGraphics.dispose();
      }
    });
  }
}
