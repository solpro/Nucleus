package test.dbhelper;

import in.solpro.nucleus.apps.common.City;
import in.solpro.nucleus.apps.common.Country;
import in.solpro.nucleus.apps.common.District;
import in.solpro.nucleus.apps.common.State;
import in.solpro.nucleus.apps.core.dbhelper.CityHelper;
import in.solpro.nucleus.apps.core.dbhelper.CountryHelper;
import in.solpro.nucleus.apps.core.dbhelper.DistrictHelper;
import in.solpro.nucleus.apps.core.dbhelper.StateHelper;
import in.solpro.nucleus.apps.core.session.SessionUtil;

import java.util.List;

/**
 * @author asheesh
 */
public class CityHelperTest extends AbstractMasterDataHelperTest
{
    private Country negcountry;
    private Country country;
    private State negstate;
    private State state;
    private District district;
    private District negdistrict;
    
    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        
        country = new Country();
        country.setName( "TEST COUNTRY" );
        new CountryHelper().addCountry( country );
        negcountry = new Country();
        negcountry.setName( "NEG TEST COUNTRY" );
        SessionUtil.setCompany( companyNegTest );
        new CountryHelper().addCountry( negcountry );
        SessionUtil.setCompany( company );
        
        state = new State();
        state.setName( "Test State" );
        state.setCountry( country );
        new StateHelper().addState(state );
        negstate = new State();
        negstate.setName( "NEG Test State" );
        negstate.setCountry( negcountry );
        SessionUtil.setCompany( companyNegTest );
        new StateHelper().addState(negstate);
        SessionUtil.setCompany( company );
       
        district = new District();
        district.setName( "Test District" );
        district.setState( state );
        new DistrictHelper().addDistrict(district );
        negdistrict = new District();
        negdistrict.setName( "NEG Test District" );
        negdistrict.setState( negstate );
        SessionUtil.setCompany( companyNegTest );
        new DistrictHelper().addDistrict(negdistrict);
        SessionUtil.setCompany( company );
       
        City city=new City();
        city.setName( "TEST CITY" );
        city.setDistrict( district );
        new CityHelper().addCity( city );
    }

    /**
     * Test method for {@link in.solpro.nucleus.apps.core.dbhelper.CompanyHelper#addCompany(in.solpro.nucleus.apps.common.Company)}.
     */

   public void testAddCity()
    {
       City city = new City();
        city.setName( "Maharshtra" );
        city.setDistrict(district);
        try
        {
            new CityHelper().addCity( city );
        }
        catch ( Exception e )
        {
            fail();
        }
    }
   
   public void testAddCity_DiffrentCompany()
   {
       City city = new City();
       city.setDistrict( negdistrict );
       city.setName( "Test City" );
       SessionUtil.setCompany( companyNegTest );
       try
       {
           new CityHelper().addCity(city );
       }
       catch ( Exception e )
       {
           fail();
       }
   }
  

    public void testDuplicateCity() 
    {
        City city = new City();
        city.setName( "Duplicate City" );
        city.setDistrict( district );
        City city2 = new City();
        city2.setName( "Duplicate City" );
       city2.setDistrict( district );
        CityHelper helper = new CityHelper();
        try
        {
            helper.addCity( city );
        }
        catch ( Exception e )
        {
            fail();
        }
          try
        {
            helper.addCity( city2 );
            fail();
        }
        catch ( Exception e )
        {

        }

    }

    public void testGetCity_DifferentCompany()
    {
        City city = new City();
        CityHelper cityHelper = new CityHelper();
        city.setName( "Canada AddCity_NoCompany" );
        city.setDistrict( district );
        try
        {
            cityHelper.addCity( city );
            assertNotNull( cityHelper.getCity( "Canada AddCity_NoCompany" ) );
            SessionUtil.setCompany( companyNegTest );
            assertNull( cityHelper.getCity( "Canada AddCity_NoCompany" ) );
            SessionUtil.setCompany( company );
        }
        catch ( Exception e )
        {
            fail();
        }

    }

    /**
     * Test method for {@link in.solpro.nucleus.apps.core.dbhelper.CityHelper#addCity(in.solpro.nucleus.apps.common.City)}.
     */

    public void testAddCity_NoName()
    {
        City city = new City();
        
        try
        {
            new CityHelper().addCity( city );
            fail();
        }
        catch ( Exception e )
        {

        }
    }

    /**
     * Test method for {@link in.solpro.nucleus.apps.core.dbhelper.CityHelper#addCity(in.solpro.nucleus.apps.common.City)}.
     * @throws Exception
     */
    public void testgetCityById() throws Exception
    {
        CityHelper helper = new CityHelper();
        City city = helper.getCity( helper.getCity( "Test City" ).getId() );
        if ( city == null )
        {
            fail();
        }
        city.validateAndUpdate();
        assertEquals( city.getId(), helper.getCity( "Test City" ).getId() );
        assertEquals( city.getName(), helper.getCity( "Test City" ).getName() );

    }

    public void testGetCity() throws Exception
    {
        CityHelper helper = new CityHelper();
        City city = helper.getCity( "Test City" );
        if ( city == null )
        {
            fail();
        }
        city.validateAndUpdate();
        assertEquals( city.getId(), city.getId() );
        assertEquals( city.getName(), city.getName() );
    }

    /**
     * Test method for {@link in.solpro.nucleus.apps.core.dbhelper.CityHelper#addCity(in.solpro.nucleus.apps.common.City)}.
     */

    public void testGetCities()
    {
        List<City> citys;

        try
        {
            CityHelper helper = new CityHelper();
            City city=new City();
            city.setName( "STATE1" );
            city.setDistrict( district );
            city.setCompany( SessionUtil.getCompany() );
            helper.addCity( city );
            City city1=new City();
            city1.setName( "STATE2" );
            city1.setDistrict( negdistrict );
            city1.setCompany( SessionUtil.getCompany() );
            helper.addCity( city1 );
            citys = helper.getCities();
            if ( citys == null )
            {
                fail();
            }
            System.out.println("COUNTRIES SIZE:"+citys.size());
            for ( int i = 0; i < citys.size(); i++ )
            {
                citys.get( i ).validateAndUpdate();
                System.out.println( "City name:" + citys.get( i ).getName() + "City ID:" + citys.get( i ).getId() );
            }
        }
        catch ( Exception e )
        {
            System.out.print( "error:" );
        }
    }

    /**
     * This test verifies updateCity
     * @throws Exception
     */

    public void testUpdateCity() throws Exception
    {

        CityHelper helper = new CityHelper();
        City city = (City) helper.getCity( "Test City" );
        city.setName( "HINDUSTAN" );
        helper.updateCity( city );
        City city2 = (City) helper.getCity( "HINDUSTAN" );
        if ( city2 == null )
        {
            fail();
        }
        assertEquals( city2.getName(), "HINDUSTAN" );
   }

}