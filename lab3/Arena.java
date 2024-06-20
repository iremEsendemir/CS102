import java.util.ArrayList;

public class Arena {
    ArrayList<Unit> playerUnits;
    ArrayList<Unit> computerUnits;
    boolean isFinished = false;
    public void battle(int playerIndex, int computerIndex){
        int deadNumPlayerAfter, deadNumComputerAfter;
        int deadNumPlayerBefore = Unit.howManyDead(playerUnits);
        int deadNumComputerBefore = Unit.howManyDead(computerUnits);
        
        //initialize units
        Unit playerUnit = playerUnits.get(playerIndex);
        Unit computerUnit = computerUnits.get(computerIndex);
        playerUnits.remove(playerIndex);
        computerUnits.remove(computerIndex);

        //first phase
        System.out.print("Player: ");
        playerUnit.firstPhase(computerUnit,playerUnits,computerUnits);
        deadNumComputerAfter = Unit.howManyDead(computerUnits);
        playerUnit.increaseLevelbyKillNum(deadNumComputerBefore, deadNumComputerAfter);

        System.out.print("Computer: ");
        computerUnit.firstPhase(playerUnit,computerUnits,playerUnits);
        deadNumPlayerAfter = Unit.howManyDead(playerUnits);
        computerUnit.increaseLevelbyKillNum(deadNumPlayerBefore, deadNumPlayerAfter);

        if (playerUnit.isDead || computerUnit.isDead) {
            if (!playerUnit.isDead && computerUnit.isDead) {
                playerUnit.increaseLevel(); 
                System.out.printf("%s is dead now%n%s is levels up%n",computerUnit.name, playerUnit.name);
            } 
            else if(playerUnit.isDead && !computerUnit.isDead){
                computerUnit.increaseLevel(); 
                System.out.printf("%s is dead now%n%s is levels up%n",playerUnit.name, computerUnit.name);
            }
            else if(playerUnit.isDead && computerUnit.isDead){
                System.out.println("Both units in the arena are dead now.");
            }
            playerUnits.add(playerUnit);
            computerUnits.add(computerUnit);
            System.out.println("Battle ended after phase 1.");
            return;
        }

        deadNumPlayerBefore = Unit.howManyDead(playerUnits);
        deadNumComputerBefore = Unit.howManyDead(computerUnits);

        //second phase 
        System.out.print("Player: ");
        playerUnit.secondPhase(computerUnit,playerUnits,computerUnits);
        deadNumComputerAfter = Unit.howManyDead(computerUnits);
        playerUnit.increaseLevelbyKillNum(deadNumComputerBefore, deadNumComputerAfter);

        System.out.print("Computer: ");
        computerUnit.secondPhase(playerUnit,computerUnits,playerUnits);
        deadNumPlayerAfter = Unit.howManyDead(playerUnits);
        computerUnit.increaseLevelbyKillNum(deadNumPlayerBefore, deadNumPlayerAfter);
        
        if (playerUnit.isDead || computerUnit.isDead) {
            if (!playerUnit.isDead && computerUnit.isDead) {
                playerUnit.increaseLevel(); 
                System.out.printf("%s is dead now%n%s is levels up%n",computerUnit.name, playerUnit.name);
            } 
            else if(playerUnit.isDead && !computerUnit.isDead){
                computerUnit.increaseLevel(); 
                System.out.printf("%s is dead now%n%s is levels up%n",playerUnit.name, computerUnit.name);
            }
            else if(playerUnit.isDead && computerUnit.isDead){
                System.out.println("Both units in the arena are dead now.");
            }
            playerUnits.add(playerUnit);
            computerUnits.add(computerUnit);
            System.out.println("Battle ended after phase 2.");
            return;
        }

        deadNumComputerBefore = Unit.howManyDead(computerUnits);
        deadNumPlayerBefore = Unit.howManyDead(playerUnits);

        //third phase
        System.out.print("Player: ");
        playerUnit.thirdPhase(computerUnit,playerUnits,computerUnits);
        deadNumComputerAfter = Unit.howManyDead(computerUnits);
        playerUnit.increaseLevelbyKillNum(deadNumComputerBefore, deadNumComputerAfter);

        System.out.print("Computer: ");        
        computerUnit.thirdPhase(playerUnit,computerUnits,playerUnits);
        deadNumPlayerAfter = Unit.howManyDead(playerUnits);
        computerUnit.increaseLevelbyKillNum(deadNumPlayerBefore, deadNumPlayerAfter);

        if (playerUnit.isDead || computerUnit.isDead) {
            if (!playerUnit.isDead && computerUnit.isDead) {
                playerUnit.increaseLevel(); 
                System.out.printf("%s is dead now%n%s is levels up%n",computerUnit.name, playerUnit.name);
            } 
            else if(playerUnit.isDead && !computerUnit.isDead){
                computerUnit.increaseLevel(); 
                System.out.printf("%s is dead now%n%s is levels up%n",playerUnit.name, computerUnit.name);
            }
            else if(playerUnit.isDead && computerUnit.isDead){
                System.out.println("Both units in the arena are dead now.");
            }
            playerUnits.add(playerUnit);
            computerUnits.add(computerUnit);
            return;
        }
        playerUnits.add(playerUnit);
        computerUnits.add(computerUnit);
        System.out.println("Battle ended after phase 3.");
    }

    public void addRandomPlayer(ArrayList<Unit> units,int number){
        for (int i = 0; i < number; i++) {
            int unitKind =(int)(Math.random()*7);
            if (unitKind == 0) {
                Warrior wa =new Warrior();
                units.add(wa);
            } 
            else if(unitKind == 1){
                Archer a = new Archer();
                units.add(a);
            }
            else if(unitKind == 2){
                Healer h = new Healer();
                units.add(h);
            }
            else if(unitKind == 3){
                Rogue r= new Rogue();
                units.add(r);
            }
            else if(unitKind == 4){
                Wizard w = new Wizard();
                units.add(w);
            }
            else if(unitKind == 5){
                Bard b = new Bard();
                units.add(b);
            }
            else if(unitKind == 6){
                Necromancer n = new Necromancer();
                units.add(n);
            }
        }
    }

    public boolean checkEnd(){
        int deadOfComputer = Unit.howManyDead(computerUnits);
        int deadOfPlayer = Unit.howManyDead(playerUnits);
        if (deadOfComputer==7 || deadOfPlayer==7) {
            isFinished = true;
        }
        else {
            isFinished = false;
        }
        return isFinished;
    }

    public Unit chooseForPc(){
        if (!isFinished) {
            int num;
            Unit chosen;
            do {
                num = (int)(Math.random()*7);
                chosen = computerUnits.get(num);
            } while (chosen.isDead);
            return chosen;
        } 
        else {
            return null;
        }
        
    }

    public void printLastPart(){
        if (Unit.howManyDead(computerUnits)==7) {
            System.out.println("\nThe player wins the game. ");
        } 
        else if(Unit.howManyDead(playerUnits)==7){
            System.out.println("The computer wins the game. ");
        }
        else {
            System.out.println("There is no winner. ");
        }
    }
    public Arena(){
        playerUnits = new ArrayList<Unit>();
        computerUnits = new ArrayList<Unit>();
        Warrior w1 = new Warrior();       
        Warrior w2 = new Warrior();
        playerUnits.add(w1);
        computerUnits.add(w2);
        addRandomPlayer(computerUnits, 6);
        addRandomPlayer(playerUnits, 6);
    }
}