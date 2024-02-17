package io.github.surajkumar.screen.drawing.shapes.impl;

import io.github.surajkumar.screen.drawing.shapes.Shape;
import io.github.surajkumar.screen.drawing.shapes.ShapeLocation;

import java.awt.Graphics2D;
import java.awt.geom.Path2D;

public class TickShape implements Shape {
    private static final int TICK_SIZE = 30;
    private final ShapeLocation shapeLocation;

    public TickShape(ShapeLocation shapeLocation) {
        this.shapeLocation = shapeLocation;
    }

    @Override
    public void draw(Graphics2D g2d) {
        Path2D path = createTickPath();
        g2d.draw(path);
    }

    private Path2D createTickPath() {
        int x = shapeLocation.startX();
        int y = shapeLocation.startY();

        Path2D path = new Path2D.Double();
        path.moveTo(x, y);
        path.lineTo(x + (double) TICK_SIZE / 2, y + (double) TICK_SIZE / 2);
        path.lineTo(x + TICK_SIZE * 2, y); // length

        return path;
    }
}