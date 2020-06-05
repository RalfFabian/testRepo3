import java.net.*;
import java.io.*;

public class EchoClient {
	public static void main(String[] args) {
		Socket theSocket; // Socketul de comunicare
		String hostname; // numele/adresa serverului
		BufferedReader userIn; // streamul de introducere date
		BufferedReader netIn; // streamul de intrare
		PrintStream netOut; // streamul de iesire
		// pt. numele de server ca argument
		// primul argument e numele serverului
		if (args.length > 0) {
			hostname = args[0];
		} else {
			hostname = "localhost"; // implicit e "localhost"
		}
		try {
			// conectare pe portul 1111 la server
			theSocket = new Socket(hostname, 1111);
			// definirea streamurilor de intrare/iesire
			netIn = new BufferedReader(new InputStreamReader(theSocket.getInputStream()));
			netOut = new PrintStream(theSocket.getOutputStream());
			userIn = new BufferedReader(new InputStreamReader(System.in));
			// linia aceasta se activeaza daca serverul trimite mesaj de
			// intampinare
			System.out.println(netIn.readLine());
			String lineData;
			while (true) { // bucla infinita de comunicare
				lineData = userIn.readLine(); // linia ce va fi trimisa
				if (lineData.equals("exit"))
					break; // iesire daca se introduce "."
				else
					netOut.println(lineData);// linia e trimisa la server
//				lineData = netIn.readLine();
				System.out.println(lineData);
			} // end while
		} // end try
		catch (UnknownHostException e) { // eroare la conectare
			System.out.println("Server inexistent ...");
			System.out.println(e);
		} catch (IOException e) { // eroare de comunicare
			System.out.println(e);
		}
	}// end main
}// end echoClient