package GUI;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class LoadScreen extends Frame {

	private static final long serialVersionUID = 1L;
	
	private static LoadScreen loadscreen;
	private Image img = null;
	
	public LoadScreen() {
		
		//スクリーンパネル定義
		
		setSize(220, 290);
		setLocationRelativeTo(null);
		setUndecorated(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("greeting.png")));
		img = Toolkit.getDefaultToolkit().getImage(getClass().getResource("load.gif"));
	}
	
	/*
	 * GIFファイルレンダー
	 */
	
	@Override
    public void update(Graphics g) {
    	paint(g);
    }
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(img, -210, -95, this);
	}
	
	/*
	 * スクリーン制御
	 */
	
	public static void launch() {
		loadscreen = new LoadScreen();
		loadscreen.setVisible(true);
	}
	
	public static void close() {
		loadscreen.setVisible(false);
		loadscreen = null;
	}
}