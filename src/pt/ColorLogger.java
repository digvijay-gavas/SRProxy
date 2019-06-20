package pt;

import java.io.IOException;
import java.io.StringWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



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
	
	private static boolean enableANSIColor=true;
	
	public static void log(String log)
	{
		if (enableANSIColor)
		{
			log=log.replaceAll("<error>", ANSI_RED);
			log=log.replaceAll("<warn>", ANSI_YELLOW);
			log=log.replaceAll("<success>", ANSI_GREEN);
			log=log.replaceAll("<info>", ANSI_CYAN);
			Pattern p =Pattern.compile("<[ ]*/[ ]*[a-zA-Z0-9]*[ ]*>") ;
			Matcher m = p.matcher(log); 
			log = m.replaceAll(ANSI_RESET);
		}
		else
		{
			Pattern p =Pattern.compile("<[ ]*[/a-zA-Z0-9]*[ ]*>") ;
			Matcher m = p.matcher(log); 
			log = m.replaceAll("");
		}
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
	public static void enableANSIColor(boolean enableANSIColor1)
	{
		enableANSIColor=enableANSIColor1;
		if(enableANSIColor)
			clear();
	}
}
