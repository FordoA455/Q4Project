// this cotains the code to draw the game
// all of these import statements import libraries needed to make the game visible and working
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.desktop.QuitEvent;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.Toolkit;

// this class extends JPanel and uses KeyListener and ActionListener to draw and detect button and key inputs
public class Screen extends JPanel implements KeyListener, ActionListener{
    // this is the instance varaibels for the screen class
	private Player p1;
	private Tree t1;
	private Tree t2;
    private NPC n1;
    private int xDiff;
    private int yDiff;
    private int currentStateIndex;
    private int treasuresFound;
    private int indexCounter;
    private String direction;
    private String chatMessage;
    private String chatMessage2;
    private String[] gameStates = new String[]{"opening", "prolouge", "item1", "item2", "item3"};
    private String currentGameState;
    private JLabel openingText;
    private JLabel openingText2;
    private JLabel chatText;
    private JLabel chatText2;
    private JButton startButton;
    private JButton quitButton;
    private ArrayList<Item> groundItems;
    private ArrayList<Item> inventory;
    private ArrayList<NPC> npcs;
    private ArrayList<Tree> trees;
    private ArrayList<Tree> sortedTrees;
    private Image coinIcon;
    private Image swordIcon;
    private Image potionIcon;
    private BufferedImage title;
    private boolean rightKeyPressed;
    private boolean leftKeyPressed;
    private boolean upKeyPressed;
    private boolean downKeyPressed;
    private boolean[] itemsFound;
	
    // this is the constructor for the screen class which intializes everything and sets up the screen
	public Screen(){
		setLayout(null);
        npcs = new ArrayList<NPC>();
        trees = new ArrayList<Tree>();
        groundItems = new ArrayList<Item>();
        inventory = new ArrayList<Item>();
		//sets up the instance variables		
		p1 = new Player(1000,0);
        
		trees.add(new Tree(1000,150));
		trees.add(new Tree(1000,0));
        for (int i = 0; i < 50; i++) {
            trees.add(new Tree((int)(Math.random()*1921), (int)(Math.random()*1081)));
        }

        for (int i = 0; i < trees.size()-1; i++) {
            int minIndex = i;
            for (int j = i+1; j < trees.size(); j++) {
                if (trees.get(j).getStartY()+ trees.get(j).getHeight() <= trees.get(minIndex).getStartY() + trees.get(minIndex).getHeight()) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                Tree temp = trees.get(i);
                trees.set(i, trees.get(minIndex));
                trees.set(minIndex, temp);
            }
        }
        groundItems.add(new Coin((int)(Math.random()*1921), (int)(Math.random()*1081)));
        groundItems.add(new Sword((int)(Math.random()*1921), (int)(Math.random()*1081)));
        groundItems.add(new Potion((int)(Math.random()*1921), (int)(Math.random()*1081)));
        for (Tree tree : trees) {
            tree.changeX(1000);
            tree.changeY(1000);
        }
        for (Item item : groundItems) {
            item.changeX(1000);
            item.changeY(1000);
        }
        npcs.add(new NPC(600, 600, 1, groundItems.get(0), "item1"));
        npcs.add(new NPC(0, 0, 2, groundItems.get(1), "item2"));
        npcs.add(new NPC(1000, 1000, 3, groundItems.get(2), "item3"));
        for (NPC npc : npcs) {
            npc.changeX(1000);
            npc.changeY(1000);
        }
        try {
            title = ImageIO.read(new File("title.png"));
        } catch (IOException e) {
            System.out.println(e);
        }
        coinIcon = Toolkit.getDefaultToolkit().createImage("Bitcoin.gif");
        swordIcon = Toolkit.getDefaultToolkit().createImage("SWORD.gif");
        potionIcon = Toolkit.getDefaultToolkit().createImage("POTION.gif");
		xDiff = 0;
        yDiff = 0;
        currentStateIndex = 0;
        treasuresFound = 0;
        indexCounter = 0;
		direction = "straight";
        currentGameState = "opening";
        chatMessage = "";
        chatMessage2 = "";
        rightKeyPressed = false;
        leftKeyPressed = false;
        upKeyPressed = false;
        downKeyPressed = false;
        itemsFound = new boolean[]{false, false, false};
        openingText = new JLabel("Welcome to Enchanted Quest in this game, you have to go around and talk to npcs");
        openingText2 = new JLabel("to complete quests and find items.\n Use the arrow keys to move and space to interact with items"); 
        chatText = new JLabel("");
        chatText2 = new JLabel("");
        startButton = new JButton("START");
        quitButton = new JButton("QUIT");
        startButton.setBounds(150, 300, 300, 30);
        startButton.addActionListener(this);
        quitButton.setBounds(150, 340, 300, 30);
        quitButton.addActionListener(this);
        openingText.setBounds(30,150,650,30);
        openingText2.setBounds(30,185,650,30);
        chatText.setBounds(5, 600, 590, 30);
        chatText2.setBounds(5, 635, 590, 30);
        openingText.setForeground(Color.WHITE);
        openingText2.setForeground(Color.WHITE);
        chatText.setForeground(Color.BLACK);
        chatText2.setForeground(Color.black);
        addKeyListener(this);
        add(startButton);
        add(quitButton);
        add(openingText);
        add(openingText2);
        add(chatText);
        add(chatText2);
		//sets keylistener
		setFocusable(true);
	}

