package test.dbhelper;

import in.solpro.nucleus.apps.common.City;
import in.solpro.nucleus.apps.common.Country;
import in.solpro.nucleus.apps.common.District;
import in.solpro.nucleus.apps.common.ProductUser;
import in.solpro.nucleus.apps.common.State;
import in.solpro.nucleus.apps.common.UserGroup;
import in.solpro.nucleus.apps.core.dbhelper.CityHelper;
import in.solpro.nucleus.apps.core.dbhelper.CountryHelper;
import in.solpro.nucleus.apps.core.dbhelper.DistrictHelper;
import in.solpro.nucleus.apps.core.dbhelper.ProductUserHelper;
import in.solpro.nucleus.apps.core.dbhelper.StateHelper;
import in.solpro.nucleus.apps.core.dbhelper.UserGroupHelper;
import in.solpro.nucleus.apps.core.session.SessionUtil;

import java.util.List;

/**
 * @author asheesh
 */
public class ProductUserHelperTest extends AbstractMasterDataHelperTest
{
    UserGroup usergroup;

    UserGroup negusergroup;

    Country country;

    Country negcountry;

    State state;

    State negstate;

    District district;

    District negdistrict;

    City city;

    City negcity;

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        usergroup = new UserGroup();
        usergroup.setName( "Test UserGroup" );
        new UserGroupHelper().addUserGroup( usergroup );
        negusergroup = new UserGroup();
        negusergroup.setName( "Test NegUserGroup" );
        SessionUtil.setCompany( companyNegTest );
        new UserGroupHelper().addUserGroup( negusergroup );
        SessionUtil.setCompany( company );
        
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
       
        city=new City();
        city.setName( "TEST CITY" );
        city.setDistrict( district );
        new CityHelper().addCity( city );
        negcity=new City();
        negcity.setName( "TEST negCITY" );
        negcity.setDistrict( negdistrict );
        SessionUtil.setCompany( companyNegTest );
        new CityHelper().addCity( negcity );
        SessionUtil.setCompany( company );


