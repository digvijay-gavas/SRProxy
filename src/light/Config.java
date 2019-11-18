package light;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;

public class Config implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Using
	static long bind_timeout=-10000;
	static int 
	port=4040,
	access_port=7070,
	client_port=8081,
	retry_interval=500;
	
	static String
	host="localhost",
	access_host="localhost",
	client_host="localhost",
	proxy_part_type="server";
	 
	
	
	//Garbage
	static int[] ports= {3030,3031,3032,3034};
	
	//Methods
	public static void save(String configFile) throws FileNotFoundException, IOException
	{
		Properties properties=new Properties();
		
		properties.setProperty("config.proxy_part_type", Config.proxy_part_type);
		
		properties.setProperty("config.port", ""+Config.port);
		properties.setProperty("config.access_port", ""+Config.access_port);
		properties.setProperty("config.client_port", ""+Config.client_port);
		
		properties.setProperty("config.host", Config.host);
		properties.setProperty("config.access_host", Config.access_host);
		properties.setProperty("config.client_host", Config.client_host);
		
		properties.setProperty("config.bind_timeout", ""+Config.bind_timeout);
		
		properties.store(new FileOutputStream(configFile),"Properties");
		
		
	}
	public static void load(String configFile) throws IOException
	{
		Properties properties=new Properties();
		properties.load(new FileInputStream(configFile));
		Config.proxy_part_type=properties.getProperty("config.proxy_part_type", Config.proxy_part_type);
		
		Config.port=Integer.parseInt(properties.getProperty("config.port", ""+Config.port));
		Config.access_port=Integer.parseInt(properties.getProperty("config.access_port", ""+Config.access_port));
		Config.client_port=Integer.parseInt(properties.getProperty("config.client_port", ""+Config.client_port));
		
		Config.host=properties.getProperty("config.host", Config.host);
		Config.access_host=properties.getProperty("config.access_host", Config.access_host);
		Config.client_host=properties.getProperty("config.client_host", Config.client_host);
		
		Config.bind_timeout=Long.parseLong(properties.getProperty("config.bind_timeout", ""+Config.bind_timeout));
		
		
		properties.store(new FileOutputStream(configFile),"Properties");
	}
}
