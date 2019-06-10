package p;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class StreamTools {
	
	public static void copyStream(InputStream input, OutputStream output) throws IOException
	{
		int buffer_size=32768;
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
	        read=input.available();
	        if(read>buffer_size)
		    {
		    	read=buffer_size;
		    }
	    }
	    //System.out.println("Done writting");
	}

}
