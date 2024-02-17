package io.github.surajkumar.screen.drawing.shapes;

public record ShapeLocation(int startX, int startY, int endX, int endY) {
    public ShapeDimension getDimensions() {
        int x = Math.min(startX, endX);
        int y = Math.min(startY, endY);
        int width = Math.abs(endX - startX);
        int height = Math.abs(endY - startY);
        return new ShapeDimension(x, y, height, width);
    }

    public boolean withinLocation(int x, int y) {
        int minX = Math.min(startX, endX);
        int minY = Math.min(startY, endY);
        int maxX = Math.max(startX, endX);
        int maxY = Math.max(startY, endY);
        return x >= minX && x <= maxX && y >= minY && y <= maxY;
    }
}
