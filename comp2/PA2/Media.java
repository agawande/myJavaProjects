
/**
 * Media Class: Abstract class which represents a series or a movie.
 *
 * Ashlesh Gawande
 * COMP 2150
 * PA 2
 */
public abstract class Media
{
    private String name;
    private int year;
    private double star;
    private String type;   //type represents whether it is movie or series.

    //constructor for common elements
    public Media(String name, int year, double star)
    {
        this.name = name;
        setYear(year);
        setStar(star);
    }

    //type setter
    public void setType(String type)
    {
       this.type=type;
    }

    //type getter
    public String getType()
    {
        return type;
    }

    //year setter
    public void setYear(int year)
    {
        if(year>=1890)
        {
            this.year = year;
        }
    }

    //star setter
    public void setStar(double star)
    {
        if(star>=1&&star<=5)
        {
            this.star = star;
        }
    }

    //name getter
    public String getName()
    {
        return name;
    }

    //year getter
    public int getYear()
    {
        return year;
    }

    //star getter
    public double getStar()
    {
        return star;
    }
}
