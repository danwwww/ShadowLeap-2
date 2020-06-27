/**
 * Sample Project for SWEN20003: Object Oriented Software Development 2018
 * by Eleanor McMurtry, University of Melbourne
 */

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Input;

/**
 * Main class for the game.
 * Handles initialisation, input and rendering.
 */
public class App extends BasicGame {
    /** screen width, in pixels */
    public static final int SCREEN_WIDTH = 1024;
    /** screen height, in pixels */
    public static final int SCREEN_HEIGHT = 768;
    public static final int InitHP = 3;

    private World world;
    private World world2;
    private boolean uplevel=false;

    public App() {
        super("Shadow Leap");
    }

    @Override
    public void init(GameContainer gc)
            throws SlickException {
        try {
			world = new World("assets/levels/0.lvl");
			world2 = new World("assets/levels/1.lvl");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    /** Update the game state for a frame.
     * @param gc The Slick game container object.
     * @param delta Time passed since last frame (milliseconds).
     */
    @Override
    public void update(GameContainer gc, int delta)
            throws SlickException {
        // Get data about the current input (keyboard state).
        Input input = gc.getInput();
        if(world.finish()==false) {
        	world.update(input, delta);	
        }
        
        else if(world.finish()==true&&uplevel==false) {
        	Frog tempPlayer=world.getPlayer();
        	tempPlayer.setX(World.startX);
        	tempPlayer.setY(World.startY);
        	world2.setPlayer(tempPlayer);
        	uplevel=true;
        }
        else {
        	world2.update(input, delta);}
        
        if(world2.finish()==true) {
			JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
			"You Win!", "You win", JOptionPane.QUESTION_MESSAGE); 
			System.exit(0);	
        }
        
    }

    /** Render the entire screen, so it reflects the current game state.
     * @param gc The Slick game container object.
     * @param g The Slick graphics object, used for drawing.
     */
    public void render(GameContainer gc, Graphics g)
            throws SlickException {
    	if(world.finish()==false) {
    		world.render(g);
    	}
    	else{
        	world2.render(g);
        }
    	
    }

    /** Start-up method. Creates the game and runs it.
     * @param args Command-line arguments (ignored).
     */
    public static void main(String[] args)
            throws SlickException {
        AppGameContainer app = new AppGameContainer(new App());
        //set the update interval fixed as 20 milliseconds
        app.setShowFPS(true);
        app.setDisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT, false);
        app.start();
    }

}