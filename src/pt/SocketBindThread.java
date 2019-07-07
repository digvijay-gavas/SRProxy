package pt;

import java.io.IOException;
import java.net.Socket;

public class SocketBindThread extends Thread {
	//Socket socket1,socket2;
	//boolean printSocketComunication;
	static ThreadGroup receiveGroup=new ThreadGroup("Receiving Threads");
	static ThreadGroup sendGroup=new ThreadGroup("Sending Threads");
	public static void bind(Socket socket1,Socket socket2,boolean printSocketComunication) {
		//this.socket1=socket1;
		//this.socket2=socket2;
		//this.printSocketComunication=printSocketComunication;
		String sockets="socket("+socket1.getLocalPort()+":"+socket1.getPort()+") and socket("+socket2.getLocalPort()+":"+socket2.getPort()+")";
		
		
		new Thread(receiveGroup,new Runnable() {
			
			@Override
			public void run() {
				try {
					ColorLogger.logln("<info>Threads:receiveGroup "+receiveGroup.activeCount()+"</info>| <success>Binding "+sockets+"</success>",true);
					SocketTools.copyStream(socket1.getInputStream(),socket2.getOutputStream(),printSocketComunication);
					ColorLogger.logln("<info>Threads:receiveGroup"+(receiveGroup.activeCount()-1)+"</info>| <warn>Closed "+sockets+"</warn>",true);
				} catch (IOException e) {
					ColorLogger.logln("<info>Threads:receiveGroup "+(receiveGroup.activeCount()-1)+"</info>| <warn>Closed "+sockets+"</warn>",true);
					try {
						socket1.close();
						socket2.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
				}
				
			}
		}).start();
		
		new Thread(sendGroup,new Runnable() {
			
			@Override
			public void run() {
				try {
					ColorLogger.logln("<info>Threads:sendGroup    "+sendGroup.activeCount()+"</info>| <success>Binding "+sockets+"</success>",true);
					SocketTools.copyStream(socket2.getInputStream(),socket1.getOutputStream(),printSocketComunication);
					ColorLogger.logln("<info>Threads:sendGroup    "+(sendGroup.activeCount()-1)+"</info>| <warn>Closed "+sockets+"</warn>",true);
				} catch (IOException e) {
					ColorLogger.logln("<info>Threads:sendGroup    "+(sendGroup.activeCount()-1)+"</info>| <warn>Closed "+sockets+"</warn>",true);
					try {
						socket1.close();
						socket2.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
				}
				
			}
		}).start();
	}
	
	
}
