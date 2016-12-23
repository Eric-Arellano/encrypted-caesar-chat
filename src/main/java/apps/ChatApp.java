package apps;

import apps.networkingutilities.ConnectionLauncher;

import java.util.concurrent.*;

class ChatApp implements Launchable {

	private final ConnectionLauncher connectionLauncher;
	private volatile boolean keepRunning;

	ChatApp(String[] args) {
		this.connectionLauncher = new ConnectionLauncher(args);
		this.keepRunning = true;
	}

	public void launchApp() {
		Runnable networkListener = () -> {
			connectionLauncher.launchConnection();
			while (keepRunning) {
				connectionLauncher.listenAndProcess();
			}
			connectionLauncher.closeConnection();
		};
		Callable commandLineInterface = this::closeApp;

		ExecutorService executor = Executors.newFixedThreadPool(2);
		try {
			executor.submit(networkListener);
			Future commandLine = executor.submit(commandLineInterface);
			commandLine.get();
		} catch (ExecutionException | InterruptedException interrupted) {
			connectionLauncher.closeConnection();
			System.out.println("Shut down.");
			executor.shutdown();
			executor.shutdownNow();
			System.exit(1);
		}
	}

	// TODO: ask to:
	// 1) send message that will be encrypted upon arrival
	// 2) wait until receiving encrypted message that can then be decrypted locally with key
	// (never send key)

	private Object closeApp() throws InterruptedException {
		throw new InterruptedException("Close app.");
	}

}
