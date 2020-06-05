import java.net.ServerSocket;
import java.net.Socket;

public class EchoServerThreaded {
	public static void main(String[] args) {
		ServerSocket servSocket; // Socketul de server
		Socket dataSocket; // Socketul de date
		ServerClientThread st;
		try {
			// Construirea socketului de server pe portul 1111
			servSocket = new ServerSocket(1111);
			System.out.println("Astept clienti pe portul: " + servSocket.getLocalPort());
			// asteapta conectarea clientilor si construieste thread-ul
			while (true) {
				dataSocket = servSocket.accept();
				st = new ServerClientThread(dataSocket);
				st.start(); // porneste thread-ul client
			}
		} catch (Exception e) { // Tratarea erorilor obligatorie
			System.err.println(e);
		}
	}
}