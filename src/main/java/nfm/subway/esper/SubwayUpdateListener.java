package nfm.subway.esper;

import org.apache.log4j.Logger;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

public class SubwayUpdateListener implements UpdateListener {
	static Logger log = Logger.getLogger(SubwayUpdateListener.class);

	public void update(EventBean[] newEvents, EventBean[] oldEvents) {
		if(newEvents != null){
			for(EventBean event : newEvents)
				System.out.println("Sensor ID " + event.get("snsr_id") + " Value: " + event.get("value") + " Count: " + event.get("count(*)"));

		}
	}
}
