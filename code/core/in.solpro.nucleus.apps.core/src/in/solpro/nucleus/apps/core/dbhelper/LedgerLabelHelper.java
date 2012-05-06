package in.solpro.nucleus.apps.core.dbhelper;

import in.solpro.nucleus.apps.common.LedgerLabel;
import in.solpro.nucleus.apps.core.session.SessionUtil;

import java.util.List;

import javax.persistence.Query;

public class LedgerLabelHelper extends GenericHelper
{

    public LedgerLabelHelper()
    {
        super( LedgerLabel.class );
    }

    public void addLedgerLabel( LedgerLabel ledgerlabel ) throws Exception
    {
        ledgerlabel.validateAndUpdate();
        save( ledgerlabel );
        System.out.println( "UserDefinedLabel Saved" );
    }

    public LedgerLabel getLedgerLabel( Integer id ) throws Exception
    {
        LedgerLabel label = (LedgerLabel) find( id );
        label.validateAndUpdate();
        return label;
    }

    public LedgerLabel getLedgerLabel( String name ) throws Exception
    {
        Query query = em.createQuery( "SELECT OBJECT(pc) FROM LedgerLabel pc WHERE pc.name = :name AND pc.company.id= :compid" );
        query.setParameter( "name", name );
        query.setParameter( "compid", SessionUtil.getCompany().getId() );

        List<?> rs = query.getResultList();
        if ( rs.size() > 0 )
        {
            LedgerLabel ledgerlabel = (LedgerLabel) rs.get( 0 );
            ledgerlabel.validateAndUpdate();
            return ledgerlabel;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public List<LedgerLabel> getLedgerLabels()
    {
        Query query = em.createQuery( "SELECT OBJECT(pc) FROM LedgerLabel pc WHERE pc.company.id= :compid" );
        query.setParameter( "compid", SessionUtil.getCompany().getId() );
        return query.getResultList();
    }

    public void updateLedgerLabel( LedgerLabel ledgerlabel ) throws Exception
    {
        ledgerlabel.validateAndUpdate();
        update( ledgerlabel );
    }

}
