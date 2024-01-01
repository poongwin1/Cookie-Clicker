package org.mellerby;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;

public class GUI {

    private final Game game;
    private final JLabel cookieDisplay;
    private final JLabel cpsDisplay;
    private final JLabel grandmaDisplay;

    public GUI(Game game) {
        this.game = game;
        JFrame frame = new JFrame();

        JButton cookie = new JButton("Cookie");
        JButton grandma = new JButton("Grandma");
        ActionListener listener = new ButtonClickListener(); // Use a separate ActionListener class
        cookie.addActionListener(listener);
        grandma.addActionListener(listener);

        cookieDisplay = new JLabel("Cookies: 0");
        cpsDisplay = new JLabel("CPS: 0");
        grandmaDisplay = new JLabel("Grandmas: 0");

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 40, 10, 40));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(cookie);
        panel.add(grandma);
        panel.add(cookieDisplay);
        panel.add(cpsDisplay);
        panel.add(grandmaDisplay);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Cookie Clicker");
        frame.pack();
        frame.setVisible(true);
    }
    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Cookie")) {
                game.incrementCount();
            } else if (e.getActionCommand().equals("Grandma")) {
                game.buyGrandma();
            }
            updateStats();
        }
    }

    public void updateStats() {
        cookieDisplay.setText("Cookies: " + game.getCount());
        cpsDisplay.setText("CPS: " + game.getCps());
        grandmaDisplay.setText("Grandmas: " + game.getGrandmas());
    }

    public static void main(String[] args) {
        Game game = new Game();
        new GUI(game);
    }
}
