package pt;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketTools {

	public static Socket getNewServerSocket() throws IOException
	{
		Socket socket=null;
		ServerSocket serverSocket=null;
		while(socket==null)
		{
			for (int i = 0; i < Config.ports.length; i++) {
				try
				{
					serverSocket=new ServerSocket(Config.ports[i]);
					System.out.println("Got "+Config.ports[i]);
					socket=serverSocket.accept();
					System.out.println("Got C"+Config.ports[i]);
					break;
					
				}
				catch(Exception e) 
				{
				}
			}
		}
		
		System.out.println("Client Connected"+socket.getPort()+"("+socket.getLocalPort()+")");
		return socket;
	}
	
	public static void copyStream(InputStream input, OutputStream output,String log_header) throws IOException
	{
		int buffer_size=32768, total_sent=0;
	    byte[] buffer = new byte[buffer_size];
	    int read=input.available();
	    if(read>buffer_size)
	    {
	    	read=buffer_size;
	    }
	    
	    while (read>0)
	    {
	    	input.read(buffer, 0, read);
	        output.write(buffer, 0, read);
	        System.err.write(buffer, 0, read);
	        total_sent+=read;
	        
	        
	        read=input.available();
	        if(read>buffer_size)
		    {
		    	read=buffer_size;
		    }
	    }
	    //System.out.println("Done writting");
	    output.flush();
	    System.out.println(log_header +" "+ total_sent + " byte sent");
	}
}
