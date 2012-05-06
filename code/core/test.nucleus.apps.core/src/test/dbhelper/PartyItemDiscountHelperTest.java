package test.dbhelper;

import in.solpro.nucleus.apps.common.DiscountType;
import in.solpro.nucleus.apps.common.Item;
import in.solpro.nucleus.apps.common.ItemCompany;
import in.solpro.nucleus.apps.common.ItemGroup;
import in.solpro.nucleus.apps.common.LedgerGroup;
import in.solpro.nucleus.apps.common.LedgerGroupType;
import in.solpro.nucleus.apps.common.Party;
import in.solpro.nucleus.apps.common.PartyItemDiscount;
import in.solpro.nucleus.apps.core.dbhelper.ItemCompanyHelper;
import in.solpro.nucleus.apps.core.dbhelper.ItemGroupHelper;
import in.solpro.nucleus.apps.core.dbhelper.ItemHelper;
import in.solpro.nucleus.apps.core.dbhelper.LedgerGroupHelper;
import in.solpro.nucleus.apps.core.dbhelper.PartyHelper;
import in.solpro.nucleus.apps.core.dbhelper.PartyItemDiscountHelper;
import in.solpro.nucleus.apps.core.session.SessionUtil;

import java.sql.Date;
import java.util.List;

/**
 * @author asheesh
 */
@SuppressWarnings("deprecation")
public class PartyItemDiscountHelperTest extends AbstractMasterDataHelperTest
{
    Date dateto = new Date( 2012, 12, 15 );

    Date datefrom = new Date( 2012, 11, 15 );

    Date dateto1 = new Date( 2012, 10, 16 );

    Date datefrom1 = new Date( 2012, 9, 15 );

    LedgerGroup ledgergroup;

    LedgerGroup negledgergroup;

    Party party, party2;

    Party negparty;

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

        party = new Party();
        party.setName( "Test Party" );
        party.setLedgergroup( ledgergroup );
        new PartyHelper().addParty( party );
        party2 = new Party();
        party2.setName( "Test Party2" );
        party2.setLedgergroup( ledgergroup );
        new PartyHelper().addParty( party2 );
        negparty = new Party();
        negparty.setName( "Test negParty" );
        negparty.setLedgergroup( negledgergroup );
        SessionUtil.setCompany( companyNegTest );
        new PartyHelper().addParty( negparty );
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

    public void testAddPartyItemDiscount()
    {
        PartyItemDiscount partyitemdiscount = new PartyItemDiscount();
        partyitemdiscount.setItem( item );
        partyitemdiscount.setDatefrom( datefrom );
        partyitemdiscount.setDateto( dateto );
        partyitemdiscount.setType( DiscountType.FIXED );
        partyitemdiscount.setParty( party );
        partyitemdiscount.setValue( 250 );
        try
        {
            new PartyItemDiscountHelper().addPartyItemDiscount( partyitemdiscount );
        }
        catch ( Exception e )
        {
            fail();
        }
    }

    public void testAddPartyItemDiscount_DiffrentCompany() throws Exception
    {
        PartyItemDiscount partyitemdiscount = new PartyItemDiscount();
        partyitemdiscount.setItem( item );
        partyitemdiscount.setDatefrom( datefrom );
        partyitemdiscount.setDateto( dateto );
        partyitemdiscount.setType( DiscountType.FIXED );
        partyitemdiscount.setParty( party );
        partyitemdiscount.setValue( 250 );
        new PartyItemDiscountHelper().addPartyItemDiscount( partyitemdiscount );
        PartyItemDiscount partyitemdiscount1 = new PartyItemDiscount();
        partyitemdiscount1.setItem( negitem );
        partyitemdiscount1.setDatefrom( datefrom );
        partyitemdiscount1.setDateto( dateto );
        partyitemdiscount1.setType( DiscountType.FIXED );
        partyitemdiscount1.setParty( negparty );
        partyitemdiscount1.setValue( 250 );
        SessionUtil.setCompany( companyNegTest );
        try
        {
            new PartyItemDiscountHelper().addPartyItemDiscount( partyitemdiscount1 );
        }
        catch ( Exception e )
        {
            fail();
        }
    }

    public void testDuplicatePartyItemDiscount()
    {
        PartyItemDiscount partyitemdiscount = new PartyItemDiscount();
        partyitemdiscount.setItem( item );
        partyitemdiscount.setDatefrom( datefrom );
        partyitemdiscount.setDateto( dateto );
        partyitemdiscount.setType( DiscountType.FIXED );
        partyitemdiscount.setParty( party );
        partyitemdiscount.setValue( 250 );
        PartyItemDiscount partyitemdiscount2 = new PartyItemDiscount();
        partyitemdiscount2.setItem( item );
        partyitemdiscount2.setDatefrom( datefrom );
        partyitemdiscount2.setDateto( dateto );
        partyitemdiscount2.setType( DiscountType.FIXED );
        partyitemdiscount2.setParty( party );
        partyitemdiscount2.setValue( 250 );
        PartyItemDiscountHelper helper = new PartyItemDiscountHelper();
        try
        {
            helper.addPartyItemDiscount( partyitemdiscount );
        }
        catch ( Exception e )
        {
            fail();
        }
        try
        {
            helper.addPartyItemDiscount( partyitemdiscount2 );
            fail();
        }
        catch ( Exception e )
        {

        }

    }

