package New_Gaming;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import New_Gaming.Game.STATE;

public class Menu extends MouseAdapter {
	
	Handler handler;
	public static int SpeLev = 0, HeaLev = 0, FullH = 100, MultC = 1, C1 = 5, C2 = 5, C3 = 5, C4 = 5, shop = 1;
	
	public Menu(Game game, Handler handler) {
		this.handler = handler;
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		//shop is used to stop user error of button pressing too long
		shop--;
		if (shop < 0) {
			shop = 0;
		}
		
		if (Game.gameState == STATE.Menu) {
			//play button
			if (mouseOver(mx, my, 300, 150, 200, 64)) {
				Game.gameState = STATE.Difficulty;
				HUD.HEALTH = 100;
				Game.c = 1;
				Game.c2 = 1;
			}
			
			//help button
			if (mouseOver(mx, my, 300, 250, 200, 64)) {
				Game.gameState = STATE.Help;
			}
			
			//quit button
			if (mouseOver(mx, my, 300, 350, 200, 64)) {
				System.exit(1);
			}
		} else if (Game.gameState == STATE.Help) {
			//back button
			if (mouseOver(mx, my, 300, 450, 200, 64)) {
				Game.gameState = STATE.Menu;
			}
		} else if (Game.gameState == STATE.Difficulty) {
			//back button
			if (mouseOver(mx, my, 300, 350, 200, 64)) {
				Game.gameState = STATE.Menu;
			}
			
			//Easy button
			if (mouseOver(mx, my, 300, 150, 200, 64)) {
				Game.gameState = STATE.Game;
				handler.clear();
			}
			
			//Hard button
			if (mouseOver(mx, my, 300, 250, 200, 64)) {
				Game.gameState = STATE.HardGame;
				handler.clear();
			}
		} else if (Game.gameState == STATE.Shop) {
			shop = 1;
			
			//Back Button
			if (mouseOver(mx, my, 300, 450, 200, 64)) {
				if (Game.hard == 1) {
					Game.gameState = STATE.HardGame;
				} else {
					Game.gameState = STATE.Game;
				}
			}
			
			//Increase Speed
			if (mouseOver(mx, my, 40, 140, 200, 100)) {
				if (Player.CC >= C1 && SpeLev < 5) {
					SpeLev++;
					Player.CC = Player.CC - C1;
					C1 += 5;
				}
			}
			
			//Increase Health
			if (mouseOver(mx, my, 296, 140, 200, 100)) {
				if (Player.CC >= C2) {
					FullH += 10;
					Player.CC = Player.CC - C2;
					C2 += 5;
				}
			}
			
			//Full Heal
			if (mouseOver(mx, my, 554, 140, 200, 100)) {
				if (Player.CC >= C3 && HUD.HEALTH != FullH) {
					HUD.HEALTH = FullH;
					Player.CC = Player.CC - C3;
					C3 += 5;
				}
			}
			
			//Coin Multiplier
			if (mouseOver(mx, my, 296, 290, 200, 100)) {
				if (Player.CC >= C4 && MultC < 5) {
					MultC++;
					Player.CC = Player.CC - C4;
					C4 += 5;
				}
			}
		} else if (HUD.HEALTH == 0 || HUD.score == 7500) {
			//Play Again or Try Again
			//Resets all variables back to default for when the player starts a new game
			if (mouseOver(mx, my, 250, 450, 300, 68)) {
				HUD.level = 1;
				HUD.score = 0;
				Player.CC = 0;
				C1 = 5;
				C2 = 5;
				C3 = 5;
				C4 = 5;
				Game.end = 1;
				Game.hard = 0;
				SpeLev = 0;
				HeaLev = 0;
				FullH = 100;
				MultC = 1;
				Game.c2 = 1;
				Spawn.clear = 1;
				Game.gameState = STATE.Menu;
			}
		}
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if (mx > x && mx < x + width) {
			if (my > y && my < y + height) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public void tick() {
	}
	
	public void render(Graphics g) {
		//Draws all the graphics for the menu windows
		if (Game.gameState == STATE.Menu) {
			Font menu = new Font("Times New Roman", Font.BOLD, 60);
			g.setFont(menu);
			g.setColor(Color.WHITE);
			g.drawString("Dodger", 305, 100);
			
			Font menu2 = new Font("Times New Roman", Font.BOLD, 50);
			g.setFont(menu2);
			
			g.drawString("Play", 352, 195);
			g.drawString("Help", 352, 295);
			g.drawString("Exit", 358, 400);
			
			g.drawRect(300, 150, 200, 64);
			g.drawRect(300, 250, 200, 64);
			g.drawRect(300, 350, 200, 64);
		} else if (Game.gameState == STATE.Help) {
			Font help = new Font("Times New Roman", Font.BOLD, 60);
			g.setFont(help);
			g.setColor(Color.WHITE);
			
			g.drawString("Help", 340, 70);
			g.drawString("Back", 340, 500);
			g.drawRect(300, 450, 200, 64);
			
			Font help2 = new Font("Times New Roman", Font.BOLD, 40);
			g.setFont(help2);
			
			g.drawString("Use the WASD keys to move player and ", 50, 130);
			g.drawString("dodge enemies.", 270, 170);
			g.drawString("Collect coins to gain score and progress ", 50, 230);
			g.drawString("levels.", 355, 270);
			g.drawString("Press the spacebar to pause the game and ", 50, 330);
			g.drawString("enter the shop.", 280, 370);
			g.drawString("Beat the Boss on level 10 to win the game.", 50, 430);
		} else if (Game.gameState == STATE.Difficulty) {
			Font diff = new Font("Times New Roman", Font.BOLD, 60);
			g.setFont(diff);
			g.setColor(Color.WHITE);
			
			g.drawString("Choose a Difficulty", 180, 70);
			
			Font diff2 = new Font("Times New Roman", Font.BOLD, 50);
			g.setFont(diff2);
			
			g.drawString("Easy", 352, 195);
			g.drawString("Hard", 348, 295);
			g.drawString("Back", 352, 400);
			
			g.drawRect(300, 150, 200, 64);
			g.drawRect(300, 250, 200, 64);
			g.drawRect(300, 350, 200, 64);
		}
	}
}