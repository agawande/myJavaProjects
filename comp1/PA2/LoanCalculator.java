
/**
 * This program reads principal, rate and term of loan as user input. Then it calculates the monthly payment, payment towards interest & principal for each month, and the total interest.
 * Author: Ashlesh Gawande
 * Section: 104
 * Date: 03/04/13
 * Programming Assignment 2
 */
import java.util.*;
public class LoanCalculator
{
  public static void main(String[] args)
  {
      
     int loanTerm, count=1, count1=2, reStart;          //using count to keep track of the loan term and count1 to keep track of months.
     double principal, rate, numerator, denominator=1, payToInterest=0, totalInterest=0, principalPaid, monthlyPay;    
      //payToInterest--> part of the monthly payment that contributes towards interest.
      //prinicpalPaid is the part of monthly payment that contributes towards principal.
          
     
     Scanner input=new Scanner(System.in);
    
     
        do
            {                 
                System.out.print("Enter the amount of money borrowed in dollars($100-$1000000): ");
                principal=input.nextDouble();

            
            } while(principal<100||principal>1000000);
            
        do    
            {
                System.out.print("Enter the annual interest rate(0.5%-15%): ");
                rate=input.nextInt();
            
            } while(rate<0.5||rate>15);
            
        do    
            {
                System.out.print("Enter the term of loan in months(1-360): ");
                loanTerm=input.nextInt();
            
            } while(loanTerm<1||loanTerm>360);    
       
     do{       
     
        rate=(double)rate/1200;                          //so that 4% is taken as 0.04/12
        numerator=principal*rate;               // calculating the numerator for the formula
          
     while(count<=loanTerm)               
        
        {
            denominator=(1/(1+rate))*denominator;      //To take the exponent of the term(1/(1+r))
        
            count++;
        }       
        
        denominator=1-denominator;                //The term 1-(1/(1+r))
        
        monthlyPay=numerator/denominator;
        monthlyPay=(double)(int)((monthlyPay+0.005)*100)/100;             //rounding the monthly payment to two decimal places
        
        
     System.out.println();
        
     System.out.println("Your monthly payment amount is: $ "+ monthlyPay);
     System.out.println();
        
     
        payToInterest=rate*principal;
        payToInterest=(double)(int)((payToInterest+0.005)*100)/100;             //rounding the pay towards interest.
        
        principalPaid=monthlyPay-payToInterest;
        principalPaid=(double)(int)((principalPaid+0.005)*100)/100;           //rounding the principalPaid
        
        
        
            System.out.println("\t\tInterest Paid\t\tPrincipal Paid\t\tTotal Interest");
            System.out.print("month 1:\t$ "+ payToInterest);
            System.out.print("                 $ "+ principalPaid );
           
            totalInterest+=payToInterest;
            System.out.println("                 $ "+totalInterest);
            System.out.println();
        
     
        while(count1<=loanTerm)
        {
            principal=principal-principalPaid;             //At first principal is for example, 9000, then the remaining principle would be 9000-monthlyPay
            principal=(double)(int)((principal+0.005)*100)/100;
            
            System.out.println();
            
            if(principal>0)                             //loop stops when principal becomes zero at the end.
                {
                     
                    payToInterest=rate*principal;
                    payToInterest=(double)(int)((payToInterest+0.005)*100)/100;
                    
                    principalPaid=monthlyPay-payToInterest;
                    principalPaid=(double)(int)((principalPaid+0.005)*100)/100;
            
                    System.out.print("month "+count1+":\t$ "+ payToInterest);
                    System.out.print("                 $ "+ principalPaid );
            
            
                    totalInterest+=payToInterest;                                  // calculating the total interest.
                    totalInterest=(double)(int)((totalInterest+0.005)*100)/100;
                    
                    System.out.println("                 $ "+totalInterest);
                    System.out.println();
                
                }
          
            count1++;
        
        }
        
     System.out.println("The total interest is: $ "+totalInterest);
            
            //reinitializing so that any result from the above loop is cleared.
            denominator=1;
            numerator=0;
            count=1;
            count1=2;
            totalInterest=0;
            
            //Asking the user whether to start again
            System.out.println();
            System.out.print("Start again? (1=yes): ");
            reStart=input.nextInt();
          
          // To reStart the loop:
            
          if(reStart==1)
          {
            do
            {                 
                System.out.print("Enter the amount of money borrowed in dollars($100-$1000000): ");
                principal=input.nextDouble();

            
            } while(principal<100||principal>1000000);
            
            do    
            {
                System.out.print("Enter the annual interest rate(0.5%-15%): ");
                rate=input.nextInt();
            
            } while(rate<0.5||rate>15);
            
            do    
            {
                System.out.print("Enter the term of loan in months(1-360): ");
                loanTerm=input.nextInt();
            
            } while(loanTerm<1||loanTerm>360);    
            
          }
          
          else
            {
                System.out.println("Thank you for using the program!");  
            }
            
     } while(reStart==1);
   }
         
}     


