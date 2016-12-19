package userinterface.networkingutilities;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	private final int PORT_NUMBER;
	private final Protocol protocol;

	private final String messageToSend;

	public Server(int PORT_NUMBER) {
		this.PORT_NUMBER = PORT_NUMBER;
		protocol = new Protocol(Protocol.ConnectionType.SERVER);
		this.messageToSend = null;
	}

	public Server(int PORT_NUMBER, String messageToSend) {
		this.PORT_NUMBER = PORT_NUMBER;
		protocol = new Protocol(Protocol.ConnectionType.SERVER);
		this.messageToSend = messageToSend;
	}

	public void launchConnection() {
		protocol.notifyOpeningConnection();
		waitForClient();
		try (
				ServerSocket serverSocket =
						new ServerSocket(PORT_NUMBER);
				Socket clientSocket = serverSocket.accept();
				PrintWriter out =
						new PrintWriter(clientSocket.getOutputStream(), true);
				BufferedReader in =
						new BufferedReader(
								new InputStreamReader(clientSocket.getInputStream()))
		) {
			protocol.setTimeout(clientSocket);
			protocol.setTimeout(serverSocket);
			if (messageToSend != null) {
				protocol.sendMessage(out, messageToSend);
			}
			protocol.readMessage(in);
			protocol.closeConnection();
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
