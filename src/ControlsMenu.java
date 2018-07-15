/**
 * Controls Menu Class
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ControlsMenu extends MouseAdapter {
	private Game game;
	private Handler handler;
	private HUD hud;
	private HealthPack hpExample;
	
	public ControlsMenu(Game game, Handler handler, HUD hud) {
		this.game = game;
		this.handler = handler;
		this.hud = hud;
		hpExample = new HealthPack(137, 487, ID.HealthPack);
	}
	
	public void mousePressed(MouseEvent e) {}
	
	/**Handle Button Presses**/
	public void mouseReleased(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		if(Game.gameState == STATE.Controls) {
			//Back Button
			if(mouseHover(mx, my, 225, 600, 190, 50)) {
				Game.gameState = STATE.Menu;
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
	
	/**Render Scores Menu**/
	public void render(Graphics g) {
		//Black Boxes
		g.setColor(new Color(0, 0, 0, 75));
		g.fillRoundRect(232, 25, 176, 65, 10, 10); //Title
		g.fillRoundRect(225, 600, 190, 50, 10, 10); //Back
		g.fillRoundRect(195, 129, 250, 27, 10, 10); //Summary
		g.fillRoundRect(95, 180, 450, 138, 10, 10); //Controls
		g.fillRoundRect(95, 330, 450, 120, 10, 10); //How to Play
		g.fillRoundRect(95, 462, 450, 80, 10, 10); //Health Pack
		g.setColor(Color.black);
		g.drawRoundRect(232, 25, 176, 65, 10, 10); 
		g.drawRoundRect(225, 600, 190, 50, 10, 10);
		g.drawRoundRect(195, 129, 250, 27, 10, 10);
		g.drawRoundRect(95, 180, 450, 138, 10, 10);
		g.drawRoundRect(95, 330, 450, 120, 10, 10);
		g.drawRoundRect(95, 462, 450, 80, 10, 10);
		
		g.setColor(Color.white);
		g.setFont(new Font("Helvetica", 1, 36));
		g.drawString("Controls", 247, 68);
		g.setFont(new Font("Helvetica", 1, 18));
		g.drawString("How to play Rowing Game.", 201, 150);
		
		g.setFont(new Font("Helvetica", 1, 24));
		g.drawString("Back", 290, 632);
		
		g.setFont(new Font("Helvetica", 1, 18));
		g.drawString("W - Move Forward", 105, 205);
		g.drawString("S - Move Backward", 105, 230);
		g.drawString("D - Move Right", 105, 255);
		g.drawString("A - Move Left", 105, 280);
		g.drawString("ESC - Pause", 105, 305);
		
		g.setFont(new Font("Helvetica", 1, 14));
		g.drawString("This is your boat. Your goal is to get the boat as far", 180, 355);
		g.drawString("as possible without your health reaching 0. Avoid", 180, 375);
		g.drawString("the beach, which will instantly destroy the boat.", 180, 395);
		g.drawString("Obstacles will also damage you along the way. The", 180, 415);
		g.drawString("game will speed up as you get further.", 180, 435);
		
		g.drawString("This is a Health Pack. They will restore your health", 180, 485);
		g.drawString("for a certain amount. They can also increase your", 180, 505);
		g.drawString("max health to up to twice of your starting health!", 180, 525);
		//Render Player and Health Pack example
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getID() == ID.Player) {
				tempObject.setX(130);
				tempObject.setY(363);
				tempObject.render(g);
			}
		}
		hpExample.render(g);
	}
}
