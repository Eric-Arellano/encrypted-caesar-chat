package userInterface.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import static userInterface.networking.LocalHostNameUtility.getLocalHostName;
import static userInterface.networking.LocalHostNameUtility.getLocalIPAddress;

class Server {

	private final int PORT_NUMBER;

	Server(int PORT_NUMBER) {
		this.PORT_NUMBER = PORT_NUMBER;
	}

	void launchConnection() {
		openServer();
		waitForClient();
		try (
				ServerSocket serverSocket =
						new ServerSocket(PORT_NUMBER);
				Socket clientSocket = serverSocket.accept();
				BufferedReader in =
						new BufferedReader(
								new InputStreamReader(clientSocket.getInputStream()))
		) {
			readMessage(in);
			closeConnection();
		} catch (IOException ioException) {
			handleIOException();
		}
	}

	private void openServer() {
		String openingMessage = String.format("Opening server connection on %s (%s)...",
				getLocalHostName(), getLocalIPAddress());
		System.out.println(openingMessage);
	}

	private void waitForClient() {
		System.out.println("Waiting for client...\n");
	}

	private void readMessage(BufferedReader in) throws IOException {
		String receivedMessage = in.readLine();
		System.out.println("Message received!\n");
		System.out.println(receivedMessage);
	}

	private void closeConnection() {
		System.out.println("\nClosing server's connection. Restart app if you'd like to run it again.");
		System.exit(1);
	}

	private void handleIOException() {
		System.out.println("IO Exception from server.");
		System.exit(1);
	}

}