        ProductUser productuser = new ProductUser();
        productuser.setUsername( "Test ProductUser" );
        productuser.setPassword( "Test Password" );
        productuser.setCity( city );
        productuser.setUserGroup( usergroup );
        new ProductUserHelper().addProductUser( productuser );
    }

    /**
     * Test method for {@link in.solpro.nucleus.apps.core.dbhelper.CompanyHelper#addCompany(in.solpro.nucleus.apps.common.Company)}.
     */

    public void testAddProductUser()
    {
        ProductUser productuser = new ProductUser();
        productuser.setUsername( "Test ProductUser1" );
        productuser.setPassword( "Test Password" );
        productuser.setCity( city );
        productuser.setUserGroup( usergroup );
        try
        {
            new ProductUserHelper().addProductUser( productuser );
        }
        catch ( Exception e )
        {
            fail();
        }
    }

    public void testAddProductUser_DiffrentCompany()
    {
        ProductUser productuser = new ProductUser();
        productuser.setUsername( "Test ProductUser" );
        productuser.setPassword( "Test Password" );
        productuser.setCity( negcity );
        productuser.setUserGroup( negusergroup );
        SessionUtil.setCompany( companyNegTest );
        try
        {
            new ProductUserHelper().addProductUser( productuser );
        }
        catch ( Exception e )
        {
            fail();
        }
    }

    public void testDuplicateProductUser()
    {
        ProductUser productuser = new ProductUser();
        productuser.setUsername( "Duplicate ProductUser" );
        productuser.setPassword( "Test Password" );
        productuser.setCity( city );
        productuser.setUserGroup( usergroup );
        ProductUser productuser2 = new ProductUser();
        productuser2.setUsername( "Duplicate ProductUser" );
        productuser2.setPassword( "Test Password" );
        productuser2.setCity( city );
        productuser2.setUserGroup( usergroup );
        ProductUserHelper helper = new ProductUserHelper();
        try
        {
            helper.addProductUser( productuser );
        }
        catch ( Exception e )
        {
            fail();
        }
        try
        {
            helper.addProductUser( productuser2 );
            fail();
        }
        catch ( Exception e )
        {

        }

    }

    public void testGetProductUser_DifferentCompany()
    {
        ProductUser productuser = new ProductUser();
        ProductUserHelper productuserHelper = new ProductUserHelper();
        productuser.setUsername( "ProductUser1" );
        productuser.setPassword( "Test Password" );
        productuser.setCity( city );
        productuser.setUserGroup( usergroup );
        try
        {
            productuserHelper.addProductUser( productuser );
            assertNotNull( productuserHelper.getProductUser( "ProductUser1" ) );
            SessionUtil.setCompany( companyNegTest );
            assertNull( productuserHelper.getProductUser( "ProductUser1" ) );
            SessionUtil.setCompany( company );
        }
        catch ( Exception e )
        {
            fail();
        }

    }

    /**
     * Test method for
     * {@link in.solpro.nucleus.apps.core.dbhelper.ProductUserHelper#addProductUser(in.solpro.nucleus.apps.common.ProductUser)}.
     */

    public void testAddProductUser_NoName()
    {
        ProductUser productuser1 = new ProductUser();

        try
        {
            new ProductUserHelper().addProductUser( productuser1 );
            fail();
        }
        catch ( Exception e )
        {

        }
    }

    /**
     * Test method for
     * {@link in.solpro.nucleus.apps.core.dbhelper.ProductUserHelper#addProductUser(in.solpro.nucleus.apps.common.ProductUser)}.
     * @throws Exception
     */
    public void testgetProductUserById() throws Exception
    {
        ProductUserHelper helper = new ProductUserHelper();
        ProductUser productuser = helper.getProductUser( helper.getProductUser( "Test ProductUser" ).getId() );
        if ( productuser == null )
        {
            fail();
        }
        productuser.validateAndUpdate();
        assertEquals( productuser.getId(), helper.getProductUser( "Test ProductUser" ).getId() );
        assertEquals( productuser.getUsername(), helper.getProductUser( "Test ProductUser" ).getUsername() );

    }

    public void testGetProductUser() throws Exception
    {
        ProductUserHelper helper = new ProductUserHelper();
        ProductUser productuser = helper.getProductUser( "Test ProductUser" );
        if ( productuser == null )
        {
            fail();
        }
        productuser.validateAndUpdate();
        assertEquals( productuser.getId(), productuser.getId() );
        assertEquals( productuser.getUsername(), productuser.getUsername() );
    }

    /**
     * Test method for
     * {@link in.solpro.nucleus.apps.core.dbhelper.ProductUserHelper#addProductUser(in.solpro.nucleus.apps.common.ProductUser)}.
     */

    public void testGetProductUsers()
    {
        List<ProductUser> productusers;

        try
        {
            ProductUserHelper helper = new ProductUserHelper();
            ProductUser productuser = new ProductUser();
            productuser.setUsername( "User1" );
            productuser.setPassword( "Test Password" );
            productuser.setCity( city );
                productuser.setUserGroup( usergroup );
            helper.addProductUser( productuser );
            ProductUser productuser1 = new ProductUser();
            productuser1.setPassword( "Test Password" );
            productuser1.setCity( city );
            productuser1.setUsername( "User2" );
            helper.addProductUser( productuser1 );
            productusers = helper.getProductUsers();
            if ( productusers == null )
            {
                fail();
            }
            for ( int i = 0; i < productusers.size(); i++ )
            {
                productusers.get( i ).validateAndUpdate();
                System.out.println( "ProductUser name:" + productusers.get( i ).getUsername() + "ProductUser ID:"
                        + productusers.get( i ).getId() );
            }
        }
        catch ( Exception e )
        {
            System.out.print( "error:" );
        }
    }

    /**
     * This test verifies updateProductUser
     * @throws Exception
     */

    public void testUpdateProductUser() throws Exception
    {

        ProductUserHelper helper = new ProductUserHelper();
        ProductUser productuser = (ProductUser) helper.getProductUser( "Test ProductUser" );
        productuser.setUsername( "XYZ_GROUP" );
        helper.updateProductUser( productuser );
        ProductUser productuser2 = (ProductUser) helper.getProductUser( "XYZ_GROUP" );
        if ( productuser2 == null )
        {
            fail();
        }
        assertEquals( productuser2.getUsername(), "XYZ_GROUP" );
    }

}