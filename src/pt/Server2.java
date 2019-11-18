package pt;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server2 {

	public static void start() throws Exception{
		ServerSocket 
		access_serverSocket=null,
		serverSocket=null;		
		try {
			access_serverSocket=new ServerSocket(Config.access_port);
			
			
			while(true)
			{
				ColorLogger.logln("<warn>Ready on "+Config.access_port+"</warn>");
				Socket access_socket=access_serverSocket.accept();
				serverSocket=new ServerSocket(Config.port);
				Socket socket=serverSocket.accept();
				serverSocket.close();
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
