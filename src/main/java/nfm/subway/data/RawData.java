package nfm.subway.data;

public class RawData {
	long id;
	String snsr_id;
	double value;
	String input_date;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getSnsr_id() {
		return snsr_id;
	}
	public void setSnsr_id(String snsr_id) {
		this.snsr_id = snsr_id;
	}
	
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	
	public String getInput_date() {
		return input_date;
	}
	public void setInput_date(String input_date) {
		this.input_date = input_date;
	}
}
