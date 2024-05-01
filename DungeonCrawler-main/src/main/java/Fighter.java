/**
 Class: Fighter

 Description:
 Part of the Factory Design Pattern
 Implements the Character interface. Is a unique character class that has high health,
 high defense, and decent strength
 */
public class Fighter implements Character {
    double health = 100;
    double maxHealth = 100;
    double strength = 2;
    double stamina = 100;
    double maxStamina = 100;
    double mana = 100;
    double maxMana = 100;
    double defense = 5;
    double speed = 3;
    String name;
    Race race;
    double xp = 0;
    int gold = 100;
    int level = 1;
    /**
     Method: Fighter
     Inputs: String name, Race race
     Returns: void

     Description:
     Creates a new Fighter Character, Buffs character based on Race.
     */
    public Fighter(String name, Race race) {
        setName(name);
        switch (race) {
            case DWARF:
                this.defense += 2;
                break;
            case ELF:
                this.mana += 30;
                this.maxMana += 30;
                break;
            case HUMAN:
                this.health -= 20;
                this.speed += 2;
                this.maxHealth -= 20;
                break;
            default:
                this.strength += 1;
                break;
        }
        setRace(race);
    }

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
        return "Fighter";
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
    /**
     Method: specialMove
     Inputs: None
     Returns: double

     Description: Calculates damage amount for specialMove ability.
     */
    @Override
    public double specialMove() {
        if (level < 5) {
            return 10 * strength;
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
        return strength * 10;
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
}
