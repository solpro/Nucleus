package in.solpro.nucleus.apps.core.dbhelper;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtil
{
	private static EntityManagerFactory factory;

	public static EntityManager getEntityManager()
	{
		if (factory == null)
		{
			factory = Persistence.createEntityManagerFactory("nucleus");
		}
		return factory.createEntityManager();
	}
}
