
/**
 * GameObject Class
 */
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
	protected int x, y;
	protected ID id;
	protected int width, height;
	protected int velX, velY;
	protected Rectangle hitbox;
	protected boolean removed;
	
	public GameObject(int x, int y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
		
		velY = 4;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public void setID(ID id) {
		this.id = id;
	}
	public void setVelX(int velX) {
		this.velX = velX;
	}
	public void setVelY(int velY) {
		this.velY = velY;
	}
	public void setRemoved(boolean removed) {
		this.removed = removed;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public ID getID() {
		return id;
	}
	public int getVelX() {
		return velX;
	}
	public int getVelY() {
		return velY;
	}
	public boolean getRemoved() {
		return removed;
	}
	
}
