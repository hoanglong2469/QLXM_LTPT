package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {
	private static HibernateUtil instance = null;

	private EntityManagerFactory entityManagerFactory;
	public HibernateUtil() {
		entityManagerFactory = Persistence.createEntityManagerFactory("QLXM_PhanTan");
	}

	public synchronized static HibernateUtil getInstance() {
		if (instance == null)
			instance = new HibernateUtil();
		return instance;

	}
	public EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}
}
