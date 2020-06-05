import java.awt.BorderLayout;
import java.net.*;
import java.util.*;
import javax.swing.*;

public class UDPTimeServerGUI {
	public static void main(String args[]) {
		// simple GUI
		///////////////////////////////////
		JFrame f = new JFrame("Server");
		JTextArea ta = new JTextArea();
		f.setLayout(new BorderLayout());
		f.add(new JScrollPane(ta), BorderLayout.CENTER);
		f.setSize(400, 400);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		///////////////////////////////////
		try {
			// socketul ramane activ pe intreaga perioada de functionare
			DatagramSocket udpSocket = new DatagramSocket(54321);
			DatagramPacket udpDataPacket;
			while (true) {
				// asteapta o cerere
				byte data[] = new byte[1024];
				udpDataPacket = new DatagramPacket(data, data.length);
				udpSocket.receive(udpDataPacket);
				// citeste adresa client dar ignora continutul pachetului
				InetAddress address = udpDataPacket.getAddress();
				int port = udpDataPacket.getPort();
				String tmp = "";
				tmp = "Cerere de la " + udpDataPacket.getAddress() + " la portul " + udpDataPacket.getPort();
				// System.out.println(tmp);
				ta.setText(tmp + "\r\n" + ta.getText());
				// construieste un pachet pentru client
				String s = new Date().toString() + "\r\n";
				data = s.getBytes();
				udpDataPacket = new DatagramPacket(data, data.length, address, port);
				udpSocket.send(udpDataPacket);
				// Thread.sleep(1000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}