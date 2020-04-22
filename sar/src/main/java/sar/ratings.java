package sar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import sar.UniqueIdGenerator;

public class ratings {
	private int sid;
	private int rid;
	private int sent_by_id;
	private int rating;
	private String comment;
	private String date;
	private String first_name;
	private boolean driver_or_rider;
	public ratings(){
		this.sid = UniqueIdGenerator.getUniqueID();
		DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
		Date date_created = new Date();
		this.date = df.format(date_created);
	}
	
	public boolean get_driver_ratings() {
		return this.driver_or_rider;
	}
	
	public void set_driver_ratings() {
		this.driver_or_rider = true;
	}
	
	public void set_rider_ratings() {
		this.driver_or_rider = false;
	}
	
	public boolean isNull() {
        return false;
	}
	
	public int getID() {
		return this.sid;
	}
	
	public int get_rid() {
		return this.rid;
	}
	
	public void set_rid(int id) {
		 this.rid = id;
	}
	
	public int get_sent_by_id() {
		return this.sent_by_id;
	}
	
	public void set_sent_by_rid(int id) {
		this.sent_by_id = id;
	}
	
	public int get_rating() {
		return this.rating;
	}
	
	public void set_rating(int r) {
		this.rating = r;
	}
	
	public String get_comment() {
		return this.comment;
	}
	
	public void set_comment(String r) {
		this.comment = r;
	}
	
	public String get_date() {
		return this.date;
	}
	
	public String get_first_name() {
		return this.first_name;
	}
	
	public void set_first_name(String r) {
		this.first_name = r;
	}
	
	public boolean matchesId(int lid) {
        return(lid == this.sid);
    }
}
