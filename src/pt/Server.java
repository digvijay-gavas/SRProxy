package pt;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("sync conneting...");
		Socket sync=new ServerSocket(Config.sync_port).accept();
		System.out.println("sync connected");
		
		
		ServerSocket access_serverSocket=new ServerSocket(Config.access_port);

		ServerSocket serverSocket=new ServerSocket(Config.port);

		while(true)
		{
			System.out.println("access waiting...");
			Socket access_socket=access_serverSocket.accept();
			System.out.println("waiting...");
			sync.getOutputStream().write(10);
			sync.getOutputStream().flush();
			Socket socket=serverSocket.accept();
			System.out.println("connected");
			
			new SocketBindThread(access_socket, socket).start();
		}
		
	}

}
