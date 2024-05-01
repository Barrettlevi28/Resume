/**
 Class: MediumEnemy

 Description:
 Implements the Character interface. Represents a medium enemy.
 Health increases every floor.
 You fight one every 5 floors.
 */
public class MediumEnemy implements Character {
    double health = 100;
    double maxHealth = 100;
    double strength = 2;
    double stamina = 100;

    double maxStamina = 100;
    double mana = 100;
    double maxMana = 100;
    double defense = 3;
    double speed = 2;
    String name;

    double xp = 40;
    int gold = 30;
    int level = 1;

    Race race;
    /**
     Method: mediumEnemy
     Inputs: int floor
     Returns: None

     Description: Constructor for the medium Enemy.
     */
    public MediumEnemy(int floor) {
        setName("Giant Ogre");
        setRace(Race.ORC);
        setHealth(getHealth() + floor);
        setMaxHealth(getMaxHealth() + floor);
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
    /**
     Method: specialMove
     Inputs: None
     Returns: double

     Description: Calculates damage amount for specialMove ability.
     Only used if Enemy has enough stamina.
     */
    @Override
    public double specialMove() {
        if (this.stamina >= 40) {
            this.stamina -= 40;
            System.out.println(this.name + " did a special attack");
            return this.strength * 20;
        } else {
            System.out.println(this.name + " did not have enough Stamina to use a "
                    + "special attack, and used a regular attack");
            return strength * 10;
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
        System.out.println(this.name + " did a regular attack");
        return strength * 10;
    }
    /**
     Method: unFortify
     Inputs: None
     Returns: void

     Description: Increases defense by 10. Represents a defensive ability.
     Not used for Enemy Classes.
     */
    @Override
    public void fortify() {
        this.defense += 10;
    }

    @Override
    public void unFortify() {
        this.defense -= 10;
    }
}
