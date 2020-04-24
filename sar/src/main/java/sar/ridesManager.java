package sar;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
		
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sar.rides;
import sar.accounts;

public class ridesManager implements BoundaryInterfaceRides {
	private static List<rides> Rides = new ArrayList<rides>();
	
	
	/*check driver id is vaild or not*/
	public  static Boolean check_driver_id(join_request T, int rid) {
		Iterator<rides> li = Rides.listIterator();
        while(li.hasNext()) {
            rides l = li.next();
            if(l.matchesId(rid)) {
            	if(T.aid == l.get_aid()) {
					return true;
				}else {
					return false;
				}
            }
        }
        
        return null;
	}
	/*check rider id is vaild or not*/
	public  static Boolean check_rider_id(join_request T, int rid, int jid) {
		Iterator<rides> li = Rides.listIterator();
        while(li.hasNext()) {
            rides l = li.next();
            if(l.matchesId(rid)) {
                List<join_request> list =l.get_list_join_request();
                Iterator<join_request> li1 = list.listIterator();
                while(li1.hasNext()) {
                	join_request r = li1.next();
                	if(r.jid == jid)
                	{ 
                		if(r.aid == T.aid)
                		   return true;
                	    else
                		   return false;
                	}
                }
            }
        }
        return null;		
	}
	
	public static List<rides> get_Rides(){
		return Rides;
	}
	/*check date is vaild or not*/
	public static boolean checkDate(String s) {
		SimpleDateFormat sdfrmt = new SimpleDateFormat("dd-MMM-yyyy");
		sdfrmt.setLenient(false);
		try
	    {
	        Date javaDate = sdfrmt.parse(s); 
	    }
	    /* Date format is invalid */
	    catch (ParseException e)
	    {
	        return false;
	    }
		return true;
	}
	
	
	public static boolean checkJoin_request(join_request T) {
		if((T.ride_confirmed == null)&&(T.pickup_confirmed==null)) {
			return true;
		}
		return false;
	}
	
	public static void ridesPUT(rides l, rides T){
		l.set_amount_per_passengers(T.get_amount_per_passengers());
		l.set_car(T.get_car());
		l.set_conditions(T.get_conditions());
		l.set_date(T.get_date());
		l.set_location(T.get_location());
		l.set_max_passengers(T.get_max_passengers());
		l.set_aid(T.get_aid());
	}
	
	@Override
	public void createRide(rides T) {
		Rides.add(T);
	}
	
	@Override
	public rides findRide(int id) {
		Iterator<rides> li = Rides.listIterator();
        while(li.hasNext()) {
            rides l = li.next();
            if(l.matchesId(id)) {
            	return l;
            }
        }
        return (new NullRides());
	}
	
	@Override
	public boolean updateRide(rides T, int id) {
		
		rides l = findRide(id);
		if(l.isNull()) {
			return false;
		}else {	
		    ridesPUT(l,T);
		    return true;
		}
	}
	
	@Override
	public boolean deleteRide(int id) {
		Iterator<rides> li = Rides.listIterator();
	    while(li.hasNext()) {
	        rides l = li.next();
	        if(l.matchesId(id)) {
	        	Rides.remove(l);
	        	return true;
	        }
	    }
	    return false;
	}
	
	@Override
	public List<rides> getallRide(){
		return Rides;
	}
	
	public void search1(String from, String to, String date, rides l,List<rides> list ) {
		if(!to.isEmpty()) {
    		if(!date.isEmpty()) {
    			if((l.get_location().from_city.equalsIgnoreCase(from))&&(l.get_location().to_city.equalsIgnoreCase(to))&&(l.get_date().date.equalsIgnoreCase(date))) {
    				list.add(l);
    			}
    		}else {
    			if((l.get_location().from_city.equalsIgnoreCase(from))&&(l.get_location().to_city.equalsIgnoreCase(to))){
    				list.add(l);
    			}
    		}
    	}else {
    		if(!date.isEmpty()) {
    			if((l.get_location().from_city.equalsIgnoreCase(from))&&(l.get_date().date.equalsIgnoreCase(date))) {
    				list.add(l);
    			}
    		}else {
    			if((l.get_location().from_city.equalsIgnoreCase(from))){
    				list.add(l);
    			}
    		}
    	}
	}
	
	public void search2( String to, String date, rides l,List<rides> list ) {
		if(!to.isEmpty()) {
    		if(!date.isEmpty()) {
    			if((l.get_location().to_city.equalsIgnoreCase(to))&&(l.get_date().date.equalsIgnoreCase(date))) {
    				list.add(l);
    			}
    		}else {
    			if((l.get_location().to_city.equalsIgnoreCase(to))){
    				list.add(l);
    			}
    		}
    	}else {
    		if(!date.isEmpty()) {
    			if((l.get_date().date.equalsIgnoreCase(date))) {
    				list.add(l);
    			}
    		}else {
    			
    				list.add(l);
    		}
    }
	}
	@Override
	public List<rides> searchRide(String from, String to, String date){
		List<rides> list = new ArrayList<rides>();
		Iterator<rides> li = Rides.listIterator();
		 while(li.hasNext()) {
		        rides l = li.next();
		        if(!from.isEmpty()) {
		        	search1(from, to, date, l, list);
		        }else
		        {
		        	search2(to, date, l, list);
		        }
		 }
		 return list;
	}

	@Override
	public void createJoin_request(join_request T, int id) {
		
		rides r = findRide(id);
		r.add_list_join_request(T);
		
	}

	
	@Override
	public boolean joint_request_ride_confirm_deny(join_request T, int lrid, int ljid) {
		    rides r = findRide(lrid);
		    accountsManager am = new accountsManager();
		    accounts a = am.getAccountDetail(T.aid);
			List<join_request> list = r.get_list_join_request();
			Iterator<join_request> li = list.listIterator();
		    while(li.hasNext()) {
		    	join_request l = li.next();
		        if(l.jid == ljid) {
		        	l.ride_confirmed=T.ride_confirmed;
		        	if(T.ride_confirmed == true)
		        		a.set_rides(a.get_rides()+1);
		        	return true;
		        }
		    }
		    return false;
	}
	
	@Override
	public boolean joint_request_pickup_confirm(join_request T, int lrid, int ljid) {
		rides r = findRide(lrid);
		accountsManager am = new accountsManager();
		accounts a = am.getAccountDetail(T.aid);
		List<join_request> list = r.get_list_join_request();
		Iterator<join_request> li = list.listIterator();
	    while(li.hasNext()) {
	    	join_request l = li.next();
	        if(l.jid == ljid) {
	        	l.pickup_confirmed=T.pickup_confirmed;
	        	if(T.pickup_confirmed==true)
	        		a.set_rides(a.get_rides()+1);
	        	return true;
	        }
	    }
	    return false;
	}
	
	@Override
	public void createMessage(messages T, int lrid) {
		rides r = findRide(lrid);
		r.get_list_messages().add(T);
	}
	
	@Override
	public List<messages> viewAllMessage(int lrid) {
		rides r = findRide(lrid);
		return r.get_list_messages();
	}
}
