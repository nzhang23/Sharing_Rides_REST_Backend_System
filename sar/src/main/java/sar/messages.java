package sar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class messages {
      public int aid;
      public String msg;
      public int mid;
      public String date;
      
      public messages() {
  		this.mid = UniqueIdGenerator.getUniqueID();
  		DateFormat df = new SimpleDateFormat("dd-MMM-yyyy, HH:mm:ss");
		Date date1 = new Date();
		this.date = df.format(date1);
      }
}
