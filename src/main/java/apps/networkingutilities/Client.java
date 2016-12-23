package apps.networkingutilities;

import java.io.IOException;
import java.net.Socket;

class Client implements Connection {

	private final Socket socket;
	private final Protocol protocol;

	private final String messageToSend;

	Client(String HOST_NAME, int PORT_NUMBER) throws IOException {
		socket = new Socket(HOST_NAME, PORT_NUMBER);
		this.protocol = new Protocol(ConnectionType.CLIENT);
		this.messageToSend = null;
		protocol.notifyOpeningConnection();
	}

	Client(String HOST_NAME, int PORT_NUMBER, String messageToSend) throws IOException {
		socket = new Socket(HOST_NAME, PORT_NUMBER);
		this.protocol = new Protocol(ConnectionType.CLIENT);
		this.messageToSend = messageToSend;
		protocol.notifyOpeningConnection();
	}

	public void listenAndProcess() throws IOException {
		protocol.sendMessage(socket, messageToSend);
		protocol.readMessage(socket);
	}

	public void closeConnection() throws IOException {
		protocol.closeConnection();
	}
}
