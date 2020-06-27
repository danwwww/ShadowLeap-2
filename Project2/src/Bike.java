import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.newdawn.slick.SlickException;

public class Bike extends Sprite{
	private boolean moveRight;
	private static final float Speed=0.2f;
	private static final float xLimitedL=24;
	private static final float xLimitedR=1000;
	private static final String BIKE_PATH = "assets/bike.png";
	//construct the bus with allocated picture
	public Bike(float x, float y,boolean moveR) throws SlickException {
		super(BIKE_PATH,"Bike",x,y);
		this.setMoveRight(moveR);
		
	}
	
	//bus's move function,the parameter is the speed
	public void move(float dx) {
		if(this.getX()<xLimitedL) {
			this.setMoveRight(true);
			
		}
		if(this.getX()>xLimitedR) {
			this.setMoveRight(false);
			
		}
		
		
		if(this.isMoveRight()==true) {
        x += dx;
		}
		else {
			x-=dx;
		}
			
    }
	
	//Update bus
	public void update(Frog player,int delta) {
		//it will move first then update its bounding box
		this.move(Speed*delta);
        //if the x coordiante beyond the screen, it will be reset in the opposite site
		if (x < 0) {
			x = App.SCREEN_WIDTH;
		}
		
		if (x > App.SCREEN_WIDTH) {
			x = 0;
		}
		this.updateBbox();
		
		//if the bus touch the frog, the game will over.The information will be informed and quite the program
		if(this.contactSprite(player)==true) {
			player.setX(World.startX);
			player.setY(World.startY);
			player.setHP(player.getHP()-1);	
		}		
		
	}

	public boolean isMoveRight() {
		return moveRight;
	}

	public void setMoveRight(boolean moveRight) {
		this.moveRight = moveRight;
	}
	

	
	
}
