package User;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import Core.Main;
import Network.Sender;

public class Session {
	
	private static Player player;
	
	public static void setup(String name, String password) {
		
		//���[�U�[�����쐬
		player = new Player();
		player.setName(name);
		player.setID(System.getProperty("user.name"));
		
		//���[�U�[�̌����ݒ�
		if(password.equals("chatowner.0064!?")) {
			player.setRank("owner");
		} else if(password.equals("chatmod.0064!?")) {
			player.setRank("mod");
		} else if(password.equals("chatdesign.0064!?")) {
			player.setRank("design");
		} else { player.setRank("user");}
		
		//���[�U�[�̃X�e�[�^�X��ύX
		Main.status = Status.USER_ACCEPT;
		
		try { Sender.sendMessage("::login");
		} catch (IOException e) {}
		
		//�����I�ɃI�����C���ɕύX
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
