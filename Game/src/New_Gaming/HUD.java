package New_Gaming;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class HUD {
	
	Game game;
	public static float HEALTH = 100;
	private float greenValue = 255;
	public static float score = 0;
	public static int level = 1;
	public static boolean lc, lc2 = false;
	
	public void tick() {
		HEALTH = Game.clamp(HEALTH, 0, 1000000000);
		
		greenValue = HEALTH * 2;
		greenValue = Game.clamp(greenValue, 0, 255);
	}
	
	public void render(Graphics g) {
		g.setColor(Color.GRAY);
		
		if (HEALTH == 0 || score == 7500) {
			g.setColor(Color.BLACK);
		}
		
		//Health bar creation and colour transition from green to red
		g.fillRect(15, 15, Menu.FullH * 2, 32);
		g.setColor(new Color(75, (int)greenValue, 0));
		
		if (HEALTH == 0 || score == 7500) {
			g.setColor(Color.BLACK);
		}
		
		g.fillRect(15, 15, (int)HEALTH * 2, 32);
		g.setColor(Color.WHITE);
		
		if (HEALTH == 0 || score == 7500) {
			g.setColor(Color.BLACK);
		}
		
		g.drawRect(15, 15, Menu.FullH * 2, 32);
		
		g.drawString("Level: " + level, 15, 64);
		g.drawString("Score: " + score, 15, 80);
		g.drawString("Coins: " + Player.CC, 15, 96);
		
		//Game Over
		if (HUD.HEALTH == 0) {
			Font end = new Font("Times New Roman", Font.BOLD, 60);
			g.setFont(end);
			g.setColor(Color.WHITE);
			
			g.drawString("GAME OVER", 200, 70);
			g.drawString("Try Again", 268, 500);
			g.drawRect(250, 450, 300, 68);
			
			Font diff2 = new Font("Times New Roman", Font.BOLD, 50);
			g.setFont(diff2);
			
			g.drawString("You lost with a score of: " + HUD.score, 70, 260);
		}
		
		//Beating the game
		if (HUD.score == 7500) {
			String s = "YOU HAVE DEFEATED THE BOSS";
			String s2 = "CONGRATULATIONS";
			Font sFont = new Font("Times New Roman", Font.BOLD, 31);
			Font s2Font = new Font("Times New Roman", Font.BOLD, 31);
			g.setColor(Color.RED);
			g.setFont(sFont);
			g.setFont(s2Font);
			g.drawString(s, Game.WIDTH/2-250, Game.HEIGHT/2-40);
			g.drawString(s2, Game.WIDTH/2-150, Game.HEIGHT/2);
			String s3 = "YOU HAVE DEFEATED THE BOSS";
			String s4 = "CONGRATULATIONS";
			Font s3Font = new Font("Times New Roman", Font.BOLD, 30);
			Font s4Font = new Font("Times New Roman", Font.BOLD, 30);
			g.setColor(Color.GREEN);
			g.setFont(s3Font);
			g.setFont(s4Font);
			g.drawString(s3, Game.WIDTH/2-244, Game.HEIGHT/2-40);
			g.drawString(s4, Game.WIDTH/2-145, Game.HEIGHT/2);
			
			Font end = new Font("Times New Roman", Font.BOLD, 60);
			g.setFont(end);
			g.setColor(Color.WHITE);
			g.drawString("Play Again", 260, 500);
			g.drawRect(250, 450, 300, 68);
		}
	}
}
