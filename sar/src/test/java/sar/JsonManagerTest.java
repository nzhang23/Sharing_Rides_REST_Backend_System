package sar;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


import com.google.gson.JsonObject;

public class JsonManagerTest {
    
	private JsonManager jm = new JsonManager();
	
	@Test
	public void testJsonManager() {
	}

	@Test
	public void testIntial_jsonobject() {
		JsonObject error = new JsonObject();
		jm.intial_jsonobject(error);
		assertEquals(error.get("type").getAsString(),"http://cs.iit.edu/~virgil/cs445/project/api/problems/data-validation");
		assertEquals(error.get("title").getAsString(), "Your request data didn't pass validation");
	}

	@Test
	public void testJson_invalid_phone_response() {
		JsonObject error = jm.Json_invalid_phone_response();
		assertEquals(error.get("detail").getAsString(),"Invalid phone number");
		assertEquals(error.get("status").getAsInt(), 400);
		assertEquals(error.get("instance").getAsString(),"/accounts");
	}

	@Test
	public void testJson_invalid_account_isactive_status_response() {
	    int id =6;
	    JsonObject error = jm.Json_invalid_account_isactive_status_response(id);
	    assertEquals(error.get("detail").getAsString(),"Invalid value for is_active");
	    assertEquals(error.get("status").getAsInt(),400);
	    assertEquals(error.get("instance").getAsString(),"/accounts/6/status");
	}

	@Test
	public void testJson_invalid_account_isactive_response() {
		 int id =6;
		 JsonObject error = jm.Json_invalid_account_isactive_response(id);
		 assertEquals(error.get("detail").getAsString(),"Invalid value for is_active");
		 assertEquals(error.get("status").getAsInt(),400);
		 assertEquals(error.get("instance").getAsString(),"/accounts/6");
	}

	@Test
	public void testJson_invalid_account_firstname_response() {
		int id =6;
		JsonObject error = jm.Json_invalid_account_firstname_response(id);
		assertEquals(error.get("detail").getAsString(),"The first name appears to be invalid.");
		assertEquals(error.get("status").getAsInt(),400);
		assertEquals(error.get("instance").getAsString(),"/accounts/6");
	}

	@Test
	public void testJson_invalid_account_lastname_response() {
		int id =6;
		JsonObject error = jm.Json_invalid_account_lastname_response(id);
		assertEquals(error.get("detail").getAsString(),"The last name appears to be invalid.");
		assertEquals(error.get("status").getAsInt(),400);
		assertEquals(error.get("instance").getAsString(),"/accounts/6");
	}

	@Test
	public void testJson_invalid_ratings_resonse() {
		ratings r = new ratings();
		int d = 6;
		r.set_sent_by_rid(10);
		r.set_rid(5);
		JsonObject error = jm.Json_invalid_ratings_resonse(r, d);
		assertEquals(error.get("detail").getAsString(),"This account (10) didn't create this ride (5) nor was it a passenger");
		assertEquals(error.get("status").getAsInt(),400);
		assertEquals(error.get("instance").getAsString(),"/accounts/6/ratings");
	}

	@Test
	public void testJson_invalid_date_response() {
		JsonObject error = jm.Json_invalid_date_response();
		assertEquals(error.get("detail").getAsString(),"Invalid date");
		assertEquals(error.get("status").getAsInt(),400);
		assertEquals(error.get("instance").getAsString(),"/rides");
	}

	@Test
	public void testJson_invalid_rides_response() {
		rides T = new rides();
		T.set_aid(9);
		JsonObject error = jm.Json_invalid_rides_response(T);
		assertEquals(error.get("detail").getAsString(),"This account (9) is not active, may not create a ride.");
		assertEquals(error.get("status").getAsInt(),400);
		assertEquals(error.get("instance").getAsString(),"/rides");
	}

	@Test
	public void testJson_invalid_creator_for_rides_response() {
		JsonObject error = jm.Json_invalid_creator_for_rides_response(6);
		assertEquals(error.get("detail").getAsString(),"Only the creator of the ride may change it");
		assertEquals(error.get("status").getAsInt(),400);
		assertEquals(error.get("instance").getAsString(),"/rides/6");
	}

	@Test
	public void testJson_invalid_ride_confirmed_response() {
		JsonObject error = jm.Json_invalid_ride_confirmed_response(6);
		assertEquals(error.get("detail").getAsString(),"Invalid value for ride_confirmed");
		assertEquals(error.get("instance").getAsString(),"/rides/6/join_requests");
		assertEquals(error.get("status").getAsInt(),400);
	}

	@Test
	public void testJson_invalid_creator_for_join_requests_response() {
		join_request jr = new join_request();
		jr.aid = 9;
		JsonObject error = jm.Json_invalid_creator_for_join_requests_response(jr, 6);
		assertEquals(error.get("detail").getAsString(),"This account (9) is not active, may not create a join ride request.");
		assertEquals(error.get("instance").getAsString(),"/rides/6/join_requests");
		assertEquals(error.get("status").getAsInt(),400);
	}

	@Test
	public void testJson_invalid_ride_confirmed_with_jid_response() {
		JsonObject error = jm.Json_invalid_ride_confirmed_with_jid_response(6,9);
		assertEquals(error.get("detail").getAsString(),"Invalid value for ride_confirmed");
		assertEquals(error.get("instance").getAsString(),"/rides/6/join_requests/9");
		assertEquals(error.get("status").getAsInt(),400);
	}

