package io.github.surajkumar.input;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

import io.github.surajkumar.screen.drawing.DrawingFrame;
import io.github.surajkumar.screen.drawing.shapes.ShapeManager;
import io.github.surajkumar.screen.drawing.shapes.ShapeType;

public class KeyboardInputListener implements NativeKeyListener {
    private static final int ESC_KEY_CODE = 1;
    private final ShapeManager shapeManager;
    private final DrawingFrame drawingFrame;

    public KeyboardInputListener(ShapeManager shapeManager, DrawingFrame drawingFrame) {
        this.shapeManager = shapeManager;
        this.drawingFrame = drawingFrame;
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
        if (e.getKeyCode() == ESC_KEY_CODE) {
            shapeManager.setSelectedType(ShapeType.NONE);
            drawingFrame.resumeDesktopInteraction();
        }
    }
}
