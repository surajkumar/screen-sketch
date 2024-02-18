package io.github.surajkumar.screen.drawing.shapes.impl;

import io.github.surajkumar.screen.drawing.shapes.Shape;
import io.github.surajkumar.screen.drawing.shapes.ShapeLocation;

import java.awt.Color;
import java.awt.Graphics2D;

public class PixelShape implements Shape {
    private static final int PIXEL_SIZE = 5;
    private final ShapeLocation shapeLocation;

    public PixelShape(ShapeLocation shapeLocation) {
        this.shapeLocation = shapeLocation;
    }

    @Override
    public Color defaultColor() {
        return Color.MAGENTA;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.fillRect(shapeLocation.getStartX(), shapeLocation.getStartY(), PIXEL_SIZE, PIXEL_SIZE);
    }
}
