package e.model;

import java.io.IOException;

import e.view.ClientWindow;

public class GenerateClient {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub

		ClientWindow cws[] = new ClientWindow[6];
		for(int i = 0; i < 6; i++) {
			String name = "Table" + (i+1);
			cws[i] = new ClientWindow(name);
			Thread.currentThread().sleep(10000);
		}
	}

}
