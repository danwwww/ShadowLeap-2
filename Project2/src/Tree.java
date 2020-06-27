import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

//source code credit to University of Melbourne SWEN20003 lecturer

public class Tree extends Tile {

	private static final String TREE_PATH = "assets/tree.png";
	
	public Tree(float x, float y) throws SlickException {		
		super(TREE_PATH,"tree", x, y);
	}
	
	public boolean hazard(Frog player,Input input) {
		if((this.getbBox().getLeft()>player.getbBox().getLeft()&&this.getbBox().getLeft()<player.getbBox().getRight()
			)||(this.getbBox().getRight()>player.getbBox().getLeft()&&this.getbBox().getRight()<player.getbBox().getRight())) {
			if(player.getbBox().getTop()-this.getbBox().getBottom()<World.gap
					&&player.getbBox().getTop()-this.getbBox().getBottom()>0
					&&input.isKeyPressed(Input.KEY_UP)) {
				return true;
			}	
			}
		return false;		
	}
		
		
	}
	
	

