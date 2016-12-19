package apps.networkingutilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import static apps.networkingutilities.LocalHostNameUtility.getLocalHostName;
import static apps.networkingutilities.LocalHostNameUtility.getLocalIPAddress;

class Protocol {

	private final ConnectionType connectionType;

	Protocol(ConnectionType connectionType) {
		this.connectionType = connectionType;
	}

	void notifyOpeningConnection() {
		String openingMessage = String.format("Opening %s connection on %s (%s)...",
				connectionType.toString(), getLocalHostName(), getLocalIPAddress());
		System.out.println(openingMessage);
	}

	void setTimeout(Socket socket) throws SocketException {
		final int THREE_SECONDS = 3000;
		socket.setSoTimeout(THREE_SECONDS);
	}

	void setTimeout(ServerSocket socket) throws SocketException {
		final int THREE_SECONDS = 3000;
		socket.setSoTimeout(THREE_SECONDS);
	}


	void sendMessage(PrintWriter writer, String messageToSend) {
		notifySending();
		writer.println(messageToSend);
		notifySent();
	}

	private void notifySending() {
		String sendingNotification = String.format("\nSending to %s...", ConnectionType
				.getOtherConnectionType(connectionType));
		System.out.println(sendingNotification);
	}

	private void notifySent() {
		String sentNotification = String.format("\nMessage sent! Check the %s.", ConnectionType
				.getOtherConnectionType(connectionType));
		System.out.println(sentNotification);
	}

	void readMessage(BufferedReader reader) throws IOException {
		String receivedMessage = reader.readLine();
		if (receivedMessage != null) {
			notifyReceived();
			System.out.println(receivedMessage);
		}
	}

	private void notifyReceived() {
		System.out.println("\nMessage received!\n");
	}

	void closeConnection() {
		String closingMessage = String.format("\nClosing %s's connection. Restart app if you'd like " +
				"to run it again.", connectionType.toString());
		System.out.println(closingMessage);
		System.exit(1);
	}

	void handleTimeoutException() {
		String exceptionMessage = "Connection timeout. Make sure either the server or client is " +
				"sending a message.";
		System.out.println(exceptionMessage);
		System.exit(1);
	}

	void handleIOException() {
		String exceptionMessage = String.format("IO Exception from %s.", connectionType.toString());
		System.out.println(exceptionMessage);
		System.exit(1);
	}

	enum ConnectionType {
		CLIENT, SERVER;

		@Override
		public String toString() {
			return name().toLowerCase();
		}

		static String getOtherConnectionType(ConnectionType currentType) {
			switch (currentType) {
				case CLIENT:
					return SERVER.toString();
				case SERVER:
					return CLIENT.toString();
				default:
					return null;
			}
		}

	}
}
