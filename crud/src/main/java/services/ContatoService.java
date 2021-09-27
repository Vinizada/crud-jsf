package services;

import java.io.Serializable;
import java.util.List;

import dao.factory.ContatoDAO;
import dao.factory.DAOFactory;
import entities.Contato;


public class ContatoService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private ContatoDAO dao = DAOFactory.createContatoDAO();
	public List<Contato> listaContatos;

	public ContatoService() {
		
	}
	
	public List<Contato> findAll() {
		this.listaContatos = dao.findAll();
		return this.listaContatos;
	}
	
	public void saveOrUpdate(Contato cont) {
		if(cont.getId() == null) {
			dao.insert(cont);
		}
		else {
			dao.update(cont);
		}
	}
	
	public void remove(Contato cont) {
		dao.deleteById(cont);
	}

}