    // this overrides the JPanel method to set the screen of 600 by 800
	@Override
	public Dimension getPreferredSize() {
		//Sets the size of the panel
        return new Dimension(600,800);
	}
	
    // this method paints all of the aspects of the game
	@Override
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
	

		//draws background		
        drawBackground(g);
		//Draw the game objects
		for (Tree tree : trees) {
            tree.drawIt(g, xDiff, yDiff);
        }
        for (NPC npc : npcs) {
            npc.draw(g, xDiff, yDiff);
        }

        for (Item item : groundItems) {
            item.draw(g, xDiff, yDiff);
        }
        drawBoundaries(g, xDiff, yDiff);
        drawInventoryBox(g);
		p1.drawIt(g, direction);
        drawEndScreen(g);
        drawTextbox(g, chatMessage, chatMessage2);
	} 

    // this draws the background
    public void drawBackground(Graphics g) {
        if (currentGameState.equals("opening")) {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, 600, 600);
            g.drawImage(title, 120, 0, 360, 360, null);
        } else {
            g.setColor(new Color(56, 183, 100));
            g.fillRect(0, 0, 1920, 1080);
        }
    }

    // this method draws the end screen
    public void drawEndScreen(Graphics g) {
        boolean allItemsFound = true;
        for (boolean state : itemsFound) {
            if (state == false) {
                allItemsFound = false;
            }
        }
        if (allItemsFound) {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, 600, 600);
            openingText.setVisible(true);
            openingText2.setVisible(true);
            openingText.setText("You win");
            openingText2.setText("");
        }
    }

    // this method draws the inventory
    public void drawInventoryBox(Graphics g) {
        if (!currentGameState.equals("opening")) {
            g.setColor(new Color(13, 13, 13, 75));
            g.fillRect(200, 530, 200, 70);
            g.setColor(new Color(255, 255, 255, 75));
            g.fillRect(215, 540, 50, 50);
            g.fillRect(275, 540, 50, 50);
            g.fillRect(335, 540, 50, 50);
            for (int i = 0; i < inventory.size(); i++) {
                if (inventory.get(i).getName().equals("Coin")) {
                    g.drawImage(coinIcon, (i*59)+206, 532, 70, 70, null);
                } else if (inventory.get(i).getName().equals("Sword")) {
                    g.drawImage(swordIcon, (i * 59) + 206, 532, 70, 70, null);
                } else {
                    g.drawImage(potionIcon, (i * 59) + 206, 532, 70, 70, null);
                }
            }
        }
    }

    // this method draws the dialouge box at the bottom
    public void drawTextbox(Graphics g, String message, String message2) {
        if (currentGameState.equals("opening") || currentGameState.equals("item3")) {
            g.setColor(Color.BLACK);
            g.fillRect(0, 600, 600, 200);
            chatText.setText("");
            chatText2.setText("");
        } else {
            g.setColor(Color.WHITE);
            g.fillRect(0, 600, 600, 200);
            chatText.setText(message);
            chatText2.setText(message2);
        }
    }

    // this dras the boundaries of the world
    public void drawBoundaries(Graphics g, int xDiff, int yDiff) {
        g.setColor(Color.BLACK);
        g.fillRect(-1000+xDiff, -600+yDiff, 4000, 500);
        g.fillRect(-600+xDiff, -1000+yDiff, 500, 4000);
        g.fillRect(1980+xDiff, -1000+yDiff, 500, 4000);
        g.fillRect(-1000+xDiff, 1200+yDiff, 4000, 500);
    }

	//implement methods of the KeyListener
	public void keyPressed(KeyEvent e) {
		// Key codes: 37 left, 38 up, 39 right, 40 down

		// System.out.println( "key code: " + e.getKeyCode()  );
		if (e.getKeyCode() == 39) {
            if (!currentGameState.equals("opening")) {
                direction = "right";
                p1.changeState("walk");
                rightKeyPressed = true;
                leftKeyPressed = false;
                downKeyPressed = false;
                upKeyPressed = false;
            }
        } else if (e.getKeyCode() == 37) {
            if (!currentGameState.equals("opening")) {
                direction = "left";
                p1.changeState("walk");
                leftKeyPressed = true;
                rightKeyPressed = false;
                downKeyPressed = false;
                upKeyPressed = false;
            }
        } else if (e.getKeyCode() == 38) {
            if (!currentGameState.equals("opening")) {
                direction = "up";
                p1.changeState("walk");
                upKeyPressed = true;
                rightKeyPressed = false;
                downKeyPressed = false;
                leftKeyPressed = false;
            }
        } else if (e.getKeyCode() == 40) {
            if (!currentGameState.equals("opening")) {
                direction = "down";
                p1.changeState("walk");
                downKeyPressed = true;
                rightKeyPressed = false;
                upKeyPressed = false;
                leftKeyPressed = false;
            }
        } else if (e.getKeyCode() == 32) {
            if (!currentGameState.equals("opening")) {
                for (NPC npc : npcs) {
                    if (npc.getInRange()) {
                        if (npc.getRelatedItem().isFound()) {
                            currentStateIndex++;
                            itemsFound[indexCounter] = true;
                            indexCounter++;
                            npc.getRelatedItem().changeFound(false);
                        }
                        if (currentStateIndex == npc.getWantedIndex()) {
                            chatMessage = npc.getName() + ": "+ npc.getMessage();
                            chatMessage2 = npc.getMessage2();
                            npc.getRelatedItem().setVisible(true);
                        } else if (currentStateIndex > npc.getWantedIndex()) {
                            inventory.remove(npc.getRelatedItem());
                            chatMessage = npc.getName() + ": "+ npc.getEndMessage();
                            chatMessage2 = "";
                        } else {
                            chatMessage = npc.getName() + ": "+ npc.getPreMessage();
                            chatMessage2 = npc.getPreMessage2();
                        }
                    }
                }
                for (Item item : groundItems) {
                    if (item.getInRange()) {
                        chatMessage = "Found "+item.getName();
                        chatMessage2 = "";
                        inventory.add(item);
                        item.changeFound(true);
                        // currentStateIndex++;
                        treasuresFound++;
                    }
                }
            }
        } else if (e.getKeyCode() == 79) {
            if (indexCounter < 3) {
                itemsFound[indexCounter] = true;
                npcs.get(indexCounter).getRelatedItem().setVisible(false);
                if (inventory.size() > 0 && inventory.get(0) == npcs.get(indexCounter).getRelatedItem()) {
                    inventory.remove(0);
                }
                indexCounter++;
                currentStateIndex++;
            }
        }
		// repaint();	
	}

	public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == 39) {
            rightKeyPressed = false;
            p1.changeState("idle");
        } else if (e.getKeyCode() == 37) {
            leftKeyPressed = false;
            p1.changeState("idle");
        } else if (e.getKeyCode() == 38) {
            upKeyPressed = false;
            p1.changeState("idle");
        } else if (e.getKeyCode() == 40) {
            downKeyPressed = false;
            p1.changeState("idle");
        }
    }
	public void keyTyped(KeyEvent e) {}

    // this detects button presses for the start menu
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            currentStateIndex++;
            currentGameState = gameStates[currentStateIndex%gameStates.length];
            startButton.setVisible(false);
            openingText.setVisible(false);
            openingText2.setVisible(false);
            quitButton.setVisible(false);
            p1.setX(300);
            p1.setY(300);
            for (Tree tree : trees) {
                tree.changeX(tree.getStartX());
                tree.changeY(tree.getStartY());
            }
            for (Item item : groundItems) {
                item.changeX(item.getStartX());
                item.changeY(item.getStartY());
            }

            for (NPC npc: npcs) {
                npc.changeX(npc.getStartX());
                npc.changeY(npc.getStartY());
            }
        } else if (e.getSource() == quitButton) {
            System.out.println("Quit game... Play again soon!");
            System.exit(0);
        }
    }

    // this method animates the game
    public void animate() {
        while (true) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException ex) {
                // Thread.currentThread().interrupt();
            }
            if (rightKeyPressed) {
                xDiff -= 1;
            }  
            else if (leftKeyPressed) {
                xDiff += 1;
            }  
            else if (upKeyPressed) {
                yDiff += 1;
            }  
            else if (downKeyPressed) {
                yDiff -= 1;
            }
            for (NPC npc : npcs) {
                p1.checkNPCCollision(npc, xDiff, yDiff);
            }
            for (Item item : groundItems) {
                p1.checkItemCollision(item, xDiff, yDiff);
                if (item.isFound()) {
                    item.changeX(2000);
                    item.changeY(2000);
                }
            }

            if (yDiff >= 400) {
                yDiff -= 1;
            }
            
            if (yDiff <= -800) {
                yDiff += 1;
            }

            if (xDiff >= 400) {
                xDiff -= 1;
            }

            if (xDiff <= -1620) {
                xDiff += 1;
            }

            currentGameState = gameStates[currentStateIndex%gameStates.length];
            System.out.println(indexCounter);
            repaint();
        }
    }
}