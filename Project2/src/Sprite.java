import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import utilities.BoundingBox;

public class Sprite {
	//put as protected because its child may easier to access to this value
	 protected float x;
	 protected float y;
	 private Image image;
	 private BoundingBox bBox;
	 
	 public float getX() {
	        return x;
	    }
	 public float getY() {
	        return y;
	   }
	 public void setX(float i) {
	        this.x=i;
	   }
	 public void setY(float i) {
		 	this.y=i;
	   }
	 
	 
	public Sprite(){
		}
	//Constructer  for a sprite, set its coordinate and image
	public Sprite(String imageSrc,String t, float x, float y) throws SlickException {
		this.x=x;
		this.y=y;
		this.setImage(new Image(imageSrc));
		this.setbBox(new BoundingBox(x,y,World.tileSize,World.tileSize,t));
	}
	
	
	
	//Because in this assignment(at least in this stage of the assignment),the update method of bus and frog are quite different,
	//So a general update is not needed. a empty method is enough.
	public void update() {
	}
	
	//draw the spite

	public void render() {
		getImage().drawCentered(x, y);
	}
	
	//if the sprite touch others, return true
	public boolean contactSprite(Sprite other) {
		return this.getbBox().intersects(other.getbBox());
		
	}
	
	//update the location of the bounding box
	public void updateBbox() {
		this.getbBox().setX(x);
		this.getbBox().setY(y);
	}

	
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	public BoundingBox getbBox() {
		return bBox;
	}
	public void setbBox(BoundingBox bBox) {
		this.bBox = bBox;
	}
	
}
