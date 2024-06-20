import java.util.ArrayList;

public class Archer extends Unit{
    public Archer(){
        super();
        name = "Archer";
    }
     public int getAttack(){
        return level + 1;
    }
    public int getMaxHealth(){
        return level + 1;
    }
    public void firstPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting){
        attackOpponent(arenaOpponent);
        System.out.printf("%s damaged arena opponent by %d %n", name, getAttack());
    }
    public void secondPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting){
        Unit randomAlive = getRandomAlive(enemyWaiting);
        attackOpponent(randomAlive);
        System.out.printf("%s damaged waiting %s by %d %n", name, randomAlive.name, getAttack());
    }
    public void thirdPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting){
        heal(1);
        System.out.printf("%s healed self by 1 %n", name);
    }
}
