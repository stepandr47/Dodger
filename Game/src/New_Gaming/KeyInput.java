package New_Gaming;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import New_Gaming.Game.STATE;

public class KeyInput extends KeyAdapter{
	private Handler handler;
	private boolean[] keyDown = new boolean[4];
	public static boolean Pause = false;
	private static int count = 0, count2 = 0;
	
	public KeyInput(Handler handler) {
		this.handler = handler;
		
		//Used to make player movement smoother
		keyDown[0] = false;
		keyDown[1] = false;
		keyDown[2] = false;
		keyDown[3] = false;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			//Player Movement
			if (tempObject.getId() == ID.Player && HUD.HEALTH != 0 && HUD.level != 7500 && Game.gameState != STATE.Shop) {
				if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
					tempObject.setVelY(-3 - Menu.SpeLev); keyDown[0] = true;
				}
				
				if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
					tempObject.setVelY(3 + Menu.SpeLev); keyDown[1] = true;
				}
				
				if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
					tempObject.setVelX(-3 - Menu.SpeLev); keyDown[2] = true;
				}
				
				if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
					tempObject.setVelX(3 + Menu.SpeLev); keyDown[3] = true;
				}
			}
		}
		
		//Counts are used to stop user error of button pressing too long
		count--;
		if (count < 0) {
			count = 0;
		}
		
		count2--;
		if (count2 < 0) {
			count2 = 0;
		}

		//Enter and exit shop
	    if (key == KeyEvent.VK_SPACE && count == 0 && (HUD.HEALTH != 0 && HUD.score != 7500)) {
	    	count = 1;
	    	
	    	if (Game.gameState == STATE.Shop) {
	    		if (Game.hard == 0) {
	    			Game.gameState = STATE.Game;
	    		} else {
	    			Game.gameState = STATE.HardGame;
	    		}
	    	} else if (Game.gameState == STATE.Game) {
	    		Game.gameState = STATE.Shop;
	    	} else if (Game.gameState == STATE.HardGame) {
	    		Game.gameState = STATE.Shop;
	    	}
		}
	    
		//In shop upgrades
	    if (Game.gameState == STATE.Shop && count2 == 0) {
	    	count2 = 1;
	    	
	    	//Speed Increase
	    	if(key == KeyEvent.VK_1) {
	    		if (Player.CC >= Menu.C1 && Menu.SpeLev < 5) {
	    			Menu.SpeLev++;
					Player.CC = Player.CC - Menu.C1;
					Menu.C1 += 5;
				}
			}
	    	
	    	//Health Increase
	    	if(key == KeyEvent.VK_2) {
	    		if (Player.CC >= Menu.C2) {
	    			Menu.FullH += 10;
					Player.CC = Player.CC - Menu.C2;
					Menu.C2 += 5;
				}
			}
	    	
	    	//Full Heal
	    	if(key == KeyEvent.VK_3) {
	    		if (Player.CC >= Menu.C3 && HUD.HEALTH != Menu.FullH) {
					HUD.HEALTH = Menu.FullH;
					Player.CC = Player.CC - Menu.C3;
					Menu.C3 += 5;
				}
			}
	    	
	    	//Coin Multiplier
	    	if(key == KeyEvent.VK_4) {
	    		if (Player.CC >= Menu.C4 && Menu.MultC < 5) {
	    			Menu.MultC++;
					Player.CC = Player.CC - Menu.C4;
					Menu.C4 += 5;
				}
			}
	    }
		
	    //Exit the game
		if (key == KeyEvent.VK_ESCAPE) {
			System.exit(1);
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			//Stop player movement when key is released
			if (tempObject.getId() == ID.Player) {
				if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
					keyDown[0] = false;
				}
				
				if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
					keyDown[1] = false;
				}
				
				if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
					keyDown[2] = false;
				}
				
				if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
					keyDown[3] = false;
				}
				
				if (!keyDown[0] && !keyDown[1]) {
					tempObject.velY = 0;
				}
				
				if (!keyDown[2] && !keyDown[3]) {
					tempObject.velX = 0;
				}
			}
		}
	}
}