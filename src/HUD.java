
/**
 * HUD Class
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class HUD {
	//Set Initial Health
	public static int HEALTH = 100;
	
	//Set Initial Score and Score Timer
	public static int SCORE = 0;
	public static int SPEED = 10;
	public static int DELAY = 1000;
    public static ActionListener scoreCalculator = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
        	SCORE++;
            if(SCORE <= 50) {
            	timer.setDelay(1000);
                SPEED = 10;
            }
            else if(SCORE <= 150) {
            	timer.setDelay(500);
                SPEED = 15;
            }
            else if(SCORE <= 300){
            	timer.setDelay(333);
                SPEED = 20;
            }
            else {
            	timer.setDelay(200);
            	SPEED = 25;
            }
        }
    };
    public static Timer timer = new Timer(DELAY, scoreCalculator);
    
	public void tick() {}
	
	/**Render Score and Health Bar**/
	public void render(Graphics g) {
		g.setFont(new Font("Helvetica", 1, 18));
		healthBar(g);
		scoreCount(g);	
		speedCount(g);
	}
	public void scoreCount(Graphics g) {
		g.setColor(new Color(0, 0, 0, 75));
		g.fillRoundRect(5, 5, 120, 60, 10, 10);
		g.setColor(Color.black);
		g.drawRoundRect(5, 5, 120, 60, 10, 10);
		
		g.setColor(Color.white);
		g.drawString("Score", 15, 25);
		
		g.setFont(new Font("Helvetica", 1, 20));
		g.drawString("" + SCORE, 15, 55);
	}
	public void healthBar(Graphics g) {
		g.setColor(new Color(0, 0, 0, 75));
		g.fillRoundRect(5, Game.HEIGHT - 110, 120, 60, 10, 10);
		g.setColor(Color.black);
		g.drawRoundRect(5, Game.HEIGHT - 110, 120, 60, 10, 10);
		
		g.setColor(Color.white);
		g.drawString("Health", 15, Game.HEIGHT - 90);
		
		if(HEALTH >= 40 && HEALTH <= 100) {
			g.setColor(Color.green);
			g.fillRect(15,  Game.HEIGHT - 85, HEALTH / 2, 24);
		}
		else if(HEALTH > 0 && HEALTH < 40) {
			g.setColor(Color.red);
			g.fillRect(15,  Game.HEIGHT - 85, HEALTH / 2, 24);
		}
		else if(HEALTH > 100 && HEALTH <= 200) {
			g.setColor(Color.blue);
			g.fillRect(15,  Game.HEIGHT - 85, HEALTH / 2, 24);
			g.setColor(Color.green);
			g.fillRect(15,  Game.HEIGHT - 85, 50, 24);	
		}
		else if(HEALTH <= 0) {
			HEALTH = 0;
			g.fillRect(15,  Game.HEIGHT - 85, 0, 24);
		}
		else if(HEALTH > 200) {
			HEALTH = 200;
			g.fillRect(15,  Game.HEIGHT - 85, 200, 24);
		}
		
		//Helath Bar Outline
		g.setColor(Color.black);
		g.drawRect(15, Game.HEIGHT - 85, 100, 24);
	}
	public void speedCount(Graphics g) {
		g.setColor(new Color(0, 0, 0, 75));
		g.fillRoundRect(Game.WIDTH - 140, 5, 120, 60, 10, 10);
		g.setColor(Color.black);
		g.drawRoundRect(Game.WIDTH - 140, 5, 120, 60, 10, 10);
		
		g.setFont(new Font("Helvetica", 1, 18));
		g.setColor(Color.white);
		g.drawString("Speed", Game.WIDTH - 130, 25);
		
		g.setFont(new Font("Helvetica", 1, 20));
		
		g.drawString((SPEED + Game.playerSpeed) + " mph", Game.WIDTH - 130, 55);
	}
}
