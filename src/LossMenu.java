/**
 * Loss Class
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class LossMenu extends MouseAdapter {
	private Game game;
	private Handler handler;
	private HUD hud;
	private Random rand;
	
	public LossMenu(Game game, Handler handler, HUD hud) {
		this.game = game;
		this.handler = handler;
		this.hud = hud;
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		rand = new Random();
		
		
		
	}
	public void mouseReleased(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		rand = new Random();
		
		
		if(Game.gameState == STATE.Loss) {
			//Replay Button
			if(mouseHover(mx, my, 120, 345, 190, 50)) {
				Game.gameState = STATE.Game;
				hud.timer.start();
				hud.HEALTH = 100;
				hud.SCORE = 0;
				
				for(int i = 0; i < handler.object.size(); i++) {
					GameObject tempObject = handler.object.get(i);
					if(tempObject.getID() != ID.Player) {
						tempObject.removed = true;
					}
					else {
						tempObject.setX(310);
						tempObject.setY(680);
					}
				}
					
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
			
			//Main Menu Button
			if(mouseHover(mx, my, 330, 345, 190, 50)) {
				for(int i = 0; i < handler.object.size(); i++) {
					GameObject tempObject = handler.object.get(i);
					if(tempObject.getID() != ID.Player) {
						tempObject.removed = true;
					}
					else {
						tempObject.setX(310);
						tempObject.setY(680);
					}
				}
				
				Game.gameState = STATE.Menu;
				hud.HEALTH = 100;
			}
		}
	}
	public void tick() {
		
	}
	public boolean mouseHover(int mx, int my, int x, int y, int width, int height) {
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
	public void render(Graphics g) {
		//You Crashed Text
		g.setColor(new Color(0, 0, 0, 75));
		g.fillRoundRect(120, 125, 400, 100, 10, 10);
		g.setColor(Color.red);
		g.setFont(new Font("Helvetica", 1, 56));
		g.drawString("You Crashed!", 135, 195);
		
		//Score
		g.setColor(new Color(0, 0, 0, 75));
		g.fillRoundRect(225, 275, 190, 50, 10, 10);
		g.setColor(Color.black);
		g.drawRoundRect(225, 275, 190, 50, 10, 10);
		g.setFont(new Font("Helvetica", 1, 24));
		g.setColor(Color.white);
		g.drawString("Score: " + HUD.SCORE, 265, 307);
		
		//Replay and Return to Menu button
		g.setColor(Color.black);
		g.drawRoundRect(120, 125, 400, 100, 10, 10);
		g.drawRoundRect(120, 345, 190, 50, 10, 10);
		g.drawRoundRect(330, 345, 190, 50, 10, 10);
		g.setColor(new Color(0, 0, 0, 60));
		g.fillRoundRect(120, 345, 190, 50, 10, 10);
		g.fillRoundRect(330, 345, 190, 50, 10, 10);
		g.setColor(Color.white);
		g.drawString("Replay", 175, 377);
		g.drawString("Main Menu", 360, 377);
	}
}
