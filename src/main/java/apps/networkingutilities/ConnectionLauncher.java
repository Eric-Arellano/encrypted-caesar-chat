package apps.networkingutilities;

import apps.AppChooser;
import apps.utilities.CommandLineParser;

import static apps.networkingutilities.LocalHostNameUtility.getLocalHostName;

public class ConnectionLauncher {

	private final String[] args;
	private final AppChooser chooser;

	public ConnectionLauncher(String[] args) {
		this.args = args;
		chooser = new AppChooser(args);
	}

	public void launchConnection() {
			if (chooser.isNetworkingApp()) {
			launchNetworkConnection();
		}
	}

	private void launchNetworkConnection() {
		if (isNetworkServer()) {
			Server server = createServer();
			server.launchConnection();
		} else if (isNetworkClient()) {
			Client client = createClient();
			client.launchConnection();
		}
	}

	private boolean isNetworkServer() {
		return args.length == 1 || args.length == 4;
	}

	private boolean isNetworkClient() {
		return args.length == 2 || args.length == 5;
	}

	private Server createServer() {
		final int PORT_NUMBER = parsePortNumber();
		if (includesMessage()) {
			String messageToSend = parseAndTranslateInputtedMessage();
			return new Server(PORT_NUMBER, messageToSend);
		} else {
			return new Server(PORT_NUMBER);
		}
	}

	private Client createClient() {
		final String HOST_NAME = parseHostName();
		final int PORT_NUMBER = parsePortNumber();
		if (includesMessage()) {
			String messageToSend = parseAndTranslateInputtedMessage();
			return new Client(HOST_NAME, PORT_NUMBER, messageToSend);
		} else {
			return new Client(HOST_NAME, PORT_NUMBER);
		}
	}

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
		return args[0];
	}

	private int parsePortNumber() {
		if (isNetworkServer()) {
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