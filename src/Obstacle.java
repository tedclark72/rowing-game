
/**
 * Obstacle Class
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Obstacle extends GameObject {
	private int damage;
	private boolean hasDamaged;
	private Color color;
	Random rand = new Random();
	
	public Obstacle(int x, int y, ID id) {
		super(x, y, id);	
		
		width = rand.nextInt(31) + 5;
		height = rand.nextInt(31) + 5;
		damage = rand.nextInt(21) + 10;
		hasDamaged = false;
		
		if(Game.gameTheme == THEME.Classic) {
			color = new Color(rand.nextInt(81), rand.nextInt(51), rand.nextInt(51));
		}
		else if(Game.gameTheme == THEME.Lava) {
			color = new Color(rand.nextInt(51) + 50, rand.nextInt(21), 0);
		}
		else if(Game.gameTheme == THEME.Space) {
			color = new Color(rand.nextInt(256), 255, rand.nextInt(256));
		}
	}
	@Override
	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, width, height);
	}
	@Override
	public void tick() {
		x += velX;
		y += velY;
	}
	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
	public boolean isHasDamaged() {
		return hasDamaged;
	}
	public void setHasDamaged(boolean hasDamaged) {
		this.hasDamaged = hasDamaged;
	}
	public int getDamage() {
		return damage;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}
}
