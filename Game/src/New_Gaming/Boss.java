package New_Gaming;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import New_Gaming.Game.STATE;

public class Boss extends GameObject{
	
	private int timer = 60;
	private int timer2 = 40;
	Random r = new Random();
	private Handler handler;
	
	public Boss(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velX = 0;
		velY = 2;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 150, 150);
	}	

	public void tick() {
		//Stop movement if player is in the shop
		if(Game.gameState == STATE.Shop) {
		} else {
			x += velX;
			y += velY;
		}
		
		if (Game.gameState != STATE.Shop) {
			timer--;
		}
		
		//Stops movement down into the window and starts to move left and right
		if (timer <= 0 && Game.gameState != STATE.Shop) {
			velY = 0;
			timer2--;
			if (timer2 <= 0) {
				if (velX == 0) {
					velX = 5;
				}
				if (velX > 0) {
					velX += 0.005;
				}
				if (velX < 0) {
					velX -= 0.005;
				}
				
				velX = Game.clamp(velX, -10, 10);
				
				//Random spawner for boss bullets
				int spawn = r.nextInt(5);
				if (spawn == 0 && Game.gameState != STATE.Shop) {
					 handler.addObject (new BossBullet((int)x + 48, (int)y + 48, ID.BossBullet, handler));
					 if(Game.hard == 1) {
						 handler.addObject (new BossBullet((int)x + 48, (int)y + 48, ID.BossBullet, handler));
					 }
				}
			}
		}
		
		//Direction Change
		if (x <= -50 || x >= Game.WIDTH - 100) {
			velX *= -1;	
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, 150, 150);
	}
	
}
