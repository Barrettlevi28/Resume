/**
 Class: Mediator

 Description:
 Part of the Mediator Design Pattern.
 Mediator interface is used by the BattleMediator concrete class.
 */
public interface Mediator {
    double notifyDamage(Character player, double incomingDamage);
}
