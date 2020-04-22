package sar;

import java.text.ParseException;
import java.util.List;

public interface BoundaryInterfaceReports {
	public List<reports> getAllReport();
	public reports ViewAllRides(int lpid) throws ParseException;
	public reports SearchRidesReports(int lpid, String start_date, String end_date ) throws ParseException;
}
