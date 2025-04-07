//Menu Particles
package New_Gaming;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class MParticle extends GameObject {
	
	Random r = new Random();
	private Color col;
	private int rand1, rand2;
	
	public MParticle(int x, int y, ID id) {
		super(x, y, id);
		
		rand1 = (r.nextInt(5 - -5 + -5));
		rand2 = (r.nextInt(5 - -5 + -5));
		
		//While statements stop it from being completely stationary
		while (rand1 == 0) {
			rand1 = (r.nextInt(5 - -5 + -5));
		}
		while (rand2 == 0) {
			rand2 = (r.nextInt(5 - -5 + -5));
		}
		
		velX = rand1;
		velY = rand2;
		
		col = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
	}

	public void tick() {
		x += velX;
		y += velY;
		
		//Direction changes so it doesn't go outside the window
		if (x <= 0 || x >= Game.WIDTH - 16) {
			velX *= -1;	
		}
		if (y <= 0 || y >= Game.HEIGHT - 50) {
			velY *= -1;
		}
	}

	public void render(Graphics g) {
		g.setColor(col);
		g.fillRect((int)x,  (int)y, 12, 12);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 12, 12);
	}
	
}