	@Test
	public void testJson_invalid_rides_join_request_response() {
		join_request jr = new join_request();
		jr.aid = 9;
		JsonObject error = jm.Json_invalid_rides_join_request_response(jr, 6, 8);
		assertEquals(error.get("detail").getAsString(),"This account (9) didn't create the ride (6)");
		assertEquals(error.get("instance").getAsString(),"/rides/6/join_requests/8");
		assertEquals(error.get("status").getAsInt(),400);
	}

	@Test
	public void testJson_invalid_pickup_confirmed_response() {
		JsonObject error = jm.Json_invalid_pickup_confirmed_response(6, 9);
		assertEquals(error.get("detail").getAsString(),"Invalid value for pickup_confirmed");
		assertEquals(error.get("instance").getAsString(),"/rides/6/join_requests/9");
		assertEquals(error.get("status").getAsInt(),400);
	}

	@Test
	public void testJson_invalid_account_join_request_response() {
		join_request jr = new join_request();
		jr.aid = 9;
		JsonObject error = jm.Json_invalid_account_join_request_response(jr, 6, 8);
		assertEquals(error.get("detail").getAsString(),"This account (9)has not requested to join this ride (6)");
		assertEquals(error.get("instance").getAsString(),"/rides/6/join_requests/8");
		assertEquals(error.get("status").getAsInt(),400);
	}

	@Test
	public void testJson_invalid_account_messages_response() {
		JsonObject error = jm.Json_invalid_account_messages_response(9);
		assertEquals(error.get("detail").getAsString(),"Account is not active");
		assertEquals(error.get("instance").getAsString(),"/rides/9/messages");
		assertEquals(error.get("status").getAsInt(),400);
	}

	@Test
	public void testGetallAccounts_response() {
		List<accounts> l = new ArrayList<accounts>();
		accounts a = new accounts();
		l.add(a);
		a.set_status_active();
		a.set_first_name("Ning");
		a.set_last_name("Zhang");
		List<JsonObject> s  = jm.getallAccounts_response(l);
		assertEquals(s.get(0).get("aid").getAsInt(), a.get_aid());
		assertEquals(s.get(0).get("name").getAsString(),a.get_first_name()+" "+a.get_last_name());
		assertEquals(s.get(0).get("date_created").getAsString(), a.get_date_created());
		assertTrue(s.get(0).get("is_active").getAsBoolean());
	}

	@Test
	public void testSearchAccounts_response() {
		List<accounts> l = new ArrayList<accounts>();
		accounts a = new accounts();
		l.add(a);
		a.set_status_active();
		a.set_first_name("Ning");
		a.set_last_name("Zhang");
		List<JsonObject> s  = jm.searchAccounts_response(l);
		assertEquals(s.get(0).get("aid").getAsInt(), a.get_aid());
		assertEquals(s.get(0).get("name").getAsString(),a.get_first_name()+" "+a.get_last_name());
		assertEquals(s.get(0).get("date_created").getAsString(), a.get_date_created());
		assertTrue(s.get(0).get("is_active").getAsBoolean());
	}

	@Test
	public void testGetDriverViews_response() {
		accounts l = new accounts();
		ratings r = new ratings();
		l.set_first_name("n");
		l.set_ratings(3);
		l.set_ratings(3);
		l.AddRatings(r);
		r.set_rating(6);
		r.set_rid(8);
		r.set_driver_ratings();
		JsonObject s = jm.getDriverViews_response(l);
		assertEquals(s.get("aid").getAsInt(), l.get_aid());
		assertEquals(s.get("first_name").getAsString(), l.get_first_name());
		assertEquals(s.get("rides").getAsInt(), l.get_rides());
		assertEquals(s.get("ratings").getAsInt(), l.get_ratings());
	}

	@Test
	public void testGetRiderViews_response() {
		accounts l = new accounts();
		ratings r = new ratings();
		l.set_first_name("n");
		l.set_ratings(3);
		l.set_ratings(3);
		l.AddRatings(r);
		r.set_rating(6);
		r.set_rid(8);
		r.set_rider_ratings();
		JsonObject s = jm.getRiderViews_response(l);
		assertEquals(s.get("aid").getAsInt(), l.get_aid());
		assertEquals(s.get("first_name").getAsString(), l.get_first_name());
		assertEquals(s.get("rides").getAsInt(), l.get_rides());
		assertEquals(s.get("ratings").getAsInt(), l.get_ratings());
	}

	@Test
	public void testGetallRides_response() {
		List<rides> l = new ArrayList<rides>();
		rides r = new rides();
		l.add(r);
		List<JsonObject> s  = jm.getallRides_response(l);
		assertEquals(s.get(0).get("rid").getAsInt(), r.get_rid());
	}

	@Test
	public void testGetRidesDetail_response() {
		BoundaryInterfaceAccounts bi = new accountsManager();
		accounts a = new accounts();
		a.set_first_name("n");
		rides r = new rides();
		r.set_aid(a.get_aid());
		bi.getallAccount().add(a);
		JsonObject s = jm.getRidesDetail_response(r, bi);
		assertEquals(s.get("rid").getAsInt(), r.get_rid());
		assertEquals(s.get("driver").getAsString(), a.get_first_name());
	}

	@Test
	public void testViewAllMessages_response() {
		List<messages> list = new ArrayList<messages>();
		messages m = new messages();
		list.add(m);
		List<JsonObject> s = jm.viewAllMessages_response(list);
		assertEquals(s.get(0).get("mid").getAsInt(),m.mid);
	}

	@Test
	public void testViewAllReports_response() {
		List<reports> list  = new ArrayList<reports>() {{add(new reports(907,"Rides posted between two dates"));
	    add(new reports(911,"Rides taken between two dates"));}};
	    List<JsonObject> s = jm.viewAllReports_response(list);
	    assertEquals(s.get(0).get("pid").getAsInt(),907);
	    
	}

}
