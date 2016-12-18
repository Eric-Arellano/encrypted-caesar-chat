package userInterface.networking;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

class Client {

	private final String hostName;
	private final int portNumber;
	private String messageToSend;

	public Client(String hostName, int portNumber, String messageToSend) {
		this.hostName = hostName;
		this.portNumber = portNumber;
		this.messageToSend = messageToSend;
	}

	public void launchConnection() {
		System.out.println("Opening client connection...");
		System.out.println("Sending to server...");
		try (
				Socket encryptionSocket =
						new Socket(hostName, portNumber);
				PrintWriter out =
						new PrintWriter(encryptionSocket.getOutputStream(), true)
		) {
			out.println(messageToSend);
			System.out.println("Message sent! Check the server.");
			closeConnection();
		} catch (IOException ioException) {
			System.out.println("IO Exception from client.");
			System.exit(1);
		}
	}

	private void closeConnection() {
		System.out.println("\nClosing client's connection. Restart app if you'd like to run it again.");
		System.exit(1);
	}

}
