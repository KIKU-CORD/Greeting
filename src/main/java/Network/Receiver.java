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
    	
        //�}���`�\�P�b�g�̑��M�A�h���X
        String multiCastAddress = "224.0.0.100";
        final int multiCastPort = 10011;
        //�I�u�W�F�N�g�̍ő呗�M��
        final int bufferSize = 1024 * 4;
 
        //�}���`�\�P�b�g�T�[�o�[�ɎQ��
        InetAddress group = InetAddress.getByName(multiCastAddress);
        MulticastSocket s = new MulticastSocket(multiCastPort);
        s.joinGroup(group);
 
        //�}���`�\�P�b�g�̎�M�����҂�
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
