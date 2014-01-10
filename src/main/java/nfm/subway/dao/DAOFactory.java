package nfm.subway.dao;

public class DAOFactory {
	public RawDataDAO rawDataDAO() {
		return new RawDataDAO(connectionMaker());
	}

	private ConnectionMaker connectionMaker() {
		return new JDBCConnectionMaker();
	}
}
