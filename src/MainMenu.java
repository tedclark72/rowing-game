/**
 * MainMenu Class
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class MainMenu extends MouseAdapter {
	private Game game;
	private Handler handler;
	private HUD hud;
	private Random rand;
	
	public MainMenu(Game game, Handler handler, HUD hud) {
		this.game = game;
		this.handler = handler;
		this.hud = hud;
	}
	
	public void mousePressed(MouseEvent e) {}
	
	/**Handle Button Presses**/
	public void mouseReleased(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		rand = new Random();
		
		if(Game.gameState == STATE.Menu) {
			//Play Button
			if(mouseHover(mx, my, 225, 275, 190, 50)) {
				Game.gameState = STATE.Game;
				hud.timer.start();
				hud.SCORE = 0;
				
				//Add Obstacles
				for(int i = 0; i < 17; i++) {
					handler.addObject(new Obstacle(rand.nextInt(641), (-i * (rand.nextInt(51) + 31)), ID.Obstacle));
				}
				//Add HealthPack
				handler.addObject(new HealthPack(rand.nextInt(200) + 200, -100, ID.HealthPack));
				//Add Beach
				for(int i = 0; i < 50; i++) {
					handler.addObject(new Beach(0 - (rand.nextInt(151) + 50), -i * 20, (rand.nextInt(251) + 75), 100, ID.Beach));
					handler.addObject(new Beach(650 - (rand.nextInt(201) + 50), -i * 20, (rand.nextInt(301) + 100), 100, ID.Beach));
				}
			}
			//Themes Button
			if(mouseHover(mx, my, 120, 345, 190, 50)) {
				Game.gameState = STATE.Themes;
			}
			//Scores Button
			if(mouseHover(mx, my, 330, 345, 190, 50)) {
				Game.gameState = STATE.Scores;
			}
			//Controls Button
			if(mouseHover(mx, my, 120, 405, 190, 50)) {
				Game.gameState = STATE.Controls;
			}
			//Quit Button
			if(mouseHover(mx, my, 330, 405, 190, 50)) {
				System.exit(1);
			}
		}
	}
	public void tick() {}
	
	/**Mouse Hover Method checks mouse location**/
	private boolean mouseHover(int mx, int my, int x, int y, int width, int height) {
		if(mx > x && mx < x + width) {
			if(my > y && my < y + height) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	
	/**Render Main Menu**/
	public void render(Graphics g) {
		//Black Boxes
		g.setColor(new Color(0, 0, 0, 75));
		g.fillRoundRect(120, 125, 400, 100, 10, 10);
		g.fillRoundRect(15, Game.HEIGHT - 85, 175, 25, 10, 10);
		g.fillRoundRect(Game.WIDTH - 175, Game.HEIGHT - 85, 140, 25, 10, 10);
		
		//Title, Author, Copyright
		g.setColor(Color.white);
		g.setFont(new Font("Helvetica", 1, 56));
		g.drawString("Rowing Game", 135, 195);
		g.setFont(new Font("Helvetica", 1, 14));
		g.drawString("Created by Teddy Clark", 20, Game.HEIGHT - 68);
		g.drawString("Copyright © 2018", Game.WIDTH - 170, Game.HEIGHT - 68);
		
		//Buttons
		g.setColor(Color.black);
		g.drawRoundRect(120, 125, 400, 100, 10, 10); //Title
		g.drawRoundRect(225, 275, 190, 50, 10, 10); //Play
		g.drawRoundRect(120, 345, 190, 50, 10, 10); //Themes
		g.drawRoundRect(120, 405, 190, 50, 10, 10); //Options
		g.drawRoundRect(330, 345, 190, 50, 10, 10); //Scores
		g.drawRoundRect(330, 405, 190, 50, 10, 10); //Quit
		g.setColor(new Color(0, 0, 0, 75));
		g.fillRoundRect(225, 275, 190, 50, 10, 10);
		g.fillRoundRect(120, 405, 190, 50, 10, 10);
		g.fillRoundRect(330, 405, 190, 50, 10, 10);
		g.fillRoundRect(120, 345, 190, 50, 10, 10);
		g.fillRoundRect(330, 345, 190, 50, 10, 10);
		
		//Button Text
		g.setFont(new Font("Helvetica", 1, 24));
		g.setColor(Color.white);
		g.drawString("Play!", 290, 307);
		g.drawString("Quit", 400, 439);
		g.drawString("Themes", 167, 377);
		g.drawString("Controls", 160, 439);
		g.drawString("Scores", 383, 377);
	}
}
