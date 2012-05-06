package in.solpro.nucleus.apps.core.dbhelper;

import in.solpro.nucleus.apps.common.Country;
import in.solpro.nucleus.apps.core.session.SessionUtil;

import java.util.List;

import javax.persistence.Query;

public class CountryHelper extends GenericHelper
{

    public CountryHelper()
    {
        super( Country.class );
    }

    public void addCountry( Country country ) throws Exception
    {
        country.validateAndUpdate();
        save( country );
        System.out.println("COUNTRY SAVED");
    }

    public Country getCountry( Integer id ) throws Exception
    {
        Country country = (Country) find( id );
        country.validateAndUpdate();
        return country;
    }

    public Country getCountry( String name ) throws Exception
    {
        Query query = em.createQuery( "SELECT OBJECT(pc) FROM Country pc WHERE pc.name = :name AND pc.company.id= :compid" );
        query.setParameter( "name", name );
        query.setParameter( "compid", SessionUtil.getCompany().getId() );
        List<?> rs = query.getResultList();
        if ( rs.size() > 0 )
        {
            Country country = (Country) rs.get( 0 );
            country.validateAndUpdate();
            return country;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public List<Country> getCountries()
    {
        Query query = em.createQuery( "SELECT OBJECT(pc) FROM Country pc WHERE pc.company.id= :compid" );
        query.setParameter( "compid", SessionUtil.getCompany().getId() );
        return (List<Country>) query.getResultList();
    }

    public void updateCountry( Country country ) throws Exception
    {
        country.validateAndUpdate();
        update( country );
    }

}