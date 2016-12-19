package userInterface.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class Client {

	private final String HOST_NAME;
	private final int PORT_NUMBER;
	private final Protocol protocol;

	private final String messageToSend;

	Client(String HOST_NAME, int PORT_NUMBER) {
		this.HOST_NAME = HOST_NAME;
		this.PORT_NUMBER = PORT_NUMBER;
		this.protocol = new Protocol(Protocol.ConnectionType.CLIENT);
		this.messageToSend = null;
	}

	Client(String HOST_NAME, int PORT_NUMBER, String messageToSend) {
		this.HOST_NAME = HOST_NAME;
		this.PORT_NUMBER = PORT_NUMBER;
		this.protocol = new Protocol(Protocol.ConnectionType.CLIENT);
		this.messageToSend = messageToSend;
	}

	void launchConnection() {
		protocol.notifyOpeningConnection();
		try (
				Socket socket =
						new Socket(HOST_NAME, PORT_NUMBER);
				PrintWriter out =
						new PrintWriter(socket.getOutputStream(), true);
				BufferedReader in =
						new BufferedReader(
								new InputStreamReader(socket.getInputStream()))
		) {
			if (messageToSend != null) {
				protocol.sendMessage(out, messageToSend);
			}
			protocol.readMessage(in);
			protocol.closeConnection();
		} catch (IOException ioException) {
			protocol.handleIOException();
		}
	}

}
