
/**
 * Series Class: A Media subclass
 * 
 * Ashlesh Gawande
 * COMP 2150
 * PA 2
 */
public class Series extends Media
{
    //stores the number of episodes or series
    private int number;
    
    //Series constructor
    public Series(String name, int year, double star, int number)
    {
        super(name, year, star);
        setNum(number); 
        setType("2");
    }

    //number setter
    public void setNum(int number)
    {
        if(number>0)
        {
            this.number=number;
        }
    }
    
    //How the series object is printed: 
    public String toString()
    {     
       String s=getName()+ "(" +getYear()+") "+" |  " +getStar()+" stars, "+ number+" Episodes/Seasons\n"; 
        
       return s;
    }
}
