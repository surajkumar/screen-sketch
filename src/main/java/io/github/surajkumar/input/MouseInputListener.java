package io.github.surajkumar.input;

import com.github.kwhat.jnativehook.mouse.NativeMouseEvent;
import com.github.kwhat.jnativehook.mouse.NativeMouseListener;
import com.github.kwhat.jnativehook.mouse.NativeMouseMotionListener;

import io.github.surajkumar.screen.drawing.DrawingFrame;
import io.github.surajkumar.screen.drawing.shapes.Shape;
import io.github.surajkumar.screen.drawing.shapes.ShapeLocation;
import io.github.surajkumar.screen.drawing.shapes.ShapeManager;
import io.github.surajkumar.screen.drawing.shapes.ShapeType;
import io.github.surajkumar.screen.drawing.shapes.impl.ArrowShape;
import io.github.surajkumar.screen.drawing.shapes.impl.CircleShape;
import io.github.surajkumar.screen.drawing.shapes.impl.CrossShape;
import io.github.surajkumar.screen.drawing.shapes.impl.PixelShape;
import io.github.surajkumar.screen.drawing.shapes.impl.SquareShape;
import io.github.surajkumar.screen.drawing.shapes.impl.TickShape;

import java.awt.Point;

public class MouseInputListener implements NativeMouseListener, NativeMouseMotionListener {
    private static final int LEFT_CLICK = NativeMouseEvent.BUTTON1;
    private static final int RIGHT_CLICK = NativeMouseEvent.BUTTON2;
    private final DrawingFrame frame;
    private final ShapeManager shapeManager;
    private Point startLocation;
    private Point endLocation;
    private boolean performingMouseDrag;

    public MouseInputListener(DrawingFrame frame, ShapeManager shapeManager) {
        this.frame = frame;
        this.shapeManager = shapeManager;
    }

    @Override
    public void nativeMouseDragged(NativeMouseEvent e) {
        if (shapeManager.getSelectedType() == ShapeType.NONE) {
            return;
        }
        performingMouseDrag = true;

        ShapeLocation shapeLocation = new ShapeLocation(e.getX(), e.getY(), e.getX(), e.getY());

        if (shapeManager.getSelectedType() == ShapeType.FREE) {
            shapeManager.addShape(new PixelShape(shapeLocation), shapeLocation);
        } else {
            shapeManager.addDraggingPoint(shapeLocation);
        }
    }

    @Override
    public void nativeMousePressed(NativeMouseEvent e) {
        if (shapeManager.getSelectedType() == ShapeType.NONE) {
            return;
        }

        if (e.getButton() == LEFT_CLICK) {
            startLocation = new Point(e.getX(), e.getY());
            endLocation = new Point(e.getX(), e.getY());
        } else if (e.getButton() == RIGHT_CLICK) {
            shapeManager.removeShape(e.getX(), e.getY());
        }
    }

    @Override
    public void nativeMouseReleased(NativeMouseEvent e) {
        ShapeType shapeType = shapeManager.getSelectedType();

        if (shapeType == ShapeType.FREE || shapeType == ShapeType.NONE) {
            return;
        }

        endLocation = new Point(e.getX(), e.getY());

        ShapeLocation shapeLocation =
                new ShapeLocation(startLocation.x, startLocation.y, endLocation.x, endLocation.y);

        if (shapeType == ShapeType.CROSS) {
            Shape shape = new CrossShape(shapeLocation);
            shapeManager.addShape(shape, shapeLocation);
            shapeManager.setSelectedType(ShapeType.NONE);
            frame.resumeDesktopInteraction();
            return;
        }
        if (shapeType == ShapeType.TICK) {
            Shape shape = new TickShape(shapeLocation);
            shapeManager.addShape(shape, shapeLocation);
            shapeManager.setSelectedType(ShapeType.NONE);
            frame.resumeDesktopInteraction();
            return;
        }

        if (e.getButton() == LEFT_CLICK && performingMouseDrag) {
            Shape shape = null;

            switch (shapeType) {
                case SQUARE -> shape = new SquareShape(shapeLocation);
                case CIRCLE -> shape = new CircleShape(shapeLocation);
                case ARROW -> shape = new ArrowShape(shapeLocation);
            }

            if (shape != null) {
                shapeManager.addShape(shape, shapeLocation);
            }
        }
        frame.resumeDesktopInteraction();
        shapeManager.clearDraggingPoints();
        performingMouseDrag = false;
        shapeManager.setSelectedType(ShapeType.NONE);
    }
}
