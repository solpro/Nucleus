package in.solpro.nucleus.apps.core.dbhelper;

import in.solpro.nucleus.apps.common.Ledger;
import in.solpro.nucleus.apps.core.session.SessionUtil;

import java.util.List;

import javax.persistence.Query;

public class LedgerHelper extends GenericHelper
{

    public LedgerHelper()
    {
        super( Ledger.class );
    }

    public void addLedger( Ledger ledger ) throws Exception
    {
        ledger.validateAndUpdate();
        save( ledger );
        System.out.println( "Ledger Saved" );
    }

    public Ledger getLedger( Integer id ) throws Exception
    {
        Ledger ledger = (Ledger) find( id );
        ledger.validateAndUpdate();
        return ledger;
    }

    public Ledger getLedger( String name ) throws Exception
    {
        Query query = em.createQuery( "SELECT OBJECT(pc) FROM Ledger pc WHERE pc.name = :name AND pc.company.id= :compid" );
        query.setParameter( "name", name );
        query.setParameter( "compid", SessionUtil.getCompany().getId() );

        List<?> rs = query.getResultList();
        if ( rs.size() > 0 )
        {
            Ledger ledger = (Ledger) rs.get( 0 );
            ledger.validateAndUpdate();
            return ledger;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public List<Ledger> getLedgers()
    {
        Query query = em.createQuery( "SELECT OBJECT(pc) FROM Ledger pc WHERE pc.company.id= :compid" );
        query.setParameter( "compid", SessionUtil.getCompany().getId() );
        return query.getResultList();
    }

    public void updateLedger( Ledger ledger ) throws Exception
    {
        ledger.validateAndUpdate();
        update( ledger );
    }

}
