package userInterface.networking;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import static userInterface.networking.LocalHostNameUtility.getLocalHostName;
import static userInterface.networking.LocalHostNameUtility.getLocalIPAddress;

class Client {

	private final String HOST_NAME;
	private final int PORT_NUMBER;
	private final String messageToSend;

	Client(String HOST_NAME, int PORT_NUMBER, String messageToSend) {
		this.HOST_NAME = HOST_NAME;
		this.PORT_NUMBER = PORT_NUMBER;
		this.messageToSend = messageToSend;
	}

	void launchConnection() {
		openClient();
		try (
				Socket encryptionSocket =
						new Socket(HOST_NAME, PORT_NUMBER);
				PrintWriter out =
						new PrintWriter(encryptionSocket.getOutputStream(), true)
		) {
			sendToServer(out);
			closeConnection();
		} catch (IOException ioException) {
			handleIOException();
		}
	}

	private void openClient() {
		String openingMessage = String.format("Opening client connection on %s (%s)...",
				getLocalHostName(), getLocalIPAddress());
		System.out.println(openingMessage);
	}

	private void sendToServer(PrintWriter out) {
		System.out.println("Sending to server...");
		out.println(messageToSend);
		System.out.println("\nMessage sent! Check the server.");
	}

	private void closeConnection() {
		System.out.println("\nClosing client's connection. Restart app if you'd like to run it again.");
		System.exit(1);
	}

	private void handleIOException() {
		System.out.println("IO Exception from client.");
		System.exit(1);
	}

}
