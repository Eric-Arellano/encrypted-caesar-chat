package userInterface.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

class Server {

	private final int PORT_NUMBER;
	private final Protocol protocol;

	Server(int PORT_NUMBER) {
		this.PORT_NUMBER = PORT_NUMBER;
		protocol = new Protocol(Protocol.ConnectionType.SERVER);
	}

	void launchConnection() {
		protocol.notifyOpeningConnection();
		waitForClient();
		try (
				ServerSocket serverSocket =
						new ServerSocket(PORT_NUMBER);
				Socket clientSocket = serverSocket.accept();
				BufferedReader in =
						new BufferedReader(
								new InputStreamReader(clientSocket.getInputStream()))
		) {
			protocol.readMessage(in);
			protocol.closeConnection();
		} catch (IOException ioException) {
			protocol.handleIOException();
		}
	}

	private void waitForClient() {
		System.out.println("Waiting for client...\n");
	}

}
