package services;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;

import dao.factory.JPAFactory;
import dao.jpa.ContatoDAOJPA;
import entities.Contato;

@Stateless
public class ContatoService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private ContatoDAOJPA dao = JPAFactory.createContatoJPA();
	public List<Contato> listaContatos;

	public ContatoService() {
		
	}
	
	public List<Contato> findAll() throws Exception {
		this.listaContatos = dao.listar(Contato.class);
		return this.listaContatos;
	}
	
	public void saveOrUpdate(Contato cont) throws Exception {
		
		if(cont.getId() == null) {
			dao.updateMerge(cont);

		}
		else {
			dao.salvar(cont);
		}
	}
	
	public void remove(Contato cont) throws Exception {
		dao.deletarPorId(cont);
	}

}
