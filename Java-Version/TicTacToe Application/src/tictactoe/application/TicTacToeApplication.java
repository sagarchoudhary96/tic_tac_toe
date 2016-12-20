package tictactoe.application;

import java.util.Scanner;

public class TicTacToeApplication {

    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        //flag to play the game
        boolean doYouWantToPlay = true;
        while(doYouWantToPlay){
            
            //setting up the players and computer
            System.out.println("Welcome to Tic Tac Toe! Are you Ready?\n"
                    + "Select the Character you want to be and character I will be.");
            
            System.out.println();
            System.out.println("Enter the Character that you want to be on the board");
            char player = sc.next().charAt(0);
            System.out.println("Enter the character that you want your opponent to be on the board");
            char opponent = sc.next().charAt(0);
            TicTacToe game = new TicTacToe(player, opponent);
            Computer computer = new Computer();
            
            
            //set up the game
            System.out.println();
            System.out.println("Lets Play the game! Top play enter a number from 1-9 where you want to put your token\n");
            TicTacToe.printIndexBoard();
            System.out.println();
            
            //let's play
            while(game.gameOver().equals("not Over")){
                if(game.currentTurn == game.userMarker){
                    //User turn
                    System.out.println("It's Your turn: Enter a spot for your token: ");
                    int spot = sc.nextInt();
                    
                    while(!game.playTurn(spot)){
                        System.out.println("Try again! spot" + spot + "is invalid. This"
                                + "spot is either taken or out of range.");
                        spot = sc.nextInt();
                    }
                    
                    System.out.println("You selected spot " + spot + "!");
                    
                } else {
                    //Computer turn
                    System.out.println("Computer: It's my Turn :)");
                    
                    int computerSpot = computer.pickSpot(game);
                    game.playTurn(computerSpot);
                    System.out.println("I picked Spot " + computerSpot + "!");
                }
                //Print Board
                System.out.println();
                game.printBoard();
            }
            System.out.println(game.gameOver());
            System.out.println();
            
            //set a new Game depending upon user
            System.out.println("Do you want to play again! Enter Y to play and enter anything else to quit");
            int playAgain = sc.next().charAt(0);
            doYouWantToPlay = playAgain == 'Y' || playAgain == 'y';
            System.out.println();
            System.out.println();
        }
    }
    
}
