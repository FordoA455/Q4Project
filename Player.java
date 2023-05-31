// this class contains the code for the player class
// this contains the method to draw the player
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Player
{
    // these are the player's instance variables
	private int x;
	private int y;
	private int size;
	private Color pColor;
	private String actionState;
    private Image idleDown;
    private Image walkDown;
    private Image idleUp;
    private Image walkUp;
    private Image idleLeft;
    private Image walkLeft;
    private Image idleRight;
    private Image walkRight;

    // this is the constructor method of the player
	public Player(int x, int y)
	{	
		this.x = x;
		this.y = y;
		pColor = new Color(255, 255, 255);
		
		this.size=80;	 //width and height will be the same
        actionState = "idle";
        idleDown = Toolkit.getDefaultToolkit().getImage("playerIdleDown.gif");
        idleUp = Toolkit.getDefaultToolkit().getImage("playerIdleUp.gif");
        idleLeft = Toolkit.getDefaultToolkit().getImage("playerIdleLeft.gif");
        idleRight = Toolkit.getDefaultToolkit().getImage("playerIdleRight.gif");
        walkDown = Toolkit.getDefaultToolkit().getImage("playerWalkDown.gif");
        walkUp = Toolkit.getDefaultToolkit().getImage("playerWalkUp.gif");
        walkLeft = Toolkit.getDefaultToolkit().getImage("playerWalkLeft.gif");
        walkRight = Toolkit.getDefaultToolkit().getImage("playerWalkRight.gif");
	}

    // this draws the player depending on state
	public void drawIt(Graphics g, String direction)
	{
		// draw the body of the snowman
		g.setColor(pColor);
		
		// draw the eyes
        if (direction.equals("straight")) {
           g.drawImage(idleDown, x, y, 64, 64, null);
        } else if (direction.equals("right")) {
            if (actionState.equals("idle")) {
                g.drawImage(idleRight, x, y, 64, 64, null);
            } else if (actionState.equals("walk")) {
                g.drawImage(walkRight, x, y, 64, 64, null);
            }
        } else if (direction.equals("left")) {
            if (actionState.equals("idle")) {
                g.drawImage(idleLeft, x, y, 64, 64, null);
            } else if (actionState.equals("walk")) {
                g.drawImage(walkLeft, x, y, 64, 64, null);
            }
        } else if (direction.equals("up")) {
            if (actionState.equals("idle")) {
                g.drawImage(idleUp, x, y, 64, 64, null);
            } else if (actionState.equals("walk")) {
                g.drawImage(walkUp, x, y, 64, 64, null);
            }
        } else if (direction.equals("down")) {
            if (actionState.equals("idle")) {
                g.drawImage(idleDown, x, y, 64, 64, null);
            } else if (actionState.equals("walk")) {
                g.drawImage(walkDown, x, y, 64, 64, null);
            }
        }
	}

    // these methods check the collision of npcs and items
    public void checkNPCCollision(NPC npc, int xDiff, int yDiff) {
        if ((x+50 >= npc.getX()+20+xDiff && x <= npc.getX()+xDiff+40) && (y+50 >= npc.getY()+yDiff && y <= npc.getY()+yDiff+50)) {
            npc.changeRange(true);
        } else {
            npc.changeRange(false);
        }
    }

    public void checkItemCollision(Item item, int xDiff, int yDiff) {
        if ((x+50 >= item.getX()+20+xDiff && x <= item.getX()+xDiff+40) && (y+50 >= item.getY()+yDiff && y <= item.getY()+yDiff+50)) {
            item.changeRange(true);
        } else {
            item.changeRange(false);
        }
    }

    // these are methods that return and change x and y
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getState() {
        return actionState;
    }

    public void changeState(String state) {
        actionState = state;
    }
}