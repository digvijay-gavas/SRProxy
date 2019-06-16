package pt;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		ServerSocket 
		access_serverSocket=null,
		serverSocket=null,
		sync_serverSocket=null;
		
		Socket sync=null;
		
		try {
			System.out.println("sync conneting...");
			sync_serverSocket=new ServerSocket(Config.sync_port);
			sync=sync_serverSocket.accept();
			System.out.println("sync connected");
			
			access_serverSocket=new ServerSocket(Config.access_port);
			serverSocket=new ServerSocket(Config.port);
			
			while(true)
			{
				Socket access_socket=access_serverSocket.accept();
				sync.getOutputStream().write(10);
				sync.getOutputStream().flush();
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
