package pt;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Server {

	public static void start() throws Exception{
		ServerSocket 
		access_serverSocket=null,
		serverSocket=null,
		sync_serverSocket=null;
		
		Socket sync=null;
		
		byte notifyByte=10;
		
		try {
			
			sync_serverSocket=new ServerSocket(Config.sync_port);
			sync=SocketTools.connectAndNotifyToClient(sync_serverSocket, sync, notifyByte);
			access_serverSocket=new ServerSocket(Config.access_port);
			serverSocket=new ServerSocket(Config.port);
			
			while(true)
			{
				Socket access_socket=access_serverSocket.accept();
				sync=SocketTools.connectAndNotifyToClient(sync_serverSocket, sync, notifyByte);
				Socket socket=serverSocket.accept();			
				new SocketBindThread(access_socket, socket).start();
				
			}
		}
		catch(IOException e)
		{
			serverSocket.close();
			access_serverSocket.close();
			sync.close();
			sync_serverSocket.close();
			e.printStackTrace();
		}
	}

}
