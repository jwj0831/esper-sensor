package nfm.subway.esper;

import java.io.File;
import java.util.ArrayList;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;

public class CEPEngine {
	static Logger log = Logger.getLogger(CEPEngine.class);
	private ArrayList<EPServiceProvider> epServiceList;
	
	public static void main(String[] args) {
		PropertyConfigurator.configure("etc/log4j.properties");
		log.setLevel(Level.WARN);
	}
	
	public CEPEngine() {
		epServiceList = start("Subway-Event-Processing");
	}
	
	public ArrayList<EPServiceProvider> getEpServiceList() {
		return epServiceList;
	}

	private static ArrayList<EPServiceProvider> start(String engineURI) {
		ArrayList<EPServiceProvider> list = new ArrayList<EPServiceProvider>();
		
		log.info("Setting up Confiugration");
		
		//XML Configuration Test
		Configuration config = new Configuration();
		config.configure(new File("./nfm.examples.cfg.xml"));
		EPServiceProvider epService = EPServiceProviderManager.getProvider(engineURI, config);
		list.add(epService);
		SubwaySensorDataStatement eplStmt = new SubwaySensorDataStatement(epService.getEPAdministrator());
		eplStmt.addListener(new SubwayUpdateListener());
		
		return list;
	}

}
