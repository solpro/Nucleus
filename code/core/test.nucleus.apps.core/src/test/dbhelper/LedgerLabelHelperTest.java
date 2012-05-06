package test.dbhelper;

import in.solpro.nucleus.apps.common.Ledger;
import in.solpro.nucleus.apps.common.LedgerGroup;
import in.solpro.nucleus.apps.common.LedgerGroupType;
import in.solpro.nucleus.apps.common.LedgerLabel;
import in.solpro.nucleus.apps.core.dbhelper.LedgerGroupHelper;
import in.solpro.nucleus.apps.core.dbhelper.LedgerHelper;
import in.solpro.nucleus.apps.core.dbhelper.LedgerLabelHelper;
import in.solpro.nucleus.apps.core.session.SessionUtil;

import java.util.List;

/**
 * @author asheesh
 */
public class LedgerLabelHelperTest extends AbstractMasterDataHelperTest
{
    LedgerGroup ledgergroup;
    LedgerGroup negledgergroup;
    Ledger ledger;
    Ledger negledger;
    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        ledgergroup= new LedgerGroup();
        ledgergroup.setName( "Test LedgerGroup" );
        ledgergroup.setType( LedgerGroupType.EXPENSES );
        new LedgerGroupHelper().addLedgerGroup( ledgergroup );
        negledgergroup= new LedgerGroup();
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
   
