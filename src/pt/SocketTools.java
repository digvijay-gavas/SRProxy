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
					ColorLogger.logln("Got "+Config.ports[i]);
					socket=serverSocket.accept();
					ColorLogger.logln("Got C"+Config.ports[i]);
					break;
					
				}
				catch(Exception e) 
				{
				}
			}
		}
		
		ColorLogger.logln("Client Connected"+socket.getPort()+"("+socket.getLocalPort()+")");
		return socket;
	}
	
	public static void copyStream(InputStream input, OutputStream output,boolean printSocketComunication) throws IOException
	{
		int buffer_size=32768;
		//long total_sent=0;
	    byte[] buffer = new byte[buffer_size];
	    while(true)
	    {
	    	//try {
			    //int first_byte=-1;
			    //System.err.println("wait start");
			    //first_byte=input.read();
			    //System.err.println("wait end");
			    //if(first_byte<0)
			    //return;
			    output.write(input.read());
			    
			    int read=input.available();
			    if(read>buffer_size)
			    	read=buffer_size;
			    
			    while (read>0)
			    {
			    	input.read(buffer, 0, read);
			        output.write(buffer, 0, read);
			        if(printSocketComunication)
			        	System.out.write(buffer, 0, read);
			        	//ColorLogger.write(buffer, 0, read);
			        //total_sent+=read;	        
			        read=input.available();
			        if(read>buffer_size)
				    	read=buffer_size;
			    }
			    output.flush();
			    //ColorLogger.log(log_header +" bytes="+ total_sent);
	    	//}
	    	//catch (SocketException e) {
			//	System.out.println("socket Exceptooin");
			//}
	    }
	}	
	
}
