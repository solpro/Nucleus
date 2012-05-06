package test.dbhelper;

import in.solpro.nucleus.apps.common.Item;
import in.solpro.nucleus.apps.common.ItemCompany;
import in.solpro.nucleus.apps.common.ItemGroup;
import in.solpro.nucleus.apps.common.ItemTax;
import in.solpro.nucleus.apps.common.Ledger;
import in.solpro.nucleus.apps.common.LedgerGroup;
import in.solpro.nucleus.apps.common.LedgerGroupType;
import in.solpro.nucleus.apps.common.TaxType;
import in.solpro.nucleus.apps.core.dbhelper.ItemCompanyHelper;
import in.solpro.nucleus.apps.core.dbhelper.ItemGroupHelper;
import in.solpro.nucleus.apps.core.dbhelper.ItemHelper;
import in.solpro.nucleus.apps.core.dbhelper.ItemTaxHelper;
import in.solpro.nucleus.apps.core.dbhelper.LedgerGroupHelper;
import in.solpro.nucleus.apps.core.dbhelper.LedgerHelper;
import in.solpro.nucleus.apps.core.session.SessionUtil;

import java.util.List;

/**
 * @author asheesh
 */
public class ItemTaxHelperTest extends AbstractMasterDataHelperTest
{
    LedgerGroup ledgergroup;

    LedgerGroup negledgergroup;

    Ledger ledger;

    Ledger negledger;

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

