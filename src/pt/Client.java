package pt;

import java.io.IOException;
import java.net.ConnectException;
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
						boolean isInWaitState = false;
						while(true)
							try {
								Socket client_socket=new Socket(Config.client_host, Config.client_port);
								SocketBindThread.bind(socket, client_socket,Config.printSocketComunication);
								break;
							}
							catch (ConnectException e) {
								if (!isInWaitState)
									ColorLogger.logln("<warn>Waiting for "+Config.client_host+":"+Config.client_port+"</warn>");
								Thread.sleep(Config.retry_interval);
								isInWaitState=true;
							}
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
