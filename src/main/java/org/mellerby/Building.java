package org.mellerby; 

public class Building {
    private long costInitial;
    private long cost;
    private double cps;
    private long count;

    public Building(long costInitial, double cps) {
        this.costInitial = costInitial;
        this.cost = costInitial;
        this.cps = cps;
        this.count = 0;
    }
    public void incrementCount() {
        this.count++;
        this.cost *= Game.costMultiplier;
    }

    // Getters and setters
    public long getCostInitial() {
        return costInitial;
    }
    public long getCost() {
        return cost;
    }
    public double getCps() {
        return cps;
    }
    public long getCount() {
        return count;
    }
}