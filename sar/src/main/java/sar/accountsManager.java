package sar;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
		
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sar.accounts;
import sar.ridesManager;
public class accountsManager implements BoundaryInterfaceAccounts {
	private static List<accounts> Accounts = new ArrayList<accounts>();
    public static void accountsPUT(accounts A, accounts B) {
    	A.set_first_name(B.get_first_name());
    	A.set_last_name(B.get_last_name());
    	A.set_phone(B.get_phone());
    	A.set_picture(B.get_picture());
    	if(B.get_status()) {
    		A.set_status_active();
    	}
    }
    /*Checks if account is active*/
    public static boolean checkaccount(int id) { 
    	
    	Iterator<accounts> li = Accounts.listIterator();
        while(li.hasNext()) {
            accounts l = li.next();
            if(l.matchesId(id)) {
            	if(l.get_status())
            	    return true;
            	else
            		return false;
            }
        }
    	return false;
    }
    /*Checks if phone is valid or not*/
    public static boolean checkphone(String phone){
    	Pattern pattern = Pattern.compile("\\d{3}-\\d{3}-\\d{4}");
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }
    
    /*Checks if both strings contain non-alphabetic characters excluding a space character*/
    public static boolean checknum(String name){
        int length2=0,i=0;
        char c; 
                length2=name.length();

                    for(i=0;i<length2;i++)    //Check for `Lastname`
                    {  
                        c = name.charAt(i);  
                        if(!((c>='a' && c<='z')||(c>='A' && c<='Z')) && c!=' ')
                        {
                            return false;
                        }
                    }

     return true;
     }
    
	@Override
	public void createAccount(accounts T) {
		Accounts.add(T);
	}
	
	@Override
	public boolean activateAccount(int id, accounts T) {
		Iterator<accounts> li = Accounts.listIterator();
        while(li.hasNext()) {
            accounts l = li.next();
            if(l.matchesId(id)) {
            	accountsPUT(l, T);
            	return true;
            }
        }
        return false;
	}
	
	@Override
	public boolean updateAccount(int id, accounts T) {
		Iterator<accounts> li = Accounts.listIterator();
        while(li.hasNext()) {
            accounts l = li.next();
            if(l.matchesId(id)) {
            	accountsPUT(l, T);
            	return true;
            }
        }
        return false;
	}
	
	@Override
	public boolean deleteAccount(int id) {
		Iterator<accounts> li = Accounts.listIterator();
        while(li.hasNext()) {
            accounts l = li.next();
            if(l.matchesId(id)) {
            	Accounts.remove(l);
            	return true;
            }
        }
        return false;
	}
	
	@Override
	public accounts getAccountDetail(int id) {
		Iterator<accounts> li = Accounts.listIterator();
        while(li.hasNext()) {
            accounts l = li.next();
            if(l.matchesId(id)) {
            	
            	return l;
            }
        }
        return(new NullAccounts());
	}
	
	@Override
	public List<accounts> getallAccount(){
		return Accounts;
	}
	
	@Override
	public List<accounts> searchAccount(String s) {
		List<accounts> list = new ArrayList<accounts>();
		Iterator<accounts> li = Accounts.listIterator();
        while(li.hasNext()) {
            accounts l = li.next();
            if(l.get_first_name().equalsIgnoreCase(s)||l.get_last_name().equalsIgnoreCase(s)||l.get_phone().equalsIgnoreCase(s)||l.get_first_name().contains(s)||l.get_last_name().contains(s)||l.get_phone().contains(s)) {
            	
            	list.add(l);
            }
        }
        return list;
	}
	
	public boolean match_rider_id(rides r, int aid) {
		List<join_request> list = r.get_list_join_request();
		if(list.isEmpty())
			return false;
		else
		{
			Iterator<join_request> li = list.listIterator();
			while(li.hasNext()) {
				join_request j = li.next();
				
				if(j.aid == aid&&j.ride_confirmed==true&&j.pickup_confirmed==true) {
					return true;
				}
				
			}
			return false;
		}
	}
	public String getName(int aid) {
		Iterator<accounts> li = Accounts.listIterator();
		while(li.hasNext()) {
            accounts l = li.next();
            if(l.matchesId(aid)) { 
               return l.get_first_name();	
             }
            }
		return null;
	}
	@Override
	public boolean createRating(ratings R, int id) {
		
		Iterator<accounts> li = Accounts.listIterator();
        while(li.hasNext()) {
            accounts l = li.next();
            if(l.matchesId(id)) {        	
            	List<rides> list = ridesManager.get_Rides();
            	Iterator<rides> li1 = list.listIterator();
            	while(li1.hasNext()) {
            		rides r = li1.next();
            		if(r.get_rid()==R.get_rid())
            		{
            			if(r.get_aid()==id && match_rider_id(r, R.get_sent_by_id())) {
            				R.set_driver_ratings();
            				R.set_first_name(getName(R.get_sent_by_id()));
            				l.add_detail(R);
            				l.set_rides(l.get_rides()+1);
            				
            				return true;
            			}
            			
            			if(r.get_aid()==R.get_sent_by_id()&&match_rider_id(r,id))
            			{
            				R.set_rider_ratings();
            				R.set_first_name(getName(R.get_sent_by_id()));
            				l.add_detail(R);
            				l.set_rides(l.get_rides()+1);
            				return true;
            			}
            			return false;
            		}
            	}
            	
            }
        }
        return false;
	}
}
