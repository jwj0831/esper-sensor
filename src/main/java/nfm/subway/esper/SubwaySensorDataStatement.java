package nfm.subway.esper;

import com.espertech.esper.client.EPAdministrator;

public class SubwaySensorDataStatement extends GeneralStatement{
	//private static String stmt = "insert into DataStream select snsr_id, count(*), value from RawData.win:time(300 sec) group by snsr_id";
	//private static String stmt = "insert into DataStream select count(*) as cnt from pattern [ every ( RawData(value>200) )]";
	//private static String stmt2 = "select * from DataStream";
	
	private static String stmt = "insert into DataStream select * from RawData";
	
	public SubwaySensorDataStatement(EPAdministrator admin){
		this.createStatement(admin, stmt);
		//this.createStatement(admin, stmt2);
	}

}
	