package apps.networkingutilities;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ServerSocket;
import java.net.Socket;

class Server {

	private final int PORT_NUMBER;
	private final Protocol protocol;

	private final String messageToSend;

	Server(int PORT_NUMBER) {
		this.PORT_NUMBER = PORT_NUMBER;
		this.protocol = new Protocol(Protocol.ConnectionType.SERVER);
		this.messageToSend = null;
	}

	Server(int PORT_NUMBER, String messageToSend) {
		this.PORT_NUMBER = PORT_NUMBER;
		this.protocol = new Protocol(Protocol.ConnectionType.SERVER);
		this.messageToSend = messageToSend;
	}

	void launchConnection() {
		protocol.notifyOpeningConnection();
		waitForClient();
		try (
				ServerSocket serverSocket =
						new ServerSocket(PORT_NUMBER);
				Socket clientSocket = serverSocket.accept()
		) {
			protocol.setTimeout(clientSocket);
			protocol.setTimeout(serverSocket);
			protocol.sendMessage(clientSocket, messageToSend);
			protocol.readMessage(clientSocket);
			protocol.closeConnection();
		} catch (InterruptedIOException timeoutException) {
			protocol.handleTimeoutException();
		} catch (IOException ioException) {
			protocol.handleIOException();
		}
	}

	void launchConcurrentConnection() {
		protocol.notifyOpeningConnection();
		waitForClient();
		try (
				ServerSocket serverSocket =
						new ServerSocket(PORT_NUMBER);
				Socket clientSocket = serverSocket.accept()
		) {
			while (true) {
				protocol.sendMessage(clientSocket, messageToSend);
				protocol.readMessage(clientSocket);
			}
		} catch (InterruptedIOException timeoutException) {
			protocol.handleTimeoutException();
		} catch (IOException ioException) {
			protocol.handleIOException();
		}
	}

	private void waitForClient() {
		System.out.println("Waiting for client...");
	}

}
