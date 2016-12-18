package userInterface.networking;

import userInterface.Launchable;
import userInterface.utilities.CommandLineUtilities;

// TODO: For now, only client can send messages and server receive them; not vice versa
public class NetworkingApp implements Launchable {

	private String[] args;

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
		final int PORT_NUMBER = Integer.parseInt(args[0]);
		return new Server(PORT_NUMBER);
	}

	private Client createClient(String messageToSend) {
		final String HOST_NAME = args[0];
		final int PORT_NUMBER = Integer.parseInt(args[1]);
		return new Client(HOST_NAME, PORT_NUMBER, messageToSend);

	}

	private String parseAndTranslateInputtedMessage() {
		String mode = args[2];
		String message = args[3];
		String key = args[4];
		return CommandLineUtilities.parseAndTranslateInputtedMessage(mode, message, key);
	}
}
