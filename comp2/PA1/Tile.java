/**
 * Ashlesh Gawande
 * Programming Assignment 2
 * Tile Class
 */
public class Tile
{
    private String state;

    public Tile()
    {
        state="#";           //This is the default empty space, instead of E, I used #.
    }

    //sets the state of a tile, W or B
    public void setState(String color)
    {
        if(color.equals("W")||color.equals("B"))
        {
            state=color;
        }
    }

    //state getter
    public String getState()
    {
        return state;
    }

    //flipping the state of a tile.
    public void flip()
    {
        if(state.equals("W"))
        {
            state="B";
        }
        else if(state.equals("B"))
        {
            state="W";
        }
    }

    //printing the state whenever an object of tile class is called. 
    public String toString()
    {
        return state; 
    }
}
