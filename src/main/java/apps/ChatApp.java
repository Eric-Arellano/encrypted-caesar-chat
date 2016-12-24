package apps;

import apps.networkingutilities.ConnectionInterfacer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class ChatApp implements Launchable {

	private final ConnectionInterfacer connectionLauncher;
	private volatile boolean keepRunning;

	ChatApp(String[] args) {
		this.connectionLauncher = new ConnectionInterfacer(args);
		this.keepRunning = true;
	}

	public void launchApp() {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		Runnable networkListener = createNetworkListener();
		Runnable commandLineInterface = createCommandLineInterface(executor);

		executor.submit(networkListener);
		executor.submit(commandLineInterface);
	}

	private Runnable createNetworkListener() {
		return () -> {
			connectionLauncher.launchConnection();
			while (keepRunning) {
				connectionLauncher.listenAndProcess();
			}
			connectionLauncher.closeConnection();
		};
	}

	private Runnable createCommandLineInterface(ExecutorService executor) {
		return () -> {
			closeApp(executor);
			// TODO: ask to:
			// 1) send message that will be encrypted upon arrival
			// 2) wait until receiving encrypted message that can then be decrypted locally with key
			// (never send key)
		};
	}

	private synchronized void closeApp(ExecutorService executor) {
		try {
			keepRunning = true;
			Thread.sleep(50);
		} catch (InterruptedException interruptedException) {
			System.out.println("Error shutting down.");
		}
//		System.out.println("Shut down.");
//		executor.shutdown();
//		executor.shutdownNow();
//		System.exit(1);
	}

}
