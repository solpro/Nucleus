package in.solpro.nucleus.apps.core.dbhelper;

import in.solpro.nucleus.apps.common.Discount;
import in.solpro.nucleus.apps.core.session.SessionUtil;

import java.util.List;

import javax.persistence.Query;

public class DiscountHelper extends GenericHelper
{

    public DiscountHelper()
    {
        super( Discount.class );
    }

    public void addDiscount( Discount discount ) throws Exception
    {
        discount.validateAndUpdate();
        save( discount );
        System.out.println( "Discount Saved" );
    }

    public Discount getDiscount( Integer id )
    {
        Discount Discount = (Discount) find( id );
        return Discount;
    }

    
    @SuppressWarnings("unchecked")
    public List<Discount> getDiscounts( )
    {
        Query query = em.createQuery( "SELECT OBJECT(pc) FROM Discount pc WHERE pc.company.id= :compid" );
        query.setParameter( "compid", SessionUtil.getCompany().getId() );
        return query.getResultList();
    }

    public void updateDiscount( Discount discount ) throws Exception
    {
        discount.validateAndUpdate();
        update( discount );
    }

}