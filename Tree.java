// this contains the method to draw each tree
// this import statements import the necessary libraries to draw the tree
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.Toolkit;

public class Tree{
	// these are the instance variables of the tree
	private int x;
	private int y;
	private int startX;
	private int startY;
	private int height;
    private BufferedImage treeImage;
	private BufferedImage treeImage2;
	private BufferedImage treeImage3;
	private Color brown;
	private Color darkGreen;
	private int randNum;
	
	// this is the constructor of the tree class that uses the passed x and y 
	public Tree(int x, int y){	
		startX = x;
		startY = y;
		this.x = startX;
		this.y = startY;

		brown = new Color(205,133,63);
		darkGreen = new Color(0,100,0);

		try {
            treeImage = ImageIO.read(new File("treeImage.png"));
			treeImage2 = ImageIO.read(new File("treeImage2.png"));
			treeImage3 = ImageIO.read(new File("treeImage3.png"));
        } catch (IOException e) {
            System.out.println(e);
        }

		// this random number decides what kind of tree the object will be
		randNum = (int)(Math.random()*3)+1;
	}

	// this is the draw method for the tree
	public void drawIt(Graphics g, int xDiff, int yDiff){
		switch (randNum) {
        	case 1:
				g.drawImage(treeImage, x+xDiff, y+yDiff, 64, 64, null);
				height = 64;
				break;
			case 2:				
				g.drawImage(treeImage2, x+xDiff, y+yDiff, 64, 64, null);
				height = 64;
				break;
			default:
				g.drawImage(treeImage3, x+xDiff, y+yDiff, 96, 120, null);
				height = 120;
				break;
		}
	}

	// these methdos return or change the tree's instance variables
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

	public int getHeight() {
		return height;
	}
}