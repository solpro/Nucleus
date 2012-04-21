package test.dbhelper;
import in.solpro.nucleus.apps.common.Country;
import in.solpro.nucleus.apps.core.dbhelper.CountryHelper;

import java.util.List;

import junit.framework.TestCase;

/**
 * @author asheesh
 */
public class CountryHelperTest extends TestCase
{

    /**
     * Test method for {@link in.solpro.nucleus.apps.core.dbhelper.CompanyHelper#addCompany(in.solpro.nucleus.apps.common.Company)}.
     */
    public void testAddCountry()
    {
        Country p = new Country();
        p.setName( "Canada" );
        CountryHelper.addCountry( p );
    }

    /**
     * Test method for {@link in.solpro.nucleus.apps.core.dbhelper.CountryHelper#addCountry(in.solpro.nucleus.apps.common.Country)}.
     */

    public void testAddCountry_NoName()
    {
        Country p = new Country();
      
        try
        {
            CountryHelper.addCountry( p );
            fail();
        }
        catch ( Exception e )
        {

        }
    }

    /**
     * Test method for {@link in.solpro.nucleus.apps.core.dbhelper.CountryHelper#addCountry(in.solpro.nucleus.apps.common.Country)}.
     */
    public void testgetCountryById()
    {
    	CountryHelper helper=new CountryHelper();
    	Country p = CountryHelper.getCountryById( helper.getCountry(CommonTestData.India.getName()).getId());
        if ( p == null )
        {
            fail();
        }
        assertEquals( p.getId(), CommonTestData.India.getId() );
        assertEquals( p.getName(), CommonTestData.India.getName());
   	
    }
    
    public void testGetCountry()
    {
        CountryHelper c = new CountryHelper();
        Country p = c.getCountry( CommonTestData.India.getName() );
        if ( p == null )
        {
            fail();
        }
        assertEquals( p.getId(), CommonTestData.India.getId());
        assertEquals( p.getName(), CommonTestData.India.getName());
    }

    /**
     * Test method for {@link in.solpro.nucleus.apps.core.dbhelper.CountryHelper#addCountry(in.solpro.nucleus.apps.common.Country)}.
     */

    public void testGetCountries()
    {
        List<Country> p;

        try
        {
            CountryHelper c = new CountryHelper();
            p = c.getCountries();
            if ( p == null )
            {
                fail();
            }
            for ( int i = 0; i < p.size(); i++ )
                System.out.println( "Country name:" + p.get( i ).getName() + "Country ID:" + p.get( i ).getId() );
          
        }
        catch ( Exception e )
        {
            System.out.print( "error:" );
        }
    }

    /**
     * This test verifies updateCountry
     */

    public void testUpdateCountry()
    {

        CountryHelper c = new CountryHelper();
        Country p = (Country) c.getCountry( CommonTestData.India.getName() );
        p.setName("HINDUSTAN");
        c.save( p );
        Country p2 = (Country) c.getCountryById(CommonTestData.India.getId());
        if ( p2 == null )
        {
            fail();
        }
        assertEquals( p2.getName(), "HINDUSTAN" );
    }

}