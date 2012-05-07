package in.solpro.nucleus.apps.core.dbhelper;

import in.solpro.nucleus.apps.common.PartyAddress;
import in.solpro.nucleus.apps.core.session.SessionUtil;

import java.util.List;

import javax.persistence.Query;

public class PartyAddressHelper extends GenericHelper
{

    public PartyAddressHelper()
    {
        super( PartyAddress.class );
    }

    public void addPartyAddress( PartyAddress partyaddress ) throws Exception
    {
        partyaddress.validateAndUpdate();
        save( partyaddress );
        System.out.println( "PartyAddress Saved" );
    }

    public PartyAddress getPartyAddress( Integer id ) throws Exception
    {
        PartyAddress partyaddress = (PartyAddress) find( id );
        partyaddress.validateAndUpdate();
        return partyaddress;
    }

    public PartyAddress getPartyAddress( String name ) throws Exception
    {
        Query query = em.createQuery( "SELECT OBJECT(pc) FROM PartyAddress pc WHERE pc.name = :name AND pc.company.id= :compid" );
        query.setParameter( "name", name );
        query.setParameter( "compid", SessionUtil.getCompany().getId() );

        List<?> rs = query.getResultList();
        if ( rs.size() > 0 )
        {
            PartyAddress partyaddress = (PartyAddress) rs.get( 0 );
            partyaddress.validateAndUpdate();
            return partyaddress;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public List<PartyAddress> getPartyAddresses()
    {
        Query query = em.createQuery( "SELECT OBJECT(pc) FROM PartyAddress pc WHERE pc.company.id= :compid" );
        query.setParameter( "compid", SessionUtil.getCompany().getId() );
        return query.getResultList();
    }

    public void updatePartyAddress( PartyAddress partyaddress ) throws Exception
    {
        partyaddress.validateAndUpdate();
        update( partyaddress );
    }

}