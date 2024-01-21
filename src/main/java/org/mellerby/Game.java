package org.mellerby;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Game {

    private GUI gui;
    private long cookies;
    private long cps;
    public static double costMultiplier = 1.15;
    private Map<String, Building> buildings;

    public Game(){
        gui = null;
        buildings = new LinkedHashMap<>();
        buildings.put("Cursor", new Building(15, 0.1));
        buildings.put("Grandma", new Building(100, 1));
        buildings.put("Farm", new Building(1100, 8));
        buildings.put("Mine", new Building(12000, 47));
        buildings.put("Factory", new Building(130000, 260));
        buildings.put("Bank", new Building(1400000, 1400));
        buildings.put("Temple", new Building(20000000, 7800));
        buildings.put("Wizard Tower", new Building(330000000, 44000));
        buildings.put("Shipment", new Building(5100000000L, 260000));
        buildings.put("Alchemy Lab", new Building(75000000000L, 1600000));
        buildings.put("Portal", new Building(1000000000000L, 10000000));
        buildings.put("Time Machine", new Building(14000000000000L, 65000000));
        buildings.put("Antimatter Condenser", new Building(170000000000000L, 430000000));
        buildings.put("Prism", new Building(2100000000000000L, 2900000000L));
        buildings.put("Chancemaker", new Building(26000000000000000L, 21000000000L));
        buildings.put("Fractal Engine", new Building(310000000000000000L, 150000000000L));
        buildings.put("Monika", new Building(9223372036854775807L, 110000000000000000L));
    }
    
    public static void main(String[] args){
        Game game = new Game();
        GUI gui = new GUI(game);
        game.setGUI(gui);
    
        // Calculate cookies
        ScheduledExecutorService updateExecutor = Executors.newScheduledThreadPool(1);
        updateExecutor.scheduleAtFixedRate(() -> {
            game.cookies += game.cps;
            gui.updateStats();
        }, 0, 1, TimeUnit.SECONDS);
    }

    // Button click methods
    public void clickedCookie() {
        cookies++;
    }
    public void buyBuilding(String buildingName) {
        Building building = buildings.get(buildingName);
        if (cookies >= building.getCost()) {
            cookies -= building.getCost();
            building.incrementCount();
            cps += building.getCps();
        }
    }

    // __________________DEBUG BUTTON______________________
    public void debugAddCookies(double amount) {
        this.cookies += amount;
    }
    // __________________DEBUG BUTTON______________________

    // Getters and setters
    public double getCookies() {
        return cookies;
    }
    public double getGuiCps() {
        return cps;
    }
    public void setGUI(GUI gui) {
        this.gui = gui;
    }
    public Building getBuilding(String buildingName) {
        return buildings.get(buildingName);
    }
    public Map<String, Building> getBuildings() {
        return buildings;
    }
}