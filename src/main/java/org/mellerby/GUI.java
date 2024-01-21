package org.mellerby;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;
import java.awt.*;

public class GUI {

    private final Game game;
    private final JButton cookieButton;
    private final JLabel cookieDisplay;
    private final JLabel cpsDisplay;
    private final Map<String, JButton> buildingButtons = new HashMap<>();
    private final Map<String, JLabel> buildingDisplays = new HashMap<>();
    private static final String[] UNITS = new String[] { "", "", "Million", "Billion", "Trillion", "Quadrillion", "Quintillion", "Patillion" };

    private static final Dimension frameDimension = new Dimension(900, 500);
    private static final String frameTitle = "Cookie Clicker";

    // GUI initialization and setup
    public static void main(String[] args) {
        Game game = new Game();
        GUI gui = new GUI(game);
        game.setGUI(gui);
    }
    public GUI(Game game) {
        this.game = game;

        JFrame frame = new JFrame();
        frame.setPreferredSize(frameDimension);
        frame.setTitle(frameTitle);
        
        cookieButton = new JButton("Cookie");
        ActionListener listener = new ButtonClickListener();
        cookieButton.addActionListener(listener);

        cookieDisplay = new JLabel("Cookies: 0");
        Dimension cookieDisplaySize = new Dimension(150, 10); 
        cookieDisplay.setPreferredSize(cookieDisplaySize); // Set the preferred size to the label
        cpsDisplay = new JLabel("CPS: 0");
        JPanel cookiePanel = new JPanel();
        cookiePanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 10, 40));
        cookiePanel.setLayout(new GridLayout(0, 1));
        cookiePanel.add(cookieButton);
        cookiePanel.add(cookieDisplay);
        cookiePanel.add(cpsDisplay);
        
        JPanel ownedPanel = new JPanel();
        ownedPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 10, 40));
        ownedPanel.setLayout(new GridLayout(0, 1));

        JPanel shopPanel = new JPanel();
        shopPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 10, 40));
        shopPanel.setLayout(new GridLayout(0, 1));
        shopPanel.setPreferredSize(new Dimension(400, 300));
        
        for (String buildingName : game.getBuildings().keySet()) {
            JButton button = new JButton(buildingName);
            button.addActionListener(listener);
            buildingButtons.put(buildingName, button);
            shopPanel.add(button);
        
            JLabel label = new JLabel(buildingName + ": 0");
            buildingDisplays.put(buildingName, label);
            ownedPanel.add(label);
        }
        
        
        // __________________DEBUG BUTTON______________________
        JButton debugButton = new JButton("Debug - Add 1Q Cookies");
            debugButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.debugAddCookies(1_000_000_000_000_000_000L); // Assuming you have a method to add cookies
                updateStats();
            }
        });
        frame.add(debugButton, BorderLayout.SOUTH); 
        // __________________DEBUG BUTTON______________________
        
        frame.add(cookiePanel, BorderLayout.WEST);
        frame.add(shopPanel, BorderLayout.EAST);
        frame.add(ownedPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    // Button listener
    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == cookieButton) {
                game.clickedCookie();
            } else {
                for (Map.Entry<String, JButton> entry : buildingButtons.entrySet()) {
                    if (e.getSource() == entry.getValue()) {
                        game.buyBuilding(entry.getKey());
                        break;
                    }
                }
            }
            updateStats();
        }
    }
    
    // Number formatting and stat updating
    public String formatLargeNumber(double number) {
        if (number < 1_000_000) return String.format("%.0f", number);
        int digitGroups = (int) (Math.log10(number)/Math.log10(1000));
        return new DecimalFormat("#,##0.0").format(number/Math.pow(1000, digitGroups)) + " " + UNITS[digitGroups];
    }
    public void updateStats() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cookies: \n").append(formatLargeNumber(game.getCookies()));
        cookieDisplay.setText(sb.toString());
    
        sb = new StringBuilder();
        sb.append("CPS: \n").append(formatLargeNumber(game.getGuiCps()));
        cpsDisplay.setText(sb.toString());
    
        for (String buildingName : buildingButtons.keySet()) {
            Building building = game.getBuilding(buildingName);
            JButton button = buildingButtons.get(buildingName);
            String cost = formatLargeNumber(building.getCost());
            button.setText(formatButton(buildingName, cost));
    
            JLabel label = buildingDisplays.get(buildingName);
            String count = formatLargeNumber(building.getCount());
            label.setText(formatDisplay(buildingName, count));
        }
    }
    private String formatButton(String name, String cost) {
        return String.format("%s - Cost: %s", name, cost);
    }
    private String formatDisplay(String name, String value) {
        return String.format("%s: %s", name, value);
    }
}
