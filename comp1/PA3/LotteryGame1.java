
/**
* Description: This program allows the user to play two types lottery games and also computes his/her chance of winning. 
* Authors: Ashlesh Gawande, Kenny Diep
* Section: 104
* Date: 04/02/13
* Programming Assignment: 3
**/
import java.util.*;
public class LotteryGame1
{
   public static double jackpotChance(int k, int n)
   {
       double chance, numerator=1, denom=1; 
              
          int l = n-k;              // so that we can define the lower limit: 36 for 42 as n, so that numerator starts with 37*38
           
          while(l<n)
          {
              numerator=numerator*(l+1);     //so that if n=42, k=6, numerator=42*41*40*39*38*37
              l++;
          }
          
          while(k>0)
          {
              denom=denom*k;             //if k=6, denom= 6*5*4*3*2*1
              k--;
          }
          
       chance=denom/numerator;        // since chance=1/(numerator/denominator).
       return chance;
    
    }
    
   public static double jackpotChance(int k, int n, int m)
   {
    double chance, numerator=1, denom=1; 
    
    int l=n-k;                        //similar as above
    while(l<n)
          {
              numerator=numerator*(l+1);    
              l++;
          }
    
    while(k>0)
          {
              denom=denom*k;             
              k--;
          }      
          
    numerator=numerator*m;    
    chance=denom/numerator;
    
    return chance;
   }  
   
 
   
