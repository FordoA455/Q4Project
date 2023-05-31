// this class contains the code for the NPC class
// these import the libraries to draw the npc
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class NPC {
    // these are the npc's instance variables
    private int x;
    private int y;
    private int startX;
	private int startY;
    private int wantedStateIndex;
    private Image npc;
    private Image npc2;
    private Image npc3;
    private boolean inRange;
    private boolean alreadyTalkedTo;
    private String preMessage;
    private String textMessage;
    private String endMessage;
    private String preMessage2;
    private String textMessage2;
    private String wantedState;
    private String name;
    private Item linkedItem;

    // this constructs the npc with passed in x, y, index, item, and state values
    public NPC(int x, int y, int wantedIndex, Item item, String state) {
        startX = x;
        startY = y;
        wantedStateIndex = wantedIndex;
        this.x = startX;
        this.y = startX;
        npc = Toolkit.getDefaultToolkit().createImage("npcImage.gif");
        npc2 = Toolkit.getDefaultToolkit().createImage("npcImage2.gif");
        npc3 = Toolkit.getDefaultToolkit().createImage("npcImage3.gif");
        inRange = false;
        alreadyTalkedTo = false;
        if (item.getName().equals("Coin")) {
            preMessage = "";
            textMessage = "Hello! To get the "+item.getName()+",";
            textMessage2 = "look around and find it you lazy bum";
            endMessage = "Thanks for finding the "+item.getName()+". Talk to the weaponsmith for the next quest.";
            name = "Farmer";
        } else if (item.getName().equals("Sword")) {
            preMessage = "Sorry, I am a little bit preoccupied with my work.";
            preMessage2 = "Talk to me after you finish the farmer's quest.";
            textMessage = "I seem to have misplaced my sword somewhere.";
            textMessage2 = "Can you find it for me?";
            endMessage = "Thanks for finding the "+item.getName()+". Talk to the mage for the next quest.";
            name = "Weaponsmith";
        } else {
            preMessage = "Sorry, I am busy brewing potions. Talk to me after you";
            preMessage2 = "finish the farmer's and the weaponsmith's quest.";
            textMessage = "I was brewing some potions but I may have";
            textMessage2 = "dropped one on the floor. Can you find it?";
            endMessage = "Thanks for finding the "+item.getName();
            name = "Mage";
        }
        linkedItem = item;
        wantedState = state;
    }

    // this draws the npc
    public void draw(Graphics g, int xDiff, int yDiff) {
        if (linkedItem.getName().equals("Coin")) {
            g.drawImage(npc, x+xDiff, y+yDiff, 64, 64, null);
        } else if (linkedItem.getName().equals("Sword")) {
            g.drawImage(npc2, x+xDiff, y+yDiff, 64, 64, null);
        } else {
            g.drawImage(npc3, x+xDiff, y+yDiff, 64, 64, null);
        }
    }

    // these methods are methods used to either change or return the npc's instance variables
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

    public int getWantedIndex() {
        return wantedStateIndex;
    }

    public void changeX(int x) {
		this.x = x;
	}
	
	public void changeY(int y) {
		this.y = y;
	}

    public boolean getInRange() {
        return inRange;
    }

    public boolean getTalkedTo() {
        return alreadyTalkedTo;
    }

    public String getPreMessage() {
        return preMessage;
    }
    public String getMessage() {
        return textMessage;
    }
    
    public String getEndMessage() {
        return endMessage;
    }

    public String getPreMessage2() {
        return preMessage2;
    }
    public String getMessage2() {
        return textMessage2;
    }

    public String getPromptedState() {
        return wantedState;
    }

    public String getName() {
        return name;
    }

    public void changeRange(boolean inRange) {
        this.inRange = inRange;
    }

    public void changeTalkedTo(boolean talked) {
        alreadyTalkedTo = talked;
    }

    public Item getRelatedItem() {
        return linkedItem;
    }
}
