package org.mellerby;

public class Game {

    private GUI gui;
    public float count = 0;
    public float cps = 0;
    public float grandmas = 0;
    public double grandmaCost;

    public Game(){
        gui = null;
        grandmaCost = 115 * 1.15 * Math.pow(1.15, grandmas);
    }
    public void incrementCount() {
        count++;
        System.out.println("Cookie Clicked");
        if (gui != null) {
            gui.updateStats();
        }
    }
    public void buyGrandma() {
        if (count >= grandmaCost) {
            grandmas++;
            count -= (float) grandmaCost;
            grandmaCost = 115 * 1.15 * Math.pow(1.15, grandmas);
            System.out.println("Grandma Bouught");
            if (gui != null) {
                gui.updateStats();
            }
        }
    }
    public float getCount() {
        return count;
    }
    public float getGrandmas() {
        return grandmas;
    }
    public float getCps() {
        return cps;
    }
    public void setGUI(GUI gui) {
        this.gui = gui;
    }
    public static void main(String[] args){
        Game game = new Game();
        GUI gui = new GUI(game);
        game.setGUI(gui);
    }
}
