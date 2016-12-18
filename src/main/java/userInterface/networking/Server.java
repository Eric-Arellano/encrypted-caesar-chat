package userInterface.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

class Server {

	private int portNumber;

	public Server(int portNumber) {
		this.portNumber = portNumber;
	}

	public void launchConnection() {
		System.out.println("Opening server connection...");
		System.out.println("Waiting for client...\n");
		try (
				ServerSocket serverSocket =
						new ServerSocket(portNumber);
				Socket clientSocket = serverSocket.accept();
				BufferedReader in =
						new BufferedReader(
								new InputStreamReader(clientSocket.getInputStream()))
		) {
			String receivedMessage = in.readLine();
			System.out.println(receivedMessage);
			closeConnection();
		} catch (IOException ioException) {
			System.out.println("IO Exception from server.");
			System.exit(1);
		}
	}

	private void closeConnection() {
		System.out.println("\nClosing server's connection. Restart app if you'd like to run it again.");
		System.exit(1);
	}

}
