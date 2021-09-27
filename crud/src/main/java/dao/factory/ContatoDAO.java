package dao.factory;

import java.util.List;

import entities.Contato;

public interface ContatoDAO {
	
	void insert(Contato cont);
	void update(Contato cont);
	void deleteById(Contato cont);
	Contato findById(Integer id);
	List<Contato> findAll();
	List<Contato> findByNome(String nome);

}
