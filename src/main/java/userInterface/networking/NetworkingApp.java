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
		} else {
			String messageToSend = parseAndTranslateInputtedMessage();
			Client client = createClient(messageToSend);
			client.launchConnection();
		}
	}

	private boolean isServer() {
		return args.length == 1;
	}

	private Server createServer() {
		// TODO: add validation/exception handling for Port number
		final int PORT_NUMBER = parsePortNumber();
		return new Server(PORT_NUMBER);
	}

	private Client createClient(String messageToSend) {
		// TODO: add validation/exception handling for Port number and host name
		final String HOST_NAME = getHostName();
		final int PORT_NUMBER = parsePortNumber();
		return new Client(HOST_NAME, PORT_NUMBER, messageToSend);

	}

	private String getHostName() {
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
		return Integer.parseInt(args[0]);
	}

	private String parseAndTranslateInputtedMessage() {
		String mode = args[1];
		String message = args[2];
		String key = args[3];
		return CommandLineParser.parseAndTranslateInputtedMessage(mode, message, key);
	}
}
