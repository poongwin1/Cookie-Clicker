package org.mellerby;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Game {

    private GUI gui;
    private double cookies;
    private double cps;
    private int cursors;
    private int grandmas;
    private int farms;

    private double costMultiplier = 1.15;
    private double cursorCostInitial = 15;
    private double cursorCost;
    private double grandmaCostInitial = 100;
    private double grandmaCost;
    private double farmCostInitial = 1100;
    private double farmCost;

    private double cursorCps = 0.1;
    private double grandmaCps = 1;
    private double farmCps = 8;

    public Game(){
        gui = null;
        cursorCost = cursorCostInitial;
        grandmaCost = grandmaCostInitial;
        farmCost = farmCostInitial;
    }
    
    public static void main(String[] args){
        Game game = new Game();
        GUI gui = new GUI(game);
        game.setGUI(gui);

        // Calculate cookies
        ScheduledExecutorService updateExecutor = Executors.newScheduledThreadPool(1);
        updateExecutor.scheduleAtFixedRate(() -> {
            game.cookies += game.cps;
        }, 0, 1, TimeUnit.SECONDS);

        // Update cps
        ScheduledExecutorService cpsExecutor = Executors.newScheduledThreadPool(1);
        cpsExecutor.scheduleAtFixedRate(() -> {
            game.cps = (game.cursors * game.cursorCps) + (game.grandmas * game.grandmaCps) + (game.farms * game.farmCps);
            gui.updateStats();
        }, 0, 1, TimeUnit.MILLISECONDS);
    }

    // Button click methods
    public void clickedCookie() {
        cookies++;
    }
    public void buyCursor() {
        if (cookies >= cursorCost) {
            cursors++;
            cookies -= cursorCost;
            cursorCost = cursorCostInitial * costMultiplier * Math.pow(costMultiplier, cursors);
        }
    }
    public void buyGrandma() {
        if (cookies >= grandmaCost) {
            grandmas++;
            cookies -= grandmaCost;
            grandmaCost = grandmaCostInitial * costMultiplier * Math.pow(costMultiplier, grandmas);
        }
    }
    public void buyFarm() {
        if (cookies >= farmCost) {
            farms++;
            cookies -= farmCost;
            farmCost = farmCostInitial * costMultiplier * Math.pow(costMultiplier, farms);
        }
    }

    // Getters and setters
    public double getCookies() {
        return cookies;
    }
    public double getCursors() {
        return cursors;
    }
    public double getCursorCost() {
        return cursorCost;
    }
    public double getGrandmas() {
        return grandmas;
    }
    public double getGrandmaCost() {
        return grandmaCost;
    }
    public double getFarms() {
        return farms;
    }
    public double getFarmCost(){
        return farmCost;
    }
    public double getGuiCps() {
        return cps;
    }
    public void setGUI(GUI gui) {
        this.gui = gui;
    }
}