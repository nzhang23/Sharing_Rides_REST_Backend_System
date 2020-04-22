package sar;

import java.util.ArrayList;
import java.util.List;

public class reports {

	private int pid;
	private String name;
	
	private String start_date;
	private String end_date;
	private int rides;
	
	private List<report_detail> detail;
	
	public reports(int pid, String name) {
		this.pid = pid;
		this.name = name;
		this.detail = new ArrayList<report_detail>();
	}
	
	public int get_pid() {
		return this.pid;
	}
	
	public String get_name() {
		return this.name;
	}
    
	public String get_start_date() {
		return this.start_date;
	}
	
	public void set_start_date(String s) {
		this.start_date = s;
	}	
    
	public String get_end_date() {
		return this.end_date;
	}
	
	public void set_end_date(String s) {
		this.end_date = s;
	}
	
	public int get_rides() {
		return this.rides;
	}
	
	public void set_rides(int r) {
		this.rides = r;
	}
	
	public List<report_detail> get_report_detail(){
		return this.detail;
	}
	
    public boolean add_in_list(report_detail e) {
    	 return this.detail.add(e);
    }
    
    public void empty_list() {
    	this.detail.clear();
    }
    
	public boolean isNull() {
        return false;
    }
}
