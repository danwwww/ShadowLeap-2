import java.util.ArrayList;
import java.util.Random;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class World {
	private Image life= new Image("assets/lives.png");
	private Extra_life extra;
	private Frog player;
	private boolean isfinish=false;
	private int holesFill=5;
	public static final int lifeX=12;
	public static final int lifeY=750;
	public static final int tileSize=48;     //tile's size
	public static final int gap=3;
	public static final int startX=512;
	public static final int startY=720;
	private static final int HOLE_X =  96;
	private static final int HOLE_Y =  48;
	private static final int OFFSET =  192;
	int Rtime;
	private int time = 0;
	private ArrayList<Bus> buses = new ArrayList<Bus>();
	private ArrayList<Race_car> racecars = new ArrayList<Race_car>();
	private ArrayList<Bike> bikes = new ArrayList<Bike>();
	private ArrayList<Log> logs = new ArrayList<Log>();
	private ArrayList<Turtles> turtles = new ArrayList<Turtles>();
	private ArrayList<Long_log> longlogs = new ArrayList<Long_log>();
	private ArrayList<bulldozer> bulldozer = new ArrayList<bulldozer>();
	private ArrayList<Tile> grass = new ArrayList<Tile>();
	private ArrayList<Tile> water = new ArrayList<Tile>();
	private ArrayList<Tree> trees = new ArrayList<Tree>();
	private ArrayList<Hole> holes = new ArrayList<Hole>();
	
		
	
	// Perform initialisation logic
	public World(String lvl) throws SlickException, IOException{
		this.Rtime = new Random(System.currentTimeMillis()).nextInt(10)+25;
		this.Rtime=Rtime*1000;
		//import the image of grass and water and initialise the player 
		setPlayer(new Frog());
		extra =new Extra_life(0,0);
		
        BufferedReader reader = new BufferedReader(new FileReader(lvl));
        String line = null;  
        while((line=reader.readLine())!=null){  
            String item[] = line.split(",");
            int Xcoordinate = Integer.parseInt(item[1]);
            int Ycoordinate =Integer.parseInt(item[2]);
            
            
            if(item[0].equals("water")) {
            	water.add(Tile.createWaterTile(Xcoordinate, Ycoordinate));        	
            }
            else if(item[0].equals("tree")) {
            	trees.add(new Tree(Xcoordinate, Ycoordinate));        	
            }
            else if(item[0].equals("grass")) {
            	grass.add(Tile.createGrassTile(Xcoordinate, Ycoordinate));        	
            }

            else if(item[0].equals("bike")) {
            	if(item[3].equals("true")) {
            	bikes.add(new Bike(Xcoordinate, Ycoordinate,true));}
            	else bikes.add(new Bike(Xcoordinate, Ycoordinate,false));
            }
            else if(item[0].equals("bike")) {
            	if(item[3].equals("true")) {
            	bikes.add(new Bike(Xcoordinate, Ycoordinate,true));}
            	else bikes.add(new Bike(Xcoordinate, Ycoordinate,false));
            }
            else if(item[0].equals("bulldozer")) {
            	if(item[3].equals("true")) {
            		bulldozer.add(new bulldozer(Xcoordinate, Ycoordinate,true));}
            	else bulldozer.add(new bulldozer(Xcoordinate, Ycoordinate,false));
            }
            else if(item[0].equals("racecar")) {
            	if(item[3].equals("true")) {
            		racecars.add(new Race_car(Xcoordinate, Ycoordinate,true));}
            	else racecars.add(new Race_car(Xcoordinate, Ycoordinate,false));
            }
            else if(item[0].equals("bus")) {
            	if(item[3].equals("true")) {
            		buses.add(new Bus(Xcoordinate, Ycoordinate,true));}
            	else buses.add(new Bus(Xcoordinate, Ycoordinate,false));
            }
            
            if(item[0].equals("turtle")) {
            	if(item[3].equals("true")) {
            		turtles.add(new Turtles(Xcoordinate, Ycoordinate,true));}
            	else turtles.add(new Turtles(Xcoordinate, Ycoordinate,false));
            }
            if(item[0].equals("log")) {
            	if(item[3].equals("true")) {
            		logs.add(new Log(Xcoordinate, Ycoordinate,true));}
            	else logs.add(new Log(Xcoordinate, Ycoordinate,false));
            }
            if(item[0].equals("longLog")) {
            	if(item[3].equals("true")) {
            		longlogs.add(new Long_log(Xcoordinate, Ycoordinate,true));}
            	else longlogs.add(new Long_log(Xcoordinate, Ycoordinate,false));
            }
            
            
            

        }
        //create holes
		for (int x = HOLE_X; x < App.SCREEN_WIDTH - tileSize; x+=OFFSET) {
			holes.add(new Hole(x,HOLE_Y));

		}
            
	}
	
		
		
		
	
	public void update(Input input, int delta) throws SlickException {
		boolean fallInWater =true;
		

		for(int i =0;i<bulldozer.size();i++) {
			if (bulldozer.get(i).hazard(getPlayer(), input)==true) {
				return;
			}
			bulldozer.get(i).update(delta,getPlayer());
		}
		
		


		for(int i =0;i<bikes.size();i++) {
			bikes.get(i).update(getPlayer(),delta);
		}
		
		for(int i =0;i<buses.size();i++) {
			buses.get(i).update(getPlayer(),delta);
		}
		for(int i =0;i<racecars.size();i++) {
			racecars.get(i).update(getPlayer(),delta);
		}
		
		for(int i =0;i<trees.size();i++) {
			if (trees.get(i).hazard(getPlayer(), input)==true) {
				return;
			}
		}
		
		
		
		getPlayer().update(input,tileSize);
		extra.update(player, delta);
		
		
		
		for(int i =0;i<logs.size();i++) {
			logs.get(i).update(extra,getPlayer(),delta);
			if(logs.get(i).contactSprite(getPlayer())==true) {
				fallInWater=false;
	        }
			
		}
		
		for(int i =0;i<longlogs.size();i++) {

			longlogs.get(i).update(extra,getPlayer(),delta);
			if(longlogs.get(i).contactSprite(getPlayer())==true) {
				fallInWater=false;
	        }
		}
		
		for(int i =0;i<turtles.size();i++) {
			turtles.get(i).update(getPlayer(),delta);
			if(turtles.get(i).contactSprite(getPlayer())==true) {
				fallInWater=false;
	        }
		}
		

		
        

        
		for(int i =0;i<water.size();i++) {
			if (water.get(i).contactSprite(getPlayer())==true&&fallInWater==true) {
				player.setX(World.startX);
				player.setY(World.startY);
				player.updateBbox();
				player.setHP(player.getHP()-1);
			}
		}
		
		int count=0;
		
		for(int i =0;i<holes.size();i++) {
			holes.get(i).update(getPlayer());
			if(holes.get(i).isFull()==true) {
				count+=1;
			}
		}
		
		
		if(count==holesFill){
			this.isfinish=true;
		}
		
		this.setTime(this.getTime()+delta);
		
		if(this.getTime()>Rtime) {
			 int ran1 = new Random(System.currentTimeMillis()).nextInt(2);
			 if (ran1==0) {
				 int ran2 = new Random(System.currentTimeMillis()).nextInt(logs.size());
				 extra.setX(logs.get(ran2).getX());
				 extra.setY(logs.get(ran2).getY());
				 extra.setLog("log");
			 }
			 
			 else {
				 int ran2 = new Random(System.currentTimeMillis()).nextInt(longlogs.size());
				 extra.setX(longlogs.get(ran2).getX());
				 extra.setY(longlogs.get(ran2).getY());
				 extra.setLog("longlog");
			 }
			
			extra.appear();
			this.setTime(0);
		}
	}
		
	
	
	
	
	
	public void render(Graphics g) {
		
		
		for (Tile water : water) {
			water.render();
			}
		for (Tile grass : grass) {
			grass.render();
			}
		for (Tile tree : trees) {
			tree.render();
			}
		for (Hole hole : holes) {
			hole.render();
			}
		for (Bus bus : buses) {
			bus.render();
			}
		for (bulldozer Bulldozer : bulldozer) {
			Bulldozer.render();
			}
		
		for (Log log : logs) {
			log.render();
			}
		for (Long_log Longlog : longlogs) {
			Longlog.render();
			}
		for (Race_car racecars : racecars) {
			racecars.render();
			}
		for (Bike bike : bikes) {
			bike.render();
			}
		
		for (Turtles T : turtles) {
			T.render();
			}
		for(int i=0;i<getPlayer().getHP();i++) {
			life.drawCentered(lifeX+i*24, lifeY);
			
		}
			
		
		extra.render();
		getPlayer().render();




	}

	public boolean finish() {
		if (this.isfinish==true) {
			return true;
		}
		else return false;	
		
	}





	public Frog getPlayer() {
		return player;
	}



	public void setPlayer(Frog player) {
		this.player = player;
	}
	
	public void setTime(int t) {
		this.time=t;
	}
	
	public int getTime() {
		return this.time;
	}
}
