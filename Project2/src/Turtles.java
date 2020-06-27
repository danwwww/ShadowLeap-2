import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import utilities.BoundingBox;

public class Turtles extends Sprite{
	private boolean moveRight;
	private static final int DisappearT=7000;
	private static final int AppearT=2000;
	private static final float Speed=0.085f;
	private static final float tileSizeW=144;
	private static final float tileSizeH=24;
	private int time = 0;
	private boolean isAppear= true;
	private static final String T_PATH = "assets/turtles.png";
	
	//construct the bus with allocated picture
	public Turtles(float x, float y,boolean moveR) throws SlickException {
		this.x=x;
		this.y=y;
		this.setImage(new Image(T_PATH));
		this.setbBox(new BoundingBox(x,y,tileSizeW,tileSizeH,"Turtles"));
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
	public void update(Frog player,int delta) throws SlickException {
		
		this.setTime(this.getTime()+delta);

		if (this.getTime()>=DisappearT && this.getAppear()==true) {
			this.disappear();
			this.setTime(0);
		}
		
		if (this.getTime()>=AppearT && this.getAppear()==false) {
			this.appear();
			this.setTime(0);
		}		
		
		
		//it will move first then update its bounding box
		this.move(Speed*delta);
        //if the x coordiante beyond the screen, it will be reset in the opposite site
		if (x < 0-tileSizeW) {
			x = App.SCREEN_WIDTH;
		}
		
		if (x > App.SCREEN_WIDTH+tileSizeW) {
			x = 0;
		}
		
		if(this.contactSprite(player)==true) 
		{	 if(this.isMoveRight()==true) {
				player.move(delta*Turtles.Speed, 0);	
			}
			else {
				player.move(delta*Turtles.Speed*(-1), 0);
			}
		}
		
		
		
		if (this.getAppear()==true) {
			this.updateBbox();
		}
		
		//if the bus touch the frog, the game will over.The information will be informed and quite the program	
	}
	
	public void disappear() throws SlickException {
		this.isAppear=false;
		this.setbBox(new BoundingBox(0,0,0,0,"Turtles"));
	}
	
	public void appear() throws SlickException {
		this.isAppear=true;
		this.setbBox(new BoundingBox(this.x,this.y,tileSizeW,tileSizeH,"Turtles"));
		
	}
	@Override
	public void render() {
		if(getAppear()==true) {
		getImage().drawCentered(x, y);
		}
	}
	

	public boolean isMoveRight() {
		return moveRight;
	}
	
	public boolean getAppear() {
		return this.isAppear;
	}
	
	public void setAppear(boolean TF) {
		this.isAppear=TF;
		
	}

	public void setMoveRight(boolean moveRight) {
		this.moveRight = moveRight;
	}
	
	public void setTime(int t) {
		this.time=t;
	}
	
	public int getTime() {
		return this.time;
	}
	
}
