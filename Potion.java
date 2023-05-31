// this program holds the code for the potion subclass
// this imports the libraries to draw the potion
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

// this is a subclass of item to use general methods for all items
public class Potion extends Item {
    private Image potionGif;
    
    // this is the constructor for the potion class
    public Potion(int x, int y) {
        super(x, y);
        potionGif = Toolkit.getDefaultToolkit().getImage("POTION.gif");
        name = "Potion";
    }
    
    // this is the draw method for the potion class
    public void draw(Graphics g, int xDiff, int yDiff) {
        if (visible) {
            g.drawImage(potionGif, super.getX()+xDiff, super.getY()+yDiff, 64, 64, null);
        }
    }
}
