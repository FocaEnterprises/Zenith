package net.focaenterprises.zenith.utils;

public final class MathUtils {
  private MathUtils() { }

  public static int clamp(int value, int min, int max) {
    if(value < min) value = min;
    else if(value > max) value = max;

    return value;
  }
}
