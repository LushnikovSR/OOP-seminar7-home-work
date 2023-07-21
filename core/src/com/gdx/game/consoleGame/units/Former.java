package com.gdx.game.consoleGame.units;

import java.util.ArrayList;

public class Former extends BaseWarrior {
    public Former(String name, int x, int y){
        super(name, x, y);
        super.setInitiative(4);
        super.takeWeapon(Weapons.stick);
    }

    @Override
    public void step(ArrayList<BaseCharacter> enemyTeam, ArrayList<BaseCharacter> myTeam) {
        super.step(enemyTeam, myTeam);
        if (!(this.getState() == State.dead)){
            super.setState(State.live);
        }
    }

    @Override
    public String getInfo() {
        return super.getInfo();
    }
}
