package introsde.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.glassfish.jersey.client.ClientConfig;
import org.json.JSONObject;

public class UserInterface {
	
    public static void main(String[] args) throws ClientProtocolException, IOException
    {
    	Scanner input = new Scanner(System.in);
    	String name = null;
    	int operation = -1;
    	int idPerson = -1;
    	boolean findExistingId = false;
    	
    	String ENDPOINT = "https://fast-tundra-9608.herokuapp.com/businesslogic/getAllPeople";
    	
    	DefaultHttpClient client = new DefaultHttpClient();
    	HttpGet request = new HttpGet(ENDPOINT);
    	HttpResponse response = client.execute(request);
    	
    	BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

    	StringBuffer result = new StringBuffer();
    	String line = "";
    	while ((line = rd.readLine()) != null) {
    	    result.append(line);
    	}
    	
    	JSONObject o = new JSONObject(result.toString());
    	

    	while(findExistingId == false){
    		System.out.println("Choose one person from the list below");
	    	if(response.getStatusLine().getStatusCode() == 200){
	    		for(int i = 0; i < o.getJSONObject("people").getJSONArray("person").length(); i++){
	    			System.out.print(o.getJSONObject("people").getJSONArray("person").getJSONObject(i).get("idPerson")+" - ");
	    			System.out.print(o.getJSONObject("people").getJSONArray("person").getJSONObject(i).getString("lastname")+" ");
	    			System.out.println(o.getJSONObject("people").getJSONArray("person").getJSONObject(i).getString("firstname"));
	            }
	    	}
	    	
	    	System.out.println("\nChoose a person => ");
	    	idPerson = Integer.parseInt(input.nextLine());
	    	
	    	for(int i = 0; i < o.getJSONObject("people").getJSONArray("person").length(); i++){
				if(Integer.parseInt(o.getJSONObject("people").getJSONArray("person").getJSONObject(i).get("idPerson").toString()) == idPerson){
					findExistingId = true;
					name = o.getJSONObject("people").getJSONArray("person").getJSONObject(i).getString("lastname") + " " + o.getJSONObject("people").getJSONArray("person").getJSONObject(i).getString("firstname");
				}
	        }
	    	
	    	if(findExistingId == false){
	    		System.out.println("\nWrong choice! Please, try again!\n");
	    	}
    	}
    	
    	
    	System.out.println("Well-done! You have chosen to run the program as "+ name);
    	System.out.println("Loading ... ");
    	
    	System.out.println("\n\n\nWelcome back "+ name + "!");
    	System.out.println("Please, choose an operation from the MENU' below to proceed\n");

    	
    	while(operation != 0){
    		System.out.println("\nMENU'\n");
    		System.out.println("1 - Print person information");
    		System.out.println("2 - Set health profile (and verify if the goal is hit)- sleep");
    		System.out.println("3 - Set health profile (and verify if the goal is hit) - weight");
    		System.out.println("4 - Set goal - hours slept the previous night");
    		System.out.println("5 - Set goal - new weight");
    		System.out.println("0 - Exit");
    		
    		System.out.print("\nHow do you want to proceed?\n");
    		operation = Integer.parseInt(input.nextLine());
    		
    		if(operation < 0 || operation > 5){
    			System.out.print("\nOperation not allowed, try again!\n\n");
    		}
    		
    		switch(operation){
    		case 0:
    			System.out.println("\nThank you for using this application!");
    			break;
    		case 1:
    			ENDPOINT = "https://fast-tundra-9608.herokuapp.com/businesslogic/getSinglePerson/"+idPerson;
    	    	
    	    	client = new DefaultHttpClient();
    	    	request = new HttpGet(ENDPOINT);
    	    	response = client.execute(request);
    	    	
    	    	rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

    	    	result = new StringBuffer();
    	    	line = "";
    	    	while ((line = rd.readLine()) != null) {
    	    	    result.append(line);
    	    	}
    	    	
    	    	o = new JSONObject(result.toString());
    	    	
    	    	if(response.getStatusLine().getStatusCode() == 200){
    	    		
    	    		System.out.println("===============================================");
    	    		System.out.println("USER INFORMATION");
    	    		System.out.println("===============================================");
    	    		
    	    		System.out.println("Name: "+o.getJSONObject("person").getString("firstname"));
    	    		System.out.println("Surname: "+o.getJSONObject("person").getString("lastname"));
    	    		System.out.println("Username: "+o.getJSONObject("person").getString("username"));
    	    		System.out.println("E-mail: "+o.getJSONObject("person").getString("email"));
    	    		System.out.println("Birthdate: "+o.getJSONObject("person").getString("birthdate"));
    	    		
    	    		System.out.println("===============================================");
    	    		System.out.println("USER HEALTH PROFILE");
    	    		System.out.println("===============================================");
    	    		
    	    		for(int i = 0; i < o.getJSONObject("person").getJSONObject("currentHealth").getJSONArray("lifeStatus").length(); i++){
    	    			System.out.println("Measure: "+o.getJSONObject("person").getJSONObject("currentHealth").getJSONArray("lifeStatus").getJSONObject(i).getJSONObject("measureType").getString("measureName"));
    	    			System.out.println("Value: "+o.getJSONObject("person").getJSONObject("currentHealth").getJSONArray("lifeStatus").getJSONObject(i).get("measureValue"));
        	    		System.out.println();
    	            }
    	    		
    	    		System.out.println("===============================================");
    	    		System.out.println("USER GOALS");
    	    		System.out.println("===============================================");
    	    		
    	    		for(int i = 0; i < o.getJSONObject("person").getJSONObject("Goals").getJSONArray("Goal").length(); i++){
    	    			System.out.println("Goal: "+o.getJSONObject("person").getJSONObject("Goals").getJSONArray("Goal").getJSONObject(i).getJSONObject("measureType").getString("measureName"));
    	    			System.out.println("Value: "+o.getJSONObject("person").getJSONObject("Goals").getJSONArray("Goal").getJSONObject(i).get("goalValue"));
        	    		System.out.println("");
    	            }
    	    		
    	    	}
    	    	break;
    		case 2:
    			double value = -1;
    			while(value < 0 || value > 24){
    				System.out.println("Health profile - sleeping hours ");
    				System.out.println("New value: ");
    				value = Integer.parseInt(input.nextLine());
    				if(value < 0 || value > 24){
    					System.out.println("Value not allowed! Please, try again!");
    				}
    			}
    				
    				
				//PUT LIFE STATUS
		    	ENDPOINT = "https://ancient-lowlands-4805.herokuapp.com/centricprocess/setVerifyLifeStatus/"+idPerson;
		    	ClientConfig clientConfig = new ClientConfig();
				Client client3 = ClientBuilder.newClient(clientConfig);
				
				WebTarget service = client3.target(ENDPOINT);

		    	Response res = null;
				String putResp = null;
				
		    	String updateLifeStatus ="{"
		        			+ "\"measureValue\":" +value+","
		        			+ "\"measureName\": \""+"sleep"+"\""
		        			+"}";
		    	
		    	res = service.request(MediaType.APPLICATION_JSON_TYPE).put(Entity.json(updateLifeStatus));
		    	putResp = res.readEntity(String.class);
		    	
		    	if(res.getStatus() != 200 ){
		    		System.out.println("ERROR during updating! Please, try again!");
		    	}
    	    	
    	    	o = new JSONObject(putResp.toString());
    	    	
    	    	if(response.getStatusLine().getStatusCode() == 200){
    	    		String resp = o.getJSONObject("comparisonInformation").getString("result");
    	    		
    	    		System.out.println("sleep - updated life status => "+o.getJSONObject("comparisonInformation").getInt("lifeStatusValue"));
    	    		System.out.println("sleep - goal set => "+o.getJSONObject("comparisonInformation").getInt("goalValue"));
    	    		
    	    		if(resp.equals("ok")){
    	    			System.out.println("\nCONGRATULATION! GOAL HIT!!!");
    	    			String picture_url = o.getJSONObject("resultInformation").getString("picture_url");
    	    			System.out.println("Pretty pic for your success: "+picture_url);
    	    		}
    	    		else{
    	    			System.out.println("\nGOAL NOT HIT!!!");
    	    			System.out.println("Motivational phrase: "+o.getJSONObject("resultInformation").getString("quote"));
    	    		}
    	    	}
			
			break;
    			
    		case 3:
    			double value2 = -1;
    			while(value2 < 0){
    				System.out.println("Health profile - weight: ");
    				System.out.println("New value: ");
    				value2 = Integer.parseInt(input.nextLine());
    				if(value2 < 0){
    					System.out.println("Value not allowed! Please, try again!");
    				}
    			}
    				
				//PUT LIFE STATUS
		    	ENDPOINT = "https://ancient-lowlands-4805.herokuapp.com/centricprocess/setVerifyLifeStatus/"+idPerson;
		    	ClientConfig clientConfig1 = new ClientConfig();
				Client client1 = ClientBuilder.newClient(clientConfig1);
				
				WebTarget service1 = client1.target(ENDPOINT);

		    	Response res1 = null;
				String putResp1 = null;
				
		    	String updateLifeStatus1 ="{"
		        			+ "\"measureValue\":" +value2+","
		        			+ "\"measureName\": \""+"weight"+"\""
		        			+"}";
		    	
		    	res1 = service1.request(MediaType.APPLICATION_JSON_TYPE).put(Entity.json(updateLifeStatus1));
		    	putResp1 = res1.readEntity(String.class);
	    		
		    	if(res1.getStatus() != 200 ){
		    		System.out.println("ERROR during updating! Please, try again!");
		    	}

    	    	o = new JSONObject(putResp1.toString());
    	    	
    	    	if(response.getStatusLine().getStatusCode() == 200){
    	    		
    	    		System.out.println("weight - updated life status => "+o.getJSONObject("comparisonInformation").getInt("lifeStatusValue"));
    	    		System.out.println("weight - goal set => "+o.getJSONObject("comparisonInformation").getInt("goalValue"));

    	    		String resp = o.getJSONObject("comparisonInformation").getString("result");
    	    		if(resp.equals("ok")){
    	    			System.out.println("\nCONGRATULATION! GOAL HIT!!!");
    	    			String picture_url = o.getJSONObject("resultInformation").getString("picture_url");
    	    			System.out.println("Pretty pic for your success: "+picture_url);
    	    		}
    	    		else{
    	    			System.out.println("\nGOAL NOT HIT!!!");
    	    			System.out.println("Motivational phrase: "+o.getJSONObject("resultInformation").getString("quote"));
    	    		}
    	    	}
    			break;

    		case 4:
    			double value3 = -1;
    			while(value3 < 0 || value3 > 24){
    				System.out.println("Goal - sleeping hours: ");
    				System.out.println("New value: ");
    				value3 = Integer.parseInt(input.nextLine());
    				if(value3 < 0 || value3 > 24){
    					System.out.println("Value not allowed! Please, try again!");
    				}
    			}
    				
				//PUT GOAL
		    	ENDPOINT = "https://ancient-lowlands-4805.herokuapp.com/centricprocess/updatePersonGoal/"+idPerson;
		    	ClientConfig clientConfig4 = new ClientConfig();
				Client client4 = ClientBuilder.newClient(clientConfig4);
				
				WebTarget service4 = client4.target(ENDPOINT);

		    	Response res4 = null;
				String putResp4 = null;
				
		    	String updateGoal ="{"
		        			+ "\"goalValue\": "+value3+","
		        			+ "\"measureName\": \""+"sleep"+"\""
		        			+"}";
		    	
		    	res4 = service4.request(MediaType.APPLICATION_JSON_TYPE).put(Entity.json(updateGoal));
		    	putResp4 = res4.readEntity(String.class);
		    	
		    	if(res4.getStatus() != 200 ){
		    		System.out.println("ERROR during updating! Please, try again!");
		    	}else{
		    		System.out.println("Goal updated successfully!");
		    	}
			
			break;
    			
    		case 5:
    			double value4 = -1;
    			while(value4 < 0){
    				System.out.println("Goal - weight: ");
    				System.out.println("New value: ");
    				value4 = Integer.parseInt(input.nextLine());
    				if(value4 < 0 || value4 > 10000){
    					System.out.println("Value not allowed! Please, try again!");
    				}
    			}
    			
				//PUT GOAL
		    	ENDPOINT = "https://ancient-lowlands-4805.herokuapp.com/centricprocess/updatePersonGoal/"+idPerson;
		    	ClientConfig clientConfig5 = new ClientConfig();
				Client client5 = ClientBuilder.newClient(clientConfig5);
				
				WebTarget service5 = client5.target(ENDPOINT);

		    	Response res5 = null;
				String putResp5 = null;
				
		    	String updateGoal5 ="{"
		        			+ "\"goalValue\":" +value4+","
		        			+ "\"measureName\": \""+"weight"+"\""
		        			+"}";
		    	
		    	res5 = service5.request(MediaType.APPLICATION_JSON_TYPE).put(Entity.json(updateGoal5));
		    	putResp5 = res5.readEntity(String.class);
		    	
		    	if(res5.getStatus() != 200 ){
		    		System.out.println("ERROR during updating! Please, try again!");
		    	}else{
		    		System.out.println("Goal updated successfully!");
		    	}
			break;
    		}
    	}
	}
}
