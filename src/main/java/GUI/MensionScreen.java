package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import GUI.INTERFACE.Button;

public class MensionScreen extends JFrame implements MouseMotionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	
	private static MensionScreen menscreen;
	private JPanel panel;
	
	private Button interfaces;

	public MensionScreen() {
		
		//スクリーンパネル定義
		
		setSize(500, 225);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setAlwaysOnTop(true);
		setUndecorated(true);
		
		setBackground(new Color(0, 0, 0, 0));
		getRootPane().setBorder(null);
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("greeting.png")));
		
		interfaces = new Button("ICON/cancel.png", menscreen, 420, 20, 40, 40, false) {
			@Override
			public void mouseClick(int mouseX, int mouseY) {
				super.mouseClick(mouseX, mouseY);
				if(!isCover(mouseX, mouseY)) return;
				close();
			}
		};
		
		panel = new JPanel() {
			private static final long serialVersionUID = 1L;
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("mension.png")), 0, 0, this.getWidth(), this.getHeight(), this);
				interfaces.render(g);
				repaint();
			}
		};
		panel.setBackground(null);
		panel.setBorder(null);
		panel.setOpaque(false);
		panel.setLayout(null);
		
		getContentPane().add(panel, BorderLayout.CENTER);
		
		//レンダーイベント定義
		
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	/*
	 * マウスイベント制御
	 */
	
	@Override
	public void mouseMoved(MouseEvent event) {
		interfaces.mouseMove(event.getX(), event.getY());
	}
	
	@Override
	public void mouseClicked(MouseEvent event) {
		interfaces.mouseClick(event.getX(), event.getY());
	}
	
	@Override
	public void mouseDragged(MouseEvent event) {}
	@Override
	public void mouseEntered(MouseEvent arg0) {}
	@Override
	public void mouseExited(MouseEvent arg0) {}
	@Override
	public void mousePressed(MouseEvent arg0) {}
	@Override
	public void mouseReleased(MouseEvent arg0) {}
	
	/*
	 * スクリーン制御
	 */
	
	public static void launch() {
		menscreen = new MensionScreen();
		menscreen.setVisible(false);
	}

	public static void open() {
		menscreen.setVisible(true);
	}
	
	public static void close() {
		menscreen.setVisible(false);
	}

}