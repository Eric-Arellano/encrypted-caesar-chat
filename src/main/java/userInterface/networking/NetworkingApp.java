package userInterface.networking;

import userInterface.CommandLineApp;
import userInterface.Launchable;

import java.util.Arrays;

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
		String[] messageArgs = Arrays.copyOfRange(args, 2, 5);
		CommandLineApp commandLineApp = new CommandLineApp(messageArgs);
		return commandLineApp.parseAndTranslateInputtedMessage();
	}
}
