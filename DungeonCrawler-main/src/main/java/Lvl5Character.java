/**
 Class: Lvl5Character

 Description:
 Part of the Decorator Design Pattern.
 Extends CharacterDecorator Abstract Class.
 Is used to wrap the character class when the character levels up to level 5.
 Implements a unique Special move.
 */
public class Lvl5Character extends CharacterDecorator {
    /**
     Method: Lvl5Character
     Inputs: Character decoratedCharacter
     Returns: None

     Description: Constructor for the Lvl5Character player.
     */
    public Lvl5Character(Character decoratedCharacter) {
        super(decoratedCharacter);
        this.name = decoratedCharacter.getName();
        this.health = decoratedCharacter.getHealth();
        this.maxHealth = decoratedCharacter.getMaxHealth();
        this.stamina = decoratedCharacter.getStamina();
        this.maxStamina = decoratedCharacter.getMaxStamina();
        this.strength = decoratedCharacter.getStrength();
        this.speed = decoratedCharacter.getSpeed();
        this.defense = decoratedCharacter.getDefense();
        this.mana = decoratedCharacter.getMana();
        this.maxMana = decoratedCharacter.getMaxMana();
        this.gold = decoratedCharacter.getGold();
        this.level = decoratedCharacter.getLevel();
        this.xp = decoratedCharacter.getXP();
        this.race = decoratedCharacter.getRace();
        setRace(this.race);
    }
    /**
     Method: specialMove
     Inputs: None
     Returns: double

     Description: Calculates damage amount for specialMove ability.
     Changed to do more damage and take away stamina.
     */
    @Override
    public double specialMove() {
        if (this.stamina >= 30) {
            this.stamina -= 30;
            return 20 * this.strength;
        } else {
            return -1;
        }
    }
    /**
     Method: attack
     Inputs: None
     Returns: double

     Description: Calculates damage for a regular attack.
     */
    @Override
    public double attack() {
        return 10 * this.strength;
    }
    /**
     Method: fortify
     Inputs: None
     Returns: void

     Description: Increases defense by 10. Represents a defensive ability.
     */
    @Override
    public void fortify() {
        this.defense += 10;
    }
    /**
     Method: unFortify
     Inputs: None
     Returns: void

     Description: decreases defense by 10. Represents a defensive ability wearing off.
     */
    @Override
    public void unFortify() {
        this.defense -= 10;
    }

    double health;
    double maxHealth;
    double strength;
    double stamina;

    double maxStamina;
    double mana;
    double maxMana;
    double defense;
    double speed;
    String name;

    double xp;
    int gold;
    int level;

    Race race;

    @Override
    public double getHealth() {
        return health;
    }

    @Override
    public double getStrength() {
        return strength;
    }

    @Override
    public double getStamina() {
        return stamina;
    }

    @Override
    public double getMana() {
        return mana;
    }

    @Override
    public double getSpeed() {
        return speed;
    }

    @Override
    public double getDefense() {
        return defense;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getClassName() {
        return "Assassin";
    }

    @Override
    public double getXP() {
        return this.xp;
    }

    @Override
    public int getGold() {
        return this.gold;
    }

    @Override
    public double getMaxHealth() {
        return this.maxHealth;
    }

    @Override
    public double getMaxStamina() {
        return this.maxStamina;
    }

    @Override
    public double getMaxMana() {
        return this.maxMana;
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setHealth(double health) {
        this.health = health;
    }

    @Override
    public void setStrength(double strength) {
        this.strength = strength;
    }

    @Override
    public void setStamina(double stamina) {
        this.stamina = stamina;
    }

    @Override
    public void setMana(double mana) {
        this.mana -= mana;
    }

    @Override
    public void setRace(Race race) {
        this.race = race;
    }

    @Override
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    @Override
    public void setDefense(double defense) {
        this.defense = defense;
    }

    @Override
    public void setXP(double xp) {
        this.xp = xp;
    }

    @Override
    public void setGold(int gold) {
        this.gold = gold;
    }

    @Override
    public void setMaxHealth(double maxHealth) {
        this.maxHealth = maxHealth;
    }

    @Override
    public void setMaxStamina(double maxStamina) {
        this.maxStamina = maxStamina;
    }

    @Override
    public void setMaxMana(double maxMana) {
        this.maxMana = maxMana;
    }

    @Override
    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public Race getRace() {
        return this.race;
    }

    @Override
    public String getRaceName() {
        switch (race) {
            case DWARF:
                return "Dwarf";
            case ELF:
                return "Elf";
            case HUMAN:
                return "Human";
            default:
                return "Orc";
        }
    }
    /**
     Method: printCharacter
     Inputs: None
     Returns: None

     Description: Prints Character info to the console.
     */
    @Override
    public void printCharacter() {
        System.out.println("------------------------------");
        System.out.println(this.name.toUpperCase() + ":");
        System.out.println(getRaceName() + " " + getClassName());
        System.out.println("Health: (" + this.health + "/" + this.maxHealth + ")");
        System.out.println("Stamina: (" + this.stamina + "/" + this.maxStamina + ")");
        System.out.println("Mana: (" + this.mana + "/" + this.maxMana + ")");
        System.out.println("Strength: " + this.strength);
        System.out.println("Speed: " + this.speed);
        System.out.println("Defense: " + this.defense);
        System.out.println("XP: " + this.xp);
        System.out.println("Gold: " + this.gold);
        System.out.println("------------------------------");
    }
}
