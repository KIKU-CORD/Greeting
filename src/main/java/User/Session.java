package User;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import Core.Main;
import Network.Sender;

public class Session {
	
	private static Player player;
	
	public static void setup(String name, String password) {
		
		//ユーザー情報を作成
		player = new Player();
		player.setName(name);
		player.setID(System.getProperty("user.name"));
		
		//ユーザーの権限設定
		player.setRank("user");
		
		//ユーザーのステータスを変更
		Main.status = Status.USER_ACCEPT;
		
		try { Sender.sendMessage("::login");
		} catch (IOException e) {}
		
		//強制的にオンラインに変更
		TimerTask task = new TimerTask() {
            public void run() {
            	if(Main.status == Status.USER_ACCEPT) {
            		Main.status = Status.ONLINE;
            	}
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 4000);
	}
	
	public static Player getUser() {
		return player;
	}
}
