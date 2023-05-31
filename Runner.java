// this class creates a jframe and paints the screen onto the screen
// this import JFrame to create a frame
import javax.swing.JFrame;

// this howlds the runner class
public class Runner 
{
  
	public static void main(String[] args) 
	{
		// this titles the window enchanted quests, the name of the game
		JFrame frame = new JFrame("Enchanted Quests");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Create panel and add it to the frame
		Screen sc = new Screen();
		
		// this sets up the canvas
		frame.add(sc);
		frame.pack();
		frame.setVisible(true);
        sc.animate();
    }
}