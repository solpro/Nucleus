package in.solpro.nucleus.apps.core.dbhelper;

import in.solpro.nucleus.apps.common.City;
import in.solpro.nucleus.apps.core.session.SessionUtil;

import java.util.List;

import javax.persistence.Query;

public class CityHelper extends GenericHelper
{

    public CityHelper()
    {
        super( City.class );
    }

    public void addCity( City city ) throws Exception
    {
        city.validateAndUpdate();
        save( city );
        System.out.println( "City Saved" );
    }

    public City getCity( Integer id ) throws Exception
    {
        City city = (City) find( id );
        city.validateAndUpdate();
        return city;
    }

    public City getCity( String name ) throws Exception
    {
        Query query = em.createQuery( "SELECT OBJECT(pc) FROM City pc WHERE pc.name = :name AND pc.company.id= :compid" );
        query.setParameter( "name", name );
        query.setParameter( "compid", SessionUtil.getCompany().getId() );

        List<?> rs = query.getResultList();
        if ( rs.size() > 0 )
        {
            City city = (City) rs.get( 0 );
            city.validateAndUpdate();
            return city;
        }
        return null;

    }

    @SuppressWarnings("unchecked")
    public List<City> getCities()
    {
        Query query = em.createQuery( "SELECT OBJECT(pc) FROM City pc WHERE pc.company.id= :compid" );
        query.setParameter( "compid", SessionUtil.getCompany().getId() );
        return query.getResultList();
    }

    public void updateCity( City city ) throws Exception
    {
        city.validateAndUpdate();
        update( city );
    }

}
