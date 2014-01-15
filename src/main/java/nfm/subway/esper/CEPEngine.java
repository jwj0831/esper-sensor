package nfm.subway.esper;

import java.io.File;
import java.util.ArrayList;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esperio.http.EsperIOHTTPAdapter;
import com.espertech.esperio.http.config.ConfigurationHTTPAdapter;
import com.espertech.esperio.http.config.Request;

public class CEPEngine {
	private static final String ENGINE_URI = "subway";
	private static final String REQUEST_URI = "http://117.16.146.87:80/esper/test";
	
	private EPServiceProvider engine;
	
	public CEPEngine() {
		configureEngine();
		startHTTPAdapter();
	}
	
	private void configureEngine() {
		//XML Configuration Test
		Configuration config = new Configuration();
		config.configure(new File("./nfm.subway.cfg.xml"));

		EPServiceProvider engine = EPServiceProviderManager.getProvider(ENGINE_URI, config);
		SubwaySensorDataStatement eplStmt = new SubwaySensorDataStatement(engine.getEPAdministrator());
		eplStmt.addListener(new SubwayUpdateListener());
		this.engine = engine;
	}

	private void startHTTPAdapter() {
		ConfigurationHTTPAdapter adapterConfig = new ConfigurationHTTPAdapter();
		Request request = new Request();
		request.setStream("DataStream");
		request.setUri(REQUEST_URI);
		adapterConfig.getRequests().add(request);
		
		EsperIOHTTPAdapter httpAdapter = new EsperIOHTTPAdapter(adapterConfig, ENGINE_URI);
		
		httpAdapter.start();
	}

	public EPServiceProvider getEngine() {
		return engine;
	}
	

}
