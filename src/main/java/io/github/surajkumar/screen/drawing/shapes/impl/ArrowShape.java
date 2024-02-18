package io.github.surajkumar.screen.drawing.shapes.impl;

import io.github.surajkumar.screen.drawing.shapes.Shape;
import io.github.surajkumar.screen.drawing.shapes.ShapeLocation;

import java.awt.Graphics2D;
import java.awt.geom.Path2D;

public class ArrowShape implements Shape {
    private static final int ARROW_SIZE = 10;
    private final ShapeLocation shapeLocation;

    public ArrowShape(ShapeLocation shapeLocation) {
        this.shapeLocation = shapeLocation;
    }

    @Override
    public void draw(Graphics2D g2d) {
        Path2D path = createArrowPath();
        g2d.draw(path);
    }

    private Path2D createArrowPath() {
        Path2D path = new Path2D.Double();

        double angle =
                Math.atan2(
                        shapeLocation.getEndY() - shapeLocation.getStartY(),
                        shapeLocation.getEndX() - shapeLocation.getStartX());
        double arrowX1 = shapeLocation.getEndX() - ARROW_SIZE * Math.cos(angle - Math.PI / 6);
        double arrowY1 = shapeLocation.getEndY() - ARROW_SIZE * Math.sin(angle - Math.PI / 6);
        double arrowX2 = shapeLocation.getEndX() - ARROW_SIZE * Math.cos(angle + Math.PI / 6);
        double arrowY2 = shapeLocation.getEndY() - ARROW_SIZE * Math.sin(angle + Math.PI / 6);

        path.moveTo(shapeLocation.getStartX(), shapeLocation.getStartX());
        path.lineTo(shapeLocation.getEndX(), shapeLocation.getEndY());
        path.lineTo(arrowX1, arrowY1);
        path.moveTo(shapeLocation.getEndX(), shapeLocation.getEndY());
        path.lineTo(arrowX2, arrowY2);

        return path;
    }
}
