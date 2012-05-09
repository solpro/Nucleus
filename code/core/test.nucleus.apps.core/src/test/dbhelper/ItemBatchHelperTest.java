package test.dbhelper;

import in.solpro.nucleus.apps.common.Item;
import in.solpro.nucleus.apps.common.ItemBatch;
import in.solpro.nucleus.apps.common.ItemCompany;
import in.solpro.nucleus.apps.common.ItemGroup;
import in.solpro.nucleus.apps.core.dbhelper.ItemBatchHelper;
import in.solpro.nucleus.apps.core.dbhelper.ItemCompanyHelper;
import in.solpro.nucleus.apps.core.dbhelper.ItemGroupHelper;
import in.solpro.nucleus.apps.core.dbhelper.ItemHelper;
import in.solpro.nucleus.apps.core.session.SessionUtil;

import java.sql.Date;
import java.util.List;

/**
 * @author asheesh
 */
@SuppressWarnings("deprecation")
public class ItemBatchHelperTest extends AbstractMasterDataHelperTest
{
    Date expirydate = new Date( 15, 12, 2012 );

    ItemGroup itemgroup;

    ItemGroup negitemgroup;

    ItemCompany itemcompany;

    ItemCompany negitemcompany;

    Item item;

    Item negitem;


    @Override
    protected void setUp() throws Exception
    {

        super.setUp();
        itemgroup = new ItemGroup();
        itemgroup.setName( "Test ItemGroup" );
        new ItemGroupHelper().addItemGroup( itemgroup );
        negitemgroup = new ItemGroup();
        negitemgroup.setName( "Test NegItemGroup" );
        SessionUtil.setCompany( companyNegTest );
        new ItemGroupHelper().addItemGroup( negitemgroup );
        SessionUtil.setCompany( company );

        itemcompany = new ItemCompany();
        itemcompany.setName( "Test ItemCompany" );
        new ItemCompanyHelper().addItemCompany( itemcompany );
        negitemcompany = new ItemCompany();
        negitemcompany.setName( "Test NegItemCompany" );
        SessionUtil.setCompany( companyNegTest );
        new ItemCompanyHelper().addItemCompany( negitemcompany );
        SessionUtil.setCompany( company );
        
        item = new Item();
        item.setName( "Test Item" );
        item.setItemgroup( itemgroup );
        item.setItemCompany( itemcompany );
        new ItemHelper().addItem( item );
        negitem = new Item();
        negitem.setName( "Test negItem" );
        negitem.setItemgroup( negitemgroup );
        negitem.setItemCompany( negitemcompany );
        SessionUtil.setCompany( companyNegTest );
        new ItemHelper().addItem( negitem );
        SessionUtil.setCompany( company);
        
        ItemBatch itembatch = new ItemBatch();
        itembatch.setName( "Test ItemBatch" );
        itembatch.setItem( item );
        itembatch.setExpirydate( expirydate );
        new ItemBatchHelper().addItemBatch( itembatch );
    }

    /**
     * Test method for {@link in.solpro.nucleus.apps.core.dbhelper.CompanyHelper#addCompany(in.solpro.nucleus.apps.common.Company)}.
     */

    public void testAddItemBatch()
    {
        ItemBatch itembatch = new ItemBatch();
        itembatch.setName( "Test BATCH" );
        itembatch.setItem( item );
        itembatch.setExpirydate( expirydate );

        try
        {
            new ItemBatchHelper().addItemBatch( itembatch );
        }
        catch ( Exception e )
        {
            fail();
        }
    }

    public void testAddItemBatch_DiffrentCompany()
    {
        ItemBatch itembatch = new ItemBatch();
        itembatch.setName( "Test ItemBatch" );
        itembatch.setItem( negitem );
        itembatch.setExpirydate( expirydate );
        SessionUtil.setCompany( companyNegTest );
        try
        {
            new ItemBatchHelper().addItemBatch( itembatch );
        }
        catch ( Exception e )
        {
            fail();
        }
    }

    public void testDuplicateItemBatch()
    {
        ItemBatch itembatch = new ItemBatch();
        itembatch.setName( "Duplicate ItemBatch" );
        itembatch.setItem( item );
        itembatch.setExpirydate( expirydate );
        ItemBatch itembatch2 = new ItemBatch();
        itembatch2.setName( "Duplicate ItemBatch" );
        itembatch2.setItem( item );
        itembatch.setExpirydate( expirydate );

        ItemBatchHelper helper = new ItemBatchHelper();
        try
        {
            helper.addItemBatch( itembatch );
        }
        catch ( Exception e )
        {
            fail();
        }
        try
        {
            helper.addItemBatch( itembatch2 );
            fail();
        }
        catch ( Exception e )
        {

        }

    }

