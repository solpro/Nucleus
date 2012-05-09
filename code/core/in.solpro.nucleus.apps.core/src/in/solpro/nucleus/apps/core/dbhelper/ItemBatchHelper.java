package in.solpro.nucleus.apps.core.dbhelper;

import in.solpro.nucleus.apps.common.ItemBatch;
import in.solpro.nucleus.apps.core.session.SessionUtil;

import java.util.List;

import javax.persistence.Query;

public class ItemBatchHelper extends GenericHelper
{

    public ItemBatchHelper()
    {
        super( ItemBatch.class );
    }

    public void addItemBatch( ItemBatch itembatch ) throws Exception
    {
        itembatch.validateAndUpdate();
        save( itembatch );
        System.out.println( "ItemBatch Saved" );
    }

    public ItemBatch getItemBatch( Integer id ) throws Exception
    {
        ItemBatch itemBatch = (ItemBatch) find( id );
        itemBatch.validateAndUpdate();
        return itemBatch;
    }

    public ItemBatch getItemBatch( String name) throws Exception
    {
        Query query = em.createQuery( "SELECT OBJECT(pc) FROM ItemBatch pc WHERE pc.name = :name AND pc.company.id= :compid" );
        query.setParameter( "name", name );
        query.setParameter( "compid",SessionUtil.getCompany().getId());

        List<?> rs = query.getResultList();
        if ( rs.size() > 0 )
        {
            ItemBatch itembatch=(ItemBatch) rs.get( 0 );
            itembatch.validateAndUpdate();
            return itembatch;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public List<ItemBatch> getItemBatches( )
    {
        Query query = em.createQuery( "SELECT OBJECT(pc) FROM ItemBatch pc WHERE pc.company.id= :compid" );
        query.setParameter( "compid", SessionUtil.getCompany().getId() );
        return query.getResultList();
    }

    public void updateItemBatch( ItemBatch itembatch ) throws Exception
    {
        itembatch.validateAndUpdate();
        update( itembatch );
    }

}
