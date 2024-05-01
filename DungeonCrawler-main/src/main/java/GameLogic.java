import java.util.Random;
import java.util.Scanner;
/**
    Class: GameLogic

    Description:
    Provides all GameLogic for the DungeonCrawler RPG.
    Starts Game, handles battles, creates characters, levels up, handles player input and output,
    the brain of the game.
 */
public class GameLogic {
    Character player;
    //Scanner scanner = new Scanner(System.in, "UTF-8");
    boolean isRunning = false;
    int floor = 1;
    Mediator battleMediator = new BattleMediator();
    Random rand = new Random();

    /**
     Method: readInt
     Inputs: none
     Returns: int

     Description:
     Reads an Integer from the Console.
     */
    /*
    public int readInt(){
        int userInput = 0;
        do{
            try {
                userInput = Integer.parseInt(scanner.next());
            } catch (Exception e){
                userInput = -1;
                System.out.println("Invalid entry, Please enter a number.");
            }
        } while (userInput < 1);
        return userInput;
    }
    */

    /**
     Method: clearConsole
     Inputs: none
     Returns: void

     Description:
     Prints 40 empty lines to clear the console.
     */
    public void clearConsole() {
        for (int i = 0; i < 40; i++) {
            System.out.println();
        }
    }
    /**
     Method: waitForPlayer
     Inputs: none
     Returns: void

     Description:
     Waits for player input.
     */
    /*
    public void waitForPlayer(){
        System.out.println("Enter Anything to continue");
        scanner.next();
    }
    */
    /**
     Method: separator
     Inputs: int num.
     Returns: void

     Description:
     Prints out a separator line of given length.
     */
    public void separator(int num) {
        for (int i = 0; i < num; i++) {
            System.out.print("_");
        }
        System.out.print("\n");
    }
    /**
     Method: header
     Inputs: none
     Returns: void

     Description:
     Prints out the title for the game.
     */
    public void header() {
        System.out.println();
        System.out.println(" ________  ___  ___  ________   ________  "
                + "_______   ________  ________           ________  ____"
                + "____  ________  ___       __   ___       _______   ________     ");
        System.out.println("|\\   ___ \\|\\  \\|\\  \\|\\   ___  \\|\\   _"
                + "___\\|\\  ___ \\ |\\   __  \\|\\   ___  \\        |\\   "
                + "____\\|\\   __  \\|\\   __  \\|\\  \\     |\\  \\|\\  \\ "
                + "    |\\  ___ \\ |\\   __  \\    ");
        System.out.println("\\ \\  \\_|\\ \\ \\  \\\\\\  \\ \\  \\\\ \\  \\"
                + " \\  \\___|\\ \\   __/|\\ \\  \\|\\  \\ \\  \\\\ \\  \\   "
                + "    \\ \\  \\___|\\ \\  \\|\\  \\ \\  \\|\\  \\ \\  \\    \\ "
                + "\\  \\ \\  \\    \\ \\   __/|\\ \\  \\|\\  \\  ");
        System.out.println(" \\ \\  \\ \\\\ \\ \\  \\\\\\  \\ \\  \\\\ \\  "
                + "\\ \\  \\  __\\ \\  \\_|/_\\ \\  \\\\\\  \\ \\  \\\\ \\  \\"
                + "       \\ \\  \\    \\ \\   _  _\\ \\   __  \\ \\  \\  __\\"
                + " \\  \\ \\  \\    \\ \\  \\_|/_\\ \\   _  _\\  ");
        System.out.println("  \\ \\  \\_\\\\ \\ \\  \\\\\\  \\ \\  \\\\ \\  "
                + "\\ \\  \\|\\  \\ \\  \\_|\\ \\ \\  \\\\\\  \\ \\  \\\\ \\"
                + "  \\       \\ \\  \\____\\ \\  \\\\  \\\\ \\  \\ \\  \\ \\  "
                + "\\|\\__\\_\\  \\ \\  \\____\\ \\  \\_|\\ \\ \\  \\\\  \\| ");
        System.out.println("   \\ \\_______\\ \\_______\\ \\__\\\\ \\__\\ "
                + "\\_______\\ \\_______\\ \\_______\\ \\__\\\\ \\__\\       "
                + "\\ \\_______\\ \\__\\\\ _\\\\ \\__\\ \\__\\ \\____________\\"
                + " \\_______\\ \\_______\\ \\__\\\\ _\\ ");
        System.out.println("    \\|_______|\\|_______|\\|__| \\|__|\\|_______|"
                + "\\|_______|\\|_______|\\|__| \\|__|        \\|_______|\\|_"
                + "_|\\|__|\\|__|\\|__|\\|____________|\\|_______|\\|_______|\\|__|\\|__|");
        System.out.println();
        separator(159);
        System.out.println();
        System.out.println("Welcome to Dungeon Crawler");
    }
    /**
     Method: startGame
     Inputs: none
     Returns: void

     Description:
     Manages Character creation for the simulation.
     Starts Gameplay Loop
     */
    public void startGame(int classSelect) {
        boolean hasName = false;
        boolean hasCharacter = false;
        boolean hasRace = false;
        Race playerRace = Race.ORC;
        String name;
        header();
        //waitforPlayer();
        do {
            clearConsole();
            separator(30);
            System.out.println("Please Enter your Characters Name");
            separator(30);
            //name = scanner.next();
            name = "Glenn the Adventurer";
            System.out.println("Hello, " + name + ", \nAre you sure "
                    + "this is what you want to be called?");
            separator(30);
            System.out.println("(1) Yes, That's my name.");
            System.out.println("(2) No, Please call me something else");
            separator(30);
            //int input = readInt();
            int input = 1;
            if (input == 1) {
                hasName = true;
            }
        } while (!hasName);
        do {
            clearConsole();
            separator(30);
            System.out.println("Hello, " + name);
            System.out.println("What Race are you?");
            System.out.println(" (1) Dwarf  -  A race capable of the best Armor");
            System.out.println(" (2) Elf    -  Elven blood makes them Strong in magic");
            System.out.println(" (3) Human  -  Squishy creatures but nimble");
            System.out.println(" (4) Orc    -  Orcs tend to be powerful and brutish");
            separator(30);
            //int input = readInt();
            int input = 4;
            if (input == 1 || input == 2 || input == 3 || input == 4) {
                switch (input) {
                    case 1:
                        playerRace = Race.DWARF;
                        break;
                    case 2:
                        playerRace = Race.ELF;
                        break;
                    case 3:
                        playerRace = Race.HUMAN;
                        break;
                    default:
                        break;
                }
                hasRace = true;
            }
        } while (!hasRace);
        separator(30);
        System.out.println("You have Selected: " + playerRace);
        separator(30);
        //waitforPlayer();
        do {
            clearConsole();
            separator(30);
            System.out.println("Hello, " + name);
            System.out.println("What Class are you?");
            System.out.println(" (1) Assassin -  Known for their swift powerful blows, "
                    + "and low health");
            System.out.println(" (2) Cleric   -  Known for their High Stamina, and Self Heal");
            System.out.println(" (3) Fighter  -  Known for their Balance");
            System.out.println(" (4) Wizard   -  Known for their Powerful Magic");
            separator(30);
            //int input = readInt();
            int input = classSelect;
            if (input == 1 || input == 2 || input == 3 || input == 4) {
                hasCharacter = true;
                CharacterFactory creator = new CharacterFactory();
                player = creator.createCharacter(name, playerRace, input);
            }
        } while (!hasCharacter);
        separator(30);
        System.out.println("You have Selected: " + player.getClassName());
        separator(30);
        clearConsole();
        separator(30);
        player.printCharacter();
        separator(30);
        //waitforPlayer();
        clearConsole();
        isRunning = true;
        gameRun();
    }
    /**
     Method: printMenu
     Inputs: none
     Returns: void

     Description:
     Prints out GameMenu.
     */
    public void printMenu() {
        clearConsole();
        separator(30);
        System.out.println("MENU - Floor: " + floor);
        System.out.println("Choose what you would like to do");
        separator(30);
        System.out.println("(1) Continue to the Fight");
        System.out.println("(2) Check Stats");
        System.out.println("(3) Return to the First Floor");
        System.out.println("(4) Exit Game");
        separator(30);
    }
    /**
     Method: gameRun
     Inputs: none
     Returns: void

     Description:
     GamePlay loop for the player.
     */
    public void gameRun() {
        while (isRunning) {
            printMenu();
            int input = 1;
            if (input == 1) {
                nextFloor();
            } else if (input == 2) {
                player.printCharacter();
            } else if (input == 3) {
                firstFloor();
            } else {
                isRunning = false;
                return;
            }
            if (floor >= 51) {
                clearConsole();
                System.out.println("Congratulations, You have beat the Game");
                isRunning = false;
                return;
            }
        }
    }
    /**
     Method: nextFloor
     Inputs: none
     Returns: void

     Description:
     Increases floor number.
     Starts a battle.
     */
    public void nextFloor() {
        floor += 1;
        battle();
    }
    /**
     Method: battle
     Inputs: none
     Returns: void

     Description:
     Chooses the enemy the player will encounter based on the floor.
     Calls Battle Loop.
     Sends player back to first floor if they die in the battle loop.
     */
    public void battle() {
        Character enemy;
        clearConsole();
        System.out.println("You have encountered an enemy, You'll have to fight it.");
        //waitforPlayer();
        if (floor % 10 == 0) {
            enemy = new LargeEnemy(floor);
        } else if (floor % 5 == 0) {
            enemy = new MediumEnemy(floor);
        } else {
            enemy = new SmallEnemy(floor);
        }
        enemy.printCharacter();
        separator(30);
        player.printCharacter();
        battleLoop(enemy);
        if (player.getHealth() <= 0) {
            player.setXP(20 + player.getXP());
            firstFloor();
        }
    }
    /**
     Method: battleLoop
     Inputs: none
     Returns: void

     Description:
     Keeps the player and enemy fighting until one of them dies.\
     Character with the highest speed will go first.
     */
    public void battleLoop(Character enemy) {
        boolean isFighting = true;
        int round = 1;
        boolean playerFortify = false;
        while (isFighting) {
            if (playerFortify) {
                System.out.println("Your fortify has been removed");
                player.unFortify();
                playerFortify = false;
            }
            if (player.getSpeed() > enemy.getSpeed()) {
                separator(30);
                System.out.println("Please Choose what to do?");
                System.out.println("(1) Attack");
                System.out.println("(2) fortify");
                if (player.getLevel() > 4) {
                    System.out.println("(3) Special Attack");
                }
                separator(30);
                //int input = readInt();
                int input = rand.nextInt(2) + 1;
                if (player.getLevel() > 4) {
                    input = rand.nextInt(3) + 1;
                }
                if (input == 1) {
                    double damage = battleMediator.notifyDamage(enemy, player.attack());
                    enemy.setHealth(enemy.getHealth() - damage);
                    System.out.println("You have hit the enemy for " + damage + " Health");
                } else if (input == 2) {
                    player.fortify();
                    System.out.println("You have fortified yourself, You will take 10 less damage");
                    playerFortify = true;
                } else {
                    double damage = battleMediator.notifyDamage(enemy, player.specialMove());
                    enemy.setHealth(enemy.getHealth() - damage);
                    System.out.println("You have hit the enemy for " + damage + " Health");
                }
                enemy.printCharacter();
                if (enemy.getHealth() <= 0) {
                    System.out.println("Enemy has been vanquished");
                    player.setGold(player.getGold() + enemy.getGold());
                    player.setXP(player.getXP() + enemy.getXP());
                    isFighting = false;
                    return;
                }
                if (round % 5 == 0) {
                    double damage = battleMediator.notifyDamage(player, enemy.specialMove());
                    player.setHealth(player.getHealth() - damage);
                    System.out.println(enemy.getName() + " have hit you for " + damage + " Health");
                } else {
                    double damage = battleMediator.notifyDamage(player, enemy.attack());
                    player.setHealth(player.getHealth() - damage);
                    System.out.println(enemy.getName() + " have hit you for " + damage + " Health");
                }
                player.printCharacter();
                if (player.getHealth() <= 0) {
                    System.out.println("You have been killed");
                    isFighting = false;
                    return;
                }
            } else {
                if (round % 5 == 0) {
                    double damage = battleMediator.notifyDamage(player, enemy.specialMove());
                    player.setHealth(player.getHealth() - damage);
                    System.out.println(enemy.getName() + " have hit you for " + damage + " Health");
                } else {
                    double damage = battleMediator.notifyDamage(player, enemy.attack());
                    player.setHealth(player.getHealth() - damage);
                    System.out.println(enemy.getName() + " have hit you for " + damage + " Health");
                }
                player.printCharacter();
                if (player.getHealth() <= 0) {
                    System.out.println("You have been killed");
                    isFighting = false;
                    return;
                }
                separator(30);
                System.out.println("Please Choose what to do?");
                System.out.println("(1) Attack");
                System.out.println("(2) fortify");
                if (player.getLevel() > 4) {
                    System.out.println("(3) Special Attack");
                }
                separator(30);
                //int input = readInt();
                int input = rand.nextInt(2) + 1;
                if (player.getLevel() > 4) {
                    input = rand.nextInt(3) + 1;
                }
                if (input == 1) {
                    double damage = battleMediator.notifyDamage(enemy, player.attack());
                    enemy.setHealth(enemy.getHealth() - damage);
                    System.out.println("You have hit the enemy for " + damage + " Health");
                } else if (input == 2) {
                    player.fortify();
                    System.out.println("You have fortified yourself, You will take 10 less damage");
                    playerFortify = true;
                } else {
                    double damage = battleMediator.notifyDamage(enemy, player.specialMove());
                    enemy.setHealth(enemy.getHealth() - damage);
                    System.out.println("You have hit the enemy for " + damage + " Health");
                }
                enemy.printCharacter();
                if (enemy.getHealth() <= 0) {
                    System.out.println("Enemy has been vanquished");
                    player.setGold(player.getGold() + enemy.getGold());
                    player.setXP(player.getXP() + enemy.getXP());
                    isFighting = false;
                    return;
                }
            }
            round++;
        }
    }
    /**
     Method: firstFloor
     Inputs: none
     Returns: void

     Description:
     Heals the player stats to full.
     Calls checkLevelUp().
     Presents the first floor game menu options.
     */
    public void firstFloor() {
        boolean returnToFight = false;
        System.out.println("You have returned to the first floor");
        player.setHealth(player.getMaxHealth());
        player.setStamina(player.getMaxStamina());
        player.setMana(player.getMaxMana());
        checkLevelUp();
        while (!returnToFight) {
            clearConsole();
            separator(30);
            System.out.println("MENU - Floor: 1");
            System.out.println("Choose what you would like to do");
            separator(30);
            System.out.println("(1) Continue to the Fight");
            System.out.println("(2) Check Stats");
            System.out.println("(3) Exit Game");
            separator(30);
            //int input = readInt();
            int input = 1;
            if (input == 1) {
                returnToFight = true;
                nextFloor();
            } else if (input == 2) {
                player.printCharacter();
            } else {
                isRunning = false;
                return;
            }
        }
    }
    /**
     Method: checkLevelUp
     Inputs: none
     Returns: void

     Description:
     Sets the player level based on XP.
     Wraps the player class using the decorator design pattern if the player level is 5, 10, or 15.
     */
    public void checkLevelUp() {
        if (player.getXP() > 1050) {
            player.setLevel(15);
        } else if (player.getXP() > 910) {
            player.setLevel(14);
        } else if (player.getXP() > 780) {
            player.setLevel(13);
        } else if (player.getXP() > 660) {
            player.setLevel(12);
        } else if (player.getXP() > 550) {
            player.setLevel(11);
        } else if (player.getXP() > 450) {
            player.setLevel(10);
        } else if (player.getXP() > 360) {
            player.setLevel(9);
        } else if (player.getXP() > 280) {
            player.setLevel(8);
        } else if (player.getXP() > 210) {
            player.setLevel(7);
        } else if (player.getXP() > 150) {
            player.setLevel(6);
        } else if (player.getXP() > 100) {
            player.setLevel(5);
        } else if (player.getXP() > 60) {
            player.setLevel(4);
        } else if (player.getXP() > 30) {
            player.setLevel(3);
        } else if (player.getXP() > 10) {
            player.setLevel(2);
        }
        System.out.println("You leveled up you are now Level: " + player.getLevel());
        if (player.getLevel() == 15) {
            player = new Lvl5Character(player);
        } else if (player.getLevel() >= 10) {
            player = new Lvl10Character(player);
        } else if (player.getLevel() >= 5) {
            player = new Lvl15Character(player);
        }
    }
}
