package test.dbhelper;

import in.solpro.nucleus.apps.common.City;
import in.solpro.nucleus.apps.core.dbhelper.CityHelper;

import java.util.List;

import junit.framework.TestCase;

/**
 * @author asheesh
 */
public class CityHelperTest extends TestCase
{

    /**
     **/
    public void testAddCity()
    {
       City p = new City();
       p.setName( "PIMPRI" );
       p.setObjDistrict(CommonTestData.Pune);
       CityHelper.addCity( p );
    }

    /**
     **/

    public void testAddCity_NoName()
    {
        City p = new City();
      
        try
        {
            CityHelper.addCity( p );
            fail();
        }
        catch ( Exception e )
        {

        }
    }

    /**
     **/
    public static void testgetCityById()
    {
    	CityHelper helper=new CityHelper();
    	City p = CityHelper.getCityById( helper.getCity(CommonTestData.PuneCity.getName()).getId());
        if ( p == null )
        {
            fail();
        }
        assertEquals( p.getId(), CommonTestData.PuneCity.getId() );
        assertEquals( p.getName(), CommonTestData.PuneCity.getName());
        assertEquals( p.getObjDistrict().getId(), CommonTestData.PuneCity.getObjDistrict().getId());
        assertEquals( p.getObjDistrict().getObjState().getId(), CommonTestData.PuneCity.getObjDistrict().getObjState().getId());
     }
    
    public void testGetCity()
    {
        CityHelper c = new CityHelper();
        City p = c.getCity( CommonTestData.PuneCity.getName() );
        if ( p == null )
        {
            fail();
        }
        assertEquals( p.getId(), CommonTestData.PuneCity.getId());
        assertEquals( p.getName(), CommonTestData.PuneCity.getName());
    }

    /**
     * */

    public void testGetCities()
    {
        List<City> p;

        try
        {
            CityHelper c = new CityHelper();
            p = c.getCities();
            if ( p == null )
            {
                fail();
            }
            for ( int i = 0; i < p.size(); i++ )
                System.out.println( "City name:" + p.get( i ).getName() + "\nCity ID:" + p.get( i ).getId() );
          
        }
        catch ( Exception e )
        {
            System.out.print( "error:" );
        }
    }

    /**
     * This test verifies updateCity
     */

    public void testUpdateCity()
    {

        CityHelper c = new CityHelper();
        City p = (City) c.getCity( CommonTestData.PuneCity.getName() );
        p.setName("PUNYANAGARI");
        p.setPincode("411027");
        c.save( p );
        City p2 = (City) c.getCityById(CommonTestData.PuneCity.getId());
        if ( p2 == null )
        {
            fail();
        }
        assertEquals( p2.getName(), "PUNYANAGARI" );
    }

}
