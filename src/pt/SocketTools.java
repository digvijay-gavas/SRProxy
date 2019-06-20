package pt;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class SocketTools {

	@Deprecated
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
					ColorLogger.log("Got "+Config.ports[i]);
					socket=serverSocket.accept();
					ColorLogger.log("Got C"+Config.ports[i]);
					break;
					
				}
				catch(Exception e) 
				{
				}
			}
		}
		
		ColorLogger.log("Client Connected"+socket.getPort()+"("+socket.getLocalPort()+")");
		return socket;
	}
	
	public static void copyStream(InputStream input, OutputStream output,String log_header) throws IOException
	{
		int buffer_size=32768;
		long total_sent=0;
	    byte[] buffer = new byte[buffer_size];
	    int read=input.available();
	    if(read>buffer_size)
	    	read=buffer_size;
	    
	    while (read>0)
	    {
	    	input.read(buffer, 0, read);
	        output.write(buffer, 0, read);
	        //System.err.write(buffer, 0, read);
	        total_sent+=read;	        
	        read=input.available();
	        if(read>buffer_size)
		    	read=buffer_size;
	    }
	    output.flush();
	    //ColorLogger.log(log_header +" bytes="+ total_sent);
	}
	
	public static Socket connectAndNotifyToClient(ServerSocket sync_serverSocket,Socket socket,byte notifyByte) throws IOException
	{
		if(socket==null || socket.isClosed() || !socket.isConnected())
		{
			ColorLogger.log("<warn>Waiting for sync...</warn>");
			socket=sync_serverSocket.accept();
			ColorLogger.log("<success>sync connected</success>");
		}
		while(true)
		{
			try {
				socket.getOutputStream().write(notifyByte);
				socket.getOutputStream().flush();
				break;
			}
			catch (SocketException e) {
				ColorLogger.log("<warn>Waiting for sync...</warn>");
				socket=sync_serverSocket.accept();
				ColorLogger.log("<success>sync connected</success>");
			}
		}
		return socket;
	}
}
