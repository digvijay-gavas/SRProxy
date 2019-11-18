package pt;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;

public class Client2 {

	public static void start() throws Exception {
		try 
		{
			while(true)
			{
				try {
						Socket socket=new Socket(Config.host, Config.port);
						boolean isInWaitState = false;
						while(true)
						{
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
							Thread.sleep(Config.retry_interval);  
						}
				}
				catch (IOException e) {
				}
				Thread.sleep(Config.retry_interval);
			}
		}
		catch(Exception e)
		{
		}
		
	}

}
