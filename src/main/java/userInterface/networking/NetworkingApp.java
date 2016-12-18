package userInterface.networking;

import userInterface.Launchable;

public class NetworkingApp implements Launchable {

	private String[] args;

	public NetworkingApp(String[] args) {
		this.args = args;
	}

	public void launchApp() {
		if (isServer()) {
			launchServer();
		} else {
			launchClient();
		}
	}

	private boolean isServer() {
		return args.length == 4;
	}

	private void launchServer() {
		Server server = new Server(args);
		server.launchConnection();
	}

	private void launchClient() {
		Client client = new Client(args);
		client.launchConnection();
	}
}