        item = new Item();
        item.setName( "Test Item" );
        item.setItemgroup( itemgroup );
        item.setItemCompany( itemcompany );
        new ItemHelper().addItem( item );
        negitem = new Item();
        negitem.setName( "Test negitem" );
        negitem.setItemgroup( negitemgroup );
        negitem.setItemCompany( negitemcompany );
        new ItemHelper().addItem( negitem );

    }

    /**
     * Test method for {@link in.solpro.nucleus.apps.core.dbhelper.CompanyHelper#addCompany(in.solpro.nucleus.apps.common.Company)}.
     */

    public void testAddItemTax()
    {
        ItemTax ItemTax = new ItemTax();
        ItemTax.setName( "TESTItemTax1" );
        ItemTax.setType( TaxType.FIXED );
        ItemTax.setLedger( ledger );
        ItemTax.setItem( item );

        try
        {
            new ItemTaxHelper().addItemTax( ItemTax );
        }
        catch ( Exception e )
        {
            fail();
        }
    }

    public void testAddItemTax_DiffrentCompany()
    {
        ItemTax itemTax = new ItemTax();
        itemTax.setName( "Test ItemTax" );
        itemTax.setType( TaxType.FIXED );
        itemTax.setLedger( negledger );
        itemTax.setItem( negitem );
        SessionUtil.setCompany( companyNegTest );
        try
        {
            new ItemTaxHelper().addItemTax( itemTax );
        }
        catch ( Exception e )
        {
            fail();
        }
    }

    public void testDuplicateItemTax()
    {
        ItemTax itemTax = new ItemTax();
        itemTax.setName( "Duplicate ItemTax" );
        itemTax.setType( TaxType.FIXED );
        itemTax.setLedger( ledger );
        itemTax.setItem( item );
        ItemTax itemTax2 = new ItemTax();
        itemTax2.setName( "Duplicate ItemTax" );
        itemTax2.setType( TaxType.FIXED );
        itemTax2.setLedger( ledger );
        itemTax.setItem( item );
        ItemTaxHelper helper = new ItemTaxHelper();
        try
        {
            helper.addItemTax( itemTax );
        }
        catch ( Exception e )
        {
            fail();
        }
        try
        {
            helper.addItemTax( itemTax2 );
            fail();
        }
        catch ( Exception e )
        {

        }

    }

    public void testGetItemTax_DifferentCompany()
    {
        ItemTax itemTax = new ItemTax();
        ItemTaxHelper itemTaxHelper = new ItemTaxHelper();
        itemTax.setName( "Canada AddItemTax_NoCompany" );
        itemTax.setType( TaxType.FIXED );
        itemTax.setLedger( ledger );
        itemTax.setItem( item );

        try
        {
            itemTaxHelper.addItemTax( itemTax );
            assertNotNull( itemTaxHelper.getItemTax( "Canada AddItemTax_NoCompany" ) );
            SessionUtil.setCompany( companyNegTest );
            assertNull( itemTaxHelper.getItemTax( "Canada AddItemTax_NoCompany" ) );
            SessionUtil.setCompany( company );
        }
        catch ( Exception e )
        {
            fail();
        }

    }

    /**
     * Test method for {@link in.solpro.nucleus.apps.core.dbhelper.ItemTaxHelper#addItemTax(in.solpro.nucleus.apps.common.ItemTax)}.
     */

    public void testAddItemTax_NoName()
    {
        ItemTax itemTax = new ItemTax();

        try
        {
            new ItemTaxHelper().addItemTax( itemTax );
            fail();
        }
        catch ( Exception e )
        {

        }
    }

    /**
     * Test method for {@link in.solpro.nucleus.apps.core.dbhelper.ItemTaxHelper#addItemTax(in.solpro.nucleus.apps.common.ItemTax)}.
     * @throws Exception
     */
    public void testgetItemTaxById() throws Exception
    {
        ItemTaxHelper helper = new ItemTaxHelper();
        ItemTax itemTax = new ItemTax();
        itemTax.setName( "Test ItemTax" );
        itemTax.setType( TaxType.FIXED );
        itemTax.setLedger( ledger );
        itemTax.setItem( item );
        helper.addItemTax( itemTax );
        ItemTax itemTax1 = helper.getItemTax( helper.getItemTax( "Test ItemTax" ).getId() );
        if ( itemTax1 == null )
        {
            fail();
        }
        itemTax.validateAndUpdate();
        assertEquals( itemTax1.getId(), helper.getItemTax( "Test ItemTax" ).getId() );
        assertEquals( itemTax1.getName(), helper.getItemTax( "Test ItemTax" ).getName() );

    }

    public void testGetItemTax() throws Exception
    {
        ItemTaxHelper helper = new ItemTaxHelper();
        ItemTax itemTax = new ItemTax();
        itemTax.setName( "Test ItemTax" );
        itemTax.setType( TaxType.FIXED );
        itemTax.setLedger( ledger );
        itemTax.setItem( item );
        helper.addItemTax( itemTax );
        ItemTax itemTax1 = helper.getItemTax( "Test ItemTax" );
        if ( itemTax1 == null )
        {
            fail();
        }
        itemTax.validateAndUpdate();
        assertEquals( itemTax1.getId(), itemTax.getId() );
        assertEquals( itemTax1.getName(), itemTax.getName() );
    }

    /**
     * Test method for {@link in.solpro.nucleus.apps.core.dbhelper.ItemTaxHelper#addItemTax(in.solpro.nucleus.apps.common.ItemTax)}.
     */

    public void testGetItemTaxes()
    {
        List<ItemTax> ItemTaxes;

        try
        {
            ItemTaxHelper helper = new ItemTaxHelper();
            ItemTax itemTax = new ItemTax();
            itemTax.setName( "TEST ItemTax1" );
            itemTax.setType( TaxType.FIXED );
            itemTax.setLedger( ledger );
            itemTax.setItem( item );
            helper.addItemTax( itemTax );
            ItemTax ItemTax1 = new ItemTax();
            ItemTax1.setName( "TEST ItemTax2" );
            ItemTax1.setType( TaxType.FIXED );
            ItemTax1.setLedger( ledger );
            itemTax.setItem( item );
            helper.addItemTax( ItemTax1 );
            ItemTaxes = helper.getItemTaxes();
            if ( ItemTaxes == null )
            {
                fail();
            }
            System.out.println( "ItemTaxES SIZE:" + ItemTaxes.size() );
            for ( int i = 0; i < ItemTaxes.size(); i++ )
            {
                ItemTaxes.get( i ).validateAndUpdate();
                System.out.println( "ItemTax name:" + ItemTaxes.get( i ).getName() + "ItemTax ID:" + ItemTaxes.get( i ).getId() );
            }
        }
        catch ( Exception e )
        {
            System.out.print( "error:" );
        }
    }

    /**
     * This test verifies updateItemTax
     * @throws Exception
     */

    public void testUpdateItemTax() throws Exception
    {
        
        ItemTaxHelper helper = new ItemTaxHelper();
        ItemTax itemTax = new ItemTax();
        itemTax.setName( "Test ItemTax" );
        itemTax.setType( TaxType.FIXED );
        itemTax.setLedger( ledger );
        itemTax.setItem( item );
        helper.addItemTax( itemTax );
        ItemTax itemTax1 = (ItemTax) helper.getItemTax( "Test ItemTax" );
        itemTax1.setName( "XYZ" );
        helper.updateItemTax( itemTax1 );
        ItemTax ItemTax2 = (ItemTax) helper.getItemTax( "XYZ" );
        if ( ItemTax2 == null )
        {
            fail();
        }
        assertEquals( ItemTax2.getName(), "XYZ" );
    }

}