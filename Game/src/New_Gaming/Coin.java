package New_Gaming;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Coin extends GameObject {
	
	Random r = new Random();
	
	Handler handler;
	
	public Coin(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 10, 10);
	}
	
	public void tick() {
	}
	
	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillOval((int)x, (int)y, 10, 13);
	}


}
