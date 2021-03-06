import java.io.*;
import java.util.*;

public class CSP {
	
		
	public static void main(String [ ] args) throws IOException {
		
		long t1 = System.currentTimeMillis();
		Calendar cal = new GregorianCalendar();
	
		// Get the components of the time
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int min = cal.get(Calendar.MINUTE);             // 0..59
		int sec = cal.get(Calendar.SECOND);             // 0..59
		
		System.out.print("\nted at "+hour+":"+min+":"+sec+"... ");

		MyMapGraph america = new MyMapGraph();
		
		long t2 = System.currentTimeMillis();
		System.out.println("Finished "+(t2-t1)/1000+" seconds. ("+(t2-t1)+"ms.)");
		
		america.colorGraph();
		
		System.out.println(america.displayGraph());
	}
}
