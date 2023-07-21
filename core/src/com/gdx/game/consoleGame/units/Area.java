package com.gdx.game.consoleGame.units;

public class Area {
    public int x;
    public int y;
    private int[] coordinates;
    public Area(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "x: " + x + " y: " + y;
    }

    public double nearestUnit(BaseCharacter hero){
        return Math.sqrt(Math.pow(this.x - hero.x, 2) + Math.pow(this.y - hero.y, 2));
    }
}
