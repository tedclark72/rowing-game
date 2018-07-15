/**
 * ScoreMenu Class
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.Collections;

public class ScoreMenu extends MouseAdapter {
	private Game game;
	private Handler handler;
	private HUD hud;
	
	public ScoreMenu(Game game, Handler handler, HUD hud) {
		this.game = game;
		this.handler = handler;
		this.hud = hud;
	}
	
	public void mousePressed(MouseEvent e) {}
	
	/**Handle Button Presses**/
	public void mouseReleased(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		if(Game.gameState == STATE.Scores) {
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
		g.fillRoundRect(245, 25, 150, 65, 10, 10); //Title
		g.fillRoundRect(225, 600, 190, 50, 10, 10); //Back
		g.fillRoundRect(117, 129, 406, 27, 10, 10); //Summary
		g.fillRoundRect(95, 180, 450, 400, 10, 10); //List
		g.setColor(Color.black);
		g.drawRoundRect(245, 25, 150, 65, 10, 10); 
		g.drawRoundRect(225, 600, 190, 50, 10, 10);
		g.drawRoundRect(117, 129, 406, 27, 10, 10);
		g.drawRoundRect(95, 180, 450, 400, 10, 10);
		g.drawLine(95, 215, 545, 215);
		
		g.setColor(Color.white);
		g.setFont(new Font("Helvetica", 1, 36));
		g.drawString("Scores", 260, 68);
		g.setFont(new Font("Helvetica", 1, 18));
		g.drawString("Here are the scores of your previous games.", 123, 150);
		
		g.setFont(new Font("Helvetica", 1, 24));
		g.drawString("Back", 290, 632);
		
		g.setFont(new Font("Helvetica", 1, 20));
		g.drawString("Score", 105, 205);
		
		sortList();
		for(int i = 0; i < Game.scoreList.size(); i++) {
			g.drawString(Game.scoreList.get(i) + "", 105, 240 + (i * 25));
		}
	}
	
	private void sortList() {
		Collections.sort(Game.scoreList);
		Collections.reverse(Game.scoreList);
		
		if(Game.scoreList.size() > 15) {
			for(int i = 15; i < Game.scoreList.size(); i++) {
				Game.scoreList.remove(Game.scoreList.get(i));
			}
		}
	}
}
