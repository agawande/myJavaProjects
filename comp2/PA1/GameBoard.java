/**
 * Ashlesh Gawande
 * Programming Assignment 2
 * GameBoard Class
 */
public class GameBoard 
{
    private Tile [][] board; 
    private int row=8;
    private int col=8;

    //GameBoard Constructor: Customizable.
    public GameBoard(int row, int col)
    {
        this.row=row;
        this.col=col; 

        board = new Tile[row][col];

        for(int y=0; y<row; y++)
        {
            for(int k=0; k<col; k++)
            {
                board[y][k] = new Tile();
            }
        }
        //The middle of the board should be already set
        board[row/2-1][(col/2)-1].setState("W");
        board[row/2][(col/2)-1].setState("B");
        board[(row/2)-1][col/2].setState("B");
        board[row/2][col/2].setState("W");

    }

    //row getter
    public int getRow()
    {
        return this.row;     
    }    

    //column getter
    public int getCol()
    {
        return this.col;     
    }    

    //count the pieces of a specific color
    public int count(String color)
    {
        int num=0;    

        for(int i=0; i<this.row; i++)
        {
            for(int j=0; i<this.col; j++)
            {
                if(j==this.getRow())
                {
                    break;
                }
                if(board[i][j].getState().equals(color))
                {
                    num++;
                }
            }       
        }
        return num;
    }

    //place the object and flips anything relevant
    public void place(String color, int row, int col)
    {  
        int position=-9;            //default value chosen -9, so it doesnot mess up with the loop value(0-7) which i assign to position 
        int position1=-9;           //Also if length of board is something other than 8X8, -9 would still work
        int rowCopy=row;            //so that I can restore defaults after changing the col and rows value when flipping diagonally
        int colCopy=col;

        board[row][col].setState(color);

        //checking whether and upto what we have to flip vertically upwards.
        for(int i=row-1; i>=0; i--)
        {
            if(board[row-1][col].getState().equals(color)||board[row-1][col].getState().equals("#"))
            {
                break;
            }
            if(board[i][col].getState().equals(color)&&!board[i][col].getState().equals("#"))
            {

                if(board[i][col].getState().equals("#"))
                {
                    break;
                }
                position=i;
                break;
            }
        }

        //flipping vertically upwards
        if(position!=-9)
        {
            for(int i=position+1; i<=row; i++)
            {
                if(!board[i][col].getState().equals(color)&&!board[i][col].getState().equals("#"))
                {
                    board[i][col].flip();
                }    
            }
        }
        position=-9;

        //checking whether and upto what we have to flip vertically downwards.
        for(int i=row+1; i<this.row; i++)
        {

            if(board[row+1][col].getState().equals("#")||board[row+1][col].getState().equals(color))
            {
                break;
            }

            if(board[i][col].getState().equals(color)&&!board[i][col].getState().equals("#"))
            {
                position=i;
                break;
            }
        }

        //flipping vertically down
        if(position!=-9)
        {
            for(int i=row+1; i<position; i++)
            {
                if(!board[i][col].getState().equals(color)&&!board[i][col].getState().equals("#"))
                {
                    board[i][col].flip();
                }    
            }
        }
        position=-9;

        //checking whether and upto what we have to flip horizontally to the right.
        for(int i=col+1; i<this.col; i++)
        {
            if(board[row][col+1].getState().equals(color)||board[row][col+1].getState().equals("#"))
            {
                break;
            }
            if(board[row][i].getState().equals(color)&&!board[row][i].getState().equals("#"))
            {
                position=i;
                break;
            }            
            if(board[row][i].getState().equals("#"))
            {
                break;
            }
        }

        //flipping horizontally right elements
        if(position!=-9)
        {
            for(int i=col+1; i<position; i++)
            {
                if(!board[row][i].getState().equals(color)&&!board[row][i].getState().equals("#"))
                {
                    board[row][i].flip();
                }
            }
        }
        position=-9;

        //checking whether and upto what we have to flip horizontally to the left.
        for(int i=col-1; i>=0; i--)
        {
            if(board[row][col-1].getState().equals(color)||board[row][col-1].getState().equals("#"))
            {
                break;            
            }

            if(board[row][i].getState().equals(color)&&!board[row][i].getState().equals("#"))
            {
                position=i;
                break;
            }
            if(board[row][i].getState().equals("#"))
            {
                break;
            }
        }

        //flipping horizontally left elements
        if(position!=-9)
        {
            for(int i=col-1; i>position; i--)
            {
                if(!board[row][i].getState().equals(color))
                {
                    board[row][i].flip();
                }
            }
        }
        position=-9;

        //checking whether and upto what we need to flip in diagonally up to the left
        for(int i=row-1; i>=0;i--)
        {
            if(col>0)
            {
                col--;
            }
            if(board[i][col].getState().equals(color)&&!board[i][col].getState().equals("#"))
            {
                position=i;
                position1=col;

                if(board[row-1][col].getState().equals(color))
                {
                    break;
                }
            }
        }

        //flipping diagonally wrt to above method
        if(position1!=-9)             //if poisition1==9, that is default, we must not flip. 
        {
            for(int i=position+1; i<=row; i++)
            {
                if(position1<this.col)
                {
                    position1++;
                }
                if(!board[i][position1].getState().equals("#")&&!board[i][position1].getState().equals(color))
                {
                    board[i][position1].flip();
                }    
            }
        }        
        col=colCopy;   //reseting the the columns and rows after they get changed in the above for loop
        row=rowCopy;        
        position=-9;
        position1=-9;

        //checking whether and upto what  we need to flip in diagonally down to the right
        for(int i=row+1; i<this.row-1; i++)
        {
            if(col<this.col-1)
            {
                col++;
            }

            if(board[i][col].getState().equals(color)&&!board[i][col].getState().equals("#"))
            {
                position=i;
                position1=col;

                //if the series is like (W) B W B W, it must stop after second W.
                if(board[i][col].getState().equals(color)||board[i][col].getState().equals("#"))
                {
                    break;
                }

            }
        }

        if(position1!=-9)
        {
            for(int i=position-1; i>row; i--)
            {
                if(position1>0)
                {
                    position1--;
                }
                if(!board[i][position1].getState().equals("#")&&!board[i][position1].getState().equals(color))
                {
                    board[i][position1].flip();
                }    

            }

        }
        row=rowCopy;
        col=colCopy;
        position=-9;
        position1=-9;

        //checking whether and upto what we need to flip diagonally up to the right:
        for(int i=row-1; i>=0; i--)
        {
            if(col<this.col-1)
            {
                col++;
            }
            if(board[i][col].getState().equals(color)&&!board[i][col].getState().equals("#"))
            {
                position=i;
                position1=col;
                if(board[i][col].getState().equals(color))
                {
                    break;
                }
            }
        }

        //flipping:
        if(position!=-9&&position1!=-9)
        {   
            for(int i=position+1; i<row; i++)
            {
                if(position1>colCopy)
                {
                    position1--;
                }
                if(!board[i][position1].getState().equals(color)&&!board[i][position1].getState().equals("#"))
                {
                    board[i][position1].flip();
                }
            }
        } 
        row=rowCopy;
        col=colCopy;
        position=-9;
        position1=-9;

        //chehcking whether and upto what flip diagonally down to the left:
        for(int i=row+1; i<this.row; i++)
        {
            if(col>0)
            {
                col--;
            }
            if(board[i][col].getState().equals(color)&&!board[i][col].getState().equals("#"))
            {
                position=i;
                position1=col;
                if(board[i][col].getState().equals(color)||board[i][col].getState().equals("#"))
                {
                    break;
                }
            }
        }

        //flipping diagoally down to the left
        if(position!=-9)
        {
            for(int i=position-1; i>=0; i--)
            {
                if(position1<colCopy)
                {
                    position1++;
                }
                if(!board[i][position1].getState().equals(color)&&!board[i][position1].getState().equals("#"))
                {
                    board[i][position1].flip();
                }

            }
        }

    }

