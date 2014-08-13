/**
 * NetflixFileReader: Reads the text file and stores each line as an appropriate media object
 * PS: I assigned a zero value when there is no year or no star.
 *
 * Ashlesh Gawande
 * COMP 2150
 * PA 2
 */
import java.io.*;
import java.util.ArrayList; //required if you need generic ArrayList
import java.util.Scanner;
public class NetflixFileReader
{
    private ArrayList<Media> masterList;

    private Media newMedia

    private String next="";
    private String name="", temp = "", hour="", min="";

    private int year;
    private double star;        //star is the rating stored as a double.
    private double movieL;      //movieLength, stored as double, for ex: 1hr 22 min = 1.22
    private int length;         //episode length, length Season/Episode

    //returns masterList which contains media objects
    public ArrayList<Media> getMasterList()
    {
        return masterList;
    }

    //resets all the variable for next media object
    private void reset()
    {
        year=0;
        name="";
        temp="";
        hour="";
        min="";
        star=0;
        movieL=0;
    }

    //to check whether we have read a num or we must pass it, usefull for cases where the year or the length is missing
    //used in the readFile method
    private boolean isNum(String next)
    {
        try
        {
            int num = Integer.parseInt(next);
        }
        catch(NumberFormatException e)
        {
            return false;
        }
        return true;
    }

    //reads the file and creates suitable media objects with all the information
    public void readFile() throws FileNotFoundException
    {
        Scanner input = new Scanner(new File("datafile.txt"));

        masterList = new ArrayList<Media>();

        //read the next word
        next = input.next();

        //The solution below reads word by word and not line by line:
        //while there is a word to read in the file
        while (input.hasNext())
        {
            boolean fullName = true;

            if(next.length()==0)
            {
                next=input.next();
            }

            while(fullName)
            {
                if(next.charAt(0)=='(')
                {
                    //so that we make sure we are not reading an year for example if next = (1990), its first character 
                    //must be a 1 or a 2, and in the case when no year is mentioned i.e. (), it must be )
                    //If we are reading a year and not a Name we must break. 
                    //This helps in cases where we have a bracket in the name
                    //For example: Being Human(U.K) 
                    if(next.charAt(1)=='1'||next.charAt(1)=='2'||next.charAt(1)==')')
                    {
                        fullName=false;
                        break;
                    }
                }
                name+=next+" ";
                next = input.next();
            }

            //reading the year
            if(next.charAt(0)=='(')
            {
                for(int i=1; i<next.length()-1; i++)
                {
                    if(next.charAt(i)=='-'||next.charAt(i)==')')
                    {
                        break;
                    }
                    temp+=next.charAt(i);
                }
                if(next.charAt(1)==')')
                {
                    temp="0";
                }
                year=Integer.parseInt(temp);
            }
            //  "|"
            next=input.next();

            next=input.next();
            if(next.equals("|"))
            {
                next=input.next();
            }

            //i.e. our next is not "stars," i.e. we dont have a missing rating
            if(!next.contains(","))
            {
                star=Double.parseDouble(next);

                //star,
                next=input.next();

                //checking whether we break the line after stars or the time is given 
                if(input.hasNext())
                {
                    next=input.next();
                }
                //The if else below handles all the cases for hr and m, when hr is present and min in absent and so on.
                if((next.contains("hr")&&next.length()<=3)||(next.contains("m")&&next.length()<=3)||next.length()==1||next.length()==2)
                {
                    if(next.contains("hr"))
                    {
                        hour += next.charAt(0);

                        if(input.hasNext())
                        {
                            next= input.next(); 
                            //the length would never be greater than 3, ex: 31m, or 3m
                            if(next.contains("m") && next.length()<=3)
                            {
                                for(int i=0; i<next.length(); i++)
                                {
                                    if(next.charAt(i)!='m')
                                    {
                                        min += next.charAt(i); 
                                    }
                                }  

                                next=""; 
                            }
                            else
                            {
                                min="0"; 
                            }
                            movieL = Double.parseDouble(hour)+Double.parseDouble(min)/100; 
                            movieL = Math.round(movieL*100.0) / 100.0;
                        }
                    }
                    else if(next.contains("m") && next.length()<=3)
                    {
                        for(int i=0; i<next.length(); i++)
                        {
                            if(next.charAt(i)!='m')
                            {
                                min += next.charAt(i); 
                            }
                        }  
                        movieL = Double.parseDouble(min)/100;
                        movieL = Math.round(movieL*100.0) / 100.0;
                        next="";
                    }
                    else 
                    {
                        length=Integer.parseInt(next);
                        next=input.next();
                        if(next.equals("Season")||next.equals("Seasons")||next.equals("Episodes")||(next.contains("m")&&next.length()<=3)||(next.contains("hr")&&next.length()<=3))
                        {
                            if(input.hasNext())
                            {
                                next=input.next();
                            }
                        }
                    }
                }
            }
            else   //if we have a missing rating, go to next input and the rating would be automatically assigned 0.
            {
                next=input.next(); 
                if(next.contains("hr"))
                {
                    hour += next.charAt(0); 
                    if(input.hasNext())
                    {
                        next= input.next(); 
                        if(next.contains("m") && next.length()<=3)
                        {
                            for(int i=0; i<next.length(); i++)
                            {
                                if(next.charAt(i)!='m')
                                {
                                    min += next.charAt(i); 
                                }
                            }

                            next="";
                        }

                        else
                        {
                            min="0"; 
                        }
                    }
                    movieL = Double.parseDouble(hour)+Double.parseDouble(min)/100; 
                    movieL = Math.round(movieL*100.0) / 100.0;
                }
                else if(next.contains("m") && next.length()<=3)
                {
                    for(int i=0; i<next.length(); i++)
                    {
                        if(next.charAt(i)!='m')
                        {
                            min += next.charAt(i); 
                        }
                    }
                    next="";
                    movieL = Double.parseDouble(min)/100;
                    movieL = Math.round(movieL*100.0) / 100.0;
                }
                else if(isNum(next))   //making sure that we are reading some number so as to assign it as length of episodes/season
                {
                    length=Integer.parseInt(next); 
                    next=input.next();
                    if(next.equals("Season")||next.equals("Seasons")||next.equals("Episodes")||(next.contains("m")&&next.length()<=3)||(next.contains("hr")&&next.length()<=3))
                    {
                        if(input.hasNext())
                        {
                            next=input.next();
                        }
                    }
                }
            }

            //checking if the line should be stored as movie object
            if(movieL!=0)
            {
                newMedia = new Movie(name, year, star, movieL); 
            }
            else
            {
                newMedia = new Series(name, year, star, length);
            }
            masterList.add(newMedia);

            //reset the variable to read the next line:
            reset();

        }

    }
}

