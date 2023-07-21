package com.gdx.game.consoleGame.units;

import java.util.ArrayList;

public class Wizard extends BaseMagician {

    public Wizard(String name, int x, int y) {
        super(name, x, y);
        super.setInitiative(2);
        super.takeWeapon(Weapons.magic_wand);
    }

    @Override
    public void step(ArrayList<BaseCharacter> enemyTeam, ArrayList<BaseCharacter> myTeam) {
        super.step(enemyTeam, myTeam);
    }

    @Override
    public String getInfo() {
        return super.getInfo();
    }
}