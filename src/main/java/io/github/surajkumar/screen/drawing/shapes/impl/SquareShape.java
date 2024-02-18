package io.github.surajkumar.screen.drawing.shapes.impl;

import io.github.surajkumar.screen.drawing.shapes.Shape;
import io.github.surajkumar.screen.drawing.shapes.ShapeDimension;
import io.github.surajkumar.screen.drawing.shapes.ShapeLocation;

import java.awt.Color;
import java.awt.Graphics2D;

public class SquareShape implements Shape {
    private final ShapeLocation shapeLocation;

    public SquareShape(ShapeLocation shapeLocation) {
        this.shapeLocation = shapeLocation;
    }

    @Override
    public Color defaultColor() {
        return Color.YELLOW;
    }

    @Override
    public void draw(Graphics2D g2d) {
        ShapeDimension dimension = shapeLocation.getDimensions();
        g2d.drawRect(dimension.x(), dimension.y(), dimension.width(), dimension.height());
    }
}
