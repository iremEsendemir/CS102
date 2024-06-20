import java.util.ArrayList;

public class Necromancer extends Unit{
    public Necromancer(){
        super();
        name = "Necromancer";
    }
 public int getAttack(){
        return level;
    }
    public int getMaxHealth(){
        return level + 1;
    }
    public void firstPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting){
        attackOpponent(arenaOpponent);
        System.out.printf("%s damaged arena opponent by %d %n", name, getAttack());
    }
    public void secondPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting){
        Unit randomDeadAlly = getRandomDead(allyWaiting);
        if (randomDeadAlly == null) {
            health--;
            System.out.printf("%s 's health is lessened by 1 %n", name);
        } 
        else {
            randomDeadAlly.revive();
            System.out.printf("%s revived %s %n", name, randomDeadAlly.name);
        }
    }
    public void thirdPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting){
       arenaOpponent.decreaseLevel();
       System.out.printf("%s decreased level of %s by 1 %n", name, arenaOpponent.name);
    }
}
