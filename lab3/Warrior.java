import java.util.ArrayList;

public class Warrior extends Unit {
    public Warrior(){
        super();
        name = "Warrior";
    }
     public int getAttack(){
        return level + 1;
    }
    public int getMaxHealth(){
        return level + 2;
    }
    public void firstPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting){
        System.out.printf("%s does nothing %n", name);
    }
    public void secondPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting){
       attackOpponent(arenaOpponent);
       System.out.printf("%s damaged arena opponent by %d %n", name, getAttack());
    }
    public void thirdPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting){
        attackOpponent(arenaOpponent);
        System.out.printf("%s damaged arena opponent by %d %n", name, getAttack());
    }
}
