package in.solpro.nucleus.apps.core.dbhelper;

import in.solpro.nucleus.apps.common.District;
import in.solpro.nucleus.apps.core.session.SessionUtil;

import java.util.List;

import javax.persistence.Query;

public class DistrictHelper extends GenericHelper
{

    public DistrictHelper()
    {
        super( District.class );
    }

    public void addDistrict( District district ) throws Exception
    {
        district.validateAndUpdate();
        save( district );
        System.out.println( "District Saved" );
    }

    public District getDistrict( Integer id ) throws Exception
    {
        District district = (District) find( id );
        district.validateAndUpdate();
        return district;
    }

    public District getDistrict( String name) throws Exception
    {
        Query query = em.createQuery( "SELECT OBJECT(pc) FROM District pc WHERE pc.name = :name AND pc.company.id= :compid" );
        query.setParameter( "name", name );
        query.setParameter( "compid",SessionUtil.getCompany().getId() );
        List<?> rs = query.getResultList();
        if ( rs.size() > 0 )
        {
            District district=(District) rs.get( 0 );
            district.validateAndUpdate();
            return district;
        }
        return null;
     }

    @SuppressWarnings("unchecked")
    public List<District> getDistricts( )
    {
        Query query = em.createQuery( "SELECT OBJECT(pc) FROM District pc WHERE pc.company.id= :compid" );
        query.setParameter( "compid", SessionUtil.getCompany().getId() );
        return query.getResultList();
    }

    public void updateDistrict( District district ) throws Exception
    {
        district.validateAndUpdate();
        update( district );
    }

}
