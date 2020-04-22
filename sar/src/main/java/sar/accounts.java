package sar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import sar.UniqueIdGenerator;
import sar.ratings;

public class accounts {
	
	private int aid;
	private String first_name;
	private String last_name;
	private String phone;
	private String picture;
	private boolean is_active; 
	
	private String date_created;
	private int rides;
	private int ratings;
	private Double average_rating;
	private List<ratings> detail;

	public accounts() {
		this.aid = UniqueIdGenerator.getUniqueID();
		this.is_active = false;
		DateFormat df = new SimpleDateFormat("dd-MMM-yyyy, HH:mm:ss");
		Date date = new Date();
		this.date_created = df.format(date);
		this.detail = new ArrayList<ratings>();
		this.average_rating = null;
	}
	
	public void AddRatings(ratings r) {
		this.detail.add(r);
	}
	
	public boolean isNull() {
        return false;
    }
	
	public int get_aid() {
		return this.aid;
	}
	
	public String get_first_name() {
		return this.first_name;
	}
	
	public void set_first_name(String s) {
		this.first_name = s;
	}
	
	public String get_last_name() {
		return this.last_name;
	}
	
	public void set_last_name(String s) {
		this.last_name = s;
	}
	
	public String get_phone() {
		return this.phone;
	}
	
	public void set_phone(String s) {
		this.phone = s;
	}
	
	public String get_picture() {
		return this.picture;
	}
	
	public void set_picture(String s) {
		this.picture = s;
	}
	
	public boolean get_status() {
		return this.is_active;
	}
	
	public void set_status_active() {
		this.is_active = true;
	}
	
	public void set_status_inactive() {
		this.is_active = false;
	}
	
	public String get_date_created(){
		return this.date_created;
	}
	public int get_rides() {
		return this.rides;
	}
	
	public void set_rides(int r) {
		this.rides = r;
	}
	
	public int get_ratings() {
		return this.ratings;
	}
	
	public void set_ratings(int r) {
		this.ratings = r;
	}
	
	public List<ratings> get_detail(){
		return this.detail;
	}
	
	public boolean add_detail(ratings r) {
		
		boolean b = this.detail.add(r);
		this.set_average_rating();
		this.ratings = this.ratings+1;
		return b;
		
	}
	
	public Double get_average_rating() {
		return this.average_rating;
	}
	
	public void set_average_rating() {
		Iterator<ratings> li = this.detail.listIterator();
		if(this.average_rating == null)
			this.average_rating =(double) 0;
        while(li.hasNext()) {
            ratings l = li.next();
            this.average_rating += l.get_rating();
        }
        this.average_rating = this.average_rating/detail.size();
	}
	
	public boolean matchesId(int lid) {
	        return(lid == this.aid);
	    }

}
