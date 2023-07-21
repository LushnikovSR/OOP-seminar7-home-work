package com.gdx.game.consoleGame.units;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class BaseWarrior extends BaseCharacter{
    private float impactAccuracy;

    public BaseWarrior(String name, int x, int y){
        super(name, x, y);
        this.impactAccuracy = 0.3f;
        super.setArmor(15);
    }

    protected void takeWeapon(Weapons weapon){
        switch (weapon) {
            case fists:
                setDamage(50);
                break;
            case stick:
                setDamage(100);
                break;
            case knife:
                setDamage(150);
                break;
            case spear:
                setDamage(200);
                break;
            case sword:
                setDamage(250);
                break;
            case excalibur:
                setDamage(300);
                break;
        }
    }
    enum Weapons{
        fists, stick, knife, spear, sword, excalibur
    }

    @Override
    public void step(ArrayList<BaseCharacter> enemyTeam, ArrayList<BaseCharacter> myTeam) {
        if ((super.getHealth() == 0) || (super.liveFoeIndex(enemyTeam) < 0)){return;}
        BaseCharacter foe = super.nearestTargetAttack(enemyTeam);
        System.out.println(this.getName() + " distance to foe: " + distance(foe));
        if (this.distance(foe) < 2){
            this.attack(foe);
        } else {
            int[] coords = this.getStepCoords(foe);
            if (this.stepPossibility(coords, enemyTeam, myTeam)){
                System.out.println(this.getName() + "move to" + Arrays.toString(coords));
                this.moveTo(coords);
            }
        }
    }

    private int distance(BaseCharacter target){
        return Math.abs((this.coordinates.x + this.coordinates.y) - (target.coordinates.x + target.coordinates.y));
    }

    private int[] getStepCoords(BaseCharacter foe){
        int[] coords = {x, y};
        if (Math.abs(this.coordinates.y - foe.coordinates.y) > Math.abs(this.coordinates.x - foe.coordinates.x)){
            if (this.coordinates.y < foe.coordinates.y){
                coords[1] = this.coordinates.y += 1;
            } else {
                coords[1] = this.coordinates.y-=1;
            }
        } else {
            if (this.coordinates.x < foe.coordinates.x){
                coords[0] = this.coordinates.x+=1;
            } else {
                coords[0] = this.coordinates.x-=1;
            }
        }
        return coords;
    }

    private boolean stepPossibility(int[] coords, ArrayList<BaseCharacter> enemyTeam, ArrayList<BaseCharacter> myTeam){
        for (BaseCharacter unit: enemyTeam) {
            if ((unit.coordinates.x == coords[0]) && (unit.coordinates.y == coords[1])){
                return false;
            }
        }
        for (BaseCharacter unit: myTeam) {
            if ((unit.coordinates.x == coords[0]) && (unit.coordinates.y == coords[1])){
                return false;
            }
        }
        return true;

    }

    private void moveTo(int[] coords){
        this.coordinates.x = coords[0];
        this.coordinates.y = coords[1];
    }

    protected void attack(BaseCharacter target){
        if ((target.getState() == State.live) || (target.getState() == State.busy)){
            int points = (int)Math.floor(this.advantagePower(target) * this.advantageGrowth(target)
                    * (super.getDamage() * this.getImpactAccuracy())) - target.getArmor();
            if (points > 0) {
                target.subtractHealthPoints(points);
                System.out.print(this.getName() + " fight and ");
                System.out.println(target.getName() + " loses " + points + " points of health ");
            }
            if (target.getHealth() <= 0) {
                target.setState(State.dead);
                target.setHealth(0);
                System.out.println(target.getName() + " is " + target.getState());
                target = null;
            }
        }
    }

    protected float getImpactAccuracy(){
        return this.impactAccuracy;
    }

    private void setImpactAccuracy(float value){
        this.impactAccuracy = value;
    }

    protected float advantageGrowth (BaseCharacter target){
        float difference = (getHeight() - target.getHeight());
        if (difference > 0 & difference <= 10) { return 1.1f; }
        else if (difference > 10 & difference <= 20) { return 1.2f; }
        else if (difference > 20 & difference <= 30) { return 1.3f; }
        else if (difference > 30 & difference <= 40) { return 1.4f; }
        else if (difference > 40 & difference <= 50) { return 1.5f; }
        else if (difference > 50 & difference <= 60) { return 1.6f; }
        else if (difference > 60 & difference <= 70) { return 1.7f; }
        else return 1.0f;
    }

    protected float advantagePower (BaseCharacter target){
        switch (super.getPower() - target.getPower()){
            case 1: return 1.1f;
            case 2: return 1.2f;
            case 3: return 1.3f;
            case 4: return 1.4f;
            case 5: return 1.5f;
            case 6: return 1.6f;
            case 7: return 1.7f;
            case 8: return 1.8f;
            case 9: return 1.9f;
            default: return 1.0f;
        }
    }

}
