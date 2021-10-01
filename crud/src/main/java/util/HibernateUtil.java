package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {

	public static EntityManagerFactory factory = null;

	static {

		init();
	}

	private static void init() {

		try {

			if (factory == null) {
				factory = Persistence.createEntityManagerFactory("hibernate");
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}
	
	public static <T> Integer getPrimaryKey(T entidade) { //Retorna a primary key
		return (Integer) factory.getPersistenceUnitUtil().getIdentifier(entidade);
	}

}
