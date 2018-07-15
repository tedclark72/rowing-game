
/**
 * HealthPack Class
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class HealthPack extends GameObject {
	private Random rand = new Random();
	private int health;
	
	public HealthPack(int x, int y, ID id) {
		super(x, y, id);
		
		width = 10;
		height = 25;
		health = rand.nextInt(21) + 10;
	}
	@Override
	public void tick() {
		x += velX;
		y += velY;
		
	}
	@Override
	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillRoundRect(x,  y, width, height, 10, 10);
		g.setColor(Color.red);
		g.drawRoundRect(x, y, width, height, 10, 10);
		
	}
	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	

}