        LedgerLabel ledgerlabel=new LedgerLabel();
        ledgerlabel.setName( "Test LedgerLabel" );
        ledgerlabel.setLedger( ledger );
        new LedgerLabelHelper().addLedgerLabel( ledgerlabel );
    }

    /**
     * Test method for {@link in.solpro.nucleus.apps.core.dbhelper.CompanyHelper#addCompany(in.solpro.nucleus.apps.common.Company)}.
     */

    public void testAddLedgerLabel()
    {
        LedgerLabel p = new LedgerLabel();
        p.setName( "Test LedgerLabel1" );
        p.setLedger( ledger );
        try
        {
            new LedgerLabelHelper().addLedgerLabel( p );
        }
        catch ( Exception e )
        {
            fail();
        }
    }

    public void testAddLedgerLabel_DiffrentCompany()
    {
        LedgerLabel ledgerlabel = new LedgerLabel();
        ledgerlabel.setName( "Test LedgerLabel" );
        ledgerlabel.setLedger( negledger );
        SessionUtil.setCompany( companyNegTest );
        try
        {
            new LedgerLabelHelper().addLedgerLabel( ledgerlabel );
        }
        catch ( Exception e )
        {
            fail();
        }
    }

    public void testDuplicateLedgerLabel()
    {
        LedgerLabel ledgerlabel = new LedgerLabel();
        ledgerlabel.setName( "Duplicate LedgerLabel" );
        ledgerlabel.setLedger( ledger );
        LedgerLabel ledgerlabel2 = new LedgerLabel();
        ledgerlabel2.setName( "Duplicate LedgerLabel" );
        ledgerlabel.setLedger( ledger );
        LedgerLabelHelper helper = new LedgerLabelHelper();
        try
        {
            helper.addLedgerLabel( ledgerlabel );
        }
        catch ( Exception e )
        {
            fail();
        }
        try
        {
            helper.addLedgerLabel( ledgerlabel2 );
            fail();
        }
        catch ( Exception e )
        {

        }

    }

    public void testGetLedgerLabel_DifferentCompany()
    {
        LedgerLabel ledgerlabel = new LedgerLabel();
        LedgerLabelHelper ledgerlabelHelper = new LedgerLabelHelper();
        ledgerlabel.setName( "LedgerLabel1" );
        ledgerlabel.setLedger( ledger );
        try
        {
            ledgerlabelHelper.addLedgerLabel( ledgerlabel );
            assertNotNull( ledgerlabelHelper.getLedgerLabel( "LedgerLabel1" ) );
            SessionUtil.setCompany( companyNegTest );
            assertNull( ledgerlabelHelper.getLedgerLabel( "LedgerLabel1" ) );
            SessionUtil.setCompany( company );
        }
        catch ( Exception e )
        {
            fail();
        }

    }

    /**
     * Test method for
     * {@link in.solpro.nucleus.apps.core.dbhelper.LedgerLabelHelper#addLedgerLabel(in.solpro.nucleus.apps.common.LedgerLabel)}.
     */

    public void testAddLedgerLabel_NoName()
    {
        LedgerLabel ledgerlabel = new LedgerLabel();

        try
        {
            new LedgerLabelHelper().addLedgerLabel( ledgerlabel );
            fail();
        }
        catch ( Exception e )
        {

        }
    }

    /**
     * Test method for
     * {@link in.solpro.nucleus.apps.core.dbhelper.LedgerLabelHelper#addLedgerLabel(in.solpro.nucleus.apps.common.LedgerLabel)}.
     * @throws Exception
     */
    public void testgetLedgerLabelById() throws Exception
    {
        LedgerLabelHelper helper = new LedgerLabelHelper();
        LedgerLabel ledgerlabel = helper.getLedgerLabel( helper.getLedgerLabel( "Test LedgerLabel" ).getId() );
        if ( ledgerlabel == null )
        {
            fail();
        }
        ledgerlabel.validateAndUpdate();
        assertEquals( ledgerlabel.getId(), helper.getLedgerLabel( "Test LedgerLabel" ).getId() );
        assertEquals( ledgerlabel.getName(), helper.getLedgerLabel( "Test LedgerLabel" ).getName() );

    }

    public void testGetLedgerLabel() throws Exception
    {
        LedgerLabelHelper helper = new LedgerLabelHelper();
        LedgerLabel ledgerlabel = helper.getLedgerLabel( "Test LedgerLabel" );
        if ( ledgerlabel == null )
        {
            fail();
        }
        ledgerlabel.validateAndUpdate();
        assertEquals( ledgerlabel.getId(), ledgerlabel.getId() );
        assertEquals( ledgerlabel.getName(), ledgerlabel.getName() );
    }

    /**
     * Test method for
     * {@link in.solpro.nucleus.apps.core.dbhelper.LedgerLabelHelper#addLedgerLabel(in.solpro.nucleus.apps.common.LedgerLabel)}.
     * @throws Exception 
     */

      public void testAddMultipleLedgers() throws Exception
    {
          Ledger testledger=new Ledger();
          testledger.setName( "test ledger 2" );
          testledger.setLedgergroup( ledgergroup );
          new LedgerHelper().addLedger( testledger );
          LedgerLabelHelper helper = new LedgerLabelHelper();
          LedgerLabel ledgerlabel = new LedgerLabel();
          ledgerlabel.setName( "LABEL1" );
          ledgerlabel.setLedger( ledger );
          ledgerlabel.setLedger( testledger );
          helper.addLedgerLabel( ledgerlabel );
       
    }
    public void testGetLedgerLabels()
    {
        List<LedgerLabel> ledgerlabels;

        try
        {
            LedgerLabelHelper helper = new LedgerLabelHelper();
            LedgerLabel ledgerlabel = new LedgerLabel();
            ledgerlabel.setName( "LEDGER1" );
            ledgerlabel.setLedger( ledger );
            helper.addLedgerLabel( ledgerlabel );
            LedgerLabel ledgerlabel1 = new LedgerLabel();
            ledgerlabel1.setName( "LEDGER2" );
            ledgerlabel.setLedger( ledger );
            helper.addLedgerLabel( ledgerlabel1 );
            ledgerlabels = helper.getLedgerLabels();
            if ( ledgerlabels == null )
            {
                fail();
            }
            for ( int i = 0; i < ledgerlabels.size(); i++ )
            {
                ledgerlabels.get( i ).validateAndUpdate();
                System.out.println( "LedgerLabel name:" + ledgerlabels.get( i ).getName() + "LedgerLabel ID:"
                        + ledgerlabels.get( i ).getId() );
            }
        }
        catch ( Exception e )
        {
            System.out.print( "error:" );
        }
    }

    /**
     * This test verifies updateLedgerLabel
     * @throws Exception
     */

    public void testUpdateLedgerLabel() throws Exception
    {

        LedgerLabelHelper helper = new LedgerLabelHelper();
        LedgerLabel ledgerlabel = (LedgerLabel) helper.getLedgerLabel( "Test LedgerLabel" );
        ledgerlabel.setName( "XYZ_LABEL" );
        helper.updateLedgerLabel( ledgerlabel );
        LedgerLabel ledgerlabel2 = (LedgerLabel) helper.getLedgerLabel( "XYZ_LABEL" );
        if ( ledgerlabel2 == null )
        {
            fail();
        }
        assertEquals( ledgerlabel2.getName(), "XYZ_LABEL" );
    }

}