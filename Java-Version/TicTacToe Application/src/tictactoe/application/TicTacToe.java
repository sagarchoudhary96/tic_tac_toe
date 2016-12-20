package tictactoe.application;


public class TicTacToe {
    protected char[] board;
    protected char userMarker;
    protected char compMarker;
    protected char currentTurn;
    protected char winner;
    
    public TicTacToe(char playerToken, char compToken){
        this.userMarker = playerToken;
        this.compMarker = compToken;
        this.winner = '-';
        this.board = setBoard();
        this.currentTurn = userMarker;
    }

    public static char[] setBoard() {
        char[] board = new char[9];
        for(int i = 0; i < board.length; i++)
            board[i] = '-';
        return board;
    }
    
    public boolean playTurn(int spot) {
        boolean isValid = withinRange(spot) && !isSpotTaken(spot);
        if (isValid) {
            board[spot-1] = currentTurn;
            currentTurn = (currentTurn == userMarker) ? compMarker : userMarker;
            
        }
        return isValid;
    }

    //check if spot is in range
    public boolean withinRange(int pos) {
        return pos > 0 && pos <= board.length;
    }
    
    //check if spot is taken or not
    public boolean isSpotTaken(int spot) {
        return board[spot-1] != '-';
    }
    
    public void printBoard() {
        
        //We need to print board like:
        // | - | - | - 
        // ------------
        // | - | - | -
        // ------------
        // | - | - | -
        
        System.out.println(); 
        for (int i = 0; i < board.length; i++){
            if(i%3 == 0 && i != 0){
                System.out.println();
                System.out.println("------------");
            }
            System.out.print(" | " + board[i]);
        }
        System.out.println();
    }
    
    public static void printIndexBoard() {
        
        //We need to print Index board like:
        // | 1 | 2 | 3 
        // ------------
        // | 4 | 5 | 6
        // ------------
        // | 7 | 8 | 9
        
        System.out.println(); 
        for (int i = 0; i < 9; i++){
            if(i%3 == 0 && i != 0){
                System.out.println();
                System.out.println(" ------------");
            }
            System.out.print(" | " + (i+1));
        }
        System.out.println();
    }
    
    public boolean isThereAWinner() {
        boolean diagonalAndMiddle = (rightdiag() || leftDiag() || middleRow() || secondCol()) && board[4] != '-';
        boolean topAndFirst =  (topRow() || firstCol()) && board[0] != '-';
        boolean bottomAndThird = (bottomRow() || thirdCol()) && board[8] != '-';
        if (diagonalAndMiddle){
            this.winner = board[4];
        } else if (topAndFirst) {
            this.winner = board[0];
        }else if (bottomAndThird){
            this.winner = board[8];
        }
        
        return diagonalAndMiddle || topAndFirst || bottomAndThird;
    }

    private boolean rightdiag() {
        return board[0] == board[4] && board[4] == board[8];
    }

    private boolean leftDiag() {
        return board[2] == board[4] && board[4] == board[6];
    }

    private boolean middleRow() {
        return board[3] == board[4] && board[4] == board[5];
    }

    private boolean secondCol() {
        return board[1] == board[4] && board[4] == board[7];
    }

    private boolean topRow() {
        return board[0] == board[1] && board[1] == board[2];
    }

    private boolean firstCol() {
        return board[0] == board[3] && board[3] == board[6];
    }

    private boolean bottomRow() {
        return board[6] == board[7] && board[7] == board[8];
    }

    private boolean thirdCol() {
        return board[2] == board[5] && board[5] == board[8];
    }
    
    public boolean isBoardFilled() {
        for (int i = 0;i < board.length; i++){
            if (board[i] == '-')
                return false;
        }
        return true;
    }
    
    public String gameOver() {
        boolean didSomeOneWin = isThereAWinner();
        if(didSomeOneWin) {
            if(this.winner == this.userMarker)
                return "Congrats! you won :)";
            else
                return "haha, I won! Better Luck next time";
        }else if (isBoardFilled()) {
            return "draw: GameOver!";
        }
        return "not Over";
    }
}
