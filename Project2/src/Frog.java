import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;


public class Frog extends Sprite{
	private static final String FROG_PATH = "assets/frog.png";
	private int HP;
	//construct the frog with allocated picture
	public Frog() throws SlickException {
		super(FROG_PATH,"frog",World.startX,World.startY);
		this.HP=3;
	}
	
	
	//frog's movement, if the frog move beyond the screen, it will be 'stucked' in the boundary
    public void move(float dx, float dy) {
        x += dx;
        y += dy;
        
		if (x < 0) {
			x = 0;
		}
		if (x > App.SCREEN_WIDTH) {
			x = App.SCREEN_WIDTH;
		}
		if (y < 0) {
			y = 0;
		}
		if (y > App.SCREEN_HEIGHT) {
			y = App.SCREEN_HEIGHT;
		}
    }
    
    
    //frag's update function
	public void update(Input input, int pixels) {
		//if collide:getBbox(),
		//if Bbox=log:move()
		int Px = 0, Py = 0;
		
		//pressed ESC to quit the game
		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			System.exit(0);
		}
		
		//Pressed each direction key, it will move once, the pixels
		//will be set as 48 in the world.java. So each time frag move 1 tile.
		
		if (input.isKeyPressed(Input.KEY_LEFT)) {
			Px -= pixels;
		}
		if (input.isKeyPressed(Input.KEY_RIGHT)) {
			Px += pixels;
		}
		if (input.isKeyPressed(Input.KEY_UP)) {
			Py -= pixels;
		}
		if (input.isKeyPressed(Input.KEY_DOWN)) {
			Py += pixels;
		}
		this.move(Px, Py);
		
		if(this.HP==0) {
			JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
			"Game Over!", "Game Over", JOptionPane.QUESTION_MESSAGE); 
			System.exit(0);		
		}
		
		//if the frag meet the river's boundary, win the game,quit the program
		this.updateBbox();
	}
	public int getHP() {
		return this.HP;
	}
	public void setHP(int x) {
		this.HP=x;
		
	}

	

}
