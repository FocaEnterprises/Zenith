package net.focaenterprises.zenith.utils;

public class Time {
  private Time() {
  }

  public static long millis() {
    return System.currentTimeMillis();
  }

  public static long nano() {
    return System.nanoTime();
  }
}
