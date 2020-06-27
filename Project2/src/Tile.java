import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import utilities.BoundingBox;

//source code credit to University of Melbourne SWEN20003 lecturer

public class Tile extends Sprite {
	private static final String GRASS_PATH = "assets/grass.png";
	private static final String WATER_PATH = "assets/water.png";

	
	public static Tile createGrassTile(float x, float y) throws SlickException {
		return new Tile(GRASS_PATH,"grass", x, y);
	}
	public static Tile createWaterTile(float x, float y) throws SlickException {
		return new Tile(WATER_PATH,"water", x, y);
	}

	public Tile() throws SlickException {		
	}
	protected Tile(String imageSrc,String t, float x, float y) throws SlickException {		
		super(imageSrc,t, x, y);
	}

}
