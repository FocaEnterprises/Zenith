package net.focaenterprises.zenith.game;

import net.focaenterprises.zenith.input.Keyboard;

public interface IGameContext {
    int getMaxFPS();

    int getWindowWidth();
    int getWindowHeight();
    int getWindowScale();

    Keyboard geKeyboard();
}