    public void testGetItemBatch_DifferentCompany()
    {
        ItemBatch itembatch = new ItemBatch();
        ItemBatchHelper itembatchHelper = new ItemBatchHelper();
        itembatch.setName( "ItemBatch1" );
        itembatch.setItem( item );
        itembatch.setExpirydate( expirydate );

        try
        {
            itembatchHelper.addItemBatch( itembatch );
            assertNotNull( itembatchHelper.getItemBatch( "ItemBatch1" ) );
            SessionUtil.setCompany( companyNegTest );
            assertNull( itembatchHelper.getItemBatch( "ItemBatch1" ) );
            SessionUtil.setCompany( company );
        }
        catch ( Exception e )
        {
            fail();
        }

    }

    /**
     * Test method for
     * {@link in.solpro.nucleus.apps.core.dbhelper.ItemBatchHelper#addItemBatch(in.solpro.nucleus.apps.common.ItemBatch)}.
     */

    public void testAddItemBatch_NoName()
    {
        ItemBatch itembatch = new ItemBatch();

        try
        {
            new ItemBatchHelper().addItemBatch( itembatch );
            fail();
        }
        catch ( Exception e )
        {

        }
    }

    /**
     * Test method for
     * {@link in.solpro.nucleus.apps.core.dbhelper.ItemBatchHelper#addItemBatch(in.solpro.nucleus.apps.common.ItemBatch)}.
     * @throws Exception
     */
    public void testgetItemBatchById() throws Exception
    {
        ItemBatchHelper helper = new ItemBatchHelper();
        ItemBatch itembatch = helper.getItemBatch( helper.getItemBatch( "Test ItemBatch" ).getId() );
        if ( itembatch == null )
        {
            fail();
        }
        itembatch.validateAndUpdate();
        assertEquals( itembatch.getId(), helper.getItemBatch( "Test ItemBatch" ).getId() );
        assertEquals( itembatch.getName(), helper.getItemBatch( "Test ItemBatch" ).getName() );

    }

    public void testGetItemBatch() throws Exception
    {
        ItemBatchHelper helper = new ItemBatchHelper();
        ItemBatch itembatch = helper.getItemBatch( "Test ItemBatch" );
        if ( itembatch == null )
        {
            fail();
        }
        itembatch.validateAndUpdate();
        assertEquals( itembatch.getId(), itembatch.getId() );
        assertEquals( itembatch.getName(), itembatch.getName() );
    }

    /**
     * Test method for
     * {@link in.solpro.nucleus.apps.core.dbhelper.ItemBatchHelper#addItemBatch(in.solpro.nucleus.apps.common.ItemBatch)}.
     */

    public void testGetItemBatchs()
    {
        List<ItemBatch> itembatchs;

        try
        {
            ItemBatchHelper helper = new ItemBatchHelper();
            ItemBatch itembatch = new ItemBatch();
            itembatch.setName( "ITEMBATCH1" );
            itembatch.setCompany( SessionUtil.getCompany() );
            helper.addItemBatch( itembatch );
            ItemBatch itembatch1 = new ItemBatch();
            itembatch1.setName( "ITEMBATCH2" );
            itembatch1.setCompany( SessionUtil.getCompany() );
            helper.addItemBatch( itembatch1 );
            itembatchs = helper.getItemBatches();
            if ( itembatchs == null )
            {
                fail();
            }
            for ( int i = 0; i < itembatchs.size(); i++ )
            {
                itembatchs.get( i ).validateAndUpdate();
                System.out.println( "ItemBatch name:" + itembatchs.get( i ).getName() + "ItemBatch ID:" + itembatchs.get( i ).getId() );
            }
        }
        catch ( Exception e )
        {
            System.out.print( "error:" );
        }
    }

    /**
     * This test verifies updateItemBatch
     * @throws Exception
     */

    public void testUpdateItemBatch() throws Exception
    {

        ItemBatchHelper helper = new ItemBatchHelper();
        ItemBatch itembatch = (ItemBatch) helper.getItemBatch( "Test ItemBatch" );
        itembatch.setName( "XYZ_BATCH" );
        helper.updateItemBatch( itembatch );
        ItemBatch itembatch2 = (ItemBatch) helper.getItemBatch( "XYZ_BATCH" );
        if ( itembatch2 == null )
        {
            fail();
        }
        assertEquals( itembatch2.getName(), "XYZ_BATCH" );
    }

}