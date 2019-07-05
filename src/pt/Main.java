package pt;

import java.io.File;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		ColorLogger.enableANSIColor(Config.enableANSIColor);
		// Loading configFile if given
		boolean is_server=true;
		for (int i = 0; i < args.length; i++) {
			if(args[i].equalsIgnoreCase("-configFile"))
			{
				try {
					Config.load(args[i+1]);
				}catch (Exception e) {
					ColorLogger.log("<error>invalid -configFile "+e.getLocalizedMessage()+"</error>");
					System.exit(1);
				}
				break;
			}
			else if(args[i].equalsIgnoreCase("-configSave"))
			{
				try {
					Config.save(args[i+1]);
					ColorLogger.log("<success>saved config file to "+args[i+1]+" </success>");
					System.exit(0);
				}catch (Exception e) {
					ColorLogger.log("<error>invalid -configSave "+e.getLocalizedMessage()+"</error>");
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
				/*case "-configSave":
					i++;
					break;*/
				default:
					ColorLogger.log("<error>invalid option "+args[i]+"</error>");
					System.exit(1);
					break;
				}
			}
			else
			{
				ColorLogger.log("<error>invalid value "+args[i]+"</error>");
				System.exit(1);
			}
		}
		
		ColorLogger.enableANSIColor(Config.enableANSIColor);
		
		if(Config.proxy_part_type.equalsIgnoreCase("server"))
		{
			System.out.println("starting server...");
			Server.start();
		}
		else if(Config.proxy_part_type.equalsIgnoreCase("client"))
		{
			System.out.println("starting client...");
			Client.start();
		}
		else
		{
			ColorLogger.log("<error>invalid value Config.proxy_part_type="+Config.proxy_part_type+"</error>");
			System.exit(1);
		}
	}

}
