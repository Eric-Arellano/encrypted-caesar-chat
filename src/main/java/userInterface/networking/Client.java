package userInterface.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

class Client {

	private String[] args;
	private String hostName;
	private int portNumber;

	public Client(String[] args) {
		this.args = args;
		this.hostName = args[0];
		this.portNumber = Integer.parseInt(args[1]);
	}

	public void launchConnection() {
		try (
				Socket encryptionSocket =
						new Socket(hostName, portNumber);
				PrintWriter out =
						new PrintWriter(encryptionSocket.getOutputStream(), true);
				BufferedReader in =
						new BufferedReader(
								new InputStreamReader(encryptionSocket.getInputStream()));
				BufferedReader stdIn =
						new BufferedReader(
								new InputStreamReader(System.in))
		) {
			System.out.println();
		} catch (UnknownHostException unknownHostException) {
			System.out.println();
		} catch (IOException ioException) {
			System.out.println();
		}
	}

}
