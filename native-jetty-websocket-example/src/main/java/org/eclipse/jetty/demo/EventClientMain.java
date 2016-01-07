package org.eclipse.jetty.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

import javax.websocket.ContainerProvider;
import javax.websocket.WebSocketContainer;

public class EventClientMain {
	public static void main(String[] args) {
		
		try {

			String dest = "ws://localhost:8080/events/";
			EventClientSocket socket = new EventClientSocket();
			WebSocketContainer container = ContainerProvider.getWebSocketContainer();
			container.connectToServer(socket, new URI(dest));

			socket.getLatch().await();
			
			getInput(socket);

		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
	
	
    public static void getInput(EventClientSocket socket) {
    	System.out.println("Enter something here : ");
    	   
    	try{
    	    BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
    	    
    	    while(true) {
	    	    String s = bufferRead.readLine();
	    	      
	    	    System.out.println("send " + s + " ...");
	    	    socket.sendMessage(s);
    	    }
    	}
    	catch(IOException e)
    	{
    		e.printStackTrace();
    	}
    }
}
