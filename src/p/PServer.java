package p;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class PServer {

	static int C_PORT=5050;
	static int PROXY_PORT=5051;
	public static void main(String[] args) throws IOException {
		ServerSocket cServer=new ServerSocket(C_PORT);
		ServerSocket sServer=new ServerSocket(PROXY_PORT);
		Socket cServerSocket = null;
		try 
		{
			System.out.println("cServer waiting.."+cServer.getLocalPort());
			cServerSocket = cServer.accept();
			System.out.println("cServer connected");
						
				
			while(true)
			{
				
				try 
				{
					System.out.println("sServer waiting.. on "+sServer.getLocalPort());
					Socket sServerSocket = sServer.accept();
					System.out.println("sServer connected");
					while (!sServerSocket.isClosed())
					{
						
						
						if(sServerSocket.getInputStream().available()>0)
						{
							System.out.println(sServerSocket.getInputStream().available()+" bytes from sServerSocket to  cServerSocket");
							//sClientSocket.getInputStream().transferTo(cClientSocket.getOutputStream());
							StreamTools.copyStream(sServerSocket.getInputStream(),cServerSocket.getOutputStream());
							//System.out.println(sServerSocket.getInputStream().available()+" done sServerSocket to  cServerSocket");
						}
						if(cServerSocket.getInputStream().available()>0)
						{
							System.out.println(cServerSocket.getInputStream().available()+" bytes from cServerSocket to sServerSocket");
							//cClientSocket.getInputStream().transferTo(sClientSocket.getOutputStream());
							StreamTools.copyStream(cServerSocket.getInputStream(),sServerSocket.getOutputStream());
							//System.out.println(cServerSocket.getInputStream().available()+" done cServerSocket to sServerSocket");
						}
						
					}
					sServerSocket.close();
				}
				catch (Exception e) 
				{
					System.out.println("cServer connection failed");
					//e.printStackTrace();
				}
			}
			
		}
		catch (Exception e) 
		{
			System.out.println("cServer connection failed");
			cServerSocket.close();
		}
	}
	
	
}
