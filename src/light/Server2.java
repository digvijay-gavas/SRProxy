package light;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server2 {

	public static void start() throws Exception{
		ServerSocket 
		access_serverSocket=null,
		serverSocket=null;		
		try {
			access_serverSocket=new ServerSocket(Config.access_port);
			
			
			while(true)
			{
				try {
					Logger.getGlobal().log(Level.INFO,"Ready on "+Config.access_port);
					Socket access_socket=access_serverSocket.accept();
					serverSocket=new ServerSocket(Config.port);
					Socket socket=serverSocket.accept();
					serverSocket.close();
					SocketBindThread.bind(access_socket, socket);
				}catch (Exception e) {
					Logger.getGlobal().log(Level.WARNING,"", e);
					serverSocket.close();
				}
			}
		}
		catch(IOException e)
		{
			Logger.getGlobal().log(Level.WARNING,"", e);
			serverSocket.close();
			access_serverSocket.close();
			e.printStackTrace();
		}
	}

}
