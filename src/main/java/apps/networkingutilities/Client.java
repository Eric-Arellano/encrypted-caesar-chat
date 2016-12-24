package apps.networkingutilities;

import java.io.IOException;
import java.net.Socket;

class Client implements Connection {

	private Socket socket;
	private final Protocol protocol;

	private final String messageToSend;

	Client(String HOST_NAME, int PORT_NUMBER) throws IOException {
		this.protocol = new Protocol(ConnectionType.CLIENT);
		this.messageToSend = null;
		openSocket(HOST_NAME, PORT_NUMBER);
	}

	Client(String HOST_NAME, int PORT_NUMBER, String messageToSend) throws IOException {
		this.protocol = new Protocol(ConnectionType.CLIENT);
		this.messageToSend = messageToSend;
		openSocket(HOST_NAME, PORT_NUMBER);
	}

	public void listenAndProcess() throws IOException {
		protocol.sendMessage(socket, messageToSend);
		protocol.readMessage(socket);
	}

	public void closeConnection() throws IOException {
		socket.close();
		protocol.closeConnection();
	}

	private void openSocket(String HOST_NAME, int PORT_NUMBER) throws IOException {
		socket = new Socket(HOST_NAME, PORT_NUMBER);
		protocol.notifyOpeningConnection();
	}

}
