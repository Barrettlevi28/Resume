/**
 Class: Main

 Description:
 Implements 4 versions of the game one for each class.
 */
public class Main {
    /**
     Method: main
     Inputs: String[] args
     Returns: void

     Description: Runs an instance of startGame with an Assassin Character.
     */
    public static void main(String[] args) {
        GameLogic game = new GameLogic();
        game.startGame(1);
        //game.startGame(2);
        //game.startGame(3);
        //game.startGame(4);
    }
}
