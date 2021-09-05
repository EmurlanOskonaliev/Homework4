package com.company;

import java.time.chrono.HijrahEra;
import java.util.Random;

public class Main {

    public static int roundNumber = 1;
    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefence = "";
    public static String[] heroesAttackType = {
            "Рыцаря", "Мака", "Кинетика", "Голем", "Локи", "Берсерк", "Тор", "Медик"};
    public static int[] heroesHealth = {260, 270, 250, 350, 230, 290, 240, 280};
    public static int[] heroesDamage = {15, 20, 25, 5, 30, 35, 40,10};

    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()) {
            round();
        }
    }

    public static void chooseBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length); // 0, 1, 2
        bossDefence = heroesAttackType[randomIndex];
        System.out.println("Босс выбирает " + bossDefence);
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Герои выиграли!!!");
            return true;
        }
        /*if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0
                && heroesHealth[2] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;*/
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Босс выиграл!!!");
        }
        return allHeroesDead;
    }

    public static void round() {
        System.out.println("РАУНД: " + roundNumber);
        chooseBossDefence();
        bossHits();
        heroesHit();
        Medic();
        Golem();
        Lucky();
        Berserk();
        Thor();
        printStatistics();
        roundNumber++;
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (heroesAttackType[i] == bossDefence) {
                    Random random = new Random();
                    int coeff = random.nextInt(11); // 0,1,2,3,4,5,6,7,8
                    if (bossHealth - heroesDamage[i] * coeff < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * coeff;
                    }
                    System.out.println("Критическое повреждение "
                            + heroesDamage[i] * coeff);
                } else {
                    if (bossHealth - heroesDamage[i] < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i];
                    }
                }
                heroesDamage[5]=35;
            }
        }
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }

    public static void printStatistics() {
        System.out.println("________________");
        System.out.println("Здоровье босса: " + bossHealth +
                "; Урон босса: " + bossDamage);
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(
                    heroesAttackType[i] + " Здоровье: "
                            + heroesHealth[i] +
                            "; " + " Урон " + heroesAttackType[i] + ":"
                            + heroesDamage[i]);
        }
        System.out.println("________________");
    }

    public static void Medic(){
    	 int hilding = 30;
		for (int i = 0; i <6 ; i++) {
			if (heroesHealth[i] < 100 && heroesHealth[i] > 0 && heroesHealth[7]>0) {
				heroesHealth[i] = heroesHealth[i] + hilding;

			}
		}
		System.out.println("Медик вылечил");
	}

	public static void Golem(){
    	Random random = new Random();
    	int ra = random.nextInt(3);
    	switch (ra){
			case 1:
				if (heroesHealth[3]>0){
					for (int i = 0; i < heroesHealth.length; i++) {
						heroesHealth[i]= heroesHealth[i]+10;
					}
					heroesHealth[3]= heroesHealth[3]-70;
					System.out.println("Голем использовал свою спасобность");
				}
		}
	}

    public static void Lucky (){
    	Random random= new Random();
		boolean rm = random.nextBoolean();
		if (heroesHealth[4]>0);{
			if (!(rm)){
				heroesHealth[4] = heroesHealth[4]+ bossDamage;
				if (heroesHealth[4]> 0){
					heroesHealth[4] = heroesHealth[4] - bossDamage;
				}
			}else  if (rm){
				heroesHealth[4] = heroesHealth[4];
			}
		}

	}


	public static void Berserk(){
    	Random random = new Random();
    	int run = 15;
    	int ra = random.nextInt(3)+1;
    	switch (ra){
			case 1: heroesDamage[5]= (heroesDamage[5] + bossDamage) - run;
				System.out.println("Берсерк наносит урон макс-но:" + run );
		}
	}

	public static void Thor(){
    	Random random = new Random();
    	boolean rd = random.nextBoolean();
    	if (heroesHealth[6]>0){
    		if (!(rd)) {
    			bossDamage = 50;
			}else if (rd){
    			bossDamage = 0;
				System.out.println("Тор усипил Босса");
			}
		}
	}
}
