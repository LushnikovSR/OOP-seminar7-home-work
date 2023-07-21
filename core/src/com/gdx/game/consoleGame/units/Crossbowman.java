package com.gdx.game.consoleGame.units;

import java.util.ArrayList;

public class Crossbowman extends BaseShooter {


    public Crossbowman(String name, int x, int y){
        super(name, x, y);
        super.setInitiative(1);
        super.takeWeapon(Weapons.crossbow);
    }


    @Override
    public void step(ArrayList<BaseCharacter> enemyTeam, ArrayList<BaseCharacter> myTeam) {
        super.step(enemyTeam, myTeam);
        super.arrowSource(myTeam, Peasant.class);
    }

    @Override
    public String getInfo() {
        return super.getInfo();
    }
}
