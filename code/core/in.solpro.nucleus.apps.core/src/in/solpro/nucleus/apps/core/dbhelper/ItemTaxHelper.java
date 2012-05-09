package in.solpro.nucleus.apps.core.dbhelper;

import in.solpro.nucleus.apps.common.ItemTax;
import in.solpro.nucleus.apps.core.session.SessionUtil;

import java.util.List;

import javax.persistence.Query;

public class ItemTaxHelper extends GenericHelper
{

    public ItemTaxHelper()
    {
        super( ItemTax.class );
    }

    public void addItemTax( ItemTax itemtax ) throws Exception
    {
        itemtax.validateAndUpdate();
        save( itemtax );
        System.out.println( "ItemTax Saved" );
    }

    public ItemTax getItemTax( Integer id ) throws Exception
    {
        ItemTax itemtax = (ItemTax) find( id );
        itemtax.validateAndUpdate();
        return itemtax;
    }

    public ItemTax getItemTax( String name ) throws Exception
    {
        Query query = em.createQuery( "SELECT OBJECT(pc) FROM ItemTax pc WHERE pc.name = :name AND pc.company.id= :compid" );
        query.setParameter( "name", name );
        query.setParameter( "compid", SessionUtil.getCompany().getId() );

        List<?> rs = query.getResultList();
        if ( rs.size() > 0 )
        {
            ItemTax itemtax = (ItemTax) rs.get( 0 );
            itemtax.validateAndUpdate();
            return itemtax;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public List<ItemTax> getItemTaxes()
    {
        Query query = em.createQuery( "SELECT OBJECT(pc) FROM ItemTax pc WHERE pc.company.id= :compid" );
        query.setParameter( "compid", SessionUtil.getCompany().getId() );
        return query.getResultList();
    }

    public void updateItemTax( ItemTax itemtax ) throws Exception
    {
        itemtax.validateAndUpdate();
        update( itemtax );
    }

}
