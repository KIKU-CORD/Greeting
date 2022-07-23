package GUI.INTERFACE;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;

public class Toggle extends Interfaces {
	
	protected boolean select;
	
	private ImageObserver imageOB;
	private Image image;
	
	private int x, y, width, height;
	private boolean cover;
	
	public Toggle(ImageObserver imageOB, int x, int y, int width, int height, boolean select) {
		this.imageOB = imageOB;
		this.image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("ICON/" + String.valueOf(select) + ".png"));
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.select = select;
		this.cover = false;
	}
	
	@Override
	public void render(Graphics g) {
		int xp = x - (cover ? 2: 0);
		int yp = y - (cover ? 2: 0);
		int w = width + (cover ? 4 : 0);
		int h = height + (cover ? 4 : 0);
		this.image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("ICON/" + String.valueOf(select) + ".png"));
		g.drawImage(image, xp, yp, w, h, imageOB);
	}
	
	@Override
	public void mouseMove(int mouseX, int mouseY) {
		if(mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height) {
			cover = true;
		} else {
			cover = false;
		}
	}
	
	@Override
	public void mouseClick(int mouseX, int mouseY) {}
	
	@Override
	public boolean isCover(int mouseX, int mouseY) {
		return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
	}
}
