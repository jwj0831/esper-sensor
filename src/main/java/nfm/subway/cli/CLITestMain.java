package nfm.subway.cli;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import nfm.subway.dao.DAOFactory;
import nfm.subway.dao.RawDataDAO;
import nfm.subway.data.RawData;
import nfm.subway.esper.CEPEngine;

public class CLITestMain {
	private static int MAX_EVENT_NUM = 10000;
	
	public static void main(String args[]) throws ClassNotFoundException, SQLException {
		RawDataDAO dao = new DAOFactory().rawDataDAO();
		System.out.println("Finish to send Event");
		
		CEPEngine engine = new CEPEngine();
		System.out.println("Finish to prepare Esper");
		int i;
		String oldDate = "";
		String newDate = "";
		int waitTime = 0;
		
		for(i=1; i<MAX_EVENT_NUM; i++){
			RawData event = dao.get(i);
			newDate = event.getInput_date();
			
			if(i != 1) 
				waitTime = getDelayTime(newDate, oldDate);
			
			if(waitTime > 0) {
				System.out.println("Wait for " + waitTime + " Sec...");
				try {Thread.sleep(waitTime * 1000);} catch (InterruptedException e) { }
			}
			
			Calendar calendar = Calendar.getInstance();
            java.util.Date date = calendar.getTime();
            String today = (new SimpleDateFormat("yyyyMMddHHmmss").format(date));
            event.setInput_date(today);
			
			engine.getEngine().getEPRuntime().sendEvent(event);
			oldDate = newDate;
		}
	}
	
	public static int getDelayTime(String newDate, String oldDate) {
		int newDateHour = Integer.parseInt(newDate.substring(8, 10));
		int newDateMin = Integer.parseInt(newDate.substring(10, 12));
		int newDateSec = Integer.parseInt(newDate.substring(12, 14));
		int oldDateHour = Integer.parseInt(oldDate.substring(8, 10));
		int oldDateMin = Integer.parseInt(oldDate.substring(10, 12));
		int oldDateSec = Integer.parseInt(oldDate.substring(12, 14));
		
		if(newDateSec > oldDateSec){
			return newDateSec - oldDateSec;
		}
		else{
			int minDiff = newDateMin - oldDateMin;
			
			if(minDiff < 0){
				int hourDiff = newDateHour - oldDateHour;
				if(hourDiff < 0){
					return -1;
				}
				else if(hourDiff >= 1){
					return (hourDiff-1)*3600 + ((newDateMin-1) + (60-oldDateMin))*60 + newDateSec + (60-oldDateSec); 
				}
			}
			else if(minDiff >= 1) {
				return (minDiff-1)*60 + newDateSec + (60-oldDateSec); 
			}
			else {
				return -1;
			}
		}
		return -1;
	}
	
}
