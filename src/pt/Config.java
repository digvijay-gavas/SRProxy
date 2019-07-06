package pt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Properties;

public class Config implements Serializable{

	
	// Using
	static long bind_timeout=-10000;
	static int 
	sync_port=4041,
	port=4040,
	access_port=7070,
	client_port=8081,
	retry_interval=100;
	
	static String
	host="localhost",
	access_host="localhost",
	client_host="localhost",
	proxy_part_type="server";
	 
	
	static boolean enableANSIColor=true;
	
	
	//Garbage
	static int[] ports= {3030,3031,3032,3034};
	
	//Methods
	public static void save(String configFile) throws FileNotFoundException, IOException
	{
		Properties properties=new Properties();
		
		properties.setProperty("config.proxy_part_type", Config.proxy_part_type);
		
		properties.setProperty("config.port", ""+Config.port);
		properties.setProperty("config.sync_port", ""+Config.sync_port);
		properties.setProperty("config.access_port", ""+Config.access_port);
		properties.setProperty("config.client_port", ""+Config.client_port);
		
		properties.setProperty("config.host", Config.host);
		properties.setProperty("config.access_host", Config.access_host);
		properties.setProperty("config.client_host", Config.client_host);
		
		properties.setProperty("config.bind_timeout", ""+Config.bind_timeout);
		properties.setProperty("config.logging.enableANSIColor",""+Config.enableANSIColor);
		
		properties.store(new FileOutputStream(configFile),"Properties");
		
		
	}
	public static void load(String configFile) throws IOException
	{
		Properties properties=new Properties();
		properties.load(new FileInputStream(configFile));
		Config.proxy_part_type=properties.getProperty("config.proxy_part_type", Config.proxy_part_type);
		
		Config.port=Integer.parseInt(properties.getProperty("config.port", ""+Config.port));
		Config.sync_port=Integer.parseInt(properties.getProperty("config.sync_port", ""+Config.sync_port));
		Config.access_port=Integer.parseInt(properties.getProperty("config.access_port", ""+Config.access_port));
		Config.client_port=Integer.parseInt(properties.getProperty("config.client_port", ""+Config.client_port));
		
		Config.host=properties.getProperty("config.host", Config.host);
		Config.access_host=properties.getProperty("config.access_host", Config.access_host);
		Config.client_host=properties.getProperty("config.client_host", Config.client_host);
		
		Config.bind_timeout=Long.parseLong(properties.getProperty("config.bind_timeout", ""+Config.bind_timeout));
		
		Config.enableANSIColor=Boolean.parseBoolean(properties.getProperty("config.logging.enableANSIColor", ""+Config.enableANSIColor));
		
		properties.store(new FileOutputStream(configFile),"Properties");
	}
}
