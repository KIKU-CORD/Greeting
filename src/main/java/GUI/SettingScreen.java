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

import Core.Main;
import GUI.INTERFACE.Button;
import GUI.INTERFACE.Interfaces;
import GUI.INTERFACE.Toggle;

public class SettingScreen extends JFrame implements MouseMotionListener, MouseListener {
	
	private static final long serialVersionUID = 1L;
	
	private static SettingScreen settingscreen;
	private JPanel panel;
	
	private ArrayList<Interfaces> interfaces;

	public SettingScreen() {
		
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
		interfaces.add(new Button("ICON/cancel.png", settingscreen, 420, 20, 40, 40, false) {
			@Override
			public void mouseClick(int mouseX, int mouseY) {
				super.mouseClick(mouseX, mouseY);
				if(!isCover(mouseX, mouseY)) return;
				close();
			}
		});
		
		interfaces.add(new Toggle(this, 320, 165, 60, 60, Boolean.valueOf(Main.getConfig().getValue("chat_trans"))) {
			@Override
			public void mouseClick(int mouseX, int mouseY) {
				super.mouseClick(mouseX, mouseY);
				if(!isCover(mouseX, mouseY)) return;
				select = !select;
				Main.getConfig().setValue("chat_trans", String.valueOf(select));
			}
		});
		
		interfaces.add(new Toggle(this, 320, 215, 60, 60, Boolean.valueOf(Main.getConfig().getValue("msg_trans"))) {
			@Override
			public void mouseClick(int mouseX, int mouseY) {
				super.mouseClick(mouseX, mouseY);
				if(!isCover(mouseX, mouseY)) return;
				select = !select;
				Main.getConfig().setValue("msg_trans", String.valueOf(select));
			}
		});
		
		interfaces.add(new Toggle(this, 320, 265, 60, 60, Boolean.valueOf(Main.getConfig().getValue("your_mension"))) {
			@Override
			public void mouseClick(int mouseX, int mouseY) {
				super.mouseClick(mouseX, mouseY);
				if(!isCover(mouseX, mouseY)) return;
				select = !select;
				Main.getConfig().setValue("your_mension", String.valueOf(select));
			}
		});
		
		interfaces.add(new Toggle(this, 320, 315, 60, 60, Boolean.valueOf(Main.getConfig().getValue("all_mension"))) {
			@Override
			public void mouseClick(int mouseX, int mouseY) {
				super.mouseClick(mouseX, mouseY);
				if(!isCover(mouseX, mouseY)) return;
				select = !select;
				Main.getConfig().setValue("all_mension", String.valueOf(select));
			}
		});
		
		interfaces.add(new Toggle(this, 320, 365, 60, 60, Boolean.valueOf(Main.getConfig().getValue("html_mode"))) {
			@Override
			public void mouseClick(int mouseX, int mouseY) {
				super.mouseClick(mouseX, mouseY);
				if(!isCover(mouseX, mouseY)) return;
				select = !select;
				Main.getConfig().setValue("html_mode", String.valueOf(select));
			}
		});
		
		panel = new JPanel() {
			private static final long serialVersionUID = 1L;
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("setting.png")), 0, 0, this.getWidth(), this.getHeight(), this);
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

	public static void launcher() {
		settingscreen = new SettingScreen();
		settingscreen.setVisible(false);
	}
	
	public static void open() {
		settingscreen.setVisible(true);
	}
	
	public static void close() {
		settingscreen.setVisible(false);
	}
}