//Enemy movement not finished
package New_Gaming;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import New_Gaming.Game.STATE;

public class Homing extends GameObject {
	
	private GameObject player;
	
	public Homing(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		for (int i = 0; i < handler.object.size(); i++) {
			if(handler.object.get(i).getId() == ID.Player) {
				player = handler.object.get(i);
			}
		}
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
		
		float diffX = (x - player.getX() - 16);
		float diffY = (x - player.getY() - 16);
		float distance = (float) Math.sqrt((x-player.getX())*(x-player.getX()) + (y-player.getY())*(y-player.getY()));
		
		velX = ((-1/distance) * diffX);
		velY = ((-1/distance) * diffY);	
	}

	public void render(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect((int)x, (int)y, 16, 16);
	}
}