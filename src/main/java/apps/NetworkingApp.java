package apps;

import apps.networkingutilities.ConnectionInterfacer;

class NetworkingApp implements Launchable {

	private final ConnectionInterfacer connectionInterfacer;

	NetworkingApp(String[] args) {
		this.connectionInterfacer = new ConnectionInterfacer(args);
	}

	public void launchApp() {
		connectionInterfacer.launchConnection();
		connectionInterfacer.sendMessage();
		connectionInterfacer.listenForMessage();
		connectionInterfacer.closeConnection();
	}

}
