/**
 * Paused Class
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PauseMenu extends MouseAdapter {
	private Game game;
	private Handler handler;
	private HUD hud;
	
	public PauseMenu(Game game, Handler handler, HUD hud) {
		this.game = game;
		this.handler = handler;
		this.hud = hud;
	}
	
	public void mousePressed(MouseEvent e) {}
	
	/**Handle Button Presses**/
	public void mouseReleased(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();

		if(Game.gameState == STATE.Paused) {
			//Continue Button
			if(mouseHover(mx, my, 225, 275, 190, 50)) {
				Game.gameState = STATE.Game;
				hud.timer.start();
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
	
	/**Render Pause Screen**/
	public void render(Graphics g) {
		g.setColor(new Color(0, 0, 0, 75));
		g.fillRoundRect(120, 125, 400, 100, 10, 10);
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		
		g.setColor(Color.white);
		g.setFont(new Font("Helvetica", 1, 56));
		g.drawString("Paused", 218, 195);
		
		//Continue Button
		g.setColor(Color.black);
		g.drawRoundRect(120, 125, 400, 100, 10, 10); //Title
		g.drawRoundRect(225, 275, 190, 50, 10, 10); //Continue
		g.setColor(new Color(0, 0, 0, 75));
		g.fillRoundRect(225, 275, 190, 50, 10, 10);
		
		//Button Text
		g.setFont(new Font("Helvetica", 1, 24));
		g.setColor(Color.white);
		g.drawString("Continue", 265, 307);
	}
}
