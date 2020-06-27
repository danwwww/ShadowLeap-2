import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import utilities.BoundingBox;

public class Log extends Sprite{
	private boolean moveRight;
	private static final float Speed=0.1f;
	private static final float tileSizeW=132;
	private static final float tileSizeH=48;
	private static final String LOG_PATH = "assets/log.png";
	//construct the bus with allocated picture
	
	public Log(float x, float y,boolean moveR) throws SlickException {
		this.x=x;
		this.y=y;
		this.setImage(new Image(LOG_PATH));
		this.setbBox(new BoundingBox(x,y,tileSizeW,tileSizeH,"Log"));
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
	public void update(Extra_life extra,Frog player,int delta) {
		//it will move first then update its bounding box
		this.move(Speed*delta);
		
        //if the x coordiante beyond the screen, it will be reset in the opposite site
		if (x < 0-tileSizeW) {
			float difference=extra.getX()-this.x;
			x = App.SCREEN_WIDTH;
			if(this.contactSprite(extra)==true) {
				extra.setX(App.SCREEN_WIDTH+difference);
			} 	
			
		}
		
		if (x > App.SCREEN_WIDTH+tileSizeW) {
			float difference=extra.getX()-this.x;
			x = 0;
			if(this.contactSprite(extra)==true) {
				extra.setX(0+difference);
			} 	
		}
		this.updateBbox();
		if(this.contactSprite(player)==true) 
		{	 if(this.isMoveRight()==true) {
				player.move(delta*Log.Speed, 0);	
			}
			else {
				player.move(delta*Log.Speed*(-1), 0);
			}
		}
		
		if(this.contactSprite(extra)==true) 
		{	 if(this.isMoveRight()==true) {
			extra.move2(delta*Log.Speed);	
			}
			else {
				extra.move2(delta*Log.Speed*(-1));
			}
		}
		
		
		this.updateBbox();

		
	}

	public boolean isMoveRight() {
		return moveRight;
	}

	public void setMoveRight(boolean moveRight) {
		this.moveRight = moveRight;
	}
	public float getX() {
		return this.x;
	}
	public float getY() {
		return this.y;
	}
	
	
	
}
