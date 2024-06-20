import java.util.ArrayList;
/**
 * Units
 */
public abstract class Unit {
    public  String name;
    public int health; 
    public  int level;
    public boolean isDead;

    //abstract methods
    public abstract int getAttack();
    public abstract int getMaxHealth();
    public abstract void firstPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting);
    public abstract void secondPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting);
    public abstract void thirdPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting);

    //implemented methods
    public void damage(int damageAmount){
        if (damageAmount >= health) {
            isDead = true;
            health =0;
        } else {
            health -= damageAmount; 
        }
    }

    public void increaseLevel(){
        level++;
    }

    public void decreaseLevel(){
        if (level > 1) {
            level--;
        }
        if(getMaxHealth() > health){
            health = getMaxHealth();
        }
    }

    public void revive(){
        if (level > 1) {
            level--;
        }
        health = this.getMaxHealth();
        isDead = false;
    }

    public void heal(int healAmount){
        health =+ healAmount;
        if (health > getMaxHealth()) {
            health = getMaxHealth();
        }
    }

    public String getInfo(){
        return name + ", LVL: " + level + ", ATK: "  + getAttack() + ", HEALTH: " + health + "/" + getMaxHealth();
    }

    public void attackOpponent(Unit opponent){
        if (!opponent.isDead) {
            int damageAmount = getAttack();
            opponent.damage(damageAmount);
        }
        //System.out.printf("%s damaged %s by %d%n", name, opponent.name, this.getAttack());
        /*if (opponent.isDead) {
            System.out.printf("%s is dead now.");
        }*/
    }

    public Unit getRandomAlive(ArrayList<Unit> waiting){
        boolean allDead = true;
        Unit randomGuy;
        for (int i = 0; i < waiting.size() && allDead; i++) {
            if (!waiting.get(i).isDead) {
                allDead = false;
            }
        }
        if (allDead == true) {
            return null;
        }
        do {
            int number = (int)(Math.random()*waiting.size());
            randomGuy = waiting.get(number);
        } while (randomGuy.isDead);
        return randomGuy;
    }

    public Unit getRandomDead(ArrayList<Unit> waiting){
        boolean allAlive = true;
        Unit randomGuy;
        for (int i = 0; i < waiting.size() && allAlive; i++) {
            if (waiting.get(i).isDead) {
                allAlive = false;
            }
        }
        if (allAlive == true) {
            return null;
        }
        do {
            int number = (int)(Math.random()*waiting.size());
            randomGuy = waiting.get(number);
        } while (!randomGuy.isDead);
        return randomGuy;
    }

    public static int howManyDead(ArrayList<Unit> unitList){
        int deadNum = 0;
        for (int i = 0; i < unitList.size(); i++) {
            if(unitList.get(i).isDead){
                deadNum++;
            }
        }
        return deadNum;
    }

    public void increaseLevelbyKillNum(int before, int after){
        int killNum = after - before;
        level += killNum;
    }

    public Unit(){
        level = 1;
        health = getMaxHealth();
    }
}