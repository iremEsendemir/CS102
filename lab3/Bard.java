import java.util.ArrayList;

public class Bard extends Unit{
    
    public Bard(){
        super();
        name = "Bard";
    }
    public int getAttack(){
        return level;
    }
    public int getMaxHealth(){
        return level;
    }
    public void firstPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting){
        attackOpponent(arenaOpponent);
        System.out.printf("%s damaged arena opponent by %d %n", name, getAttack());
    }
    public void secondPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting){
        heal(1);
        System.out.printf("%s healed self by 1 \n", name);
    }
    public void thirdPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting){
        Unit randomAlive = getRandomAlive(allyWaiting);
        if (randomAlive != null) {
            randomAlive.level++;
            System.out.printf("%s levels up waiting %s by 1 %n", name, arenaOpponent.name);
        }
        System.out.println("Bard did not do anything as there is not any waiting alive ally.");
    }
}