    //Whether the move is valid:
    public boolean isValid(String color, int row, int col)
    {
        boolean valid=false;
        int rowCopy=row;
        int colCopy=col;

        //if the tile is not empty return false
        if(board[row][col].getState().equals("B")||board[row][col].getState().equals("W"))
        {
            return false;
        }

        //if the tile is empty check wether the poisition is correct for the color
        if(!board[row][col].getState().equals("B")||!board[row][col].getState().equals("W"))
        {
            //chechking vertically up
            for(int i=row-1; i>=0; i--)
            {   
                if(board[row-1][col].getState().equals(color)||board[row-1][col].getState().equals("#"))
                {
                    break;
                }

                if(board[i][col].getState().equals(color)&&!board[i][col].getState().equals("#"))
                {
                    valid=true;
                    if(board[i][col].getState().equals("#"))
                    {
                        break;
                    }
                }
            }          

            //checking vertically downwards
            for(int i=row+1; i<this.row; i++)
            {
                if(board[row+1][col].getState().equals(color)||board[row+1][col].getState().equals("#"))
                {
                    break;
                }
                if(board[i][col].getState().equals(color)&&!board[i][col].getState().equals("#"))
                {
                    valid=true;

                }
            }

            //checking horizontally to the right.
            for(int i=col+1; i<this.col; i++)
            {
                if(board[row][col+1].getState().equals(color)||board[row][col+1].getState().equals("#"))
                {
                    break;
                }
                if(board[row][i].getState().equals(color)&&!board[row][i].getState().equals("#"))
                {
                    valid=true;

                }
                if(board[row][i].getState().equals("#"))
                {
                    break;
                }
            }

            //checking horizontally to the left
            for(int i=col-1; i>=0; i--)
            {
                if(board[row][col-1].getState().equals(color)||board[row][col-1].getState().equals("#"))
                {
                    break; 
                }
                if(board[row][i].getState().equals(color)&&!board[row][i].getState().equals("#"))
                {
                    valid=true;

                }
                if(board[row][i].getState().equals("#"))
                {
                    break;
                }
            }

            //diagonally up to the left
            for(int i=row-1; i>=0;i--)
            {
                if(col>0)
                {
                    col--;
                }
                else
                {
                    break;
                }
                //checking whether nearest element is of the same color!
                if(colCopy>0)
                {
                    if(board[row-1][colCopy-1].getState().equals(color)||board[row-1][colCopy-1].getState().equals("#"))
                    {
                        break;
                    }
                }

                if(board[i][col].getState().equals(color)&&!board[i][col].getState().equals("#"))
                {
                    valid=true; 

                    break;
                }
            }
            row=rowCopy;
            col=colCopy;

            //checking diagonally down to the right
            for(int i=row+1; i<this.row-1; i++)
            {
                if(col<this.col-1)
                {
                    col++;
                }
                else
                {
                    break;
                }
                if(colCopy<this.col-1)
                {
                    if(board[row+1][colCopy+1].getState().equals(color)||board[row+1][colCopy+1].getState().equals("#"))
                    {
                        break;
                    }
                }
                if(board[i][col].getState().equals(color)&&!board[i][col].getState().equals("#"))
                {
                    valid=true;
                    break;
                }
            }
            row=rowCopy;
            col=colCopy;

            //checking diagonally up to the right:
            for(int i=row-1; i>=0; i--)
            {
                if(col<this.col-1)
                {
                    col++;
                }
                else
                {
                    break;
                }                
                //if the nearest element is of the same color
                if(colCopy<7)
                {
                    if(board[row-1][colCopy+1].getState().equals(color)||board[row-1][colCopy+1].getState().equals("#"))
                    {
                        break;
                    }
                }
                if(board[i][col].getState().equals(color)&&!board[i][col].getState().equals("#"))
                {
                    valid=true;  
                    break;
                }
            }
            row=rowCopy;
            col=colCopy;

            //diagonally down to the left:
            for(int i=row+1; i<this.row; i++)
            {
                if(col>0)
                {
                    col--;
                }
                else
                {
                    break;
                }
                if(colCopy>0)
                {
                    if(board[row+1][colCopy-1].getState().equals(color)||board[row+1][colCopy-1].getState().equals("#"))
                    {
                        break; 
                    }
                }
                if(board[i][col].getState().equals(color)&&!board[i][col].getState().equals("#"))
                {
                    valid=true;

                }
            }
        }

        return valid;
    }

