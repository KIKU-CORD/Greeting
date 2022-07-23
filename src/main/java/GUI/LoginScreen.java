package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import Core.Main;
import GUI.INTERFACE.Button;
import User.Session;

public class LoginScreen extends JFrame implements MouseMotionListener, MouseListener {
	
	private static final long serialVersionUID = 1L;
	
	private static LoginScreen loginscreen;
	private JTextField name;
	private JTextField pass;
	private JPanel panel;
	
	private Button interfaces;

	public LoginScreen() {
		
		//スクリーンパネル定義
		
		setSize(500, 368);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setAlwaysOnTop(true);
		setUndecorated(true);
		
		setBackground(new Color(0, 0, 0, 0));
		getRootPane().setBorder(null);
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("greeting.png")));
		
		interfaces = new Button("ICON/login.png", this, 215, 280, 60, 60, false) {
			@Override
			public void mouseClick(int mouseX, int mouseY) {
				super.mouseClick(mouseX, mouseY);
				if(!isCover(mouseX, mouseY)) return;
				if(Main.isUnicode(name.getText())) {
					name.setText("Unicodeは非対応です！");
					return;}
				if(Main.isUnicode(pass.getText())) {
					pass.setText("Unicodeは非対応です！");
					return;}
				Session.setup(name.getText(), pass.getText());
				close();
			}
		};
		
		panel = new JPanel() {
			private static final long serialVersionUID = 1L;
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("login.png")), 0, 0, this.getWidth(), this.getHeight(), this);
				interfaces.render(g);
				repaint();
			}
		};
		panel.setBackground(null);
		panel.setOpaque(false);
		panel.setBorder(null);
		panel.setLayout(null);
		name = new JTextField(50);
		pass = new JTextField(50);

		name.setText("Name");
		name.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 16));
		name.setForeground(Color.WHITE);
		name.setOpaque(false);
		name.setBackground(null);
		name.setBorder(null);
		panel.add(name);
		name.setBounds(65, 144, 370, 26);
		
		pass.setText("Password");
		pass.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 16));
		pass.setForeground(Color.WHITE);
		pass.setOpaque(false);
		pass.setBackground(null);
		pass.setBorder(null);
		panel.add(pass);
		pass.setBounds(65, 222, 370, 26);
		
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
		loginscreen = new LoginScreen();
		loginscreen.setVisible(true);
	}
	
	public static void close() {
		loginscreen.setVisible(false);
		loginscreen = null;
		ChatScreen.getInstance().setVisible(true);
	}
}