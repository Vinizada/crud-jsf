package dao.factory;

import java.util.List;

public interface ContatoJPA<Contato> {
	
	void salvar(Contato entidade);
	Contato updateMerge(Contato entidade);
	void deletarPorId(Contato entidade);
	Contato pesquisar(Contato entidade);
	List<Contato>  listar();

}
