package net.focaenterprises.zenith.game;

import net.focaenterprises.zenith.utils.Time;

public class Loop {
  private Thread thread;
  private final int maxFPS;
  private boolean isRunning;
  private final Runnable updateCallback;
  private final Runnable renderCallback;

  public Loop(Runnable updateCallback, Runnable renderCallback, int maxFPS) {
    this.updateCallback = updateCallback;
    this.renderCallback = renderCallback;
    this.thread = new Thread(this::loop);
    this.maxFPS = maxFPS;
  }

  public void start() {
    thread.start();
    isRunning = true;
  }

  public void loop() {
    long lastTime = Time.nano();
    long now;

    double ns = 1_000_000_000 / maxFPS;
    double deltaTime = 0;

    while (isRunning) {
      now = System.nanoTime();
      deltaTime += (now - lastTime) / ns;
      lastTime = now;

      while (deltaTime >= 1) {
        deltaTime--;
        updateCallback.run();
        renderCallback.run();
      }
    }
  }
}
