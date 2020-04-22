package sar;
import java.util.List;

public interface BoundaryInterfaceRides {
	void createRide(rides T);
	rides findRide(int id);
	boolean updateRide(rides T, int id);
	boolean deleteRide(int id);
	List<rides> getallRide();
	List<rides> searchRide(String from, String to, String date);
	void createJoin_request(join_request T, int id);
	boolean joint_request_ride_confirm_deny(join_request T, int lrid, int ljid);
	boolean joint_request_pickup_confirm(join_request T, int lrid, int ljid);
	void createMessage(messages T, int lrid);
	List<messages> viewAllMessage(int lrid);
}
