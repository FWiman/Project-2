package se.Fredrik.adventure;

import se.Fredrik.adventure.model.Entity;
import se.Fredrik.adventure.model.Burglar;
import se.Fredrik.adventure.model.Resident;

import java.util.Scanner;

public class AdventureGame {

    private static final String KITCHEN = "kitchen";
    private static final String BEDROOM = "bedroom";
    private static final String OFFICE = "office";
    private static final String HALLWAY = "hallway";
    private static final String LIVING_ROOM = "living room";
    private static final String START = "start";


    Scanner input = new Scanner(System.in);
    Resident resident = new Resident(10, 3, "Resident");
    Burglar burglar = new Burglar(12, 4, "Burglar");

    boolean fighting = true;
    boolean running = true;
    boolean fryingPanFound = false;



    private static String currentLocation = START;

    public void runGame() {
        Scanner sc = new Scanner(System.in);


        System.out.println("Welcome to my adventure game!\n In this game you wake up in the living room by a strange sound....\n");
        System.out.println("MISSION: You must try to reach the office where you can call the police!\n");
        System.out.println("You realise that its a burglar that is sneaking around your house. You need something to fight back with to stand a chance. ");
        System.out.println("You can move by typing: go to kitchen, go to bedroom, go to office, go to hallway, go to living room or exit to turn the game off.\n");
        livingRoom();

        while (running) {


            String directionInput = sc.nextLine();

            switch (directionInput) {
                case "go to bedroom" -> bedroom();
                case "go to office" -> office();
                case "go to kitchen" -> kitchen();
                case "go to hallway" -> hallway();
                case "go to living room" -> livingRoom();
                case "exit" -> running = false;
                default -> System.out.println("Invalid input");
            }
        }
    }

     private void livingRoom() {

         if(!currentLocation.equals(LIVING_ROOM)) {
             System.out.println("You are in the living room...");
             System.out.println("What would you like to do next?");

            currentLocation = LIVING_ROOM;

        } else {
            System.out.println("You cant that room from here. ");
        }
    }

    private void bedroom() {

        if(currentLocation.equals(LIVING_ROOM)) {
            System.out.println("You are in the bedroom...");
            System.out.println("What would you like to do next?");

            currentLocation = BEDROOM;
        } else {
            System.out.println("You cant go this direction from here. ");
        }

    }

    private void office() {
        // Lägg till logik för att ringa polisen
        if(currentLocation.equals(LIVING_ROOM)) {

            if(burglar.isConscious()) {
                System.out.println("You cant call the police until you have dealt with the burglar.");
                System.out.println("What would you like to do next?");
            } else {
                System.out.println("You have beaten the burglar and successfully reached the phone!");
                System.out.println("Would you like to call the police?");

                if(input.nextLine().equals("yes")) {
                    System.out.println("You completed the game and survived the robbery!");
                    running = false;
                } else {
                    System.out.println("You can still look around the house. ");
                }

            }

            currentLocation = OFFICE;
        } else {
            System.out.println("You cant reach that room from here. ");
        }

    }

    private void kitchen() {
        if(currentLocation.equals(LIVING_ROOM)) {
            System.out.println("You are in the kitchen. ");

            if(!fryingPanFound) {
                System.out.println("You found the frying pan, would you like to pick it up?");
                if(input.nextLine().equals("yes")) {
                    resident.setDamage(resident.getDamage() + 3);
                    System.out.println("You picked up the frying pan.");
                    fryingPanFound = true;
                } else {
                    System.out.println("You chose to leave the frying pan.");
                }
                System.out.println("What would you like to do next?");
            }

            currentLocation = KITCHEN;
        } else {
            System.out.println("You cant reach that room from here. ");
        }
    }

    private void hallway() {

        if(currentLocation.equals(LIVING_ROOM)) {
            System.out.println("You are in the hallway.");
            System.out.println("You have come across the burglar!!!!.... Do you want to \"fight\" or \"run away\"?");

            while (fighting && resident.isConscious() && burglar.isConscious()) {
                if(input.nextLine().equals("fight")) {
                    fightOneRound(resident, burglar);
                } else {
                    fighting = false;
                }

            }

            currentLocation = HALLWAY;
        } else {
            System.out.println("You cant reach that room from here. ");
        }

    }

    private void executeAttack(Entity attacker, Entity defender) {
        attacker.punch(defender);
        System.out.println(attacker.getRole() + " Attacked " + defender.getRole() + " for " + attacker.getDamage() + " damage!");

        if(defender.isConscious()) {
            System.out.println(defender.getRole() + " now has: " + defender.getHealth() + " health!");
        } else {
            System.out.println(defender.getRole() + " is unconscious!");

            if(!resident.isConscious()) {
                System.out.println("GAME OVER");
                System.out.println("The burglar took all your things.....");
                running = false;
            } else {
                System.out.println("You can now go to the office and call the police!!");
            }

        }

    }

    private void fightOneRound(Entity player, Entity monster) {
        executeAttack(player, monster);

        if(monster.isConscious()) {
            executeAttack(monster, player);
        }
    }

}