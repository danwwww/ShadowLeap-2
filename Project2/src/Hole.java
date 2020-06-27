import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import utilities.BoundingBox;

//source code credit to University of Melbourne SWEN20003 lecturer

public class Hole extends Tile {
	private static final String FROG_PATH = "assets/frog.png";
	private static final float tileSizeW=96;
	private static final float tileSizeH=48;
	private boolean isFull= false;

	
	public Hole(float x, float y) throws SlickException {		
		this.x=x;
		this.y=y;
		this.setImage(new Image(FROG_PATH));
		this.setbBox(new BoundingBox(x,y,tileSizeW,tileSizeH,"Holes"));
	}
	
	
	public void update(Frog player) throws SlickException {
		
		if(this.contactSprite(player)==true) 
		{	 if(this.isFull==false) {
				this.isFull=true;
				player.setX(World.startX);
				player.setY(World.startY);
			}
			else {
				player.setX(World.startX);
				player.setY(World.startY);
				player.setHP(player.getHP()-1);
			}
		}
		
		
	}
	
	
	public void setFull(boolean TF) {
		this.isFull=TF;
		
	}
	public boolean isFull() {
		return this.isFull;
	}
	
	
	@Override
	public void render() {
		if(isFull==true) {
		getImage().drawCentered(x+World.tileSize/2, y);
		}
	}
		
		
	}
	
	

