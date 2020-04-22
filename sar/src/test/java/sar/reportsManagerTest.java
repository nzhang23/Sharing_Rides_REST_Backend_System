package sar;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.text.ParseException;


public class reportsManagerTest {

	@Test
	public void testGetAllReport() {
		
	}

	@Test
	public void testIsRidesPostedorTaken() {
		rides r = new rides();
		join_request jr = new join_request();
		r.add_list_join_request(jr);
		jr.ride_confirmed =true;
		reportsManager rm = new reportsManager();
		assertFalse(rm.isRidesPostedorTaken(r));
		jr.ride_confirmed = false;
		assertTrue(rm.isRidesPostedorTaken(r));
	}

	@Test
	public void testSearchByDate() throws ParseException {
		
		rides r = new rides();
		date_info d = new date_info();
		d.date = "15-Apr-2020";
		r.set_date(d);
		rides r1 = new rides();
		date_info d1 = new date_info();
		d1.date = "16-Apr-2020";
		r1.set_date(d1);
		ridesManager rm = new ridesManager();
		rm.createRide(r);
		rm.createRide(r1);
		reportsManager RM = new reportsManager();
		String start_date = "16-Apr-2020";
		String end_date = "";
		int pid =907;
		reports R = RM.searchByDate(rm.getallRide(),pid, start_date, end_date);
		assertEquals(1,R.get_report_detail().get(0).count);
		rm.getallRide().clear();
	}

	@Test
	public void testViewAllRides() throws ParseException {
		rides r = new rides();
		date_info d = new date_info();
		d.date = "15-Apr-2020";
		r.set_date(d);
		rides r1 = new rides();
		date_info d1 = new date_info();
		d1.date = "16-Apr-2020";
		r1.set_date(d1);
		ridesManager rm = new ridesManager();
		rm.createRide(r);
		rm.createRide(r1);
		reportsManager RM = new reportsManager();
		int pid =907;
		reports R = RM.ViewAllRides(pid);
		assertEquals(R.get_report_detail().get(0).count, 2);
		rm.getallRide().clear();
	}

	@Test
	public void testSearchRidesReports() throws ParseException {
		rides r = new rides();
		date_info d = new date_info();
		d.date = "15-Apr-2020";
		r.set_date(d);
		rides r1 = new rides();
		date_info d1 = new date_info();
		d1.date = "16-Apr-2020";
		r1.set_date(d1);
		rides r2 = new rides();
		date_info d2 = new date_info();
		d2.date = "16-Apr-2020";
		location l = new location();
		l.from_zip = "60616";
		r2.set_location(l);
		r2.set_date(d2);
		ridesManager rm = new ridesManager();
		rm.createRide(r);
		rm.createRide(r1);
		rm.createRide(r2);
		reportsManager RM = new reportsManager();
		String start_date = "14-Apr-2020";
		String end_date = "";
		int pid =907;
		reports R = RM.SearchRidesReports(pid, start_date, end_date);
		assertEquals(2,R.get_report_detail().get(0).count);
	    start_date = "";
	    R = RM.SearchRidesReports(pid, start_date, end_date);
		assertEquals(3,R.get_rides());	
		end_date = "16-Apr-2020";
	    R = RM.SearchRidesReports(pid, start_date, end_date);
		assertEquals(3,R.get_rides());
		start_date = "14-Apr-2020";
		R = RM.SearchRidesReports(pid, start_date, end_date);
		assertEquals(3,R.get_rides());
		pid =911;
		R = RM.SearchRidesReports(pid, start_date, end_date);
		assertEquals(0,R.get_rides());
		join_request jr = new join_request();
		jr.ride_confirmed = true;
		join_request jr1 = new join_request();
		jr1.ride_confirmed = true;
		join_request jr2 = new join_request();
		jr2.ride_confirmed = true;
		r.add_list_join_request(jr);
		r1.add_list_join_request(jr1);
		r2.add_list_join_request(jr2);
		start_date = "14-Apr-2020";
		end_date = "";
	    R = RM.SearchRidesReports(pid, start_date, end_date);
		assertEquals(2,R.get_report_detail().get(0).count);
	    start_date = "";
	    R = RM.SearchRidesReports(pid, start_date, end_date);
		assertEquals(3,R.get_rides());	
		end_date = "16-Apr-2020";
	    R = RM.SearchRidesReports(pid, start_date, end_date);
		assertEquals(3,R.get_rides());
		start_date = "14-Apr-2020";
		R = RM.SearchRidesReports(pid, start_date, end_date);
		assertEquals(3,R.get_rides());
		
		rm.getallRide().clear();
	}

}
