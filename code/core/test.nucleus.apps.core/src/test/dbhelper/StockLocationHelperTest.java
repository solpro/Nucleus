package test.dbhelper;

import in.solpro.nucleus.apps.common.StockLocation;
import in.solpro.nucleus.apps.common.StockLocationType;
import in.solpro.nucleus.apps.core.dbhelper.StockLocationHelper;
import in.solpro.nucleus.apps.core.dbhelper.StockLocationTypeHelper;
import in.solpro.nucleus.apps.core.session.SessionUtil;

import java.util.List;

/**
 * @author asheesh
 */
public class StockLocationHelperTest extends AbstractMasterDataHelperTest
{
    StockLocationType stocklocationtype;
    StockLocationType negstocklocationtype;
    
    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        stocklocationtype = new StockLocationType();
        stocklocationtype.setName( "Test StockLocationType" );
        new StockLocationTypeHelper().addStockLocationType( stocklocationtype );
        
        negstocklocationtype = new StockLocationType();
        negstocklocationtype.setName( "Test negStockLocationType" );
        SessionUtil.setCompany( companyNegTest );
        new StockLocationTypeHelper().addStockLocationType( negstocklocationtype );
        SessionUtil.setCompany( company );
        
        StockLocation stockLocation=new StockLocation();
        stockLocation.setName( "Test StockLocation" );
        stockLocation.setType( stocklocationtype );
       new StockLocationHelper().addStockLocation( stockLocation );
    }

    /**
     * Test method for {@link in.solpro.nucleus.apps.core.dbhelper.CompanyHelper#addCompany(in.solpro.nucleus.apps.common.Company)}.
     */

    public void testAddStockLocation()
    {
        StockLocation p = new StockLocation();
        p.setName( "Test STOCK LOCATION" );
        p.setType( stocklocationtype ); 
        try
        {
            new StockLocationHelper().addStockLocation( p );
        }
        catch ( Exception e )
        {
            fail();
        }
    }

    public void testAddStockLocation_DiffrentCompany()
    {
        StockLocation stocklocation = new StockLocation();
        stocklocation.setName( "Test StockLocation" );
        stocklocation.setType( negstocklocationtype );
        SessionUtil.setCompany( companyNegTest );
        try
        {
            new StockLocationHelper().addStockLocation( stocklocation );
        }
        catch ( Exception e )
        {
            fail();
        }
    }

    public void testDuplicateStockLocation()
    {
        StockLocation stocklocation = new StockLocation();
        stocklocation.setName( "Duplicate StockLocation" );
        stocklocation.setType( stocklocationtype );
        StockLocation stocklocation2 = new StockLocation();
        stocklocation2.setName( "Duplicate StockLocation" );
        stocklocation2.setType( stocklocationtype );
        StockLocationHelper helper = new StockLocationHelper();
        try
        {
            helper.addStockLocation( stocklocation );
        }
        catch ( Exception e )
        {
            fail();
        }
        try
        {
            helper.addStockLocation( stocklocation2 );
            fail();
        }
        catch ( Exception e )
        {

        }

    }

    public void testGetStockLocation_DifferentCompany()
    {
        StockLocation stocklocatio = new StockLocation();
        StockLocationHelper stocklocatioHelper = new StockLocationHelper();
        stocklocatio.setName( "StockLocation1" );
        stocklocatio.setType( stocklocationtype );
        try
        {
            stocklocatioHelper.addStockLocation( stocklocatio );
            assertNotNull( stocklocatioHelper.getStockLocation( "StockLocation1" ) );
            SessionUtil.setCompany( companyNegTest );
            assertNull( stocklocatioHelper.getStockLocation( "StockLocation1" ) );
            SessionUtil.setCompany( company );
        }
        catch ( Exception e )
        {
            fail();
        }

    }

    /**
     * Test method for
     * {@link in.solpro.nucleus.apps.core.dbhelper.StockLocationHelper#addStockLocation(in.solpro.nucleus.apps.common.StockLocation)}
     * .
     */

    public void testAddStockLocation_NoName()
    {
        StockLocation stocklocatio = new StockLocation();

        try
        {
            new StockLocationHelper().addStockLocation( stocklocatio );
            fail();
        }
        catch ( Exception e )
        {

        }
    }

    /**
     * Test method for
     * {@link in.solpro.nucleus.apps.core.dbhelper.StockLocationHelper#addStockLocation(in.solpro.nucleus.apps.common.StockLocation)}
     * .
     * @throws Exception
     */
    public void testgetStockLocationById() throws Exception
    {
        StockLocationHelper helper = new StockLocationHelper();
        StockLocation stocklocatio = helper.getStockLocation( helper.getStockLocation( "Test StockLocation" ).getId() );
        if ( stocklocatio == null )
        {
            fail();
        }
        stocklocatio.validateAndUpdate();
        assertEquals( stocklocatio.getId(), helper.getStockLocation( "Test StockLocation" ).getId() );
        assertEquals( stocklocatio.getName(), helper.getStockLocation( "Test StockLocation" ).getName() );

    }

    public void testGetStockLocation() throws Exception
    {
        StockLocationHelper helper = new StockLocationHelper();
        StockLocation stocklocatio = helper.getStockLocation( "Test StockLocation" );
        if ( stocklocatio == null )
        {
            fail();
        }
        stocklocatio.validateAndUpdate();
        assertEquals( stocklocatio.getId(), stocklocatio.getId() );
        assertEquals( stocklocatio.getName(), stocklocatio.getName() );
    }

    /**
     * Test method for
     * {@link in.solpro.nucleus.apps.core.dbhelper.StockLocationHelper#addStockLocation(in.solpro.nucleus.apps.common.StockLocation)}
     * .
     */

    public void testGetStockLocations()
    {
        List<StockLocation> stocklocatios;

        try
        {
            StockLocationHelper helper = new StockLocationHelper();
            StockLocation stocklocatio = new StockLocation();
            stocklocatio.setName( "STOCK LOCATION 1" );
            stocklocatio.setCompany( SessionUtil.getCompany() );
            helper.addStockLocation( stocklocatio );
            StockLocation stocklocatio1 = new StockLocation();
            stocklocatio1.setName( "STOCK LOCATION 2" );
            stocklocatio1.setCompany( SessionUtil.getCompany() );
            helper.addStockLocation( stocklocatio1 );
            stocklocatios = helper.getStockLocations();
            if ( stocklocatios == null )
            {
                fail();
            }
            for ( int i = 0; i < stocklocatios.size(); i++ )
            {
                stocklocatios.get( i ).validateAndUpdate();
                System.out.println( "StockLocation name:" + stocklocatios.get( i ).getName() + "StockLocation ID:"
                        + stocklocatios.get( i ).getId() );
            }
        }
        catch ( Exception e )
        {
            System.out.print( "error:" );
        }
    }

    /**
     * This test verifies updateStockLocation
     * @throws Exception
     */

    public void testUpdateStockLocation() throws Exception
    {

        StockLocationHelper helper = new StockLocationHelper();
        StockLocation stocklocatio = (StockLocation) helper.getStockLocation( "Test StockLocation" );
        stocklocatio.setName( "XYZ_GROUP" );
        helper.updateStockLocation( stocklocatio );
        StockLocation stocklocatio2 = (StockLocation) helper.getStockLocation( "XYZ_GROUP" );
        if ( stocklocatio2 == null )
        {
            fail();
        }
        assertEquals( stocklocatio2.getName(), "XYZ_GROUP" );
    }

}