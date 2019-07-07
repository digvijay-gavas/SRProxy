package pt;

import java.net.ServerSocket;
import java.net.Socket;

public class SyncSocket extends Thread {
	byte notifyByte;
	int noOfConnectionRequested = 0;
	String host;
	int port;
	ServerSocket sync_server_Socket;
	Socket sync_Socket;
	boolean isServer=true;
	int retry_interval;

	public SyncSocket(String host,int port,byte notifyByte,int retry_interval) {
		this.host=host;
		this.port=port;
		this.notifyByte = notifyByte;
		this.retry_interval=retry_interval;
		isServer=false;
		start();
	}
	
	public SyncSocket(int port,byte notifyByte) {
		this.port=port;
		this.notifyByte = notifyByte;
		isServer=true;
	}
	
	public void requestConnection()
	{
		noOfConnectionRequested++;
		try {
			server_sync();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		try {
			while(true)
			{
				try {
					client_sync();
					Thread.sleep(retry_interval);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void serveConnection()
	{
		while(noOfConnectionRequested<=0)
		{
			try {
				Thread.sleep(retry_interval);
				//System.out.println("Thread ..");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		noOfConnectionRequested--;
	}

	public void client_sync() throws Exception {
		boolean isInWaitState = false;
		while (true) 
		{
			try {
				if (sync_Socket == null) 
				{
					if (!isInWaitState)
						ColorLogger.log("<warn>Waiting for "+this.host+":"+this.port+"</warn>");
					sync_Socket = new Socket(this.host, this.port);
					ColorLogger.logln("--> <success>connected</success>");
				} else 
				{
					int read=-1;
					try
					{
						read = sync_Socket.getInputStream().read();
					}
					catch (Exception e) {
						if (!isInWaitState)
							ColorLogger.log("<warn>Waiting for "+this.host+":"+this.port+"</warn>");
						sync_Socket = new Socket(this.host, this.port);
						ColorLogger.logln("--> <success>connected</success>");
					}
					if (read == (int) notifyByte) {
						noOfConnectionRequested++;
						//ColorLogger.log("<info>noOfConnectionRequested</info>="+noOfConnectionRequested);
					}

				}

			} 
			catch (Exception e) 
			{
				isInWaitState = true;
				Thread.sleep(retry_interval);
				continue;
			}
			break;
		}
	}
	
	public void server_sync() throws Exception {
		if(sync_server_Socket==null)
			sync_server_Socket=new ServerSocket(this.port);

		while(this.noOfConnectionRequested>0)
		{
			try {
				sync_Socket.getOutputStream().write(notifyByte);
				sync_Socket.getOutputStream().flush();
				noOfConnectionRequested--;
				break;
			}
			catch (Exception e) {
				ColorLogger.log("<warn>Waiting on "+this.port+"</warn>");
				sync_Socket=sync_server_Socket.accept();
				ColorLogger.logln("--> <success>connected</success>");
			}
		}
		
	}

	public static void main(String[] args) throws Exception {
		SyncSocket socket = new SyncSocket(Config.host,Config.sync_port,(byte)10,100);
		socket.start();

		while (true) {
			// System.out.println(socket.noOfConnectionRequested);
			Thread.sleep(1000);
		}
		/*
		 * try { socket.validateConnection(); }catch (Exception e) {
		 * e.printStackTrace(); } Thread.sleep(1000); try { socket.validateConnection();
		 * }catch (Exception e) { e.printStackTrace(); }
		 */
	}

}
