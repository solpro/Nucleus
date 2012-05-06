package in.solpro.nucleus.apps.core.dbhelper;

import in.solpro.nucleus.apps.common.Item;
import in.solpro.nucleus.apps.common.ItemDiscount;
import in.solpro.nucleus.apps.core.session.SessionUtil;

import java.sql.Date;
import java.util.List;

import javax.persistence.Query;

public class ItemDiscountHelper extends GenericHelper
{
    private void validate( ItemDiscount itemdiscount ) throws Exception
    {
        int i = 0;
        Date xfrom, xto, yfrom, yto;

        ItemDiscount itemdiscount1;
        List<ItemDiscount> list = (List<ItemDiscount>) getItemDiscounts( itemdiscount.getItem() );
        for ( i = 0; i < list.size(); i++ )
        {
            itemdiscount1 = list.get( i );
            xfrom = itemdiscount.getDatefrom();
            xto = itemdiscount.getDateto();
            yfrom = itemdiscount1.getDatefrom();
            yto = itemdiscount1.getDateto();
            if ( ( xfrom.before( yfrom ) && xto.before( yfrom ) ) || ( xfrom.after( yto ) && xto.after( yto ) ) )
            {
                continue;
            }
            else
            {
                System.out.println( "DISCOUNT DATES OVERLAP !!" );
                throw new Exception( "DISCOUNT DATES OVERLAP !!" );
            }
        }
    }

    public ItemDiscountHelper()
    {
        super( ItemDiscount.class );
    }

    public void addItemDiscount( ItemDiscount itemdiscount ) throws Exception
    {
        itemdiscount.validateAndUpdate();

        if ( itemdiscount.getDatefrom().before( itemdiscount.getDateto() ) )
        {
            validate( itemdiscount );
            save( itemdiscount );
        }
        else
        {
            System.out.println( "DATEFROM SHOULD BE LESS TAHN DATETO !!" );
            throw new Exception( "DATEFROM SHOULD BE LESS TAHN DATETO !!" );
        }
        System.out.println( "ItemDiscount Saved" );
    }

    public ItemDiscount getItemDiscount( Integer id ) throws Exception
    {
        ItemDiscount itemdiscount = (ItemDiscount) find( id );
        itemdiscount.validateAndUpdate();
        return itemdiscount;
    }

    @SuppressWarnings("unchecked")
    public List<ItemDiscount> getAllItemDiscounts()
    {
        Query query = em.createQuery( "SELECT OBJECT(pc) FROM ItemDiscount pc WHERE pc.company.id= :compid" );
        query.setParameter( "compid", SessionUtil.getCompany().getId() );
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<ItemDiscount> getItemDiscounts(Item item)
    {
        Query query = em.createQuery( "SELECT OBJECT(pc) FROM ItemDiscount pc WHERE pc.item.id= :itemid AND pc.company.id= :compid" );
        query.setParameter( "compid", SessionUtil.getCompany().getId() );
        query.setParameter( "itemid", item.getId() );
        return query.getResultList();
    }

    public void updateItemDiscount( ItemDiscount itemdiscount ) throws Exception
    {
        itemdiscount.validateAndUpdate();
        update( itemdiscount );
    }

}