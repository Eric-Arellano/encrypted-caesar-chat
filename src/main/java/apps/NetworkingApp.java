package apps;

import apps.networkingutilities.ConnectionInterfacer;

class NetworkingApp implements Launchable {

	private final ConnectionInterfacer connectionInterfacer;

	NetworkingApp(String[] args) {
		this.connectionInterfacer = new ConnectionInterfacer(args);
	}

	public void launchApp() {
		connectionInterfacer.launchConnection();
		sendMessage();
		connectionInterfacer.listenForMessage();
		connectionInterfacer.closeConnection();
	}

	private void sendMessage() {
		if (connectionInterfacer.isMessageToSend()) {
			connectionInterfacer.sendMessage(connectionInterfacer.getMessageToSend());
		}
	}

}
