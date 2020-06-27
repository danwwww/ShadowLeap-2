import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.newdawn.slick.SlickException;

public class Bus extends Sprite{
	private boolean moveRight;
	private static final float Speed=0.15f;
	//construct the bus with allocated picture
	private static final String BUS_PATH = "assets/bus.png";
	public Bus(float x, float y,boolean moveR) throws SlickException {
		super(BUS_PATH,"Bus",x,y);
		this.setMoveRight(moveR);
		
	}
	
	//bus's move function,the parameter is the speed
	public void move(float dx) {
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
		if (x < 0-World.tileSize) {
			x = App.SCREEN_WIDTH;
		}
		
		if (x > App.SCREEN_WIDTH+World.tileSize) {
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
