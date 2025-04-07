package New_Gaming;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 1550691097823471818L;
	
	public static final int WIDTH = 800, HEIGHT = WIDTH / 12 * 9;
	
	private Thread thread;
	private boolean running = false;
	private Random r;
	private Handler handler;
	private HUD hud;
	private Spawn spawner;
	private Menu menu;
	private Shop shop;
	public static int c = 1, c2 = 1, end = 1;
	public static int hard = 0;
	
	public enum STATE {
		Menu,
		Game,
		HardGame,
		Help,
		Difficulty,
		Shop,
		GameOver
	};
	
	public static STATE gameState = STATE.Menu;
	
	public Game() {
		handler = new Handler();
		menu = new Menu(this, handler);
		shop = new Shop(handler);
		
		//All Key Events are coded in KeyInput
		this.addKeyListener(new KeyInput(handler));
		
		//All Mouse Events are coded in Menu
		this.addMouseListener(menu);
		
		//Creates the Window
		new Window(WIDTH, HEIGHT, "GAME", this);
		
		hud = new HUD();
		spawner = new Spawn(handler, hud);
		r = new Random();
		
		//Menu Pixel Effect
		for (int i = 0; i < 50; i++) {
			handler.addObject(new MParticle(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.MParticle));
		}
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Found this function online to help get started
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		while (running) {
			long now =System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			if (running)
				render();
			
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
			}
		}
		stop();
	}
	
	private void tick() {
		//Goes into different classes' tick based on the state the game is in
		handler.tick();
		if (gameState == STATE.Game || gameState == STATE.HardGame || gameState == STATE.Shop) {
			hud.tick();
			spawner.tick();
		} else if (gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.Difficulty || gameState == STATE.GameOver || HUD.HEALTH == 0) {
			menu.tick();
		}
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		//Rendering Based on the state of the game
		if (gameState == STATE.Game || gameState == STATE.HardGame) {
			hud.render(g);
			handler.render(g);
			
			if ((HUD.HEALTH == 0 || HUD.score == 7500) && end == 1 && (gameState == STATE.Game || gameState == STATE.HardGame)) {
				handler.clear();
				for (int i = 0; i < 50; i++) {
					handler.addObject(new MParticle(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.MParticle));
				}
				end--;
			}
			
			if (gameState == STATE.HardGame) {
				hard = 1;
			}
			
			if (HUD.level == 1 && c == 1) {
				c--;
				if (gameState == STATE.Game || gameState == STATE.HardGame) {
					handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
					
					handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy));
				
					handler.addObject(new Coin(r.nextInt(Game.WIDTH - 38), r.nextInt(Game.HEIGHT - 72), ID.Coin, handler));
					
					if (gameState == STATE.HardGame) {
						handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy));
					}
				}
			}
		} else if (gameState == STATE.Shop) {
			shop.render(g);
		} else if (gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.Difficulty || HUD.HEALTH == 0) {
			menu.render(g);
			handler.render(g);
		}

		//Extra spawns if game is in hard mode
		if (HUD.score%500 == 0 && gameState == STATE.HardGame) {
			if (HUD.lc2 == true) {
				HUD.lc2 = false;
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
		
		g.dispose();
		bs.show();
	}
	
	public static float clamp(float var, float min, float max) {
		if (var >= max) {
			return var = max;
		} else if (var <= min) {
			return var = min;
		} else {
			return var;
		}
	}
	
	public static void main(String args[]) {
		new Game();
	}
}
