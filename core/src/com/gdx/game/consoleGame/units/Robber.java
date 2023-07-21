package com.gdx.game.consoleGame.units;

import java.util.ArrayList;

public class Robber extends BaseWarrior {
    public Robber(String name, int x, int y){
        super(name, x, y);
        super.setInitiative(3);
        super.takeWeapon(Weapons.knife);
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
