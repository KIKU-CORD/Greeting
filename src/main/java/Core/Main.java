package Core;
import java.awt.EventQueue;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import GUI.ChatScreen;
import GUI.DetailScreen;
import GUI.InfoScreen;
import GUI.LoadScreen;
import GUI.LoginScreen;
import GUI.MensionScreen;
import GUI.SettingScreen;
import GUI.TransScreen;
import Network.Receiver;
import User.Player;
import User.Setting;
import User.Status;

public class Main {
	
	public static Status status;
	
	private static Setting setting;
	
	private static ArrayList<Player> player;
	
	private static ArrayList<String> banned;
	
	/*
	 * ソフトウェア起動
	 */
	
	public static void main(String args[]) {
		player = new ArrayList<>();
		banned = new ArrayList<>();
		setting = new Setting();
		LoadScreen.launch();
		try {Thread.sleep(6000);
		} catch (InterruptedException e) {}
		LoadScreen.close();
		LoginScreen.launch();
		SettingScreen.launcher();
		TransScreen.launcher();
		DetailScreen.launcher();
		InfoScreen.launch();
		MensionScreen.launch();
		EventQueue.invokeLater(new Runnable() {
		public void run() {
				new ChatScreen();}});
		try {
			new Thread(Receiver.lunch()).start();
		} catch (IOException error) {}
	}
	
	/*
	 * Unicodeチェック
	 */
	
	public static boolean isUnicode(String message) {
		try {
			byte[] bytes = message.getBytes("EUC_JP");
			String newbytes = new String(bytes, "EUC_JP");
			if(!message.equals(newbytes)) {return true;}
		} catch (UnsupportedEncodingException e) {}
		return false;
	}
	
	public static ArrayList<Player> getPlayers() {
		return player;
	}
	
	public static ArrayList<String> getBanned() {
		return banned;
	}
	
	public static Setting getConfig() {
		return setting;
	}
}
