package test.dbhelper;

import in.solpro.nucleus.apps.common.Country;
import in.solpro.nucleus.apps.core.dbhelper.CountryHelper;
import in.solpro.nucleus.apps.core.session.SessionUtil;

import java.util.List;

/**
 * @author asheesh
 */
public class CountryHelperTest extends AbstractMasterDataHelperTest
{
    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        Country country = new Country();
        country.setName( "Test Country" );
        new CountryHelper().addCountry( country );
    }

    /**
     * Test method for {@link in.solpro.nucleus.apps.core.dbhelper.CompanyHelper#addCompany(in.solpro.nucleus.apps.common.Company)}.
     */

   public void testAddCountry()
    {
        Country p = new Country();
        p.setName( "Canada" );
        try
        {
            new CountryHelper().addCountry( p );
        }
        catch ( Exception e )
        {
            fail();
        }
    }
   
   public void testAddCountry_DiffrentCompany()
   {
       Country country = new Country();
       country.setName( "Test Country" );
       SessionUtil.setCompany( companyNegTest );
       try
       {
           new CountryHelper().addCountry(country );
       }
       catch ( Exception e )
       {
           fail();
       }
   }
  

    public void testDuplicateCountry() 
    {
        Country country = new Country();
        country.setName( "Duplicate Country" );
        Country country2 = new Country();
        country2.setName( "Duplicate Country" );
        CountryHelper helper = new CountryHelper();
        try
        {
            helper.addCountry( country );
        }
        catch ( Exception e )
        {
            fail();
        }
          try
        {
            helper.addCountry( country2 );
            fail();
        }
        catch ( Exception e )
        {

        }

    }

    public void testGetCountry_DifferentCompany()
    {
        Country country = new Country();
        CountryHelper countryHelper = new CountryHelper();
        country.setName( "Canada AddCountry_NoCompany" );
        try
        {
            countryHelper.addCountry( country );
            assertNotNull( countryHelper.getCountry( "Canada AddCountry_NoCompany" ) );
            SessionUtil.setCompany( companyNegTest );
            assertNull( countryHelper.getCountry( "Canada AddCountry_NoCompany" ) );
            SessionUtil.setCompany( company );
        }
        catch ( Exception e )
        {
            fail();
        }

    }

    /**
     * Test method for {@link in.solpro.nucleus.apps.core.dbhelper.CountryHelper#addCountry(in.solpro.nucleus.apps.common.Country)}.
     */

    public void testAddCountry_NoName()
    {
        Country country = new Country();

        try
        {
            new CountryHelper().addCountry( country );
            fail();
        }
        catch ( Exception e )
        {

        }
    }

    /**
     * Test method for {@link in.solpro.nucleus.apps.core.dbhelper.CountryHelper#addCountry(in.solpro.nucleus.apps.common.Country)}.
     * @throws Exception
     */
    public void testgetCountryById() throws Exception
    {
        CountryHelper helper = new CountryHelper();
        Country country = helper.getCountry( helper.getCountry( "Test Country" ).getId() );
        if ( country == null )
        {
            fail();
        }
        country.validateAndUpdate();
        assertEquals( country.getId(), helper.getCountry( "Test Country" ).getId() );
        assertEquals( country.getName(), helper.getCountry( "Test Country" ).getName() );

    }

    public void testGetCountry() throws Exception
    {
        CountryHelper helper = new CountryHelper();
        Country country = helper.getCountry( "Test Country" );
        if ( country == null )
        {
            fail();
        }
        country.validateAndUpdate();
        assertEquals( country.getId(), country.getId() );
        assertEquals( country.getName(), country.getName() );
    }

    /**
     * Test method for {@link in.solpro.nucleus.apps.core.dbhelper.CountryHelper#addCountry(in.solpro.nucleus.apps.common.Country)}.
     */

    public void testGetCountries()
    {
        List<Country> countries;

        try
        {
            CountryHelper helper = new CountryHelper();
            Country country=new Country();
            country.setName( "INDIA" );
            country.setCompany( SessionUtil.getCompany() );
            helper.addCountry( country );
            Country country1=new Country();
            country1.setName( "CHINA" );
            country1.setCompany( SessionUtil.getCompany() );
            helper.addCountry( country1 );
            countries = helper.getCountries();
            if ( countries == null )
            {
                fail();
            }
            System.out.println("COUNTRIES SIZE:"+countries.size());
            for ( int i = 0; i < countries.size(); i++ )
            {
                countries.get( i ).validateAndUpdate();
                System.out.println( "Country name:" + countries.get( i ).getName() + "Country ID:" + countries.get( i ).getId() );
            }
        }
        catch ( Exception e )
        {
            System.out.print( "error:" );
        }
    }

    /**
     * This test verifies updateCountry
     * @throws Exception
     */

    public void testUpdateCountry() throws Exception
    {

        CountryHelper helper = new CountryHelper();
        System.out.println(SessionUtil.getCompany().getId() +":COMPANY NAME:"+SessionUtil.getCompany().getName());
        Country country = (Country) helper.getCountry( "Test Country" );
        country.setName( "HINDUSTAN" );
        helper.updateCountry( country );
        Country country2 = (Country) helper.getCountry( "HINDUSTAN" );
        if ( country2 == null )
        {
            fail();
        }
        assertEquals( country2.getName(), "HINDUSTAN" );
   }

}