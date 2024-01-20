package org.mellerby;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.*;
import java.awt.*;

public class GUI {

    private final Game game;
    private final JButton cookieButton;
    private final JButton cursorButton;
    private final JButton grandmaButton;
    private final JButton farmButton;
    private final JLabel cookieDisplay;
    private final JLabel cpsDisplay;
    private final JLabel cursorDisplay;
    private final JLabel grandmaDisplay;
    private final JLabel farmDisplay;

    private static final Dimension frameDimension = new Dimension(600, 250);
    private static final String frameTitle = "Cookie Clicker";
    DecimalFormat roundingFormat = new DecimalFormat("0.00");
    DecimalFormat wholeFormat = new DecimalFormat("0");

    public GUI(Game game) {
        this.game = game;

        JFrame frame = new JFrame();
        frame.setPreferredSize(frameDimension);
        frame.setTitle(frameTitle);

        cookieButton = new JButton("Cookie");
        cursorButton = new JButton("Cursor");
        grandmaButton = new JButton("Grandma");
        farmButton = new JButton("Farm");

        ActionListener listener = new ButtonClickListener();
        cookieButton.addActionListener(listener);
        cursorButton.addActionListener(listener);
        grandmaButton.addActionListener(listener);
        farmButton.addActionListener(listener);

        cookieDisplay = new JLabel("Cookies: 0");
        cpsDisplay = new JLabel("CPS: 0");
        cursorDisplay = new JLabel("Cursors: 0");
        grandmaDisplay = new JLabel("Grandmas: 0");
        farmDisplay = new JLabel("Farms: 0");

        JPanel cookiePanel = new JPanel();
        cookiePanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 10, 40));
        cookiePanel.setLayout(new GridLayout(0, 1));
        cookiePanel.add(cookieButton);
        cookiePanel.add(cookieDisplay);
        cookiePanel.add(cpsDisplay);

        JPanel shopPanel = new JPanel();
        shopPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 10, 40));
        shopPanel.setLayout(new GridLayout(0, 1));
        shopPanel.add(cursorButton);
        shopPanel.add(grandmaButton);
        shopPanel.add(farmButton);

        JPanel ownedPanel = new JPanel();
        ownedPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 10, 40));
        ownedPanel.setLayout(new GridLayout(0, 1));
        ownedPanel.add(cursorDisplay);
        ownedPanel.add(grandmaDisplay);
        ownedPanel.add(farmDisplay);

        frame.add(cookiePanel, BorderLayout.WEST);
        frame.add(shopPanel, BorderLayout.EAST);
        frame.add(ownedPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == cookieButton) {
                game.clickedCookie();
            } else if (e.getSource() == cursorButton) {
                game.buyCursor();
            } else if (e.getSource() == grandmaButton) {
                game.buyGrandma();
            } else if (e.getSource() == farmButton) {
                game.buyFarm();
            }
            updateStats();
        }
    }
    
    public void updateStats() {
        cursorButton.setText(formatButton("Cursor", wholeFormat.format(game.getCursorCost())));
        grandmaButton.setText(formatButton("Grandma", wholeFormat.format(game.getGrandmaCost())));
        farmButton.setText(formatButton("Farms", wholeFormat.format(game.getFarmCost())));
        cookieDisplay.setText(formatDisplay("Cookies", roundingFormat.format(game.getCookies())));
        cpsDisplay.setText(formatDisplay("CPS", roundingFormat.format(game.getGuiCps())));
        cursorDisplay.setText(formatDisplay("Cursors", wholeFormat.format(game.getCursors())));
        grandmaDisplay.setText(formatDisplay("Grandmas", wholeFormat.format(game.getGrandmas())));
        farmDisplay.setText(formatDisplay("Farms", wholeFormat.format(game.getFarms())));
    }
    private String formatButton(String name, String cost) {
        return String.format("%s - Cost: %s", name, cost);
    }
    
    private String formatDisplay(String name, String value) {
        return String.format("%s: %s", name, value);
    }

    public static void main(String[] args) {
        Game game = new Game();
        GUI gui = new GUI(game);
    }
}
