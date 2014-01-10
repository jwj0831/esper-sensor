package nfm.subway.dao;

import java.sql.SQLException;

import nfm.subway.data.RawData;

public class RawDataTest {
	public static void main(String args[]) throws ClassNotFoundException, SQLException {
		RawDataDAO dao = new DAOFactory().rawDataDAO();
	
		RawData rawData = dao.get(1);
		System.out.println(rawData.getSnsr_id());
		System.out.println(rawData.getValue());
		System.out.println(rawData.getInput_date());
	}
}
