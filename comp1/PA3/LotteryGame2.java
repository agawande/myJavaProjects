
/**
* Description: This program allows the user to play a lottery ticket while keeping track of his/her winnings. 
* Authors: Ashlesh Gawande, Kenny Diep
* Section: 104
* Date: 04/02/13
* Programming Assignment: 3
**/
import java.util.*; 
public class LotteryGame2
{
    
    public static double calculateWinnings(int [] numbers)
    {
        int [] count=new int[21];
        double [] winnings=new double[21]; 
        double totalWin=0;
        
        
        for(int i=0; i<numbers.length; i++)
        {
        for(int k =1; k<21; k++)
                    {
                        if(numbers[i]==k)
                        {
                            count[k]++;                              //count[k] keeps the count of how many times a number repeats (frequency).
                        }
                    }
                
            }
            
        System.out.println("Numbers     Frequency");    
        for(int k=1;  k<21; k++)
        {
          if(count[k]!=0)  
          {
              System.out.println(k +"\t\t" + count[k]);              // printing the count and frequency for the random ticket numbers.
             
           } 
        }
        
        for(int k=1; k<21; k++)
        {
            if(count[k]!=0&&count[k]!=1)
            {
                
                switch(count[k])
                {
                    
                    case 2: System.out.println(" "+count[k]+" matching "+ k+"'s \t"+ "Winnings: "+.1+"*"+k); break;               //printing according to frequency and showing how the winnings is calculated.
                    case 3: System.out.println(" "+count[k]+" matching "+ k+"'s \t"+ "Winnings: "+.5+"*"+k); break;
                    case 4: System.out.println(" "+count[k]+" matching "+ k+"'s \t"+ "Winnings: "+1+"*"+k); break;
                    case 5: System.out.println(" "+count[k]+" matching "+ k+"'s \t"+ "Winnings: "+2+"*"+k); break;
                    case 6: System.out.println(" "+count[k]+" matching "+ k+"'s \t"+ "Winnings: "+5+"*"+k); break;
                    case 7: System.out.println(" "+count[k]+" matching "+ k+"'s \t"+ "Winnings: "+10+"*"+k); break;
                                   
                }
            }   
            
        }
        
        
        System.out.println();
        
        for(int k=1; k<21; k++)
        {
            if(count[k]!=0)
            {                                                                       
                
                switch(count[k])            // using the swicth statement again to ccalculate the winnings for different cases. 
                {
                    case 2: winnings[k]=k*.1;   break;
                    case 3: winnings[k]=k*.5;   break;
                    case 4: winnings[k]=k;      break;
                    case 5: winnings[k]=k*2;    break;
                    case 6: winnings[k]=k*5;    break;
                    case 7: winnings[k]=k*10;   break;
                
                    default: winnings[k]=0;     break;                   
                
                }
                
            }
        
        }
        
        for (int z=1; z<21; z++)
        {
            totalWin+=winnings[z];               //so that if more than one number repeats itself, then the total win is combined.
        }
        
        totalWin= (int)((totalWin+0.005)*100.0)/100.0;         //rounding off to two decimal places.
        totalWin=(double) totalWin;
        
        System.out.println("Winnings on this ticket: $"+totalWin);                 
        
        return totalWin;
    }    
    
    public static void main(String[] args)
    {
        int playAgain, totalCost=0, ticketTotal=0;
        double winSoFar=0;
               
        Scanner input=new Scanner(System.in);
             
        
        do{
              
          int [] ticket= new int[7];
        
            for(int d=0; d<7; d++)
            {
                ticket[d]=(int)(20*Math.random()+1);        
            }    
          
         
          winSoFar+=calculateWinnings(ticket);
          winSoFar = (double)(int)((winSoFar+0.005)*100.0)/100.0;           //rounding off to two decimal places.
          
          System.out.println("Winnings so far: $"+ winSoFar);                  //when a user plays more than one ticket, this stores the total winnings so fa
        
          System.out.println("Play again (1=yes)? "); 
          playAgain=input.nextInt();
        
          totalCost+=3;
          ticketTotal++;
         
          
        } while(playAgain==1);
        
        
        System.out.println("Total money you spent on playing Scratch-Off: $"+totalCost);
        System.out.println("Total number of tickets played: "+ ticketTotal);
        System.out.println("Total money you won: $"+winSoFar);
        
        
        if(winSoFar>totalCost)
        {
            double won=winSoFar-totalCost; 
            won=(double)(int)((won+0.005)*100.0)/100.0;
            
            System.out.println("You won: $"+(won));
        }
        else if(winSoFar<totalCost)
        {
            double lost=totalCost-winSoFar; 
            lost=(double)(int)((lost+0.005)*100.0)/100.0;
            
            System.out.println("You lost: $"+(lost));
        }
        else
        {
            System.out.println("There was neither profit nor loss!");
        }
        
    }   
}
