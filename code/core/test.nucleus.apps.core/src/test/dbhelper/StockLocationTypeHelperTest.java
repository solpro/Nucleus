package test.dbhelper;

import in.solpro.nucleus.apps.common.StockLocationType;
import in.solpro.nucleus.apps.core.dbhelper.StockLocationTypeHelper;
import in.solpro.nucleus.apps.core.session.SessionUtil;

import java.util.List;

/**
 * @author asheesh
 */
public class StockLocationTypeHelperTest extends AbstractMasterDataHelperTest
{
    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        StockLocationType stocklocationtype = new StockLocationType();
        stocklocationtype.setName( "Test StockLocationType" );
        new StockLocationTypeHelper().addStockLocationType( stocklocationtype );
    }

    /**
     * Test method for {@link in.solpro.nucleus.apps.core.dbhelper.CompanyHelper#addCompany(in.solpro.nucleus.apps.common.Company)}.
     */

    public void testAddStockLoactionType()
    {
        StockLocationType p = new StockLocationType();
        p.setName( "Test Group" );

        try
        {
            new StockLocationTypeHelper().addStockLocationType( p );
        }
        catch ( Exception e )
        {
            fail();
        }
    }

    public void testAddStockLocationType_DiffrentCompany()
    {
        StockLocationType stocklocatiotype = new StockLocationType();
        stocklocatiotype.setName( "Test StockLocationType" );
        SessionUtil.setCompany( companyNegTest );
        try
        {
            new StockLocationTypeHelper().addStockLocationType( stocklocatiotype );
        }
        catch ( Exception e )
        {
            fail();
        }
    }

    public void testDuplicateStockLocationType()
    {
        StockLocationType stocklocatiotype = new StockLocationType();
        stocklocatiotype.setName( "Duplicate StockLocationType" );
        StockLocationType stocklocatiotype2 = new StockLocationType();
        stocklocatiotype2.setName( "Duplicate StockLocationType" );
        StockLocationTypeHelper helper = new StockLocationTypeHelper();
        try
        {
            helper.addStockLocationType( stocklocatiotype );
        }
        catch ( Exception e )
        {
            fail();
        }
        try
        {
            helper.addStockLocationType( stocklocatiotype2 );
            fail();
        }
        catch ( Exception e )
        {

        }

    }

    public void testGetStockLocationType_DifferentCompany()
    {
        StockLocationType stocklocatiotype = new StockLocationType();
        StockLocationTypeHelper stocklocatiotypeHelper = new StockLocationTypeHelper();
        stocklocatiotype.setName( "StockLocationType1" );
        try
        {
            stocklocatiotypeHelper.addStockLocationType( stocklocatiotype );
            assertNotNull( stocklocatiotypeHelper.getStockLocationType( "StockLocationType1" ) );
            SessionUtil.setCompany( companyNegTest );
            assertNull( stocklocatiotypeHelper.getStockLocationType( "StockLocationType1" ) );
            SessionUtil.setCompany( company );
        }
        catch ( Exception e )
        {
            fail();
        }

    }

    /**
     * Test method for
     * {@link in.solpro.nucleus.apps.core.dbhelper.StockLocationTypeHelper#addStockLocationType(in.solpro.nucleus.apps.common.StockLocationType)}
     * .
     */

    public void testAddStockLocationType_NoName()
    {
        StockLocationType stocklocatiotype = new StockLocationType();

        try
        {
            new StockLocationTypeHelper().addStockLocationType( stocklocatiotype );
            fail();
        }
        catch ( Exception e )
        {

        }
    }

    /**
     * Test method for
     * {@link in.solpro.nucleus.apps.core.dbhelper.StockLocationTypeHelper#addStockLocationType(in.solpro.nucleus.apps.common.StockLocationType)}
     * .
     * @throws Exception
     */
    public void testgetStockLocationTypeById() throws Exception
    {
        StockLocationTypeHelper helper = new StockLocationTypeHelper();
        StockLocationType stocklocatiotype = helper.getStockLocationType( helper.getStockLocationType( "Test StockLocationType" )
                .getId() );
        if ( stocklocatiotype == null )
        {
            fail();
        }
        stocklocatiotype.validateAndUpdate();
        assertEquals( stocklocatiotype.getId(), helper.getStockLocationType( "Test StockLocationType" ).getId() );
        assertEquals( stocklocatiotype.getName(), helper.getStockLocationType( "Test StockLocationType" ).getName() );

    }

    public void testGetStockLocationType() throws Exception
    {
        StockLocationTypeHelper helper = new StockLocationTypeHelper();
        StockLocationType stocklocatiotype = helper.getStockLocationType( "Test StockLocationType" );
        if ( stocklocatiotype == null )
        {
            fail();
        }
        stocklocatiotype.validateAndUpdate();
        assertEquals( stocklocatiotype.getId(), stocklocatiotype.getId() );
        assertEquals( stocklocatiotype.getName(), stocklocatiotype.getName() );
    }

    /**
     * Test method for
     * {@link in.solpro.nucleus.apps.core.dbhelper.StockLocationTypeHelper#addStockLocationType(in.solpro.nucleus.apps.common.StockLocationType)}
     * .
     */

    public void testGetStockLocationTypes()
    {
        List<StockLocationType> stocklocatiotypes;

        try
        {
            StockLocationTypeHelper helper = new StockLocationTypeHelper();
            StockLocationType stocklocatiotype = new StockLocationType();
            stocklocatiotype.setName( "LEDGERGROUP1" );
            stocklocatiotype.setCompany( SessionUtil.getCompany() );
            helper.addStockLocationType( stocklocatiotype );
            StockLocationType stocklocatiotype1 = new StockLocationType();
            stocklocatiotype1.setName( "LEDGERGROUP2" );
            stocklocatiotype1.setCompany( SessionUtil.getCompany() );
            helper.addStockLocationType( stocklocatiotype1 );
            stocklocatiotypes = helper.getStockLocationTypes();
            if ( stocklocatiotypes == null )
            {
                fail();
            }
            for ( int i = 0; i < stocklocatiotypes.size(); i++ )
            {
                stocklocatiotypes.get( i ).validateAndUpdate();
                System.out.println( "StockLocationType name:" + stocklocatiotypes.get( i ).getName() + "StockLocationType ID:"
                        + stocklocatiotypes.get( i ).getId() );
            }
        }
        catch ( Exception e )
        {
            System.out.print( "error:" );
        }
    }

    /**
     * This test verifies updateStockLocationType
     * @throws Exception
     */

    public void testUpdateStockLocationType() throws Exception
    {

        StockLocationTypeHelper helper = new StockLocationTypeHelper();
        StockLocationType stocklocatiotype = (StockLocationType) helper.getStockLocationType( "Test StockLocationType" );
        stocklocatiotype.setName( "XYZ_GROUP" );
        helper.updateStockLocationType( stocklocatiotype );
        StockLocationType stocklocatiotype2 = (StockLocationType) helper.getStockLocationType( "XYZ_GROUP" );
        if ( stocklocatiotype2 == null )
        {
            fail();
        }
        assertEquals( stocklocatiotype2.getName(), "XYZ_GROUP" );
    }

}