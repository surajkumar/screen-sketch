package io.github.surajkumar.screen;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class HelpScreen {
    private final JFrame frame;

    public HelpScreen() {
        frame = new JFrame("Information");
        frame.setSize(500, 320);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        /* Heading */
        JLabel headingLabel = new JLabel("Screen Sketch - Help");
        headingLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
        headingLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(headingLabel, BorderLayout.NORTH);

        /* Information Content */
        JTextArea helpText = new JTextArea();
        String content =
                """
                ------------------------------------------------------------
                Screen Sketch - Help & Information!
                ------------------------------------------------------------
                Useful to know:

                When you are drawing a shape, you cannot access your desktop!

                To resume Desktop interact press the ESC key.

                For further assistance, create an issue on our GitHub.
                """;

        helpText.setText(content);

        // Add padding to the JTextArea
        helpText.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Increase font size
        helpText.setFont(new Font("Helvetica", Font.PLAIN, 16));

        helpText.setEditable(false);
        helpText.setLineWrap(true);
        helpText.setWrapStyleWord(true);

        panel.add(new JScrollPane(helpText), BorderLayout.CENTER);
        frame.add(panel);
    }

    public void show() {
        frame.setVisible(true);
    }

    public void hide() {
        frame.setVisible(false);
    }
}
