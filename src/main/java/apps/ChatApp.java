package apps;

import apps.networkingutilities.ConnectionLauncher;

class ChatApp implements Launchable {

	private final ConnectionLauncher connectionLauncher;

	ChatApp(String[] args) {
		this.connectionLauncher = new ConnectionLauncher(args);
	}

	public void launchApp() {
		Runnable networkListener = new NetworkListener();
		networkListener.run();
		Runnable commandLineInterface = new CommandLineInterface();
		commandLineInterface.run();
	}

	private class NetworkListener implements Runnable {

		public void run() {
			connectionLauncher.launchConnection();
		}
	}

	private class CommandLineInterface implements Runnable {

		public void run() {
			connectionLauncher.closeConcurrentConnection();
			// ask to:
			// 1) send message that will be encrypted upon arrival
			// 2) wait until receiving encrypted message that can then be decrypted locally with key
			// (never send key)
		}

	}

}
