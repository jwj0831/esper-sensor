package nfm.subway.esper;

import com.espertech.esper.client.EPAdministrator;

public class SubwaySensorDataStatement extends GeneralStatement{
	private static String stmt = "select snsr_id, count(*), value from RawData.win:time(300 sec) group by snsr_id";
	
	public SubwaySensorDataStatement(EPAdministrator admin){
		this.createStatement(admin, stmt);
	}

}
