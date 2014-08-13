/**
 * Calculate class. Mthod sinlePass contains the algorithm to
 * evaluate an infix expression in a single pass, w/o first converting to a postfix
 *
 * Ashlesh Gawande
 * COMP 2150
 * PA 3
 */
import java.util.Stack;
import java.util.Scanner;
public class Calculate
{
    private Stack<Double> operand;     //contains operands
    private Stack<String> operator;    //contains operators

    private static final String OPERATORS = "+-*/%^(){}[]";

    private static final int[] PRECEDENCE = {1,1,2,2,2,3,-3,-3,-2,-2,-1,-1};
                                            //precedance of ():-3, {}:-2, []: -1
    //default constructor
    public Calculate()
    {

    }

    //method use to evaluate an infix expression in a single pass
    public double singlePass(String s)
    {
        operator = new Stack<String>();
        operand = new Stack<Double>();

        String op;    //to store a popped operator;

        double num1=0, num2=0;      //to store two operands on which the operation is performed.
        double result;              //to store the operation of above numbers

        Scanner input = new Scanner(s);
        String nextToken;

        //modified version of what the book contained, \\. for decimal, \\[\\] for square brackets and so on. 
        //findInLine was used to find the next token, hence the method works irrespective of space in the input
        while(((nextToken = input.findInLine("[\\p{L}\\p{N}\\.]+|[-+/\\*%^(){}\\[\\]]"))!=null))
        {
            String token = nextToken;

            if(isNum(token))
            {
                operand.push(Double.parseDouble(token));
            }
            else if(isOperator(token))
            {
                if(operator.empty()||token.equals("(")||token.equals("{")||token.equals("["))
                {
                    operator.push(token);
                }
                else
                {
                    String top = operator.peek();
                    if(precedence(token)>precedence(top))
                    {
                        operator.push(token);
                    }

                    else
                    {
                        while(!operator.empty() && precedence(token) <= precedence(top))
                        {
                            op = operator.pop();      //pop an operator

                            if(top.equals("(")||top.equals("{")||top.equals("["))
                            {
                                break;
                            }

                            num1 = operand.pop();
                            num2 = operand.pop();

                            result=performOperation(op, num1, num2);   //performing the operation on the numbers 

                            operand.push(result);              //push back the result on to operand stack.

                            if(!operator.empty())
                            {
                                top = operator.peek();
                            }
                        }
                        if(!token.equals(")")&&!token.equals("}")&&!token.equals("]"))
                        {
                            operator.push(token);
                        }
                    }
                }
            }
        }

        //if there is anything left in the stacks.
        while(!operator.empty())
        {
            op = operator.pop();

            num1 = operand.pop();
            num2 = operand.pop(); 

            result=performOperation(op, num1, num2); 
            operand.push(result); 
        }
        return operand.peek();
    }

    //whether the string is an operator.
    public boolean isOperator(String tk)
    {
        return OPERATORS.indexOf(tk)!=-1;
    }

    //whats the precedance of the operator
    public int precedence(String tk)
    {
        return PRECEDENCE[OPERATORS.indexOf(tk)];
    }

    //to help calculate the result, so that we can append the result to operand 
    public double performOperation(String op, double num1, double num2)
    {
        double result=0; 

        switch(op)
        {
            case "+": result = num1+num2; break; 
            case "-": result = num2-num1; break;
            case "*": result = num1*num2; break;
            case "/": result = num2/num1; break;
            case "%": result = num2%num1; break;
            case "^": result = Math.pow(num2,num1); break;
        }
        return result;
    }

    //to decide whether the given string is a num.
    public static boolean isNum(String s)
    {
        try
        {
            double num = Double.parseDouble(s);
        }
        catch(NumberFormatException e)
        {
            return false;
        }
        return true;
    }

}
