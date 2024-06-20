import java.util.ArrayList;

public class Rogue extends Unit{
    public Rogue(){
        super();
        name = "Rogue";
    }
     public int getAttack(){
        return level + 2;
    }
    public int getMaxHealth(){
        return level;
    }
    public void firstPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting){
        attackOpponent(arenaOpponent);
        System.out.printf("%s damaged arena opponent by %d%n", name, getAttack());
    }
    public void secondPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting){
        attackOpponent(arenaOpponent);
        System.out.printf("%s damaged arena opponent by %d%n", name, getAttack());
    }
    public void thirdPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting){
        Unit randomAlive = getRandomAlive(enemyWaiting);
        attackOpponent(randomAlive);
        System.out.printf("%s damaged waiting %s by %d%n", name, randomAlive.name, getAttack());
    }
}
