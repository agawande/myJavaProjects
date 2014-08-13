/**
 * Bar is a rectangle of pixels. 
 * 
 * Ashlesh Gawande
 * Project 2
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Bar extends JPanel
{    
    private JPanel pixelContainer = new JPanel();

    private Pixel [] element = new Pixel[40];
 
    //Bar can be made of any color.
    public Bar(int a, Color c)
    {
        pixelContainer.setLayout(new GridLayout(40, 1));
        for(int i=0; i<element.length; i++)
        {
            element[i] = new Pixel(); 
            if(i>element.length-a)
            {
                element[i].setColor(c);
            }
            pixelContainer.add(element[i]);
        }      

        add(pixelContainer);
    }
}
