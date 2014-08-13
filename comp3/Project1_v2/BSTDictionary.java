/**
 * Write a description of class BSTDictionary here.
 * //MAKE IT GENERAL FOR ANY FILE!
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Iterator; 
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.io.*;
import java.util.Scanner;
public class BSTDictionary<K extends Comparable<K>, V> implements Iterable<K>
{
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
            {
                throw new NoSuchElementException();
            }
        }

        // Removes the last element returned from calling next().  Not supported
        //  in this implementation.
        public void remove()
        {
            throw new UnsupportedOperationException();
        }
    }

    public Iterator<K> iterator()
    {
        return new InOrderIterator(); 
    }

    BSTNode<K, V> root; 

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

    public V getValue(K key)
    {
        return getValue(key, root);
    }

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

    public static BSTDictionary createDictWordLengths(File file)
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

        //MAKE IT GENERAL FOR ANY FILE!

        double baseFreq=100.0/98220.0;    //100, since we are dealing with %.
        baseFreq = (double) Math.round(baseFreq*1000000000)/1000000000;

        double round; 
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

    public static BSTDictionary createDictLetterFrequencies(String n, File file)
    {
        BSTDictionary<String, BSTDictionary<String, Double>> outerDict = new BSTDictionary<String, BSTDictionary<String, Double>>();        

        BSTDictionary<String, Double> innerDict = new BSTDictionary<String, Double>();

        Scanner input=null, input2=null; 
        String next, next2;

        try
        {
            input = new Scanner(file);
            input2 = new Scanner(file);
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File Not Found!");
        }

        next = input.next(); 
        next2 = input2.next();

        double numOfWords=0.0; 
        while(input.hasNext())
        {
            if(next.contains(n))
            {
                numOfWords++; 
            }
            next = input.next();
        }
        double baseFreq = 100.0/numOfWords; 

        //System.out.println(numOfWords);

        String letter=" ";  //the next letter
        while(input2.hasNext())
        {
            if(next2.contains(n))
            {
                if(next2.length()>n.length())
                {
                    for(int i=0; i<next2.length()-1; i++)
                    {
                        if((i+next2.length())<next2.length())
                        {
                            break;
                        }
                        if(next2.substring(i, i+n.length()).equals(n))
                        {
                            if(i+n.length()<next2.length())
                            {
                                letter=""+next2.charAt(i+n.length());
                            }
                            break;
                        }
                    }
                }
                else if(next2.length()==n.length())
                {
                    letter=" ";
                }
                if(!innerDict.contains(letter))
                {
                    innerDict.add(letter, baseFreq);
                }
                else
                {
                    innerDict.add(letter, baseFreq+innerDict.getValue(letter));
                }
            }
            next2 = input2.next();
        }
        outerDict.add(n, innerDict);
        return outerDict;
    }

    public K generateKey(BSTDictionary<K, V> d)
    {
        BSTDictionary<K, Double> newDict = (BSTDictionary<K, Double>) d; 

        Iterator<K> it = newDict.iterator();

        double prob = Math.random()*100.18337436899998;
        double totalProb=0.0; 

        K newIt=null;
        if(it.hasNext())
        {
            newIt = it.next();
        }
        while(it.hasNext())
        {
            totalProb+=newDict.getValue(newIt);
            if(totalProb>prob)
            {
                return newIt;
            }
            newIt = it.next();
        }   

        return newIt;
    }    

    public String toString()
    {
        return toString(root, "");
    }   

    private String toString(BSTNode<K, V> where, String indent)
    {
        if (where == null)
            return indent + "null";
        else
            return indent +"<" +where.key +", " +where.value+ ">\n" + toString(where.left, indent + " ") + "\n" + toString(where.right, indent + " ");
    }

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

    public static void main(String [] args)
    {
        // System.out.println(createDictLetterFrequencies("frit", new File("english.txt")));
        File english = new File("english.txt");

        BSTDictionary wordLength = createDictWordLengths(english);
        BSTDictionary whatNext;    //stores the dictionary of dictionary
        BSTDictionary nextFriq=null;    //stores the inner dictionary of whatNext to generateKey       

        int j=3;   //last three letters of a word, will be declared as 2 if null follows the last three letters of a word
        String next=""; 
        for(int i=0; i<100; i++)
        {
            int length = (int) wordLength.generateKey(wordLength); 
            //System.out.println(length); 

            String word = generateChar(); 

            System.out.println("Length: "+length); 

            Iterator<String> it;

            while(word.length()<length)
            {
                if(word.length()<=4)
                {
                    whatNext = createDictLetterFrequencies(word, english);                     
                }
                else
                {
                    whatNext = createDictLetterFrequencies(word.substring(word.length()-3), english);
                }
                // System.out.println(whatNext);
                it = whatNext.iterator();

                while(it.hasNext())
                {
                    nextFriq = (BSTDictionary) whatNext.getValue(it.next());         
                }
                //System.out.println("Next <letter, frequency> of "+word+" is: "+nextFriq); 

                next = (String) nextFriq.generateKey(nextFriq); 
                if(next!=null&&!next.equals(" "))
                {
                    word+=next; 
                }
                else
                {
                    word+=generateChar();  
                }

                word = word.replaceAll("\\s+","");    //cleaning the word of any whitespace
            } 
            System.out.println(word);
        }
    }
}
