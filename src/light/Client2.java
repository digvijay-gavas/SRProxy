package light;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client2 {

	public static void start() throws Exception {
		try 
		{
			boolean isInWaitState = false;
			while(true)
			{
				try {
						Socket socket=new Socket(Config.host, Config.port);
						isInWaitState = false;
						while(true)
						{
							try {
								Socket client_socket=new Socket(Config.client_host, Config.client_port);
								SocketBindThread.bind(socket, client_socket);
								break;
							}
							catch (ConnectException e) {
								if (!isInWaitState)
									Logger.getGlobal().log(Level.INFO,"Waiting for  "+Config.client_host+":"+Config.client_port);
								Thread.sleep(Config.retry_interval);
								isInWaitState=true;
							}
							Thread.sleep(Config.retry_interval);  
						}
				}
				catch (IOException e) {
					if (!isInWaitState)
						Logger.getGlobal().log(Level.WARNING," waiting for "+Config.host+":"+Config.port+" retrying interval "+Config.retry_interval +" millis");
					isInWaitState=true;
				}
				Thread.sleep(Config.retry_interval);
			}
		}
		catch(Exception e)
		{
			Logger.getGlobal().log(Level.WARNING,"",e);
		}
		
	}

}
