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
import GUI.INTERFACE.Select;
import Translate.Translator;

public class TransScreen extends JFrame implements MouseMotionListener, MouseListener {
	
	private static final long serialVersionUID = 1L;
	
	private static TransScreen transscreen;
	private JPanel panel;
	
	private ArrayList<Interfaces> interfaces;

	public TransScreen() {
		
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
		interfaces.add(new Button("ICON/cancel.png", transscreen, 420, 20, 40, 40, false) {
			@Override
			public void mouseClick(int mouseX, int mouseY) {
				super.mouseClick(mouseX, mouseY);
				if(!isCover(mouseX, mouseY)) return;
				close();
			}
		});
		
		interfaces.add(new Select(Translator.lang, this, 300, 200, 95, 60, Integer.parseInt(Main.getConfig().getValue("c_trans_lang"))) {
			@Override
			public void mouseClick(int mouseX, int mouseY) {
				super.mouseClick(mouseX, mouseY);
				if(!isCover(mouseX, mouseY)) return;
				if((list.size() - 1) == index) {index = 0;} else {index++;}
				select = list.get(index);
				Main.getConfig().setValue("c_trans_lang", String.valueOf(index));
			}
		});
		
		interfaces.add(new Select(Translator.do_lang, this, 300, 245, 95, 60, Integer.parseInt(Main.getConfig().getValue("c_trans_do_lang"))) {
			@Override
			public void mouseClick(int mouseX, int mouseY) {
				super.mouseClick(mouseX, mouseY);
				if(!isCover(mouseX, mouseY)) return;
				if((list.size() - 1) == index) {index = 0;} else {index++;}
				select = list.get(index);
				Main.getConfig().setValue("c_trans_do_lang", String.valueOf(index));
			}
		});
		
		interfaces.add(new Select(Translator.lang, this, 300, 325, 95, 60, Integer.parseInt(Main.getConfig().getValue("m_trans_lang"))) {
			@Override
			public void mouseClick(int mouseX, int mouseY) {
				super.mouseClick(mouseX, mouseY);
				if(!isCover(mouseX, mouseY)) return;
				if((list.size() - 1) == index) {index = 0;} else {index++;}
				select = list.get(index);
				Main.getConfig().setValue("m_trans_lang", String.valueOf(index));
			}
		});
		
		interfaces.add(new Select(Translator.do_lang, this, 300, 370, 95, 60, Integer.parseInt(Main.getConfig().getValue("m_trans_do_lang"))) {
			@Override
			public void mouseClick(int mouseX, int mouseY) {
				super.mouseClick(mouseX, mouseY);
				if(!isCover(mouseX, mouseY)) return;
				if((list.size() - 1) == index) {index = 0;} else {index++;}
				select = list.get(index);
				Main.getConfig().setValue("m_trans_do_lang", String.valueOf(index));
			}
		});
		
		panel = new JPanel() {
			private static final long serialVersionUID = 1L;
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("translate.png")), 0, 0, this.getWidth(), this.getHeight(), this);
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
		transscreen = new TransScreen();
		transscreen.setVisible(false);
	}
	
	public static void open() {
		transscreen.setVisible(true);
	}
	
	public static void close() {
		transscreen.setVisible(false);
	}
}