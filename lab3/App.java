import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        int chosenForComputer, chosenForPlayer;
        //Unit chosenForPc, chosenForPlayer;
        Scanner in = new Scanner(System.in);
        Arena arena = new Arena();
        for (int i = 0; i < 100 && !arena.isFinished; i++) {
            System.out.println("Turn: " + (i+1));
            System.out.println("Computer's units:" );
            for (int j = 0; j < arena.computerUnits.size(); j++) {
                System.out.print((j+1) + "." + arena.computerUnits.get(j).getInfo() + "\n");
            }
            System.out.println();
            System.out.println("Player's units:" );
            for (int k = 0; k < arena.playerUnits.size(); k++) {
                System.out.print((k+1) + "." + arena.playerUnits.get(k).getInfo() + "\n");
            }
            System.out.print("Which unit you choose: ");
            chosenForPlayer = in.nextInt();
            chosenForPlayer--;
            while ((chosenForPlayer>7||chosenForPlayer<0)||arena.playerUnits.get(chosenForPlayer).isDead) {
                System.out.println("Not a valid index, please enter again: ");
                chosenForPlayer = in.nextInt();
                chosenForPlayer--;
            }
            System.out.println();
            System.out.printf("You choose %s%n",arena.playerUnits.get(chosenForPlayer).getInfo());
            do {
                chosenForComputer = (int)(Math.random()*7);
            } while (arena.computerUnits.get(chosenForComputer).isDead);     
            System.out.printf("Computer chooses %s%n", arena.computerUnits.get(chosenForComputer).getInfo());
            System.out.println();
            arena.battle(chosenForPlayer, chosenForComputer);
            System.out.println();
            arena.checkEnd();
        }
        arena.printLastPart();
    }
}