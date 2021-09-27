package dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.factory.ContatoDAO;
import db.DB;
import db.DbException;
import entities.Contato;

public class ContatoDAOJDBC implements ContatoDAO {
	
	private Connection conn;
	
	public ContatoDAOJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Contato cont) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement(
				"INSERT INTO CONTATO "
				+ "(NOME)"
				+ "VALUES "
				+ "(?)",
				Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, cont.getNome());
			
			Integer linhasAfetadas = st.executeUpdate();
			
			if(linhasAfetadas > 0) {
				ResultSet rs = st.getGeneratedKeys();
				DB.closeResultSet(rs);
			}
			else {
				throw new DbException("Erro desconhecido, nenhuma linha afetada!");
			}

		}catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void update(Contato cont) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE CONTATO "
					+ "SET ID = ?, NOME = ?"
					+ "WHERE ID = ?");
			
			st.setInt(1, cont.getId());
			st.setString(2, cont.getNome());
			st.setInt(3, cont.getId());
			
			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
		
		
	}

	@Override
	public void deleteById(Contato cont) {
		
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM CONTATO WHERE ID = ?");
			
			st.setInt(1, cont.getId());
			
			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public Contato findById(Integer id) {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT *"
					+ "FROM CONTATO "
					+ "WHERE CONTATO.ID = ?");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Contato cont = instantiateContato(rs);
				return cont;
			}
			return null;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
	
	private Contato instantiateContato(ResultSet rs) throws SQLException {
		Contato cont = new Contato();
		cont.setId(rs.getInt("ID"));
		cont.setNome(rs.getString("NOME"));
		
		return cont;
	}

	@Override
	public List<Contato> findAll() {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT * "
					+ "FROM CONTATO "
					+ "ORDER BY ID");
			
			rs = st.executeQuery();
			
			List<Contato> list = new ArrayList<>();
			
			while (rs.next()) {

				Contato cont = instantiateContato(rs);
				list.add(cont);
			}
			return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Contato> findByNome(String nome) {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT * "
					+ "FROM CONTATO "
					+ "WHERE NOME LIKE '%?%' "
					+ "ORDER BY NOME");
			
			st.setString(1, nome);
			
			rs = st.executeQuery();
			
			List<Contato> list = new ArrayList<>();
			
			while (rs.next()) {
				
				Contato cont = instantiateContato(rs);
				list.add(cont);
			}
			return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

}
