package io.github.surajkumar;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;

import io.github.surajkumar.input.KeyboardInputListener;
import io.github.surajkumar.input.MouseInputListener;
import io.github.surajkumar.screen.NativeFrameAction;
import io.github.surajkumar.screen.UserSelectionFrame;
import io.github.surajkumar.screen.drawing.DrawingFrame;
import io.github.surajkumar.screen.drawing.shapes.ShapeManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    private static final ScheduledExecutorService EXECUTOR_SERVICE =
            Executors.newScheduledThreadPool(1);

    public static void main(String[] args) {
        LOGGER.info("Starting Screen-Sketch program");

        ShapeManager shapeManager = new ShapeManager();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        DrawingFrame drawingFrame =
                new DrawingFrame(shapeManager, screenSize.width, screenSize.height);
        UserSelectionFrame userSelectionFrame = new UserSelectionFrame(shapeManager, drawingFrame);

        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            LOGGER.error("Failed to register native hook for keyboard and mouse events", ex);
            return;
        }

        MouseInputListener mouseListener = new MouseInputListener(drawingFrame, shapeManager);
        GlobalScreen.addNativeMouseListener(mouseListener);
        GlobalScreen.addNativeMouseMotionListener(mouseListener);
        GlobalScreen.addNativeKeyListener(new KeyboardInputListener(shapeManager, drawingFrame));

        drawingFrame.show();
        userSelectionFrame.show();

        NativeFrameAction nativeFrameAction = new NativeFrameAction(drawingFrame.getFrame());
        nativeFrameAction.allowPassThrough();
        drawingFrame.setNativeFrameAction(nativeFrameAction);

        EXECUTOR_SERVICE.scheduleAtFixedRate(
                drawingFrame::updateScreen, 0, 16, TimeUnit.MILLISECONDS);
    }
}
