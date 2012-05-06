package in.solpro.nucleus.apps.core.dbhelper;

import in.solpro.nucleus.apps.common.Tax;
import in.solpro.nucleus.apps.core.session.SessionUtil;

import java.util.List;

import javax.persistence.Query;

public class TaxHelper extends GenericHelper
{

    public TaxHelper()
    {
        super( Tax.class );
    }

    public void addTax( Tax tax ) throws Exception
    {
        tax.validateAndUpdate();
        save( tax );
        System.out.println( "Tax Saved" );
    }

    public Tax getTax( Integer id ) throws Exception
    {
        Tax tax = (Tax) find( id );
        tax.validateAndUpdate();
        return tax;
    }

    public Tax getTax( String name ) throws Exception
    {
        Query query = em.createQuery( "SELECT OBJECT(pc) FROM Tax pc WHERE pc.name = :name AND pc.company.id= :compid" );
        query.setParameter( "name", name );
        query.setParameter( "compid", SessionUtil.getCompany().getId() );

        List<?> rs = query.getResultList();
        if ( rs.size() > 0 )
        {
            Tax tax = (Tax) rs.get( 0 );
            tax.validateAndUpdate();
            return tax;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public List<Tax> getTaxes()
    {
        Query query = em.createQuery( "SELECT OBJECT(pc) FROM Tax pc WHERE pc.company.id= :compid" );
        query.setParameter( "compid", SessionUtil.getCompany().getId() );
        return query.getResultList();
    }

    public void updateTax( Tax tax ) throws Exception
    {
        tax.validateAndUpdate();
        update( tax );
    }

}