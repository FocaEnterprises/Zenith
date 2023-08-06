package net.focaenterprises.zenith;

import net.focaenterprises.zenith.utils.Time;

public class Loop {
  private boolean isRunning;

  private final Runnable updateCallback;
  private final Runnable renderCallback;

  public Loop(Runnable updateCallback, Runnable renderCallback) {
    this.updateCallback = updateCallback;
    this.renderCallback = renderCallback;
  }

  public void loop() {
    isRunning = true;

    long lastTime = Time.nano();
    long now;

    double ns = 1_000_000_000 / 60.0D;
    double deltaTime = 0;

    while(isRunning) {
      now = System.nanoTime();
      deltaTime += (now - lastTime) / ns;
      lastTime = now;

      while(deltaTime >= 1) {
        deltaTime--;
        updateCallback.run();
        renderCallback.run();
      }
    }
  }
}
