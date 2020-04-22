package sar;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.google.gson.Gson;

public class ridesManagerTest {

	@Test
	public void testCheck_driver_id() {
		rides r = new rides();
		r.set_aid(10);
		join_request jr = new join_request();
		jr.aid = 10;
		ridesManager rm = new ridesManager();
		rm.getallRide().add(r);
		assertTrue(rm.check_driver_id(jr, r.get_rid()));
		jr.aid =  9;
		assertFalse(rm.check_driver_id(jr, r.get_rid()));
		rm.getallRide().clear();
		assertNull(rm.check_driver_id(jr, r.get_rid()));
	}

	@Test
	public void testCheck_rider_id() {
		rides r = new rides();
		r.set_aid(10);
		join_request jr = new join_request();
		jr.aid = 10;
		join_request jr1 = new join_request();
		jr1.aid = 10;
		ridesManager rm = new ridesManager();
		rm.getallRide().add(r);
		r.add_list_join_request(jr1);
		assertTrue(ridesManager.check_rider_id(jr, r.get_rid(), jr1.jid));
		jr1.aid = 9;
		assertFalse(ridesManager.check_rider_id(jr, r.get_rid(), jr1.jid));
		rm.getallRide().clear();
		assertNull(ridesManager.check_rider_id(jr, r.get_rid(), jr1.jid));
	}

	@Test
	public void testGet_Rides() {
		
	}

	@Test
	public void testCheckDate() {
		String d = "15-Apr-2020";
		assertTrue(ridesManager.checkDate(d));
		d = "31-Apr-2020";
		assertFalse(ridesManager.checkDate(d));
	}

	@Test
	public void testCheckJoin_request() {
		join_request T = new join_request();
		T.pickup_confirmed = null;
		T.ride_confirmed = null;	
		assertTrue(ridesManager.checkJoin_request(T));
		T.pickup_confirmed = true;
		assertFalse(ridesManager.checkJoin_request(T));
	}
		
	@Test
	public void testRidesPUT() {
		Gson gson = new Gson();
		String json ="{\n" + 
				"  \"aid\": 2,\n" + 
				"  \"location_info\": {\n" + 
				"    \"from_city\": \"Barrington\",\n" + 
				"    \"from_zip\": \"60010\",\n" + 
				"    \"to_city\": \"Milwaukee\",\n" + 
				"    \"to_zip\": \"53202\"\n" + 
				"  },\n" + 
				"  \"date_time\": {\n" + 
				"    \"date\": \"14-Apr-2020\",\n" + 
				"    \"time\": \"09:00\"\n" + 
				"  },\n" + 
				"  \"car_info\": {\n" + 
				"    \"make\": \"Audi\",\n" + 
				"    \"model\": \"A4\",\n" + 
				"    \"color\": \"Gray\",\n" + 
				"    \"plate_state\": \"IL\",\n" + 
				"    \"plate_serial\": \"COVID19\"\n" + 
				"  },\n" + 
				"  \"max_passengers\": 2,\n" + 
				"  \"amount_per_passenger\": 15.00,\n" + 
				"  \"conditions\": \"No more than one carry on per passenger. No pets.\"\n" + 
				"}";
        rides T = gson.fromJson(json, rides.class);
        rides r = new rides();
        ridesManager.ridesPUT(r, T);
        assertEquals(T.get_aid(),r.get_aid());
        assertEquals(T.get_car().color, r.get_car().color);
        assertEquals(T.get_conditions(),r.get_conditions());
        assertEquals(T.get_amount_per_passengers(),r.get_amount_per_passengers());
        assertEquals(T.get_date().date, r.get_date().date);
        assertEquals(T.get_location().from_city,r.get_location().from_city);
        assertEquals(T.get_max_passengers(),r.get_max_passengers());
	}
  
	@Test
	public void testCreateRide() {
		rides r = new rides();
		ridesManager rm = new ridesManager();
		rm.createRide(r);
		assertEquals(rm.findRide(r.get_rid()),r);
		rm.getallRide().clear();
	}

	@Test
	public void testFindRide() {
		rides r = new rides();
		ridesManager rm = new ridesManager();
		rm.createRide(r);
		assertEquals(rm.findRide(r.get_rid()),r);
		rm.getallRide().clear();
	
	}

	@Test
	public void testUpdateRide() {
		Gson gson = new Gson();
		String json ="{\n" + 
				"  \"aid\": 2,\n" + 
				"  \"location_info\": {\n" + 
				"    \"from_city\": \"Barrington\",\n" + 
				"    \"from_zip\": \"60010\",\n" + 
				"    \"to_city\": \"Milwaukee\",\n" + 
				"    \"to_zip\": \"53202\"\n" + 
				"  },\n" + 
				"  \"date_time\": {\n" + 
				"    \"date\": \"14-Apr-2020\",\n" + 
				"    \"time\": \"09:00\"\n" + 
				"  },\n" + 
				"  \"car_info\": {\n" + 
				"    \"make\": \"Audi\",\n" + 
				"    \"model\": \"A4\",\n" + 
				"    \"color\": \"Gray\",\n" + 
				"    \"plate_state\": \"IL\",\n" + 
				"    \"plate_serial\": \"COVID19\"\n" + 
				"  },\n" + 
				"  \"max_passengers\": 2,\n" + 
				"  \"amount_per_passenger\": 15.00,\n" + 
				"  \"conditions\": \"No more than one carry on per passenger. No pets.\"\n" + 
				"}";
        rides T = gson.fromJson(json, rides.class);
        rides r = new rides();
        ridesManager rm = new ridesManager();
        rm.createRide(r);
        rm.updateRide(T, r.get_rid());
        assertEquals(T.get_aid(),r.get_aid());
        assertEquals(T.get_car().color, r.get_car().color);
        assertEquals(T.get_conditions(),r.get_conditions());
        assertEquals(T.get_amount_per_passengers(),r.get_amount_per_passengers());
        assertEquals(T.get_date().date, r.get_date().date);
        assertEquals(T.get_location().from_city,r.get_location().from_city);
        assertEquals(T.get_max_passengers(),r.get_max_passengers());
        assertFalse(rm.updateRide(T, 1000));
        
        rm.getallRide().clear();
	}

