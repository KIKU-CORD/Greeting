package Network;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import User.Player;
import User.Session;
 
public class Sender {
 
    /**
     * @param args the command line arguments
     */
	
    @SuppressWarnings({ "resource", "deprecation" })
	public static void sendMessage(String message) throws IOException {
 
    	//マルチソケットの送信アドレス
        String multiCastAddress = "224.0.0.100";
        final int multiCastPort = 10011;
 
        //マルチソケットサーバーに参加
        InetAddress group = InetAddress.getByName(multiCastAddress);
        MulticastSocket s = new MulticastSocket(multiCastPort);
        s.joinGroup(group);
 
        //送信データの準備
        Player player = Session.getUser();
        player.setMessage(message);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(player);
        
        byte[] data = baos.toByteArray();
 
        //データを送信
        s.send(new DatagramPacket(data, data.length, group, multiCastPort));
	
	//送るたびにセッションを切断
	s.close();
    }
}
