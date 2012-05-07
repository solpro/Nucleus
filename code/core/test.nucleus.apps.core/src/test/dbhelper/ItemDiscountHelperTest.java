package test.dbhelper;

import in.solpro.nucleus.apps.common.DiscountType;
import in.solpro.nucleus.apps.common.Item;
import in.solpro.nucleus.apps.common.ItemCompany;
import in.solpro.nucleus.apps.common.ItemDiscount;
import in.solpro.nucleus.apps.common.ItemGroup;
import in.solpro.nucleus.apps.core.dbhelper.ItemCompanyHelper;
import in.solpro.nucleus.apps.core.dbhelper.ItemDiscountHelper;
import in.solpro.nucleus.apps.core.dbhelper.ItemGroupHelper;
import in.solpro.nucleus.apps.core.dbhelper.ItemHelper;
import in.solpro.nucleus.apps.core.session.SessionUtil;

import java.sql.Date;
import java.util.List;

/**
 * @author asheesh
 */
@SuppressWarnings("deprecation")
public class ItemDiscountHelperTest extends AbstractMasterDataHelperTest
{
    Date dateto = new Date( 2012, 12, 15 );

    Date datefrom = new Date( 2012, 11, 15 );

    Date dateto1 = new Date( 2012, 10, 16 );

    Date datefrom1 = new Date( 2012, 9, 15 );

    ItemGroup itemgroup;

    ItemGroup negitemgroup;

    ItemCompany itemcompany;

    ItemCompany negitemcompany;

    Item item;

    Item item2;

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
        item2 = new Item();
        item2.setName( "Test Item2" );
        item2.setItemgroup( itemgroup );
        item2.setItemCompany( itemcompany );
        new ItemHelper().addItem( item2 );

        negitem = new Item();
        negitem.setName( "Test negItem" );
        negitem.setItemgroup( negitemgroup );
        negitem.setItemCompany( negitemcompany );
        SessionUtil.setCompany( companyNegTest );
        new ItemHelper().addItem( negitem );
        SessionUtil.setCompany( company );

