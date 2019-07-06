package pt;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Server {

	public static void start() throws Exception{
		ServerSocket 
		access_serverSocket=null,
		serverSocket=null;
		ColorLogger.log("<info>sarting</info>=");
		
		try {
			SyncSocket syncSocket=new SyncSocket(Config.sync_port,(byte)10);
			access_serverSocket=new ServerSocket(Config.access_port);
			serverSocket=new ServerSocket(Config.port);
			
			while(true)
			{
				Socket access_socket=access_serverSocket.accept();
				syncSocket.requestConnection();
				Socket socket=serverSocket.accept();			
				new SocketBindThread(access_socket, socket).start();
				
			}
		}
		catch(IOException e)
		{
			serverSocket.close();
			access_serverSocket.close();
			e.printStackTrace();
		}
	}

}
