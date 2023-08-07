package net.focaenterprises.zenith.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

    private static final int KEY_COUNT = KeyEvent.KEY_LAST;
    // Current state of the keyboard
    private boolean[] currentKeys = null;
    // Polled keyboard state
    private KeyState[] keys = null;

    public Keyboard() {
        currentKeys = new boolean[KEY_COUNT];
        keys = new KeyState[KEY_COUNT];
        for (int i = 0; i < KEY_COUNT; ++i) {
            keys[i] = KeyState.RELEASED;
        }
    }

    public synchronized void poll() {
        for (int i = 0; i < KEY_COUNT; ++i) {
            // Set the key state
            if (currentKeys[i]) {
                // If the key is down now, but was not
                // down last frame, set it to ONCE,
                // otherwise, set it to PRESSED
                if (keys[i] == KeyState.RELEASED)
                    keys[i] = KeyState.ONCE_PRESSED;
                else
                    keys[i] = KeyState.PRESSED;
            } else {
                if (keys[i] == KeyState.PRESSED)
                    keys[i] = KeyState.ONCE_RELEASED;
                else
                    keys[i] = KeyState.RELEASED;
            }
        }
    }

    public boolean keyboard_check(int keyCode) {
        return keys[keyCode] == KeyState.ONCE_PRESSED ||
                keys[keyCode] == KeyState.PRESSED;
    }

    public boolean keyboard_check_pressed(int keyCode) {
        return keys[keyCode] == KeyState.ONCE_PRESSED;
    }

    public boolean keyboard_check_released(int keyCode) {
        return keys[keyCode] == KeyState.ONCE_RELEASED;
    }

    public synchronized void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode >= 0 && keyCode < KEY_COUNT) {
            currentKeys[keyCode] = true;
        }
    }

    public synchronized void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode >= 0 && keyCode < KEY_COUNT) {
            currentKeys[keyCode] = false;
        }
    }

    public void keyTyped(KeyEvent e) {
        // Not needed
    }

    private enum KeyState {
        RELEASED, // Not down
        PRESSED,  // Down, but not the first time
        ONCE_PRESSED,      // Down for the first time
        ONCE_RELEASED
    }
}