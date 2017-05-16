import networkingutilities.ConnectionInterfacer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@Nested
@DisplayName("Server test")
class ServerTest {

	private static ConnectionInterfacer server;

	@BeforeEach
	void setupServer() {
		String[] args = {"40" };
		server = new ConnectionInterfacer(args);
	}

	@Test
	void connect() {
		server.launchConnection();
	}

	@Test
	void invalidConnection() {

	}

}

@Nested
@DisplayName("Client test")
class ClientTest {

	private static ConnectionInterfacer server;
	private static ConnectionInterfacer client;

	@BeforeEach
	void setupServerAndClient() {
		String[] serverArgs = {"40" };
		server = new ConnectionInterfacer(serverArgs);
		String[] clientArgs = {"local", "50" };
		client = new ConnectionInterfacer(clientArgs);
	}

	@Test
	void connect() {
		server.launchConnection();
	}

	@Test
	void invalidConnection() {

	}

	@Test
	void sendEncryption() {

	}

	@Test
	void sendDecryption() {

	}

	@Test
	void receiveEncryption() {

	}

	@Test
	void receiveDecryption() {

	}
}
