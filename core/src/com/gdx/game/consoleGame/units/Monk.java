package com.gdx.game.consoleGame.units;

import java.util.ArrayList;
import java.util.Random;

public class Monk extends BaseMagician {

    public Monk(String name, int x, int y){
        super(name, x, y);
        super.setInitiative(2);
        super.takeWeapon(Weapons.stick);
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
