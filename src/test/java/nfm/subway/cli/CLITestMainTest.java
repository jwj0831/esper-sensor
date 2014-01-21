package nfm.subway.cli;

import org.junit.Test;
import static org.junit.Assert.*;

public class CLITestMainTest {
	
	@Test
	public void 시분초새시간이모두높을때() {
		String newDate = "20140115130118";
		String oldDate = "20140115130103";
		int waitTime = CLITestMain.getDelayTime(newDate, oldDate);
		assertEquals(15, waitTime);
	}
	
	@Test
	public void 초가새시간이느릴때() {
		String newDate = "20140115130103";
		String oldDate = "20140115130050";
		int waitTime = CLITestMain.getDelayTime(newDate, oldDate);
		assertEquals(13, waitTime);
	}

	@Test
	public void 초분새시간이모두느릴때() {
		String newDate = "20140115150002";
		String oldDate = "20140115145950";
		int waitTime = CLITestMain.getDelayTime(newDate, oldDate);
		assertEquals(12, waitTime);
	}
	
}
