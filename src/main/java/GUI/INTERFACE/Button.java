package GUI.INTERFACE;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;

import GUI.ScaleFixer;

public class Button extends Interfaces {
	
	private ImageObserver imageOB;
	private Image image;
	
	private int x, y, width, height;
	private boolean scale, cover;
	
	public Button(String path, ImageObserver imageOB, int x, int y, int width, int height, boolean scale) {
		this.imageOB = imageOB;
		this.image = Toolkit.getDefaultToolkit().getImage(getClass().getResource(path));
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.scale = scale;
		this.cover = false;
	}
	
	@Override
	public void render(Graphics g) {
		int xp = (scale ? ScaleFixer.getWidth(x) : x) - (cover ? 2: 0);
		int yp = (scale ? ScaleFixer.getHeight(y) : y) - (cover ? 2: 0);
		int w = (scale ? ScaleFixer.getWidth(width) : width) + (cover ? 4 : 0);
		int h = (scale ? ScaleFixer.getWidth(width) : width) + (cover ? 4 : 0);
		g.drawImage(image, xp, yp, w, h, imageOB);
	}
	
	@Override
	public void mouseMove(int mouseX, int mouseY) {
		if(isCover(mouseX, mouseY)) {
			cover = true;
		} else {
			cover = false;
		}
	}
	
	@Override
	public void mouseClick(int mouseX, int mouseY) {}
	
	@Override
	public boolean isCover(int mouseX, int mouseY) {
		if(scale) {
			return mouseX >= ScaleFixer.getWidth(x) && mouseX <= ScaleFixer.getWidth(x) + ScaleFixer.getWidth(width) 
			&& mouseY >= ScaleFixer.getHeight(y) && mouseY <= ScaleFixer.getHeight(y) + ScaleFixer.getHeight(height);
		} else {
			return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
		}
	}
}
