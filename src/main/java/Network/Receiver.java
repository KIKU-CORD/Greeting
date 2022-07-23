package Network;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import GUI.ChatScreen;
import User.Player;
 
public class Receiver {
 
    /**
     * @param args the command line arguments
     */
	
    @SuppressWarnings({ "resource", "deprecation" })
	public static Runnable lunch() throws IOException {
    	
        //マルチソケットの送信アドレス
        String multiCastAddress = "224.0.0.100";
        final int multiCastPort = 10011;
        //オブジェクトの最大送信量
        final int bufferSize = 1024 * 4;
 
        //マルチソケットサーバーに参加
        InetAddress group = InetAddress.getByName(multiCastAddress);
        MulticastSocket s = new MulticastSocket(multiCastPort);
        s.joinGroup(group);
 
        //マルチソケットの受信応答待ち
        while (true) {
 
            byte[] buffer = new byte[bufferSize];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            s.receive(packet);
 
            ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
            ObjectInputStream ois = new ObjectInputStream(bais);
            try {
                Object readObject = ois.readObject();
                if (readObject instanceof Player) {
                    Player player = (Player) readObject;
                    ChatScreen.getInstance().renderMessage(player);
                }
            } catch (Exception e) {}
        }
    }
}
