/**
 Class: Character

 Description: Character Interface
 Provides the interface for the Factory Design Pattern, and the Decorator Design Pattern.
 Is implemented by all 4 Player classes, 3 Enemy Classes,
 3 LvlUp classes, and the CharacterFactory class.
 */
public interface Character {

    double getHealth();

    double getStrength();

    double getStamina();

    double getMana();

    double getSpeed();

    double getDefense();

    String getName();

    void setMaxHealth(double maxHealth);

    void setMaxStamina(double maxStamina);

    void setMaxMana(double maxMana);

    Race getRace();

    double getXP();

    int getGold();

    String getClassName();

    String getRaceName();

    double getMaxHealth();

    double getMaxStamina();

    double getMaxMana();

    int getLevel();

    void setName(String name);

    void setHealth(double health);

    void setStrength(double strength);

    void setStamina(double stamina);

    void setMana(double mana);

    void setRace(Race race);

    void setSpeed(double speed);

    void setDefense(double defense);

    void setGold(int gold);

    void setXP(double xp);

    void setLevel(int level);

    void printCharacter();

    double specialMove();

    void fortify();

    void unFortify();

    double attack();
}
