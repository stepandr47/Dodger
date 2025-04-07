package New_Gaming;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Player extends GameObject{
	
	Random r = new Random();
	Handler handler;
	public static int CC = 0;
	
	public static final int WIDTH = 800, HEIGHT = WIDTH / 12 * 9;
	
	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}

	public void tick() {
		//Stop movement if player is in the shop
		if (HUD.score == 7500 || HUD.HEALTH == 0) {
		} else {
			x += velX;
			y += velY;
		}
		
		//Makes it so that the player can't go outside of the game window
		x = Game.clamp((int)x, 0, Game.WIDTH - 38);
		y = Game.clamp((int)y, 0, Game.HEIGHT - 72);
		
		collision();
	}
	
	private void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			
			GameObject tempObject = handler.object.get(i);
			
			//Removes boss bullets when the reach the bottom of the screen
			if (tempObject.getId() == ID.BossBullet && tempObject.getY() == Game.HEIGHT) {
					handler.removeObject(tempObject);
			}
			
			//Deals damage and removes coins when player collides with other game objects
			if (tempObject.getId() == ID.Coin) {
				if (getBounds().intersects(tempObject.getBounds())) {
					handler.removeObject(tempObject);
					handler.addObject(new Coin(r.nextInt(WIDTH - 38), r.nextInt(HEIGHT - 72), ID.Coin, handler));
					HUD.score += 100;
					CC += Menu.MultC;
					if (HUD.score%500 == 0 && HUD.level != 10) {
						HUD.level++;
						HUD.lc = true;
						HUD.lc2 = true;
					}
				}
			} else if (tempObject.getId() == ID.BasicEnemy) {
				if (getBounds().intersects(tempObject.getBounds())) {
					HUD.HEALTH -= 2 + Game.hard;
				}
			} else if (tempObject.getId() == ID.Speedy) {
				if (getBounds().intersects(tempObject.getBounds())) {
					HUD.HEALTH -= 3 + Game.hard;
				}
			} else if (tempObject.getId() == ID.Tank) {
				if (getBounds().intersects(tempObject.getBounds())) {
					HUD.HEALTH -= 4 + Game.hard;
				}
			} else if (tempObject.getId() == ID.Homing) {
				if (getBounds().intersects(tempObject.getBounds())) {
					HUD.HEALTH -= 2 + Game.hard;
				}
			} else if (tempObject.getId() == ID.BossBullet) {
				if (getBounds().intersects(tempObject.getBounds())) {
					HUD.HEALTH -= 4 + Game.hard;
				}
			} else if (tempObject.getId() ==  ID.Boss) {
				if (getBounds().intersects(tempObject.getBounds())) {
					HUD.HEALTH -= 2 + Game.hard;
				}
			}
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect((int)x,  (int)y,  32, 32);
	}
}