package GUI.INTERFACE;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.util.List;

import GUI.ScaleFixer;

public class Select extends Interfaces {
	
	protected String select;
	
	protected List<String> list;
	
	protected int index;
	
	private ImageObserver imageOB;
	private Image image;
	
	private int x, y, width, height;
	private boolean cover;
	
	public Select(List<String> list, ImageObserver imageOB, int x, int y, int width, int height, int index) {
		this.list = list;
		this.imageOB = imageOB;
		this.image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("ICON/select.png"));
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.index = index;
		this.cover = false;
	}
	
	@Override
	public void render(Graphics g) {
		int xp = x - (cover ? 2: 0);
		int yp = y - (cover ? 2: 0);
		int w = width + (cover ? 4 : 0);
		int h = height + (cover ? 4 : 0);
		String selected = "< " + list.get(index) + " >";
		this.image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("ICON/select.png"));
		g.drawImage(image, xp, yp, w, h, imageOB);
		g.drawString(selected, x + (width / 2) - (g.getFontMetrics().stringWidth(selected) / 2), y + (height / 2) + ScaleFixer.getWidth(4));
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