	@Test
	public void testDeleteRide() {
		rides r = new rides();
		ridesManager rm = new ridesManager();
        rm.createRide(r);
        assertTrue(rm.deleteRide(r.get_rid()));
        assertFalse(rm.deleteRide(10000));
        rm.getallRide().clear();
	}

	@Test
	public void testGetallRide() {
		
	}

	@Test
	public void testSearchRide() {
		
		Gson gson = new Gson();
		String json ="{\n" + 
				"  \"aid\": 2,\n" + 
				"  \"location_info\": {\n" + 
				"    \"from_city\": \"Barrington\",\n" + 
				"    \"from_zip\": \"60010\",\n" + 
				"    \"to_city\": \"Milwaukee\",\n" + 
				"    \"to_zip\": \"53202\"\n" + 
				"  },\n" + 
				"  \"date_time\": {\n" + 
				"    \"date\": \"14-Apr-2020\",\n" + 
				"    \"time\": \"09:00\"\n" + 
				"  },\n" + 
				"  \"car_info\": {\n" + 
				"    \"make\": \"Audi\",\n" + 
				"    \"model\": \"A4\",\n" + 
				"    \"color\": \"Gray\",\n" + 
				"    \"plate_state\": \"IL\",\n" + 
				"    \"plate_serial\": \"COVID19\"\n" + 
				"  },\n" + 
				"  \"max_passengers\": 2,\n" + 
				"  \"amount_per_passenger\": 15.00,\n" + 
				"  \"conditions\": \"No more than one carry on per passenger. No pets.\"\n" + 
				"}";
        rides T = gson.fromJson(json, rides.class);
        ridesManager rm = new ridesManager();
        rm.createRide(T);
        String from = "Barrington";
        String to = "Milwaukee";
        String date = "14-Apr-2020";
        assertEquals(rm.searchRide(from, to, date).get(0), T);
        to = "";
        assertEquals(rm.searchRide(from, to, date).get(0), T);
        to = "Milwaukee";
        date = "";
        assertEquals(rm.searchRide(from, to, date).get(0), T);
        to = "";
        date = "";
        assertEquals(rm.searchRide(from, to, date).get(0), T);
        from ="";
        to = "Milwaukee";
        date = "";
        assertEquals(rm.searchRide(from, to, date).get(0), T);
        from ="";
        to = "";
        date = "14-Apr-2020";
        assertEquals(rm.searchRide(from, to, date).get(0), T);
        from ="";
        to = "Milwaukee";
        date = "14-Apr-2020";
        assertEquals(rm.searchRide(from, to, date).get(0), T);
        from ="";
        to = "";
        date = "";
        assertEquals(rm.searchRide(from, to, date).get(0), T);
        rm.getallRide().clear();
        
	}

	@Test
	public void testCreateJoin_request() {
		rides r = new rides();
		ridesManager rm = new ridesManager();
        rm.createRide(r);
        join_request jr = new join_request();
        rm.createJoin_request(jr, r.get_rid());
        assertEquals(r.find_in_list_join_requests(jr.jid),jr);
        rm.getallRide().clear();
	}

	@Test
	public void testJoint_request_ride_confirm_deny() {
		rides r = new rides();
		ridesManager rm = new ridesManager();
        rm.createRide(r);
        join_request jr = new join_request();
        r.add_list_join_request(jr);
        join_request jr1 = new join_request();
        jr1.ride_confirmed = true;
        rm.joint_request_ride_confirm_deny(jr1, r.get_rid(), jr.jid);
        assertTrue(jr.ride_confirmed);
        assertFalse(rm.joint_request_ride_confirm_deny(jr1, 1000, 1000));
        rm.getallRide().clear();
	}

	@Test
	public void testJoint_request_pickup_confirm() {
		rides r = new rides();
		ridesManager rm = new ridesManager();
        rm.createRide(r);
        join_request jr = new join_request();
        r.add_list_join_request(jr);
        join_request jr1 = new join_request();
        jr1.pickup_confirmed = true;
        rm.joint_request_pickup_confirm(jr1, r.get_rid(), jr.jid);
        assertTrue(jr.pickup_confirmed);
        assertFalse(rm.joint_request_pickup_confirm(jr1, 10000, 1000));
        rm.getallRide().clear();
	}

	@Test
	public void testCreateMessage() {
		rides r = new rides();
		ridesManager rm = new ridesManager();
		messages m = new messages();
		rm.createRide(r);
		rm.createMessage(m, r.get_rid());
		assertEquals(m.mid, r.get_list_messages().get(0).mid);
		rm.getallRide().clear();
	}

	@Test
	public void testViewAllMessage() {
		
		rides r = new rides();
		ridesManager rm = new ridesManager();
		messages m = new messages();
		rm.createRide(r);
		rm.createMessage(m, r.get_rid());
		assertEquals(rm.viewAllMessage(r.get_rid()).get(0).mid, m.mid);
		
	}

}
