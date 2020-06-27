import org.newdawn.slick.SlickException;

import utilities.BoundingBox;


public class Extra_life extends Sprite{
	private static final int DisappearT=14000;
	private static final int moveT=2000;
	private int time = 0;
	private int timeM = 0;
	private int count=0;
	private boolean move1=false;
	private boolean move2=false;
	private boolean move3=false;
	private boolean move4=false;
	private boolean move5=false;
	private boolean move6=false;
	private boolean isAppear= false;
	private String log;
	private static final String ExLife_PATH = "assets/extralife.png";
	//construct the frog with allocated picture
	public Extra_life(float x, float y) throws SlickException {
		super(ExLife_PATH,"extralife",x,y);
	}
	
	
	//frog's movement, if the frog move beyond the screen, it will be 'stucked' in the boundary
    public void move() {
    	if(this.timeM>2000) {
    		if(this.log=="log") {
	    		if(move1==false) {
	    			move1=true;
		    		x += World.tileSize;
		    		this.timeM=0;
	    		}
	    		else if(move2==false) {
	    			move2=true;
		    		x -= World.tileSize;
		    		this.timeM=0;
	    		}
	    		else if(move3==false) {
	    			move3=true;
		    		x -= World.tileSize;
		    		this.timeM=0;
	    		}
	    		else if(move4==false) {
	    			move4=true;
		    		x += World.tileSize;
		    		this.timeM=0;
	    		}
	    		else if(move5==false) {
	    			move5=true;
		    		x += World.tileSize;
		    		this.timeM=0;
	    		}
	    		else if(move6==false) {
	    			move6=true;
		    		x -= World.tileSize;
		    		this.timeM=0;
	    		}
    		}
    		else {
	    		if(move1==false) {
	    			move1=true;
		    		x += World.tileSize;
		    		this.timeM=0;
	    		}
	    		else if(move2==false) {
	    			move2=true;
		    		x += World.tileSize;
		    		this.timeM=0;
	    		}
	    		else if(move3==false) {
	    			move3=true;
		    		x -= World.tileSize;
		    		this.timeM=0;
	    		}
	    		else if(move4==false) {
	    			move4=true;
		    		x -= World.tileSize;
		    		this.timeM=0;
	    		}
	    		else if(move5==false) {
	    			move5=true;
		    		x -= World.tileSize;
		    		this.timeM=0;
	    		}
	    		else if(move6==false) {
	    			move6=true;
		    		x -= World.tileSize;
		    		this.timeM=0;
	    		}	
    			
    		}
    		
    	}
    }
    
	public void move2(float dx) {
		x+=dx;
			
    }
    //frag's update function
	public void update(Frog player,int delta) throws SlickException {
		if(this.contactSprite(player)==true) {
			this.disappear();
			player.setHP(player.getHP()+1);
		}
		
		
		if (this.getAppear()==true) {
			this.updateBbox();
			this.time+=delta;
			this.timeM+=delta;
			if(this.time>=DisappearT) {
				this.disappear();
			}
			this.move();
		}

	}
	
	public void disappear() throws SlickException {
		this.isAppear=false;
		this.setbBox(new BoundingBox(0,0,0,0,"extralife"));
	}
	
	public void appear() throws SlickException {
		this.time=0;
		this.timeM=0;
		this.isAppear=true;
		this.setbBox(new BoundingBox(this.x,this.y,World.tileSize,World.tileSize,"extralife"));
		this.resetMove();
	}
	@Override
	public void render() {
		if(getAppear()==true) {
			getImage().drawCentered(x, y);
		}
	}
	
	public boolean getAppear() {
		return this.isAppear;
	}
	
	public void setAppear(boolean TF) {
		this.isAppear=TF;
		
	}
	
	public String getLog() {
		return this.log;
	}
	
	public void setLog(String TF) {
		this.log=TF;
		
	}
	public void resetMove() {
		this.move1=false;
		this.move2=false;
		this.move3=false;
		this.move4=false;
		this.move5=false;
		this.move6=false;
		
	}
	

}
