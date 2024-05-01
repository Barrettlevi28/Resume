import java.util.Random;
/**
 Class: BattleMediator

 Description: Implements the Mediator interface.
 Represents the Mediator design Pattern.
 Is responsible for calculating Whether an attack hits and for how much damage.
 Calculates dodge chance based on speed.
 Critical hit chance is a 1 in 20.
 If a players defense is higher than the attack. The attack will do zero damage.
 */
public class BattleMediator implements Mediator {
    /**
     Method: notifyDamage
     Inputs: Character player, double incomingDamage
     Returns: void

     Description:
     Notify's the player how much damage to remove from their healthpool.
     Uses the random numbers based on speed value to decide if character dodges the attacks.
     1/20 chance to critical hit, dealing double damage.
     If a players defense negates all damage returns 0.
     */
    public double notifyDamage(Character player, double incomingDamage) {
        Random rand = new Random();
        int randNum = rand.nextInt(10 - ((int) player.getSpeed()));
        int randNum1 = rand.nextInt(20);
        if (randNum == 0) {
            //character dodged
            System.out.println(player.getName() + " Dodged the Attack");
            return 0;
        } else if (randNum1 == 0) {
            if ((incomingDamage * 2) - player.getDefense() < 0) {
                return 0;
            }
            return (incomingDamage * 2) - player.getDefense();
        } else {
            if ((incomingDamage * 2) - player.getDefense() < 0) {
                return 0;
            }
            return incomingDamage - player.getDefense();
        }
    }
}
