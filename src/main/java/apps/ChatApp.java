package apps;

import apps.networkingutilities.ConnectionInterfacer;
import apps.utilities.EncryptMode;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static apps.utilities.ConsoleInterfacer.requestKey;
import static apps.utilities.ConsoleInterfacer.requestMessage;
import static apps.utilities.MessageTranslator.translateMessage;

class ChatApp implements Launchable {

	private final ConnectionInterfacer connectionInterfacer;
	private volatile boolean keepRunning;
	private volatile boolean messageReceived;

	ChatApp(String[] args) {
		this.connectionInterfacer = new ConnectionInterfacer(args);
		this.keepRunning = true;
		this.messageReceived = false;
	}

	public void launchApp() {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		Runnable networkListener = createNetworkListener();
		Runnable commandLineInterface = createCommandLineInterface(executor);

		connectionInterfacer.launchConnection();
		executor.submit(networkListener);
		executor.submit(commandLineInterface);
	}

	private synchronized void closeApp() {
		keepRunning = false;
		notifyAll();
		connectionInterfacer.closeConnection();
	}

	private Runnable createNetworkListener() {
		return () -> {
			while (keepRunning) {
				connectionInterfacer.listenForMessage();
				messageReceived = true;
				notifyAll();
			}
		};
	}

	private Runnable createCommandLineInterface(ExecutorService executor) {
		return () -> {
			closeApp();
			// TODO: ask to:
			// 1) send message that will be encrypted upon arrival
			// 2) wait until receiving encrypted message that can then be decrypted locally with key
			// (never send key)
		};
	}


	// -----------------------------------------------------------------------------------
	// Receive and decrypt message
	// -----------------------------------------------------------------------------------

	private synchronized void receiveMessage() {
		if (messageReceived) {
			askToDecryptReceivedMessage();
			String encryptedMessage = requestMessage(EncryptMode.DECRYPT);
			String key = requestKey(EncryptMode.DECRYPT);
			translateMessage(EncryptMode.DECRYPT, encryptedMessage, key);
			messageReceived = false;
		}
	}

	private void askToDecryptReceivedMessage() {

	}

	// -----------------------------------------------------------------------------------
	// Encrypt and send message
	// -----------------------------------------------------------------------------------

	private void encryptAndSendMessage() {
		String message = requestMessage(EncryptMode.ENCRYPT);
		String key = requestKey(EncryptMode.ENCRYPT);
		String encryptedMessage = translateMessage(EncryptMode.ENCRYPT, message, key);
		sendMessage(encryptedMessage);
	}

	private synchronized void sendMessage(String message) {
		connectionInterfacer.sendMessage(message);
	}

}
