package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import GUI.INTERFACE.Button;

public class InfoScreen extends JFrame implements MouseMotionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	
	private static InfoScreen infoscreen;
	private JPanel panel;
	
	private ArrayList<Button> interfaces;

	public InfoScreen() {
		
		//スクリーンパネル定義
		
		setSize(500, 458);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setAlwaysOnTop(true);
		setUndecorated(true);
		
		setBackground(new Color(0, 0, 0, 0));
		getRootPane().setBorder(null);
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("greeting.png")));
		
		interfaces = new ArrayList<>();
		interfaces.add(new Button("ICON/cancel.png", infoscreen, 420, 20, 40, 40, false) {
			@Override
			public void mouseClick(int mouseX, int mouseY) {
				super.mouseClick(mouseX, mouseY);
				if(!isCover(mouseX, mouseY)) return;
				close();
			}
		});
		
		panel = new JPanel() {
			private static final long serialVersionUID = 1L;
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("info.png")), 0, 0, this.getWidth(), this.getHeight(), this);
				for(Button button : interfaces) {
					button.render(g);
				}
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
		for(Button button : interfaces) {
			button.mouseMove(event.getX(), event.getY());
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent event) {
		for(Button button : interfaces) {
			button.mouseClick(event.getX(), event.getY());
		}
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
		infoscreen = new InfoScreen();
		infoscreen.setVisible(false);
	}

	public static void open() {
		infoscreen.setVisible(true);
	}
	
	public static void close() {
		infoscreen.setVisible(false);
	}

}