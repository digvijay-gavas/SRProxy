package pt;

import java.io.IOException;
import java.net.Socket;

public class Client {

	public static void start() throws Exception {
		try 
		{
			SyncSocket syncSocket=new SyncSocket(Config.host, Config.sync_port, (byte)10,Config.retry_interval);
			while(true)
			{
				try {
						syncSocket.serveConnection();
						Socket socket=new Socket(Config.host, Config.port);
						Socket client_socket=new Socket(Config.client_host, Config.client_port);
						new SocketBindThread(socket, client_socket).start();
				}
				catch (IOException e) {
				}
			}
		}
		catch(Exception e)
		{
		}
		
	}

}
