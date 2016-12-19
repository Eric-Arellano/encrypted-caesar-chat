package userInterface.networking;

import userInterface.Launchable;
import userInterface.utilities.CommandLineParser;

import static userInterface.networking.LocalHostNameUtility.getLocalHostName;

public class NetworkingApp implements Launchable {

	private final String[] args;

	public NetworkingApp(String[] args) {
		this.args = args;
	}

	public void launchApp() {
		if (isServer()) {
			Server server = createServer();
			server.launchConnection();
		} else if (isClient()) {
			String messageToSend = parseAndTranslateInputtedMessage();
			Client client = createClient(messageToSend);
			client.launchConnection();
		}
	}

	private boolean isServer() {
		return args.length == 1 || args.length == 4;
	}

	private boolean isClient() {
		return args.length == 2 || args.length == 5;
	}

	private boolean isLocalClient() {
		String HOST_NAME = args[0];
		return HOST_NAME.equalsIgnoreCase("local")
				|| HOST_NAME.equalsIgnoreCase("l")
				|| HOST_NAME.equalsIgnoreCase("-l");
	}

	private Server createServer() {
		final int PORT_NUMBER = parsePortNumber();
		return new Server(PORT_NUMBER);
	}

	private Client createClient(String messageToSend) {
		final String HOST_NAME = parseHostName();
		final int PORT_NUMBER = parsePortNumber();
		return new Client(HOST_NAME, PORT_NUMBER, messageToSend);

	}

	private String parseHostName() {
		String hostName = "";
		if (isLocalClient()) {
			hostName = getLocalHostName();
		} else {
			hostName = getInputtedHostName();
		}
		return hostName;
	}

	private String getInputtedHostName() {
		return args[0];
	}

	private int parsePortNumber() {
		if (isServer()) {
			return Integer.parseInt(args[0]);
		} else {
			return Integer.parseInt(args[1]);
		}
	}

	private String parseAndTranslateInputtedMessage() {
		final int START_MESSAGE_INDEX = parseMessageArgIndex();
		String mode = args[START_MESSAGE_INDEX];
		String message = args[START_MESSAGE_INDEX + 1];
		String key = args[START_MESSAGE_INDEX + 2];
		return CommandLineParser.parseAndTranslateInputtedMessage(mode, message, key);
	}

	private int parseMessageArgIndex() {
		if (isServer()) {
			return 1;
		} else {
			return 2;
		}
	}
}
