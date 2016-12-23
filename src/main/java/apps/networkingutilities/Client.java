package apps.networkingutilities;

import java.io.IOException;
import java.io.InterruptedIOException;
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
						new Socket(HOST_NAME, PORT_NUMBER)
		) {
			protocol.setTimeout(socket);
			protocol.sendMessage(socket, messageToSend);
			protocol.readMessage(socket);
			protocol.closeConnection();
		} catch (InterruptedIOException timeoutException) {
			protocol.handleTimeoutException();
		} catch (IOException ioException) {
			protocol.handleIOException();
		}
	}

	void launchConcurrentConnection() {
		protocol.notifyOpeningConnection();
		try (
				Socket socket =
						new Socket(HOST_NAME, PORT_NUMBER)
		) {
			while (true) {
				protocol.sendMessage(socket, messageToSend);
				protocol.readMessage(socket);
			}
		} catch (InterruptedIOException timeoutException) {
			protocol.closeConnection();
		} catch (IOException ioException) {
			protocol.handleIOException();
		}
	}

}
