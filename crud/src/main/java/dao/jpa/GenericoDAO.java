package dao.jpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;

public class GenericoDAO<K, T>{
	
	@PersistenceContext
	private EntityManager entityManager;

	public GenericoDAO() {
	}

	public void inserir(T entidade) throws Exception {

		try {

			entityManager.persist(entidade);

		} catch (Exception e) {

			throw new Exception("Falha ao tentar persistir a entidade "
					+ entidade.getClass() + " causa: " + e.getCause());
		}

	}

	public void alterar(T entidade) throws Exception {

		try {

			entityManager.merge(entidade);

		} catch (Exception e) {

			throw new Exception("Falha ao tentar alterar a entidade "
					+ entidade.getClass() + " causa: " + e.getCause());

		}

	}

	public void deletar(T entidade) throws Exception {

		try {
			
			T tempEntidade = entityManager.merge(entidade);
			entityManager.remove(tempEntidade);

		} catch (Exception e) {

			throw new Exception("Falha ao tentar deletar a entidade "
					+ entidade.getClass() + " causa: " + e.getMessage());

		}
	}

	@SuppressWarnings("unchecked")
	public T buscar(K chave) throws Exception {

		try {

			T objeto = (T) entityManager.find(getTypeClass(), chave);

			return objeto;

		} catch (Exception e) {

			throw new Exception(
					"Falha ao tentar recuperar a entidade pela chave " + chave
							+ this.getClass() + " causa: " + e.getCause());
		}

	}

	@SuppressWarnings("unchecked")
	public List<T> listarTodos() throws Exception {

		try {

			List<T> listaObjetos = (List<T>) entityManager.createQuery(
					("FROM " + getTypeClass().getName())).getResultList();

			return listaObjetos;

		} catch (Exception e) {

			//throw new Exception("Falha ao tentar listar todos "
					//+ this.getClass() + " causa: " + e.getCause());
			e.printStackTrace();

		}
		return null;

	}

	private Class<?> getTypeClass() {
		Class<?> clazz = (Class<?>) ((ParameterizedType) this.getClass()
				.getGenericSuperclass()).getActualTypeArguments()[1];
		return clazz;
	}

	public EntityManager getEntityManager() {

		return this.entityManager;

	}

}