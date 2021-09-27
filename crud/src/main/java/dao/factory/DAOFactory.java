package dao.factory;

import dao.jdbc.ContatoDAOJDBC;
import db.DB;

public class DAOFactory {

	public static ContatoDAO createContatoDAO() {
		return new ContatoDAOJDBC(DB.getConnection());
	}
	
}
