/**
 * Rowing Game Remastered 
 * Game Class
 * @author Teddy Clark
 * July 2018
 */
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

public class Game extends Canvas implements Runnable {
	private static Random rand = new Random();
	
	//Constants
	public static final int WIDTH = 640, HEIGHT = WIDTH / 9 * 12;
	
	public static final Color WATER = new Color(57, 139, 222);
    public static final Color BEACH = new Color(252, 195, 38);
    public static final Color LAVA_WATER = new Color(200, 0, 0);
    public static final Color LAVA_BEACH = new Color(59, 15, 0);
    public static final Color SPACE_WATER = Color.black;
    public static final Color SPACE_BEACH = new Color(150, 150, 150);
    
    //Images
    private BufferedImage classicBoat;
    private BufferedImage lavaBoat;
    private BufferedImage spaceBoat;
    
    //Thread
	private Thread thread;
	private boolean running = false;
	
	//Menus and HUD
	private Handler handler;
	private HUD hud;
	private MainMenu mainMenu;
	private LossMenu lossMenu;
	private PauseMenu pauseMenu;
	private ThemeMenu themeMenu;
	private ScoreMenu scoreMenu;
	private ControlsMenu controlsMenu;
	
	//Player
	private Player player;
	
	//Game States and Themes
	public static STATE gameState = STATE.Menu;
	public static THEME gameTheme = THEME.Classic;
	
	//Other
	public static int playerSpeed = 0;
	public static ArrayList<Integer> scoreList = new ArrayList<Integer>();
	
	/**Main Game Initialization**/
	public Game() {
		//Load boat pictures
        try {
           classicBoat = ImageIO.read(getClass().getResource("/images/boat.png"));
           lavaBoat = ImageIO.read(getClass().getResource("/images/lavaboat.png")); 
           spaceBoat = ImageIO.read(getClass().getResource("/images/spaceboat.png")); 
        } 
        catch (IOException e) {
        	System.out.println("Unable to load images.");
        }
        
        //Instantiate handler and window
		handler = new Handler();
		new Window(WIDTH, HEIGHT, "Rowing Game by Teddy Clark", this);
		
		//Instantiate HUD and Menus and add Listeners
		hud = new HUD();
		mainMenu = new MainMenu(this, handler, hud);
		lossMenu = new LossMenu(this, handler, hud);
		pauseMenu = new PauseMenu(this, handler, hud);
		themeMenu = new ThemeMenu(this, handler, hud);
		scoreMenu = new ScoreMenu(this, handler, hud);
		controlsMenu = new ControlsMenu(this, handler, hud);
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(mainMenu);
		this.addMouseListener(lossMenu);
		this.addMouseListener(pauseMenu);
		this.addMouseListener(themeMenu);
		this.addMouseListener(scoreMenu);
		this.addMouseListener(controlsMenu);
		
		//Add Player
		player = new Player(310, 680, ID.Player, handler, classicBoat);
		handler.addObject(player);		
	}
	
	/**Thread Control**/
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**Game Loop methods and Framerate**/
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			while(delta >= 1) {
				tick();
				delta--;
			}
			if(running) {
				render();
			}
			frames++;
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frames = 0;
			}
		}
		stop();
	}
	
	/**Main Tick Method**/
	private void tick() {
		//Tick while game is being played
		if(gameState == STATE.Game) {
			handler.tick();
			hud.tick();
			
			//Increase speed based on score
			for(int i = 0; i < handler.object.size(); i++) {
				GameObject tempObject = handler.object.get(i);
				
				if(tempObject.getID() != ID.Player) {
					if(hud.SCORE > 0) {
						tempObject.setVelY(4);
					}
					if(hud.SCORE > 50) {
						tempObject.setVelY(6);
					}
					if(hud.SCORE > 150) {
						tempObject.setVelY(8);
					}
					if(hud.SCORE > 300) {
						tempObject.setVelY(10);
					}
					if(hud.SCORE > 600) {
						tempObject.setVelY(12);
					}
				}
			}
		}
		//Lose if health is less than 0 and stop score counter
		if(hud.HEALTH <= 0) {
			hud.HEALTH = 100;
			scoreList.add(hud.SCORE);
			gameState = STATE.Loss;
			hud.timer.stop();
		}
		//Pause game and set all objects velocity to 0
		if(gameState == STATE.Paused) {
			hud.timer.stop();
			
			for(int i = 0; i < handler.object.size(); i++) {
				GameObject tempObject = handler.object.get(i);
				
				if(tempObject.getID() != ID.Player) {
					tempObject.setVelY(0);
				}	
			}
		}
		
		//Set boat picture based on theme
		if(gameTheme == THEME.Classic) {
			player.setBoatPicture(classicBoat);
		}
		else if(gameTheme == THEME.Lava) {
			player.setBoatPicture(lavaBoat);
		}
		else if(gameTheme == THEME.Space) {
			player.setBoatPicture(spaceBoat);
		}
		
	}
	
	/**Main Render Method**/
	private void render() {
		//Instantiate graphics
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		
		//Background color based on theme
		if(gameTheme == THEME.Classic) {
			g.setColor(WATER);
		}
		if(gameTheme == THEME.Lava) {
			g.setColor(LAVA_WATER);
		}
		if(gameTheme == THEME.Space) {
			g.setColor(SPACE_WATER);
		}
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);
		
		if(gameState == STATE.Game) {
			hud.render(g);
			//Loop Game Objects back to top
			for(int i = 0; i < handler.object.size(); i++) {
				GameObject tempObject = handler.object.get(i);
				
				if(tempObject.getY() > HEIGHT) {
					handler.removeObject(tempObject);
					
					if(tempObject.getID() == ID.Obstacle) {	
						handler.addObject(new Obstacle(rand.nextInt(601), (-i * (rand.nextInt(51) + 31)), ID.Obstacle));		
					}	
					if(tempObject.getID() == ID.HealthPack) {	
						handler.addObject(new HealthPack(rand.nextInt(400) + 100, -100, ID.HealthPack));		
					}
					if(tempObject.getID() == ID.Beach) {	
						if(tempObject.getX() < 250) {
							handler.addObject(new Beach(0 - (rand.nextInt(151) + 50), -90, (rand.nextInt(251) + 75), 100, ID.Beach));
						}
						if(tempObject.getX() > 250) {
							handler.addObject(new Beach(650 - (rand.nextInt(201) + 50), -90, (rand.nextInt(301) + 100), 100, ID.Beach));
						}
					}
				}
			}
		}
		
		//Render menus based on game state
		else if(gameState == STATE.Menu){
			mainMenu.render(g);
			player.setX(310);
			player.setY(680);
		}
		else if(gameState == STATE.Loss) {
			lossMenu.render(g);
		}
		else if(gameState == STATE.Paused) {
			pauseMenu.render(g);
		}
		else if(gameState == STATE.Themes) {
			themeMenu.render(g);
		}
		else if(gameState == STATE.Scores) {
			scoreMenu.render(g);
		}
		else if(gameState == STATE.Controls) {
			controlsMenu.render(g);
		}

		g.dispose();
		bs.show();
	}
	
	/**MAIN METHOD**/
	public static void main(String[] args) {
		new Game();
	}
}
