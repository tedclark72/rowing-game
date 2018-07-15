
/**
 * KeyInput Class
 */
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
	
	private Handler handler;
	
	public KeyInput(Handler handler) {
		this.handler = handler;
	}
	/**Player Controls**/
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(Game.gameState == STATE.Game) {
			for(int i = 0; i < handler.object.size(); i++) {
				GameObject tempObject = handler.object.get(i);
				
				if(tempObject.getID() == ID.Player) {
					if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
						tempObject.setY(tempObject.getY() - 1);
						Game.playerSpeed = 2;
					}
					if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
						tempObject.setY(tempObject.getY() + 1);
						Game.playerSpeed = -2;
					}
					if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
						tempObject.setX(tempObject.getX() - 1);
					}
					if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
						tempObject.setX(tempObject.getX() + 1);
					}
					if(key == KeyEvent.VK_ESCAPE) {
						Game.gameState = STATE.Paused;
					}
				}
			}
		}
		
	}
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(Game.gameState == STATE.Game) {
			for(int i = 0; i < handler.object.size(); i++) {
				GameObject tempObject = handler.object.get(i);
				
				if(tempObject.getID() == ID.Player) {
					if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
						tempObject.setY(tempObject.getY() - 2);
						Game.playerSpeed = 0;
					}
					if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
						tempObject.setY(tempObject.getY() + 2);
						Game.playerSpeed = 0;
					}
					if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
						if(HUD.SCORE < 50) {
							tempObject.setX(tempObject.getX() - 3);
						}
						else if(HUD.SCORE >= 50 && HUD.SCORE < 150) {
							tempObject.setX(tempObject.getX() - 5);
						}
						else if(HUD.SCORE >= 150 && HUD.SCORE < 300) {
							tempObject.setX(tempObject.getX() - 7);
						}
						else if(HUD.SCORE >= 300) {
							tempObject.setX(tempObject.getX() - 9);
						}
					}
					if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
						if(HUD.SCORE < 50) {
							tempObject.setX(tempObject.getX() + 3);
						}
						else if(HUD.SCORE >= 50 && HUD.SCORE < 150) {
							tempObject.setX(tempObject.getX() + 5);
						}
						else if(HUD.SCORE >= 150 && HUD.SCORE < 300) {
							tempObject.setX(tempObject.getX() + 7);
						}
						else if(HUD.SCORE >= 300) {
							tempObject.setX(tempObject.getX() + 9);
						}
					}
				}
			}
		}
	}
}
