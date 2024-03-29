package io.github.surajkumar.screen.drawing.shapes.impl;

import io.github.surajkumar.screen.drawing.shapes.Shape;
import io.github.surajkumar.screen.drawing.shapes.ShapeLocation;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;

public class CrossShape implements Shape {
    private static final int CROSS_SIZE = 10;
    private final ShapeLocation shapeLocation;

    public CrossShape(ShapeLocation shapeLocation) {
        this.shapeLocation = shapeLocation;
    }

    @Override
    public Color defaultColor() {
        return Color.RED;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setStroke(new BasicStroke(3.0f));
        Path2D path = createCrossPath();
        g2d.draw(path);
    }

    private Path2D createCrossPath() {
        int x = shapeLocation.getStartX();
        int y = shapeLocation.getStartY();
        Path2D path = new Path2D.Double();
        path.moveTo(x - (double) CROSS_SIZE / 2, y - (double) CROSS_SIZE / 2);
        path.lineTo(x + (double) CROSS_SIZE / 2, y + (double) CROSS_SIZE / 2);
        path.moveTo(x + (double) CROSS_SIZE / 2, y - (double) CROSS_SIZE / 2);
        path.lineTo(x - (double) CROSS_SIZE / 2, y + (double) CROSS_SIZE / 2);
        return path;
    }
}
