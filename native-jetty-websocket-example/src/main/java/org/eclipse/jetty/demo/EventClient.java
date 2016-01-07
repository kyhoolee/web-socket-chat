package org.eclipse.jetty.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.concurrent.Future;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.client.WebSocketClient;

public class EventClient
{
    public static void main(String[] args)
    {
        URI uri = URI.create("ws://localhost:8080/events/");

        WebSocketClient client = new WebSocketClient();
        try
        {
            try
            {
                client.start();
                // The socket that receives events
                EventSocket socket = new EventSocket();
                // Attempt Connect
                Future<Session> fut = client.connect(socket, uri);
                // Wait for Connect
                Session session = fut.get();
                // Send a message
                session.getRemote().sendString("Hello ");
                
                // Close session
                session.close();
            }
            finally
            {
                client.stop();
            }
        }
        catch (Throwable t)
        {
            t.printStackTrace(System.err);
        }
    }
    
    
    public static void getInput() {
    	System.out.println("Enter something here : ");
    	   
    	try{
    	    BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
    	    
    	    while(true) {
	    	    String s = bufferRead.readLine();
	    	      
	    	    System.out.println(s);
    	    }
    	}
    	catch(IOException e)
    	{
    		e.printStackTrace();
    	}
    }
}
