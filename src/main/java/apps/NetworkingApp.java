package apps;

import apps.networkingutilities.ConnectionInterfacer;

class NetworkingApp implements Launchable {

	private final ConnectionInterfacer connectionLauncher;

	NetworkingApp(String[] args) {
		this.connectionLauncher = new ConnectionInterfacer(args);
	}

	public void launchApp() {
		connectionLauncher.launchConnection();
		connectionLauncher.listenAndProcess();
		connectionLauncher.closeConnection();
	}

}
