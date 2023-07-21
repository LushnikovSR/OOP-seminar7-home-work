package com.gdx.game.consoleGame.units;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

public abstract class BaseCharacter extends Area implements CharacterInterface {
    private final static String FORMAT = "#0.00";

    private String name;
    private State state;
    private int health;
    private int maxHealth;
    private int damage;
    private float height;
    private int power;
    public int initiative;
    private int speed;
    private int armor;

    public Area coordinates;

    protected BaseCharacter(int x, int y) {
        super(x, y);
        this.coordinates = new Area(x, y);
        this.state = State.live;
        this.damage = 40;
        this.health = new Random().nextInt(50, 101);
        this.maxHealth = 100;
        this.height = Float.parseFloat((new DecimalFormat(FORMAT)
                .format(new Random().nextFloat(150, 220))).replace(',', '.'));
        this.power = new Random().nextInt(0, 10);
        this.initiative = 1;
        this.speed = new Random().nextInt(0, 10);
        this.armor = 1;
    }

    public BaseCharacter(String name, int x, int y) {
        this(x, y);
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    protected void setName(String name) {
        if (!name.matches("[a-zA-Z][0-9]")) {
            this.name = name;
        } else {
            this.name = "Snowman";
        }
    }

    protected State getState() {
        return this.state;
    }

    protected void setState(State state) {
        this.state = state;
    }

    enum State {
        live, dead, busy
    }

    protected int getDamage() {
        return this.damage;
    }

    protected void setDamage(int weaponDamage) {
        this.damage = weaponDamage;
    }

    public int getHealth() {
        return this.health;
    }

    protected void setHealth(int value) {
        this.health = value;
    }

    protected void subtractHealthPoints(int points) {
        this.health -= points;
    }

    protected int getMaxHealth() {
        return this.maxHealth;
    }

    private void setMaxHealth(int value) {
        this.maxHealth = value;
    }

    protected float getHeight() {
        return this.height;
    }

    private void setHeight(float height) {
        if ((height < 220.0f) & (height > 150.0f)) {
            this.height = height;
        }
    }

    protected int getPower() {
        return this.power;
    }

    protected void setPower(int points) {
        if ((this.power + points) <= 10) {
            this.power += points;
        }
    }

    protected int getArmor() {
        return this.armor;
    }

    protected void setArmor(int armor) {
        this.armor = armor;
    }

    protected int getInitiative() {
        return this.initiative;
    }

    protected void setInitiative(int value) {
        this.initiative = value;
    }

    public BaseCharacter nearestTargetAttack(ArrayList<BaseCharacter> enemyTeam) {
        int index = liveFoeIndex(enemyTeam);
        double nearestFoe = nearestUnit(enemyTeam.get(index));
        for (int i = index + 1; i < enemyTeam.size(); i++) {
            if ((nearestUnit(enemyTeam.get(i)) < nearestFoe) && !(enemyTeam.get(i).state == State.dead)) {
                nearestFoe = nearestUnit(enemyTeam.get(i));
                index = i;
            }
        }
        return enemyTeam.get(index);
    }

    protected int liveFoeIndex(ArrayList<BaseCharacter> enemyTeam) {
        int index;
        for (int i = 0; i < enemyTeam.size(); i++) {
            if (!(enemyTeam.get(i).state == State.dead)) {
                index = i;
                return index;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String getInfo() {
        return String.format("N: %s %s \u2661: %d \u2694: %d Ar %d In %d", this.name, this.getClass().getSimpleName(),
                this.getHealth(), this.getDamage(), this.getArmor(), this.getInitiative());
    }
}
