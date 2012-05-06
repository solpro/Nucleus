package in.solpro.nucleus.apps.core.dbhelper;

import in.solpro.nucleus.apps.common.BaseObject;

import javax.persistence.EntityManager;

public class GenericHelper
{

    Class<?> cls;

    protected EntityManager em;

    public GenericHelper( Class<?> cls )
    {
        this.cls = cls;
        em = EntityManagerUtil.getEntityManager();
    }

    public void save( Object category )
    {
        em.getTransaction().begin();
        em.persist( category );
        em.getTransaction().commit();
    }

    public Object find( Object id )
    {
        return em.find( cls, id );
    }

    public void update( Object object ) throws Exception
    {
        if ( object instanceof BaseObject )
        {
            ((BaseObject)object).validateAndUpdate();
        }
        em.getTransaction().begin();
        em.persist( object );
        em.getTransaction().commit();
    }

    public void delete( Object category )
    {
        em.getTransaction().begin();
        em.remove( em.merge( category ) );
        em.getTransaction().commit();
    }
}
