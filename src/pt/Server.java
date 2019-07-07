package pt;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void start() throws Exception{
		ServerSocket 
		access_serverSocket=null,
		serverSocket=null;		
		try {
			SyncSocket syncSocket=new SyncSocket(Config.sync_port,(byte)10);
			access_serverSocket=new ServerSocket(Config.access_port);
			serverSocket=new ServerSocket(Config.port);
			
			while(true)
			{
				ColorLogger.logln("<warn>Ready on "+Config.access_port+"</warn>");
				Socket access_socket=access_serverSocket.accept();
				syncSocket.requestConnection();
				Socket socket=serverSocket.accept();			
				SocketBindThread.bind(access_socket, socket,Config.printSocketComunication);
				
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
