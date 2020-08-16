package com.company;

import java.util.Random;

public class Main {

    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefenceType = "";
    public static int[] heroesHealth = {250, 260, 270, 280, 450, 300,290,310};
    public static int[] heroesDamage = {20, 15, 25, 0, 10, 20,15,30};
    public static String[] heroesAttackType = {"Physical",
            "Magical", "Kinetic", "Medic", "Golem", "Luky","Berserk", "Thor"};
    public static int roundNumber = 0;

    public static void main(String[] args) {
        String i = hhh();
        System.out.println(i);
        System.out.println("Game start");
        printStatistics();
        while (!isGameFinished()) {
            roundNumber++;
            System.out.println("Round: " + roundNumber);
            round();
        }
    }

    public static void changeBossDefence() {
        Random r = new Random();
        int randomIndex = r.nextInt(heroesAttackType.length); //0,1,2
        bossDefenceType = heroesAttackType[randomIndex];
        System.out.println("Boss choose " + bossDefenceType);
    }

    public static void round() {
        changeBossDefence();
        heroesHit();
        health();
        if (bossHealth > 0) {
            bossHits();
        }
        printStatistics();
    }

    private static String hhh() {
        return "ugnujjjj";
    }

    private static void health() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] < 100 && heroesHealth[3] > 0 && heroesHealth[i] > 0) {
                heroesHealth[i] += 40;
                System.out.println("медик вылечил " + heroesAttackType[i] + " на 40");
            }
        }
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }

    public static void bossHits() {
       if (!ifSleep) {
           for (int i = 0; i < heroesHealth.length; i++) {
               if (heroesHealth[i] > 0) {
                   if (heroesHealth[i] < bossDamage) {
                       heroesHealth[i] = 0;

                   } else {
                       heroesHealth[i] = heroesHealth[i] - bossDamage;

                   }

               }
           }
       }
        Golem();
        Luky();
        Berserk();
        Thor();
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0) {
                if (bossHealth > 0) {
                    if (bossDefenceType == heroesAttackType[i]) {
                        Random r = new Random();
                        int coef = r.nextInt(8) + 2; //2,3,4,5,6,7,8,9
                        if (bossHealth - heroesDamage[i] * coef < 0) {
                            bossHealth = 0;
                        } else {
                            bossHealth = bossHealth - heroesDamage[i] * coef;
                        }
                        System.out.println("Critical attack: "
                                + (heroesDamage[i] * coef));
                    } else {
                        if (bossHealth - heroesDamage[i] < 0) {
                            bossHealth = 0;
                        } else {
                            bossHealth = bossHealth - heroesDamage[i];
                        }
                    }
                } else {
                    break;
                }
            }
        }
    }

    public static void printStatistics() {
        System.out.println("__________________");
        System.out.println("Boss health: " + bossHealth);
        for (int i = 0; i < heroesAttackType.length; i++) {
            System.out.println(heroesAttackType[i]
                    + " Health: " + heroesHealth[i]);
        }
        System.out.println("__________________");
    }

    //    public static int[] heroesHealth = {250, 260, 270, 280,300};
    public static void Golem() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[4] > 0 && heroesHealth[i] > 0 && !ifSleep) {
                heroesHealth[i] = heroesHealth[i] + bossDamage / 5;
                heroesHealth[4] -= bossDamage / 5;
            }
        }

    }

    public static void Luky() {
        Random r = new Random();
        int Luky = r.nextInt(2) + 1;
        if (Luky == 2 && heroesHealth[4] > 0) {
            heroesHealth[5] += bossDamage - 10;
            System.out.println("Пропускает удар Босса!");

        }
    }
public static void Berserk(){
        int Berserk = 20;
        if(bossHealth > 0 &&!ifSleep){
            heroesHealth[6] += Berserk;
            bossHealth -= heroesDamage[6]+Berserk;
        }
}
public static boolean ifSleep = false;
public static void Thor(){
        Random r = new Random();
        int Thor = r.nextInt(2)+1;
        if (Thor==1 && heroesHealth[7]>0) {
            ifSleep = true;
            System.out.println("Thor оглушил Босса!");

        }
}

}
