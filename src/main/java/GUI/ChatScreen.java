package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.WindowConstants;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import com.swjtu.lang.LANG;

import Core.Main;
import GUI.INTERFACE.Button;
import Network.Sender;
import Translate.Translator;
import User.Player;
import User.Session;
import User.Status;

public class ChatScreen extends JFrame implements MouseListener, MouseMotionListener , ComponentListener {

	private static final long serialVersionUID = 1L;
	
	private ArrayList<Button> interfaces;
	
	private static ChatScreen instance;
	
	private int alpha = 255;
	
	private JFrame frame;
	private JPanel panel;
	private JTextField chat;
	private JTextPane board, login;
	private JScrollPane chat_scroll, login_scroll;
	private String html = "", login_html = "";

	public ChatScreen() {
		open();
	}

	private void open() {
		
		//�X�N���[���p�l����`

		frame = this;
		frame.setSize(1280, 720);
		double w = (double)1280 / (double)frame.getWidth();
		double h = (double)720 / (double)frame.getHeight();
		ScaleFixer.setup(w, h);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		frame.setTitle("Greeting");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("greeting.png")));
		
		interfaces = new ArrayList<>();
		interfaces.add(new Button("ICON/setting.png", this, 87, 100, 60, 60, true) {
			@Override
			public void mouseClick(int mouseX, int mouseY) {
				super.mouseClick(mouseX, mouseY);
				if(!isCover(mouseX, mouseY)) return;
				SettingScreen.open();
			}
		});
		
		interfaces.add(new Button("ICON/trans.png", this, 87, 205, 60, 60, true) {
			@Override
			public void mouseClick(int mouseX, int mouseY) {
				super.mouseClick(mouseX, mouseY);
				if(!isCover(mouseX, mouseY)) return;
				TransScreen.open();
			}
		});
		
		interfaces.add(new Button("ICON/memo.png", this, 89, 310, 60, 60, true) {
			@Override
			public void mouseClick(int mouseX, int mouseY) {
				super.mouseClick(mouseX, mouseY);
				if(!isCover(mouseX, mouseY)) return;
				DetailScreen.open();
			}
		});
		
		interfaces.add(new Button("ICON/info.png", this, 87, 415, 60, 60, true) {
			@Override
			public void mouseClick(int mouseX, int mouseY) {
				super.mouseClick(mouseX, mouseY);
				if(!isCover(mouseX, mouseY)) return;
				InfoScreen.open();
			}
		});
		
		interfaces.add(new Button("ICON/cancel.png", this, 90, 520, 52, 52, true) {
			@Override
			public void mouseClick(int mouseX, int mouseY) {
				super.mouseClick(mouseX, mouseY);
				if(!isCover(mouseX, mouseY)) return;
				try { Sender.sendMessage("::leave");
				} catch (IOException e) {}
				System.exit(0);
			}
		});
		
		panel = new JPanel() {
			private static final long serialVersionUID = 1L;
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("back.jpg")), 0, 0, frame.getWidth() - 15, frame.getHeight() - 38, this);
				for(Button button : interfaces) {
					button.render(g);
				}
				repaint();
			}
		};
		panel.setLayout(null);
		
		board = new JTextPane();
		board.setContentType("text/html");
		board.addHyperlinkListener(new HyperlinkListener() {
			@Override
			public void hyperlinkUpdate(HyperlinkEvent link) {
				if(link.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
					Desktop desk = Desktop.getDesktop();
					try {
						desk.browse(new URI(link.getURL().toString()));
					}catch(Exception e) {}
				} else if (link.getEventType() == HyperlinkEvent.EventType.ENTERED) {
				      board.setToolTipText(link.getURL().toString());
				}
			}
		});
		board.setEditable(false);
		board.setOpaque(false);
		board.setBackground(null);
		board.setBorder(null);
		
		chat_scroll = new JScrollPane(board);
		chat_scroll.setOpaque(false);
		chat_scroll.getViewport().setOpaque(false);
		chat_scroll.setBorder(null);
		panel.add(chat_scroll);
		chat_scroll.setBounds(250, 60, 770, 470);
		
		login = new JTextPane();
		login.setContentType("text/html");
		login.setEditable(false);
		login.setOpaque(false);
		login.setBackground(null);
		login.setBorder(null);
		login_scroll = new JScrollPane(login);
		login_scroll.setOpaque(false);
		login_scroll.getViewport().setOpaque(false);
		login_scroll.setBorder(null);
		login_scroll.setBounds(1080, 50, 150, 585);
		panel.add(login_scroll);
		
		chat = new JTextField(800);
		chat.setText("");
		chat.setForeground(Color.WHITE);
		chat.setFont(new Font("�l�r �S�V�b�N", Font.PLAIN, 16));
		chat.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent event) {}
			@Override
			public void keyPressed(KeyEvent event) {}
			@Override
			public void keyReleased(KeyEvent event) {
				if(alpha == 255) return;
				chat.setText(chat.getText().replace(String.valueOf(event.getKeyChar()), ""));
			}
		});
		chat.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if(Main.status == Status.LOGIN) {
					chat.setText("");
					errorMessage("���O�C�����Ă��܂���I");
					return;}
				if(chat.getText().equals("")) {
					errorMessage("�X�y�[�X����̓��͂ł��I");
					return;}
				ChatEvent(event);
			}
		});
		chat.setOpaque(false);
		chat.setBackground(null);
		chat.setBorder(null);
		panel.add(chat);
		chat.setBounds((frame.getWidth() / 2) - 390, frame.getHeight() - 130, 780, 45);
		
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.setVisible(false);
		instance = this;
		
		//�����_�[�C�x���g��`
		
		addMouseListener(this);
		addMouseMotionListener(this);
		addComponentListener(this);
	}
	
	/*
	 * ���[�U�[�X�e�[�^�X�����_�[�����_�����O
	 */
	
	public void renderStatus() {
		login_html = "";
		login.setText(login_html);
		for(Player player : Main.getPlayers()) {
			login_html += "<BR>" + "<font color=\"#008000\" size=\"4\" face=\"�l�r �S�V�b�N\"><b>" + "* ONLINE" + "</b></font>";
			login_html += "<BR>" + "<font color=\"#87cefa\" size=\"5\" face=\"�l�r �S�V�b�N\">" + getRankText(player) + player.getName() + "<BR>";}
		login.setContentType("text/html");
		login.setText(login_html);
		login.setCaretPosition(login.getDocument().getLength());
	}
	
	/*
	 * �V�X�e���A���[�U�[���b�Z�[�W�����_�����O
	 */
	
	public void renderSystemMsg(String message, String color) {
		html += "<BR>" + "<font size=\"5\" face=\"�l�r �S�V�b�N\" color=\"" + color + "\">" + "[SYSTEM] " + message + "</font>" + "<BR>";
		board.setContentType("text/html");
		board.setText(html);
		board.setCaretPosition(board.getDocument().getLength());
	}
	
	public void renderMessage(Player player) {
		String name = player.getName();
		String id = player.getID();
		String message = player.getMessage();
		
		//�R�}���h���C���`�F�b�N
		
		if(message.startsWith("::login")) {
			if(!Main.getBanned().isEmpty() && Main.getBanned().contains(id)) {
				try { Sender.sendMessage("/msg_ban " + id);
				} catch (IOException e) {}
			return;}
			Main.getPlayers().add(player);
			renderStatus();
			renderSystemMsg(getRankText(player) + name + " ���񂪃`���b�g�ɎQ�����܂����I", "#00bfff");
			if(Main.status == Status.ONLINE) {
				try { Sender.sendMessage("::accept");
				} catch (IOException e) {}}
			return;
		} else if(message.startsWith("::accept")) {
			if(Main.status == Status.USER_ACCEPT) {
				for(Player players : Main.getPlayers()) {
					if(players.getName().equals(name)) {
						Main.status = Status.ONLINE;
						return;}}
				Main.getPlayers().add(player);
				renderStatus();}
			return;
		} else if(message.startsWith("::leave")) {
			for(Player players : Main.getPlayers()) {
				if(players.getName().equals(name)) {
					Main.getPlayers().remove(players);
					break;}}
			renderStatus();
			renderSystemMsg(getRankText(player) + name + " ���񂪃`���b�g����E�ނ��܂����B", "#ffd700");
			return;
		}else if(message.startsWith("::kick") || message.startsWith("::ban")) {
			for(Player players : Main.getPlayers()) {
				if(players.getName().equals(name)) {
					Main.getPlayers().remove(players);
					break;}}
			renderStatus();
			if(message.startsWith("::kick")) {
				renderSystemMsg(getRankText(player) + name + " ���񂪃`���b�g����L�b�N����܂����I", "#ff4500");
			} else if(message.startsWith("::ban")) {
				Main.getBanned().add(id);
				renderSystemMsg(getRankText(player) + name + " ���񂪃`���b�g����o������܂����I", "#ff0000");}
			return;
		} else if(message.contains("/msg_ban")) {
			if(message.equals("/msg_ban " + Session.getUser().getID())) {
				Main.status = Status.BANNED;
				return;}
			return;
		} else if(message.contains("/kick") || message.contains("/ban")) {
			if(message.startsWith("/kick ") || message.startsWith("/ban ")) {
				String arg = message.substring(message.indexOf("/") + 1, message.indexOf(" "));
				String target = message.substring(message.indexOf(" ") + 1, message.length());
				if(target.equals("")) return;
				if(Session.getUser().getName().equals(target)) {
					try { Sender.sendMessage("::" + arg);
					} catch (IOException e) {}
					System.exit(0);
					return;}}return;}
		
		if(Boolean.valueOf(Main.getConfig().getValue("chat_trans"))) {
			message = Translator.trans(message, LANG.values()[Integer.parseInt(Main.getConfig().getValue("c_trans_lang"))], LANG.values()[Integer.parseInt(Main.getConfig().getValue("c_trans_do_lang")) + 1]);}
		
		Calendar cl = Calendar.getInstance();
		SimpleDateFormat form = new SimpleDateFormat("yyyy/MM/dd '('h:mm a')'");
		html += "<BR>" + "<font size=\"5\" face=\"�l�r �S�V�b�N\" color=\"white\">" + getRankText(player) + player.getName() + "</font>" + "<font size=\"3\" face=\"�l�r �S�V�b�N\" color=\"white\"> " + form.format(cl.getTime()) + "</font><BR>";
		html += "<BR>" + "<font size=\"4\" face=\"�l�r �S�V�b�N\" color=\"white\">" + getFormat(message) + "</font><BR>";
		board.setContentType("text/html");
		board.setText(html);
		board.setCaretPosition(board.getDocument().getLength());
	}
	
	/*
	 * �G���[���b�Z�[�W�����_�����O
	 */
	
	public void errorMessage(String message) {
		alpha = 255;
		chat.setForeground(new Color(255, 255, 255, alpha));
		chat.setText(message);
		Timer timer = new Timer(false);
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				chat.setForeground(new Color(255, 255, 255, alpha));
				alpha -= 1;
				if(alpha == 0) {
					timer.cancel();
					timer.purge();
					alpha = 255;
					chat.setText("");
					chat.setForeground(new Color(255, 255, 255, 255));
				}
			}
		};
		timer.schedule(task, 0, 8);
	}
	
	/*
	 * ���b�Z�[�W�t�H�[�}�b�g����
	 */
	
	String getFormat(String message) {
		if(Boolean.valueOf(Main.getConfig().getValue("html_mode"))) return message;
		if(message.contains("http://") || message.contains("https://")) {
			if(!message.contains(" ")) {
				String url = message.substring(message.indexOf("http"), message.length());
				message = "<A HREF=\"" + url + "\">" + url + "</A>";
				return message;
			} else {
				boolean forward = (message.indexOf(" ") <= message.indexOf("http"));
				String url = message.substring(message.indexOf("http"), forward ? message.length() : message.indexOf(" "));
				String msg = message.substring(forward ? 0 : message.indexOf(" "), forward ? message.indexOf(" ") : message.length());
				if(forward) {message = msg + " <A HREF=\"" + url + "\">" + url + "</A>";}else{message = "<A HREF=\"" + url + "\">" + url + "</A>" + msg;}
				return message;
			}
		} else if(message.contains("<") && message.contains(">")) {
			String name = message.substring(message.indexOf("<") + 1, message.indexOf(">"));
			message = message.replace("<" + name + ">", "<span style=\"background-color:#0000cd\">" + name + "</span>");
			if((name.equalsIgnoreCase("everyone") && Boolean.valueOf(Main.getConfig().getValue("all_mension")))
			|| (Session.getUser().getName().equals(name) && Boolean.valueOf(Main.getConfig().getValue("your_mension")))) {MensionScreen.open();}
			return message;
		}else if(message.contains("[") && message.contains("]")) {
			String msg = "<B>" + message.substring(message.indexOf("[") + 1, message.indexOf("]")) + "</B>";
			message = message.replace(message.substring(message.indexOf("["), message.indexOf("]") + 1), msg);
			return message;
		}else if(message.contains("{") && message.contains("}")) {
			String msg = "<I>" + message.substring(message.indexOf("{") + 1, message.indexOf("}")) + "</I>";
			message = message.replace(message.substring(message.indexOf("{"), message.indexOf("}") + 1), msg);
			return message;}
		return message;
	}
	
	String getRankText(Player player) {
		switch(player.getRank()) {
		case "owner":
			return "[<font color=\"#FF0033\">��</font>] ";
		case "mod":
			return "[<font color=\"#00CCFF\">��</font>] ";
		case "design":
			return "[<font color=\"#990099\">��</font>] ";
		default:
			return "";
		}
	}
	
	/*
	 * �`���b�g�C�x���g����
	 */
	
	private void ChatEvent(ActionEvent event) {
		if(alpha < 255) return;
		String message = chat.getText();
		chat.setText("");
		if(Main.isUnicode(message)) {
			if(alpha == 255) {
				errorMessage("Unicode�͔�Ή��ł��I");
				return;}}
		try {
		if(hasPermission(message)) {
			if(Boolean.valueOf(Main.getConfig().getValue("msg_trans"))) {
				message = Translator.trans(message, LANG.values()[Integer.parseInt(Main.getConfig().getValue("m_trans_lang"))], LANG.values()[Integer.parseInt(Main.getConfig().getValue("m_trans_do_lang")) + 1]);}
			Sender.sendMessage(message);}
		} catch (IOException e) {}
	}
	
	/*
	 * �}�E�X�C�x���g����
	 */
	
	@Override
	public void mouseMoved(MouseEvent event) {
		for(Button button : interfaces) {
			button.mouseMove(event.getX(), event.getY() - 30);
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent event) {
		for(Button button : interfaces) {
			button.mouseClick(event.getX(), event.getY() - 30);
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent arg0) {}
	@Override
	public void mouseEntered(MouseEvent arg0) {}
	@Override
	public void mouseExited(MouseEvent arg0) {}
	@Override
	public void mousePressed(MouseEvent arg0) {}
	@Override
	public void mouseReleased(MouseEvent arg0) {}
	
	/*
	 * �R���|�[�l���g�T�C�Y����
	 */
	
	@Override
	public void componentResized(ComponentEvent arg0) {
		double w = (double)frame.getWidth() / (double)1280;
		double h = (double)frame.getHeight() / (double)720;
		ScaleFixer.setup(w, h);
		chat.setBounds((frame.getWidth() / 2) - ScaleFixer.getWidth(390), frame.getHeight() - ScaleFixer.getHeight(130),
				ScaleFixer.getWidth(780), ScaleFixer.getHeight(45));
		chat_scroll.setBounds(ScaleFixer.getWidth(250), ScaleFixer.getHeight(60), 
				ScaleFixer.getWidth(770), ScaleFixer.getHeight(450));
		login_scroll.setBounds(ScaleFixer.getWidth(1080), ScaleFixer.getHeight(50), 
				ScaleFixer.getWidth(150), ScaleFixer.getHeight(585));
		repaint();
	}
	
	@Override
	public void componentHidden(ComponentEvent arg0) {}
	@Override
	public void componentMoved(ComponentEvent arg0) {}
	@Override
	public void componentShown(ComponentEvent arg0) {}
	
	/*
	 * �X�^�b�t��������
	 */
	
	public boolean hasPermission(String message) {
		if(message.contains("/msg_ban")) {return false;}
		if(Main.status == Status.BANNED) {
			errorMessage("���Ȃ��̓`���b�g����o������Ă��܂��I"); return false;}
		if(message.contains("/kick") || message.contains("/ban")) {
			if(!Session.getUser().getRank().equals("user")) {return true;}
			return false;}
		return true;
	}

	/*
	 * �N���X�C���X�^���X
	 */
	
	public static ChatScreen getInstance() {
		return instance;
	}
}