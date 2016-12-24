package apps.networkingutilities;

import java.io.IOException;

public class ConnectionInterfacer {

	private Connection connection;
	private final NetworkingCommandLineParser parser;

	public ConnectionInterfacer(String[] args) {
		parser = new NetworkingCommandLineParser(args);
	}

	public void launchConnection() {
		try {
			if (parser.isClient()) {
				connection = createClient();
			} else {
				connection = createServer();
			}
		} catch (IOException failureToLaunchException) {
			System.out.println("The connection failed to start.");
		}

	}

	public void listenAndProcess() {
		try {
			connection.listenAndProcess();
		} catch (IOException e) {
			System.out.println("Failure listening to and writing to other socket.");
		}
	}

	public void closeConnection() {
		try {
			connection.closeConnection();
		} catch (IOException e) {
			System.out.println("Failure closing connection.");
		}
	}

	private Server createServer() throws IOException {
		final int PORT_NUMBER = parser.parsePortNumber();
		if (parser.includesMessage()) {
			String messageToSend = parser.parseAndTranslateInputtedMessage();
			return new Server(PORT_NUMBER, messageToSend);
		} else {
			return new Server(PORT_NUMBER);
		}
	}

	private Client createClient() throws IOException {
		final String HOST_NAME = parser.parseHostName();
		final int PORT_NUMBER = parser.parsePortNumber();
		if (parser.includesMessage()) {
			String messageToSend = parser.parseAndTranslateInputtedMessage();
			return new Client(HOST_NAME, PORT_NUMBER, messageToSend);
		} else {
			return new Client(HOST_NAME, PORT_NUMBER);
		}
	}

}
