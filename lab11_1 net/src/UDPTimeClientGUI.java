import java.awt.BorderLayout;
import java.io.IOException;
import java.net.*;
import javax.swing.*;

class UDPTimeClientGUI {
	public static void main(String args[]) {
		// simple GUI
		///////////////////////////////////
		JFrame f = new JFrame("Client");
		JTextArea ta = new JTextArea();
		f.setLayout(new BorderLayout());
		f.add(new JScrollPane(ta), BorderLayout.CENTER);
		f.setSize(400, 400);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		///////////////////////////////////
		try {
			DatagramSocket udpSocket;
			DatagramPacket udpDataPacket;
			while (true) {
				// cerere la server
				udpSocket = new DatagramSocket();
				byte data[] = new byte[1024];
				InetAddress address = InetAddress.getByName("localhost");
				int port = 54321;
				udpDataPacket = new DatagramPacket(data, data.length, address, port);
				udpSocket.send(udpDataPacket);
				// receptioneaza ceva de la server
				udpDataPacket = new DatagramPacket(data, data.length);
				udpSocket.receive(udpDataPacket);
				String tmp = "";
				tmp = "Server" + udpDataPacket.getAddress() + " la portul " + udpDataPacket.getPort() + " da timpul "
						+ (new String(udpDataPacket.getData()).trim());
				// System.out.println(tmp);
				ta.setText(tmp + "\r\n" + ta.getText());
				udpSocket.close();
				Thread.sleep(1000);
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}