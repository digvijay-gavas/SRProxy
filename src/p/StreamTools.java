package p;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class StreamTools {
	
	public static void copyStream(InputStream input, OutputStream output) throws IOException
	{
	    byte[] buffer = new byte[32768];
	    int read=input.available();
	    
	    while (read>0)
	    {
	    	input.read(buffer, 0, read);
	        output.write(buffer, 0, read);
	        read=input.available();
	    }
	    //System.out.println("Done writting");
	}

}
