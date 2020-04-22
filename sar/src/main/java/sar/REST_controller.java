package sar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import javax.ws.rs.*;
import javax.ws.rs.core.*;
import sar.accountsManager;

@Path("/")
public class REST_controller {
	private BoundaryInterfaceAccounts bi = new accountsManager();
	private BoundaryInterfaceRides bir = new ridesManager();
	private BoundaryInterfaceReports birt = new reportsManager();
	private BoundaryInterfaceJson bij = new JsonManager();
	@Path("accounts")
	@POST
	//@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response makeAccounts(String json, @Context UriInfo uriInfo) {
		
		Gson gson = new Gson();
        accounts T = gson.fromJson(json, accounts.class);
        Gson gson1 = new GsonBuilder().setPrettyPrinting().create();
        if(accountsManager.checkphone(T.get_phone())) {
        	bi.createAccount(T);
        	UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        	builder.path(Integer.toString(T.get_aid()));
        	JsonObject id = new JsonObject();
        	id.addProperty("aid", T.get_aid());
        	
        	String s = gson1.toJson(id);
        	return Response.created(builder.build()).entity(s).build();
        }
        else {
        	JsonObject error = bij.Json_invalid_phone_response();
        	String s = gson1.toJson(error);
        	return Response.status(400).entity(s).build();
        }   		
	}
	
	@Path("accounts/{id}/status")
	@PUT
	//@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response activateAccounts(@PathParam("id") int lid, String json) {
		
		Gson gson = new Gson();
        accounts T = gson.fromJson(json, accounts.class);
        if(T.get_status()==false) {
        	JsonObject error = bij.Json_invalid_account_isactive_status_response(lid);
        	Gson gson1 = new GsonBuilder().setPrettyPrinting().create();
        	String S = gson1.toJson(error);
        	return Response.status(400).entity(S).build();
        }else {
           if(bi.activateAccount(lid, T)) {
        	   return Response.status(204).build();
           }
           else {
        	   return Response.status(404).build();//should this include a string or not like "aid does not exist"
           }
        }
	 }
	
	@Path("accounts/{id}")
	@PUT
	//@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateAccounts(@PathParam("id") int lid, String json) {
		
		Gson gson = new Gson();
        accounts T = gson.fromJson(json, accounts.class);
        Gson gson1 = new GsonBuilder().setPrettyPrinting().create();
        if(T.get_status()== true) {
        	JsonObject error = bij.Json_invalid_account_isactive_response(lid);
        	String S = gson1.toJson(error);
        	return Response.status(400).entity(S).build();
        }
        
        if(accountsManager.checknum(T.get_first_name())== false) {
        	JsonObject error = bij.Json_invalid_account_firstname_response(lid);
        	String S = gson1.toJson(error);
        	return Response.status(400).entity(S).build();
        }
        
        
        if(accountsManager.checknum(T.get_last_name())== false) {
        	JsonObject error = bij.Json_invalid_account_lastname_response(lid);
        	String S = gson1.toJson(error);
        	return Response.status(400).entity(S).build();
        }
        
        if(bi.updateAccount(lid, T)) {
     	   return Response.status(204).build();
        }
        else {
     	   return Response.status(404).build();//should this include a string or not like "aid does not exist"
        }
	}
	
	@Path("accounts/{id}")
	@DELETE
	//@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteAccounts(@PathParam("id") int lid) {
		 if(bi.deleteAccount(lid)) {
	     	   return Response.status(204).build();
	        }
	        else {
	     	   return Response.status(404).build();//should this include a string or not like "aid does not exist"
	        }
	}
	
	@Path("accounts/{id}")
	@GET
	//@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAccountsDetail(@PathParam("id") int lid) {
		accounts l = bi.getAccountDetail(lid);
		if(l.isNull()) {
			return Response.status(404).build();
		}
		else
		{
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
	        String s = gson.toJson(l);
	        return Response.ok(s).build();
		}
	}
    
	

	public Response getallAccounts() {
		
		List<accounts> l = bi.getallAccount();
		List<JsonObject> s = bij.getallAccounts_response(l);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
    	String S = gson.toJson(s);
        return Response.status(200).entity(S).build();
		
	}
    
	@Path("accounts")
	@GET
	//@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchAccounts(@Context UriInfo uriInfo) {// for url accounts?key=keyword using QueryParam
		MultivaluedMap<String, String> u = uriInfo.getQueryParameters();
		
		if(u.isEmpty()) {
	      return getallAccounts();
		}else {
		  String key = u.getFirst("key");
		  if(key.isEmpty())
			  return getallAccounts();
          List<accounts> a = bi.searchAccount(key);
          if(a.size()==0){
        	  return Response.status(404).build(); 
          }else{
        	  List<JsonObject> s = bij.searchAccounts_response(a);
              Gson gson = new GsonBuilder().setPrettyPrinting().create();
          	  String S = gson.toJson(s);
              return Response.status(200).entity(S).build();
          }
	  }
	}
	
	@Path("accounts/{id}/ratings")//need to test
	@POST
	//@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response makeRatings(String json, @PathParam("id") int lid, @Context UriInfo uriInfo) {
		Gson gson = new Gson();
        ratings r = gson.fromJson(json, ratings.class);
        Gson gson1 = new GsonBuilder().setPrettyPrinting().create();
        if(bi.createRating(r, lid)) {
        	JsonObject id = new JsonObject();
        	id.addProperty("sid", r.getID());
        	UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        	builder.path(Integer.toString(r.getID()));       	
        	String s = gson1.toJson(id);
        	return Response.created(builder.build()).entity(s).build();
        }else {
        	JsonObject error = bij.Json_invalid_ratings_resonse(r, lid);
        	String s = gson1.toJson(error);
        	return Response.status(400).entity(s).build();
        }
	}
	
	@Path("accounts/{id}/driver")//need to test
	@GET
	//@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDriverViews(@PathParam("id") int lid) {
		
		accounts l = bi.getAccountDetail(lid);
		
		if(l.isNull()) {
			return Response.status(404).build();
		}else{        
			JsonObject H = bij.getDriverViews_response(l);
            Gson gson1 = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
        	String s1 = gson1.toJson(H);
        	return Response.status(200).entity(s1).build();         
		}		
	}
	
	@Path("accounts/{id}/rider")//need to test
	@GET
	//@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRiderViews(@PathParam("id") int lid) {
		
		accounts l = bi.getAccountDetail(lid);
		
		if(l.isNull()) {
			return Response.status(404).build();
		}else{	        
			JsonObject H = bij.getRiderViews_response(l);   
            Gson gson1 = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
        	String s1 = gson1.toJson(H);
        	return Response.status(200).entity(s1).build();        
		}	
	}
	
	@Path("rides")
	@POST
	//@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response makeRides(String json, @Context UriInfo uriInfo) {
		
		//double d =0;
		Gson gson = new Gson();
        rides T = gson.fromJson(json, rides.class);
       /* JsonParser parser = new JsonParser();
		JsonElement jsonElement = parser.parse(json);
		JsonObject L = jsonElement.getAsJsonObject();
		if(!L.get("amount_per_passenger").isJsonNull())
		   d = L.get("amount_per_passenger").getAsDouble();
	    T.set_amount_per_passengers(d);*/
        Gson gson1 = new GsonBuilder().setPrettyPrinting().create();
        if(ridesManager.checkDate(T.get_date().date)&&accountsManager.checkaccount(T.get_aid())) {
        	bir.createRide(T);
        	UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        	builder.path(Integer.toString(T.get_rid()));
        	JsonObject id = new JsonObject();
        	id.addProperty("rid", T.get_rid());       	
        	String s = gson1.toJson(id);
        	return Response.created(builder.build()).entity(s).build();
        }
        else {
        	if(ridesManager.checkDate(T.get_date().date) == false) {
	        	JsonObject error = bij.Json_invalid_date_response();
	        	String s = gson1.toJson(error);
	        	return Response.status(400).entity(s).build();
        	}else {
        		if(accountsManager.checkaccount(T.get_aid())==false)
        		{
        			JsonObject error = bij.Json_invalid_rides_response(T);
    	        	String s = gson1.toJson(error);
    	        	return Response.status(400).entity(s).build();
        		}
        		
        	}
        	return Response.status(400).build();
        	
        }   		
	}
	
	@Path("rides/{id}")
	@PUT
	//@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateRides(@PathParam("id") int lid, String json) {
		/*JsonParser parser = new JsonParser();
		JsonElement jsonElement = parser.parse(json);
		JsonObject L = jsonElement.getAsJsonObject();
		double d = L.get("amount_per_passenger").getAsDouble();*/
		Gson gson = new Gson();
        rides T = gson.fromJson(json, rides.class);
      //  T.set_amount_per_passengers(d);
        if(bir.findRide(lid).get_aid()==T.get_aid()) {
        	if(bir.updateRide(T, lid))
        		return Response.status(204).build();
        	else
        		return Response.status(404).build();
        }else{
        	
        	JsonObject error = bij.Json_invalid_creator_for_rides_response(lid);
        	Gson gson1 = new GsonBuilder().setPrettyPrinting().create();
        	String s = gson1.toJson(error);
        	return Response.status(400).entity(s).build();
        	
        }
	}
	
	@Path("rides/{id}")
	@DELETE
	//@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteRides(@PathParam("id") int lid) {
		 if(bir.deleteRide(lid)) {
	     	   return Response.status(204).build();
	        }
	        else {
	     	   return Response.status(404).build();//should this include a string or not like "aid does not exist"
	        }
	}
	
	
	public Response getallRides() {
		
		List<rides> l = bir.getallRide();
		List<JsonObject> s = bij.getallRides_response(l);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
    	String S = gson.toJson(s);
        return Response.status(200).entity(S).build();
		
	}
	
	@Path("rides/{id}")
	@GET
	//@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRidesDetail(@PathParam("id") int lid) {
		rides a = bir.findRide(lid);
		if(a.isNull()) {
			return Response.status(404).build();
		}
		else
		{
			    JsonObject J = bij.getRidesDetail_response(a, bi);
	            Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
	            String s3 = gson.toJson(J);
	            return Response.ok(s3).build();
		}
	}
	
	@Path("rides")
	@GET
	//@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchRides(@Context UriInfo uriInfo) {// for url accounts?key=keyword using QueryParam
		  MultivaluedMap<String, String> u = uriInfo.getQueryParameters();
		  if(u.isEmpty())
		  {
			  return getallRides();
			  
		  }else {
				  String from = u.getFirst("from");
				  String to = u.getFirst("to");
				  String date = u.getFirst("date");
		          List<rides> a = bir.searchRide(from, to, date);
		          if(a.size()==0){
		        	  return Response.status(404).build(); 
		          }else{
		        	  List<JsonObject> s = new ArrayList<JsonObject>();
		        	  Iterator<rides> li = a.listIterator();
		              while(li.hasNext()) {
		                  rides l = li.next();
		            	  JsonObject J = new JsonObject();
		                  J.addProperty("rid", l.get_rid());
		  	              J.add("location_info", new Gson().toJsonTree(l.get_location()));
		  	              J.add("date_time", new Gson().toJsonTree(l.get_date()));
		                  s.add(J);
		              }
		              
		              Gson gson = new GsonBuilder().setPrettyPrinting().create();
		          	  String S = gson.toJson(s);
		              return Response.status(200).entity(S).build();
		          }
		  }
	  
	}
	
	@Path("rides/{id}/join_requests")
	@POST
	//@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createJoin_requests(@PathParam("id") int lid, String json, @Context UriInfo uriInfo) {
		
		Gson gson = new Gson();
        join_request T = gson.fromJson(json, join_request.class);
        Gson gson1 = new GsonBuilder().setPrettyPrinting().create();
        if(ridesManager.checkJoin_request(T)&&accountsManager.checkaccount(T.aid)) {
        	bir.createJoin_request(T,lid);
        	UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        	builder.path(Integer.toString(T.jid));
        	JsonObject id = new JsonObject();
        	id.addProperty("jid", T.jid);        	
        	String s = gson1.toJson(id);
        	return Response.created(builder.build()).entity(s).build();
        }
        else {
        	if(!ridesManager.checkJoin_request(T))
        	{
	        	JsonObject error = bij.Json_invalid_ride_confirmed_response(lid);
	        	String s = gson1.toJson(error);
	        	return Response.status(400).entity(s).build();
        	}
        	if(!accountsManager.checkaccount(T.aid))
        	{
	        	JsonObject error = bij.Json_invalid_creator_for_join_requests_response(T, lid);
	        	String s = gson1.toJson(error);
	        	return Response.status(400).entity(s).build();
        	}
        	
        	return Response.status(400).build();
        	
        }   		
	}
	
	@Path("rides/{rid}/join_requests/{jid}")
	@PATCH
	//@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response Joint_request_ride_confirm_deny(@PathParam("rid") int lrid, @PathParam("jid") int ljid, String json) {
		JsonParser parser = new JsonParser();
		JsonElement jsonElement = parser.parse(json);
		JsonObject L = jsonElement.getAsJsonObject();
		Gson gson2 = new Gson();
		join_request T = gson2.fromJson(json, join_request.class);
		Gson gson1 = new GsonBuilder().setPrettyPrinting().create();
		//return Response.status(200).build();
		if(!L.has("pickup_confirmed"))
		{ 
			if(T.ride_confirmed == null ) {
        	JsonObject error = bij.Json_invalid_ride_confirmed_with_jid_response(lrid, ljid);       	
        	String s = gson1.toJson(error);
        	return Response.status(400).entity(s).build();
        }else {
        	
        	Boolean b = ridesManager.check_driver_id(T, lrid);
        	if(b==true) {
        		if(bir.joint_request_ride_confirm_deny(T, lrid, ljid))
        			return Response.status(200).build();
        		else
        			return Response.status(404).build();
        	}
        	else {
        		
        		if(b==null) {
        			return Response.status(404).build();
        		}
        		
        		JsonObject error = bij.Json_invalid_rides_join_request_response(T, lrid, ljid);
            	String s = gson1.toJson(error);
            	return Response.status(400).entity(s).build();
        	}
        }
	  }else
	  {
		  if(T.pickup_confirmed == false || T.pickup_confirmed == null) {
	        	JsonObject error = bij.Json_invalid_pickup_confirmed_response(lrid, ljid);
	        	String s = gson1.toJson(error);
	        	return Response.status(400).entity(s).build();
	        }else {
	        	
	        	Boolean b = ridesManager.check_rider_id(T, lrid, ljid);
	        	if(b==true) {
	        		if(bir.joint_request_pickup_confirm(T, lrid, ljid))
	        			return Response.status(200).build();
	        		else
	        			return Response.status(404).build();
	        	}
	        	else {
	        		
	        		if(b==null) {
	        			return Response.status(404).build();
	        		}
	        		
	        		JsonObject error = bij.Json_invalid_account_join_request_response(T, lrid, ljid);
	            	String s = gson1.toJson(error);
	            	return Response.status(400).entity(s).build();
	        	}
	  }
	 }
	}
	
	@Path("rides/{rid}/messages")
	@POST
	//@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createMessages(@PathParam("rid") int lrid, String json, @Context UriInfo uriInfo) {
		
		Gson gson = new Gson();
		messages T = gson.fromJson(json, messages.class);
		Gson gson1 = new GsonBuilder().setPrettyPrinting().create();
        if(accountsManager.checkaccount(T.aid)) {
        	bir.createMessage(T,lrid);
        	UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        	builder.path(Integer.toString(T.mid));
        	JsonObject id = new JsonObject();
        	id.addProperty("mid", T.mid);
        	String s = gson1.toJson(id);
        	return Response.created(builder.build()).entity(s).build();
        }
        else {
        	JsonObject error = bij.Json_invalid_account_messages_response(lrid);
        	String s = gson1.toJson(error);
        	return Response.status(400).entity(s).build();
        }   		
	}
	
	@Path("rides/{rid}/messages")
	@GET
	//@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response viewAllMessages(@PathParam("rid") int lrid) {
		List<messages> list = bir.viewAllMessage(lrid);
		List<JsonObject> s = bij.viewAllMessages_response(list);
        Gson gson1 = new GsonBuilder().setPrettyPrinting().create();
    	String S = gson1.toJson(s);
    	return Response.status(200).entity(S).build();
	}
	
	@Path("reports")
	@GET
	//@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response viewAllReports() {
		List<reports> list = reportsManager.Reports;
		List<JsonObject> s = bij.viewAllReports_response(list);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
    	String S = gson.toJson(s);
        return Response.status(200).entity(S).build();
		
	}
	
	
	@Path("reports/{pid}")
	@GET
	//@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response GetReports(@PathParam("pid") int lpid, @Context UriInfo uriInfo) throws ParseException {		
		MultivaluedMap<String, String> u = uriInfo.getQueryParameters();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		if(u.isEmpty()) {
		   reports r = birt.ViewAllRides(lpid);	
	       String S = gson.toJson(r);
	       return Response.status(200).entity(S).build();
			//return Response.status(200).build();
		}else {
			  //MultivaluedMap<String, String> u = uriInfo.getQueryParameters();
				  String start_date = u.getFirst("start_date");
				  String end_date = u.getFirst("end_date");
				  reports R = birt.SearchRidesReports(lpid,start_date,end_date );
			      String s1 = gson.toJson(R); 
			      return Response.status(200).entity(s1).build();
			
		}
	}
	
}
