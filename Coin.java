// this program holds the code for the coin subclass
// this imports the libraries to draw the coin
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

// this is a subclass of item to use general methods for all items
public class Coin extends Item {
    private Image coinGIF;
    
    // this is the constructor for the coin class
    public Coin(int x, int y) {
        super(x, y);
        coinGIF = Toolkit.getDefaultToolkit().createImage("coin.gif");
        name = "Coin";
    }
    
    // this is the draw method for the coin class
    public void draw(Graphics g, int xDiff, int yDiff) {
        if (visible) {
            g.drawImage(coinGIF, super.getX()+xDiff, super.getY()+yDiff, 50, 50, null);
        }
    }
}
