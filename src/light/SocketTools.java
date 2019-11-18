package light;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SocketTools {

	
	
	public static void copyStream(InputStream input, OutputStream output) throws IOException
	{
		int buffer_size=32768;
		//long total_sent=0;
	    byte[] buffer = new byte[buffer_size];
	    while(true)
	    {
			    output.write(input.read());
			    
			    int read=input.available();
			    if(read>buffer_size)
			    	read=buffer_size;
			    
			    while (read>0)
			    {
			    	input.read(buffer, 0, read);
			        output.write(buffer, 0, read);
			        //if(printSocketComunication)
			        	//System.out.write(buffer, 0, read);
			        	//ColorLogger.write(buffer, 0, read);
			        //total_sent+=read;	        
			        read=input.available();
			        if(read>buffer_size)
				    	read=buffer_size;
			    }
			    output.flush();
	    }
	}	
	
}
