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
 
    	//�}���`�\�P�b�g�̑��M�A�h���X
        String multiCastAddress = "224.0.0.100";
        final int multiCastPort = 10011;
 
        //�}���`�\�P�b�g�T�[�o�[�ɎQ��
        InetAddress group = InetAddress.getByName(multiCastAddress);
        MulticastSocket s = new MulticastSocket(multiCastPort);
        s.joinGroup(group);
 
        //���M�f�[�^�̏���
        Player player = Session.getUser();
        player.setMessage(message);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(player);
        
        byte[] data = baos.toByteArray();
 
        //�f�[�^�𑗐M
        s.send(new DatagramPacket(data, data.length, group, multiCastPort));
    }
}