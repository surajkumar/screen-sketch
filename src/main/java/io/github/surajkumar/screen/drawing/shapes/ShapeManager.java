package io.github.surajkumar.screen.drawing.shapes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class ShapeManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShapeManager.class);
    private final List<SpaceContainer> shapes = new ArrayList<>();
    private final List<ShapeLocation> draggingPoints = new ArrayList<>();
    private ShapeType selectedType = ShapeType.NONE;
    private Color selectedColor = Color.RED;

    public void addShape(Shape shape, ShapeLocation location) {
        LOGGER.info("Adding shape {}", shape.getClass().getName());
        shapes.add(new SpaceContainer(shape, location, selectedColor));
    }

    public void removeShape(int x, int y) {
        SpaceContainer shapeToRemove = null;

        for (SpaceContainer spaceContainer : shapes) {
            ShapeLocation shapeLocation = spaceContainer.location();
            if (shapeLocation.withinLocation(x, y)) {
                shapeToRemove = spaceContainer;
            }
        }

        if (shapeToRemove != null) {
            LOGGER.info("Removing shape {} at x={} y={}", shapeToRemove.getClass().getName(), x, y);
            shapes.remove(shapeToRemove);
        }
    }

    public List<SpaceContainer> getShapes() {
        return shapes;
    }

    public void addDraggingPoint(ShapeLocation shapeLocation) {
        draggingPoints.add(shapeLocation);
    }

    public List<ShapeLocation> getDraggingPoints() {
        return draggingPoints;
    }

    public void clearDraggingPoints() {
        draggingPoints.clear();
        LOGGER.info("Dragging points have been cleared");
    }

    public ShapeType getSelectedType() {
        return selectedType;
    }

    public void setSelectedType(ShapeType selectedType) {
        this.selectedType = selectedType;
    }

    public void setSelectedColor(Color selectedColor) {
        this.selectedColor = selectedColor;
    }
}
