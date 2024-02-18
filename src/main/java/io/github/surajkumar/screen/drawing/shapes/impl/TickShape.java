package io.github.surajkumar.screen.drawing.shapes.impl;

import io.github.surajkumar.screen.drawing.shapes.Shape;
import io.github.surajkumar.screen.drawing.shapes.ShapeLocation;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;

public class TickShape implements Shape {
    private static final int TICK_SIZE = 10;
    private final ShapeLocation shapeLocation;

    public TickShape(ShapeLocation shapeLocation) {
        this.shapeLocation = shapeLocation;
    }

    @Override
    public Color defaultColor() {
        return Color.GREEN;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setStroke(new BasicStroke(3.0f));
        Path2D path = createTickPath();
        g2d.draw(path);
    }

    private Path2D createTickPath() {
        int x = shapeLocation.getStartX();
        int y = shapeLocation.getStartY();

        Path2D path = new Path2D.Double();
        path.moveTo(x, y);
        path.lineTo(x + (double) TICK_SIZE / 4, y + (double) TICK_SIZE / 2);
        path.quadTo(
                x + (double) TICK_SIZE / 2,
                y + (double) TICK_SIZE,
                x + TICK_SIZE,
                y + (double) TICK_SIZE / 2);
        path.lineTo(x + TICK_SIZE * 3, y); // increased length

        return path;
    }
}
