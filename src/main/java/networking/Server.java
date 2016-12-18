package networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server {

	String[] args;
	int portNumber;

	public Server(String[] args) {
		this.args = args;
		this.portNumber = Integer.parseInt(args[0]);
	}

	public void launchConnection() {
		try (
				ServerSocket serverSocket =
						new ServerSocket(portNumber);
				Socket clientSocket = serverSocket.accept();
				PrintWriter out =
						new PrintWriter(clientSocket.getOutputStream(), true);
				BufferedReader in =
						new BufferedReader(
								new InputStreamReader(clientSocket.getInputStream()))
		) {
			System.out.println();
		} catch (UnknownHostException unknownHostException) {
			System.out.println();
		} catch (IOException ioException) {
			System.out.println();
		}
	}

}
