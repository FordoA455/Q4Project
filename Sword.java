// this program holds the code for the sword subclass
// this imports the libraries to draw the sword
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

// this is a subclass of item to use general methods for all items
public class Sword extends Item {
    private Image swordGif;
    
    // this is the constructor for the sword class
    public Sword(int x, int y) {
        super(x, y);
        swordGif = Toolkit.getDefaultToolkit().createImage("SWORD.gif");
        name = "Sword";
    }

    // this is the draw method for the sword class
    public void draw(Graphics g, int xDiff, int yDiff) {
        if (visible) {
            g.drawImage(swordGif, super.getX() + xDiff, super.getY() + yDiff, 50, 50, null);
        }
    }
}
