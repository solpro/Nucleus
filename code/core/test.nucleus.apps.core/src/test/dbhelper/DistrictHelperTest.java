package test.dbhelper;

import in.solpro.nucleus.apps.common.Country;
import in.solpro.nucleus.apps.common.District;
import in.solpro.nucleus.apps.common.State;
import in.solpro.nucleus.apps.core.dbhelper.CountryHelper;
import in.solpro.nucleus.apps.core.dbhelper.DistrictHelper;
import in.solpro.nucleus.apps.core.dbhelper.StateHelper;
import in.solpro.nucleus.apps.core.session.SessionUtil;

import java.util.List;

/**
 * @author asheesh
 */
public class DistrictHelperTest extends AbstractMasterDataHelperTest
{
    private Country negcountry;
    private Country country;
    private State negstate;
    private State state;
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
        District district=new District();
        district.setName( "TEST DISTRICT" );
        district.setState( state );
        new DistrictHelper().addDistrict( district );
    }

    /**
     * Test method for {@link in.solpro.nucleus.apps.core.dbhelper.CompanyHelper#addCompany(in.solpro.nucleus.apps.common.Company)}.
     */

   public void testAddDistrict()
    {
       District district = new District();
        district.setName( "Maharshtra" );
        district.setState(state);
        try
        {
            new DistrictHelper().addDistrict( district );
        }
        catch ( Exception e )
        {
            fail();
        }
    }
   
   public void testAddDistrict_DiffrentCompany()
   {
       District district = new District();
       district.setState( negstate );
       district.setName( "Test District" );
       SessionUtil.setCompany( companyNegTest );
       try
       {
           new DistrictHelper().addDistrict(district );
       }
       catch ( Exception e )
       {
           fail();
       }
   }
  

    public void testDuplicateDistrict() 
    {
        District district = new District();
        district.setName( "Duplicate District" );
        district.setState( state );
        District district2 = new District();
        district2.setName( "Duplicate District" );
       district2.setState( state );
        DistrictHelper helper = new DistrictHelper();
        try
        {
            helper.addDistrict( district );
        }
        catch ( Exception e )
        {
            fail();
        }
          try
        {
            helper.addDistrict( district2 );
            fail();
        }
        catch ( Exception e )
        {

        }

    }

    public void testGetDistrict_DifferentCompany()
    {
        District district = new District();
        DistrictHelper districtHelper = new DistrictHelper();
        district.setName( "Canada AddDistrict_NoCompany" );
        district.setState( state );
        try
        {
            districtHelper.addDistrict( district );
            assertNotNull( districtHelper.getDistrict( "Canada AddDistrict_NoCompany" ) );
            SessionUtil.setCompany( companyNegTest );
            assertNull( districtHelper.getDistrict( "Canada AddDistrict_NoCompany" ) );
            SessionUtil.setCompany( company );
        }
        catch ( Exception e )
        {
            fail();
        }

    }

    /**
     * Test method for {@link in.solpro.nucleus.apps.core.dbhelper.DistrictHelper#addDistrict(in.solpro.nucleus.apps.common.District)}.
     */

    public void testAddDistrict_NoName()
    {
        District district = new District();
        
        try
        {
            new DistrictHelper().addDistrict( district );
            fail();
        }
        catch ( Exception e )
        {

        }
    }

    /**
     * Test method for {@link in.solpro.nucleus.apps.core.dbhelper.DistrictHelper#addDistrict(in.solpro.nucleus.apps.common.District)}.
     * @throws Exception
     */
    public void testgetDistrictById() throws Exception
    {
        DistrictHelper helper = new DistrictHelper();
        District district = helper.getDistrict( helper.getDistrict( "Test District" ).getId() );
        if ( district == null )
        {
            fail();
        }
        district.validateAndUpdate();
        assertEquals( district.getId(), helper.getDistrict( "Test District" ).getId() );
        assertEquals( district.getName(), helper.getDistrict( "Test District" ).getName() );

    }

    public void testGetDistrict() throws Exception
    {
        DistrictHelper helper = new DistrictHelper();
        District district = helper.getDistrict( "Test District" );
        if ( district == null )
        {
            fail();
        }
        district.validateAndUpdate();
        assertEquals( district.getId(), district.getId() );
        assertEquals( district.getName(), district.getName() );
    }

    /**
     * Test method for {@link in.solpro.nucleus.apps.core.dbhelper.DistrictHelper#addDistrict(in.solpro.nucleus.apps.common.District)}.
     */

    public void testGetDistricts()
    {
        List<District> districts;

        try
        {
            DistrictHelper helper = new DistrictHelper();
            District district=new District();
            district.setName( "STATE1" );
            district.setState( state );
            district.setCompany( SessionUtil.getCompany() );
            helper.addDistrict( district );
            District district1=new District();
            district1.setName( "STATE2" );
            district1.setState( negstate );
            district1.setCompany( SessionUtil.getCompany() );
            helper.addDistrict( district1 );
            districts = helper.getDistricts();
            if ( districts == null )
            {
                fail();
            }
            System.out.println("COUNTRIES SIZE:"+districts.size());
            for ( int i = 0; i < districts.size(); i++ )
            {
                districts.get( i ).validateAndUpdate();
                System.out.println( "District name:" + districts.get( i ).getName() + "District ID:" + districts.get( i ).getId() );
            }
        }
        catch ( Exception e )
        {
            System.out.print( "error:" );
        }
    }

    /**
     * This test verifies updateDistrict
     * @throws Exception
     */

    public void testUpdateDistrict() throws Exception
    {

        DistrictHelper helper = new DistrictHelper();
        District district = (District) helper.getDistrict( "Test District" );
        district.setName( "HINDUSTAN" );
        helper.updateDistrict( district );
        District district2 = (District) helper.getDistrict( "HINDUSTAN" );
        if ( district2 == null )
        {
            fail();
        }
        assertEquals( district2.getName(), "HINDUSTAN" );
   }

}