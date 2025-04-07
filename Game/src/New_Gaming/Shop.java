package New_Gaming;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;

public class Shop extends MouseAdapter{
	
	Handler handler;
	
	public Shop(Handler handler) {
		this.handler = handler;
	}
	
	//All shop text and rectangles
	public void render(Graphics g) {
		Font shop = new Font("Times New Roman", Font.BOLD, 60);
		g.setFont(shop);
		g.setColor(Color.WHITE);
		
		g.drawString("Shop", 340, 70);
		g.drawString("Back", 340, 500);
		g.drawRect(300, 450, 200, 64);
		
		Font shop2 = new Font("Times New Roman", Font.BOLD, 40);
		g.setFont(shop2);
		g.drawString("Number of Coins: " + Player.CC, 230, 130);
		
		Font shop3 = new Font("Times New Roman", Font.BOLD, 25);
		g.setFont(shop3);
		
		g.drawString("Increase Speed", 60, 190);
		if (Menu.SpeLev < 5 ) {
			g.drawString("Cost: " + Menu.C1, 105, 220);
		} else {
			g.drawString("Max Level", 85, 220);
		}
		g.drawRect(40, 160, 200, 100);
		
		g.drawString("Increase Health", 312, 190);
		g.drawString("Cost: " + Menu.C2, 360, 220);
		g.drawRect(296, 160, 200, 100);
		
		g.drawString("Full Heal", 605, 190);
		g.drawString("Cost: " + Menu.C3, 620, 220);
		g.drawRect(554, 160, 200, 100);
		
		g.drawString("Coin Multiplier", 312, 335);
		if (Menu.MultC < 5 ) {
			g.drawString("Cost: " + Menu.C4, 360, 365);
		} else {
			g.drawString("Max Level", 340, 365);
		}
		g.drawRect(296, 305, 200, 100);
		
		Font shop4 = new Font("Times New Roman", Font.BOLD, 20);
		g.setFont(shop4);
		
		g.drawString("Press 1 to activate", 65, 245);
		g.drawString("Press 2 to activate", 320, 245);
		g.drawString("Press 3 to activate", 580, 245);
		g.drawString("Press 4 to activate", 320, 390);
	}
}
