/**
 * Client Class: Lets the user specify/remove filters and create currentList.  
 *
 * Ashlesh Gawande
 * COMP 2150
 * PA 2
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

public class NetflixClient
{
    public static void main(String[] args)
    {
        int addFilter=0;
        int removeFilter;
        int field, relation;
        String target;              //target is stored as String so as to allow non-integer user input.

        NetflixFileReader reader = new NetflixFileReader();

        Scanner input = new Scanner(System.in);

        //read the file and creates the masterList
        try
        {
            reader.readFile();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File Not Found!");
        }

        //getting the masterList that the reader just read.
        ArrayList<Media> masterList= reader.getMasterList();

        //Stores the filter specifed by the user.
        ArrayList<Filter> filterList = new ArrayList<Filter>();

        //The list which contains media in constraint with the specified filter.
        ArrayList<Media> currentList = new ArrayList<Media>();

        menu();
        System.out.println("PS: You have to enter 3 on \"Enter Choice\" to see the result."); 

        do{
            do
            {
                System.out.print("\nEnter Choice? (0 = I forgot the menu!): ");
                addFilter = checkInt();
                if(addFilter<0||addFilter>5)
                {
                    System.out.println("Please look at the menu and enter the correct number!"); 
                    menu();
                }

            } while(addFilter<0||addFilter>5);

            if(addFilter==5)
            {
                break;
            }

            if(addFilter==0)
            {
                menu();
            }
            //handling all the cases for Choice:
            else if(addFilter==1)
            {
                boolean exist=true;
                do
                {
                    System.out.print("\nChoose Field: ");
                    field = checkInt();
                    if(field<1||field>5)
                    {
                        System.out.println("Please look at the menu and enter the correct number!"); 
                        menu();
                    }

                } while(field<1||field>5);

                //handling all the cases for various fields
                if(field == 1)   //i.e. genre
                {
                    System.out.print("Enter target, movie or series: ");
                    target = checkTarget(field);

                    if(target.equals("1"))
                    {
                        Filter newFilter = new Filter(1, 1, target);    //the last 1=movie
                        filterList.add(newFilter);
                    }
                    else if(target.equals("2"))
                    {
                        Filter newFilter = new Filter(1, 1, target);    //the last 1=movie
                        filterList.add(newFilter);
                    }
                }

                if(field == 2)   //i.e. year
                {
                    do
                    {
                        System.out.print("Please enter relation: ");
                        relation = checkInt();

                        if(relation<1||relation>3)
                        {
                            System.out.println("Please look at the menu and enter the correct relation!");
                            menu();
                        }
                    } while(relation<1||relation>3);

                    System.out.print("Please enter the target: ");
                    target = checkTarget(field);

                    Filter newFilter = new Filter(2, relation, target);
                    filterList.add(newFilter);
                }

                if(field == 3)  //i.e. star
                {
                    do
                    {
                        System.out.print("Please enter relation: ");
                        relation = checkInt();

                        if(relation<1||relation>3)
                        {
                            System.out.println("Please look at the menu and enter the correct relation!");
                            menu();
                        }
                    } while(relation<1||relation>3);

                    System.out.print("Please enter the target (0.0-5.0): ");
                    target = checkTarget(field);

                    Filter newFilter = new Filter(3, relation, target);
                    filterList.add(newFilter);
                }

                if(field == 4) //i.e. title =
                {
                    if(filterList.size()==0)
                    {
                        System.out.println("This will search the entire database, since you do not have any previous filters."); 
                    }
                    else
                    {
                        System.out.println("This will search the limited database constrainted by your previous filters.");
                        System.out.println("To search the entire database, clear all filters by entering 4 on Choice. Thank you."); 
                    }

                    System.out.print("Please enter the target (Case and Space sensetive): ");
                    target = input.nextLine();;

                    System.out.println("Please enter 3 to show result. Thank you."); 
                    Filter newFilter = new Filter(4, 1, target);
                    filterList.add(newFilter);
                }

                if(field ==5)  //i.e. title contains
                {
                    if(filterList.size()==0)
                    {
                        System.out.println("This will search the entire database, since you do not have any previous filters."); 
                    }
                    else
                    {
                        System.out.println("This will search the limited database constrainted by your previous filters.");
                        System.out.println("To search the entire database, clear all filters by entering 4 on Choice. Thank you."); 
                    }

                    System.out.print("Please enter the target (Space and Case sensetive): ");
                    target = input.nextLine();;

                    System.out.println("Please enter 3 to show result. Thank you"); 
                    Filter newFilter = new Filter(5, 1, target);
                    filterList.add(newFilter);

                }
                currentList = match(filterList, masterList);
                System.out.println("Items that match your current filter(s): "+currentList.size());
            }
            else if(addFilter==2)
            {
                if(filterList.size()!=0)
                {
                    boolean match= false;

                    do{
                        System.out.print("Please enter the field to remove(-1=none, 0=print filters): ");
                        removeFilter = checkInt();

                        if(removeFilter==0)
                        {
                            System.out.println(filterList);
                        }
                    } while(removeFilter==0);

                    for(int i=0; i<filterList.size(); i++)
                    {
                        if(filterList.get(i).getField() == removeFilter)
                        {
                            filterList.remove(i);
                            System.out.println("Field "+removeFilter+" has been removed!"); 
                            match = true;
                        }
                    }
                    if(match==false&&removeFilter!=-1)
                    {
                        System.out.println("No such filter exist.");
                    }

                }
                else
                {
                    if(addFilter!=-1)
                    {
                        System.out.println("Nothing to remove!");
                    }
                }
                currentList = match(filterList, masterList);
                System.out.println("Items that match your current filter(s): "+currentList.size());            
            }

            else if(addFilter==3)
            {
                System.out.println("\n"+currentList);
                System.out.println("\nTotal items that match your current filter(s): "+currentList.size());  
            }

            else if(addFilter==4)
            {
                filterList.clear();
                System.out.println("All filters were cleared.");
            }

            else
            {
                System.out.println("Please look at the menu and enter the correct number. Thank You: \n");
                menu();
            }

        } while(addFilter!=5);

        System.out.println("\nThank You for using my Filter! Have a nice day!"); 
    }

    //returns the menu:
    public static void menu()
    {
        System.out.println("|---------------------------------------------------------------------------|");
        System.out.println("| CHOICE  : 1=Add Filter; 2=Remove Filter, 3=Show Result, 4=Clear Filters.  |\n| \t    5=END\t\t\t\t\t\t\t    |"); 
        System.out.println("| FIELD   : 1 = Genre, 2 = Year, 3 = Rating, Title = 4, 5=Title Contains.   |"); 
        System.out.println("| RELATION: 1:>,  2:<, 3:=                                                  |"); 
        System.out.println("| TARGET  : 1= Movie, 2= Sseries/ Specify Year/ Specify Stars/ Enter Name.  |");
        System.out.println("|---------------------------------------------------------------------------|");
    }

    //The method which figures out what to put into the currentList based on the filterList. Returns the currentList
    public static ArrayList<Media> match(ArrayList<Filter> filterList, ArrayList<Media> masterList)
    {
        boolean match = false;
        ArrayList<Media> currentList = new ArrayList<Media>();

        for(int i=0; i<filterList.size(); i++)
        {
            //cycle throught masterList for each case to figure out dynamically what we need to put in or remove
            //from our filterList
            for(int j=0; j<masterList.size(); j++)
            {
                if(filterList.get(i).getField()==1)
                {
                    if(filterList.get(i).getTarget().equals(masterList.get(j).getType()))
                    {
                        if(i==0)
                        {
                            currentList.add(masterList.get(j));
                        }
                        else
                        {
                            for(int k=0; k<currentList.size(); k++)
                            {
                                if(!currentList.get(k).getType().equals(filterList.get(i).getTarget()))
                                {
                                    currentList.remove(k);
                                }
                            }
                        }
                    }
                }
                if(filterList.get(i).getField()==2)
                {
                    if(filterList.get(i).getRelation()==1&&masterList.get(j).getYear()>Integer.parseInt(filterList.get(i).getTarget()))
                    {
                        if(i==0)   //i.e. it is the first filter of filterList
                        {
                            currentList.add(masterList.get(j));
                        }
                        else
                        {
                            for(int k=0; k<currentList.size(); k++)
                            {
                                if(currentList.get(k).getYear()<=Integer.parseInt(filterList.get(i).getTarget()))
                                {
                                    currentList.remove(k);
                                }
                            }
                        }
                    }
                    else if(filterList.get(i).getRelation()==2&&masterList.get(j).getYear()<Integer.parseInt(filterList.get(i).getTarget()))
                    {
                        if(i==0)   //i.e. it is the first filter of filterList
                        {
                            currentList.add(masterList.get(j));
                        }
                        else
                        {
                            for(int k=0; k<currentList.size(); k++)
                            {
                                if(currentList.get(k).getYear()>=Integer.parseInt(filterList.get(i).getTarget()))
                                {
                                    currentList.remove(k);
                                }
                            }
                        }
                    }
                    else if(filterList.get(i).getRelation()==3&&masterList.get(j).getYear()==Integer.parseInt(filterList.get(i).getTarget()))
                    {
                        if(i==0)   //i.e. it is the first filter of filterList
                        {
                            currentList.add(masterList.get(j));
                        }
                        else
                        {
                            for(int k=0; k<currentList.size(); k++)
                            {
                                if(currentList.get(k).getYear()!=Integer.parseInt(filterList.get(i).getTarget()))
                                {
                                    currentList.remove(k);
                                }
                            }
                        }
                    }
                }

                if(filterList.get(i).getField()==3)
                {
                    if(filterList.get(i).getRelation()==1&&masterList.get(j).getStar()>Double.parseDouble(filterList.get(i).getTarget()))
                    {
                        if(i==0)
                        {
                            currentList.add(masterList.get(j));
                        }
                        else
                        {
                            for(int k=0; k<currentList.size(); k++)
                            {
                                if(currentList.get(k).getStar()<=Double.parseDouble(filterList.get(i).getTarget()))
                                {
                                    currentList.remove(k);
                                }
                            }
                        }
                    }
                    else if( filterList.get(i).getRelation()==2&&masterList.get(j).getStar()<Double.parseDouble(filterList.get(i).getTarget()))
                    {
                        if(i==0)
                        {
                            currentList.add(masterList.get(j));
                        }
                        else
                        {
                            for(int k=0; k<currentList.size(); k++)
                            {
                                if(currentList.get(k).getStar()>=Double.parseDouble(filterList.get(i).getTarget()))
                                {
                                    currentList.remove(k);
                                }
                            }
                        }
                    }
                    else if(filterList.get(i).getRelation()==3)
                    {
                        if(masterList.get(j).getStar()==Double.parseDouble(filterList.get(i).getTarget()))
                        {
                            if(i==0)
                            {
                                currentList.add(masterList.get(j));
                            }
                            else
                            {
                                for(int k=0; k<currentList.size(); k++)
                                {
                                    if(currentList.get(k).getStar()!=Double.parseDouble(filterList.get(i).getTarget()))
                                    {
                                        currentList.remove(k);
                                    }
                                }
                            }
                        }
                        else
                        {
                            currentList.remove(masterList.get(j));
                        }
                    }
                }

                if(filterList.get(i).getField()==4)
                {
                    if(currentList.size()==0)
                    {
                        //outsourced this method to decide cleanly what we need without duplicating code.
                        currentList = titleExact(filterList.get(i).getTarget() , masterList);  
                    }
                    else
                    {
                        currentList = titleExact(filterList.get(i).getTarget() , currentList);
                    }
                }

                if(filterList.get(i).getField()==5)
                {
                    if(currentList.size()==0)
                    {
                        //outsourced this method to decide cleanly what we need without duplicating code.
                        currentList = titleContains(filterList.get(i).getTarget() , masterList);  
                    }
                    else
                    {
                        currentList = titleContains(filterList.get(i).getTarget() , currentList);
                    }

                }
            }
        }
        return currentList;
    }

    //used when title is chosen:
    public static ArrayList<Media> titleExact(String target, ArrayList<Media> masterList)
    {
        ArrayList<Media> currentList = new ArrayList<Media>();
        String name;
        target+=" ";  //added space at the end cause thats how the names are stored in my masterList 

        for(int i=0; i<masterList.size(); i++)
        {
            if(masterList.get(i).getName().equals(target))
            {
                currentList.add(masterList.get(i));
            }
        }
        return currentList;
    }

    //used when title Contains is chosen:
    public static ArrayList<Media> titleContains(String target, ArrayList<Media> masterList)
    {
        ArrayList<Media> currentList = new ArrayList<Media>();

        for(int i=0; i<masterList.size(); i++)
        {
            if(masterList.get(i).getName().contains(target))
            {
                currentList.add(masterList.get(i));
            }
        }
        return currentList;
    }

    //to make sure the user does not input a non integer in an integer field
    public static int checkInt()
    {
        Scanner input = new Scanner(System.in);
        int num=0;
        boolean cont=true;

        while(cont)
        {
            try
            {
                num=input.nextInt();
                cont=false;
            }
            catch(InputMismatchException e)
            {
                System.out.print("Please enter an integer: ");
                num = checkInt();
                cont =false;
            }
        }
        return num;
    }

    //making sure the user enters everything within valid bounds
    public static String checkTarget(int field)
    {
        Scanner input = new Scanner(System.in);
        String target="";
        int check=0;
        Double checkDouble =0.0;

        if(field==1)
        {
            do
            {
                try
                {
                    target=input.nextLine();;
                    check=Integer.parseInt(target);
                } catch(NumberFormatException e)
                {
                    System.out.print("Please enter an integer: ");
                    target =checkTarget(field);
                    check=Integer.parseInt(target);
                }
                if(check<1||check>2)
                {
                    System.out.println("Please look at the menu and enter the correct number!"); 
                    menu();
                    System.out.print("Enter target, movie or series: ");
                }

            } while(check<1||check>2);
        }

        else if(field==2)
        {
            do{
                do
                {
                    try
                    {
                        target = input.nextLine();;
                        check=Integer.parseInt(target);
                    }
                    catch(NumberFormatException e)
                    {
                        System.out.print("Please enter an integer: ");
                        target = checkTarget(field);
                    }
                    if(target.length()!=4||(check<1919||check>2012))
                    {
                        System.out.print("Please enter an year XXXX (1919-2012): "); 
                    }
                } while(check<1919||check>2012);

            }while(target.length()!=4);
        }
        else if(field==3)
        {
            do{
                try
                {
                    target = input.nextLine();;
                    checkDouble=Double.parseDouble(target);
                }
                catch(NumberFormatException e)
                {
                    System.out.print("Please enter an integer: ");
                    target = checkTarget(field);
                }
                if(checkDouble<0||checkDouble>5)
                {
                    System.out.print("Please enter a decimal b/w 0.0 and 5.0");
                }
            } while(checkDouble<0.0||checkDouble>5.0);

        }
        return target;
    }

}

