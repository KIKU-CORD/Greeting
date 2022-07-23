package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

import GUI.INTERFACE.Button;
import GUI.INTERFACE.Interfaces;

public class DetailScreen extends JFrame implements MouseMotionListener, MouseListener {
	
	private static final long serialVersionUID = 1L;
	
	private static DetailScreen detailscreen;
	private JLabel label;
	private JScrollPane ex_scroll;
	private JPanel panel;
	
	private ArrayList<Interfaces> interfaces;

	public DetailScreen() {
		
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
		interfaces.add(new Button("ICON/cancel.png", detailscreen, 420, 20, 40, 40, false) {
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
				g.drawImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("detail.png")), 0, 0, this.getWidth(), this.getHeight(), this);
				for(Interfaces button : interfaces) {
					button.render(g);
				}
				repaint();
			}
		};
		panel.setBackground(null);
		panel.setBorder(null);
		panel.setOpaque(false);
		panel.setLayout(null);
		
		ImageIcon icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("explan.png")));
		
		label = new JLabel();
		label.setIcon(icon);
		
		ex_scroll = new JScrollPane(label);
		ex_scroll.setOpaque(false);
		ex_scroll.getViewport().setOpaque(false);
		ex_scroll.setBorder(null);
		panel.add(ex_scroll);
		ex_scroll.setBounds(60, 165, 400, 100);
		
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
		for(Interfaces button : interfaces) {
			button.mouseMove(event.getX(), event.getY());
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent event) {
		for(Interfaces button : interfaces) {
			button.mouseClick(event.getX(), event.getY());
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent event) {}
	@Override
	public void mouseEntered(MouseEvent arg0) {	}
	@Override
	public void mouseExited(MouseEvent arg0) {}
	@Override
	public void mousePressed(MouseEvent arg0) {}
	@Override
	public void mouseReleased(MouseEvent arg0) {}
	
	/*
	 * スクリーン制御
	 */

	public static void launcher() {
		detailscreen = new DetailScreen();
		detailscreen.setVisible(false);
	}
	
	public static void open() {
		detailscreen.setVisible(true);
	}
	
	public static void close() {
		detailscreen.setVisible(false);
	}
}