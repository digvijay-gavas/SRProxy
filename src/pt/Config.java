package pt;

public class Config {

	// Internals
	static int[] ports= {3030,3031,3032,3034};
	static String host="localhost";
	static long bind_timeout=10000;
	
	// Externals 
	static int 
	sync_port=4041,
	port=4040,
	access_port=7070,
	client_port=8080;
	
	static String
	access_host="localhost",
	client_host="localhost";
}
