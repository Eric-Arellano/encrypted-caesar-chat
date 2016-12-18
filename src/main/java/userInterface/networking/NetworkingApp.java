package userInterface.networking;

import userInterface.Launchable;
import userInterface.utilities.CommandLineParser;

import java.net.InetAddress;
import java.net.UnknownHostException;

// TODO: For now, only client can send messages and server receive them; not vice versa
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
		return args.length == 1;
	}

	private boolean isClient() {
		return isLocalClient() || isNetworkClient();
	}

	private boolean isLocalClient() {
		return args.length == 4;
	}

	private boolean isNetworkClient() {
		return args.length == 5;
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
		} else if (isNetworkClient()) {
			hostName = getNetworkHostName();
		}
		return hostName;
	}

	private String getNetworkHostName() {
		return args[0];
	}

	private String getLocalHostName() {
		String hostName = "";
		try {
			hostName = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException unknownHostException) {
			System.out.println("Host cannot be resolved.");
			System.exit(1);
		}
		return hostName;
	}

	private int parsePortNumber() {
		if (isNetworkClient()) {
			return Integer.parseInt(args[1]);
		} else {
			return Integer.parseInt(args[0]);
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
		if (isNetworkClient()) {
			return 2;
		} else {
			return 1;
		}
	}
}
