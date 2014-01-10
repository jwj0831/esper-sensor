package nfm.subway.cli;

import java.sql.SQLException;

import nfm.subway.dao.DAOFactory;
import nfm.subway.dao.RawDataDAO;
import nfm.subway.esper.CEPEngine;

public class CLITestMain {
	private static int MAX_EVENT_NUM = 10000;
	private static int INTERVAL_TIME = 500;
	
	public static void main(String args[]) throws ClassNotFoundException, SQLException {
		RawDataDAO dao = new DAOFactory().rawDataDAO();
		System.out.println("Finish to send Event");
		
		CEPEngine engine = new CEPEngine();
		System.out.println("Finish to prepare Esper");
		int i;
		for(i=1; i<MAX_EVENT_NUM; i++){
			engine.getEpServiceList().get(0).getEPRuntime().sendEvent(dao.get(i));
			try {Thread.sleep(INTERVAL_TIME);} catch (InterruptedException e) { }
		}
	}
}
