package io.github.surajkumar.screen.drawing.shapes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.CopyOnWriteArrayList;

public class ShapeManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShapeManager.class);
    private final List<SpaceContainer> shapes = new CopyOnWriteArrayList<>();
    private final Stack<List<SpaceContainer>> undoStack = new Stack<>();
    private final Stack<List<SpaceContainer>> redoStack = new Stack<>();
    private final List<ShapeLocation> draggingPoints = new CopyOnWriteArrayList<>();
    private ShapeType selectedType = ShapeType.NONE;
    private Color selectedColor = null;
    private boolean movingShape;

    public void addShape(Shape shape, ShapeLocation location) {
        LOGGER.info("Adding shape {}", shape.getClass().getName());
        undoStack.push(new ArrayList<>(shapes));
        redoStack.clear();
        shapes.add(
                new SpaceContainer(
                        shape,
                        location,
                        selectedColor == null ? shape.defaultColor() : selectedColor));
    }

    public void removeShape(int x, int y) {
        SpaceContainer shapeToRemove = getShape(x, y);
        if (shapeToRemove != null) {
            LOGGER.info("Removing shape {} at x={} y={}", shapeToRemove.getClass().getName(), x, y);
            undoStack.push(new ArrayList<>(shapes));
            redoStack.clear();
            shapes.remove(shapeToRemove);
        }
    }

    public void resetBackToDefaultColor() {
        selectedColor = null;
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            redoStack.push(new ArrayList<>(shapes));
            shapes.clear();
            shapes.addAll(undoStack.pop());
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            undoStack.push(new ArrayList<>(shapes));
            shapes.clear();
            shapes.addAll(redoStack.pop());
        }
    }

    public SpaceContainer getShape(int x, int y) {
        SpaceContainer shape = null;
        for (SpaceContainer spaceContainer : shapes) {
            ShapeLocation shapeLocation = spaceContainer.location();
            if (shapeLocation.withinLocation(x, y)) {
                shape = spaceContainer;
            }
        }
        return shape;
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

    public boolean isMovingShape() {
        return movingShape;
    }

    public void setMovingShape(boolean movingShape) {
        this.movingShape = movingShape;
    }
}
