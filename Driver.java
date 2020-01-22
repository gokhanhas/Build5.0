package BuilSocketServer;

import java.io.IOException;

public class Driver {

	private static String pJson;
	
	public static void main(String[] args) throws IOException {
//		testPostJson();
//		testMainBuild();
		testBuildSocketServer();
		
	}
	
	/**
	 * example input
	 * {"object_type": "ot","operation": "op", "status": "st","description": "de", "method": "change me", "card_id": "change me", "project_name": "my-app", "repository_url": "https://github.com/gokhanhas/my-app.git", "github_login": "gokhanhas",  "github_password": "Deneme123456789"}
	 * 
	 */
/*test input json 
 
{"object_type": "ot",
 "operation": "op",
 "status": "st",
 "description": "de",
 "method": "change me",
 "card_id": "change me",
 "project_name": "my-app",
 "repository_url": "https://github.com/gokhanhas/my-app.git",
 "github_login": "gokhanhas", 
 "github_password": "Deneme123456789"}			 
 */
    public static void testBuildSocketServer() {
	    	try{
	        	BuilSocketServer app = new BuilSocketServer("localhost");
	            System.out.println("\r\nRunning Server: " + 
	                    "Host=" + app.getSocketAddress().getHostAddress() + 
	                    " Port=" + app.getPort());
	            
//	            Main.build(app.listen());
	        }
	    	catch(Exception e){
	    		//System.err.println("Exception");
			}
		
    }
	
	/**
	 * test for Main.build()  method
	 */
	/*public static void testMainBuild(){
		pJson = 
				"{\n" + 
				"        \"object_type\": \"ot\" ,\n" + 
				"        \"operation\": \"op\",\n" + 
				"        \"status\": \"st\",\n" + 
				"        \"description\": \"de\",\n" + 
				"        \"method\": \"change me\",\n" + 
				"        \"card_id\": \"change me\",\n" + 
				"        \"project_name\": \"my-app\",\n" +//todo directory alanı eklenecek
				"        \"repository_url\": \"https://github.com/gokhanhas/my-app.git\",\n" + 
				"        \"github_login\": \"gokhanhas\",\n" + 
				"        \"github_password\": \"Deneme123456789\"\n" +  //ToDo Şİfreyi doğru gir    
				"}\n";
				
		System.out.println(pJson);
		Main.build(pJson);

	}*/
	
	public static void testPostJson() throws IOException{
		
		String url  = "http://dummy.restapiexample.com/api/v1/create";
		String json = "{\"name\":\"Mustafa Alp\",\"salary\":\"10000\",\"age\":\"29\"}";
		
		String responce = (HttpRequest.PostJson(url, json));
		System.out.print(responce);
	}
	
	

}
//url username password eklenicek
 