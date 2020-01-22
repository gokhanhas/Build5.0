package BuilSocketServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.commons.httpclient.methods.PutMethod;

/**
 * java class that sends http requests
 * 
 *
 */
public class HttpRequest {

	/**
	 *	Example 
	 *		@param postUrl example:
	 * 			"http://dummy.restapiexample.com/api/v1/create"
	 *		@param jsonInputString example:
	 * 			"{\"name\":\"Mustafa Alp\",\"salary\":\"10000\",\"age\":\"29\"}"
	 * 
	 * method that posts
	 * 
	 * @throws IOException
	 */
	public static String PostJson(String postUrl ,String jsonInputString) throws IOException{
		// TODO Auto-generated method stub
		URL url = new URL (postUrl);
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		
		//Set the Request Method
		con.setRequestMethod("POST");
		
		//Set the Request Content-Type Header Parameter
		con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		
		//Set Response Format Type
		con.setRequestProperty("Accept", "application/json");
		
		//Ensure the Connection Will Be Used to Send Content
		con.setDoOutput(true);
				
		try(OutputStream os = con.getOutputStream()) {
		    byte[] input = jsonInputString.getBytes("utf-8");
		    os.write(input, 0, input.length);           
		}
		con.disconnect();
		
		try(BufferedReader br = new BufferedReader(
			new InputStreamReader(con.getInputStream(), "utf-8"))) {
		    StringBuilder response = new StringBuilder();
		    String responseLine = null;
		    while ((responseLine = br.readLine()) != null) {
		        response.append(responseLine.trim());
		    }
		    
		    con.disconnect();
		    return response.toString();
			
		}
		
//	return "";
	}
	
	
}