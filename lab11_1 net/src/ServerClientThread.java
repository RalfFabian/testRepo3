import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

class ServerClientThread extends Thread {
	private Socket socket;

	public ServerClientThread(Socket s) // Constructorul
	{
		this.socket = s;
	}

	public void run() // Thread-ul principal
	{
		BufferedReader netIn = null; // Streamul de la client
		PrintStream netOut = null; // Streamul catre client
		try {
			// construieste streamurile de I/O
			netIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			netOut = new PrintStream(socket.getOutputStream());
			// mesaj de intampinare
			netOut.println("Conexiune reusita");
			// mesaj de conectare reusita
			System.out.println("Client conectat de la: " + socket);
			String client = (socket.getInetAddress()).getHostName();
			String line;
			boolean done = false;
			while (!done) { // Bucla citire linii de la client
				line = netIn.readLine();
				System.out.println(client + ": " + line);
				if (line.trim().equals("exit"))
					done = true;
			} // Iese doar cand clientul termina
		} catch (IOException e) { // Tratarea erorilor de comunicare
			System.err.println(e);
		} finally {
			try {
				netIn.close();
				netOut.close();
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}