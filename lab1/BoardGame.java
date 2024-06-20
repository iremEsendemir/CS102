import java.util.Arrays;
import java.util.Scanner;
public class BoardGame{
    //gives a random number of dice
    public static int rollDice(){
        int dice = (int)(Math.random()*6 + 1);
        return dice;
    }
    //checks whether the game is finished
    public static boolean finished(Player[] players, int width, int height){
        boolean isFinished=false;
        for (int i = 0; i < players.length && !isFinished; i++) {
            if (players[i].getSquareNum()==2 * (width + height - 2)) {
                isFinished = true;
            } 
        }
        return isFinished;
    }
    // checks whether the given number is on the corner
    public static boolean isCorner(char[][] gameBoard, int num){
        boolean corner;
        int row = gameBoard.length ;
        int column = gameBoard[0].length;
        int width = (column - 1)/3;
        int height = (row - 1)/3;
        if (num == width|| num == width+height-1|| num == (2*width)+height -2|| num == 1) {
            corner = true;
        } 
        else {
            corner = false;
        }
        return corner;
    }
    //creates a random unique trap array which does not include corners
    public static int[] createTrap(int trapNum, char[][] gameBoard){
        int row = gameBoard.length ;
        int column = gameBoard[0].length;
        int width = (column - 1)/3;
        int height = (row - 1)/3;
        int[] trapArray = new int[trapNum];
        for (int i = 0; i < trapArray.length; i++) {
            int randomNum;
            boolean contains;
            do {
                randomNum = (int)((Math.random() * (2 * (width + height - 2)) + 1));
                contains = false;
                for (int j = 0; j < i && !contains; j++) {
                    if (trapArray[j] == randomNum) {
                        contains = true;
                    }
                }
            } while (contains || isCorner(gameBoard, randomNum));
            trapArray[i] = randomNum;
        }
        return trapArray;
    }
    //returns the index of winner
    public static int whoWon (Player[] players, int width, int height){
        int winnerNum = -1 ;
        for (int i = 0; i < players.length && winnerNum == -1; i++) {
            int squareNum = players[i].getSquareNum();
            if (squareNum == 2 * (width + height - 2)) {
                winnerNum = i;
            }
        }
        return winnerNum;
    }
    //methods for displaying board
    public static char[] createFullLine(int width){
        char[] line = new char[3*width+1];
        for (int i = 0; i < 3*width+1; i++) {
            line[i]='#';
        }
        return line;
    }
    public static char[] createLineWithoutChar(int width){
        char[] line = new char[3*width+1];
        for (int i = 0; i < 4; i++) {
            line[i]='#';
        }
        for (int i = 4; i < 3*width-3; i++) {
            line[i] = ' ';
        }
        for (int i = 3*width-3; i < line.length; i++) {
            line[i] ='#';
        }
        return line;
    }
    public static char[] createLineWithChar(int width){
        char[] line = new char[3*width+1];
        line[0]='#';
        line[1]=' ';
        line[2]=' ';
        line[3]='#';
        for (int i = 4; i < 3*width-3; i++) {
            line[i] = ' ';
        }
        line[3*width-3]='#';
        line[3*width-2]=' ';
        line[3*width-1]=' ';
        line[3*width]='#';
        return line;
    }
    public static char[] createLineForFirstAndLastLine(int width){
        char[] line = new char[3*width+1];
        for (int i = 0; i < 3*width+1 ; i++) {
            if (i % 3 == 0) {
                line[i] = '#';
            } else {
                line[i] = ' ';
            }
        }
        return line;
    }
    public static void createGameBoard(char[][] gameBoard, int height, int width){
        for (int i = 0; i < 3 * height + 1; i++) {
            if (i == 0 || i == 3 || i == 3*height|| i == 3*height -3) {
                gameBoard[i] = createFullLine(width);
            }
            else if (i == 1 || i == 2 || i == 3*height - 1|| i == 3*height -2) {
                gameBoard[i] = createLineForFirstAndLastLine(width);
            }
            else if (i % 3 == 0) {
                gameBoard[i] = createLineWithoutChar(width);
            } 
            else {
                gameBoard[i] = createLineWithChar(width);            
            }
        }
    }
    public static void displayGame(char[][] gameBoard){
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                System.out.print(gameBoard[i][j]);
            }
            System.out.println(); // Move to the next line for the next row
        }
    }
    // checks the entered number is smaller than the dice number
    public static int checkNum(int given, Scanner in){
        int wanted;
        wanted = in.nextInt();
        while(wanted > given && given < 0){
            System.out.print("Enter valid number: ");
            wanted = in.nextInt();
        }
        return wanted;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int width, height, playerNum;
        int[] firstRoll; 
        //char[] names;
        Player[] players;
        char[][] gameBoard;
        int[] trapArray;
        String name, continueString;
        char continueChar;
        boolean continued = false;
        //starting menu
        System.out.println("Welcome to the board game.");
        //checks whether the user wants a new game
        do {
            //enterance menu
            System.out.print("Please enter board width: ");
            width = in.nextInt();
            System.out.print("Please enter board height: ");
            height = in.nextInt();
            gameBoard = new char[3*height+1][3*width+1];
            trapArray = createTrap(5, gameBoard);
            System.out.println(Arrays.toString(trapArray));
            //takes the players name
            do {
                System.out.print("How many players? (2-4): ");
                playerNum = in.nextInt();
                in.nextLine();
            } while (playerNum > 4 && playerNum < 2);
            firstRoll = new int[playerNum];
            players = new Player[playerNum];
            //takes names
            for (int i = 0; i < playerNum; i++) {
                System.out.print("For player " + (i+1) + ":");
                name = in.nextLine();
                players [i] = new Player(name.charAt(0));
            }
            System.out.println("Players are rolling dice.");
            //take first roll
            for (int i = 0; i < playerNum ; i++) {
                firstRoll[i] = rollDice();
                if(i != playerNum - 1){
                    System.out.print(players[i].getName() + ": " + firstRoll[i] + ", ");
                }
                else{
                    System.out.println(players[i].getName() + ": " + firstRoll[i]);
                }
            }
            //put in order with bubble sort
            boolean swapped = false; 
            for (int i = 0; i < players.length - 1 && !swapped; i++) {
                swapped = true;
                for (int j = 0; j < players.length - i - 1; j++) {
                    if (firstRoll[j] < firstRoll[j + 1]) {
                        int tempFirstRoll = firstRoll[j];
                        firstRoll[j] = firstRoll[j + 1];
                        firstRoll[j + 1] = tempFirstRoll;

                        Player tempPlayer = players[j];
                        players[j] = players[j+1];
                        players[j+1] = tempPlayer;
                        swapped = false;
                    }
                }
            } 
            //breaking tie for kısmı
            if (players.length == 2) {
                System.out.printf("Breaking tie for: %c %c%n", players[0].getName(), players[1].getName() );
                System.out.printf("Playing order is: %c %c%n",  players[0].getName(), players[1].getName());
            }
            else if (players.length == 3) {
            System.out.printf("Breaking tie for: %c %c%n", players[0].getName(), players[1].getName() );
            System.out.printf("Breaking tie for: %c%n", players[2].getName());
            System.out.printf("Playing order is: %c %c %c %n",  players[0].getName(), players[1].getName(), players[2].getName());
            }
            else if(players.length == 4){
            System.out.printf("Breaking tie for: %c %c%n", players[0].getName(), players[1].getName() );
            System.out.printf("Breaking tie for: %c %c%n", players[2].getName(), players[3].getName() );
            System.out.printf("Playing order is: %c %c %c %c%n",  players[0].getName(), players[1].getName(), players[2].getName(), players[3].getName());
            }
            createGameBoard(gameBoard, height, width);
            //set first location
            for (int i = 0; i < players.length; i++) {
                if (i == 0) {
                    players[i].setPlace(1,1);
                    gameBoard[1][1]=players[i].getName();
                } 
                else if (i==1) {
                    players[i].setPlace(1,2);
                    gameBoard[1][2]=players[i].getName();
                }
                else if (i==2) {
                    players[i].setPlace(2,1);
                    gameBoard[2][1]=players[i].getName();
                } 
                else {
                    players[i].setPlace(2, 2);
                    gameBoard[2][2]=players[i].getName();
                }
            }
            displayGame(gameBoard);
            //loop until the game is finished
            for (int i = 0; !finished(players, width, height) ; i++) {
                int playerIndex = i % players.length ;
                int dice = rollDice();
                System.out.printf("Player %c rolls %d, how many cells you move? (0 - %d): ", players[playerIndex].getName(), dice, dice);
                int increaseNum = checkNum(dice, in);
                players[playerIndex].moveOn(gameBoard, increaseNum);
                for (int j = 0; j < trapArray.length; j++) {
                    if (trapArray[j] == players[playerIndex].getSquareNum()) {
                        players[playerIndex].ifTrapped(gameBoard);
                        trapArray[j] = -1;
                    } 
                }
                displayGame(gameBoard);
            }
            System.out.printf("Winner is %c, congratulations!%n", players[whoWon(players, width, height)].getName());
            //final display for move and trap number
            System.out.println("Players   Move     Trap");
            for (int i = 0; i < players.length; i++) {
                System.out.printf("%-10c%-9d%-5d%n",players[i].getName(),players[i].getSquareNum(),players[i].getTrapNum());
            }
            System.out.print("Play again? (Y/N): ");
            continueString = in.next();
            continueChar = continueString.charAt(0);
            if (continueChar == 'Y' || continueChar == 'y') {

                continued = true;
            } 
        } while (continued);
        in.close();
    }
}