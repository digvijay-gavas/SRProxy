package light;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SocketBindThread extends Thread {

	static ThreadGroup receiveGroup=new ThreadGroup("Receiving Threads");
	static ThreadGroup sendGroup=new ThreadGroup("Sending Threads");
	public static void bind(Socket socket1,Socket socket2) {
		String sockets="socket("+socket1.getLocalPort()+":"+socket1.getPort()+") and socket("+socket2.getLocalPort()+":"+socket2.getPort()+")";
		
		
		new Thread(receiveGroup,new Runnable() {
			
			@Override
			public void run() {
				try {
					
					SocketTools.copyStream(socket1.getInputStream(),socket2.getOutputStream());
					Logger.getGlobal().log(Level.INFO, "Threads:receiveGroup "+receiveGroup.activeCount()+"| Closed  "+sockets);
				} catch (IOException e) {Logger.getGlobal().log(Level.INFO, "Threads:receiveGroup "+receiveGroup.activeCount()+"| Binding "+sockets);
					Logger.getGlobal().log(Level.INFO, "Threads:receiveGroup "+receiveGroup.activeCount()+"| Closed  "+sockets);
					//Logger.getGlobal().log(Level.WARNING,"", e);
					try {
						socket1.close();
						socket2.close();
					} catch (IOException e1) {
						Logger.getGlobal().log(Level.WARNING,"", e);
						e1.printStackTrace();
					}
					
				}
				
			}
		}).start();
		
		new Thread(sendGroup,new Runnable() {
			
			@Override
			public void run() {
				try {
					Logger.getGlobal().log(Level.INFO, "Threads:sendGroup    "+sendGroup.activeCount()+"| Binding "+sockets);
					SocketTools.copyStream(socket2.getInputStream(),socket1.getOutputStream());
					Logger.getGlobal().log(Level.INFO, "Threads:sendGroup    "+sendGroup.activeCount()+"| Closed  "+sockets);
				} catch (IOException e) {
					Logger.getGlobal().log(Level.INFO, "Threads:sendGroup    "+sendGroup.activeCount()+"| Closed  "+sockets);
					//Logger.getGlobal().log(Level.WARNING,"", e);
					try {
						socket1.close();
						socket2.close();
					} catch (IOException e1) {
						Logger.getGlobal().log(Level.WARNING,"", e);
						e1.printStackTrace();
					}
					
				}
				
			}
		}).start();
	}
	

	
}
