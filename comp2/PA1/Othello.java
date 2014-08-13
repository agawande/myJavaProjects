/**
 * Ashlesh Gawande
 * Programming Assignment 2
 * Main Class: Othello
 */
import java.util.Scanner;

public class Othello
{
    public static void main(String[] args)
    {
        String color="B";
        //x, y is the row and col when game is going on; row and col are for customized game.    
        //default is used for asking the player whether s/he wants to play 8X8 or customized.
        int x,y, row, col, def;
        GameBoard newGame;

        Scanner input = new Scanner(System.in);

        System.out.print("Play Default (8X8)? (1=Yes, Any other int=No): ");
        def=input.nextInt();

        if(def==1)
        {
            newGame = new GameBoard(8, 8);         //since default game is 8X8
        }
        else
        {

            do{
                System.out.print("Enter Row Length: ");
                row=input.nextInt();

                System.out.print("Enter Col Length: ");
                col=input.nextInt();

                //since you can't play the game with only 2X2 tile board
                if(row<4&&col<4)
                {
                    System.out.println("Game must be atleast 4X4.");
                }
                //since the initial configuration should be in the middle
                if(row!=col||row%2!=0||col%2!=0)
                {
                    System.out.println("Game must be a square of even length!");
                }
            } while(row<4||col<4||row!=col||row%2!=0||col%2!=0);

            newGame = new GameBoard(row,col);
        }

        System.out.println("\nInstructions:");
        System.out.println("1)Empty Tile: #, Black Tile: B, White Tile: W");
        System.out.println("2)Player 1 (Black) begins the game. Enter the row and column.");
        System.out.println("3)Example: row:2 \n\t   col:3");
        System.out.println("4)Enter row: -99 to end the game early.");
        System.out.println("5)Origin starts at top left corner!\n");
        System.out.println(newGame);

        do{
            //Ends the game if somebody wins and calls the state to determine who won.
            if(newGame.count("B")+newGame.count("W")==newGame.getRow()*newGame.getCol()||newGame.count("B")==0||newGame.count("W")==0||(!newGame.isExist("W")&&!newGame.isExist("B")))
            {
                newGame.state();
                break;
            }

            newGame.state();

            //Alternating turns based on color:
            if(color.equals("B"))
            {
                System.out.println("Player 1(B) plays: ");
            }
            else
            {
                System.out.println("Player 2(W) plays: ");
            }

            //Checking if a valid move exists for a color:
            if(newGame.isExist(color))
            {
                do{

                    do{
                        System.out.print("row: ");
                        x=input.nextInt();
                        //if you want to end the game early, very handy.
                        if(x==-99)
                        {
                            System.out.println("Thanks for playing!");
                            System.exit(0);
                        }

                        System.out.print("coloumn: ");
                        y=input.nextInt();

                        if(x<0||y<0||x>newGame.getRow()||y>newGame.getCol())
                        {
                            System.out.println("Coordinates out of board. Please try again!");
                        }

                    } while(x<0||y<0||x>newGame.getRow()||y>newGame.getCol());

                    //if move is not valid
                    if(!newGame.isValid(color, x, y))
                    {
                        System.out.println("Sorry, Invalid Move. Please Try Again!");
                    }

                } while(!newGame.isValid(color, x, y));  
                //while the placement is not valid

                System.out.println();
                newGame.place(color, x, y);

                System.out.println(newGame);

                //switching the colors
                if(color.equals("B"))
                {
                    color="W";
                }
                else
                {
                    color="B";
                }

            }       
            //if a valid move does not exist switch the colors without doing anything.
            else
            {
                //if moves doesnot exist for both, the program must break and give the output. Otherwise endless loop. 
                if((!newGame.isExist("W")&&!newGame.isExist("B")))
                {
                    System.out.println("Valid moves does not exist for any color!");
                    break;
                }
                System.out.println("Sorry, No Valid Moves Exist For "+ color+"\n");

                if(color.equals("B"))
                {
                    color="W";
                }
                else
                {
                    color="B";
                }

            }
        } while((newGame.count("B")+newGame.count("W")!=newGame.getRow()*newGame.getCol())||newGame.count("B")!=0||newGame.count("W")!=0);   
        //while the game does not end. 
    }
}
