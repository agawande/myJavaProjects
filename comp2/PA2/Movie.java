
/**
 * Movie Class: A Media subclass representing Movie
 *
 * Ashlesh Gawande
 * COMP 2150
 * PA 2
 */
public class Movie extends Media
{
    //movie length is stored as a double for ex: 1 hr 20 min = 1.20 (hr.min)
    private double length;

    //movie constructor
    public Movie(String name, int year, double star, double length)
    {
        super(name, year, star);
        setLength(length);
        setType("1");
    }

    //length setter
    public void setLength(double length)
    {
        if(length>0)
        {
            this.length=length;
        }
    }

    //how the movie object is printed
    public String toString()
    {
       String s=getName()+ "(" +getYear()+") "+" |  " +getStar()+" stars, "+ length+" hr.min\n"; 

       return s;
    }
}
