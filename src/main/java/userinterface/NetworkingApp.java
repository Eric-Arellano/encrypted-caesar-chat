package userinterface;

import userinterface.networkingutilities.ConnectionUtility;

class NetworkingApp implements Launchable {

	private final ConnectionUtility connectionUtility;

	NetworkingApp(String[] args) {
		this.connectionUtility = new ConnectionUtility(args);
	}

	public void launchApp() {
		connectionUtility.launchConnection();
	}

}
