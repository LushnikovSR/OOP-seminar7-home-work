package com.gdx.game.consoleGame.units;

import java.util.ArrayList;

public interface CharacterInterface {
    public void step(ArrayList<BaseCharacter> enemyTeam, ArrayList<BaseCharacter> myTeam);

    public String getInfo();
}
