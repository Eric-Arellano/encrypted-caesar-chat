package userinterface.networkingutilities;

import userinterface.InterfaceChooser;
import userinterface.utilities.CommandLineParser;

import static userinterface.networkingutilities.LocalHostNameUtility.getLocalHostName;

public class ConnectionUtility {

	private final String[] args;

	public ConnectionUtility(String[] args) {
		this.args = args;
	}

	public void launchConnection() {
		InterfaceChooser chooser = new InterfaceChooser(args);
		if (chooser.isChatApp()) {
			launchChatConnection();
		} else if (chooser.isNetworkingApp()) {
			launchNetworkConnection();
		}
	}

	private void launchChatConnection() {
		if (isChatServer()) {
			Server server = createServer();
			server.launchConnection();
		} else if (isChatClient()) {
			Client client = createClient();
			client.launchConnection();
		}
	}

	private boolean isChatServer() {
		return args.length == 2;
	}

	private boolean isChatClient() {
		return args.length == 3;
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
		String HOST_NAME = args[0];
		return HOST_NAME.equalsIgnoreCase("local")
				|| HOST_NAME.equalsIgnoreCase("l")
				|| HOST_NAME.equalsIgnoreCase("-l");
	}

	private String getInputtedHostName() {
		return args[0];
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
		InterfaceChooser chooser = new InterfaceChooser(args);
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
