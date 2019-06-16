package pt;

public class Status {
	private static String MODE="split.server";
	private static int SocketBindThreadCount=0;
	public static void increaseSocketBindThreadCount()
	{
		SocketBindThreadCount++;
	}
	public static void decreaseSocketBindThreadCount()
	{
		SocketBindThreadCount--;
	}
	public static void getSocketBindThreadCount()
	{
		SocketBindThreadCount++;
	}
}