        /*
         * ItemDiscount itemdiscount = new ItemDiscount(); itemdiscount.setItem( item ); itemdiscount.setDatefrom( datefrom );
         * itemdiscount.setDateto( dateto ); itemdiscount.setType( DiscountType.FIXED ); itemdiscount.setValue( 250 ); new
         * ItemDiscountHelper().addItemDiscount( itemdiscount );
         */
    }

    /**
     * Test method for {@link in.solpro.nucleus.apps.core.dbhelper.CompanyHelper#addCompany(in.solpro.nucleus.apps.common.Company)}.
     */

    public void testAddItemDiscount()
    {
        ItemDiscount itemdiscount = new ItemDiscount();
        itemdiscount.setItem( item );
        itemdiscount.setDatefrom( datefrom );
        itemdiscount.setDateto( dateto );
        itemdiscount.setType( DiscountType.FIXED );
        itemdiscount.setValue( 250 );
        try
        {
            new ItemDiscountHelper().addItemDiscount( itemdiscount );
        }
        catch ( Exception e )
        {
            fail();
        }
    }

    public void testAddItemDiscount_DiffrentCompany() throws Exception
    {
        ItemDiscount itemdiscount = new ItemDiscount();
        itemdiscount.setItem( item );
        itemdiscount.setDatefrom( datefrom );
        itemdiscount.setDateto( dateto );
        itemdiscount.setType( DiscountType.FIXED );
        itemdiscount.setValue( 250 );
        new ItemDiscountHelper().addItemDiscount( itemdiscount );
        ItemDiscount itemdiscount1 = new ItemDiscount();
        itemdiscount1.setItem( negitem );
        itemdiscount1.setDatefrom( datefrom );
        itemdiscount1.setDateto( dateto );
        itemdiscount1.setType( DiscountType.FIXED );
        itemdiscount1.setValue( 250 );
        SessionUtil.setCompany( companyNegTest );
        try
        {
            new ItemDiscountHelper().addItemDiscount( itemdiscount1 );
        }
        catch ( Exception e )
        {
            fail();
        }
    }

    public void testDuplicateItemDiscount()
    {
        ItemDiscount itemdiscount = new ItemDiscount();
        itemdiscount.setItem( item );
        itemdiscount.setDatefrom( datefrom );
        itemdiscount.setDateto( dateto );
        itemdiscount.setType( DiscountType.FIXED );
        itemdiscount.setValue( 250 );
        ItemDiscount itemdiscount2 = new ItemDiscount();
        itemdiscount2.setItem( item );
        itemdiscount2.setDatefrom( datefrom );
        itemdiscount2.setDateto( dateto );
        itemdiscount2.setType( DiscountType.FIXED );
        itemdiscount2.setValue( 250 );
        ItemDiscountHelper helper = new ItemDiscountHelper();
        try
        {
            helper.addItemDiscount( itemdiscount );
        }
        catch ( Exception e )
        {
            fail();
        }
        try
        {
            helper.addItemDiscount( itemdiscount2 );
            fail();
        }
        catch ( Exception e )
        {

        }

    }

    /*
     * public void testGetItemDiscount_DifferentCompany() { ItemDiscount itemdiscount = new ItemDiscount(); ItemDiscountHelper
     * itemdiscountHelper = new ItemDiscountHelper(); itemdiscount.setItem( item ); itemdiscount.setDatefrom( datefrom );
     * itemdiscount.setDateto( dateto ); itemdiscount.setType( DiscountType.FIXED ); itemdiscount.setValue( 250 ); try {
     * System.out.println("1"); itemdiscountHelper.addItemDiscount( itemdiscount ); System.out.println("2"); assertNotNull(
     * itemdiscountHelper.getItemDiscount( itemdiscount.getId() ) ); System.out.println("3"); SessionUtil.setCompany( companyNegTest );
     * System.out.println("4 "+itemdiscountHelper.getItemDiscount( itemdiscount.getId() ).getValue()); assertNull(
     * itemdiscountHelper.getItemDiscount( itemdiscount.getId() ) ); System.out.println("5"); SessionUtil.setCompany( company );
     * System.out.println("6"); } catch ( Exception e ) { fail(); } }
     */

    /**
     * Test method for
     * {@link in.solpro.nucleus.apps.core.dbhelper.ItemDiscountHelper#addItemDiscount(in.solpro.nucleus.apps.common.ItemDiscount)}.
     */

    public void testgetItemDiscountById() throws Exception
    {
        ItemDiscount itemdiscount = new ItemDiscount();
        itemdiscount.setItem( item );
        itemdiscount.setDatefrom( datefrom );
        itemdiscount.setDateto( dateto );
        itemdiscount.setType( DiscountType.FIXED );
        itemdiscount.setValue( 250 );
        new ItemDiscountHelper().addItemDiscount( itemdiscount );
        ItemDiscountHelper helper = new ItemDiscountHelper();
        ItemDiscount itemdiscount1 = helper.getItemDiscount( itemdiscount.getId() );
        if ( itemdiscount1 == null )
        {
            fail();
        }
        itemdiscount1.validateAndUpdate();
        assertEquals( itemdiscount.getId(), itemdiscount1.getId() );

    }

    /**
     * Test method for
     * {@link in.solpro.nucleus.apps.core.dbhelper.ItemDiscountHelper#addItemDiscount(in.solpro.nucleus.apps.common.ItemDiscount)}.
     */

    public void testGetAllItemDiscounts()
    {
        List<ItemDiscount> itemdiscounts;

        try
        {
            ItemDiscountHelper helper = new ItemDiscountHelper();
            ItemDiscount itemdiscount = new ItemDiscount();
            itemdiscount.setItem( item );
            itemdiscount.setDatefrom( datefrom );
            itemdiscount.setDateto( dateto );
            itemdiscount.setType( DiscountType.FIXED );
            itemdiscount.setValue( 250 );
            itemdiscount.setCompany( SessionUtil.getCompany() );
            helper.addItemDiscount( itemdiscount );
            ItemDiscount itemdiscount1 = new ItemDiscount();
            itemdiscount1.setItem( item );
            itemdiscount1.setDatefrom( datefrom1 );
            itemdiscount1.setDateto( dateto1 );
            itemdiscount1.setType( DiscountType.FIXED );
            itemdiscount1.setValue( 250 );
            itemdiscount1.setCompany( SessionUtil.getCompany() );
            helper.addItemDiscount( itemdiscount1 );
            itemdiscounts = helper.getAllItemDiscounts();
            if ( itemdiscounts == null )
            {
                fail();
            }
            for ( int i = 0; i < itemdiscounts.size(); i++ )
            {
                itemdiscounts.get( i ).validateAndUpdate();
                System.out.println( "ItemDiscount ID:" + itemdiscounts.get( i ).getId() );
            }
        }
        catch ( Exception e )
        {
            System.out.print( "error:" );
        }
    }

    public void testGetItemDiscounts()
    {
        List<ItemDiscount> itemdiscounts;

        try
        {
            ItemDiscountHelper helper = new ItemDiscountHelper();
            ItemDiscount itemdiscount = new ItemDiscount();
            itemdiscount.setItem( item );
            itemdiscount.setDatefrom( datefrom );
            itemdiscount.setDateto( dateto );
            itemdiscount.setType( DiscountType.FIXED );
            itemdiscount.setValue( 250 );
            itemdiscount.setCompany( SessionUtil.getCompany() );
            helper.addItemDiscount( itemdiscount );
            ItemDiscount itemdiscount1 = new ItemDiscount();
            itemdiscount1.setItem( item );
            itemdiscount1.setDatefrom( datefrom1 );
            itemdiscount1.setDateto( dateto1 );
            itemdiscount1.setType( DiscountType.FIXED );
            itemdiscount1.setValue( 250 );
            itemdiscount1.setCompany( SessionUtil.getCompany() );
            helper.addItemDiscount( itemdiscount1 );
            ItemDiscount itemdiscount2 = new ItemDiscount();
            itemdiscount2.setItem( item2 );
            itemdiscount2.setDatefrom( datefrom1 );
            itemdiscount2.setDateto( dateto1 );
            itemdiscount2.setType( DiscountType.FIXED );
            itemdiscount2.setValue( 250 );
            itemdiscount2.setCompany( SessionUtil.getCompany() );
            helper.addItemDiscount( itemdiscount2 );
            itemdiscounts = helper.getItemDiscounts( item2 );
            if ( itemdiscounts == null )
            {
                fail();
            }
            for ( int i = 0; i < itemdiscounts.size(); i++ )
            {
                itemdiscounts.get( i ).validateAndUpdate();
                System.out.println( "ItemDiscount ID:" + itemdiscounts.get( i ).getId() );
            }
        }
        catch ( Exception e )
        {
            System.out.print( "error:" );
        }
    }

    public void testOverLappedDatesItemDiscounts()
    {
        Date dateto2 = new Date( 2012, 11, 16 );

        Date datefrom2 = new Date( 2012, 9, 15 );
        try
        {
            ItemDiscountHelper helper = new ItemDiscountHelper();
            ItemDiscount itemdiscount = new ItemDiscount();
            itemdiscount.setItem( item );
            itemdiscount.setDatefrom( datefrom );
            itemdiscount.setDateto( dateto );
            itemdiscount.setType( DiscountType.FIXED );
            itemdiscount.setValue( 250 );
            itemdiscount.setCompany( SessionUtil.getCompany() );
            helper.addItemDiscount( itemdiscount );
            ItemDiscount itemdiscount1 = new ItemDiscount();
            itemdiscount1.setItem( item );
            itemdiscount1.setDatefrom( datefrom2 );
            itemdiscount1.setDateto( dateto2 );
            itemdiscount1.setType( DiscountType.FIXED );
            itemdiscount1.setValue( 250 );
            itemdiscount1.setCompany( SessionUtil.getCompany() );
            try
            {
                helper.addItemDiscount( itemdiscount1 );
                fail();
            }
            catch ( Exception e )
            {

            }
            ItemDiscount itemdiscount2 = new ItemDiscount();
            itemdiscount2.setItem( item2 );
            itemdiscount2.setDatefrom( datefrom1 );
            itemdiscount2.setDateto( dateto1 );
            itemdiscount2.setType( DiscountType.FIXED );
            itemdiscount2.setValue( 250 );
            itemdiscount2.setCompany( SessionUtil.getCompany() );
            helper.addItemDiscount( itemdiscount2 );
        }
        catch ( Exception e )
        {
            System.out.print( "error:" );
        }
    }

    /**
     * This test verifies updateItemDiscount
     * @throws Exception
     */

    public void testUpdateItemDiscount() throws Exception
    {
        ItemDiscountHelper helper = new ItemDiscountHelper();

        ItemDiscount itemdiscount = new ItemDiscount();
        itemdiscount.setItem( item );
        itemdiscount.setDatefrom( datefrom );
        itemdiscount.setDateto( dateto );
        itemdiscount.setType( DiscountType.FIXED );
        itemdiscount.setValue( 250 );
        helper.addItemDiscount( itemdiscount );

        ItemDiscount itemdiscount1 = (ItemDiscount) helper.getItemDiscount( itemdiscount.getId() );
        itemdiscount1.setValue( 500 );
        helper.updateItemDiscount( itemdiscount1 );
        ItemDiscount itemdiscount2 = (ItemDiscount) helper.getItemDiscount( itemdiscount.getId() );
        if ( itemdiscount2 == null )
        {
            fail();
        }
        assertEquals( itemdiscount2.getItem().getId(), itemdiscount.getItem().getId() );
    }

}