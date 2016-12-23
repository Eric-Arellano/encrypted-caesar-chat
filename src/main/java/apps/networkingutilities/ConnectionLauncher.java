package apps.networkingutilities;

import apps.AppChooser;
import apps.utilities.CommandLineParser;

import java.io.IOException;

import static apps.networkingutilities.LocalHostNameUtility.getLocalHostName;

public class ConnectionLauncher {

	private final String[] args;
	private final AppChooser chooser;
	private Connection connection;

	public ConnectionLauncher(String[] args) {
		this.args = args;
		chooser = new AppChooser(args);
	}

	// -----------------------------------------------------------------------------------
	// Public interface
	// -----------------------------------------------------------------------------------

	public void launchConnection() {
		try {
			if (isNetworkClient() || isChatClient()) {
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

	// -----------------------------------------------------------------------------------
	// Launch connections
	// -----------------------------------------------------------------------------------


	private boolean isChatServer() {
		return args.length == 2;
	}

	private boolean isChatClient() {
		return args.length == 3;
	}

	private boolean isNetworkServer() {
		return args.length == 1 || args.length == 4;
	}

	private boolean isNetworkClient() {
		return args.length == 2 || args.length == 5;
	}

	private Server createServer() throws IOException {
		final int PORT_NUMBER = parsePortNumber();
		if (includesMessage()) {
			String messageToSend = parseAndTranslateInputtedMessage();
			return new Server(PORT_NUMBER, messageToSend);
		} else {
			return new Server(PORT_NUMBER);
		}
	}

	private Client createClient() throws IOException {
		final String HOST_NAME = parseHostName();
		final int PORT_NUMBER = parsePortNumber();
		if (includesMessage()) {
			String messageToSend = parseAndTranslateInputtedMessage();
			return new Client(HOST_NAME, PORT_NUMBER, messageToSend);
		} else {
			return new Client(HOST_NAME, PORT_NUMBER);
		}
	}

	// -----------------------------------------------------------------------------------
	// Parse inputs for creation
	// -----------------------------------------------------------------------------------

	private String parseHostName() {
		String hostName;
		if (isLocalClient()) {
			hostName = getLocalHostName();
		} else {
			hostName = getInputtedHostName();
		}
		return hostName;
	}

	private boolean isLocalClient() {
		String HOST_NAME = getInputtedHostName();
		return HOST_NAME.equalsIgnoreCase("local")
				|| HOST_NAME.equalsIgnoreCase("l")
				|| HOST_NAME.equalsIgnoreCase("-l");
	}

	private String getInputtedHostName() {
		if (chooser.isChatApp()) {
			return args[1];
		} else {
			return args[0];
		}
	}

	private int parsePortNumber() {
		if (isChatServer()) {
			return Integer.parseInt(args[1]);
		} else if (isChatClient()) {
			return Integer.parseInt(args[2]);
		} else if (isNetworkServer()) {
			return Integer.parseInt(args[0]);
		} else {
			return Integer.parseInt(args[1]);
		}
	}

	private boolean includesMessage() {
		return chooser.isNetworkingApp() && (args.length == 4 || args.length == 5);
	}

	private String parseAndTranslateInputtedMessage() {
		final int START_MESSAGE_INDEX = parseMessageArgIndex();
		String mode = args[START_MESSAGE_INDEX];
		String message = args[START_MESSAGE_INDEX + 1];
		String key = args[START_MESSAGE_INDEX + 2];
		return CommandLineParser.parseAndTranslateInputtedMessage(mode, message, key);
	}

	private int parseMessageArgIndex() {
		if (isNetworkServer()) {
			return 1;
		} else {
			return 2;
		}
	}
}
