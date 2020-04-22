package sar;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import sar.accounts;
public class rides {

	
	private int rid;
	private location location_info;
	private car car_info;
	private date_info date_time;
	private int aid;
	private int max_passengers;
    private Double amount_per_passenger;
	private String conditions;
	//private accounts driver;
	private List<join_request> list_join_requests;
	private List<messages> list_messages;
	
	public rides() {
		this.rid = UniqueIdGenerator.getUniqueID();
		this.list_join_requests = new ArrayList<join_request>();
		this.list_messages = new ArrayList<messages>();
		this.date_time = new date_info();
		this.car_info = new car();
		this.location_info = new location();
		this.amount_per_passenger = null;
	}
	
	public boolean matchesId(int lid) {
        return(lid == this.rid);
    }
	
	public int get_rid() {
		return this.rid;
	}
	
	public location get_location() {
		return this.location_info;
	}
	
	public void set_location(location l) {
		this.location_info.from_city = l.from_city;
		this.location_info.to_city = l.to_city;
		this.location_info.from_zip = l.from_zip;
		this.location_info.to_zip = l.to_zip;
	}
	
	public car get_car() {
		
		return this.car_info;
	}
	
	public void set_car(car c) {
		this.car_info.color = c.color;
		this.car_info.make = c.make;
		this.car_info.model = c.model;
		this.car_info.plate_serial = c.plate_serial;
		this.car_info.plate_state = c.plate_state;
	}
	
	public date_info get_date() {
		return this.date_time;
	}
	
	public void set_date(date_info d) {
		this.date_time.date = d.date;
		this.date_time.time = d.time;
	}

	public int get_aid() {
		return this.aid;
	}
	
	public void set_aid(int id) {
		this.aid = id;
	}
	
	public int get_max_passengers() {
		return this.max_passengers;
	}
	
	public void set_max_passengers(int m) {
          this.max_passengers =m;
	}
	
	public Double get_amount_per_passengers() {
		return this.amount_per_passenger;
	}
	
	public void set_amount_per_passengers(double m) {
		this.amount_per_passenger = m;

	}
	
	public String get_conditions() {
		return this.conditions;
	}
	
	public void set_conditions(String s) {
		this.conditions = s;
	}
    
    public void add_list_join_request(join_request e) {
		
		this.list_join_requests.add(e);
	}
	
	public List<join_request> get_list_join_request(){
		return this.list_join_requests;
	}
	
	public join_request find_in_list_join_requests(int id) {
		Iterator<join_request> li = this.list_join_requests.listIterator();
        while(li.hasNext()) {
        	join_request l = li.next();
            if(l.jid == id)
            	return l;
            }
        return (new NullJoin_request());
	}
	public void add_list_messages(messages e) {
		
		this.list_messages.add(e);
	}
	
	public List<messages> get_list_messages(){
		return this.list_messages;
	}
	
	
	
	public boolean isNull() {
        return false;
    }
	
	
	
}
