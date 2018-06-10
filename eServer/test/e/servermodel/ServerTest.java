package e.servermodel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ServerTest {

	@Test
	void test() {
		Server server = new Server();
		server.addConnectListener(new ConnectListener() {
			public void hasConnect(Table table) {
				System.out.println(table.getNumber());
			}
		});
		while(true) {
			
		}
	}

}
