package com.gdx.game.consoleGame.main;

import com.badlogic.gdx.utils.reflect.Constructor;
import com.gdx.game.consoleGame.units.*;
import com.gdx.game.consoleGame.views.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public ArrayList<BaseCharacter> teamWhite = new ArrayList<>();
    public ArrayList<BaseCharacter> teamBlack = new ArrayList<>();
    public ArrayList<BaseCharacter> fightingTeams = new ArrayList<>();

    public void main() {
//        ДЗ - 1
//        Крестьянин, разработчик, снайпер, колдун, копейщик, арбалетчик, монах.
//        peasant, developer, sniper, sorcerer, lanceman, crossbowman, monk.

//        Для каждого сформировать список свойств и возможных действий(поведение).
//        Например свойство - скорость, действие - нанести удар.
//        Проанализировать получившиеся свойства и действия персонажей и создать Обобщенный класс
//        описывающий свойства и действия имеющиеся у всех персонажей. Создать этот класс.
//        Создать классы для каждого типа персонажей наследующие и расширяющие абстрактный (обобщённый) класс.
//        В основной программе создать по одному экземпляру каждого типа персонажей.
//
//        ДЗ - 2
//        Добавить файл с описанием интерфейса. В котором описать два метода, void step(); и String getInfo();
//        Реализовать интерфейс в абстрактном классе и в наследниках так, чтобы getInfo возвращал тип персонажа.
//        Создать два списка в классе main. В каждый из списков добавить по десять экземнляров наследников BaseHero.
//        Удалить ненужные методы из абстрактного класса, если такие есть.
//        В main пройти по спискам и вызвать у всех персонажей getInfo.

//        Семинар - 3
//        1. Создать класс с описанием координат, x и y.
//        2. Добавить в абстрактный класс поле с координатами и пробросить его инициализацию
//        до конструкторов персонажей. Farmer farmer = new Farmer(getName(), x, y);
//        3.2. Поиск среди противников наиболее приближенного и вывести в консоль.
//
//        Семинар - 4
//        Step арбалетчика и лучника должны включать следующий интерфейс:
//        1.Если жизни 0 вернуть управление
//        2.Если стрел 0 вернуть управление
//        3.Найти ближайшего противника
//        4.Нанести ему среднее повреждение
//        5.Если среди своих есть крестьянин вернуть управление
//        6.Если нет крестьян уменьшить кол-во стрел на одну и вернуть управление
//
//        Семинар - 5
//        Заполнить степ крестьянина:
//        1. Проверяет здоровье, если больше 0 сбрасывает флаг.
//        Изменить степ лучника:
//        1. Находит первого Крестьянина и ставит ему флаг занят.

//        Семинар - 6
//        1. Проверяем здоровье
//        2. Ищем ближайшего врага
//        3. Двигаемся в сторну врага
//        4. Не наступаем на живых персонажей
//        5. Если расстояние до врага одна клетка бьём его!
//

//        ArrayList<BaseCharacter> teamWhite = new ArrayList<>();
//        ArrayList<BaseCharacter> teamBlack = new ArrayList<>();
//        ArrayList<BaseCharacter> fightingTeams = new ArrayList<>();

        fillTeam(teamWhite, 1, true);
        fillTeam(teamBlack, 10, false);

        fightingTeams.addAll(teamWhite);
        fightingTeams.addAll(teamBlack);
        fightingTeams.sort(Comparator.comparingInt((BaseCharacter hero) -> hero.initiative));

//        Scanner in = new Scanner(System.in);
//        while (true){
//            View.view();
//            in.nextLine();
//
//            for (BaseCharacter hero : fightingTeams) {
//                if (teamWhite.contains(hero)) {
//                    hero.step(teamBlack, teamWhite);
//                } else {
//                    hero.step(teamWhite, teamBlack);
//                }
//            }
//            if (allUnitsDie(teamWhite)){
//                System.out.println("\nteamBlack (Blue) win");
//                break;
//            }
//            if (allUnitsDie(teamBlack)){
//                System.out.println("\nteamWhite (Green) win");
//                break;
//            }
//        }

//        System.out.println("teamWhite: ");
//        showHeroes(teamWhite);
//        System.out.println();
//        System.out.println("teamBlack: ");
//        showHeroes(teamBlack);
//
//        System.out.println();
//        System.out.println("Fight: ");
//        teamWhite.forEach(n -> n.step(teamBlack, teamWhite)); // Поиск среди противников наиболее приближенного и вывести в консоль.
//        teamBlack.forEach(n -> n.step(teamWhite, teamBlack));

//        System.out.println("teamWhite: ");
//        teamWhite.forEach(n -> System.out.println(n.getInfo() + " init: " + n.initiative));
//        System.out.println("teamBlack: ");
//        teamBlack.forEach(n -> System.out.println(n.getInfo() + " init: " + n.initiative));
//        System.out.println("========Fight========");


//        System.out.println("========FINAL========");
//        System.out.println("teamWhite: ");
//        teamWhite.forEach(n -> System.out.println(n.getInfo()));
//        System.out.println("teamBlack: ");
//        teamBlack.forEach(n -> System.out.println(n.getInfo()));
        int a = 0;
    }

    public boolean run(){
        for (BaseCharacter hero : fightingTeams) {
                if (teamWhite.contains(hero)) {
                    hero.step(teamBlack, teamWhite);
                } else {
                    hero.step(teamWhite, teamBlack);
                }
            }
        return !(allUnitsDie(teamWhite)) && !(allUnitsDie(teamBlack));
    }

    public static void fillTeam(ArrayList<BaseCharacter> list, int teamSide, boolean lightForces) {
        final int TEAM_SIZE = 10;

        for (int i = 1; i < TEAM_SIZE + 1; i++) {
            int randNum = new Random().nextInt(0, heroesClasses.values().length / 2);
            if (lightForces == true) {
                switch (randNum) {
                    case 0:
                        list.add(new Sniper("sniper" + "_" + i, teamSide, TEAM_SIZE - i));
                        break;
                    case 1:
                        list.add(new Monk("monk" + "_" + i, teamSide, TEAM_SIZE - i));
                        break;
                    case 2:
                        list.add(new Lanceman("lanceman" + "_" + i, teamSide, TEAM_SIZE - i));
                        break;
                    case 3:
                        list.add(new Former("former" + "_" + i, teamSide, TEAM_SIZE - i));
                        break;
                }
            } else {
                switch (randNum) {
                    case 0:
                        list.add(new Crossbowman("crossbowman" + "_" + i, teamSide, TEAM_SIZE - i));
                        break;
                    case 1:
                        list.add(new Wizard("wizard" + "_" + i, teamSide, TEAM_SIZE - i));
                        break;
                    case 2:
                        list.add(new Robber("robber" + "_" + i, teamSide, TEAM_SIZE - i));
                        break;
                    case 3:
                        list.add(new Peasant("peasant" + "_" + i, teamSide, TEAM_SIZE - i));
                        break;
                }
            }

        }
    }

    enum heroesClasses {
        Sniper, Monk, Lanceman, Former, Crossbowman, Wizard, Robber, Peasant
    }

    static void showHeroes (ArrayList < BaseCharacter > teamName) {
        for (BaseCharacter hero : teamName) {
            System.out.println(hero.getInfo() + " " + hero.getName() + " x: " + hero.x + " y: " + hero.y);
        }
    }

    static boolean allUnitsDie(ArrayList<BaseCharacter> team){
        for (BaseCharacter unit: team) {
            if (unit.getHealth() > 0){
                return false;
            }
        }
        return true;
    }
}