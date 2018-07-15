
/**
 * Beach Class
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Beach extends GameObject {

	public Beach(int x, int y, int width, int height, ID id) {
		super(x, y, id);	
		this.width = width;
		this.height = height;
	}
	@Override
	public void render(Graphics g) {
		if(Game.gameTheme == THEME.Classic) {
			g.setColor(Game.BEACH);
		}
		else if(Game.gameTheme == THEME.Lava) {
			g.setColor(Game.LAVA_BEACH);
		}
		else if(Game.gameTheme == THEME.Space) {
			g.setColor(Game.SPACE_BEACH);
		}
		g.fillRoundRect(x, y, width, height, 10, 10);
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
}
