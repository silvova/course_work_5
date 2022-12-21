package gomoku.client;

import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

public class Client {
	
	private boolean serverIsConnect = true;

	private Socket socket;
	private int port = 1337;

	private final String quitCommand = "@quit";
	private final int maxNameLength = 128;
	private final int maxPacketLength = 1024;

	private OutputStream out;
	private InputStream in;
	private boolean enable;

	public Client() throws UnknownHostException, IOException{
		this.out = connection();
		this.in = socket.getInputStream();
		this.enable = true;
		this.recieveData();
	}

	private OutputStream connection() throws UnknownHostException, IOException {
		socket =  new Socket("localhost", 1337);
		return socket.getOutputStream();
	}

	public int sendMove(int x, int y) throws IOException {
		send("@move " + Integer.toString(x) + " " + Integer.toString(y));
		String res = recieveData();
		switch(res) {
			case "OK":
				return (0);
			case "END":
				return (1);
			case "EXIT":
				return (-2);
			case "LEAVE":
				recieveData();
				return (-2);
			default:
				return (-1);
		}
	}

	public String sendName(String name) throws IOException {
		send("@name " + name);
		return recieveData();
	}

	public String getName() throws IOException {
		send("@getName .");
		return recieveData();
	}

	public boolean sendCreate() throws IOException {
		send("@create .");
		String player = recieveData();
		send(player);
		if(player.equals("[X]")) {
			return false;
		}
		return true;
	}

	public boolean sendConnect(String toName) throws IOException {
		send("@connect " + toName);
		String status = recieveData();
		if(status.equals("OK")) {
			return true;
		} else {
			return false;
		}
	}

	public void sendLeave() throws IOException {
		// System.out.println("LEAVE");
		send("@leave .");
	}

	public void sendLeaveBoth() throws IOException {
		// System.out.println("LEAVE");
		send("@leaveBoth .");
	}

	public String getCreateList() throws IOException {
		send("@crList .");
		return recieveData();
	}

	public void sendExit() throws IOException {
		send("@exit .");
		// recieveData();
	}

	public void sendQuit() throws IOException {
		send("@quit .");
	}


	public void sendWaitingStop() throws IOException {
		send("[X]");
	}

	private void send(String data) throws IOException {
		if(enable){
			byte[] sendData = new byte[maxNameLength + maxPacketLength];
			sendData = data.getBytes();
			out.write(sendData);
			out.flush();
		}
	}

	public void breakConnection() throws IOException {
		try {
			in.close();
			out.close();
			socket.close();
		} catch(SocketException se) {

		}
	}


	public String recieveData() throws IOException {
		byte[] recieveData = new byte[maxPacketLength + maxNameLength];
		in.read(recieveData);
		return (new String(recieveData, StandardCharsets.UTF_8)).trim();		
	}
		
}