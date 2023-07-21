package com.gdx.game.consoleGame.units;

import java.util.ArrayList;
import java.util.Random;

public abstract class BaseShooter extends BaseWarrior {
    private float shootingAccuracy;
    private int arrows;

    public BaseShooter(String name, int x, int y){
        super(name, x, y);
        this.shootingAccuracy = 0.3f;
        this.arrows = new Random().nextInt(10, 30);
        super.setArmor(10);
    }

    protected void takeWeapon(Weapons weapon){
        switch (weapon) {
            case stone:
                setDamage(50);
                break;
            case bow:
                setDamage(100);
                break;
            case crossbow:
                setDamage(150);
                break;
            case gun:
                setDamage(200);
                break;
            case rifle:
                setDamage(250);
                break;
            case zeus_lightning:
                setDamage(600);
                break;
        }
    }
    enum Weapons{
        stone, bow, crossbow, gun, rifle, zeus_lightning
    }

    @Override
    public void step(ArrayList<BaseCharacter> enemyTeam, ArrayList<BaseCharacter> myTeam) {
        if (this.getHealth() == 0 || this.getArrows() == 0 || super.liveFoeIndex(enemyTeam) < 0) {return;}
        BaseCharacter foe = super.nearestTargetAttack(enemyTeam);
        this.attack(foe);
    }

    @Override
    protected void attack(BaseCharacter target){
        if ((target.getState() == State.live) || (target.getState() == State.busy)){
            int points = (int)Math.floor(advantagePower(target) * advantageGrowth(target)
                    * (super.getDamage() * this.getShootingAccuracy()) * advantageAccuracy(target)) - target.getArmor();
            if (points > 0) {
                target.subtractHealthPoints(points);
                setArrows(getArrows() - 1);
                System.out.print(this.getName() + " shoot and ");
                System.out.println(target.getName() + " loses " + points + " points of health" + " less " + target.getHealth() + " HP ");
            }
            if (target.getHealth() <= 0) {
                target.setState(State.dead);
                target.setHealth(0);
                System.out.println(target.getName() + " is " + target.getState());
                target = null;
            }
        }
    }

    protected float getShootingAccuracy(){
        return this.shootingAccuracy;
    }

    private void setShootingAccuracy(float value){
        this.shootingAccuracy = value;
    }

    protected float advantageAccuracy (BaseCharacter target){
        switch ((int) Math.floor(nearestUnit(target))){
            case 1: return 1.5f;
            case 2: return 1.4f;
            case 3: return 1.3f;
            case 4: return 1.2f;
            case 5: return 1.1f;
            case 6: return 1.0f;
            case 7: return 0.9f;
            case 8: return 0.8f;
            case 9: return 0.7f;
            default: return 0.6f;
        }
    }

    protected int getArrows(){ return this.arrows;}

    protected void setArrows(int value){
        this.arrows = value;
    }

    protected boolean existenceFreeBearer(ArrayList <BaseCharacter> myTeam, Class unitClass) {
        for (int i = 0; i < myTeam.size(); i++) {
            if ((myTeam.get(i).getClass() == unitClass) && (isFree(myTeam.get(i)))) {
                return true;
            }
        }
        return false;
    }

    private boolean isFree(BaseCharacter hero) {
        if (!(hero.getState() == State.busy) && !(hero.getState() == State.dead)) {
            return true;
        }
        return false;
    }

    protected void setPeasantStatusBusy(ArrayList<BaseCharacter> myTeam, Class unitClass){
        for (int i = 1; i < myTeam.size(); i++) {
            if ((myTeam.get(i).getClass() == unitClass) && (isFree(myTeam.get(i)))) {
                myTeam.get(i).setState(State.busy);
            }
        }
    }

    protected void arrowSource(ArrayList<BaseCharacter> myTeam, Class bearer){
        if (existenceFreeBearer(myTeam, bearer)){
            setPeasantStatusBusy(myTeam, bearer);
            setArrows(getArrows() + 1);
        }
    }

    @Override
    public String getInfo() {
        return String.format("%s arr: %d ", super.getInfo(), this.getArrows());
    }
}
