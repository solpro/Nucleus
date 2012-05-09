package in.solpro.nucleus.apps.core.dbhelper;

import in.solpro.nucleus.apps.common.ItemGroup;
import in.solpro.nucleus.apps.core.session.SessionUtil;

import java.util.List;

import javax.persistence.Query;

public class ItemGroupHelper extends GenericHelper
{

    public ItemGroupHelper()
    {
        super( ItemGroup.class );
    }

    public void addItemGroup( ItemGroup itemgroup ) throws Exception
    {
        itemgroup.validateAndUpdate();
        save( itemgroup );
        System.out.println( "ItemGroup Saved" );
    }

    public ItemGroup getItemGroup( Integer id ) throws Exception
    {
        ItemGroup itemgroup = (ItemGroup) find( id );
        itemgroup.validateAndUpdate();
        return itemgroup;
    }

    public ItemGroup getItemGroup( String name ) throws Exception
    {
        Query query = em.createQuery( "SELECT OBJECT(pc) FROM ItemGroup pc WHERE pc.name = :name AND pc.company.id= :compid" );
        query.setParameter( "name", name );
        query.setParameter( "compid", SessionUtil.getCompany().getId() );

        List<?> rs = query.getResultList();
        if ( rs.size() > 0 )
        {
            ItemGroup itemgroup = (ItemGroup) rs.get( 0 );
            itemgroup.validateAndUpdate();
            return itemgroup;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public List<ItemGroup> getItemGroups()
    {
        Query query = em.createQuery( "SELECT OBJECT(pc) FROM ItemGroup pc WHERE pc.company.id= :compid" );
        query.setParameter( "compid", SessionUtil.getCompany().getId() );
        return query.getResultList();
    }

    public void updateItemGroup( ItemGroup itemgroup ) throws Exception
    {
        itemgroup.validateAndUpdate();
        update( itemgroup );
    }

}
