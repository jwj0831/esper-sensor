package nfm.subway.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import nfm.subway.data.*;

public class RawDataDAO {
	private ConnectionMaker connectionMaker;
	
	public RawDataDAO(ConnectionMaker connectionMaker) {
		this.connectionMaker = connectionMaker;
	}
	
	public void add(RawData rawData) throws ClassNotFoundException, SQLException {
		Connection c = connectionMaker.makeConnection();
		
		PreparedStatement ps = c.prepareStatement("INSERT INTO t002_rawdata(id, snsr_id, val, inp_dt_str) VALUES(?, ?, ?, ?)");
		ps.setLong(1, rawData.getId());
		ps.setString(2, rawData.getSnsr_id());
		ps.setDouble(3,rawData.getValue());
		ps.setString(4, rawData.getInput_date());
		
		ps.executeUpdate();
		
		ps.close();
		c.close();
	}
	
	public RawData get(long id) throws ClassNotFoundException, SQLException {
		Connection c = connectionMaker.makeConnection();
		
		PreparedStatement ps = c.prepareStatement("SELECT * FROM t002_rawdata WHERE id = ?");
		ps.setLong(1, id);
		ResultSet rs = ps.executeQuery();
		
		rs.next();
		RawData rawData = new RawData();
		rawData.setId(rs.getLong("id"));
		rawData.setSnsr_id(rs.getString("snsr_id"));
		rawData.setValue(rs.getDouble("val"));
		rawData.setInput_date(rs.getString("inp_dt_str"));
		
		rs.close();
		ps.close();
		c.close();
		
		return rawData;
	}
}
