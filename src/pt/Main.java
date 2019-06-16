package pt;


public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		boolean is_server=true;
		String configFile=null;
		for (int i = 0; i < args.length; i++) {
			if(args[i].charAt(0)=='-')
			{
				switch (args[i]) {
				case "-s":
					is_server=true;
					break;
				case "-c":
					is_server=false;
					break;
				case "-configFile":
					configFile=args[i+1];
					i++;
					break;
				default:
					break;
				}
			}
		}
		if(configFile!=null)
		Config.load(configFile);
		
		if(is_server==true)
		{
			System.out.println("starting server...");
			Server.start();
		}
		else
		{
			System.out.println("starting client...");
			Client.start();
		}
	}

}