   public static void main(String[] args)
   {
       int gameType = 0;
       int k, n, bonusLimit, m; 
       Scanner input = new Scanner(System.in);
       
       do
        {
         System.out.print("Please enter the subtype number of the game you want to play (1 or 2): ");
         gameType=input.nextInt();
        
         if (gameType < 1 || gameType > 2)
         {
          System.out.println("Sorry that is an invalid entry!");
         }
        } while(gameType<1 || gameType>2);            // making sure that game type is either 1 or 2.
      
       
       if(gameType==1)
       {
            do{
             System.out.print("Please enter how many number you want to pick (k): ");
             k=input.nextInt();                                                                                 //total numbers user wants to pick.
             if (k < 1)
             {
              System.out.println("Sorry that is an invalid entry!");
              }
            } while(k<0);
            
            do{
             System.out.print("Please enter the highest number that could be drawn from random (n): ");              //the upper limit of numbers the user want to choose from.
             n=input.nextInt();
             if (n < 1)
             {
              System.out.println("Sorry that is an invalid entry!");
              }
            } while(n<0||n<k);
            
            
            System.out.println("Chance of winning: "+ jackpotChance(k,n));
            
            
            
            int [] userGuess= new int[k];
                        
            for(int i=0; i<k; i++)
            {
                
            
             {
                System.out.print("Please enter your choice for slot "+(i+1)+": ");
                userGuess[i]=input.nextInt();       
                
                if(userGuess[i]<1)
                {
                    System.out.println("Number can not be negative or zero!");
                    i--;
                }
                
                else if(userGuess[i]>n)
                
                {
                    System.out.println("Number should be between 1 and "+n+" inclusive!");
                    i--;
                }
                
                else{                                                                 //else is necessary to make sure that if a user enters a negative number on the first call, we dont get out of bounds exception.
                for (int j=0; j<k; j++)
                {
                    if(i==j)
                        {
                            j++;                                                     // this makes sure that the number is not checked against itself.
                        }
                        else if(userGuess[i]==userGuess[j])                          //checking to see if there are any repeat inputs. 
                        {
                                         
                            System.out.println("You cannot repeat numbers!");              
                            i--;
                            break;
                        
                        }
                }
                    }
             }
            
            
            }           
          
           
            
            int [] winningNum=new int[k];
            
            for(int i=0; i<k; i++)                                        
            {
                winningNum[i]=(int) (n*Math.random()+1);              //drawing a random number
              
                for(int j=0; j<k; j++)
                {
                    if(i==j)
                    {
                        j++;
                    }
                    else if (winningNum[i]==winningNum[j])
                    {
                        i--;                                        // redrawing a random number if its a repeat.
                        break;
                    }
                  
                }
                
             }
             
             System.out.print("Winning lottery numbers are: ");
             for(int z=0; z<k; z++)
             {
                System.out.print(winningNum[z]+" ");                  //printing the random numbers drawn.
             }
            
            System.out.println();
            
            int count=0;
            
             for(int i=0; i<k; i++)
             {
               
                for(int j=0; j<k; j++)
                {
                    if(userGuess[i]==winningNum[j])
                    {
                       count++;                               //so that if the count is equal to k, then all the numbers match.
                    }
                
                }
               
             }
             
              if (count==k)
                {
                    System.out.println("Congratulations, you won the jackpot!");
                }
                else
                {
                    System.out.println("Sorry, better luck next time!");
                }
             
             
       }
     
       
            else if(gameType==2)
            {
                 do{
                     System.out.print("Please enter how many number you want to pick (k): ");
                     k=input.nextInt();
                   if (k < 1)
                   {
                   System.out.println("Sorry that is an invalid entry!");
                   } 
                    } while(k<0);
            
                 do{
                     System.out.print("Please enter the highest number that could be drawn from random (n): ");
                     n=input.nextInt();
                      if (n < 1)
                   {
                   System.out.println("Sorry that is an invalid entry!");
                   }
                    } while(n<0||n<k);
            
                 do{
                     System.out.print("Please enter the highest bonus number limit (m): ");               //for example a user wants a bonus number to be picked from 1 to 39, then bonus liit is 39.
                     bonusLimit=input.nextInt(); 
                      if (bonusLimit < 1)
                   {
                   System.out.println("Sorry that is an invalid entry!");
                   }  
                    } while(bonusLimit<0);
            
                 do{
                     System.out.print("Please enter your bonus number guess (between 1 and "+bonusLimit+"): ");       // for example asking the user to pick a bous number between 1 and 39, which will be our m.
                     m=input.nextInt();
                      if (m < 1)
                   {
                   System.out.println("Sorry that is an invalid entry!");
                   }
                    } while(m<0||m>bonusLimit);
            
            
            System.out.println("Chance of winning: "+ jackpotChance(k,n,m));
            
            int [] userGuess= new int[k];
            
                          
            for(int i=0; i<k; i++)
            {
                
                System.out.print("Please enter your choice for slot "+(i+1)+": ");
                userGuess[i]=input.nextInt();       

                if(userGuess[i]<1)
                {
                    System.out.println("Number can not be negative or zero!");
                    i--;
                }
                
                else if(userGuess[i]>n)
                
                {
                    System.out.println("Number should be between 1 and "+n+" inclusive!");
                    i--;
                }
                
                else{                                   //else is necessary to make sure that if a user enters a negative number on the first call, we dont get out of bounds exception.
                for (int j=0; j<k; j++)
                {
                    if(i==j)
                    {
                        j++;
                    }
                    else if(userGuess[i]==userGuess[j])
                    {
                        System.out.println("You cannot repeat numbers!");
                        i--;
                        break;
                    }
                }    
                    }
            }
            
                       
            int [] winningNum=new int[k];
            
            for(int i=0; i<k; i++)
            {
                winningNum[i]=(int) (n*Math.random()+1);
              
                for(int j=0; j<k; j++)
                {
                    if(i==j)
                    {
                        j++;
                    }
                    else if (winningNum[i]==winningNum[j])
                    {
                      
                        i--;
                        break;
                    }
                  
                }
                
             }
             
              
            int randBonusNum=(int) (m*Math.random()+1); 
            
            
            System.out.print("Winning lottery numbers are: ");
            for (int z=0; z<k; z++)
            {
                System.out.print(winningNum[z]+" ");           
            }
            System.out.println();
            System.out.println("Winning bonus number is: "+ randBonusNum);
            
             System.out.println();
              int count=0;  
             for(int i=0; i<k; i++)
             {
               
                for(int j=0; j<k; j++)
                {
                    if(userGuess[i]==winningNum[j])
                    {
                       count++;                             //so that if the count is equal to k, then all the numbers match.
                    }
                
                }
               
             }
             
              if (count==k&&randBonusNum==m)                //To win count should be equal to k and the users bonus number should match with the randomly drawn bonus numbers.
                {
                    System.out.println("Congratulations, you won the jackpot!");
                }
                else
                {
                    System.out.println("Sorry, better luck next time!");
                }
            
            
            
            }

 
 
}

   
}

