package io.github.surajkumar.screen;

import io.github.surajkumar.screen.drawing.DrawingFrame;
import io.github.surajkumar.screen.drawing.shapes.ShapeManager;
import io.github.surajkumar.screen.drawing.shapes.ShapeType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class UserSelectionFrame {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserSelectionFrame.class);
    private final JFrame frame;
    private final DrawingFrame drawFrame;
    private final ShapeManager shapeManager;
    private int mouseX, mouseY;

    public UserSelectionFrame(ShapeManager shapeManager, DrawingFrame drawFrame) {
        this.drawFrame = drawFrame;
        this.shapeManager = shapeManager;
        frame = new JFrame("Screen Sketch");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.getContentPane().setBackground(Color.BLACK);
        frame.getContentPane().setForeground(Color.BLACK);

        frame.setAlwaysOnTop(true);
        frame.setFocusable(true);
        frame.setResizable(false);
        frame.setBounds(drawFrame.getFrame().getWidth() / 2 - 200, 10, 200, 400);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        frame.setUndecorated(true);

        JPanel titleBar = new JPanel();
        titleBar.setBackground(new Color(50, 50, 50)); // Set the title area color
        titleBar.setLayout(new FlowLayout(FlowLayout.RIGHT));

        // Add a close button to the title bar
        JButton closeButton = new JButton("X");
        closeButton.setFocusPainted(false);
        closeButton.setBorderPainted(false);
        closeButton.setBackground(new Color(50, 50, 50));
        closeButton.setForeground(Color.WHITE);
        closeButton.addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        System.exit(0);
                    }
                });
        titleBar.add(closeButton);

        titleBar.addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        mouseX = e.getX();
                        mouseY = e.getY();
                    }
                });

        titleBar.addMouseMotionListener(
                new MouseAdapter() {
                    @Override
                    public void mouseDragged(MouseEvent e) {
                        int x = e.getXOnScreen() - mouseX;
                        int y = e.getYOnScreen() - mouseY;
                        frame.setLocation(x, y);
                    }
                });

        // Add the title bar to the content pane
        frame.getContentPane().add(titleBar, BorderLayout.NORTH);

        addComponents();
        frame.pack();
    }

    private JButton createActionButton(
            String text, Color backgroundColor, Color foregroundColor, Runnable action) {
        JButton button = new JButton(text);
        button.setBackground(backgroundColor);
        button.setForeground(foregroundColor);
        button.setBorderPainted(false);
        button.addActionListener(e -> action.run());
        return button;
    }

    private void addComponents() {
        frame.add(
                createActionButton(
                        "Move",
                        new Color(20, 20, 20),
                        Color.WHITE,
                        () -> {
                            drawFrame.blockDesktopInteraction();
                            shapeManager.setSelectedType(ShapeType.NONE);
                            shapeManager.setMovingShape(true);
                        }));

        frame.add(
                createActionButton(
                        "Square",
                        new Color(20, 20, 20),
                        Color.YELLOW,
                        () -> {
                            drawFrame.blockDesktopInteraction();
                            shapeManager.setSelectedType(ShapeType.SQUARE);
                        }));

        frame.add(
                createActionButton(
                        "Circle",
                        new Color(20, 20, 20),
                        Color.YELLOW,
                        () -> {
                            drawFrame.blockDesktopInteraction();
                            shapeManager.setSelectedType(ShapeType.CIRCLE);
                        }));

        frame.add(
                createActionButton(
                        "Cross",
                        new Color(20, 20, 20),
                        Color.RED,
                        () -> {
                            drawFrame.blockDesktopInteraction();
                            shapeManager.setSelectedType(ShapeType.CROSS);
                        }));

        frame.add(
                createActionButton(
                        "Tick",
                        new Color(20, 20, 20),
                        Color.GREEN,
                        () -> {
                            drawFrame.blockDesktopInteraction();
                            shapeManager.setSelectedType(ShapeType.TICK);
                        }));

        frame.add(
                createActionButton(
                        "Arrow",
                        new Color(20, 20, 20),
                        Color.WHITE,
                        () -> {
                            drawFrame.blockDesktopInteraction();
                            shapeManager.setSelectedType(ShapeType.ARROW);
                        }));

        frame.add(
                createActionButton(
                        "Free Hand",
                        new Color(20, 20, 20),
                        Color.MAGENTA,
                        () -> {
                            drawFrame.blockDesktopInteraction();
                            shapeManager.setSelectedType(ShapeType.FREE);
                        }));

        frame.add(
                createActionButton(
                        "Select Color",
                        new Color(20, 20, 20),
                        Color.CYAN,
                        () ->
                                shapeManager.setSelectedColor(
                                        JColorChooser.showDialog(
                                                null, "Choose a color", Color.RED))));

        frame.add(
                createActionButton(
                        "Clear",
                        new Color(20, 20, 20),
                        Color.WHITE,
                        () -> {
                            shapeManager.getShapes().clear();
                            shapeManager.setSelectedType(ShapeType.NONE);
                            LOGGER.info("Cleared screen");
                        }));

        frame.add(
                createActionButton("Undo", new Color(20, 20, 20), Color.WHITE, shapeManager::undo));

        frame.add(
                createActionButton("Redo", new Color(20, 20, 20), Color.WHITE, shapeManager::redo));

        frame.add(
                createActionButton(
                        "Reset Color",
                        new Color(20, 20, 20),
                        Color.WHITE,
                        shapeManager::resetBackToDefaultColor));
    }

    public void show() {
        frame.setVisible(true);
    }
}
