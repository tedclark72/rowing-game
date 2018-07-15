/**
 * ThemeMenu Class
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class ThemeMenu extends MouseAdapter {
	private Game game;
	private Handler handler;
	private HUD hud;
	
	public ThemeMenu(Game game, Handler handler, HUD hud) {
		this.game = game;
		this.handler = handler;
		this.hud = hud;
	}
	/**Handle Button Presses**/
	public void mouseReleased(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		if(Game.gameState == STATE.Themes) {
			//Back Button
			if(mouseHover(mx, my, 225, 600, 190, 50)) {
				Game.gameState = STATE.Menu;
			}
			//Classic Theme
			if(mouseHover(mx, my, 100, 180, 190, 150)) {
				Game.gameTheme = THEME.Classic;
			}
			//Lava Theme
			if(mouseHover(mx, my, 350, 180, 190, 150)) {
				Game.gameTheme = THEME.Lava;
			}
			//Lava Theme
			if(mouseHover(mx, my, 100, 390, 190, 150)) {
				Game.gameTheme = THEME.Space;
			}
		}
		
	}
	
	public void mousePressed(MouseEvent e) {}
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
	
	/**Render Theme Menu**/
	public void render(Graphics g) {
		//Black Boxes
		g.setColor(new Color(0, 0, 0, 75));
		g.fillRoundRect(245, 25, 150, 65, 10, 10); //Title
		g.fillRoundRect(41, 129, 558, 27, 10, 10); //Summary
		g.fillRoundRect(225, 600, 190, 50, 10, 10); //Back
		g.fillRoundRect(100, 180, 190, 150, 10, 10); //Classic
		g.fillRoundRect(350, 180, 190, 150, 10, 10); //Lava
		g.fillRoundRect(100, 390, 190, 150, 10, 10); //Space
		g.setColor(Color.black);
		g.drawRoundRect(245, 25, 150, 65, 10, 10); 
		g.drawRoundRect(41, 129, 558, 27, 10, 10); 
		g.drawRoundRect(225, 600, 190, 50, 10, 10); 
		g.drawRoundRect(100, 180, 190, 150, 10, 10); 
		g.drawRoundRect(350, 180, 190, 150, 10, 10); 
		g.drawRoundRect(100, 390, 190, 150, 10, 10);
		
		g.setColor(Color.white);
		g.setFont(new Font("Helvetica", 1, 36));
		g.drawString("Themes", 253, 68);
		g.setFont(new Font("Helvetica", 1, 18));
		g.drawString("Themes are used to change the look and feel of Rowing Game.", 47, 150);
		
		g.setFont(new Font("Helvetica", 1, 24));
		g.drawString("Back", 290, 632);
		g.drawString("Classic", 150, 210);
		g.drawString("Lava", 415, 210);
		g.drawString("Space", 155, 420);
		
		g.setColor(Game.WATER);
		g.fillRect(115, 220, 155, 90);
		g.setColor(Game.BEACH);
		g.fillRect(115, 220, 40, 90);
		g.fillRect(230, 220, 40, 90);
		g.setColor(Game.LAVA_WATER);
		g.fillRect(365, 220, 155, 90);
		g.setColor(Game.LAVA_BEACH);
		g.fillRect(365, 220, 40, 90);
		g.fillRect(480, 220, 40, 90);
		g.setColor(Game.SPACE_WATER);
		g.fillRect(115, 430, 155, 90);
		g.setColor(Game.SPACE_BEACH);
		g.fillRect(115, 430, 40, 90);
		g.fillRect(230, 430, 40, 90);
		
		g.setColor(Color.black);
		g.drawRect(115, 220, 155, 90);
		g.drawRect(365, 220, 155, 90);
		g.drawRect(115, 430, 155, 90);
	}
}
