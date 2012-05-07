package in.solpro.nucleus.apps.core.dbhelper;

import in.solpro.nucleus.apps.common.LedgerGroup;
import in.solpro.nucleus.apps.core.session.SessionUtil;

import java.util.List;

import javax.persistence.Query;

public class LedgerGroupHelper extends GenericHelper
{

    public LedgerGroupHelper()
    {
        super( LedgerGroup.class );
    }

    public void addLedgerGroup( LedgerGroup ledgergroup ) throws Exception
    {
        ledgergroup.validateAndUpdate();
        save( ledgergroup );
        System.out.println( "LedgerGroup Saved" );
    }

    public LedgerGroup getLedgerGroup( Integer id ) throws Exception
    {
        LedgerGroup ledgergroup = (LedgerGroup) find( id );
        ledgergroup.validateAndUpdate();
        return ledgergroup;
    }

    public LedgerGroup getLedgerGroup( String name ) throws Exception
    {
        Query query = em.createQuery( "SELECT OBJECT(pc) FROM LedgerGroup pc WHERE pc.name = :name AND pc.company.id= :compid" );
        query.setParameter( "name", name );
        query.setParameter( "compid", SessionUtil.getCompany().getId() );

        List<?> rs = query.getResultList();
        if ( rs.size() > 0 )
        {
            LedgerGroup ledgergroup = (LedgerGroup) rs.get( 0 );
            ledgergroup.validateAndUpdate();
            return ledgergroup;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public List<LedgerGroup> getLedgerGroups()
    {
        Query query = em.createQuery( "SELECT OBJECT(pc) FROM LedgerGroup pc WHERE pc.company.id= :compid" );
        query.setParameter( "compid", SessionUtil.getCompany().getId() );
        return query.getResultList();
    }

    public void updateLedgerGroup( LedgerGroup ledgergroup ) throws Exception
    {
        ledgergroup.validateAndUpdate();
        update( ledgergroup );
    }
    
    
    public void deleteLedgerGroup(LedgerGroup ledgergroup)
    {
        Query query = em.createQuery( "DELETE OBJECT(pc) FROM Ledger pc WHERE pc.ledger_id= :compid" );
        query.setParameter( "compid",ledgergroup.getId() );
        query.executeUpdate();
        delete(ledgergroup);
    }

}
