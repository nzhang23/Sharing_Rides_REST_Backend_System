package sar;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class JsonManager implements BoundaryInterfaceJson {

	public JsonManager() {
	}
	
    public void intial_jsonobject(JsonObject error) {
    	error.addProperty("type","http://cs.iit.edu/~virgil/cs445/project/api/problems/data-validation");
    	error.addProperty("title","Your request data didn't pass validation");   	
    }
    
	@Override
	public JsonObject Json_invalid_phone_response() {
		JsonObject error = new JsonObject();
		intial_jsonobject(error);
    	error.addProperty("detail","Invalid phone number");
    	error.addProperty("status", 400);
    	error.addProperty("instance","/accounts");
		return error;
	}

	@Override
	public JsonObject Json_invalid_account_isactive_status_response(int id) {
		JsonObject error = new JsonObject();
		intial_jsonobject(error);
    	error.addProperty("detail","Invalid value for is_active");
    	error.addProperty("status",400);
    	error.addProperty("instance","/accounts/"+id+"/status");
		return error;
	}

	@Override
	public JsonObject Json_invalid_account_isactive_response(int id) {
		JsonObject error = new JsonObject();
		intial_jsonobject(error);
    	error.addProperty("detail","Invalid value for is_active");
    	error.addProperty("status",400);
    	error.addProperty("instance","/accounts/"+id);
		return error;
	}

	@Override
	public JsonObject Json_invalid_account_firstname_response(int id) {
		JsonObject error = new JsonObject();
		intial_jsonobject(error);
    	error.addProperty("detail","The first name appears to be invalid.");
    	error.addProperty("status",400);
    	error.addProperty("instance","/accounts/"+id);
		return error;
	}

	@Override
	public JsonObject Json_invalid_account_lastname_response(int id) {
		JsonObject error = new JsonObject();
		intial_jsonobject(error);
    	error.addProperty("detail","The last name appears to be invalid.");
    	error.addProperty("status",400);
    	error.addProperty("instance","/accounts/"+id);
		return error;
	}

	@Override
	public JsonObject Json_invalid_ratings_resonse(ratings r, int lid) {
		JsonObject error = new JsonObject();
		intial_jsonobject(error);
    	error.addProperty("detail","This account ("+r.get_sent_by_id()+") didn't create this ride ("+r.get_rid()+") nor was it a passenger");
    	error.addProperty("status",400);
    	error.addProperty("instance","/accounts/"+lid+"/ratings");
		return error;
	}

	@Override
	public JsonObject Json_invalid_date_response() {
		JsonObject error = new JsonObject();
		intial_jsonobject(error);
    	error.addProperty("detail","Invalid date");
    	error.addProperty("status",400);
    	error.addProperty("instance","/rides");
		return error;
	}

	@Override
	public JsonObject Json_invalid_rides_response(rides T) {
		JsonObject error = new JsonObject();
		intial_jsonobject(error);
    	error.addProperty("detail","This account ("+T.get_aid()+") is not active, may not create a ride.");
    	error.addProperty("status",400);
    	error.addProperty("instance","/rides");
		return error;
	}

	@Override
	public JsonObject Json_invalid_creator_for_rides_response(int lid) {
		JsonObject error = new JsonObject();
		intial_jsonobject(error);
    	error.addProperty("detail","Only the creator of the ride may change it");
    	error.addProperty("status",400);
    	error.addProperty("instance","/rides/"+lid);
		return error;
	}

	@Override
	public JsonObject Json_invalid_ride_confirmed_response(int lid) {
		JsonObject error = new JsonObject();
		intial_jsonobject(error);
    	error.addProperty("detail","Invalid value for ride_confirmed");
    	error.addProperty("status",400);
    	error.addProperty("instance","/rides/"+lid+"/join_requests");
		return error;
	}

	@Override
	public JsonObject Json_invalid_creator_for_join_requests_response(join_request T, int lid) {
		JsonObject error = new JsonObject();
		intial_jsonobject(error);
    	error.addProperty("detail","This account ("+T.aid+") is not active, may not create a join ride request.");
    	error.addProperty("status",400);
    	error.addProperty("instance","/rides/"+lid+"/join_requests");
		return error;
	}

	@Override
	public JsonObject Json_invalid_ride_confirmed_with_jid_response(int lrid, int ljid) {
		JsonObject error = new JsonObject();
		intial_jsonobject(error);
    	error.addProperty("detail","Invalid value for ride_confirmed");
    	error.addProperty("status",400);
    	error.addProperty("instance","/rides/"+lrid+"/join_requests/"+ljid);
		return error;
	}

	@Override
	public JsonObject Json_invalid_rides_join_request_response(join_request T, int lrid, int ljid) {
		JsonObject error = new JsonObject();
		intial_jsonobject(error);
    	error.addProperty("detail","This account ("+T.aid + ") didn't create the ride ("+lrid+")");
    	error.addProperty("status",400);
    	error.addProperty("instance","/rides/"+lrid+"/join_requests/"+ljid);
		return error;
	}

	@Override
	public JsonObject Json_invalid_pickup_confirmed_response(int lrid, int ljid) {
		JsonObject error = new JsonObject();
		intial_jsonobject(error);
    	error.addProperty("detail","Invalid value for pickup_confirmed");
    	error.addProperty("status",400);
    	error.addProperty("instance","/rides/"+lrid+"/join_requests/"+ljid);
		return error;
	}

	@Override
	public JsonObject Json_invalid_account_join_request_response(join_request T, int lrid, int ljid) {
		JsonObject error = new JsonObject();
		intial_jsonobject(error);
    	error.addProperty("detail","This account ("+T.aid + ")has not requested to join this ride ("+lrid+")");
    	error.addProperty("status",400);
    	error.addProperty("instance","/rides/"+lrid+"/join_requests/"+ljid);
		return error;
	}

	@Override
	public JsonObject Json_invalid_account_messages_response(int lrid) {
		JsonObject error = new JsonObject();
		intial_jsonobject(error);
    	error.addProperty("detail","Account is not active");
    	error.addProperty("status",400);
    	error.addProperty("instance","/rides/"+lrid+"/messages");
		return error;
	}

	@Override
	public List<JsonObject> getallAccounts_response(List<accounts> l) {
		List<JsonObject> s = new ArrayList<JsonObject>();
		Iterator<accounts> li = l.listIterator();
        while(li.hasNext()) {
            accounts a = li.next();
            JsonObject J = new JsonObject();
            J.addProperty("aid", a.get_aid());
            J.addProperty("name", a.get_first_name()+" "+a.get_last_name());
            J.addProperty("date_created", a.get_date_created());
            J.addProperty("is_active", a.get_status());
            s.add(J);
        }
		return s;
	}

	@Override
	public List<JsonObject> searchAccounts_response(List<accounts> a) {
		List<JsonObject> s = new ArrayList<JsonObject>();
  	  Iterator<accounts> li = a.listIterator();
        while(li.hasNext()) {
            accounts l = li.next();
            JsonObject J = new JsonObject();
            J.addProperty("aid", l.get_aid());
            J.addProperty("name", l.get_first_name()+" "+l.get_last_name());
            J.addProperty("date_created", l.get_date_created());
            J.addProperty("is_active", l.get_status());
            s.add(J);
        }
		return s;
	}

	@Override
	public JsonObject getDriverViews_response(accounts l) {
		JsonObject H = new JsonObject();
		H.addProperty("aid", l.get_aid());
        H.addProperty("first_name", l.get_first_name());
        H.addProperty("rides", l.get_rides());
        H.addProperty("ratings", l.get_ratings());
        H.addProperty("average_rating", l.get_average_rating());
        List<ratings> list = l.get_detail();
		List<JsonObject> s = new ArrayList<JsonObject>();
		Iterator<ratings> li = list.listIterator();
        while(li.hasNext()) {
            ratings a = li.next();
            if(a.get_driver_ratings()) {
	            JsonObject J = new JsonObject();
	            J.addProperty("rid", a.get_rid());
	            J.addProperty("sent_by_id", a.get_sent_by_id());
	            J.addProperty("first_name", a.get_first_name());
	            J.addProperty("date", a.get_date());
	            J.addProperty("rating", a.get_rating());
	            J.addProperty("comment", a.get_comment());
	            s.add(J);
            }
        }
        H.add("detail", new Gson().toJsonTree(s));
		return H;
	}

	@Override
	public JsonObject getRiderViews_response(accounts l) {
		JsonObject H = new JsonObject();
		H.addProperty("aid", l.get_aid());
        H.addProperty("first_name", l.get_first_name());
        H.addProperty("rides", l.get_rides());
        H.addProperty("ratings", l.get_ratings());
        H.addProperty("average_rating", l.get_average_rating());
        List<ratings> list = l.get_detail();
		List<JsonObject> s = new ArrayList<JsonObject>();
		Iterator<ratings> li = list.listIterator();
        while(li.hasNext()) {
            ratings a = li.next();
            if(!a.get_driver_ratings()) {
	            JsonObject J = new JsonObject();
	            J.addProperty("rid", a.get_rid());
	            J.addProperty("sent_by_id", a.get_sent_by_id());
	            J.addProperty("first_name", a.get_first_name());
	            J.addProperty("date", a.get_date());
	            J.addProperty("rating", a.get_rating());
	            J.addProperty("comment", a.get_comment());
	            s.add(J);
            }
        }
        H.add("detail", new Gson().toJsonTree(s));
		return H;
	}

	@Override
	public List<JsonObject> getallRides_response(List<rides> l) {
		List<JsonObject> s = new ArrayList<JsonObject>();
		Iterator<rides> li = l.listIterator();
        while(li.hasNext()) {
            rides a = li.next();
            JsonObject J = new JsonObject();
            J.addProperty("rid", a.get_rid());
            J.add("location_info", new Gson().toJsonTree(a.get_location()));
            J.add("date_time", new Gson().toJsonTree(a.get_date()));
            s.add(J);
        }
		return s;
	}

	@Override
	public JsonObject getRidesDetail_response(rides a, BoundaryInterfaceAccounts bi) {
		JsonObject J = new JsonObject();
        J.addProperty("rid", a.get_rid());
        J.add("location_info", new Gson().toJsonTree(a.get_location()));
        J.add("date_time", new Gson().toJsonTree(a.get_date()));
        J.add("car_info", new Gson().toJsonTree(a.get_car()));
        J.addProperty("max_passengers", a.get_max_passengers());
        if(a.get_amount_per_passengers()==null)
        	J.addProperty("amount_per_passenger",a.get_amount_per_passengers());
        else
        	J.addProperty("amount_per_passenger", a.get_amount_per_passengers().doubleValue());
        J.addProperty("conditions",a.get_conditions());
        accounts l = bi.getAccountDetail(a.get_aid());
        J.addProperty("driver", l.get_first_name());
        J.addProperty("driver_picture", l.get_picture());
        J.addProperty("rides", l.get_rides());
        J.addProperty("ratings", l.get_ratings());
        J.addProperty("average_rating",l.get_average_rating());
        List<ratings> list = l.get_detail();
		List<JsonObject> s = new ArrayList<JsonObject>();
		Iterator<ratings> li = list.listIterator();
        while(li.hasNext()) {
            ratings b = li.next();
            if(b.get_driver_ratings()) {
            	ridesManager rm = new ridesManager();
            	rides r = rm.findRide(b.get_rid());
	            JsonObject H = new JsonObject();
	            H.addProperty("rid", b.get_rid());
	            H.addProperty("date", r.get_date().date);
	            H.addProperty("rating", b.get_rating());
	            H.addProperty("comment", b.get_comment());
	            s.add(H);
            }
        }
        J.add("comments_about_driver", new Gson().toJsonTree(s));
		return J;
	}

	@Override
	public List<JsonObject> viewAllMessages_response(List<messages> list) {
		List<JsonObject> s = new ArrayList<JsonObject>();
		Iterator<messages> li = list.listIterator();
        while(li.hasNext()) {
        	messages b = li.next();       
	        JsonObject H = new JsonObject();
	        H.addProperty("mid", b.mid);
	        H.addProperty("sent_by_aid", b.aid);
	        H.addProperty("date", b.date);
	        H.addProperty("body", b.msg);
	        s.add(H);
        }
		return s;
	}

	@Override
	public List<JsonObject> viewAllReports_response(List<reports> list) {
		List<JsonObject> s = new ArrayList<JsonObject>();
  	  	Iterator<reports> li = list.listIterator();
        while(li.hasNext()) {
        	reports l = li.next();
      	    JsonObject J = new JsonObject();
            J.addProperty("pid", l.get_pid());
            J.addProperty("name", l.get_name());
            s.add(J);
        }       
		return s;
	}

}
