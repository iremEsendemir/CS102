public class Player {
    int gameOrder, startOrder, squareNum;
    int trapNum = 0;
    int[] place;
    char name;
    boolean passedFirstHalf;
    public Player(char name){
        this.name = name;
        place = new int[2];
    }
    public int getGameOrder() {
        return gameOrder;
    }
    public void setGameOrder(int gameOrder) {
        this.gameOrder = gameOrder;
    }
    public int getStartOrder() {
        return startOrder;
    }
    public void setStartOrder(int startOrder) {
        this.startOrder = startOrder;
    }
    public int[] getPlace() {
        return place;
    }
    public int getTrapNum() {
        return trapNum;
    }
    public void setPlace(int row, int column) {
        place[0] = row;
        place[1] = column;
    }
    public char getName() {
        return name;
    }
    public void setName(char name) {
        this.name = name;
    }
    public boolean isPassedFirstHalf() {
        return passedFirstHalf;
    }
    public void setPassedFirstHalf(boolean passedFirstHalf) {
        this.passedFirstHalf = passedFirstHalf;
    }
    public void moveOn(char[][] gameBoard, int increaseNum){
        int row = gameBoard.length ;
        int column = gameBoard[0].length;
        int width = (column - 1)/3;
        int height = (row - 1)/3;
        for (int i = 0; (i < increaseNum) && squareNum < 2 * (width + height - 2); i++) {
            int prevRow = place[0];
            int prevColumn = place[1];
            if(squareNum < width){
                place[1] += 3;
                setSquareNum(gameBoard);
            }
            else if(squareNum < width+height-1){
                place[0] += 3;
                setSquareNum(gameBoard);
            }
            else if (squareNum < (2*width)+height -2) {
                place[1] -= 3;
                setSquareNum(gameBoard);
            } 
            else {
                place[0] -= 3; 
                setSquareNum(gameBoard);
            }
            gameBoard[prevRow][prevColumn]= ' ';
            gameBoard[place[0]][place[1]] = name;
        }
    }
    public void setSquareNum (char[][] gameBoard){
        int adding;
        squareNum = -1;
        int row = gameBoard.length ;
        int column = gameBoard[0].length;
        int width = (column - 1)/3;
        int height = (row - 1)/3;
        if(place[0] == 1 || place[0] == 2){
            squareNum = (int) place[1]/3 + 1;
        }
        else if(place[1] == column - 2 || place[1] == column - 3){
            squareNum = (int) width + (place[0]/3);
        }
        else if(place[0] == row - 2 || place[0] == row - 3){
            adding = (int)(column - place[1] - 1 )/3;
            squareNum = (int) height + width  -1 + adding;
        }
        else{
            adding = (int)(row - place[0] - 1 )/3;
            squareNum = (2 * width) + height - 2 + adding;
        }
    }
    public void ifTrapped(char[][] gameBoard){
        System.out.println("You moved into a trap!");
        System.out.println("You moved back to the closest corner!");
        int prevRow = place[0];
        int prevColumn = place[1];
        int row = gameBoard.length ;
        int column = gameBoard[0].length;
        int width = (column - 1)/3;
        int height = (row - 1)/3;
        if(squareNum < width){
            while (squareNum != 1) {
                place[1] -= 3;
                setSquareNum(gameBoard);
            }
        }
        else if(squareNum < width+height-1){
            while (squareNum != width) {
                place[0] -= 3;
                setSquareNum(gameBoard);
            }
        }
        else if (squareNum < (2*width)+height -2) {
            while (squareNum != (width+height-1))  {
                place[1] -= -3;
                setSquareNum(gameBoard);
            }
        } 
        else {
            while (squareNum != (2*width)+height -2) {
                place[0] -= 3; 
                setSquareNum(gameBoard);
            }
        }
        gameBoard[prevRow][prevColumn]= ' ';
        gameBoard[place[0]][place[1]] = name;
        trapNum++;

    }
    public int getSquareNum() {
        return squareNum;
    }
}