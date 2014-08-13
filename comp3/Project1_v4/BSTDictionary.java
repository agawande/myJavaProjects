/**
 * Description: BSTDictionary class with two generic parameters.
 *              Includes the method add, getValue, contains and iterator (over keys).
 *              Also includes the following methods:
 *              1) createDictWordLengths
 *              2) createDictLetterFrequencies
 *              3) generateKey
 *              4) total : calculates the total number of words in a file.
 *              
 *              PS: For n=3, With the English dictionary each word takes an average 30 sec to generate.
 *                  And with the Latin dictionary it takes about 10 sec/word.
 *                  Try Latin dictionary for super quick results.
 *                  
 *                  I have included 10 sample words as an xps document. 
 *                  
 *              The main method included in this class can be used to print the dictionaries.  
 *              
 * Ashlesh Gawande
 * Project 1
 */
import java.util.Iterator; 
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class BSTDictionary<K extends Comparable<K>, V> implements Iterable<K>
{
    //class representing a single node in BSTDictionary.
    private static class BSTNode<K, V>
    {
        private K key; 
        private V value;
        private BSTNode<K, V> left, right;

        public BSTNode(K key, V value, BSTNode<K, V> left, BSTNode<K, V> right)
        {
            this.key = key;
            this.value = value; 
            this.left = left;
            this.right = right;
        }

        public String toString()
        {
            return "<"+key+", "+value+">";
        }
    }

    // Nested class for an in-order iterator over the tree
    private class InOrderIterator implements Iterator<K>
    {
        private LinkedList<BSTNode<K, V>> stack = new LinkedList<BSTNode<K, V>>();
        private BSTNode<K, V> current = root;

        // Returns whether the iterator has more elements
        public boolean hasNext()
        {
            return current != null || !stack.isEmpty();
        }

        // Returns the iterator's next element, and advances the iterator to
        //  the following element
        public K next()
        {
            if (hasNext()) {    // I added this check to ensure that the iterator has a next element before trying to get the next one
                while (current != null) {   // go left as far as possible
                    stack.push(current);
                    current = current.left;
                }

                BSTNode<K, V> temp = stack.pop();
                current = temp.right;
                return temp.key;                
            } else
                throw new NoSuchElementException();
        }

        // Removes the last element returned from calling next().  Not supported
        //  in this implementation.
        public void remove()
        {
            throw new UnsupportedOperationException();
        }
    }

    //Iterator method which is actually called.
    public Iterator<K> iterator()
    {
        return new InOrderIterator(); 
    }

    BSTNode<K, V> root; 

    //add a key, value pair to the dictionary.
    public void add(K key, V value)
    {
        if(root==null)
        {
            root = new BSTNode<K, V>(key, value, null, null);
        }
        else
        {
            add(key, value, root);
        }
    }

    //private add method, which checks for duplicates and calculates where to insert.    
    private void add(K key, V value, BSTNode<K, V> where)
    {       
        int compare = key.compareTo(where.key);        //since dictionary entries are orderd by their keys.

        if(compare==0)    //if where.data == key
        {
            where.value = value;
        }
        else if (compare < 0 && where.left == null)     
        {
            where.left = new BSTNode<K, V>(key, value, null, null);
        }
        else if (compare > 0 && where.right == null)    
        {
            where.right = new BSTNode<K, V>(key, value, null, null);
        }
        else if(compare<0)
        {
            add(key, value, where.left);
        }
        else
        {
            add(key, value, where.right);
        }
    }    

    //returns the value assosciated with a key
    public V getValue(K key)
    {
        return getValue(key, root);
    }

    //searches through to the key and returns the value.
    private V getValue(K key, BSTNode<K, V> where)
    {
        if(where==null)
        {
            return null;
        }
        else
        {
            int compare = key.compareTo(where.key);

            if(compare<0)
            {
                return getValue(key, where.left);
            }
            else if(compare>0)
            {
                return getValue(key, where.right);
            }         
            else
            {
                return where.value;
            }
        }
    }

    //returns whether a key is present in a dictionary.
    public boolean contains(K key)
    {
        return contains(key, root);
    }    

    private boolean contains(K key, BSTNode<K, V> where)
    {
        if(where==null)
        {
            return false;
        }
        else
        {
            int compare = key.compareTo(where.key);

            if(compare<0)
            {
                return contains(key, where.left);
            }
            else if(compare>0)
            {
                return contains(key, where.right);
            }
            else
            {
                return true;
            }
        }
    }

    //returns the total number of words in a given file. (Used when calculating frequency for createDictWordLengths)
    public double total(File file)
    {
        Scanner input = null;

        try
        {
            input = new Scanner(file);
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found!");
        }

        int i=0; 
        String next; 

        while(input.hasNext())
        {            
            i++;
            next = input.next();
        }

        return i;
    }

    //returns a dictionary with the frequency of word lengths as they appear in the given file.
    public BSTDictionary createDictWordLengths(File file)
    {
        BSTDictionary<Integer, Double> freqDist = new BSTDictionary<Integer, Double>();  //frequence distribution.
        Scanner input=null; 
        String next; 

        try
        {
            input = new Scanner(file);
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found!");
        }

        next = input.next();     

        double totalNumWords = total(file);

        double baseFreq=1.0/totalNumWords;    

        //so that our numbers are neat: 
        baseFreq = (double) Math.round(baseFreq*1000000000)/1000000000;        

        double round; //for rounding the doubles before adding.
        next = input.next();
        while(input.hasNext())
        {                       
            if(!freqDist.contains(next.length()))  //if the key does not exist in the dictionary add it.
            {
                freqDist.add(next.length(), baseFreq);    //since total words are 98220 and we need a %
            }
            else
            {
                //if the key already exists, add the same key, but with an incremented value. 
                round= (double) Math.round((freqDist.getValue(next.length())+baseFreq)*100000)/100000;
                freqDist.add(next.length(), round);      
            }
            //Hence using the fact, that add method replaces the key if it already exists, we can make the dictionary
            //using only one pass through the file. 
            next=input.next();
        }  
        return freqDist; 
    }

    //return a dictionary associating the previous n letter of a word with an inner dictionary 
    public BSTDictionary createDictLetterFrequencies(int n, File file)
    {
        BSTDictionary<String, BSTDictionary<String, Double>> outer = new BSTDictionary<String, BSTDictionary<String, Double>>();        

        BSTDictionary<String, Double> inner = new BSTDictionary<String, Double>();

        Scanner input=null; 
        String next;         

        try
        {
            input = new Scanner(file);
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File Not Found!");
        }        

        next = input.next(); 
        String sub="";

        int j=0; 
        int i=0;       

        //this dictionary is for storing the number of words that follow a particular string for example <"aar",8>
        //Later in the loop this will be used to calculate frequency by running over an iterator and dividing the values of 
        //inner by the values of numberSub.
        BSTDictionary<String, Double> numberSub = new BSTDictionary<String, Double>();  

        while(input.hasNext())
        {
            if(j+n<=next.length())    //if we are still shorter than or equal to the word length
            {                
                sub = next.substring(j,j+n);                

                if(j+n!=next.length())  //first handling the case of shorter than.
                {
                    if(!outer.contains(sub))
                    {
                        if(!inner.contains(next.charAt(j+n)+""))  //checking whether the inner contains the letter.
                        {
                            inner.add(next.charAt(j+n)+"", 1.0);
                        }
                        else
                        {
                            inner.add(next.charAt(j+n)+"", inner.getValue(next.charAt(j+n)+"")+1.0);
                        }
                    }
                    else
                    {
                        if(!outer.getValue(sub).contains(next.charAt(j+n)+""))   //the inner inside does not contain the next character
                        {
                            inner.add(next.charAt(j+n)+"", 1.0); 
                        }             
                        else
                        {
                            inner.add(next.charAt(j+n)+"", outer.getValue(sub).getValue(next.charAt(j+n)+"")+1.0);
                        }
                    }
                }
                else
                {
                    if(!outer.contains(sub))           //adding a "" if sub is the last term in the word.
                    {
                        if(!outer.contains(sub))
                        {
                            if(!inner.contains(""))
                            {
                                inner.add("", 1.0);
                            }
                            else
                            {
                                inner.add("", inner.getValue("")+1.0);
                            }
                        }
                    }
                    else
                    {
                        if(!outer.getValue(sub).contains(""))
                        {
                            inner.add("", 1.0); 
                        }             
                        else
                        {
                            inner.add("", outer.getValue(sub).getValue("")+1.0);
                        }
                    }
                } 

                if(!outer.contains(sub))
                {
                    outer.add(sub, inner);
                }
                else     //if outer already contains a key, how do we add rest of the fragments of the inner dictionary
                {        //this part is required because I reset the inner dictionary every loop.   
                    BSTDictionary<String, Double> innerTemp = outer.getValue(sub);

                    Iterator<String> it = innerTemp.iterator();

                    //using an iterator on the inner dictionary obtained by getting the value of an outer dictionary
                    //so that we can add to the inner dictionary, then add this bigger inner dictionary back to the 
                    //key (sub) of the outer dictionary.

                    String key; 
                    while(it.hasNext())
                    {                        
                        key = it.next();
                        if(!inner.contains(key))
                        {
                            inner.add(key, innerTemp.getValue(key));
                        }
                        else
                        {
                            inner.add(key, innerTemp.getValue(key)+1.0);
                        }
                    }                   

                    //hence this does not overrides the value of sub with a new inner dictionary
                    //but a with a new inner dictionary that contains the old values. 
                    outer.add(sub, inner);                    
                }

                //creating a new inner for cleaner addition to outer.
                inner = new BSTDictionary();

                j++;    

                if(!numberSub.contains(sub))
                {
                    numberSub.add(sub, 1.0);
                }
                else
                {
                    numberSub.add(sub, numberSub.getValue(sub)+1.0);
                }
            }
            else
            {
                j=0; 
                next = input.next();
            }

        }
        //My outer dcitionary right now looks like:
        /*<"aar", <d, 4.0>

        < , 1.0>

        <s, 1.0>

        <t, 2.0>>*/
        //So to calculate frequencies I use the numberSub dictionary I made earlier: 
        //the first entry for n=3, in the number sub dictionary would be <"aar", 8>
        //the following loop would make my outer dictionary as:
        /*<"aar", <d, 4.0/8.0>

        < , 1.0/8.0>

        <s, 1.0/8.0>

        <t, 2.0/8.0>>*/

        Iterator<String> it = outer.iterator();
        Iterator<String> total = numberSub.iterator();

        String out; 
        String key;        

        while(it.hasNext())
        {

            out = it.next();

            key = total.next(); 

            Iterator<String> in = outer.getValue(out).iterator();   //an iterator over the inner dictionary/

            String inKey;

            while(in.hasNext())
            {
                inKey = in.next();
                outer.getValue(out).add(inKey, outer.getValue(out).getValue(inKey)/numberSub.getValue(key));
            }

        }

        return outer;
    }

    public K generateKey(BSTDictionary<K, Double> d)
    {
        BSTDictionary<K, Double> newDict = (BSTDictionary<K, Double>) d; 

        Iterator<K> it = newDict.iterator();  

        double prob = Math.random();     //calculating a random number b/w 0 and 1.    

        double totalProb=0.0;          //starting at 0.0

        //We add till total probability the values of our dictionary till it becomes greater than the random no.(prob)
        //This will return the value according to their %. 
        K newIt;
        while(it.hasNext())
        {            
            newIt = it.next();
            totalProb+=newDict.getValue(newIt);
            if(totalProb>prob)
            {
                return newIt;
            }
        }

        return null;
    }    

    //How the dictionary is printed (iterator was used.)
    public String toString()
    {        
        Iterator<K> it = this.iterator();

        String  result=""; 
        K key; 

        while(it.hasNext())
        {
            key = it.next();
            result+= "< " + key+", " + this.getValue(key) + " >\n"; 

        }
        //return toString(root, "");

        return result;
    }   

    public static void main(String [] args)
    {
        BSTDictionary stable = new BSTDictionary(); 

        System.out.println("Word Length Frequency Dictionary: ");
        BSTDictionary wordLength = stable.createDictWordLengths(new File("english.txt"));

        System.out.println(wordLength);
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("--------------------------------------------------------------------------");        

        System.out.println("Dictionary of Dictionaries: ");        
        //for n=3
        BSTDictionary outer = stable.createDictLetterFrequencies(3, new File("english.txt"));
        System.out.println(outer);
    }
}
