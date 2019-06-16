package pt;

import java.io.IOException;
import java.net.Socket;

public class Client {

	public static void main(String[] args) throws Exception {
		System.out.println("sync conneting...");
		Socket sync=new Socket(Config.host,Config.sync_port);
		System.out.println("sync conneted");
		try 
		{
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
				catch (IOException e) {
					// TODO: handle exception
				}
			}
		}
		catch(Exception e)
		{
			sync.close();
		}
		
	}

}