    public void testGetPartyItemDiscount_DifferentCompany()
    {
        PartyItemDiscount partyitemdiscount = new PartyItemDiscount();
        PartyItemDiscountHelper partyitemdiscountHelper = new PartyItemDiscountHelper();
        partyitemdiscount.setItem( item );
        partyitemdiscount.setDatefrom( datefrom );
        partyitemdiscount.setDateto( dateto );
        partyitemdiscount.setType( DiscountType.FIXED );
        partyitemdiscount.setParty( party );
        partyitemdiscount.setValue( 250 );
        try
        {
            partyitemdiscountHelper.addPartyItemDiscount( partyitemdiscount );
            assertNotNull( partyitemdiscountHelper.getPartyItemDiscount( partyitemdiscount.getId() ) );
            SessionUtil.setCompany( companyNegTest );
            try
            {
                partyitemdiscountHelper.getPartyItemDiscount( partyitemdiscount.getId()  );
            }
            catch(Exception e)
            {
                
            }
            SessionUtil.setCompany( company );

        }
        catch ( Exception e )
        {
            fail();
        }
    }

    /**
     * Test method for
     * {@link in.solpro.nucleus.apps.core.dbhelper.PartyItemDiscountHelper#addPartyItemDiscount(in.solpro.nucleus.apps.common.PartyItemDiscount)}
     * .
     */

    public void testgetPartyItemDiscountById() throws Exception
    {
        PartyItemDiscount partyitemdiscount = new PartyItemDiscount();
        partyitemdiscount.setItem( item );
        partyitemdiscount.setDatefrom( datefrom );
        partyitemdiscount.setDateto( dateto );
        partyitemdiscount.setParty( party );
        partyitemdiscount.setType( DiscountType.FIXED );
        partyitemdiscount.setValue( 250 );
        new PartyItemDiscountHelper().addPartyItemDiscount( partyitemdiscount );
        PartyItemDiscountHelper helper = new PartyItemDiscountHelper();
        PartyItemDiscount partyitemdiscount1 = helper.getPartyItemDiscount( partyitemdiscount.getId() );
        if ( partyitemdiscount1 == null )
        {
            fail();
        }
        partyitemdiscount1.validateAndUpdate();
        assertEquals( partyitemdiscount.getId(), partyitemdiscount1.getId() );

    }

    /**
     * Test method for
     * {@link in.solpro.nucleus.apps.core.dbhelper.PartyItemDiscountHelper#addPartyItemDiscount(in.solpro.nucleus.apps.common.PartyItemDiscount)}
     * .
     */

    public void testGetAllPartyItemDiscounts()
    {
        List<PartyItemDiscount> partyitemdiscounts;

        try
        {
            PartyItemDiscountHelper helper = new PartyItemDiscountHelper();
            PartyItemDiscount partyitemdiscount = new PartyItemDiscount();
            partyitemdiscount.setItem( item );
            partyitemdiscount.setDatefrom( datefrom );
            partyitemdiscount.setDateto( dateto );
            partyitemdiscount.setParty( party );
            partyitemdiscount.setType( DiscountType.FIXED );
            partyitemdiscount.setValue( 250 );
            partyitemdiscount.setCompany( SessionUtil.getCompany() );
            helper.addPartyItemDiscount( partyitemdiscount );
            PartyItemDiscount partyitemdiscount1 = new PartyItemDiscount();
            partyitemdiscount1.setItem( item );
            partyitemdiscount1.setDatefrom( datefrom1 );
            partyitemdiscount1.setDateto( dateto1 );
            partyitemdiscount1.setType( DiscountType.FIXED );
            partyitemdiscount.setParty( party );
            partyitemdiscount1.setValue( 250 );
            partyitemdiscount1.setCompany( SessionUtil.getCompany() );
            helper.addPartyItemDiscount( partyitemdiscount1 );
            partyitemdiscounts = helper.getAllPartyItemDiscounts();
            if ( partyitemdiscounts == null )
            {
                fail();
            }
            for ( int i = 0; i < partyitemdiscounts.size(); i++ )
            {
                partyitemdiscounts.get( i ).validateAndUpdate();
                System.out.println( "PartyItemDiscount ID:" + partyitemdiscounts.get( i ).getId() );
            }
        }
        catch ( Exception e )
        {
            System.out.print( "error:" );
        }
    }

