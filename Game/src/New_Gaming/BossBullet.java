package New_Gaming;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import New_Gaming.Game.STATE;

public class BossBullet extends GameObject{
	
	Random r = new Random();
	
	public BossBullet(int x, int y, ID id, Handler handler) {
		super(x, y, id);
			
		velX = (r.nextInt(5 - -5 + -5));
		velY = 5;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 16, 16);
	}	

	public void tick() {
		//Stop movement if player is in the shop
		if(Game.gameState == STATE.Shop) {
		} else {
			x += velX;
			y += velY;
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x,  (int)y, 16, 16);
	}

}