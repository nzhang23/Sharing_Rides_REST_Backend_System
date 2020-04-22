package sar;


import java.util.List;

import com.google.gson.JsonObject;

public interface BoundaryInterfaceJson {
   JsonObject Json_invalid_phone_response();
   JsonObject Json_invalid_account_isactive_status_response(int id);
   JsonObject Json_invalid_account_isactive_response(int id);
   JsonObject Json_invalid_account_firstname_response(int id);
   JsonObject Json_invalid_account_lastname_response(int id);
   JsonObject Json_invalid_ratings_resonse(ratings r, int lid);
   JsonObject Json_invalid_date_response();
   JsonObject Json_invalid_rides_response(rides T);
   JsonObject Json_invalid_creator_for_rides_response(int lid);
   JsonObject Json_invalid_ride_confirmed_response(int lid);
   JsonObject Json_invalid_creator_for_join_requests_response(join_request T,int lid);
   JsonObject Json_invalid_ride_confirmed_with_jid_response(int lrid, int ljid);
   JsonObject Json_invalid_rides_join_request_response(join_request T, int lrid, int ljid);
   JsonObject Json_invalid_pickup_confirmed_response(int lrid, int ljid);
   JsonObject Json_invalid_account_join_request_response(join_request T, int lrid, int ljid);
   JsonObject Json_invalid_account_messages_response(int lrid);
   List<JsonObject> getallAccounts_response(List<accounts> l);
   List<JsonObject> searchAccounts_response(List<accounts> a);
   JsonObject getDriverViews_response(accounts l);
   JsonObject getRiderViews_response(accounts l);
   List<JsonObject> getallRides_response(List<rides> l);
   JsonObject getRidesDetail_response(rides a, BoundaryInterfaceAccounts bi);
   List<JsonObject> viewAllMessages_response(List<messages> list);
   List<JsonObject> viewAllReports_response(List<reports> list);
}
