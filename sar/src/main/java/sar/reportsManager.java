package sar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class reportsManager implements BoundaryInterfaceReports{

    public static List<reports> Reports = new ArrayList<reports>() {{add(new reports(907,"Rides posted between two dates"));
    add(new reports(911,"Rides taken between two dates"));}};
    
    @Override
    public List<reports> getAllReport(){
    	
    	return Reports;
    	
    }
    
    public boolean isRidesPostedorTaken(rides r) {
    	
    	List<join_request> jr = r.get_list_join_request();
    	if(jr.isEmpty()) {
    		return true;
    	}else {
    		Iterator<join_request> li = jr.listIterator();
    		while(li.hasNext()) {
    			join_request j = li.next();
    			if(j.ride_confirmed == true) {
    				return false;
    			}
    		}
    		return true;
    	}
    }
    public void find_by_date( List<report_detail> rd, rides l, int i) {
		if(i==1) {
    		report_detail T = new report_detail();
    		T.count =1;
    		T.from_zip = l.get_location().from_zip;
    		T.to_zip = l.get_location().to_zip;
    		rd.add(T);
		}else
		{
			boolean mark = false;
			Iterator<report_detail> li2 = rd.listIterator();
			while(li2.hasNext()) {
				report_detail T1 = li2.next();
				if((T1.from_zip == l.get_location().from_zip)&& (T1.to_zip==l.get_location().to_zip))
				{
					mark = true;
					T1.count++;
					break;
				}	
				
			}
			if(mark == false) {
				report_detail T2 = new report_detail();
    			T2.count =1;
	    		T2.from_zip = l.get_location().from_zip;
	    		T2.to_zip = l.get_location().to_zip;
	    		rd.add(T2);
			}
		}
    	
    }
    public void search1(reports R, int pid,String start_date, String end_date, Iterator<rides> li1, int i, List<report_detail> rd) {
    	if(pid == 907) {		
		    while(li1.hasNext()) {
		    	rides l = li1.next(); 
		    	i++;
		    	find_by_date(rd, l, i);	
		    }
		    R.set_rides(i);
		    R.set_end_date(end_date);
		    R.set_start_date(start_date);
    	   }
		    else
    		{
    			if(pid == 911) {
    				while(li1.hasNext()) {
        		    	rides l = li1.next();
        		    	if(!isRidesPostedorTaken(l)) {
        		    		i++;
        		    		find_by_date(rd, l, i);	
        		    	}
        		    }
        		    R.set_rides(i);
        		    R.set_end_date(end_date);
        		    R.set_start_date(start_date);
    				
    			}
    }
  }
   
    public void search2(reports R, int pid,String start_date, String end_date, Iterator<rides> li1, int i, List<report_detail> rd) throws ParseException {
    	Date date = new SimpleDateFormat("dd-MMM-yyyy").parse(end_date);
		if(pid == 907) {		
		    while(li1.hasNext()) {
		    	rides l = li1.next();
		    	Date date1 = new SimpleDateFormat("dd-MMM-yyyy").parse(l.get_date().date);
		    	if((date1.before(date)||date1.equals(date))) {
		    		i++;
		    		find_by_date(rd, l, i);	
		    	}
		    }
		    R.set_rides(i);
		    R.set_end_date(end_date);
		    R.set_start_date(start_date);
		}else
		{
			if(pid == 911) {
				while(li1.hasNext()) {
    		    	rides l = li1.next();
    		    	Date date1 = new SimpleDateFormat("dd-MMM-yyyy").parse(l.get_date().date);
    		    	if(!isRidesPostedorTaken(l)&&(date1.before(date)||date1.equals(date))) {
    		    		i++;
    		    		find_by_date(rd, l, i);	
    		    	}
    		    }
    		    R.set_rides(i);
    		    R.set_end_date(end_date);
    		    R.set_start_date(start_date);
			}
		}
    }
    public void search3(reports R, int pid,String start_date, String end_date, Iterator<rides> li1, int i, List<report_detail> rd) throws ParseException {
    	Date date = new SimpleDateFormat("dd-MMM-yyyy").parse(start_date);
		if(pid == 907) {		
		    while(li1.hasNext()) {
		    	rides l = li1.next();
		    	Date date1 = new SimpleDateFormat("dd-MMM-yyyy").parse(l.get_date().date);
		    	if((date1.after(date)||date1.equals(date))) {
		    		i++;
		    		find_by_date(rd, l, i);	
		    	}
		    }
		    R.set_rides(i);
		    R.set_end_date(end_date);
		    R.set_start_date(start_date);
		
		}else
		{
			if(pid == 911) {
				while(li1.hasNext()) {
    		    	rides l = li1.next();
    		    	Date date1 = new SimpleDateFormat("dd-MMM-yyyy").parse(l.get_date().date);
    		    	if(!isRidesPostedorTaken(l)&&(date1.after(date)||date1.equals(date))) {
    		    		i++;
    		    		find_by_date(rd, l, i);	
    		    	}
    		    }
    		    R.set_rides(i);
    		    R.set_end_date(end_date);
    		    R.set_start_date(start_date);
		
			}
		}
    } 
    public void search4(reports R, int pid,String start_date, String end_date, Iterator<rides> li1, int i, List<report_detail> rd) throws ParseException {
    	Date date = new SimpleDateFormat("dd-MMM-yyyy").parse(start_date);
		Date date2 = new SimpleDateFormat("dd-MMM-yyyy").parse(end_date);
		if(pid == 907) {		
		    while(li1.hasNext()) {
		    	rides l = li1.next();
		    	Date date1 = new SimpleDateFormat("dd-MMM-yyyy").parse(l.get_date().date);
		    	if(((date1.after(date)&&date1.before(date2))||date1.equals(date)||date1.equals(date2))) {
		    		i++;
		    		find_by_date(rd, l, i);	
		    	}
		    }
		    R.set_rides(i);
		    R.set_end_date(end_date);
		    R.set_start_date(start_date);
		}else
		{
			if(pid == 911) {
				while(li1.hasNext()) {
    		    	rides l = li1.next();
    		    	Date date1 = new SimpleDateFormat("dd-MMM-yyyy").parse(l.get_date().date);
    		    	if(!isRidesPostedorTaken(l)&&((date1.after(date)&&date1.before(date2))||date1.equals(date)||date1.equals(date2))) {
    		    		i++;
    		    		find_by_date(rd, l, i);	
    		    	}
    		    }
    		    R.set_rides(i);
    		    R.set_end_date(end_date);
    		    R.set_start_date(start_date);
		
			}
		}
    }
    public reports searchByDate(List<rides> list, int pid, String start_date, String end_date ) throws ParseException {
    	reports R = null;
    	Iterator<reports> li = Reports.listIterator();
	    while(li.hasNext()) {
	    	reports l = li.next();
	    	if(l.get_pid()==pid) {
	    		R=l;
	    		R.get_report_detail().clear();
	    	}
	    }
	    List<report_detail> rd = R.get_report_detail();
		Iterator<rides> li1 = list.listIterator();
		int i = 0;
    	if(start_date.isEmpty()&&end_date.isEmpty()){
    		 search1(R, pid, start_date,  end_date, li1,  i, rd);	
    		 return R;	
    	}
        if(start_date.isEmpty()&&!end_date.isEmpty()) {
    			
    	     search2(R, pid, start_date,  end_date, li1,  i, rd);
    	     return R;	
    	}
    	if(!start_date.isEmpty()&&end_date.isEmpty()) {
    		 search3(R, pid, start_date,  end_date, li1,  i, rd);
   	         return R;	
				
    	}
    	if(!start_date.isEmpty()&&!end_date.isEmpty()) {
    		 search4(R, pid, start_date,  end_date, li1,  i, rd);
   	         return R;			
    	}
    	return R;
    }
    
    @Override
    public reports ViewAllRides(int lpid) throws ParseException {
    	List<rides> list = ridesManager.get_Rides();
        String a = "",b = "";
        
			return searchByDate(list, lpid, a, b);
    }
    
    @Override
    public reports SearchRidesReports(int lpid, String start_date, String end_date ) throws ParseException {
    	List<rides> list = ridesManager.get_Rides();
    	return searchByDate(list, lpid, start_date, end_date);
    }
}
