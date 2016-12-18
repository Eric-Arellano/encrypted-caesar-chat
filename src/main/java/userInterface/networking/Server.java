package userInterface.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

class Server {

	private final int portNumber;

	Server(int portNumber) {
		this.portNumber = portNumber;
	}

	void launchConnection() {
		openServer();
		waitForClient();
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

	private void openServer() {
		String openingMessage = String.format("Opening server connection on %s...", getLocalHostName());
		System.out.println(openingMessage);
	}

	private String getLocalHostName() {
		String hostName = "";
		try {
			hostName = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException unknownHostException) {
			System.out.println("Host cannot be resolved.");
			System.exit(1);
		}
		return hostName;
	}

	private void waitForClient() {
		System.out.println("Waiting for client...\n");
	}

	private void closeConnection() {
		System.out.println("\nClosing server's connection. Restart app if you'd like to run it again.");
		System.exit(1);
	}

}
