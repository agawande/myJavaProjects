/**
 * Pixel class provided for HW5.
 * Makes a colored JPanel.
 * 
 */
import java.awt.*; 
import javax.swing.*; 

public class Pixel extends JPanel
{
    private Color color = Color.WHITE;  

    public void setColor(Color c)
    {
        this.color = c; 
    }

    //return the color of pixel. 
    public Color getColor()
    {
        return color; 
    }
    
    // the paintComponent method is called whenever an item is rendered on the screen
    public void paintComponent(Graphics g)
    {
        g.setColor(color);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
    }
}
