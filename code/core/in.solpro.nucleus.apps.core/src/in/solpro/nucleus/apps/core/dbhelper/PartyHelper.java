package in.solpro.nucleus.apps.core.dbhelper;

import in.solpro.nucleus.apps.common.Party;
import in.solpro.nucleus.apps.core.session.SessionUtil;

import java.util.List;

import javax.persistence.Query;

public class PartyHelper extends GenericHelper
{

    public PartyHelper()
    {
        super( Party.class );
    }

    public void addParty( Party party ) throws Exception
    {
        party.validateAndUpdate();
        save( party );
        System.out.println( "Party Saved" );
    }

    public Party getParty( Integer id ) throws Exception
    {
        Party party = (Party) find( id );
        party.validateAndUpdate();
        return party;
    }

    public Party getParty( String name ) throws Exception
    {
        Query query = em.createQuery( "SELECT OBJECT(pc) FROM Party pc WHERE pc.name = :name AND pc.company.id= :compid" );
        query.setParameter( "name", name );
        query.setParameter( "compid", SessionUtil.getCompany().getId() );

        List<?> rs = query.getResultList();
        if ( rs.size() > 0 )
        {
            Party party = (Party) rs.get( 0 );
            party.validateAndUpdate();
            return party;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public List<Party> getParties()
    {
        Query query = em.createQuery( "SELECT OBJECT(pc) FROM Party pc WHERE pc.company.id= :compid" );
        query.setParameter( "compid", SessionUtil.getCompany().getId() );
        return query.getResultList();
    }

    public void updateParty( Party party ) throws Exception
    {
        party.validateAndUpdate();
        update( party );
    }

}