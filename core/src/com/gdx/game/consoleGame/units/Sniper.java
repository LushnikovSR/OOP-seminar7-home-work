package com.gdx.game.consoleGame.units;

import java.util.ArrayList;

public class Sniper extends BaseShooter {
    public Sniper(String name, int x, int y){
        super(name, x, y);
        super.setInitiative(1);
        super.takeWeapon(Weapons.gun);
    }

    @Override
    public void step(ArrayList<BaseCharacter> enemyTeam, ArrayList<BaseCharacter> myTeam) {
        super.step(enemyTeam, myTeam);
        super.arrowSource(myTeam, Former.class);
    }

    @Override
    public String getInfo() {
        return super.getInfo();
    }
}
