package gomoku.net.exe;

import gomoku.net.server.Server;
import gomoku.net.utils.NETparam;

import java.io.IOException;

public class ServerExe {
	public static void main(String[] args) {
		try {
			Server serv = new Server(NETparam.serverPort);
			serv.run();
		} catch (IOException e) {
			e.getMessage();
			e.printStackTrace();
		}
	}
}