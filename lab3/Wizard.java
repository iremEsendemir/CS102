import java.util.ArrayList;

public class Wizard extends Unit{
    public Wizard(){
        super();
        name = "Wizard";
    }
    public int getAttack(){
        return 1;
    }
    public int getMaxHealth(){
        return level + 2;
    }
    public void firstPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting){
        System.out.printf("%s does nothing %n", name);
    }
    public void secondPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting){
        System.out.printf("%s does nothing %n", name);
    }
    public void thirdPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting){
        for (int i = 0; i < enemyWaiting.size(); i++) {
            Unit damaged = enemyWaiting.get(i);
            if (!damaged.isDead) {
            attackOpponent(damaged);
            System.out.printf("%s damaged waiting %s by %d %n",name, damaged.name, getAttack());
            if (damaged.isDead) {
                System.out.printf("Waiting %s is dead %n", damaged.name);
            }
            }
        }
    }
}
