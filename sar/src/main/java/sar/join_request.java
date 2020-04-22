package sar;

public class join_request {

	public int jid;
	public int aid;
	public int passengers;
	public Boolean ride_confirmed;
	public Boolean pickup_confirmed;
	
	public join_request() {
		
		this.jid = UniqueIdGenerator.getUniqueID();
	}

	
	

    public boolean isNull() {
        return false;
    }
}
