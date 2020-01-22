package BuilSocketServer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class BuilSocketServer {
    private ServerSocket server;
    
    public BuilSocketServer(String ipAddress) throws Exception {
        if (ipAddress != null && !ipAddress.isEmpty()) 
          this.server = new ServerSocket(50000, 1, InetAddress.getByName(ipAddress));
        else 
          this.server = new ServerSocket(50000, 1, InetAddress.getLocalHost());
    }
    
    public String listen() throws Exception {
        String data = null;
        Socket client = this.server.accept();
        String clientAddress = client.getInetAddress().getHostAddress();
        System.out.println("\r\nNew connection from " + clientAddress);
        
        BufferedReader in = new BufferedReader(
                new InputStreamReader(client.getInputStream()));

        StringBuilder json = new StringBuilder("");
        
        while ( (data = in.readLine()) != null ) {
        	json.append(data);
            System.out.println("\r\nMessage from " + clientAddress + ": " + data);
            if(data.contains("}"))
            	break;
        }
        
        return json.toString();
               
    }
    public InetAddress getSocketAddress() {
        return this.server.getInetAddress();
    }
    
    public int getPort() {
        return this.server.getLocalPort();
    }

}