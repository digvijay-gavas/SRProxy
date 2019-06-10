package p;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class PClient {

	static int C_PORT=5050;
	static String C_HOST="localhost";
	
	static int ACCESS_PORT=8080;
	static String ACCESS_HOST="localhost";
	public static void main(String[] args) throws IOException {
		Socket sClientSocket = null;
		try 
		{
			sClientSocket=new Socket(C_HOST,C_PORT);
			System.out.println("sClient connected ");
			while(true)
			{
				//System.out.println("cClient connecting..");
				try 
				{
					Socket cClientSocket=new Socket(ACCESS_HOST,ACCESS_PORT);
					
					while (!cClientSocket.isClosed())
					{
						
						if(sClientSocket.getInputStream().available()>0)
						{
							System.out.println(sClientSocket.getInputStream().available()+" bytes from sClientSocket to  cClientSocket");
							//sClientSocket.getInputStream().transferTo(cClientSocket.getOutputStream());
							StreamTools.copyStream(sClientSocket.getInputStream(),cClientSocket.getOutputStream());
							//System.out.println(sClientSocket.getInputStream().available()+" done sClientSocket to  cClientSocket");
						}
						if(cClientSocket.getInputStream().available()>0)
						{
							System.out.println(cClientSocket.getInputStream().available()+" bytes from cClientSocket to sClientSocket");
							//cClientSocket.getInputStream().transferTo(sClientSocket.getOutputStream());
							StreamTools.copyStream(cClientSocket.getInputStream(),sClientSocket.getOutputStream());
							//System.out.println(cClientSocket.getInputStream().available()+" done cClientSocket to sClientSocket");
							cClientSocket.close();
						}
					}
					cClientSocket.close();
					//System.out.println("cClient closed");
				}
				catch (Exception e) 
				{
					//System.out.println("cClient connection failed");
					//e.printStackTrace();
				}
				
			}
		}
		catch (Exception e) 
		{
			System.out.println("sClient connection failed");
			e.printStackTrace();
			sClientSocket.close();
		}
		
	}
	
}
