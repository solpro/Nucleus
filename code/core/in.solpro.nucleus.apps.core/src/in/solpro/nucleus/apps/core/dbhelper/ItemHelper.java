package in.solpro.nucleus.apps.core.dbhelper;

import in.solpro.nucleus.apps.common.Item;
import in.solpro.nucleus.apps.core.session.SessionUtil;

import java.util.List;

import javax.persistence.Query;

public class ItemHelper extends GenericHelper
{

    public ItemHelper()
    {
        super( Item.class );
    }

    public void addItem( Item item ) throws Exception
    {
        item.validateAndUpdate();
        save( item );
        System.out.println( "Item Saved" );
    }

    public Item getItem( Integer id ) throws Exception
    {
        Item item = (Item) find( id );
        item.validateAndUpdate();
        return item;
    }

    public Item getItem( String name ) throws Exception
    {
        Query query = em.createQuery( "SELECT OBJECT(pc) FROM Item pc WHERE pc.name = :name AND pc.company.id= :compid" );
        query.setParameter( "name", name );
        query.setParameter( "compid", SessionUtil.getCompany().getId() );

        List<?> rs = query.getResultList();
        if ( rs.size() > 0 )
        {
            Item item = (Item) rs.get( 0 );
            item.validateAndUpdate();
            return item;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public List<Item> getItems()
    {
        Query query = em.createQuery( "SELECT OBJECT(pc) FROM Item pc WHERE pc.company.id= :compid" );
        query.setParameter( "compid", SessionUtil.getCompany().getId() );
        return query.getResultList();
    }

    public void updateItem( Item item ) throws Exception
    {
        item.validateAndUpdate();
        update( item );
    }

}
