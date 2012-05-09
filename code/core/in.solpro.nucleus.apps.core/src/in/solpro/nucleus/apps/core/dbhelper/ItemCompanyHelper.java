package in.solpro.nucleus.apps.core.dbhelper;

import in.solpro.nucleus.apps.common.ItemCompany;
import in.solpro.nucleus.apps.core.session.SessionUtil;

import java.util.List;

import javax.persistence.Query;

public class ItemCompanyHelper extends GenericHelper
{

    public ItemCompanyHelper()
    {
        super( ItemCompany.class );
    }

    public void addItemCompany( ItemCompany itemcompany ) throws Exception
    {
        itemcompany.validateAndUpdate();
        save( itemcompany );
        System.out.println( "ItemCompany Saved" );
    }

    public ItemCompany getItemCompany( Integer id ) throws Exception
    {
        ItemCompany itemcompany = (ItemCompany) find( id );
        itemcompany.validateAndUpdate();
        return itemcompany;
    }

    public ItemCompany getItemCompany( String name ) throws Exception
    {
        Query query = em.createQuery( "SELECT OBJECT(pc) FROM ItemCompany pc WHERE pc.name = :name AND pc.company.id= :compid" );
        query.setParameter( "name", name );
        query.setParameter( "compid", SessionUtil.getCompany().getId());

        List<?> rs = query.getResultList();
        if ( rs.size() > 0 )
        {
            ItemCompany itemcompany=(ItemCompany) rs.get( 0 );
            itemcompany.validateAndUpdate();
            return itemcompany;
        }
        return null;
     }

    @SuppressWarnings("unchecked")
    public List<ItemCompany> getItemCompanies( )
    {
        Query query = em.createQuery( "SELECT OBJECT(pc) FROM ItemCompany pc WHERE pc.company.id= :compid" );
        query.setParameter( "compid", SessionUtil.getCompany().getId() );
        return query.getResultList();
    }

    public void updateItemCompany( ItemCompany itemcompany ) throws Exception
    {
        itemcompany.validateAndUpdate();
        update( itemcompany );
    }

}
