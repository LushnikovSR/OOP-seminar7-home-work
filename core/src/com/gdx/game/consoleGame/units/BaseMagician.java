package com.gdx.game.consoleGame.units;

import java.util.ArrayList;

public abstract class BaseMagician extends BaseShooter {

    int mana;
    int maxMana;

    public BaseMagician(String name, int x, int y) {
        super(name, x, y);
        this.mana = 20;
        super.setArmor(5);
    }

    protected void takeWeapon(Weapons weapon) {
        switch (weapon) {
            case magic_stuff:
                super.setDamage(300);
            case stick:
                super.setDamage(200);
            case magic_wand:
                super.setDamage(180);
            case sword:
                super.setDamage(150);
            case gloves:
                super.setDamage(100);
            case rings:
                super.setDamage(50);
            case fists:
                super.setDamage(10);
        }
    }

    enum Weapons {
        magic_stuff, stick, magic_wand, sword, gloves, rings, fists
    }

    @Override
    public void step(ArrayList<BaseCharacter> enemyTeam, ArrayList<BaseCharacter> myTeam) {
        if (super.getHealth() == 0 || this.mana == 0 || super.liveFoeIndex(enemyTeam) < 0) {
            return;
        }
        if (existenceWoundedComrade(myTeam)) {
            BaseCharacter injured = this.findWoundedComrade(myTeam);
            this.healComrade(injured);
            this.mana--;
        } else {
            BaseCharacter foe = super.nearestTargetAttack(enemyTeam);
            this.attack(foe);
            this.mana--;
        }
    }

    private boolean existenceWoundedComrade(ArrayList<BaseCharacter> myTeam) {
        for (BaseCharacter unit : myTeam) {
            if ((unit.getHealth() > 0) && (unit.getHealth() < unit.getMaxHealth())) {
                return true;
            }
        }
        return false;
    }

    private BaseCharacter findWoundedComrade(ArrayList<BaseCharacter> myTeam) {
        int index = 0;
        float residualHealth = 1;
        for (int i = 0; i < myTeam.size(); i++) {
            if ((myTeam.get(i).getHealth() > 0) && (heroHealthPercentage(myTeam, i) < residualHealth)) {
                residualHealth = heroHealthPercentage(myTeam, i);
                index = i;
            }
        }
        return myTeam.get(index);
    }

    private float heroHealthPercentage(ArrayList<BaseCharacter> myTeam, int index) {
        return (float) myTeam.get(index).getHealth() / myTeam.get(index).getMaxHealth();
    }

    private void healComrade(BaseCharacter hero) {
        if (hero.getHealth() >= hero.getMaxHealth()) {
            return;
        }
        int addHP = (int) (hero.getHealth() * 0.3);
        if (hero.getHealth() < 10) {
            hero.setHealth((hero.getHealth() * 2));
        } else if ((hero.getHealth() + addHP) < hero.getMaxHealth()) {
            hero.setHealth((hero.getHealth() + addHP));
        } else {
            hero.setHealth(hero.getMaxHealth());
        }

        System.out.print(super.getName() + " magic and ");
        System.out.println(hero.getName() + " get " + addHP + " points of health ");
    }

    @Override
    protected void attack(BaseCharacter target) {
        if ((target.getState() == State.live) || (target.getState() == State.busy)) {
            int points = (int) Math.floor(advantagePower(target) * advantageGrowth(target)
                    * (getDamage() * getShootingAccuracy())) - target.getArmor();
            if (points > 0) {
                target.subtractHealthPoints(points);
                System.out.print(super.getName() + " magic and ");
                System.out.println(target.getName() + " less " + points + " points of health ");
            }
            if (target.getHealth() <= 0) {
                target.setState(State.dead);
                target.setHealth(0);
                System.out.println(target.getName() + " is " + target.getState());
                target = null;
            }
        }
    }

    @Override
    public String getInfo() {
        return String.format("%s mana: %d ", super.getInfo(), this.mana);
    }
}
