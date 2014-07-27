
/**
 * This program asks the user to enter the number of knocked pins and calculates their score for a 5-frame, 10 pin bowling game. 
 * Author: Ashlesh Gawande
 * Section: 104
 * Date: 02/17/13
 * Version:2.0
 * Programming Assignment 1
 */
import java.util.*;
public class BowlingScore1
 {
    public static void main(String[] args)
    {   
        int F1R1,F1R2,F2R1,F2R2,F3R1,F3R2,F4R1,F4R2, F5R1, F5R2 , frameScore, totalScore=0, bonusRoll, bonusRoll1;
        
        // where F represents Frame and R represents Roll, so that F1R1 stands for frame1 and roll1 and so on.
        //two bonus rolls were needed in the case F5R1=10, hence bonusRoll1 was added.
        
        Scanner input=new Scanner(System.in);
        
        //Asking user for the number of pins they knocked out:
        
        System.out.print("Pins knocked down on the first roll(0-10): ");
        F1R1=input.nextInt();
        
        System.out.print("Pins knocked down on the second roll(0-10): ");
        F1R2=input.nextInt();
        
        
        System.out.print("Pins knocked down on the first roll(0-10): ");
        F2R1=input.nextInt();
        
        System.out.print("Pins knocked down on the second roll(0-10): ");
        F2R2=input.nextInt();
        
       
        System.out.print("Pins knocked down on the first roll(0-10): ");
        F3R1=input.nextInt();
        
        System.out.print("Pins knocked down on the second roll(0-10): ");
        F3R2=input.nextInt();
        
        
        System.out.print("Pins knocked down on the first roll(0-10): ");
        F4R1=input.nextInt();
        
        System.out.print("Pins knocked down on the second roll(0-10): ");
        F4R2=input.nextInt();
        
        
        System.out.print("Pins knocked down on the first roll(0-10): ");
        F5R1=input.nextInt();
       
        System.out.print("Pins knocked down on the second roll(0-10): ");
        F5R2=input.nextInt();
        
        /**-----------------------------------------------------------------------------------------------------------------------------------------------------------**/
        
        //There would be four distinct cases for every frame.
        //Hence four if-else statements are used for each frame.
        //Boolean statements are used to differentiate between different cases within the frames. 
         
        
        if(F1R1==10&&F2R2==0)             
        {
            F1R2=0;
            frameScore=10+F2R1+F3R1;          //where 10 equals to F1R1, frameScore is the score of frame one here.
            System.out.println("Frame one score: "+frameScore);
            
            totalScore+=frameScore;           //This calculates the total score till know.
            System.out.println("Total Score: "+ totalScore);
        }
        else if (F1R1==10&&F2R2!=0)
        {
            F1R2=0;
            frameScore=10+F2R1+F2R2;
            System.out.println("Frame one score: "+frameScore);
                       
            totalScore+=frameScore;
            System.out.println("Total Score: "+ totalScore);
        }        
        
        else if(F1R1!=10&&(F1R1+F1R2!=10))
        {
           frameScore=F1R1+F1R2;
           System.out.println("Frame one score: "+frameScore);
           
           totalScore+=frameScore;
           System.out.println("Total Score: "+ totalScore);
        }
       
        else if(F1R1!=10&&(F1R1+F1R2==10)) 
        {
            
            frameScore=F1R1+F1R2+F2R1;
            System.out.println("Frame one score: "+frameScore);
                       
            totalScore+=frameScore;
            System.out.println("Total Score: "+ totalScore);
            
        } 
        
        /**-----------------------------------------------------------------------------------------------------------------------------------------------------------**/
        //Similar cases are defined for the frames 2-4
        
         if(F2R1==10&&F3R2==0)
        {
            F2R2=0;
            frameScore=10+F3R1+F4R1;
            System.out.println("Frame two score: "+frameScore);
           
            totalScore+=frameScore;
            System.out.println("Total Score: "+ totalScore);
        }
        
        else if(F2R1==10&&F3R2!=0)
        {
           F2R2=0;
           frameScore=10+F3R1+F3R2;
           System.out.println("Frame two score: "+frameScore);
           
           totalScore+=frameScore;
           System.out.println("Total Score: "+ totalScore);
        }
        
        else if(F2R1!=10&&(F2R1+F2R2!=10))
        {
           frameScore=F2R1+F2R2;
           System.out.println("Frame two score: "+frameScore);
          
           totalScore+=frameScore;
           System.out.println("Total Score: "+ totalScore);
        }
        
        else if(F2R1!=10&&(F2R1+F2R2==10))
        {
          frameScore=F2R1+F2R2+F3R1;
          System.out.println("Frame three score: "+frameScore);
           
          totalScore+=frameScore;
          System.out.println("Total Score: "+ totalScore);  
        }
        
       /**-----------------------------------------------------------------------------------------------------------------------------------------------------------**/
        
        
       if(F3R1==10&&F4R2==0) 
       {
            F3R2=0;
            frameScore=10+F4R1+F5R1;
            System.out.println("Frame three score: "+frameScore);
            
            totalScore+=frameScore;
            System.out.println("Total Score: "+ totalScore);
       } 
          
       
       else if (F3R1==10&&F4R2!=0)
       {
            F3R2=0;
            frameScore=10+F4R1+F4R2;
            System.out.println("Frame three score: "+frameScore);
            
            totalScore+=frameScore;
            System.out.println("Total Score: "+ totalScore);
       }
       
       else if (F3R1!=10&&(F3R1+F3R2!=10))
       {
            frameScore=F3R1+F3R2;
            System.out.println("Frame three score: "+frameScore);
            
            totalScore+=frameScore;
            System.out.println("Total Score: "+ totalScore);   
       }
        
       else if (F3R1!=10&&(F3R1+F3R2==10))
       {
            frameScore=F3R1+F3R2+F4R1;
            System.out.println("Frame three score:"+ frameScore);
            
            totalScore+=frameScore;
            System.out.println("Total Score: "+ totalScore);   
       }
       
       /**-----------------------------------------------------------------------------------------------------------------------------------------------------------**/
       
       
       if(F4R1==10&&F5R2==0) 
       {
            F4R2=0;
            frameScore=10+F5R1;
            System.out.println("Frame four score: "+frameScore);
            
            totalScore+=frameScore;
            System.out.println("Total Score: "+ totalScore);
       } 
          
       
       else if (F4R1==10&&F4R2!=0)
       {
            F4R2=0;
            frameScore=10+F5R1+F5R2;
            System.out.println("Frame four score: "+frameScore);
            
            totalScore+=frameScore;
            System.out.println("Total Score: "+ totalScore);
       }
       
       else if (F4R1!=10&&(F4R1+F4R2!=10))
       {
            frameScore=F4R1+F4R2;
            System.out.println("Frame four score: "+frameScore);
        
            totalScore+=frameScore;
            System.out.println("Total Score: "+ totalScore);   
       }
        
       else if (F4R1!=10&&(F4R1+F4R2==10))
       {
            frameScore=F4R1+F4R2+F5R1;
            System.out.println("Frame four score: "+frameScore);
             totalScore+=frameScore;
            System.out.println("Total Score: "+ totalScore);   
       }
       
       /**-----------------------------------------------------------------------------------------------------------------------------------------------------------**/
       //For the last frame a bit different cases are defined, since it has to ask the user for bonus pins, if F5R1=10.
       
       if(F5R1==10)
       {
        System.out.print("Pins knocked out in the bonus roll number 1: ");
        bonusRoll=input.nextInt();
        System.out.print("Pins knocked out in the bonus roll number 2: ");
        bonusRoll1=input.nextInt();
        
        frameScore=bonusRoll+bonusRoll1+10;
        System.out.println("Frame five score: "+frameScore);
        
        totalScore+=frameScore;
        System.out.println("Total Score: "+ totalScore);
    
       }
       
       else if(F5R1!=10&&(F5R1+F5R2!=10))
       {
        frameScore=F5R1+F5R2;
        System.out.println("Frame five Score: "+frameScore);
        
        totalScore+=frameScore;
        System.out.println("Total Score: "+ totalScore);
    
       }
       
       else if(F5R1!=10&&(F5R1+F5R2==10))
       {
        System.out.print("Pins knocked down on the bonus roll: ");
        bonusRoll=input.nextInt();
        frameScore=F5R1+F5R2+bonusRoll;
        System.out.println("Frame five score: "+frameScore);
        
        totalScore+=frameScore;
        System.out.println("Total Score: "+ totalScore);
      
        }
       
    }      
        
  
 }    
