import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import utilities.BoundingBox;

public class bulldozer extends Sprite{
	private boolean moveRight;
	private static final float Speed=0.05f;
	private BoundingBox bBox2;
	private static final String Bulldozer_PATH = "assets/bulldozer.png";
	//construct the bus with allocated picture
	public bulldozer(float x, float y,boolean moveR) throws SlickException {
		super(Bulldozer_PATH,"bulldozer",x,y);
		this.setMoveRight(moveR);
		this.setbBox2(new BoundingBox(x,y,(World.tileSize)*3-1,World.tileSize,"bulldozer"));
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
	public void update(int delta,Frog player) {
		if(this.contactSprite(player)==true &&this.getX()<player.getX()) 
		{	 
				player.move(delta*this.getSpeed(), 0);
				if(player.getX()==App.SCREEN_WIDTH) {
							player.setX(World.startX);
							player.setY(World.startY);
							player.setHP(player.getHP()-1);
				}		
				
		}
		else if(this.contactSprite(player)==true &&this.getX()>player.getX()) {
				player.move(delta*this.getSpeed()*(-1), 0);
				if(player.getX()==0) {
					player.setX(World.startX);
					player.setY(World.startY);
					player.setHP(player.getHP()-1);
				}
			
			}
		
		
		
		
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
		this.updateBbox2();
		
	}
	
	public boolean hazard(Frog player,Input input) {
		
		if(this.contactSprite2(player)==true&&this.getbBox().getTop()==player.getbBox().getTop()) {
			if (this.getX()<player.getX()) {
				if(input.isKeyPressed(Input.KEY_LEFT)) {
					return true;
				}
			}
			else if(this.getX()>player.getX()) {
				if(input.isKeyPressed(Input.KEY_RIGHT)) {
					return true;
				}			
			}
		}
	
//abandon frog move up or down to crash a bulldozer
	
		if(this.getbBox2().getLeft()<player.getbBox().getLeft()&&this.getbBox2().getRight()>player.getbBox().getRight()
				) {
			if(player.getbBox().getTop()-this.getbBox2().getBottom()<World.gap
					&&player.getbBox().getTop()-this.getbBox2().getBottom()>0
					&&input.isKeyPressed(Input.KEY_UP)) {
				return true;
			}	
			else if(this.getbBox2().getTop()-player.getbBox().getBottom()<World.gap
					&&this.getbBox2().getTop()-player.getbBox().getBottom()>0
					&&input.isKeyPressed(Input.KEY_DOWN)) {
				return true;
			}		
		}
		return false;
		
		
		
		
	}
	
	
	public float getSpeed() {
		return bulldozer.Speed;
	}
	
	public boolean isMoveRight() {
		return moveRight;
	}

	public void setMoveRight(boolean moveRight) {
		this.moveRight = moveRight;
	}
	
	public void ReverseMoveRight() {
		if (this.isMoveRight() == true) {
			this.setMoveRight(false);
		}
		else {
			this.setMoveRight(true);
			
		}
	}
	public boolean contactSprite2(Sprite other) {
		return this.getbBox2().intersects(other.getbBox());
		
	}

	public void updateBbox2() {
		this.getbBox2().setX(x);
		this.getbBox2().setY(y);
	}

	public BoundingBox getbBox2() {
		return bBox2;
	}

	public void setbBox2(BoundingBox bBox2) {
		this.bBox2 = bBox2;
	}
	
	
}
