package light;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		// Loading configFile if given
		for (int i = 0; i < args.length; i++) {
			if(args[i].equalsIgnoreCase("-configFile"))
			{
				try {
					Config.load(args[i+1]);
				}catch (Exception e) {
					Logger.getGlobal().log(Level.SEVERE,"invalid -configFile", e);
					System.exit(1);
				}
				break;
			}
			else if(args[i].equalsIgnoreCase("-configSave"))
			{
				try {
					Config.save(args[i+1]);
					Logger.getGlobal().log(Level.INFO,"saved config file to "+args[i+1]);
					System.exit(0);
				}catch (Exception e) {
					Logger.getGlobal().log(Level.SEVERE,"invalid -configSave", e);
					System.exit(1);
				}
				break;
			}
		}
		
		// overriding configFile option if given as args
		for (int i = 0; i < args.length; i++) {
			if(args[i].charAt(0)=='-')
			{
				switch (args[i]) {
				case "-s":
					Config.proxy_part_type="server";
					break;
				case "-c":
					Config.proxy_part_type="client";
					break;
				case "-configFile":
					i++;
					break;
				default:
					Logger.getGlobal().log(Level.SEVERE,"invalid option "+args[i]);
					System.exit(1);
					break;
				}
			}
			else
			{
				Logger.getGlobal().log(Level.SEVERE,"invalid value "+args[i]);
				System.exit(1);
			}
		}
		
		
		if(Config.proxy_part_type.equalsIgnoreCase("server"))
		{
			System.out.println("starting server...");
			Server2.start();
		}
		else if(Config.proxy_part_type.equalsIgnoreCase("client"))
		{
			System.out.println("starting client...");
			Client2.start();
		}
		else
		{
			Logger.getGlobal().log(Level.SEVERE,"invalid value Config.proxy_part_type="+Config.proxy_part_type);
			System.exit(1);
		}
	}

}
