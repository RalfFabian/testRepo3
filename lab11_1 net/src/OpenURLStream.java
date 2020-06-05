import java.net.*;
import java.io.*;

class OpenURLStream {
	public static void main(String args[]) {
		try {
			URL link = new URL("https://www.ulbsibiu.ro");
			
			if (args.length > 0)
				link = new URL(args[0]);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(link.openStream()));
			
			String tmp = null;
			while ((tmp = in.readLine()) != null)
				System.out.println(tmp);
			
			in.close();
			
		} catch (MalformedURLException e) {
			System.out.println("MalformedURLException: " + e);
		} catch (IOException e) {
			System.out.println("IOException: " + e);
		}
	}
}