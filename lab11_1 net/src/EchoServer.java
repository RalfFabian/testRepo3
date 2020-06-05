import java.io.*;
import java.net.*;

public class EchoServer {
	public static void main(String[] args) {
		ServerSocket servSocket; // Socketul de server
		Socket dataSocket; // Socketul de date
		BufferedReader netIn; // Streamul de la client
		PrintStream netOut; // Streamul catre client
		try {
			// Construirea socketului de server pe portul 1111
			servSocket = new ServerSocket(1111);
			System.out.println("Astept conexiuni de la clienti pe portul: " + servSocket.getLocalPort());
			// asteapta conectarea unui client

			dataSocket = servSocket.accept();
			// dupa conectare construieste streamurile de I/O
			netIn = new BufferedReader(new InputStreamReader(dataSocket.getInputStream()));
			netOut = new PrintStream(dataSocket.getOutputStream());
			// mesaj de intampinare
			netOut.println("Conexiune stabilita cu succes!");
			// mesaj de conectare reusita
			System.out.println("Client conectat de la " + dataSocket);
			String lineData;
			
			while (true) { // Bucla citire linii de la client
				lineData = netIn.readLine();
				System.out.println(lineData);
				netOut.println("OK");
			} // end while //Iese doar cand clientul termina
		} // end try
		catch (Exception e) { // Tratarea erorilor de comunicare
			System.err.println(e);
		}
	}// end main
}// end class