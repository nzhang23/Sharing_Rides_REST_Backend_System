package sar;
import sar.accountsManager;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

public class accountsManagerTest {

	@Test
	public void testAccountsPUT() {
	   accounts A = new accounts();
	   accounts B = new accounts();
	   A.set_first_name("Ning");
	   A.set_last_name("Zhang");
	   A.set_phone("123");
	   A.set_picture("abc");
	   A.set_status_inactive();
	   accountsManager am = new accountsManager();
	   am.accountsPUT(B, A);
	   assertEquals(B.get_first_name(),A.get_first_name());
	   assertEquals(B.get_last_name(), A.get_last_name());
	   assertEquals(B.get_phone(),A.get_phone());
	   assertEquals(B.get_status(),A.get_status());
	   assertEquals(B.get_picture(),A.get_picture());
	}

	@Test
	public void testCheckaccount() {
	   accountsManager am = new accountsManager();
	   accounts A = new accounts();
	   List<accounts> list = am.getallAccount();
	   list.clear();
	   list.add(A);
	   boolean b = accountsManager.checkaccount(A.get_aid());
	   assertFalse(b);
	   A.set_status_active();
	   b = accountsManager.checkaccount(A.get_aid());
	   assertTrue(b);
	   list.clear();
	   b = accountsManager.checkaccount(A.get_aid());
	   assertFalse(b);
	}
	@Test
	public void testCheckphone() {
		
		assertTrue(accountsManager.checkphone("333-333-3333"));
		assertFalse(accountsManager.checkphone("3o3-333-3333"));
		assertFalse(accountsManager.checkphone("33-333-3333"));
	}

	@Test
	public void testChecknum() {
		assertTrue(accountsManager.checknum("Ning"));
		assertFalse(accountsManager.checknum("Ning 23 (),./!@*&^%#@"));
	}

	@Test
	public void testCreateAccount() {
	    accounts a = new accounts();
	    accountsManager am = new accountsManager();
	    am.getallAccount().clear();
	    am.createAccount(a);
	    assertFalse(accountsManager.checkaccount(a.get_aid()));
	}

	@Test
	public void testActivateAccount() {
		accounts a = new accounts();
		accounts b = new accounts();
		accountsManager am = new accountsManager();
		am.getallAccount().add(a);
		b.set_status_active();
		am.activateAccount(a.get_aid(),b);
		assertTrue(a.get_status());
		am.getallAccount().clear();
		am.activateAccount(a.get_aid(),b);
		assertFalse(am.activateAccount(a.get_aid(),b));
	}

	@Test
	public void testUpdateAccount() {
		   accounts A = new accounts();
		   accounts B = new accounts();
		   A.set_first_name("Ning");
		   A.set_last_name("Zhang");
		   A.set_phone("123");
		   A.set_picture("abc");
		   A.set_status_inactive();
		   accountsManager am = new accountsManager();
		   am.getallAccount().add(B);
		   am.updateAccount(B.get_aid(), A);
		   assertEquals(B.get_first_name(),A.get_first_name());
		   assertEquals(B.get_last_name(), A.get_last_name());
		   assertEquals(B.get_phone(),A.get_phone());
		   assertEquals(B.get_status(),A.get_status());
		   assertEquals(B.get_picture(),A.get_picture());
		   am.getallAccount().clear();
		   assertFalse(am.updateAccount(B.get_aid(), A));
	}

	@Test
	public void testDeleteAccount() {
		 accounts A = new accounts();
		 accountsManager am = new accountsManager();
		 am.getallAccount().add(A);
		 assertTrue(am.deleteAccount(A.get_aid()));
		 am.getallAccount().add(A);
		 assertFalse(am.deleteAccount(1000));
		 am.getallAccount().clear();
		 assertFalse(am.deleteAccount(A.get_aid()));
	}

	@Test
	public void testGetAccountDetail() {
		
		 accounts A = new accounts();
		 accountsManager am = new accountsManager();
		 am.getallAccount().add(A);
		 assertEquals(am.getAccountDetail(A.get_aid()).get_aid(),A.get_aid());
		 am.getallAccount().clear();
		 assertTrue(am.getAccountDetail(A.get_aid()).isNull());
	}

	@Test
	public void testGetallAccount() {
		
	}

	@Test
	public void testSearchAccount() {
		accounts A = new accounts();
		A.set_first_name("Ning");
		A.set_last_name("Zhang");
		A.set_phone("123");
		A.set_picture("abc");
		A.set_status_inactive();
		accounts B = new accounts();
		B.set_first_name("Zhang");
		B.set_last_name("Ning");
		B.set_phone("123");
		B.set_picture("abc");
		B.set_status_inactive();
		accountsManager am = new accountsManager();
		am.getallAccount().clear();
		am.getallAccount().add(A);
		am.getallAccount().add(B);
		List<accounts> list = am.searchAccount("Ning");
	    assertEquals(list.get(0).get_first_name(), A.get_first_name());
	    assertEquals(list.get(1).get_first_name(), B.get_first_name());	
	    
	}

	@Test
	public void testMatch_rider_id() {
		rides r =new rides();
		accountsManager am = new accountsManager();
		int aid = 16;
		assertFalse(am.match_rider_id(r, aid));
		join_request jr = new join_request();
		jr.aid =16;
	    jr.pickup_confirmed = true;
	    jr.ride_confirmed = true;   
		r.add_list_join_request(jr);
		assertTrue(am.match_rider_id(r, aid));	
		jr.aid =16;
	    jr.pickup_confirmed = false;
	    jr.ride_confirmed = true;  
	    assertFalse(am.match_rider_id(r, aid));
	    jr.aid =15;
	    jr.pickup_confirmed = true;
	    jr.ride_confirmed = true;  
	    assertFalse(am.match_rider_id(r, aid));
	}

	@Test
	public void testCreateRating() {
		rides r =new rides();
		accountsManager am = new accountsManager();
		ridesManager rm = new ridesManager();
		ratings R = new ratings();
		join_request jr = new join_request();
		am.getallAccount().clear();
		accounts a = new accounts();
		am.getallAccount().add(a);
		rm.getallRide().clear();
		rm.getallRide().add(r);
		R.set_driver_ratings();
		jr.aid = a.get_aid();
	    jr.pickup_confirmed = true;
	    jr.ride_confirmed = true;   
		r.set_aid(16);
		R.set_rid(r.get_rid());
		R.set_sent_by_rid(16);
		r.add_list_join_request(jr);
		am.createRating(R, a.get_aid());
		assertFalse(R.get_driver_ratings());
		jr.aid = 16;
		r.set_aid(a.get_aid());
		R.set_sent_by_rid(16);
		am.createRating(R, a.get_aid());
		assertTrue(R.get_driver_ratings());
		
		am.createRating(R, a.get_aid());
	}

}
