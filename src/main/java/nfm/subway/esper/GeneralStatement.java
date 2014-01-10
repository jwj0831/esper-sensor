package nfm.subway.esper;

import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.UpdateListener;

public class GeneralStatement {
	private EPStatement statement;
	
	public void createStatement(EPAdministrator admin, String stmt){
		statement = admin.createEPL(stmt);
	}

	public void addListener(UpdateListener listener)
	{
		statement.addListener(listener);
	}
	
}
