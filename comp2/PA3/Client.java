/**
 * Client class. Instantiates the Calculate class and calls the singlePass methos on it. With improved input validation.
 *
 * Ashlesh Gawande
 * COMP 2150
 * PA 3
 */
import java.util.Scanner;
import java.util.Stack;
import java.util.EmptyStackException;
public class Client
{
    public static void main(String[] args)
    {
        Calculate my = new Calculate();

        Scanner input = new Scanner(System.in);
        String exp;

        System.out.println("Enter 'end' to terminate program.\n");

        do{
            System.out.print("Enter infix expression (positive numbers): "); 
            exp = input.nextLine();
            if(!exp.equals("end"))
            {
                try
                {
                    System.out.println("Answer: "+my.singlePass(exp)+"\n");
                } catch(EmptyStackException e)
                {
                    //if the expression is not well formed
                    if(!isWellFormed(exp))
                    {
                        System.out.println("Sorry, thats not a well formed expression!"); 
                        System.out.println("Pleas try again. Thank you.\n"); 
                    }
                    else  //the expression is not infix.
                    {
                        if(!exp.contains("-"))
                        {
                            System.out.println("Please enter an infix expression! Thank you.\n"); 
                        }
                        else
                        {
                            System.out.println("Please enter positive numbers in your infix expression! Thank you.\n"); 
                        }
                    }
                }
            }
        } while(!exp.equals("end"));
        System.out.println("\nThank you for using my version of SUPER DUPER JAVA CALCULATORT™!"); 
    }

    //whether the expression is parenthetically correct
    public static boolean isWellFormed(String s)
    {
        boolean isFormed = false;
        boolean brackets = false;
        char popped;

        Stack<Character> bracket = new Stack<Character>();

        final String OPEN = "[{(";     //to search whether charAt(x) is contained in OPEN /CLOSE
        final String CLOSE = ")}]";


        s.replaceAll("\\s+","");    //removing white space from the string for correct checking

        for(int i=0; i<s.length(); i++)
        {
            if(OPEN.contains(s.charAt(i)+""))
            {
                bracket.add(s.charAt(i));
                brackets = true;
            }
            else if(CLOSE.contains(s.charAt(i)+""))
            {
                if(!bracket.empty())
                {
                    popped = bracket.pop();
                    if(match(popped,s.charAt(i)))
                    {
                        isFormed = true;
                    }
                }
            }
        }
        //if the expression contains no brackets it is well formed.
        if(!s.contains("(")&&!s.contains("{")&&!s.contains("[")&&!s.contains(")")&&!s.contains("}")&&!s.contains("]"))
        {
            return true;
        }

        if(!bracket.empty())
        {
            isFormed = false;
        }

        return isFormed; 
    }

    //helps the isWellFormed method to match brackets. 
    private static boolean match(char a, char b)
    {
        if(a=='('&&b==')')
        {
            return true; 
        }
        else if(a=='{'&&b=='}')
        {
            return true; 
        }
        else if(a=='['&&b==']')
        {
            return true; 
        }
        else
        {
            return false; 
        }
    }
}
