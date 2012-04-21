package in.solpro.nucleus.apps.core.dbhelper;

import javax.persistence.EntityManager;

public class GenericHelper
{

	Class<?> cls;
	protected EntityManager em;

	public GenericHelper(Class<?> cls)
	{
		this.cls = cls;
		em = EntityManagerUtil.getEntityManager();
	}

	public void save(Object category)
	{
		em.getTransaction().begin();
		em.persist(category);
		em.getTransaction().commit();
	}

	public Object find(Object id)
	{
		return em.find(cls, id);
	}

	public void update(Object category)
	{
		em.getTransaction().begin();
		em.persist(category);
		em.getTransaction().commit();
	}
}
