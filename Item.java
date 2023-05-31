// this program has all of the code for the item subclass
// this imports the graphics method to draw the item which is used in the abstract draw method
import java.awt.Graphics;
public abstract class Item {
    // this is the item's instqance variables
    private int x;
    private int y;
    private int startX;
    private int startY;
    protected String name;
    protected boolean visible;
    private boolean inRange;
    private boolean found;

    // this is the item's constructor with takes in parameters x and y
    public Item(int x, int y) {
        startX = x;
        startY = y;
        this.x = startX;
        this.y = startY;
        inRange = false;
        found = false;
        visible = false;
    }

    // this is an abstract method that is filled out by all of its subclasses
    public abstract void draw(Graphics g, int xDiff, int yDiff);

    // all of these methods return or change the item's instance variables
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public void changeX(int x) {
        this.x = x;
    }

    public void changeY(int y) {
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public boolean getInRange() {
        return inRange;
    }

    public boolean isFound() {
        return found;
    }

    public void changeRange(boolean range) {
        inRange = range;
    }

    public void changeFound(boolean found) {
        this.found = found;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