    //checking whether a poisition exists for a color on the board, if not return false.
    public boolean isExist(String color)
    {
        boolean exist=false;

        for(int i=0; i<this.row; i++)
        {
            for(int j=0; j<this.col; j++)
            {
                exist = isValid(color, i, j);
                if(exist==true)
                {
                    break;
                }
            }
            if(exist==true)
            {
                break;
            }
        }
        return exist;
    }   

    //deciding the state of th match, and whether anybody has won or the game is going on.
    public void state()
    {
        System.out.println("B: "+count("B")+ " W: "+count("W"));

        if(count("B")+count("W")==this.getRow()*this.getCol()||count("B")==0||count("W")==0||(!isExist("W")&&!isExist("B"))) 
        { 
            if(count("W")>count("B"))
            {
                System.out.println("Player 2(W) won the game!"); 
                System.out.println("Thanks For Playing!\n");
            }       
            else if(count("B")>count("W"))
            {
                System.out.println("Player 1(B) won the game!"); 
                System.out.println("Thanks For Playing!\n");
            }
            else
            {
                System.out.println("The game was Tie!"); 
                System.out.println("Thanks For Playing!\n");
            }
        }      
    }

    //printing the tiles. 
    public String toString()
    {
        String s="";

        if(this.row<=10)
        {
            System.out.print("  ");

            for(int i=0; i<row; i++)
            {
                //this if-else is used to print the the position
                if(i==row-1)
                {
                    System.out.println(i);
                }
                else
                {
                    System.out.print(i+"  ");
                }
                s+=i+" ";
                for(int j=0; j<col; j++)
                {
                    s+=board[i][j]+"  ";
                }
                s+="\n";
            }
        }
        //if the row is greater than or equal to 10, we must make some changes to make the board print better
        else
        {
            System.out.print("   ");
            for(int i=0; i<row; i++)
            {
                if(i==row-1)
                {
                    System.out.println(i);
                }
                else
                {
                    if(i>=10)
                    {
                        System.out.print(i+"   ");
                    }
                    else
                    {
                        System.out.print("0"+i+"  ");
                    }
                }

                if(i>=10)
                {
                    s+=i+" ";
                }
                else
                {
                    s+="0"+i+" ";
                }
                for(int j=0; j<col; j++)
                {
                    if(j<10)
                    {
                        s+=" "+board[i][j]+"  ";
                    }
                    else
                    {
                        s+=" "+board[i][j]+"   ";
                    }
                }
                s+="\n";
            }
        }

        return s;
    }
}