    public void testGetPartyItemDiscounts()
    {
        List<PartyItemDiscount> partyitemdiscounts;

        try
        {
            PartyItemDiscountHelper helper = new PartyItemDiscountHelper();
            PartyItemDiscount partyitemdiscount = new PartyItemDiscount();
            partyitemdiscount.setItem( item );
            partyitemdiscount.setDatefrom( datefrom );
            partyitemdiscount.setDateto( dateto );
            partyitemdiscount.setType( DiscountType.FIXED );
            partyitemdiscount.setParty( party );
            partyitemdiscount.setValue( 250 );
            partyitemdiscount.setCompany( SessionUtil.getCompany() );
            helper.addPartyItemDiscount( partyitemdiscount );
            PartyItemDiscount partyitemdiscount1 = new PartyItemDiscount();
            partyitemdiscount1.setItem( item );
            partyitemdiscount1.setDatefrom( datefrom1 );
            partyitemdiscount1.setDateto( dateto1 );
            partyitemdiscount1.setType( DiscountType.FIXED );
            partyitemdiscount1.setParty( party );
            partyitemdiscount1.setValue( 250 );
            partyitemdiscount1.setCompany( SessionUtil.getCompany() );
            helper.addPartyItemDiscount( partyitemdiscount1 );
            PartyItemDiscount partyitemdiscount2 = new PartyItemDiscount();
            partyitemdiscount2.setItem( item2 );
            partyitemdiscount2.setDatefrom( datefrom1 );
            partyitemdiscount2.setDateto( dateto1 );
            partyitemdiscount2.setType( DiscountType.FIXED );
            partyitemdiscount2.setParty( party2 );
            partyitemdiscount2.setValue( 250 );
            partyitemdiscount2.setCompany( SessionUtil.getCompany() );
            helper.addPartyItemDiscount( partyitemdiscount2 );
            partyitemdiscounts = helper.getPartyItemDiscounts( party );
            if ( partyitemdiscounts == null )
            {
                fail();
            }
            for ( int i = 0; i < partyitemdiscounts.size(); i++ )
            {
                partyitemdiscounts.get( i ).validateAndUpdate();
                System.out.println( "PartyItemDiscount ID:" + partyitemdiscounts.get( i ).getId() );
            }
        }
        catch ( Exception e )
        {
            System.out.print( "error:" );
        }
    }

    public void testOverLappedDatesPartyItemDiscounts() throws Exception
    {
        Date dateto2 = new Date( 2012, 11, 16 );

        Date datefrom2 = new Date( 2012, 9, 15 );
        PartyItemDiscountHelper helper = new PartyItemDiscountHelper();
        PartyItemDiscount partyitemdiscount = new PartyItemDiscount();
        partyitemdiscount.setItem( item );
        partyitemdiscount.setDatefrom( datefrom );
        partyitemdiscount.setDateto( dateto );
        partyitemdiscount.setType( DiscountType.FIXED );
        partyitemdiscount.setParty( party );
        partyitemdiscount.setValue( 250 );
        partyitemdiscount.setCompany( SessionUtil.getCompany() );
        helper.addPartyItemDiscount( partyitemdiscount );
        PartyItemDiscount partyitemdiscount1 = new PartyItemDiscount();
        partyitemdiscount1.setItem( item );
        partyitemdiscount1.setDatefrom( datefrom2 );
        partyitemdiscount1.setDateto( dateto2 );
        partyitemdiscount1.setParty( party );
        partyitemdiscount1.setType( DiscountType.FIXED );
        partyitemdiscount1.setValue( 250 );
        partyitemdiscount1.setCompany( SessionUtil.getCompany() );
        try
        {
            helper.addPartyItemDiscount( partyitemdiscount1 );
            fail();
        }
        catch ( Exception e )
        {

        }
        PartyItemDiscount partyitemdiscount2 = new PartyItemDiscount();
        partyitemdiscount2.setItem( item2 );
        partyitemdiscount2.setDatefrom( datefrom1 );
        partyitemdiscount2.setDateto( dateto1 );
        partyitemdiscount2.setType( DiscountType.FIXED );
        partyitemdiscount2.setParty( party );
        partyitemdiscount2.setValue( 250 );
        partyitemdiscount2.setCompany( SessionUtil.getCompany() );
        helper.addPartyItemDiscount( partyitemdiscount2 );

    }

    /**
     * This test verifies updatePartyItemDiscount
     * @throws Exception
     */

    public void testUpdatePartyItemDiscount() throws Exception
    {
        PartyItemDiscountHelper helper = new PartyItemDiscountHelper();

        PartyItemDiscount partyitemdiscount = new PartyItemDiscount();
        partyitemdiscount.setItem( item );
        partyitemdiscount.setDatefrom( datefrom );
        partyitemdiscount.setDateto( dateto );
        partyitemdiscount.setType( DiscountType.FIXED );
        partyitemdiscount.setParty( party );
        partyitemdiscount.setValue( 250 );
        helper.addPartyItemDiscount( partyitemdiscount );

        PartyItemDiscount partyitemdiscount1 = (PartyItemDiscount) helper.getPartyItemDiscount( partyitemdiscount.getId() );
        partyitemdiscount1.setValue( 500 );
        helper.updatePartyItemDiscount( partyitemdiscount1 );
        PartyItemDiscount partyitemdiscount2 = (PartyItemDiscount) helper.getPartyItemDiscount( partyitemdiscount.getId() );
        if ( partyitemdiscount2 == null )
        {
            fail();
        }
        assertEquals( partyitemdiscount2.getItem().getId(), partyitemdiscount.getItem().getId() );
    }

}