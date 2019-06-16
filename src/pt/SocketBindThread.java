package pt;

import java.io.IOException;
import java.net.Socket;

public class SocketBindThread extends Thread {
	Socket socket1,socket2;
	public SocketBindThread(Socket socket1,Socket socket2) {
		this.socket1=socket1;
		this.socket2=socket2;
	}
	@Override
	public void run() {
		String sockets="socket("+socket1.getLocalPort()+":"+socket1.getPort()+") and socket("+socket2.getLocalPort()+":"+socket2.getPort()+")";
		System.out.println("Binding "+sockets);
		long bind_timeout=System.currentTimeMillis()+Config.bind_timeout;	
		try 
		{
			while (!socket1.isClosed() && !socket2.isClosed())
			{
				if(socket1.getInputStream().available()>0)
				{
					SocketTools.copyStream(socket1.getInputStream(),socket2.getOutputStream(),"REQUEST("+socket1.getPort()+")");
					bind_timeout=System.currentTimeMillis()+Config.bind_timeout;
				}
				if(socket2.getInputStream().available()>0)
				{
					SocketTools.copyStream(socket2.getInputStream(),socket1.getOutputStream(),"RESPONSE"+socket2.getPort()+")");
					bind_timeout=System.currentTimeMillis()+Config.bind_timeout;
				}
				if(bind_timeout-System.currentTimeMillis()<0 && Config.bind_timeout>0)
					break;
			}
		}
		catch(Exception e)
		{
			try 
			{
				socket2.close();
				socket1.close();
				System.out.println("Closed "+sockets);
			} 
			catch (IOException e1) 
			{
				e1.printStackTrace();
			}
			
		}
		
		try 
		{
			socket2.close();
			socket1.close();
			System.out.println("Closed "+sockets);
		} 
		catch (IOException e1) 
		{
			e1.printStackTrace();
		}
	}
}
