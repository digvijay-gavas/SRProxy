package pt;

import java.io.IOException;
import java.io.StringWriter;

public class ColorLogger {
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
	public static final String ANSI_CLEAR_SCREEN = "\u001B[2J";
	
	public static void log(String log)
	{
		StringWriter logw=new StringWriter();
		log=log.replaceAll("<error>", ANSI_RED);
		log=log.replaceAll("</error>", ANSI_RESET);
		log=log.replaceAll("<warn>", ANSI_YELLOW);
		log=log.replaceAll("</warn>", ANSI_RESET);
		log=log.replaceAll("<success>", ANSI_GREEN);
		log=log.replaceAll("</success>", ANSI_RESET);
		log=log.replaceAll("<info>", ANSI_CYAN);
		log=log.replaceAll("</info>", ANSI_RESET);
		System.out.println(log);
	}
	
	public static void log(String log,boolean doClear)
	{
		//if(doClear)
			//System.out.print(ANSI_CLEAR_SCREEN);
		log(log);
	}
	
	public static void clear()
	{
		 try {
             if (System.getProperty("os.name").contains("Windows"))
                 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
             else
                 Runtime.getRuntime().exec("clear");
         } catch (IOException | InterruptedException ex) {ex.printStackTrace();}
	}
	public static void enableColor()
	{
		 clear();
	}
}
