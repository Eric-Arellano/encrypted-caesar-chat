package userInterface.networking;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

class Client {

	private final String HOST_NAME;
	private final int PORT_NUMBER;
	private final String messageToSend;
	private final Protocol protocol;

	Client(String HOST_NAME, int PORT_NUMBER, String messageToSend) {
		this.HOST_NAME = HOST_NAME;
		this.PORT_NUMBER = PORT_NUMBER;
		this.messageToSend = messageToSend;
		this.protocol = new Protocol(Protocol.ConnectionType.CLIENT);
	}

	void launchConnection() {
		protocol.notifyOpeningConnection();
		try (
				Socket encryptionSocket =
						new Socket(HOST_NAME, PORT_NUMBER);
				PrintWriter out =
						new PrintWriter(encryptionSocket.getOutputStream(), true)
		) {
			protocol.sendMessage(out, messageToSend);
			protocol.closeConnection();
		} catch (IOException ioException) {
			protocol.handleIOException();
		}
	}

}
