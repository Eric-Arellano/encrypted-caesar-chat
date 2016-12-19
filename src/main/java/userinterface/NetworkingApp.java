package userinterface;

import userinterface.networkingutilities.ConnectionLauncher;

class NetworkingApp implements Launchable {

	private final ConnectionLauncher connectionLauncher;

	NetworkingApp(String[] args) {
		this.connectionLauncher = new ConnectionLauncher(args);
	}

	public void launchApp() {
		connectionLauncher.launchConnection();
	}

}
