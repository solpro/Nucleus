package in.solpro.nucleus.apps.core.dbhelper;

import in.solpro.nucleus.apps.common.AddressType;
import in.solpro.nucleus.apps.core.session.SessionUtil;

import java.util.List;

import javax.persistence.Query;

public class AddressTypeHelper extends GenericHelper
{

    public AddressTypeHelper()
    {
        super( AddressType.class );
    }

    public void addAddressType( AddressType addresstype ) throws Exception
    {
        addresstype.validateAndUpdate();
        save( addresstype );
        System.out.println( "AddressType Saved" );
    }

    public AddressType getAddressType( Integer id ) throws Exception
    {
        AddressType addresstype = (AddressType) find( id );
        addresstype.validateAndUpdate();
        return addresstype;
    }

    public AddressType getAddressType( String name ) throws Exception
    {
        Query query = em.createQuery( "SELECT OBJECT(pc) FROM AddressType pc WHERE pc.name = :name AND pc.company.id= :compid" );
        query.setParameter( "name", name );
        // query.setParameter("compid",compid);
        query.setParameter( "compid", SessionUtil.getCompany().getId() );

        List<?> rs = query.getResultList();
        if ( rs.size() > 0 )
        {
            AddressType addresstype = (AddressType) rs.get( 0 );
            addresstype.validateAndUpdate();
            return addresstype;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public List<AddressType> getAddressTypes()
    {
        Query query = em.createQuery( "SELECT OBJECT(pc) FROM AddressType pc WHERE pc.company.id= :compid" );
        query.setParameter( "compid", SessionUtil.getCompany().getId() );
        return query.getResultList();
    }

    public void updateAddressType( AddressType addresstype ) throws Exception
    {
        addresstype.validateAndUpdate();
        update( addresstype );
    }
}
