package com.example.a2340a_team10.model;

import android.view.KeyEvent;

public class MoveKeyActionFactory extends KeyActionFactory {

    @Override
    public KeyAction createKeyAction(int keyCode) {
        switch (keyCode) {
        case KeyEvent.KEYCODE_DPAD_LEFT:
            return new MoveLeftAction();
        case KeyEvent.KEYCODE_DPAD_RIGHT:
            return new MoveRightAction();
        case KeyEvent.KEYCODE_DPAD_UP:
            return new MoveUpAction();
        case KeyEvent.KEYCODE_DPAD_DOWN:
            return new MoveDownAction();
        default:
            return null;
        }
    }
}
