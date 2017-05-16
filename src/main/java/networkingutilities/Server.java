package apps.networkingutilities;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

class Server implements Connection {

	private ServerSocket serverSocket;
	private Socket clientSocket;
	private final Protocol protocol;

	Server(int PORT_NUMBER) throws IOException {
		this.protocol = new Protocol(ConnectionType.SERVER);
		openSockets(PORT_NUMBER);
	}

	public void listenForMessage() throws IOException {
		protocol.readMessage(clientSocket);
	}

	public void sendMessage(String message) throws IOException {
		protocol.sendMessage(clientSocket, message);
	}

	public void closeConnection() throws IOException {
		clientSocket.close();
		serverSocket.close();
		protocol.closeConnection();
	}

	private void openSockets(int PORT_NUMBER) throws IOException {
		this.serverSocket = new ServerSocket(PORT_NUMBER);
		protocol.notifyOpeningConnection();
		waitForClient();
		clientSocket = serverSocket.accept();
	}

	private void waitForClient() {
		System.out.println("Waiting for client...");
	}

}
