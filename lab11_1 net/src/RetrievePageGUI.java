import java.net.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.*;

public class RetrievePageGUI extends JFrame {
	// Declare variable
	JButton btnRetrieve;
	JTextArea sourceText;
	JTextField addressField;
	static URL link;
	static URLConnection con;
	String address = "http://www.ulbsibiu.ro";
	InputStream rawInput;

	RetrievePageGUI() {
		// Construire interfata grafica
		super("The URL Application");
		addressField = new JTextField(address, 40);
		getContentPane().setLayout(new BorderLayout(10, 10));
		btnRetrieve = new JButton("Get site");
		btnRetrieve.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clickedRetrieve();
			}
		});
		JPanel cmdPane = new JPanel(new FlowLayout());
		cmdPane.add(btnRetrieve);
		cmdPane.add(addressField);
		getContentPane().add(cmdPane, BorderLayout.NORTH);
		sourceText = new JTextArea(30, 50);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		getContentPane().add(new JScrollPane(sourceText), BorderLayout.CENTER);
		pack();
		setVisible(true);
	}

	public void clickedRetrieve() {
		// Verifica daca resursa este accesibila
		try {
			link = addressField.getText().equals("") ? new URL(address)

					: new URL(addressField.getText());
		} catch (MalformedURLException e) {
			sourceText.setText("ERROR: URL acces. " + e);
		} // Obtine informatia
		try {
			con = link.openConnection();
			rawInput = con.getInputStream();
			BufferedReader in = new BufferedReader(new InputStreamReader(rawInput));
			// Citeste si afiseaza date.
			String s = "";
			StringBuilder text = new StringBuilder();
			while ((s = in.readLine()) != null) {
				text.append(s + "\r\n");
			}
			sourceText.setText(text.toString());
			sourceText.setCaretPosition(0);
		} catch (IOException e) {
			sourceText.setText("ERROR: IO Exception. " + e);
		}
	}

	public static void main(String argv[]) {
		new RetrievePageGUI();
	}
}