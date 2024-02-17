package io.github.surajkumar.screen;

import io.github.surajkumar.screen.drawing.DrawingFrame;
import io.github.surajkumar.screen.drawing.shapes.ShapeManager;
import io.github.surajkumar.screen.drawing.shapes.ShapeType;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;

public class UserSelectionFrame {
    private final JFrame frame;
    private final DrawingFrame drawFrame;
    private final ShapeManager shapeManager;

    public UserSelectionFrame(ShapeManager shapeManager, DrawingFrame drawFrame) {
        this.drawFrame = drawFrame;
        this.shapeManager = shapeManager;
        frame = new JFrame("Screen Sketch");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setAlwaysOnTop(true);
        frame.setFocusable(true);
        frame.setBounds(0, 500, 200, 400);
        frame.setLayout(new GridLayout(0, 1));
        addComponents();
    }

    private void addComponents() {
        JButton createSquareButton = new JButton("Square");
        createSquareButton.addActionListener(
                e -> {
                    drawFrame.blockDesktopInteraction();
                    shapeManager.setSelectedType(ShapeType.SQUARE);
                });

        JButton createCircleButton = new JButton("Circle");
        createCircleButton.addActionListener(
                e -> {
                    drawFrame.blockDesktopInteraction();
                    shapeManager.setSelectedType(ShapeType.CIRCLE);
                });

        JButton createCrossButton = new JButton("Cross");
        createCrossButton.addActionListener(
                e -> {
                    drawFrame.blockDesktopInteraction();
                    shapeManager.setSelectedType(ShapeType.CROSS);
                });

        JButton createTickButton = new JButton("Tick");
        createTickButton.addActionListener(
                e -> {
                    drawFrame.blockDesktopInteraction();
                    shapeManager.setSelectedType(ShapeType.TICK);
                });

        JButton createArrowButton = new JButton("Arrow");
        createArrowButton.addActionListener(
                e -> {
                    drawFrame.blockDesktopInteraction();
                    shapeManager.setSelectedType(ShapeType.ARROW);
                });

        JButton createFreeButton = new JButton("Free Hand");
        createFreeButton.addActionListener(
                e -> {
                    drawFrame.blockDesktopInteraction();
                    shapeManager.setSelectedType(ShapeType.FREE);
                });

        JButton selectColorButton = new JButton("Select Color");
        selectColorButton.addActionListener(
                e ->
                        shapeManager.setSelectedColor(
                                JColorChooser.showDialog(null, "Choose a color", Color.RED)));

        frame.add(createSquareButton);
        frame.add(createCircleButton);
        frame.add(createCrossButton);
        frame.add(createTickButton);
        frame.add(createArrowButton);

        frame.add(createFreeButton);
        frame.add(selectColorButton);
    }

    public void show() {
        frame.setVisible(true);
    }
}
