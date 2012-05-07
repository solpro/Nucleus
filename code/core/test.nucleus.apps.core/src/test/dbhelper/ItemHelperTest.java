package test.dbhelper;

import in.solpro.nucleus.apps.common.Item;
import in.solpro.nucleus.apps.common.ItemCompany;
import in.solpro.nucleus.apps.common.ItemGroup;
import in.solpro.nucleus.apps.common.Ledger;
import in.solpro.nucleus.apps.common.LedgerGroup;
import in.solpro.nucleus.apps.common.LedgerGroupType;
import in.solpro.nucleus.apps.core.dbhelper.ItemCompanyHelper;
import in.solpro.nucleus.apps.core.dbhelper.ItemGroupHelper;
import in.solpro.nucleus.apps.core.dbhelper.ItemHelper;
import in.solpro.nucleus.apps.core.dbhelper.LedgerGroupHelper;
import in.solpro.nucleus.apps.core.dbhelper.LedgerHelper;
import in.solpro.nucleus.apps.core.session.SessionUtil;

import java.util.List;

/**
 * @author asheesh
 */
public class ItemHelperTest extends AbstractMasterDataHelperTest
{
    LedgerGroup ledgergroup;

    LedgerGroup negledgergroup;

    Ledger ledger;

    Ledger negledger;

    ItemGroup itemgroup;

    ItemGroup negitemgroup;

    ItemCompany itemcompany;

    ItemCompany negitemcompany;

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        
        ledgergroup = new LedgerGroup();
        ledgergroup.setName( "Test LedgerGroup" );
        ledgergroup.setType( LedgerGroupType.EXPENSES );
        new LedgerGroupHelper().addLedgerGroup( ledgergroup );
        negledgergroup = new LedgerGroup();
        negledgergroup.setName( "Test NegLedgerGroup" );
        negledgergroup.setType( LedgerGroupType.EXPENSES );
        SessionUtil.setCompany( companyNegTest );
        new LedgerGroupHelper().addLedgerGroup( negledgergroup );
        SessionUtil.setCompany( company );

        ledger = new Ledger();
        ledger.setName( "Test Ledger" );
        ledger.setLedgergroup( ledgergroup );
        new LedgerHelper().addLedger( ledger );
        negledger = new Ledger();
        negledger.setName( "Test negLedger" );
        negledger.setLedgergroup( negledgergroup );
        SessionUtil.setCompany( companyNegTest );
        new LedgerHelper().addLedger( negledger );
        SessionUtil.setCompany( company );

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

        Item item = new Item();
        item.setName( "Test Item" );
        item.setItemgroup( itemgroup );
        item.setItemCompany( itemcompany );
        new ItemHelper().addItem( item );
    }

    /**
     * Test method for {@link in.solpro.nucleus.apps.core.dbhelper.CompanyHelper#addCompany(in.solpro.nucleus.apps.common.Company)} .
     */

    public void testAddItem()
    {
        Item p = new Item();
        p.setName( "Test Item1" );
        p.setItemCompany( itemcompany );
        p.setItemgroup( itemgroup );
        try
        {
            new ItemHelper().addItem( p );
        }
        catch ( Exception e )
        {
            fail();
        }
    }

    public void testAddItem_DiffrentCompany()
    {
        Item item = new Item();
        item.setName( "Test Item" );
        item.setItemCompany( negitemcompany );
        item.setItemgroup( negitemgroup );
        SessionUtil.setCompany( companyNegTest );
        try
        {
            new ItemHelper().addItem( item );
        }
        catch ( Exception e )
        {
            fail();
        }
    }

    public void testDuplicateItem()
    {
        Item item = new Item();
        item.setName( "Duplicate Item" );
        item.setItemCompany( itemcompany );
        item.setItemgroup( itemgroup );
        Item item2 = new Item();
        item2.setName( "Duplicate Item" );
        item2.setItemCompany( itemcompany );
        item2.setItemgroup( itemgroup );
        ItemHelper helper = new ItemHelper();
        try
        {
            helper.addItem( item );
        }
        catch ( Exception e )
        {
            fail();
        }
        try
        {
            helper.addItem( item2 );
            fail();
        }
        catch ( Exception e )
        {

        }

    }

    public void testGetItem_DifferentCompany()
    {
        Item item = new Item();
        ItemHelper itemHelper = new ItemHelper();
        item.setName( "Item1" );
        item.setItemCompany( itemcompany );
        item.setItemgroup( itemgroup );
        try
        {
            itemHelper.addItem( item );
            assertNotNull( itemHelper.getItem( "Item1" ) );
            SessionUtil.setCompany( companyNegTest );
            assertNull( itemHelper.getItem( "Item1" ) );
            SessionUtil.setCompany( company );
        }
        catch ( Exception e )
        {
            fail();
        }

    }

    /**
     * Test method for {@link in.solpro.nucleus.apps.core.dbhelper.ItemHelper#addItem(in.solpro.nucleus.apps.common.Item)} .
     */

    public void testAddItem_NoName()
    {
        Item item = new Item();

        try
        {
            new ItemHelper().addItem( item );
            fail();
        }
        catch ( Exception e )
        {

        }
    }

    /**
     * Test method for {@link in.solpro.nucleus.apps.core.dbhelper.ItemHelper#addItem(in.solpro.nucleus.apps.common.Item)} .
     * @throws Exception
     */
    public void testgetItemById() throws Exception
    {
        ItemHelper helper = new ItemHelper();
        Item item = helper.getItem( helper.getItem( "Test Item" ).getId() );
        if ( item == null )
        {
            fail();
        }
        item.validateAndUpdate();
        assertEquals( item.getId(), helper.getItem( "Test Item" ).getId() );
        assertEquals( item.getName(), helper.getItem( "Test Item" ).getName() );

    }

    public void testGetItem() throws Exception
    {
        ItemHelper helper = new ItemHelper();
        Item item = helper.getItem( "Test Item" );
        if ( item == null )
        {
            fail();
        }
        item.validateAndUpdate();
        assertEquals( item.getId(), item.getId() );
        assertEquals( item.getName(), item.getName() );
    }

    /**
     * Test method for {@link in.solpro.nucleus.apps.core.dbhelper.ItemHelper#addItem(in.solpro.nucleus.apps.common.Item)} .
     */

    public void testGetItems()
    {
        List<Item> items;

        try
        {
            ItemHelper helper = new ItemHelper();
            Item item = new Item();
            item.setName( "ITEM1" );
            item.setItemCompany( itemcompany );
            item.setItemgroup( itemgroup );
            helper.addItem( item );
            Item item1 = new Item();
            item1.setName( "ITEM2" );
            item.setItemCompany( itemcompany );
            item.setItemgroup( itemgroup );
            helper.addItem( item1 );
            items = helper.getItems();
            if ( items == null )
            {
                fail();
            }
            for ( int i = 0; i < items.size(); i++ )
            {
                items.get( i ).validateAndUpdate();
                System.out.println( "Item name:" + items.get( i ).getName() + "Item ID:" + items.get( i ).getId() );
            }
        }
        catch ( Exception e )
        {
            System.out.print( "error:" );
        }
    }

    /**
     * This test verifies updateItem
     * @throws Exception
     */

    public void testUpdateItem() throws Exception
    {
        ItemHelper helper = new ItemHelper();
        Item item = (Item) helper.getItem( "Test Item" );
        item.setName( "XYZ_GROUP" );
        helper.updateItem( item );
        Item item2 = (Item) helper.getItem( "XYZ_GROUP" );
        if ( item2 == null )
        {
            fail();
        }
        assertEquals( item2.getName(), "XYZ_GROUP" );
    }

}