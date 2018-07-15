
/**
 * Player Class
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Player extends GameObject {
	private BufferedImage boatPicture;
	private Random rand = new Random();
	Handler handler; 
	
	public Player(int x, int y, ID id, Handler handler, BufferedImage boatPicture) {
		super(x, y, id);
		width = 25;
		height = 49;
		velY = 0;
		this.handler = handler;
		this.boatPicture = boatPicture;
	}
	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		collision();
	}
	@Override
	public void render(Graphics g) {
		g.drawImage(boatPicture, x, y, null);
	}
	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
	
	/**Player Collision**/
	private void collision() {
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getID() == ID.Obstacle) {
				if(this.getBounds().intersects(tempObject.getBounds())) {
					HUD.HEALTH -= ((Obstacle) tempObject).getDamage();
					handler.removeObject(tempObject);
					handler.addObject(new Obstacle(rand.nextInt(641), (-i * (rand.nextInt(51) + 31)), ID.Obstacle));
				}
			}
			if(tempObject.getID() == ID.Beach) {
				if(this.getBounds().intersects(tempObject.getBounds())) {
					HUD.HEALTH -= 10;
				}
			}
			if(tempObject.getID() == ID.HealthPack) {
				if(this.getBounds().intersects(tempObject.getBounds())) {
					HUD.HEALTH += ((HealthPack) tempObject).getHealth();
					handler.removeObject(tempObject);
					handler.addObject(new HealthPack(rand.nextInt(641) + 200, -100, ID.HealthPack));
				}
			}
		}
	}
	public BufferedImage getBoatPicture() {
		return boatPicture;
	}
	public void setBoatPicture(BufferedImage boatPicture) {
		this.boatPicture = boatPicture;
	}
	
}
