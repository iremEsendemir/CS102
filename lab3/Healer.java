import java.util.ArrayList;

public class Healer extends Unit{
    public Healer(){
        super();
        name = "Healer";
    }
     public int getAttack(){
        return level;
    }
    public int getMaxHealth(){
        return level + 2;
    }
    public void firstPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting){
        if (this.health >= getMaxHealth()) {
            Unit randomAliveAlly = getRandomAlive(allyWaiting);
            if (randomAliveAlly != null) {
                randomAliveAlly.heal(level);
                System.out.printf("%s healed %s by %d%n", name, randomAliveAlly.name, level);
            } 
        }
        else{
            this.heal(level);
            System.out.printf("%s healed self by %d %n", name, level);
        }
    }
    public void secondPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting){
        System.out.printf("%s damaged arena opponent by %d %n", name, getAttack());
        attackOpponent(arenaOpponent);
    }
    public void thirdPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting){
        Unit randomDeadAlly = getRandomDead(allyWaiting);
        if (randomDeadAlly != null) {
            randomDeadAlly.revive();
            System.out.printf("%s revived waiting %s %n", name, randomDeadAlly.name);
        }
        else{
            System.out.println("There is nobody to revive");
        }
    }
}
