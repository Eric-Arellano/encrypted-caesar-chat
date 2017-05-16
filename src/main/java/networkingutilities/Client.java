package networkingutilities;

import java.io.IOException;
import java.net.Socket;

class Client implements Connection {

	private Socket socket;
	private final Protocol protocol;

	Client(String HOST_NAME, int PORT_NUMBER) throws IOException {
		this.protocol = new Protocol(ConnectionType.CLIENT);
		openSocket(HOST_NAME, PORT_NUMBER);
	}

	public void listenForMessage() throws IOException {
		protocol.readMessage(socket);
	}

	public void sendMessage(String message) throws IOException {
		protocol.sendMessage(socket, message);
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
