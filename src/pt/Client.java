package pt;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Client {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//ServerSocket access_serverSocket=new ServerSocket(Config.access_port);
		//ServerSocket serverSocket=new ServerSocket(Config.port);
		System.out.println("sync conneting...");
		Socket sync=new Socket(Config.host,Config.sync_port);
		System.out.println("sync conneted");
		while(true)
		{
			try {
				if(sync.getInputStream().read()==10)
				{
					Socket socket=new Socket(Config.host, Config.port);
					Socket client_socket=new Socket(Config.client_host, Config.client_port);
					new SocketBindThread(socket, client_socket).start();
				}
			}
			catch (Exception e) {
				// TODO: handle exception
			}
		}
		
	}

}
