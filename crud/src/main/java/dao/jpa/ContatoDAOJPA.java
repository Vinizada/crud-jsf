package dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entities.Contato;
import util.HibernateUtil;

public class ContatoDAOJPA {

	private EntityManager entityManager;

	public ContatoDAOJPA() {
		this.entityManager = HibernateUtil.getEntityManager();
	}

	public void salvar(Contato entidade) {

		EntityTransaction transaction = entityManager.getTransaction();
		
		transaction.begin();
		
		entityManager.persist(entidade);
		
		transaction.commit();

	}

	public Contato updateMerge(Contato entidade) {
		EntityTransaction transaction = entityManager.getTransaction();
		
		transaction.begin();
		
		Contato entidadeSalva = (Contato) entityManager.merge(entidade);
		
		transaction.commit();
		
		return entidadeSalva;
	}

	public void deletarPorId(Contato entidade) {

		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		entityManager
				.createNativeQuery(
						"delete from " + entidade.getClass().getSimpleName().toLowerCase() + " where id =" + entidade.getId())
				.executeUpdate(); // faz delete

		transaction.commit();

	}

	public Contato pesquisar(Contato entidade) {
		Integer id = HibernateUtil.getPrimaryKey((entities.Contato) entidade);
		Contato entity = (Contato) entityManager.find(entidade.getClass(), id);

		return entity;
	}

	@SuppressWarnings("unchecked")
	public List<Contato> listar(Class<Contato> entidade) {

		try {

			List<Contato> listaRetorno = entityManager.createQuery("from " + entidade.getName()).getResultList();

			return listaRetorno;

		} catch (Exception e) {

			e.printStackTrace();
		}

		return null;

	}

}
