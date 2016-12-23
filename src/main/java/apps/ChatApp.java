package apps;

import apps.networkingutilities.ConnectionLauncher;

import java.util.concurrent.*;

class ChatApp implements Launchable {

	private final ConnectionLauncher connectionLauncher;

	ChatApp(String[] args) {
		this.connectionLauncher = new ConnectionLauncher(args);
	}

	public void launchApp() {
		Runnable networkListener = new NetworkListener();
		Callable commandLineInterface = new CommandLineInterface();
		ExecutorService executor = Executors.newFixedThreadPool(2);
		try {
			executor.submit(networkListener);
			Future commandLine = executor.submit(commandLineInterface);
			commandLine.get();
		} catch (ExecutionException | InterruptedException interrupted) {
			System.out.println("Shut down.");
			executor.shutdown();
			executor.shutdownNow();
			System.exit(1);
		}
	}

	private class NetworkListener implements Runnable {

		public void run() {
			connectionLauncher.launchConnection();
		}
	}

	private class CommandLineInterface implements Callable {

		public Object call() throws InterruptedException {
			throw new InterruptedException("Close app.");
			// ask to:
			// 1) send message that will be encrypted upon arrival
			// 2) wait until receiving encrypted message that can then be decrypted locally with key
			// (never send key)
		}

	}

}
