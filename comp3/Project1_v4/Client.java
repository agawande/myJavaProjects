/**
 * Description: This is the client which takes user inputs and generate pseduorandom words. 
 *
 *
 *
 * Ashlesh Gawande
 * Project 1
 */
import java.util.Scanner; 
import java.io.*;
import java.util.InputMismatchException;
public class Client
{
    public static void main(String [] args)
    {
        BSTDictionary stable = new BSTDictionary();         //this is made just we have something to call our
        //createDictLetterFrequencies and createDictWordLengths on.
 
        System.out.println("Test latin dictionary if you are in a hurry!");
        System.out.println("Best results for n=3(english), n=2(latin)!\n");

        Scanner input = new Scanner(System.in);

        int dataFile=1;
        int numOfWords;
        int n;

        //error checking on inputs:
        do
        {
            System.out.print("Enter data file (1=English, 2=Latin): ");
            //error checking on inputs:
            while(true)
            {
                try
                {
                    dataFile = input.nextInt();
                    break;
                }
                catch(InputMismatchException e)
                {
                    System.out.println("Please enter an integer!");
                    System.out.print("Enter data file (1=English, 2=Latin): ");
                    input.next();
                }
            }
            if(dataFile!=1&&dataFile!=2)
            {
                System.out.println("Please enter a valid file!"); 
            }
        }   while(dataFile!=1&&dataFile!=2);

        do
        {
            System.out.print("Enter n: ");
            while(true)
            {
                try
                {
                    n = input.nextInt();
                    break;
                }
                catch(InputMismatchException e)
                {
                    System.out.println("Please enter an integer!");
                    System.out.print("Enter n: ");
                    input.next();
                }
            }
            if(n<=0)
            {
                System.out.println("n should be greater than zero!"); 
            }
        }   while(n<=0);

        do
        {
            System.out.print("Enter number of words to genetarte: ");
            while(true)
            {
                try
                {
                    numOfWords = input.nextInt();
                    break;
                }
                catch(InputMismatchException e)
                {
                    System.out.println("Please enter an integer!");
                    System.out.print("Enter number of words to genetarte: ");
                    input.next();
                }
            }
            if(numOfWords<=0)
            {
                System.out.println("Please enter a positive nin zero number!"); 
            }
        }   while(numOfWords<=0);

        File file;
        if(dataFile==1)
        {
            file = new File("english.txt");
        }
        else
        {
            file = new File("latin.txt");
        }

        BSTDictionary wordLength = stable.createDictWordLengths(file);
        BSTDictionary outer = new BSTDictionary();  

        int length;
        String word=""; 
        String prev ="";
        int j=1; 
        //outer loop handles the number of words to generate
        for(int i=0; i<numOfWords; i++)
        {
            length = (int) stable.generateKey(wordLength); 
            System.out.println((i+1)+") length: "+length);

            word = generateChar(); //generating the first letter of our word.

            //generating the rest.
            while(word.length()<length)
            {
                if(j<=n)   //no need to make the dictionary again and again, afte it reaches n.
                {
                    outer = stable.createDictLetterFrequencies(j, file);
                }

                if(word.length()<n)
                {
                    if(outer.getValue(word)!=null)  //if the inner is not null.
                    {
                        word += stable.generateKey((BSTDictionary)outer.getValue(word))+"";                
                    }
                    else     //adding a purely random word if inner dictionary does not exists.
                    {
                        word+=generateChar();
                    }
                }
                else
                {
                    prev = word.substring(word.length()-n);    //for example last three letters of word.
                    if(outer.getValue(prev)!=null)
                    {
                        word += stable.generateKey((BSTDictionary)outer.getValue(prev))+"";                                            
                    }
                    else
                    {
                        word+=generateChar();
                    }
                }
                if(j<n)
                {
                    j++;
                }
            }
            j=1;   //reseting j=1 wor the next word.
            System.out.println(word+"\n");
        }
        System.out.println("Thank you for using my version of Gibberish Generator!");
    }

    //used to return a uniformly random character.
    public static String generateChar()
    {
        int random = (int) (25*Math.random());
        switch(random)
        {
            case 0: return "a";
            case 1: return "b";
            case 2: return "c";
            case 3: return "d";
            case 4: return "e";
            case 5: return "f";
            case 6: return "g";
            case 7: return "h";
            case 8: return "i";
            case 9: return "j";
            case 10: return "k";
            case 11: return "l";
            case 12: return "m";
            case 13: return "n";
            case 14: return "o";
            case 15: return "p";
            case 16: return "q";
            case 17: return "r";
            case 18: return "s";
            case 19: return "t";
            case 20: return "u";
            case 21: return "v";
            case 22: return "w";
            case 23: return "x";
            case 24: return "y";
            case 25: return "z";
            default: return ""; 
        }
    }
}
