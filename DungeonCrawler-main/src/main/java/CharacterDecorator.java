/**
 Class: CharacterDecorator

 Description: Abstract class
 Part of the Decorator Design Pattern.
 Provides an abstract wrapper class for the character class.
 */
public abstract class CharacterDecorator implements Character {
    protected Character decoratedCharacter;
    /**
     Method: CharacterDecorator
     Inputs: Character decoratedCharacter
     Returns: void

     Description:
     Abstract method for Wrapping a Character class in a decorated character class.
     Part of the Decorator Design Pattern.
     */
    public CharacterDecorator(Character decoratedCharacter) {
        this.decoratedCharacter = decoratedCharacter;
    }
}
