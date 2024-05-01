/**
 Class: CharacterFactory

 Description:
 Part of the Factory Design Pattern.
 Decides which class the Player character should be created as,
 hides this from the gameLogic/client.
 */
public class CharacterFactory {
    /**
     Method: createCharacter
     Inputs: String name, Race race, int num.
     Returns: Character

     Description:
     Creates a character class based on the given number.
     Concrete Factory Method.
     */
    public Character createCharacter(String name, Race race, int num) {
        //1 = Assassin
        //2 = Cleric
        //3 == Fighter
        //4 = Wizard
        switch (num) {
            case 1:
                return new Assassin(name, race);
            case 2:
                return new Cleric(name, race);
            case 3:
                return new Fighter(name, race);
            case 4:
                return new Wizard(name, race);
            default:
                return new Fighter("Character Fail", Race.ORC);
        }
    }
}
