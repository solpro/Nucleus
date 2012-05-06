package test.dbhelper;

import in.solpro.nucleus.apps.common.Ledger;
import in.solpro.nucleus.apps.common.LedgerGroup;
import in.solpro.nucleus.apps.common.LedgerGroupType;
import in.solpro.nucleus.apps.common.LedgerLabel;
import in.solpro.nucleus.apps.core.dbhelper.LedgerGroupHelper;
import in.solpro.nucleus.apps.core.dbhelper.LedgerHelper;
import in.solpro.nucleus.apps.core.dbhelper.LedgerLabelHelper;
import in.solpro.nucleus.apps.core.session.SessionUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author asheesh
 */
public class LedgerHelperTest extends AbstractMasterDataHelperTest
{
    LedgerGroup ledgergroup;

    LedgerGroup negledgergroup;

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

        Ledger ledger = new Ledger();
        ledger.setName( "Test Ledger" );
        ledger.setLedgergroup( ledgergroup );
        new LedgerHelper().addLedger( ledger );
    }

    /**
     * Test method for {@link in.solpro.nucleus.apps.core.dbhelper.CompanyHelper#addCompany(in.solpro.nucleus.apps.common.Company)}.
     */

    public void testAddLedger()
    {
        Ledger p = new Ledger();
        p.setName( "Test Ledger1" );
        p.setLedgergroup( ledgergroup );
        try
        {
            new LedgerHelper().addLedger( p );
        }
        catch ( Exception e )
        {
            fail();
        }
    }

    public void testAddLedger_DiffrentCompany()
    {
        Ledger ledger = new Ledger();
        ledger.setName( "Test Ledger" );
        ledger.setLedgergroup( negledgergroup );
        SessionUtil.setCompany( companyNegTest );
        try
        {
            new LedgerHelper().addLedger( ledger );
        }
        catch ( Exception e )
        {
            fail();
        }
    }

    public void testAddMultipleLabels() throws Exception
    {
        LedgerLabelHelper helper = new LedgerLabelHelper();
        LedgerLabel testlabel = new LedgerLabel();
        testlabel.setName( "test label 2" );
        helper.addLedgerLabel( testlabel );
        LedgerLabel ledgerlabel = new LedgerLabel();
        ledgerlabel.setName( "LABEL 1" );
        helper.addLedgerLabel( ledgerlabel );
        Ledger ledger1 = new Ledger();
        ledger1.setName( "Test Ledger1" );
        ledger1.setLedgergroup( ledgergroup );
        Set<LedgerLabel> labels=new HashSet<LedgerLabel>();
        labels.add( ledgerlabel );
        labels.add( testlabel );
        ledger1.setLabels( labels );
        new LedgerHelper().addLedger( ledger1 );

    }

    public void testDuplicateLedger()
    {
        Ledger ledger = new Ledger();
        ledger.setName( "Duplicate Ledger" );
        ledger.setLedgergroup( ledgergroup );
        Ledger ledger2 = new Ledger();
        ledger2.setName( "Duplicate Ledger" );
        ledger.setLedgergroup( ledgergroup );
        LedgerHelper helper = new LedgerHelper();
        try
        {
            helper.addLedger( ledger );
        }
        catch ( Exception e )
        {
            fail();
        }
        try
        {
            helper.addLedger( ledger2 );
            fail();
        }
        catch ( Exception e )
        {

        }

    }

    public void testGetLedger_DifferentCompany()
    {
        Ledger ledger = new Ledger();
        LedgerHelper ledgerHelper = new LedgerHelper();
        ledger.setName( "Ledger1" );
        ledger.setLedgergroup( ledgergroup );
        try
        {
            ledgerHelper.addLedger( ledger );
            assertNotNull( ledgerHelper.getLedger( "Ledger1" ) );
            SessionUtil.setCompany( companyNegTest );
            assertNull( ledgerHelper.getLedger( "Ledger1" ) );
            SessionUtil.setCompany( company );
        }
        catch ( Exception e )
        {
            fail();
        }

    }

    /**
     * Test method for {@link in.solpro.nucleus.apps.core.dbhelper.LedgerHelper#addLedger(in.solpro.nucleus.apps.common.Ledger)}.
     */

    public void testAddLedger_NoName()
    {
        Ledger ledger = new Ledger();

        try
        {
            new LedgerHelper().addLedger( ledger );
            fail();
        }
        catch ( Exception e )
        {

        }
    }

    /**
     * Test method for {@link in.solpro.nucleus.apps.core.dbhelper.LedgerHelper#addLedger(in.solpro.nucleus.apps.common.Ledger)}.
     * @throws Exception
     */
    public void testgetLedgerById() throws Exception
    {
        LedgerHelper helper = new LedgerHelper();
        Ledger ledger = helper.getLedger( helper.getLedger( "Test Ledger" ).getId() );
        if ( ledger == null )
        {
            fail();
        }
        ledger.validateAndUpdate();
        assertEquals( ledger.getId(), helper.getLedger( "Test Ledger" ).getId() );
        assertEquals( ledger.getName(), helper.getLedger( "Test Ledger" ).getName() );

    }

    public void testGetLedger() throws Exception
    {
        LedgerHelper helper = new LedgerHelper();
        Ledger ledger = helper.getLedger( "Test Ledger" );
        if ( ledger == null )
        {
            fail();
        }
        ledger.validateAndUpdate();
        assertEquals( ledger.getId(), ledger.getId() );
        assertEquals( ledger.getName(), ledger.getName() );
    }

    /**
     * Test method for {@link in.solpro.nucleus.apps.core.dbhelper.LedgerHelper#addLedger(in.solpro.nucleus.apps.common.Ledger)}.
     */

    public void testGetLedgers()
    {
        List<Ledger> ledgers;

        try
        {
            LedgerHelper helper = new LedgerHelper();
            Ledger ledger = new Ledger();
            ledger.setName( "LEDGER1" );
            ledger.setLedgergroup( ledgergroup );
            helper.addLedger( ledger );
            Ledger ledger1 = new Ledger();
            ledger1.setName( "LEDGER2" );
            helper.addLedger( ledger1 );
            ledgers = helper.getLedgers();
            if ( ledgers == null )
            {
                fail();
            }
            for ( int i = 0; i < ledgers.size(); i++ )
            {
                ledgers.get( i ).validateAndUpdate();
                System.out.println( "Ledger name:" + ledgers.get( i ).getName() + "Ledger ID:" + ledgers.get( i ).getId() );
            }
        }
        catch ( Exception e )
        {
            System.out.print( "error:" );
        }
    }

    /**
     * This test verifies updateLedger
     * @throws Exception
     */

    public void testUpdateLedger() throws Exception
    {

        LedgerHelper helper = new LedgerHelper();
        Ledger ledger = (Ledger) helper.getLedger( "Test Ledger" );
        ledger.setName( "XYZ_GROUP" );
        helper.updateLedger( ledger );
        Ledger ledger2 = (Ledger) helper.getLedger( "XYZ_GROUP" );
        if ( ledger2 == null )
        {
            fail();
        }
        assertEquals( ledger2.getName(), "XYZ_GROUP" );
    }

}