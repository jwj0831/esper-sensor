package nfm.subway.esper;

import org.apache.log4j.Logger;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

import nfm.subway.data.*;

public class PatternTestListener implements UpdateListener {
	static Logger log = Logger.getLogger(SubwayUpdateListener.class);

	public void update(EventBean[] newEvents, EventBean[] oldEvents) {
		if(newEvents != null){
			for(EventBean event : newEvents) {
				RawData test = (RawData) event.get("test");
				System.out.println("Value : " + test.getValue());
				//System.out.println("Sensor ID " + event.get("snsr_id") + " Value: " + event.get("value") );
			}

		}
	}
}
