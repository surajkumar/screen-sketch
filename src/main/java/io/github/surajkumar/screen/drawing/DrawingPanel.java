package io.github.surajkumar.screen.drawing;

import io.github.surajkumar.screen.drawing.shapes.ShapeLocation;
import io.github.surajkumar.screen.drawing.shapes.ShapeManager;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class DrawingPanel extends JPanel {
    private static final BasicStroke DRAG_STOKE =
            new BasicStroke(
                    2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1, new float[] {5, 5}, 0);
    private final ShapeManager shapeManager;

    public DrawingPanel(ShapeManager shapeManager) {
        this.shapeManager = shapeManager;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        drawDragLines(g2d);
        drawShapes(g2d);
    }

    private void drawShapes(Graphics2D g2d) {
        shapeManager
                .getShapes()
                .forEach(
                        shapeContainer -> {
                            g2d.setColor(shapeContainer.color());
                            g2d.setStroke(new BasicStroke(2.0F));
                            shapeContainer.shape().draw(g2d);
                        });
    }

    private void drawDragLines(Graphics2D g2d) {
        if (shapeManager.getDraggingPoints().size() < 2) {
            return;
        }

        ShapeLocation start = shapeManager.getDraggingPoints().getFirst();
        ShapeLocation end = shapeManager.getDraggingPoints().getLast();

        int x = Math.min(start.startX(), end.startX());
        int y = Math.min(start.startY(), end.startY());
        int width = Math.abs(end.startX() - start.startX());
        int height = Math.abs(end.startY() - start.startY());

        g2d.setStroke(DRAG_STOKE);
        g2d.setColor(Color.GRAY);

        switch (shapeManager.getSelectedType()) {
            case SQUARE -> g2d.drawRect(x, y, width, height);
            case CIRCLE -> g2d.drawOval(x, y, width, height);
            case ARROW -> {
                int startX = start.startX();
                int startY = start.startY();
                int endX = end.startX();
                int endY = end.startY();
                int midX = (startX + endX) / 2;
                int midY = (startY + endY) / 2;
                g2d.drawLine(startX, startY, midX, midY);
                g2d.drawLine(midX, midY, endX, endY);
            }
        }
    }
}
