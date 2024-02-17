package io.github.surajkumar.screen.drawing;

import io.github.surajkumar.screen.NativeFrameAction;
import io.github.surajkumar.screen.drawing.shapes.ShapeManager;

import java.awt.Color;

import javax.swing.JFrame;

public class DrawingFrame {
    public static final Color TRANSPARENT = new Color(0, 0, 0, 0);
    private final JFrame frame;
    private NativeFrameAction nativeFrameAction;

    public DrawingFrame(ShapeManager shapeManager, int width, int height) {
        DrawingPanel drawingPanel = new DrawingPanel(shapeManager);
        drawingPanel.setBackground(TRANSPARENT);

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.setAlwaysOnTop(true);
        frame.setLocationRelativeTo(null);
        frame.setBackground(TRANSPARENT);
        frame.setBounds(0, 0, width, height);
        frame.add(drawingPanel);
    }

    public void show() {
        frame.setVisible(true);
    }

    public void updateScreen() {
        frame.repaint();
    }

    public void blockDesktopInteraction() {
        nativeFrameAction.disablePassThrough();
        frame.setBackground(Color.BLACK);
        frame.setOpacity(0.10F);
    }

    public void resumeDesktopInteraction() {
        nativeFrameAction.disablePassThrough();
        frame.setBackground(TRANSPARENT);
        frame.setOpacity(1F);
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setNativeFrameAction(NativeFrameAction nativeFrameAction) {
        this.nativeFrameAction = nativeFrameAction;
    }
}
