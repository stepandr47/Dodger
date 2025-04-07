package New_Gaming;

import java.util.Random;

import New_Gaming.Game.STATE;

public class Spawn {
	
	private Handler handler;
	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
	private Random r;
	public static int counter = 50, clear = 1;
	
	public Spawn (Handler handler, HUD hud) {
		this.handler = handler;
	}
	
	public void tick() {
		//Clears all game objects once health reaches zero
		if (HUD.HEALTH == 0 && clear == 1) {
			handler.clear();
			clear = 0;
		} else {
			
			r = new Random();
			
			//Enemy spawns based on score and level
			if (HUD.level == 10) {
				if (HUD.score == 4500) {
					handler.clearEnemies();
					handler.addObject(new Boss((Game.WIDTH / 2) - 70, -120, ID.Boss, handler));
				}
				
				counter--;
				if(counter < 0) {
					counter = 0;
				}
				
				if (HUD.score == 7500 || HUD.HEALTH == 0 || Game.gameState == STATE.Shop) {
				} else if (counter == 0) {
					HUD.score += 100;
					counter = 50;
				}
			} else if (HUD.score%500 == 0) {
				if (HUD.lc == true) {
					HUD.lc = false;
					if (HUD.level == 2) {
						handler.addObject(new BasicEnemy(r.nextInt(WIDTH - 50), r.nextInt(HEIGHT - 50), ID.BasicEnemy));
					} else if (HUD.level == 3) {
						handler.addObject(new BasicEnemy(r.nextInt(WIDTH - 50), r.nextInt(HEIGHT - 50), ID.BasicEnemy));
					} else if (HUD.level == 4) {
						handler.addObject(new BasicEnemy(r.nextInt(WIDTH - 50), r.nextInt(HEIGHT - 50), ID.BasicEnemy));
					} else if (HUD.level == 5) {
						handler.addObject(new Tank(r.nextInt(WIDTH - 50), r.nextInt(HEIGHT - 50), ID.Tank));
					} else if (HUD.level == 6) {
						handler.addObject(new Tank(r.nextInt(WIDTH - 50), r.nextInt(HEIGHT - 50), ID.Tank));
					} else if (HUD.level == 7) {
						handler.addObject(new BasicEnemy(r.nextInt(WIDTH - 50), r.nextInt(HEIGHT - 50), ID.BasicEnemy));
					} else if (HUD.level == 8) {
						handler.addObject(new Speedy(r.nextInt(WIDTH - 50), r.nextInt(HEIGHT - 50), ID.Speedy));
					} else if (HUD.level == 9) {
						handler.addObject(new Speedy(r.nextInt(WIDTH - 50), r.nextInt(HEIGHT - 50), ID.Speedy));
					}
				}
			}
		}
	}
}