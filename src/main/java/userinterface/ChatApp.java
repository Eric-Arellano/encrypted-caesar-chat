package userinterface;

class ChatApp implements Launchable {

	private final String[] args;

	// expected args[] = chat port || chat host port (no command-line message)
	public ChatApp(String[] args) {
		this.args = args;
	}

	public void launchApp() {
		// new Network
		// parse args[] to set up socket
		// listen constantly for message
		// ask to:
		// 1) send message that will be encrypted upon arrival
		// 2) wait until receiving encrypted message that can then be decrypted locally with key
		// (never send key)
	}

	class Network implements Runnable {

		public void run() {
			// launchConnection(); // chooses server or client; will listen constantly for message
		}
	}

	static class CommandLine implements Runnable {

		public void run() {

		}

	}

}
