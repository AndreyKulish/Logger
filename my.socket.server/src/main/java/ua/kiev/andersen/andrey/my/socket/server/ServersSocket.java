package ua.kiev.andersen.andrey.my.socket.server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;

public class ServersSocket {
	
	private static final Logger LOGGER = Logger.getLogger(ServersSocket.class.getSimpleName());
	private static final Logger LOGGER2 = Logger.getLogger("Server");
	
	public static void main(String args[]) throws IOException{

		LOGGER.info("Server Started");
		
	    BufferedReader in = null;
	    PrintWriter    out= null;

	    ServerSocket servers = null;
	    Socket       fromclient = null;
	    
	      servers = new ServerSocket(4444);
	      fromclient= servers.accept();

	    in  = new BufferedReader(new InputStreamReader(fromclient.getInputStream()));
	    out = new PrintWriter(fromclient.getOutputStream(),true);
	    String         input,output;

	    System.out.println("Wait for messages");
	    while ((input = in.readLine()) != null) {
	     if (input.equalsIgnoreCase("exit")){
	    	 LOGGER.info("Server Drop");
	    	 LOGGER2.info("Server Drop");
	    	 break;
	     }
	     
	     if(input.equalsIgnoreCase("1")){
	    	 LOGGER.info("Server 1 method call");
	    	 LOGGER2.info("Server 1 method call");
	    	CallMethods.firstMethod(); 
	     }
		if(input.equalsIgnoreCase("2")){
			LOGGER.info("Server 2 method call");
			LOGGER2.info("Server 2 method call");
			CallMethods.secondMethod();	 
			     }
		if(input.equalsIgnoreCase("3")){
			LOGGER.info("Server 3 method call");
			LOGGER2.info("Server 3 method call");
			CallMethods.firhdMethod();
		}
		
		if(input.equalsIgnoreCase("error")){
			try{
				int i = 5/0;
			}catch(Exception e){
				LOGGER.error("5/0", e);
				LOGGER2.error("5/0", e);
			}
		}
	     out.println("S ::: "+input);
	     System.out.println(input);
	    }
	    
	    out.close();
	    in.close();
	    fromclient.close();
	    servers.close();
	    
	    
	}

}
